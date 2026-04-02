name: Bug Report
description: Создать отчёт об ошибке
labels: [bug]
body:
  - type: markdown
    attributes:
      value: |
        Спасибо за отчёт! Пожалуйста, заполните форму подробно.

  - type: input
    id: version
    attributes:
      label: Версия программы
      description: Какую версию используете?
      placeholder: например, 0.1.0
    validations:
      required: true

  - type: input
    id: os
    attributes:
      label: Операционная система
      description: На какой ОС произошла ошибка?
      placeholder: Windows 11, macOS 14, Ubuntu 22.04
    validations:
      required: true

  - type: textarea
    id: what-happened
    attributes:
      label: Что произошло?
      description: Опишите проблему подробно.
      placeholder: Ожидал..., но произошло...
    validations:
      required: true

  - type: textarea
    id: reproduce
    attributes:
      label: Шаги воспроизведения
      description: Как повторить ошибку?
      placeholder: |
        1. Открыть...
        2. Нажать...
        3. Ввести...
        4. Ошибка!
    validations:
      required: true

  - type: textarea
    id: logs
    attributes:
      label: Логи/скриншоты
      description: Приложите логи или скриншоты.
      render: shell
