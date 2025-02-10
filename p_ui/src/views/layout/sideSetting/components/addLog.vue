<!--
 * @Description: 新增更新日志弹窗
-->
<template>
  <c-dialog
    class="log"
    v-model="dialogVisible"
    :title="title"
    width="28rem"
    :modal="true"
    @keyup.enter.native="submit()"
    @close="emit('close')"
  >
    <el-form class="log-form" :model="form" label-width="70px">
      <el-form-item label="日志内容">
        <el-input
          v-model="form.operation"
          type="textarea"
          maxlength="100"
          show-word-limit
          :rows="3"
          placeholder="写点什么吧。。。"
        ></el-input>
      </el-form-item>
      <el-form-item label="更新时间">
        <el-date-picker v-model="form.operateTime" type="date" placeholder="请选择" size="mini" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button size="mini" @click="emit('close')">取消</el-button>
        <el-button size="mini" @click="submit()">确定</el-button>
      </span>
    </template>
  </c-dialog>
</template>
<script setup lang="ts">
import { ref, nextTick, onMounted, watch } from 'vue';
import { saveUpdateLog } from '@/api/system/updateLog';
import { ElNotification } from 'element-plus';
const title = ref('新增更新日志');
const dialogVisible = ref(true);
const emit = defineEmits(['close', 'getList']);
const props = defineProps({
  form: {
    id: null,
    operation: '',
    operateTime: new Date()
  } as any
});

const form = ref({
  id: null,
  operation: '',
  operateTime: new Date()
} as any);

function close() {
  emit('close');
}

watch(
  () => props.form,
  val => {
    Object.assign(form.value, val);
  },
  {
    deep: true,
    immediate: true
  }
);

async function submit() {
  console.log(form.value);
  const { code } = (await saveUpdateLog(form.value)) as any;
  if (code == 200) {
    ElNotification.success('保存成功');
    emit('getList');
    emit('close');
  }
}

onMounted(() => {
  form.value.operateTime = null;
  form.value.operateTime = new Date();
});
</script>

<style lang="scss">
@include theme() {
}
</style>
