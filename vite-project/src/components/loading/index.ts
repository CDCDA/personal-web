import { createApp, Directive } from 'vue';
import Loading from './loading.vue';

export const isEmptyObj = (obj: any) => {
  return JSON.stringify(obj) === '{}';
};
/** v-eLoading:[loadingConfig]="state.l||state.a */
const loading: Directive = {
  mounted(el, binding) {
    const app = createApp(Loading);
    const instance = app.mount(document.createElement('div')) as any;
    el.instance = instance;
    el.style.position = 'relative';
    const param = binding.value;
    if (!isEmptyObj(param as any)) {
      const options = {
        type: param
      };
      instance.updateInfo(options);
    }

    if (binding.value) {
      appendEl(el);
    }
  },
  updated(el, binding) {
    if (binding.value !== binding.oldValue) {
      binding.value ? appendEl(el) : removeEl(el);
    }
  }
};
const appendEl = (el: { appendChild: (arg0: any) => void; instance: { $el: any } }) => {
  el.appendChild(el.instance.$el);
};
const removeEl = (el: { removeChild: (arg0: any) => void; instance: { $el: any } }) => {
  el.removeChild(el.instance.$el);
};
export default loading;
