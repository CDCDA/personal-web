<!--
 * @Description: 自定义弹窗
-->
<template>
  <el-dialog
    class="c-dialog"
    :draggable="draggable"
    :class="[isLessen ? 'is-lessen' : '', isFull ? 'is-full' : '']"
    v-model="props.modelValue"
    :title="props.title"
    :width="props.width"
    :modal="true"
    :fullscreen="isFull"
    destroy-on-close
    :show-close="false"
    @close="close"
  >
    <template #header>
      <div class="c-dialog-header" @click="restore">
        <div class="c-dialog-title">{{ props.title }}</div>
        <div class="c-dialog-bt-group">
          <svg-icon v-if="!isLessen" iconName="缩小" @click="lessen" />
          <svg-icon iconName="放大" @click="blowUp" />
          <svg-icon iconName="关闭" @click="close" />
        </div>
      </div>
    </template>
    <slot></slot>
    <slot name="footer"></slot>
  </el-dialog>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { autoClearTimer } from '@/utils/timer';
const props = defineProps({
  options: {
    default: {
      isDefaultFull: false
    }
  },
  title: {
    default: ''
  },
  width: {
    default: ''
  },
  modelValue: {
    default: false
  },
  drag: {
    default: false
  },
  isFull: {
    default: false
  },
  modal: {
    default: true
  },
  draggable: {
    default: true
  }
});
const isFull = ref(false);
const draggable = ref(false);
const isLessen = ref(false);
const emit = defineEmits(['update:modelValue', 'close']);

function close() {
  emit('close');
  emit('update:modelValue', false);
}
function open() {
  emit('update:modelValue', true);
}

var restore = () => {
  return;
};

let tempDraggle = ref(false);

function lessen() {
  isLessen.value = !isLessen.value;
  let overlay = document.querySelector('.el-overlay-dialog') as any;
  if (isLessen.value) {
    overlay.parentNode.style.width = '250px';
    overlay.parentNode.style.height = '35px';
    overlay.parentNode.style.bottom = '0px';
    overlay.parentNode.style.backdropFilter = 'none';
    overlay.parentNode.style.background = 'transparent';
    overlay.style.width = '250px';
    overlay.style.height = '35px';
    overlay.style.top = 'initial';
    tempDraggle.value = JSON.parse(JSON.stringify(draggable.value));
    draggable.value = false;
    autoClearTimer(() => {
      const func = () => {
        lessen();
      };
      restore = func;
    }, 100);
  } else {
    overlay.parentNode.style.width = 'initial';
    overlay.parentNode.style.height = 'initial';
    overlay.parentNode.style.bottom = '0px';
    overlay.parentNode.style.backdropFilter = 'blur(15px)';
    overlay.parentNode.style.background = 'initial';
    overlay.style.width = 'initial';
    overlay.style.height = 'initial';
    overlay.style.top = '0';
    draggable.value = tempDraggle.value;
    autoClearTimer(() => {
      const func = () => {
        return;
      };
      restore = func;
    }, 100);
  }
}

function blowUp() {
  isFull.value = !isFull.value;
  if (isFull.value) {
    tempDraggle.value = JSON.parse(JSON.stringify(draggable.value));
    draggable.value = false;
  } else {
    draggable.value = tempDraggle.value;
  }
}

function init() {
  isFull.value = props.isFull;
  draggable.value = props.draggable;
}
onMounted(() => {
  init();
});
defineExpose({
  close,
  open
});
</script>
<style lang="scss" scoped>
@include theme() {
  .c-dialog-header {
    width: 100%;
    margin: 0px 9px 0px 14px;
    height: 100%;
    display: flex;
    color: white;
    justify-content: space-between;
    align-items: center;
    .c-dialog-bt-group {
      display: flex;
    }
    .svg-icon-wrap {
      margin-left: 5px;
      width: 20px;
      height: 20px;
      cursor: pointer;
      :deep(.theme-icon) {
        fill: white !important;
      }
    }
    .svg-icon-wrap:active {
      transform: translateY(1px);
    }
  }
}
</style>
