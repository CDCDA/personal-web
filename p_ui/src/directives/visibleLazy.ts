// 进入可视页面再加载组件的指令
import { Directive } from 'vue';
export const visibleLazy: Directive = {
  beforeMount(el, binding: any) {
    console.log('触发监听');
    if (!binding.value) return;
    el.style.display = 'none';
    const io = new IntersectionObserver(
      (changes, observer) => {
        console.log('元素进入视野', changes, observer);
        if (changes[0].isIntersecting) {
          el.style.display = 'block';
          io.disconnect();
        }
      },
      { threshold: 0.5 }
    );
    io.observe(el);
  }
};
