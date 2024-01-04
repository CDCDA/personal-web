<!--
 * @Description: 标签管理
-->
<template>
  <div class="tag-manage manage-main" :class="isSearchShow ? 'is-hidden' : ''">
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
          placeholder="请输入标签名称"
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
    <el-row :gutter="10" class="mb8" style="margin-bottom: 15px">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Edit" @click="handleEdit">修改</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="View" @click="handleView">查看</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Select" @click="handleDel">删除</el-button>
      </el-col>
      <div class="manage-tools">
        <svg-icon iconName="refresh" @click="getList()"></svg-icon>
        <svg-icon iconName="hideMenu" @click="hideSearch()"></svg-icon>
      </div>
    </el-row>
    <el-table :data="tableList" class="manage-table" style="" @selection-change="selectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="封面" align="center" prop="isOriginal" width="150">
        <template #default="scope">
          <el-image :src="scope.row.coverUrl">
            <template #placeholder>
              <div
                class="image-slot"
                v-cLoading="'rotate'"
                style="width: 100%; height: 100%"
              ></div> </template
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column label="标题" align="center" prop="tagName" show-overflow-tooltip />
      <el-table-column label="关联文章数" align="center" prop="total" show-overflow-tooltip />
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
  <!-- 新增或编辑 -->
  <el-dialog v-model="open" :title="title" width="500" :modal="false">
    <el-form :model="form" label-width="40">
      <el-form-item label="名称">
        <el-input v-model="form.tagName" clearable></el-input>
      </el-form-item>
      <el-form-item label="封面">
        <upload v-model="form.coverUrl"></upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="open = false">取消</el-button>
        <el-button type="primary" @click="submit"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { listTag, delTag, saveTag } from '@/api/tag.ts';
import { useRouter } from 'vue-router';
import Pagination from '@/components/pagination/index.vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import upload from '@/components/upload/upload.vue';
const router = useRouter();
const queryParams = ref({
  tagName: null,
  pageNum: 1,
  pageSize: 10
} as any);
const dateRange = ref([null, null] as any);
const selection = ref([] as any);
const isSearchShow = ref(false as any);
const selectIds = ref([] as any);
const title = ref('新增' as String);
const form = ref({
  coverUrl: null,
  intro: '',
  tagName: ''
} as any);
const tableList = ref([] as any);
const open = ref(false as any);
const total = ref(0);

async function getList() {
  const { code, msg, data } = (await listTag(queryParams.value)) as any;
  if (code == 200) {
    tableList.value = data.list;
    total.value = data.total;
  }
}

async function submit() {
  const res = (await saveTag(form.value)) as any;
  if (res) {
    if (form.value.tagId) ElMessage.success('新增成功');
    else ElMessage.success('修改成功');
    getList();
    open.value = false;
  }
}

function resetQuery() {
  queryParams.value = {
    tagName: null,
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
    coverUrl: null,
    intro: '',
    tagName: ''
  };
}

function handleAdd() {
  open.value = true;
  title.value = '新增';
  resetForm();
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
    const { code, msg, data } = (await delTag(ids)) as any;
    if (code == 200) {
      ElMessage.success('删除成功');
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
