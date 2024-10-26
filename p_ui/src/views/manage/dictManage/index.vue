<!--
 * @Description: 标签管理
-->
<template>
  <div class="dict-manage manage-main" :class="isSearchShow ? 'is-hidden' : ''">
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
          placeholder="请输入字典名称"
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
    <tools
      @handleAdd="handleAdd"
      @handleEdit="handleEdit"
      @handleDel="handleDel"
      :selection="selection"
      @refresh="getList"
    />
    <el-table :data="tableList" class="manage-table" style="" @selection-change="selectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="字典名称" align="center" prop="dictName" show-overflow-tooltip />
      <el-table-column label="字典类型" align="center" prop="dictType" width="150">
        <template #default="scope">
          <a @click="manageDictData(scope.row)" style="text-decoration: none">{{
            scope.row.dictType
          }}</a>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" show-overflow-tooltip>
        <template #default="scope">
          {{ scope.row.status }}
        </template>
      </el-table-column>
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
  </div>
  <el-drawer v-model="drawer" title="I am the title" :with-header="false">
    <span>Hi there!</span>
  </el-drawer>
  <!-- 新增或编辑 -->
  <c-dialog v-model="open" :title="title" width="500" :modal="false">
    <el-form :model="form" label-width="40">
      <el-form-item label="名称">
        <el-input v-model="form.dictName" clearable></el-input>
      </el-form-item>
      <el-form-item label="类型">
        <el-input v-model="form.dictType" clearable></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="open = false">取消</el-button>
        <el-button type="primary" @click="submit"> 确定 </el-button>
      </span>
    </template>
  </c-dialog>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { listDictType, delDictType, saveDictType } from '@/api/system/dict/dictType.ts';
import Pagination from '@/components/pagination/index.vue';
import { ElNotification, ElMessageBox } from 'element-plus';
import { useTableResize } from '@/utils/manage';
import tools from '../components/tools.vue';
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
  const { code, msg, data } = (await listDictType(queryParams.value)) as any;
  if (code == 200) {
    tableList.value = data.list;
    total.value = data.total;
  }
}

async function submit() {
  const res = (await saveDictType(form.value)) as any;
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
    const { code, msg, data } = (await delDictType(ids)) as any;
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
  useTableResize();
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
