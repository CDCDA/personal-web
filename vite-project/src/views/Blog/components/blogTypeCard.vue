<!--
 * @Description:  分类卡
-->
<template>
  <div class="blog-type-card c-card">
    <div class="card-header">
      <el-icon><FolderOpened /></el-icon><span class="type-nmae">分类</span>
    </div>
    <div class="type-list">
      <div class="type-item" v-for="item in typeList">
        <span class="type-item-prepend">#</span>{{ item.typeName
        }}<span class="type-item-suffix">#</span>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { listTypeTree } from '@/api/type';
import useUserStore from '@/store/modules/user';
const userStore = useUserStore();
const typeList = ref([
  {
    typeName: '前端',
    key: '1',
    children: [
      { typeName: 'html', key: '1-1' },
      { typeName: 'css', key: '1-2' },
      { typeName: 'javascript', key: '1-3' }
    ]
  },
  {
    typeName: '后端',
    key: '2',
    children: [
      { typeName: 'springboot', key: '2-1' },
      { typeName: 'tomcat', key: '2-2' }
    ]
  },
  {
    typeName: '数据库',
    key: '3',
    children: [
      { typeName: 'mysql', key: '3-1' },
      { typeName: 'dbeaver', key: '3-2' }
    ]
  },
  {
    typeName: '记录',
    key: '4',
    children: [
      { typeName: '问题记录', key: '4-1' },
      { typeName: '分享', key: '4-2' }
    ]
  }
]);
const isShowView = ref([] as any);
const defaultProps = {
  children: 'children',
  label: 'typetypeName',
  isLeaf: 'leaf'
};

function clickFoucus(data: any) {
  let key = data.typeId;
  isShowView.value = [];
  isShowView.value[key] = true;
}

async function getTypeTree(userId: any) {
  const { code, msg, data } = (await listTypeTree({ userId })) as any;
  if (code === 200 && data) {
    // typeList.value = data;
  }
}

onMounted(() => {
  getTypeTree(userStore.userId);
});
</script>
<style lang="scss" scoped>
@include theme() {
  .blog-type-card {
    text-align: left;
    .card-header {
      @include flex;
      width: 100%;
      justify-content: start;
      font-size: 19px;
      font-weight: bold;
      .el-icon {
        margin: 8px;
        font-size: 22px;
      }
    }
    .type-list {
      @include flex;
      justify-content: start;
      flex-wrap: wrap;
      .type-item {
        padding: 8px 15px;
        border-radius: 8px;
        color: get('font-color');
        border: 1px solid #b9b8b8;
        margin: 8px;
        font-size: 18px;
      }
      .type-item-prepend {
        margin-right: 6px;
        opacity: 0.8;
      }
      .type-item-suffix {
        margin-left: 6px;
        opacity: 0.8;
        background: #d1d1d1;
        border-radius: 5px;
        padding: 0px 7px;
      }
      .type-item:hover {
        background: get('bk');
        color: get('re-font-color');
      }
    }
  }
}
</style>
