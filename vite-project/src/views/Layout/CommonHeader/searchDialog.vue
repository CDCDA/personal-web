<!--
 * @Description: 换肤 
-->
<template>
  <el-dialog
    class="skin-change"
    v-model="dialogVisible"
    title="搜索"
    style="height: 500px"
    width="800px"
  >
  </el-dialog>
</template>

<script setup lang="ts">
import { reactive, ref, onMounted } from 'vue';

const dialogVisible = ref(false);
const activeName = ref('theme');
var activeBack = {
  key: '4',
  label: '',
  background: require('../../../assets/images/bk-3.jpg'),
  url: '/src/assets/images/bk-3.jpg',
  active: false
} as any;
var activeTheme = {
  key: 'theme-dark',
  label: '深色',
  background: require('../../../assets/images/bk-3.jpg'),
  active: true
} as any;
const options = reactive({
  //是否开启粒子特效
  isParticles: true,
  //主题
  themes: [
    {
      key: 'theme-dark',
      label: '深色',
      background: require('../../../assets/images/bk-3.jpg'),
      active: true
    },
    {
      key: 'theme-light',
      label: '浅色',
      background: require('../../../assets/images/bk-4.jpg'),
      active: false
    }
  ],
  //背景图
  backs: [
    {
      key: '1',
      label: '未来世界',
      background: require('../../../assets/images/bk-1.jpg'),
      url: '/src/assets/images/bk-1.jpg',
      active: true
    },
    {
      key: '2',
      label: '太空飞船',
      background: require('../../../assets/images/bk-2.jpg'),
      url: '/src/assets/images/bk-2.jpg',
      active: false
    },
    {
      key: '3',
      label: '赛博城市',
      background: require('../../../assets/images/cyberpunk-city.jpg'),
      url: '/src/assets/images/cyberpunk-city.jpg',
      active: false
    },
    {
      key: '4',
      label: '星河瀑布',
      background: require('../../../assets/images/bk-3.jpg'),
      url: '/src/assets/images/bk-3.jpg',
      active: false
    },
    {
      key: '5',
      label: '失落方舟',
      background: require('../../../assets/images/bk-4.jpg'),
      url: '/src/assets/images/bk-4.jpg',
      active: false
    }
    // {
    //   key: '6',
    //   label: '',
    //   background: 'white',
    //   url: 'white',
    //   active: false
    // }
  ]
});

const fits = ['fill', 'contain', 'cover', 'none', 'scale-down'];

function close() {
  dialogVisible.value = false;
}
function open() {
  dialogVisible.value = true;
}

function submit() {
  let back = document.getElementById('tsparticles') as any;
  let themeKey = activeTheme.key as any;
  let backUrl = activeBack.url as any;
  (window.localStorage as any).setItem('themeKey', themeKey);
  (window.localStorage as any).setItem('backUrl', backUrl);
  (document.getElementById('app-theme') as any).setAttribute('data-theme', themeKey);
  back.style.background = 'left/cover fixed no-repeat url(' + backUrl + ')';
}

function changeTheme(item: any) {
  options.themes.forEach((theme: any) => {
    theme.active = false;
  });
  item.active = true;
  activeTheme = item;
}

function changeBack(item: any) {
  options.backs.forEach((back: any) => {
    back.active = false;
  });
  item.active = true;
  activeBack = item;
}

function preview(val: any) {
  console.log('aaaa', val);
}

onMounted(() => {
  //
});
defineExpose({
  close,
  open
});
</script>
<style lang="scss">
@include theme() {
}
</style>
