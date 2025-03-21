<!--
 * @Description: 随笔编辑
-->
<template>
  <div class="page-main essay-editor">
    <el-form
      :model="essayData"
      label-width="85px"
      class="essay-form"
      label-position="left"
      @submit.native.prevent
    >
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
        <upload v-model="essayData.imageRelations" path="essay"></upload>
        <c-dialog v-model="dialogVisible">
          <img w-full :src="dialogImageUrl" alt="Preview Image" />
        </c-dialog>
      </el-form-item>
      <el-form-item label="常用标签">
        <el-tag v-for="tag in commonUseTags" class="tag-item" @click.native="addTag(tag)">
          {{ tag }}
        </el-tag>
      </el-form-item>
      <el-form-item label="博客标签">
        <el-tag
          v-for="tag in essayData.tags"
          :key="tag"
          class="tag-item"
          closable
          :disable-transitions="false"
          @close="tagDel(tag)"
        >
          {{ tag.tagName }}
        </el-tag>

        <el-input
          v-if="inputVisible"
          ref="InputRef"
          v-model="inputValue"
          class="ml-1 w-20 button-new-tag"
          size="small"
          @keyup.enter="addTag"
          @blur="addTag"
        />
        <el-button v-else class="button-new-tag ml-1" size="small" @click="showInput">
          + Tag
        </el-button>
      </el-form-item>
      <el-form-item label="发布时间">
        <el-date-picker v-model="essayData.createTime"></el-date-picker>
      </el-form-item>
    </el-form>

    <div class="essay-editor-footer">
      <el-button @click="save()">保存</el-button>
      <el-button @click="submit()">发布</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted, nextTick } from 'vue';
import { useRouter } from 'vue-router';
import { saveEssay, getEssayById } from '@/api/essay.ts';
import useUserStore from '@/store/modules/user';
import { ElNotification } from 'element-plus';
import upload from '@/components/upload/upload.vue';
import { formatDate } from '@/utils/date.ts';
const router = useRouter();
const userStore = useUserStore();
const commonUseTags = ref(['游戏', '吐槽', '分享', '生活', '音乐', '电影', '开发']);
const essayData = ref({
  name: '',
  content: '',
  imageRelations: [],
  tags: [],
  createTime: new Date()
} as any);
const inputValue = ref('');
const inputVisible = ref(false);
const dialogImageUrl = ref('');
const dialogVisible = ref(false);
const InputRef = ref() as any;
function showInput() {
  inputVisible.value = true;
  nextTick(() => {
    InputRef.value!.input!.focus();
  });
}

function resetEssay() {
  essayData.value = {
    name: '',
    content: '',
    imageRelations: [],
    tags: [],
    createTime: new Date()
  };
}

//添加标签
function addTag(tag: String) {
  const { tags } = essayData.value;
  let tagName = tag || inputValue.value;
  if (tagName) {
    let tempTag = tags.filter((x: any) => x.tagName == tagName);
    console.log(tags, tempTag);
    if (tempTag.length == 0) {
      tags.push({ tagName });
    } else {
      ElNotification.warning({
        message: '已经选过了 ╮(╯▽╰)╭',
        offset: 100
      });
    }
  }
  inputVisible.value = false;
  inputValue.value = '';
}
//删除标签
function tagDel(tag: string) {
  const { tags } = essayData.value;
  tags.splice(tags.indexOf(tag), 1);
}

async function submit() {
  let form = JSON.parse(JSON.stringify(essayData.value));
  form.createTime = formatDate(new Date(form.createTime));
  form.tags = JSON.stringify(essayData.value.tags);
  if (!form.content) {
    ElNotification.warning('别发个空随笔');
  }
  const { code, msg, data } = (await saveEssay(form)) as any;
  if (code == 200) {
    resetEssay();
    ElNotification.success({
      title: '随笔发布成功',
      message: '点击前往随笔',
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
    Object.assign(essayData.value, data);
    essayData.value.imageRelations = [];
    data.images.forEach((img: any) => {
      let spArr = img.split('/');
      essayData.value.imageRelations.push({ name: spArr[spArr.length - 1], url: img, id: null });
    });
    console.log('SSS', essayData.value.imageRelations);
    if (data.tags) essayData.value.tags = JSON.parse(data.tags);
  }
}

//保存随笔到本地
function save() {
  window.localStorage.setItem('essayData', JSON.stringify(essayData.value));
  ElNotification.success({
    title: '保存成功',
    offset: 100
  });
}

onMounted(() => {
  essayData.value.createTime = new Date();
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
    background: get('back') !important;
    box-shadow: get('box-shadow');
    padding: 30px;
    width: calc(80% - 60px) !important;
    min-height: calc(100vh - 3.7rem - 60px) !important;
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
  font-size: 0.9rem;
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
.button-new-tag {
  width: 60px !important;
}
.tag-item {
  cursor: pointer;
}
</style>
