<!--
 * @Description: 博客管理
-->
<template>
  <div class="blog-manage">
    <el-form :inline="true" class="manage-form" label-width="80">
      <el-form-item label="">
        <el-input v-model="queryParams.blogTitle" placeholder="名称" clearable></el-input>
      </el-form-item>
      <el-form-item label="">
        <el-date-picker
          v-model="queryParams.createTime"
          clearable
          type="datetimerange"
          range-separator="To"
          start-placeholder="Start date"
          end-placeholder="End date"
        />
      </el-form-item>
      <el-form-item label="">
        <el-input v-model="queryParams.blogType" placeholder="分类" clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="getList">查询</el-button>
        <el-button icon="RefreshRight" @click="resetQuery">重置</el-button>
        <el-button icon="Plus" @click="add">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table align="center" :data="blogList" border>
      <!--多选-->
      <el-table-column label="序号" type="selection" width="50" align="center" />
      <el-table-column
        label="名称"
        prop="blogTitle"
        align="center"
        width="auto"
        show-overflow-tooltip
      />
      <el-table-column
        label="分类"
        prop="typeId"
        align="center"
        width="120"
        show-overflow-tooltip
      />
      <!-- <el-table-column
        label="创建时间"
        prop="createTime"
        align="center"
        width="170"
        show-overflow-tooltip
      /> -->
      <el-table-column
        label="更新时间"
        prop="updateTime"
        align="center"
        width="170"
        show-overflow-tooltip
      />
      <el-table-column prop="option" label="操作" width="130" align="center">
        <template #default="scope: any">
          <el-icon @click="edit(scope.row)" style="font-size: 18px; margin: 5px">
            <Edit />
          </el-icon>
          <el-icon @click="view(scope.row)" style="font-size: 18px; margin: 5px">
            <View />
          </el-icon>
          <el-icon @click="del(scope.row)" style="font-size: 18px; margin: 5px">
            <Delete />
          </el-icon>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      class="ds-dialog-pagination"
      v-model:current-page="queryParams.pageNum"
      v-model:page-size="queryParams.pageSize"
      :page-sizes="[10, 20, 50]"
      :small="false"
      :background="true"
      layout="total, sizes, prev, pager, next, jumper"
      :total="total"
      @size-change="getList"
      @current-change="getList"
    />
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { listBlog, saveBlog, deleteBlogs, deleteBlog } from '@/api/blog';
import { useRouter } from 'vue-router';
import { ElMessage, ElMessageBox } from 'element-plus';
const router = useRouter();
const queryParams = ref({
  pageNum: 1,
  pageSize: 10
} as any);
const total = ref(0);
const blogList = ref([{}]);

async function getList() {
  const { code, msg, data } = (await listBlog(queryParams.value)) as any;
  if (code === 200 && data) {
    blogList.value = data.list;
  }
}

function resetQuery() {
  queryParams.value = {
    pageNum: 1,
    pageSize: 10
  };
}

async function edit(row: any) {
  router.push({ name: 'BlogEditor', query: { blogId: row.blogId } });
}

async function del(row: any) {
  ElMessageBox.confirm('是否删除选中博客?', 'Warning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(async () => {
      const { code, msg, data } = (await deleteBlog(row.blogId)) as any;
      if (code === 200) {
        ElMessage.success('删除成功');
        getList();
      } else {
        ElMessage.error('删除失败', msg);
      }
    })
    .catch(() => {});
}

function view(row: any) {
  router.push({ name: 'BlogDisplay', query: { blogId: row.blogId } });
}

function add() {
  router.push({ name: 'Blog' });
}

onMounted(() => {
  getList();
});
</script>
<style lang="scss">
.blog-manage {
  width: 800px;
  height: calc(100% - 15px);
  .el-table {
    margin: 10px;
    width: calc(100% - 20px);
    height: calc(100% - 170px);
  }
  .el-input {
    width: 200px;
  }
  .manage-form {
    text-align: left;
    margin: 15px;
    width: calc(100% - 30px);
  }
}
</style>
