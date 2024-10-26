<!--
 * @Description: 相册管理
-->
<template>
  <div class="album-manage manage-main" :class="isSearchShow ? 'is-hidden' : ''">
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
          v-model="queryParams.name"
          placeholder="请输入相册名称"
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
      <el-table-column label="封面" align="center" prop="isOriginal" width="150">
        <template #default="scope">
          <el-image :src="scope.row.coverUrl">
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
      <el-table-column label="标题" align="center" prop="name" show-overflow-tooltip />
      <el-table-column label="简介" align="center" prop="intro" show-overflow-tooltip />
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
  <c-dialog v-model="open" :title="title" width="720" style="height: 70%" :modal="false">
    <el-form :model="form" label-width="40" style="height: calc(100% - 40px); overflow: auto">
      <el-form-item label="名称">
        <el-input v-model="form.name" clearable></el-input>
      </el-form-item>
      <el-form-item label="简介">
        <el-input
          type="textarea"
          :rows="3"
          maxlength="100"
          show-word-limit
          v-model="form.intro"
          placeholder="写点什么吧[]~(￣▽￣)~*"
          clearable
        />
      </el-form-item>
      <el-form-item label="封面">
        <upload v-model="form.coverUrl"></upload>
      </el-form-item>
      <el-form-item label="图片">
        <upload v-model="form.images"></upload>
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
import { listAlbum, delAlbum, saveAlbum } from '@/api/album.ts';
import Pagination from '@/components/pagination/index.vue';
import { ElMessageBox, ElNotification } from 'element-plus';
import upload from '@/components/upload/upload.vue';
import { useTableResize } from '@/utils/manage';
import { autoClearTimer } from '@/utils/timer';
import tools from '../components/tools.vue';
const queryParams = ref({
  name: null,
  pageNum: 1,
  pageSize: 10
} as any);
const dateRange = ref([null, null] as any);
const selection = ref([] as any);
const isSearchShow = ref(false as any);
const selectIds = ref([] as any);
const title = ref('新增' as String);
const form = ref({
  id: null,
  coverUrl: null,
  intro: '',
  name: '',
  images: []
} as any);
const tableList = ref([] as any);
const open = ref(false as any);
const total = ref(0);

async function getList() {
  const { code, msg, data } = (await listAlbum(queryParams.value)) as any;
  if (code == 200) {
    tableList.value = data.list;
    total.value = data.total;
  }
}

async function submit() {
  const res = (await saveAlbum(form.value)) as any;
  if (res) {
    if (!form.value.albumId) ElNotification.success('新增成功');
    else ElNotification.success('修改成功');
    getList();
    open.value = false;
  }
}

function resetQuery() {
  queryParams.value = {
    name: null,
    pageNum: 1,
    pageSize: 10
  };
  dateRange.value = [null, null];
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
    coverUrl: null,
    intro: '',
    name: '',
    images: []
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
  form.value.images.forEach((img: any) => {
    img.name = null;
  });
}

async function handleDel() {
  ElMessageBox.confirm('是否确认删除选中数据?', 'Warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    let ids = [];
    ids = selectIds.value;
    const { code, msg, data } = (await delAlbum(ids)) as any;
    if (code == 200) {
      ElNotification.success({
        title: '删除成功',
        offset: 100
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
