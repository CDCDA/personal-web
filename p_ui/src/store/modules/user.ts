/*
 * @Description:登录用户数据
 */

import { defineStore } from 'pinia';
import { computed, reactive, ref } from 'vue';

interface T {
  setGlobalState: any;
}

const useUserStore = defineStore('user', () => {
  const userName = ref('');
  const avatar = ref('');
  const userId = ref('');
  const token = ref('');
  const permission = ref([] as any);
  return {
    userName,
    token,
    permission,
    userId,
    avatar
  };
});

export default useUserStore;
