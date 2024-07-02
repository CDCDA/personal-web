<!--
 * @Description: 自定义弹窗
-->
<template>
  <el-dialog
    class="c-dialog"
    :draggable="draggable"
    :class="[isLessen ? 'is-lessen' : '', isFull ? 'is-full' : '']"
    v-model="props.modelValue"
    :close-on-click-modal="false"
    :title="props.title"
    :width="props.width"
    :modal="props.modal"
    :fullscreen="isFull"
    destroy-on-close
    :show-close="false"
    @close="close"
  >
    <template #header>
      <div class="c-dialog-header" @click="restore">
        <div class="c-dialog-title">{{ props.title }}</div>
        <div class="c-dialog-bt-group">
          <svg-icon v-if="!isLessen" iconName="lessen" @click="lessen" />
          <svg-icon iconName="blowUp" @click="blowUp" />
          <svg-icon iconName="close" @click="close" />
        </div>
      </div>
    </template>
    <slot></slot>
    <slot name="footer"></slot>
  </el-dialog>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
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
    default: false
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
    overlay.parentNode.style.height = '28px';
    overlay.style.width = '250px';
    overlay.style.height = '28px';
    overlay.style.top = 'initial';
    tempDraggle.value = JSON.parse(JSON.stringify(draggable.value));
    draggable.value = false;
    setTimeout(() => {
      const func = () => {
        lessen();
      };
      restore = func;
    }, 100);
  } else {
    overlay.parentNode.style.width = 'initial';
    overlay.parentNode.style.height = 'initial';
    overlay.style.width = 'initial';
    overlay.style.height = 'initial';
    overlay.style.top = '0';
    draggable.value = tempDraggle.value;
    setTimeout(() => {
      const func = () => {
        return;
      };
      restore = func;
    }, 100);
  }
}

function blowUp() {
  isFull.value = !isFull.value;
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
