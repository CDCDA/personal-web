/*
 * @Description:权限控制
 */
import useUserStore from '@/store/modules/user';
import { ElMessage } from 'element-plus';

export const permission = {
  mounted(el: any, bindling: any) {
    const userStore = useUserStore();
    //bindling.value为指令的绑定值
    let perVal = bindling.value;
    if (!perVal) return;
    if (bindling.value) {
      //假设某用户对某模块只有添加和删除的权限
      //这个权限信息(即pers)应该是不同用户登录时从后台拿到的对应的信息
      let pers = userStore.permission || [];
      //hasPer为true为有权限
      //hasPer为false为无权限
      let hasPer = pers.some((item: any) => {
        return item == perVal;
      });
      if (perVal == 'show') {
        if (!hasPer) {
          el.remove();
        }
      } else if (perVal == 'operate') {
        if (!hasPer) {
          el.setAttribute('disabled', true);
          ElMessage.error('游客登录无法操作');
        }
      }
    }
  }
};
