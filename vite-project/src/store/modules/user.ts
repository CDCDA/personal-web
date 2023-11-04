/*
 * @Description:登录用户数据
 */

import { defineStore } from 'pinia';
import { computed, reactive, ref } from 'vue';

interface T {
  setGlobalState: any;
}

const useUserStore = defineStore('user', () => {
  const props = reactive<T>({} as T);
  const userName = ref('');
  const userId = ref('1');
  const token = ref('');
  const permission = ref(['add', 'delete']);
  const isToken = computed(() => `${userName.value}`);
  return {
    isToken,
    userName,
    token,
    props,
    permission,
    userId
  };
});

export default useUserStore;
