<!--
 * @Description: 自定义弹窗
-->
<template>
  <el-dialog
    class="c-dialog"
    :id="id"
    :draggable="draggable"
    :class="[
      isLessen ? 'is-lessen' : '',
      isFull ? 'is-full' : '',
      props.dialogClass ? props.dialogClass : 'dialog-filter'
    ]"
    v-model="props.modelValue"
    :title="props.title"
    :width="props.width"
    :modal="true"
    :fullscreen="isFull"
    destroy-on-close
    :show-close="false"
    append-to=".dialog-base"
    @close="close"
  >
    <template #header>
      <div class="c-dialog-header" @click="restore" @dblclick="lessen">
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
import { generateRandomId } from '@/utils/common';
const id = generateRandomId();
const props = defineProps({
  options: {
    default: {
      isDefaultFull: false
    }
  },
  dialogClass: {
    default: ''
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
let tempOverlay = {} as any;
let tempOverlayDialog = {} as any;

function lessen() {
  isLessen.value = !isLessen.value;
  let dialog = document.getElementById(id) as any;
  let overlayDialog = dialog.parentNode;
  let overlay = overlayDialog.parentNode;
  if (isLessen.value) {
    tempOverlayDialog = {
      width: overlayDialog.style.width,
      height: overlayDialog.style.height,
      top: overlayDialog.style.top
    };
    tempOverlay = {
      width: overlay.style.width,
      height: overlay.style.height,
      bottom: overlay.style.bottom,
      backdropFilter: overlay.style.backdropFilter,
      background: overlay.style.background
    };
    overlay.style.width = '250px';
    overlay.style.height = '35px';
    overlay.style.bottom = '0px';
    overlay.style.backdropFilter = 'none';
    overlay.style.background = 'transparent';
    overlayDialog.style.width = '250px';
    overlayDialog.style.height = '35px';
    overlayDialog.style.top = 'initial';
    tempDraggle.value = JSON.parse(JSON.stringify(draggable.value));
    draggable.value = false;
  } else {
    overlay.style.width = tempOverlay.width;
    overlay.style.height = tempOverlay.height;
    overlay.style.bottom = tempOverlay.bottom;
    overlay.style.backdropFilter = tempOverlay.backdropFilter;
    overlay.style.background = tempOverlay.background;
    overlayDialog.style.width = tempOverlayDialog.width;
    overlayDialog.style.height = tempOverlayDialog.height;
    overlayDialog.style.top = tempOverlayDialog.top;
    draggable.value = tempDraggle.value;
  }
}

function blowUp() {
  if (isLessen.value) {
    isFull.value = false;
    lessen();
  } else {
    isFull.value = !isFull.value;
    if (isFull.value) {
      tempDraggle.value = JSON.parse(JSON.stringify(draggable.value));
      draggable.value = false;
    } else {
      draggable.value = tempDraggle.value;
    }
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

    justify-content: space-between;
    align-items: center;
    .c-dialog-bt-group {
      display: flex;
    }

    .svg-icon-wrap:active {
      transform: translateY(1px);
    }
  }
}
</style>
