<!--
 * @Description: 换肤 
-->
<template>
  <el-dialog
    class="skin-change"
    v-model="dialogVisible"
    title="换肤"
    style="height: 500px"
    width="800px"
  >
    <el-tabs v-model="activeName" class="skin-tabs">
      <el-tab-pane label="主题" name="theme">
        <div class="theme-main">
          <div
            v-for="item in options.themes"
            :class="['theme-item', item.active ? 'theme-item-active' : '']"
            @click="changeTheme(item)"
          >
            <el-image
              class="theme-image"
              style="width: 150px; height: 90px; margin: 2px; border-radius: 3px"
              :src="item.background"
              :fit="fits"
            />
            <span class="theme-label">{{ item.label }}</span>
          </div>
        </div></el-tab-pane
      >
      <el-tab-pane label="背景" name="back"
        ><div class="back-main">
          <div
            v-for="item in options.backs"
            :class="['back-item', item.active ? 'back-item-active' : '']"
            @click="changeBack(item)"
          >
            <el-image
              class="back-image"
              style="width: 150px; height: 90px"
              :src="item.background"
              :preview-src-list="[item.background]"
              :fit="fits"
            />
            <!-- <span class="preview"><span class="preview-text" @click="preview">预览</span></span> -->
            <span class="back-label">{{ item.label }}</span>
          </div>
        </div></el-tab-pane
      >
      <el-tab-pane label="其他" name="other">
        <el-form :model="options" class="other-main">
          <el-form-item label="粒子效果" prop="isParticles">
            <el-switch v-model="options.isParticles"></el-switch>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button @click="submit()">确定</el-button>
      </span>
    </template>
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
  .skin-change {
    background: get('background') !important;
    .preview {
      position: absolute;
      opacity: 0;
      background: rgba(0, 0, 0, 0.5);
      height: 87px;
      width: 150px;
      z-index: 1000;
      display: flex;
      align-items: center;
      justify-content: center;
      border-radius: 4px 4px 0px 0px;
      .preview-text {
        color: white;
      }
    }
    .preview:hover {
      opacity: 2;
      .preview-text {
        display: block;
      }
    }

    .theme-main,
    .back-main {
      display: flex;
      flex-wrap: wrap;
      .theme-item,
      .back-item {
        height: 120px;
        display: flex;
        flex-direction: column;
        margin: 10px;
        box-shadow: get('box-shadow');
        border-radius: 5px;
      }
      .theme-label,
      .back-label {
        height: 30px;
        line-height: 30px;
        color: get('font-color');
      }
      .theme-item-active,
      .back-item-active {
        border: 1px solid white;
      }
    }
    .el-dialog__body {
      height: calc(100% - 130px);
      overflow: auto;
    }
    .other-main {
      padding: 0px 100px;
    }
  }
}
</style>
