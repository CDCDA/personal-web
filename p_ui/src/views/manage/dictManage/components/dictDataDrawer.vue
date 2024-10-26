<template>
  <el-drawer v-model="drawer" title="I am the title" :with-header="false" class="manage-main">
    <el-form
      class="manage-query-form"
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      label-width="40"
      size="mini"
    >
      <el-form-item label="名称">
        <el-input
          v-model="queryParams.tagName"
          placeholder="请输入字典类型"
          clearable
          style="width: 200px"
          @keyup.enter="getList"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="getList">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <div class="c-divider"></div>
    <el-table :data="tableList" class="manage-table" style="" @selection-change="selectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="字典标签" align="center" prop="dictLabel" show-overflow-tooltip />
      <el-table-column label="字典键值" align="center" prop="dictValue" show-overflow-tooltip />
      <el-table-column label="状态" align="center" prop="status" show-overflow-tooltip>
        <template #default="scope">
          {{ scope.row.status == '1' ? '禁用' : '正常' }}
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" show-overflow-tooltip />
      <el-table-column label="创建时间" width="auto" align="center" prop="createTime" />
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
  </el-drawer>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { listDictData, delDictData, saveDictData } from '@/api/system/dict/dictData.ts';
import Pagination from '@/components/pagination/index.vue';
import { ElNotification, ElMessageBox } from 'element-plus';
const props = defineProps({
  drawer: {
    default: false
  }
});

const queryParams = ref({
  dictName: null,
  pageNum: 1,
  pageSize: 10
} as any);
const dateRange = ref([null, null] as any);
const selection = ref([] as any);
const isSearchShow = ref(false as any);
const selectIds = ref([] as any);
const title = ref('新增' as String);
const form = ref({
  dictName: null,
  dictType: null
} as any);
const drawer = ref(true);
const tableList = ref([] as any);
const open = ref(false as any);
const total = ref(0);

async function getList() {
  const { code, msg, data } = (await listDictData(queryParams.value)) as any;
  if (code == 200) {
    tableList.value = data.list;
    total.value = data.total;
  }
}

async function submit() {
  const res = (await saveDictData(form.value)) as any;
  if (res) {
    if (!form.value.id)
      ElNotification({
        title: 'Success',
        message: '新增成功',
        type: 'success'
      });
    else
      ElNotification({
        title: 'Success',
        message: '修改成功',
        type: 'success'
      });
    getList();
    open.value = false;
  }
}

function resetQuery() {
  queryParams.value = {
    dictName: null,
    pageNum: 1,
    pageSize: 10
  };
  dateRange.value = [null, null];
}

function selectionChange(val: any) {
  selection.value = val;
  selectIds.value = [];
  val.forEach((e: any) => {
    selectIds.value.push(e.tagId);
  });
}

function resetForm() {
  form.value = {
    dictName: null,
    dictType: null
  };
}

function handleAdd() {
  open.value = true;
  title.value = '新增';
  resetForm();
}

function manageDictData(row: any) {
  console.log('SD', row);
}

function handleEdit() {
  open.value = true;
  title.value = '编辑';
  form.value = JSON.parse(JSON.stringify(selection.value[0]));
}

// function handleView(item: any) {
//   router.push({ name: 'blogDisplay', query: { blogId: item.blogId } });
// }
async function handleDel() {
  ElMessageBox.confirm('是否确认删除选中数据?', 'Warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    let ids = [];
    ids = selectIds.value;
    const { code, msg, data } = (await delDictData(ids)) as any;
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

onMounted(() => {
  getList();
});
</script>
<style lang="scss" scoped></style>
