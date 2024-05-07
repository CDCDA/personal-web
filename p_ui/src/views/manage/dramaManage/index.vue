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
      <el-form-item label="名称" prop="blogTitle">
        <el-input
          v-model="queryParams.blogTitle"
          placeholder="请输入影视名称"
          clearable
          style="width: 200px"
          @keyup.enter="getList"
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
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Select" @click="handleDel">删除</el-button>
      </el-col>
      <div class="manage-tools">
        <svg-icon iconName="refresh" @click="getList()"></svg-icon>
        <svg-icon iconName="hideMenu" @click="hideSearch()"></svg-icon>
      </div>
    </el-row>
    <el-table :data="blogList" class="manage-table" style="" @selection-change="selectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="封面" align="center" prop="isOriginal" width="150">
        <template #default="scope">
          <c-image :src="scope.row.coverUrl" />
        </template>
      </el-table-column>
      <el-table-column label="名称" align="center" prop="name" show-overflow-tooltip />
      <el-table-column label="分类" align="center" prop="type">
        <template #default="scope">
          <!-- 影视分类(0:电影;1:电视剧;2:动漫) -->
          {{ getDramaType(scope.row.type) }}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" />
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
    <el-form :model="form" label-width="80">
      <el-form-item label="影视名称">
        <el-input v-model="form.name" clearable />
      </el-form-item>
      <el-form-item label="封面">
        <upload v-if="!fromNet" v-model="form.coverUrl" />
        <el-input v-else v-model="form.coverUrl" clearable />
      </el-form-item>
      <el-form-item label="分类">
        <el-radio-group v-model="form.type" class="ml-4">
          <el-radio :label="'0'" size="mini">电影</el-radio>
          <el-radio :label="'1'" size="mini">电视剧</el-radio>
          <el-radio :label="'2'" size="mini">动漫</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="播放地址">
        <el-input v-model="form.url" clearable />
      </el-form-item>
      <el-form-item label="简介">
        <el-input
          type="textarea"
          :rows="3"
          maxlength="100"
          show-word-limit
          v-model="form.intro"
          placeholder="写点什么吧。。。"
          clearable
        />
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
import { listDrama, delDrama, saveDrama, getDramaById } from '@/api/dramaSeries.ts';
import { useRouter } from 'vue-router';
import Pagination from '@/components/pagination/index.vue';
import { ElMessageBox, ElNotification } from 'element-plus';

const title = ref('');
const fromNet = ref(true as any);
const form = ref({
  coverUrl: null,
  url: null,
  intro: '',
  type: '0',
  name: ''
} as any);
const open = ref(false as any);
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

function getDramaType(type: any) {
  switch (type) {
    case 0:
      return '电影';
    case 1:
      return '电视剧';
    case 2:
      return '动漫';
    default:
      return '未知';
  }
}

async function getList() {
  queryParams.value.startTime = dateRange.value[0];
  queryParams.value.endTime = dateRange.value[1];
  const { code, msg, data } = (await listDrama(queryParams.value)) as any;
  if (code == 200) {
    blogList.value = data.list;
    total.value = data.total;
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

function resetForm() {
  form.value = {
    coverUrl: null,
    url: null,
    intro: '',
    type: '0',
    name: ''
  };
}

function selectionChange(val: any) {
  selection.value = val;
  selectIds.value = [];
  val.forEach((e: any) => {
    selectIds.value.push(e.id);
  });
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

async function handleDel() {
  ElMessageBox.confirm('是否确认删除选中数据?', 'Warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    let ids = [];

    ids = selectIds.value;
    const { code, msg, data } = (await delDrama(ids)) as any;
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

async function submit() {
  const { code, msg } = (await saveDrama(form.value)) as any;
  if (code == 200) {
    console.log('FFF', form.value);
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

function hideSearch() {
  isSearchShow.value = !isSearchShow.value;
}
onMounted(() => {
  getList();
});
</script>
<style lang="scss" scoped></style>
