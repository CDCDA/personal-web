<!--
 * @Description: 博客发布弹窗
-->
<template>
  <el-dialog class="blog-release" v-model="dialogVisible" title="发布" width="700px">
    <el-form class="blog-release-settings" :model="blogData" label-width="120px">
      <el-form-item label="博客标签">
        <el-tag
          v-for="tag in blogData.tags"
          :key="tag"
          class="mx-1"
          closable
          :disable-transitions="false"
          @close="tagDel(tag)"
        >
          {{ tag }}
        </el-tag>
        <el-input
          v-if="inputVisible"
          ref="InputRef"
          v-model="inputValue"
          class="ml-1 w-20"
          size="small"
          @keyup.enter="addTag"
          @blur="addTag"
        />
        <el-button v-else class="button-new-tag ml-1" size="small" @click="showInput">
          + New Tag
        </el-button>
      </el-form-item>
      <el-form-item label="博客分类">
        <el-tree-select
          v-model="blogData.typeId"
          :data="typeList"
          check-strictly
          @node-click="typeChange"
          :props="defaultProps"
          :render-after-expand="false"
          ><template #default="{ data: { typeName } }"> {{ typeName }}</template>
        </el-tree-select>
      </el-form-item>
      <el-form-item label="添加封面">
        <el-upload
          class="avatar-uploader"
          action="/dev-api/pw/blog/uploadImg"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <el-image v-if="imageUrl" :src="imageUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="博客摘要">
        <el-input
          v-model="blogData.blogAbstract"
          :rows="2"
          type="textarea"
          placeholder="请输入..."
        ></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <!-- <el-button @click="dialogVisible = false">保存为草稿 </el-button>
        <el-button @click="dialogVisible = false">定时发布 </el-button> -->
        <el-button @click="blogRelease">发布 </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup lang="ts">
import { ref, nextTick, onMounted } from 'vue';
import { saveBlog } from '@/api/blog';
import type { UploadProps } from 'element-plus';
import { ElMessage } from 'element-plus';
import { listTypeTree } from '@/api/type';
import type { UploadFile } from 'element-plus';
const dialogVisible = ref(false);
const props = defineProps({
  blogData: null
});
const defaultProps = {
  children: 'children',
  label: 'typeName',
  value: 'typeId'
};
const dialogImageUrl = ref('');
const inputValue = ref('');
const inputVisible = ref(false);
const InputRef = ref() as any;
const typeList = ref([]);
const imageUrl = ref(null as any);

function close() {
  dialogVisible.value = false;
}
function open() {
  dialogVisible.value = true;
}

//添加标签
function addTag() {
  const { tags } = props.blogData;
  if (inputValue.value) {
    tags.push(inputValue.value);
  }
  inputVisible.value = false;
  inputValue.value = '';
}

//删除标签
function tagDel(tag: string) {
  const { tags } = props.blogData;
  tags.splice(tag.indexOf(tag), 1);
}

function showInput() {
  inputVisible.value = true;
  nextTick(() => {
    InputRef.value!.input!.focus();
  });
}

async function blogRelease() {
  const { code, msg } = (await saveBlog(props.blogData)) as any;
  if (code === 200) {
    ElMessage.success('发布成功');
    close();
  } else {
    ElMessage.error('发布失败：', msg);
  }
}

function typeChange(val: any) {
  console.log(val);
}

async function getTypeTree() {
  const params = {
    userId: props.blogData.userId
  };
  const { code, msg, data } = (await listTypeTree(params)) as any;
  if (code === 200) {
    typeList.value = data;
  } else {
    ElMessage.error('分类数据获取失败', msg);
  }
}

const handleRemove = (file: UploadFile) => {
  console.log(file);
};

const handlePictureCardPreview = (file: UploadFile) => {
  dialogImageUrl.value = file.url!;
  dialogVisible.value = true;
};

const handleDownload = (file: UploadFile) => {
  console.log(file);
};

const handleAvatarSuccess: UploadProps['onSuccess'] = (response: any) => {
  imageUrl.value = response.data;
};

const beforeAvatarUpload: UploadProps['beforeUpload'] = rawFile => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('Avatar picture must be JPG format!');
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!');
    return false;
  }
  return true;
};

onMounted(() => {
  getTypeTree();
});
defineExpose({
  close,
  open
});
</script>
<style lang="scss">
@include theme() {
  .blog-settings {
    width: calc(100% - 20px);
    text-align: left;
    margin-top: 5px;
    border-radius: 3px;
    padding: 10px;
    color: get('font-color');
    border: 1px solid get('font-color');
  }
}

.el-upload {
  border: 1px dashed get('font-color');
  background: transparent;
  height: 100px;
  width: 100px;
}
.el-upload-list__item.is-ready {
  height: 100px;
  width: 100px;
}
</style>
