<!--
 * @Description: 更新日志管理
-->
<template>
  <div class="log-manage manage-main" :class="isSearchShow ? 'is-hidden' : ''">
    <el-form
      class="manage-query-form"
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      label-width="40"
      size="mini"
    >
      <el-form-item label="操作内容" label-width="70">
        <el-input
          v-model="queryParams.operation"
          placeholder="请输入操作内容"
          clearable
          style="width: 200px"
          @keyup.enter="getList"
        />
      </el-form-item>
      <el-form-item>
        <el-button log="primary" icon="Search" @click="getList">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <div class="c-divider"></div>
    <el-row :gutter="10" class="mb8" style="margin-bottom: 15px">
      <el-col :span="1.5">
        <el-button log="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          log="primary"
          :disabled="selection.length == 0"
          plain
          icon="Edit"
          @click="handleEdit"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          log="primary"
          :disabled="selection.length == 0"
          plain
          icon="Select"
          @click="handleDel"
          >删除</el-button
        >
      </el-col>
      <div class="manage-tools">
        <svg-icon iconName="refresh" @click="getList()"></svg-icon>
        <svg-icon iconName="hideMenu" @click="hideSearch()"></svg-icon>
      </div>
    </el-row>
    <el-table
      :data="tableList"
      ref="table"
      class="manage-table"
      @selection-change="selectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="更新内容"
        align="center"
        prop="operation"
        width="auto"
        show-overflow-tooltip
      />
      <el-table-column label="更新时间" width="auto" align="center" prop="operateTime" />
    </el-table>
    <Pagination
      v-model:page="queryParams.pageNum"
      v-model:page-size="queryParams.pageSize"
      :total="total"
      :on-page-change="getList"
      :showSizes="true"
      :pageSizeList="[10, 20, 30]"
      :on-page-size-change="getList"
      class="pagi page-content"
    />
  </div>
  <!-- 新增或编辑 -->
  <addLog
    v-if="addLogOpen"
    :title="title"
    :form="form"
    @close="addLogOpen = false"
    @getList="getList"
  ></addLog>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { listUpdateLog, delUpdateLog } from '@/api/updateLog.ts';
import { useRouter } from 'vue-router';
import Pagination from '@/components/pagination/index.vue';
import { ElNotification, ElMessageBox } from 'element-plus';
import addLog from '@/views/layout/sideSetting/components/addLog.vue';
const router = useRouter();
const addLogOpen = ref(false);
const queryParams = ref({
  operation: '',
  pageNum: 1,
  pageSize: 10
} as any);
const selection = ref([] as any);
const isSearchShow = ref(false as any);
const selectIds = ref([] as any);
const title = ref('新增' as String);
const form = ref({
  id: null,
  operation: '',
  operateTime: new Date()
} as any);
const tableList = ref([] as any);
const total = ref(0);

async function getList() {
  const { code, msg, data } = (await listUpdateLog(queryParams.value)) as any;
  if (code == 200) {
    tableList.value = data.list;
    total.value = data.total;
  }
}

function resetQuery() {
  queryParams.value = {
    operation: '',
    pageNum: 1,
    pageSize: 10
  };
}

function selectionChange(val: any) {
  selection.value = val;
  selectIds.value = [];
  val.forEach((e: any) => {
    selectIds.value.push(e.id);
  });
}

function resetForm() {
  form.value = {
    id: null,
    operation: '',
    operateTime: new Date()
  };
}

function handleAdd() {
  addLogOpen.value = true;
  title.value = '新增更新日志';
  resetForm();
}

function handleEdit() {
  addLogOpen.value = true;
  title.value = '编辑更新日志';
  form.value = JSON.parse(JSON.stringify(selection.value[0]));
}

async function handleDel() {
  (ElMessageBox as any)
    .confirm('是否确认删除选中数据?', 'Warning', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    .then(async () => {
      let ids = [];
      ids = selectIds.value;
      const { code, msg, data } = (await delUpdateLog(ids)) as any;
      if (code == 200) {
        ElNotification({
          title: 'Success',
          message: '删除成功',
          type: 'success'
        });
        getList();
      }
    });
}

function hideSearch() {
  isSearchShow.value = !isSearchShow.value;
}
onMounted(() => {
  getList();
});
</script>
<style lang="scss" scoped>
@keyframes hidden {
  0% {
    height: 50px;
  }
  100% {
    height: 0px;
  }
}
@keyframes show {
  0% {
    height: 0px;
  }
  100% {
    height: 50px;
  }
}
@keyframes table-show {
  0% {
    height: calc(100% - 140px);
  }
  100% {
    height: calc(100% - 210px);
  }
}
@keyframes table-hidden {
  0% {
    height: calc(100% - 210px);
  }
  100% {
    height: calc(100% - 140px);
  }
}
</style>
