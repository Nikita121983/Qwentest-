name: Feature Request
description: Предложить новую функцию
labels: [enhancement]
body:
  - type: markdown
    attributes:
      value: |
        Спасибо за идею! Расскажите подробнее о предлагаемой функции.

  - type: textarea
    id: problem
    attributes:
      label: Проблема
      description: Какую проблему решает эта функция?
      placeholder: Я всегда сталкиваюсь с...
    validations:
      required: true

  - type: textarea
    id: solution
    attributes:
      label: Решение
      description: Как должна работать функция?
      placeholder: Хотелось бы, чтобы программа могла...
    validations:
      required: true

  - type: textarea
    id: alternatives
    attributes:
      label: Альтернативы
      description: Какие есть обходные пути?
      placeholder: Сейчас я делаю так...

  - type: dropdown
    id: priority
    attributes:
      label: Приоритет
      description: Насколько важна эта функция?
      options:
        - Низкий (хотелось бы)
        - Средний (будет полезно)
        - Высокий (критично для работы)
    validations:
      required: true
