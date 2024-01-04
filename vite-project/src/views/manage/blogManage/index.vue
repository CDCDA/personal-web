<!--
 * @Description: 博客管理
-->
<template>
  <div class="blog-manage manage-main" :class="isSearchShow ? 'is-hidden' : ''">
    <el-form
      class="manage-query-form"
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      label-width="40"
      size="mini"
    >
      <el-form-item label="标题" prop="blogTitle">
        <el-input
          v-model="queryParams.blogTitle"
          placeholder="请输入博客标题"
          clearable
          style="width: 200px"
          @keyup.enter="getBlogList"
        />
      </el-form-item>
      <el-form-item label="分类" prop="typeId">
        <el-select v-model="queryParams.typeId" style="width: 200px" placeholder="请选择分类">
          <el-option
            v-for="item in typeList"
            :value="item.typeId"
            :label="item.typeName"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="标签" prop="tagId">
        <el-select v-model="queryParams.tagId" placeholder="请选择标签">
          <el-option v-for="item in tagList" :value="item.tagId" :label="item.tagName"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="时间" prop="tagId">
        <el-date-picker
          v-model="dateRange"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          value-format="YYYY-MM-DD"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="getBlogList">搜索</el-button>
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
      <el-col :span="1.5">
        <el-button type="warning" plain icon="View" @click="handleView">查看</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Select" @click="handleDel">删除</el-button>
      </el-col>
      <div class="manage-tools">
        <svg-icon iconName="refresh" @click="getBlogList()"></svg-icon>
        <svg-icon iconName="hideMenu" @click="hideSearch()"></svg-icon>
      </div>
    </el-row>
    <el-table :data="blogList" class="manage-table" style="" @selection-change="selectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="封面" align="center" prop="isOriginal" width="150">
        <template #default="scope">
          <el-image :src="scope.row.coverUrl"
            ><template #placeholder>
              <div class="image-slot" v-cLoading="'rotate'" style="width: 100%; height: 100%"></div>
            </template>
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="博客标题" align="center" prop="blogTitle" show-overflow-tooltip />
      <el-table-column label="博客分类" width="120" align="center" prop="blogType" />
      <el-table-column label="是否原创" width="120" align="center" prop="isOriginal">
        <template #default="scope">
          {{ scope.row.isOriginal == '1' ? '原创' : '转载' }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="180" align="center" prop="createTime" />
      <el-table-column label="作者" width="150" align="center" prop="author" />
      <!-- <el-table-column label="操作" width="120" align="center" prop="isOriginal" fixed="right">
        <template #default="scope">
          <el-icon class="operation-icon" @click="handleView(scope.row)"><View /></el-icon>
          <el-icon class="operation-icon" @click="handleEdit(scope.row)"><Edit /></el-icon>
          <el-icon class="operation-icon" @click="handleDel(scope.row)"><Delete /></el-icon>
        </template>
      </el-table-column> -->
    </el-table>
    <Pagination
      v-model:page="queryParams.pageNum"
      v-model:page-size="queryParams.pageSize"
      :total="total"
      :on-page-change="getBlogList"
      :showSizes="true"
      :pageSizeList="[10, 20, 30]"
      :on-page-size-change="getBlogList"
      class="pagi page-content"
    />
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { listBlog, deleteBlogs } from '@/api/blog.ts';
import { listType } from '@/api/type.ts';
import { listTag } from '@/api/tag.ts';
import { useRouter } from 'vue-router';
import Pagination from '@/components/pagination/index.vue';
import { ElMessage } from 'element-plus';

const router = useRouter();
const queryParams = ref({
  blogTitle: null,
  typeId: null,
  tagId: null,
  startTime: null,
  endTime: null,
  pageNum: 1,
  pageSize: 10
} as any);
const dateRange = ref([null, null] as any);
const selection = ref([] as any);
const isSearchShow = ref(false as any);
const selectIds = ref([] as any);
const blogList = ref([] as any);
const typeList = ref([] as any);
const tagList = ref([] as any);
const total = ref(0);

async function getBlogList() {
  queryParams.value.startTime = dateRange.value[0];
  queryParams.value.endTime = dateRange.value[1];
  // delete queryParams.value.dateRange;
  const { code, msg, data } = (await listBlog(queryParams.value)) as any;
  if (code == 200) {
    blogList.value = data.list;
    total.value = data.total;
  }
}

async function getTypeList() {
  const { code, msg, data } = (await listType({})) as any;
  if (code == 200) {
    typeList.value = data.list;
  }
}

async function getTagList() {
  const { code, msg, data } = (await listTag({})) as any;
  if (code == 200) {
    tagList.value = data.list;
  }
}

function resetQuery() {
  queryParams.value = {
    blogTitle: '',
    typeId: '',
    tagId: '',
    pageNum: 0,
    pageSize: 10
  };
  dateRange.value = [null, null];
}

function selectionChange(val: any) {
  selection.value = val;
  selectIds.value = [];
  val.forEach((e: any) => {
    selectIds.value.push(e.blogId);
  });
}

function handleAdd() {
  router.push({ name: 'blogEditor' });
}
function handleEdit(item: any) {
  router.push({ name: 'blogEditor', query: { blogId: item.blogId } });
}
function handleView(item: any) {
  router.push({ name: 'blogDisplay', query: { blogId: item.blogId } });
}
async function handleDel(item: any) {
  let ids = [];
  if (item) ids.push(item.blogId);
  ids = selectIds.value;
  const { code, msg, data } = (await deleteBlogs(ids)) as any;
  if (code == 200) {
    ElMessage.success('删除成功');
    getBlogList();
  }
}

function hideSearch() {
  isSearchShow.value = !isSearchShow.value;
}
onMounted(() => {
  getBlogList();
  getTypeList();
  getTagList();
});
</script>
<style lang="scss" scoped></style>
