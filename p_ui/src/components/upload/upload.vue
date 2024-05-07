<template>
  <el-upload
    v-if="!Array.isArray(props.modelValue)"
    class="avatar-uploader"
    :action="uploadAction"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload"
    crossorigin="anonymous"
  >
    <el-image v-if="props.modelValue" :src="props.modelValue" class="avatar" />
    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
  </el-upload>
  <el-upload
    v-else
    v-model:file-list="props.modelValue"
    :action="uploadAction"
    list-type="picture-card"
    :on-preview="handlePictureCardPreview"
    :on-remove="handleRemove"
    :on-success="handleSucess"
  >
    <el-icon><Plus /></el-icon>
  </el-upload>
  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog>
</template>
<script setup lang="ts">
import { ref, nextTick, onMounted, watch } from 'vue';
import type { UploadProps } from 'element-plus';
import { ElMessage } from 'element-plus';

const uploadAction = ` ${
  process.env.NODE_ENV === 'development' ? '/dev-api' : '/prod-api'
}/pw/blog/uploadImg`;
const dialogImageUrl = ref('');
const dialogVisible = ref(false);
const emit = defineEmits(['update:modelValue']);
const props = defineProps({
  modelValue: null
});
const imgList = ref([] as any);

const handleAvatarSuccess: UploadProps['onSuccess'] = (response: any) => {
  if (response.data) emit('update:modelValue', response.data);
  else ElMessage.error('上传失败');
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
const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
  uploadFile;
  uploadFiles;
};

const handlePictureCardPreview: UploadProps['onPreview'] = uploadFile => {
  dialogImageUrl.value = uploadFile.url!;
  dialogVisible.value = true;
};

function handleSucess(response: any) {
  let imageRelations = props.modelValue;
  imageRelations.pop();
  if (response.code == 200) {
    imageRelations.push({
      name: response.data.replace('http://111.229.144.36:8008/', ''),
      url: response.data
    });
  } else {
    ElMessage.error('图片上传失败');
  }
  emit('update:modelValue', imageRelations);
}
// watch(
//   () => props.modelValue,
//   newValue => {
//     // console.log('sss', newValue);
//   },
//   {
//     deep: true
//   }
// );
</script>
<style lang="scss" scoped>
@include theme() {
  .avatar-uploader {
    border: 1px dashed get('border-color');
    background: transparent;
    height: 100px;
    width: 100px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 4px;
    :deep(.el-upload) {
      height: 100px;
      width: 100px;
    }
  }
}
</style>
