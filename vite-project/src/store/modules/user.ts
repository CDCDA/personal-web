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
  const username = ref('');
  const userId = ref('1');
  const token = ref('');
  const permission = ref(['add', 'delete']);
  const isToken = computed(() => `${username.value}`);
  return {
    isToken,
    username,
    token,
    props,
    permission,
    userId
  };
});

export default useUserStore;
