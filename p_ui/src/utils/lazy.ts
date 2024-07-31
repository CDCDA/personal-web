/*
 * @Description:
 */
/*
 * @Description:
 */

import { autoClearTimer } from './timer';

//进入视野加载动画
export const useLazyAppear = (el: Element, delay: any = 0, animationName: string = 'appear') => {
  if (!el) return;
  //监听组件是否进入可视区域
  const io = new IntersectionObserver(entries => {
    if (entries[0].isIntersecting) {
      //解除监听
      io.disconnect();
      autoClearTimer(() => {
        el.classList.add(animationName);
        autoClearTimer(() => {
          el.classList.remove(animationName);
        }, 1000);
      }, delay);
    }
  });
  io.observe(el);
};
