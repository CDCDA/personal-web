/*
 * @Description:
 */
/*
 * @Description:主题数据
 */

import { defineStore } from 'pinia';
import { computed, reactive, ref } from 'vue';

interface T {
  setGlobalState: any;
}

const useThemeStore = defineStore('theme', () => {
  const props = reactive<T>({} as T);
  //主体
  const theme = ref('theme-light');
  //背景类型
  const backType = ref('img');
  //外观设置
  const aspectOptions = ref(true as any);
  return {
    theme,
    backType,
    props,
    aspectOptions
  };
});

export default useThemeStore;
