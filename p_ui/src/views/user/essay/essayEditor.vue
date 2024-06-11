<!--
 * @Description: 随笔编辑
-->
<template>
  <div class="page-main essay-editor">
    <el-form :model="essayData" label-width="85px" class="essay-form" label-position="left">
      <el-form-item label="随笔内容">
        <el-input
          v-model="essayData.content"
          type="textarea"
          maxlength="300"
          show-word-limit
          :rows="5"
          placeholder="写点什么吧。。。"
        ></el-input>
      </el-form-item>
      <el-form-item label="随笔图片">
        <upload v-model="essayData.imageRelations"></upload>
        <el-dialog v-model="dialogVisible">
          <img w-full :src="dialogImageUrl" alt="Preview Image" />
        </el-dialog>
      </el-form-item>
    </el-form>

    <div class="essay-editor-footer">
      <el-button @click="save()">保存</el-button>
      <el-button @click="submit()">发布</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';
import { listEssay, saveEssay, getEssayById } from '@/api/essay.ts';
import useUserStore from '@/store/modules/user';
import { Plus } from '@element-plus/icons-vue';
import { ElMessage, ElNotification, type UploadProps, type UploadUserFile } from 'element-plus';
import upload from '@/components/upload/upload.vue';
const router = useRouter();
const userStore = useUserStore();
const essayData = ref({
  name: '',
  content: '',
  imageRelations: []
} as any);

const dialogImageUrl = ref('');
const dialogVisible = ref(false);

const handleRemove: UploadProps['onRemove'] = (uploadFile, uploadFiles) => {
  uploadFile;
  uploadFiles;
};

const handlePictureCardPreview: UploadProps['onPreview'] = uploadFile => {
  dialogImageUrl.value = uploadFile.url!;
  dialogVisible.value = true;
};

function handleSucess(response: any) {
  let imageRelations = essayData.value.imageRelations;
  imageRelations.pop();
  if (response.code == 200) {
    imageRelations.push({
      name: response.data.replace('http://111.229.144.36:8008/', ''),
      url: response.data
    });
  } else {
    ElMessage.error('图片上传失败');
  }
}

async function submit() {
  const { code, msg, data } = (await saveEssay(essayData.value)) as any;
  if (code == 200) {
    ElNotification.success({
      title: 'Success',
      message: '随笔发布成功\n点击前往随笔',
      offset: 100,
      onClick: () => {
        router.push('essay');
      }
    });
  }
}

async function getEssayData(id: any) {
  const { code, msg, data } = (await getEssayById({ essayId: id })) as any;
  if (code == 200) {
    essayData.value = data;
    essayData.value.imageRelations = [];
    data.images.forEach((img: any) => {
      let spArr = img.split('/');
      essayData.value.imageRelations.push({ name: spArr[spArr.length - 1], url: img });
    });
  }
}

//保存随笔到本地
function save() {
  window.localStorage.setItem('essayData', JSON.stringify(essayData.value));
  ElMessage.success('保存成功');
}

onMounted(() => {
  const { id } = router.currentRoute.value.query;
  let tempEssayData = window.localStorage.getItem('essayData');
  if (id) {
    getEssayData(id);
  } else if (tempEssayData) {
    essayData.value = JSON.parse(tempEssayData);
  }
});
</script>
<style lang="scss" scoped>
@include theme() {
  .essay-editor {
    background: get('background');
    box-shadow: get('box-shadow');
    border-radius: 15px;
    padding: 30px;
    width: calc(86% - 60px) !important;
    min-height: calc(100% - 150px) !important;
    position: relative;
    display: flex;
    justify-content: center;
  }
}
.essay-form {
  width: 100%;
  height: calc(100% - 40px);
}
:deep(.el-form-item .el-form-item__label) {
  font-size: 18px;
}
:deep(.el-textarea__inner) {
  background-image: url('@/assets/png/pikachu-sit.png') !important;
  background-repeat: no-repeat !important;
  object-fit: cover !important;
  background-size: 12% auto !important;
  background-position: 99% -13% !important;
}
.essay-editor-footer {
  position: absolute;
  bottom: 20px;
  width: calc(100% - 40px);
}
</style>
