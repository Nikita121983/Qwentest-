//Multi Find, Vincent Greco, 2023-24
"use strict";

if (!customElements.get('multi-find-0-extension')) {
  class MultiFind0Extension extends HTMLElement {
    constructor() {
      super();
    }
  }
  customElements.define('multi-find-0-extension', MultiFind0Extension);
}

if (!customElements.get('multi-find-1-extension')) {
  class MultiFind1Extension extends HTMLElement {
    constructor() {
      super();
    }
  }
  customElements.define('multi-find-1-extension', MultiFind1Extension);
}

if (!customElements.get('multi-find-3-extension')) {
  class MultiFind3Extension extends HTMLElement {
    constructor() {
      super();
    }
  }
  customElements.define('multi-find-3-extension', MultiFind3Extension);
}