<!--
 * @Description: 随笔管理
-->
<template>
  <div class="essay-manage manage-main" :class="isSearchShow ? 'is-hidden' : ''">
    <el-form
      class="manage-query-form"
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      label-width="40"
      size="mini"
    >
      <el-form-item label="内容">
        <el-input
          v-model="queryParams.tagName"
          placeholder="请输入随笔内容"
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
      <el-table-column label="图片(仅展示第一张)" align="center" prop="isOriginal" width="300">
        <template #default="scope">
          <el-image :src="scope.row.images[0]">
            <template #placeholder>
              <div
                class="image-slot"
                v-cLoading="'rotate'"
                style="width: 100%; height: 100%"
              ></div> </template
            ><template #error>
              <div class="image-error-slot">
                <svg-icon iconName="图片加载失败"></svg-icon>
              </div> </template
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="内容" align="center" prop="content" show-overflow-tooltip />
      <el-table-column label="创建时间" width="220" align="center" prop="createTime" />
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
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { listEssay, saveEssay, delEssay } from '@/api/essay.ts';
import { useRouter } from 'vue-router';
import { autoClearTimer } from '@/utils/timer';
import Pagination from '@/components/pagination/index.vue';
import { ElMessageBox, ElNotification } from 'element-plus';
import { useTableResize } from '@/utils/manage';
import tools from '../components/tools.vue';
const router = useRouter();
const queryParams = ref({
  content: '',
  pageNum: 1,
  pageSize: 10
} as any);
const selection = ref([] as any);
const isSearchShow = ref(false as any);
const selectIds = ref([] as any);
const title = ref('新增' as String);
const form = ref({
  id: null,
  content: '',
  imageRelations: []
} as any);
const tableList = ref([] as any);
const total = ref(0);

async function getList() {
  const { code, msg, data } = (await listEssay(queryParams.value)) as any;
  if (code == 200) {
    tableList.value = data.list;
    total.value = data.total;
  }
}

async function submit() {
  const res = (await saveEssay(form.value)) as any;
  if (res) {
    if (!form.value.tagId)
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
  }
}

function resetQuery() {
  queryParams.value = {
    content: '',
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
    content: '',
    imageRelations: []
  };
}

function handleAdd() {
  resetForm();
  router.push({ name: 'essayEditor' });
}

function handleEdit() {
  router.push({ name: 'essayEditor', query: { id: selection.value[0].id } });
}

async function handleDel() {
  ElMessageBox.confirm('是否确认删除选中数据?', 'Warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    let ids = [];
    ids = selectIds.value;
    const { code, msg, data } = (await delEssay(ids)) as any;
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
  autoClearTimer(() => {
    useTableResize();
  }, 100);
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
