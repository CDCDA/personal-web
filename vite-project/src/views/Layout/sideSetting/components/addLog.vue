<!--
 * @Description: 新增更新日志弹窗
-->
<template>
  <el-dialog
    class="log"
    v-model="dialogVisible"
    :title="title"
    width="500px"
    :modal="false"
    @keyup.enter.native="submit()"
    @close="emit('close')"
  >
    <el-form class="log-form" :model="props.form" label-width="90px">
      <el-form-item label="日志内容">
        <el-input
          v-model="props.form.operation"
          type="textarea"
          maxlength="100"
          show-word-limit
          :rows="3"
          placeholder="写点什么吧。。。"
        ></el-input>
      </el-form-item>
      <el-form-item label="更新时间">
        <el-date-picker
          v-model="props.form.operateTime"
          type="date"
          placeholder="Pick a day"
          size="mini"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="emit('close')">取消</el-button>
        <el-button @click="submit()">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup lang="ts">
import { ref, nextTick, onMounted } from 'vue';
import { saveUpdateLog } from '@/api/updateLog.ts';
import { ElMessage } from 'element-plus';
const title = ref('新增更新日志');
const dialogVisible = ref(true);
const emit = defineEmits(['close', 'getList']);
const props = defineProps({
  form: null
});

function close() {
  emit('close');
}

async function submit() {
  const { code, data } = (await saveUpdateLog(props.form)) as any;
  if (code == 200) {
    ElMessage.success('保存成功');
    emit('getList');
    emit('close');
  }
}
</script>
<style lang="scss">
@include theme() {
}
</style>
