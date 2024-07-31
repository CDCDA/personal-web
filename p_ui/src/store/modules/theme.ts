/*
 * @Description:主题数据
 */

import { defineStore } from 'pinia';
import { computed, reactive, ref } from 'vue';

interface T {
  setGlobalState: any;
}

const useThemeStore = defineStore('theme', () => {
  const isShow = ref(false);
  //主题风格
  const theme = ref('theme-white');
  //背景类型
  const backType = ref('img');
  //背景url
  const backUrl = ref('../src/assets/images/123123123.jpg');
  //外观设置
  const options = ref({
    //粒子特效
    isParticles: false,
    //樱花特效
    isSakura: false,
    //首页字体颜色
    mhFontColor: '#fff',
    //字体
    fontFamily: 'Shark'
  } as any);
  return {
    isShow,
    theme,
    backType,
    options,
    backUrl
  };
});

export default useThemeStore;
