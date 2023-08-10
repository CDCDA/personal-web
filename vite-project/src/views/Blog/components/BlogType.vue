<template>
  <div class="blog-type-component">
    <span class="type-name">分类</span>
    <el-tree
      class="type-tree"
      :data="typeTree"
      :props="defaultProps"
      :default-expand-all="true"
      node-key="key"
      :indent="0"
      @node-click="clickFoucus"
    >
      <template #default="{ node, data }">
        <span class="custom-tree-node">
          <span class="type-node-name">{{ data.typeName }}</span>
          <el-icon v-if="isShowView[data.typeId]">
            <View></View>
          </el-icon>
        </span>
      </template>
    </el-tree>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { listTypeTree } from '@/api/type';
import useUserStore from '@/store/modules/user';
const userStore = useUserStore();
const typeTree = ref([
  {
    name: '前端',
    key: '1',
    children: [
      { name: 'html', key: '1-1' },
      { name: 'css', key: '1-2' },
      { name: 'javascript', key: '1-3' }
    ]
  },
  {
    name: '后端',
    key: '2',
    children: [
      { name: 'springboot', key: '2-1' },
      { name: 'tomcat', key: '2-2' }
    ]
  },
  {
    name: '数据库',
    key: '3',
    children: [
      { name: 'mysql', key: '3-1' },
      { name: 'dbeaver', key: '3-2' }
    ]
  },
  {
    name: '记录',
    key: '4',
    children: [
      { name: '问题记录', key: '4-1' },
      { name: '分享', key: '4-2' }
    ]
  }
]);
const isShowView = ref([] as any);
const defaultProps = {
  children: 'children',
  label: 'typeName',
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
    typeTree.value = data;
  }
}

onMounted(() => {
  getTypeTree(userStore.userId);
});
</script>
<style lang="less" scoped>
.blog-type-component {
  background-color: transparent;
  border-radius: 10px;
  box-shadow: 0 2px 12px 0 #000000;
  color: rgba(255, 255, 255, 0.6);
  text-align: left;
  // margin: 5px;
  width: 100%;
  border-radius: 5px;
  color: #fff;
  background-color: transparent;
  font-size: 15px;
}
.custom-tree-node {
  display: flex;
  justify-content: space-between;
  width: 80%;
  height: 30px;
  line-height: 30px;
}
.type-node-name,
.icon-view {
  height: 30px;
  line-height: 30px;
}
.type-tree {
  background-color: transparent;
  border-radius: 5px;
  color: #fff;
  font-size: 15px;
  margin: 5px;
  padding-bottom: 12px;
}
:v-deep(.el-tree-node__content:hover) {
  background-color: transparent;
  border-radius: 5px;
}
:v-deep(.el-tree-node .el-tree-node__content) {
  background-color: transparent !important;
  border-radius: 5px;
}
:v-deep(.el-tree-node:focus > .el-tree-node__content) {
  background-color: #ffffff;
}
.type-name {
  margin-top: 15px;
  display: block;
  text-align: center;
  padding-top: 15px;
}
:v-deep(.el-tree-node) {
  position: relative;
}
:v-deep(.el-tree-node__children) {
  padding-left: 16px; // 缩进量
}
:v-deep(.el-tree-node::before) {
  content: '';
  height: 100%;
  width: 1px;
  position: absolute;
  left: -3px;
  top: -26px;
  border-width: 1px;
  border-left: 1px dashed #52627c;
}
// 当前层最后一个节点的竖线高度固定
:v-deep(.el-tree-node:last-child::before) {
  height: 38px; // 可以自己调节到合适数值
}

// 横线
:v-deep(.el-tree-node::after) {
  content: '';
  width: 24px;
  height: 20px;
  position: absolute;
  left: -3px;
  top: 12px;
  border-width: 1px;
  border-top: 1px dashed #52627c;
}
</style>
<style lang="less" scoped>
:v-deep(& > .el-tree-node::after) {
  border-top: none;
}
:v-deep(& > .el-tree-node::before) {
  border-left: none;
}

// 展开关闭的icon
:v-deep(.el-tree-node__expand-icon) {
  font-size: 16px;
  // 叶子节点（无子节点）
  &.is-leaf {
    color: transparent;
    // display: none; // 也可以去掉
  }
}
</style>
