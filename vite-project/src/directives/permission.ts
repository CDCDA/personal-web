/*
 * @Description:权限控制
 */
import useUserStore from '@/store/modules/user';

export const permission = {
  inserted(el: any, bindling: any) {
    alert('SS');
    const userStore = useUserStore();
    //bindling.value为指令的绑定值
    let perVal = bindling.value;
    if (bindling.value) {
      //假设某用户对某模块只有添加和删除的权限
      //这个权限信息(即pers)应该是不同用户登录时从后台拿到的对应的信息
      let pers = userStore.permission;
      //hasPer为true为有权限
      //hasPer为false为无权限
      let hasPer = pers.some(item => {
        return item == perVal;
      });
      //没有权限移除此元素
      if (!hasPer) {
        el.remove();
      }
    }
  }
};
