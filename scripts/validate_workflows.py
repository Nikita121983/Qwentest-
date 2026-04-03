#!/usr/bin/env python3
"""
Валидация GitHub Actions workflow файлов.

Проверяет:
1. YAML синтаксис
2. Обязательные поля (name, on, jobs)
3. Правильность if: условий (hashFiles только в step-level)
4. Наличие required полей в jobs (runs-on, steps)
5. Правильность uses: версий (не использовать @master)
6. continue-on-error для опциональных шагов (Codecov)

Использование:
    python scripts/validate_workflows.py
    python scripts/validate_workflows.py .github/workflows/ci.yml
"""

import sys
import yaml
from pathlib import Path
from typing import Optional


class WorkflowError:
    def __init__(
        self, file: str, line: Optional[int], message: str, severity: str = "error"
    ):
        self.file = file
        self.line = line
        self.message = message
        self.severity = severity

    def __str__(self) -> str:
        loc = f":{self.line}" if self.line else ""
        return f"{self.severity.upper()} {self.file}{loc}: {self.message}"


class WorkflowValidator:
    """Валидатор GitHub Actions workflow файлов."""

    # hashFiles нельзя использовать в job-level if
    HASHFILES_IN_JOB_IF = (
        "hashFiles нельзя использовать в job-level if. Используйте step-level проверку."
    )

    # Обязательные поля workflow
    REQUIRED_WORKFLOW_KEYS = {"name", "on", "jobs"}

    # Обязательные поля job
    REQUIRED_JOB_KEYS = {"runs-on", "steps"}

    # Обязательные поля step
    REQUIRED_STEP_KEYS = {"uses", "run"}  # хотя бы одно

    def __init__(self):
        self.errors: list[WorkflowError] = []

    def validate_file(self, filepath: Path) -> list[WorkflowError]:
        """Валидировать один workflow файл."""
        try:
            with open(filepath, "r", encoding="utf-8") as f:
                content = f.read()
                data = yaml.safe_load(content)
        except yaml.YAMLError as e:
            self.errors.append(
                WorkflowError(
                    str(filepath), getattr(e, "problem_mark", None), f"YAML ошибка: {e}"
                )
            )
            return self.errors

        if not isinstance(data, dict):
            self.errors.append(
                WorkflowError(str(filepath), None, "Workflow должен быть YAML объектом")
            )
            return self.errors

        self._check_workflow_structure(data, str(filepath))
        self._check_jobs(data.get("jobs", {}), str(filepath))
        return self.errors

    def _check_workflow_structure(self, data: dict, filepath: str):
        """Проверить обязательные поля workflow."""
        for key in self.REQUIRED_WORKFLOW_KEYS:
            if key not in data:
                # 'on' может быть распарсен как True
                if key == "on" and True in data:
                    continue
                self.errors.append(
                    WorkflowError(
                        filepath, None, f"Отсутствует обязательное поле: '{key}'"
                    )
                )

    def _check_jobs(self, jobs: dict, filepath: str):
        """Проверить все jobs."""
        for job_name, job in jobs.items():
            if not isinstance(job, dict):
                self.errors.append(
                    WorkflowError(
                        filepath, None, f"Job '{job_name}' должен быть объектом"
                    )
                )
                continue

            # Проверка job-level if: на hashFiles
            if "if" in job:
                if_val = str(job["if"])
                if "hashFiles" in if_val:
                    self.errors.append(
                        WorkflowError(
                            filepath,
                            None,
                            f"Job '{job_name}': {self.HASHFILES_IN_JOB_IF}",
                        )
                    )

            # Проверка обязательных полей job
            for key in self.REQUIRED_JOB_KEYS:
                if key not in job:
                    self.errors.append(
                        WorkflowError(
                            filepath,
                            None,
                            f"Job '{job_name}': отсутствует обязательное поле '{key}'",
                        )
                    )

            # Проверка steps
            steps = job.get("steps", [])
            if isinstance(steps, list):
                self._check_steps(steps, filepath, job_name)

    def _check_steps(self, steps: list, filepath: str, job_name: str):
        """Проверить все steps в job."""
        for i, step in enumerate(steps):
            if not isinstance(step, dict):
                self.errors.append(
                    WorkflowError(
                        filepath,
                        None,
                        f"Job '{job_name}', step {i}: должен быть объектом",
                    )
                )
                continue

            # Step должен иметь uses или run
            has_uses = "uses" in step
            has_run = "run" in step
            if not has_uses and not has_run:
                self.errors.append(
                    WorkflowError(
                        filepath,
                        None,
                        f"Job '{job_name}', step {i}: должен иметь 'uses' или 'run'",
                    )
                )

            # Проверка if: в step на hashFiles (допустимо, но предупредить)
            if "if" in step:
                if_val = str(step["if"])
                if "hashFiles" in if_val and "${{" not in if_val:
                    self.errors.append(
                        WorkflowError(
                            filepath,
                            None,
                            f"Job '{job_name}', step {i}: hashFiles должен быть в ${{{{ }}}}",
                        )
                    )

            # Проверка Codecov без continue-on-error
            if step.get("uses", "").startswith("codecov/"):
                if not step.get("continue-on-error"):
                    self.errors.append(
                        WorkflowError(
                            filepath,
                            None,
                            f"Job '{job_name}', step '{step.get('name', i)}': "
                            f"Codecov шаг должен иметь continue-on-error: true",
                        )
                    )

            # Проверка uses: @master (лучше использовать теги версий)
            uses = step.get("uses", "")
            if uses.endswith("@master") or uses.endswith("@main"):
                self.errors.append(
                    WorkflowError(
                        filepath,
                        None,
                        f"Job '{job_name}', step '{step.get('name', i)}': "
                        f"Используйте тег версии вместо @master/@main: {uses}",
                    )
                )

            # Проверка working-directory в step с run
            if "run" in step and "working-directory" not in step:
                run_val = step["run"]
                if "./gradlew" in run_val or "gradle " in run_val:
                    if "chmod" not in run_val:
                        # Gradle без gradlew — ок, но предупредить
                        pass


def main():
    """Точка входа."""
    workflows_dir = Path(".github/workflows")

    if len(sys.argv) > 1:
        files = [Path(f) for f in sys.argv[1:]]
    else:
        files = list(workflows_dir.glob("*.yml")) + list(workflows_dir.glob("*.yaml"))

    if not files:
        print("⚠️  Workflow файлы не найдены")
        sys.exit(0)

    validator = WorkflowValidator()

    for filepath in files:
        if filepath.exists():
            validator.validate_file(filepath)

    if validator.errors:
        print(f"\n❌ Найдено {len(validator.errors)} ошибок:\n")
        for error in validator.errors:
            print(f"  {error}")
        print()
        sys.exit(1)
    else:
        print(f"✅ Все {len(files)} workflow файлы валидны")
        sys.exit(0)


if __name__ == "__main__":
    main()
