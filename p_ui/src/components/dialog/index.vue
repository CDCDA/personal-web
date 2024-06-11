<!--
 * @Description: 自定义弹窗
-->
<template>
  <el-dialog class="c-dialog" v-model="dialogVisible" title="发布" width="700px" :modal="false">
  </el-dialog>
</template>
<script setup lang="ts">
import { ref, nextTick, onMounted } from 'vue';
import { saveBlog } from '@/api/blog';
import upload from '@/components/upload/upload.vue';
import type { UploadProps } from 'element-plus';
import { ElMessage, ElNotification } from 'element-plus';
import { listType } from '@/api/type';
import { listTag } from '@/api/tag';
import { useRouter } from 'vue-router';
import type { UploadFile } from 'element-plus';
const router = useRouter();
const tagVisible = ref(false);

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
const tagList = ref([] as any);
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
    tags.push({ tagName: inputValue.value });
  }
  inputVisible.value = false;
  inputValue.value = '';
}

//删除标签
function tagDel(tag: string) {
  const { tags } = props.blogData;
  tags.splice(tags.indexOf(tag), 1);
}

function showInput() {
  inputVisible.value = true;
  nextTick(() => {
    InputRef.value!.input!.focus();
  });
}

async function submit() {
  const { code, msg, data } = (await saveBlog(props.blogData)) as any;
  if (code === 200) {
    // ElMessage.success('发布成功');
    ElNotification.success({
      title: 'Success',
      message: '博客发布成功\n点击前往博客',
      offset: 100,
      onClick: () => {
        router.push({ name: 'blogDisplay', query: { blogId: data.id } });
      }
    });
    close();
  } else {
    ElMessage.error('发布失败：', msg);
  }
}

function typeChange(val: any) {
  val;
  // console.log(val);
}

async function getTypeTree() {
  const params = {
    userId: props.blogData.userId
  };
  const { code, msg, data } = (await listType(params)) as any;
  if (code === 200) {
    typeList.value = data.list;
  } else {
    ElMessage.error('分类数据获取失败', msg);
  }
}

async function getTagList() {
  const { code, msg, data } = (await listTag({})) as any;
  if (code === 200) {
    tagList.value = data.list;
  } else {
    ElMessage.error('标签数据获取失败', msg);
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

function chooseTag(tag: any) {
  console.log('tag', tag);
  tag.isActive = !tag.isActive;
}

function setTags() {
  props.blogData.tags = [];
  tagList.value.forEach((tag: any) => {
    if (tag.isActive) {
      props.blogData.tags.push(tag);
    }
  });
  tagVisible.value = false;
}

onMounted(() => {
  getTypeTree();
  getTagList();
});
defineExpose({
  close,
  open
});
</script>
<style lang="scss">
.el-upload {
  // border: 1px dashed get('font-color');
  background: transparent;
  height: 100px;
  width: 100px;
}
.el-upload-list__item.is-ready {
  height: 100px;
  width: 100px;
}
.button-new-tag {
  width: 60px !important;
}
.tag-item {
  cursor: pointer;
}
</style>
