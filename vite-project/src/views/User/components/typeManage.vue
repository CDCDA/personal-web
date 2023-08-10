<!--
 * @Author: chenyd
 * @Date: 2022-07-12 09:28:14
 * @LastEditTime: 2023-07-13 19:02:59
 * @Description: 
-->
<template>
  <div class="type-manage">
    <div style="text-align: left">
      <el-button @click="addFirst" icon="Plus" style="margin: 25px 20px 10px 25px">新增</el-button>
    </div>

    <el-tree
      :data="typeTree"
      show-checkbox
      node-key="typeId"
      default-expand-all
      :props="defaultProps"
      :expand-on-click-node="false"
    >
      <template #default="{ node, data }">
        <span class="custom-tree-node">
          <span>{{ node.label }}</span>
          <span>
            <el-icon @click="edit(data)">
              <Edit></Edit>
            </el-icon>
            <el-icon @click="append(data)" style="margin-left: 8px">
              <Plus></Plus>
            </el-icon>
            <el-icon style="margin-left: 8px" @click="remove(data)">
              <Delete></Delete>
            </el-icon>
          </span>
        </span>
      </template>
    </el-tree>
    <el-dialog v-model="dialogVisible" width="500">
      <el-input v-model="currentName"></el-input>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="confirm"> 确定 </el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { listTypeTree, delBlogType, saveBlogType } from '@/api/type';
import useUserStore from '@/store/modules/user';
import type Node from 'element-plus/es/components/tree/src/model/node';
import { ElMessage, ElMessageBox } from 'element-plus';

interface Tree {
  typeId: string;
  typeName: string;
  children?: Tree[];
}
const userStore = useUserStore();
const typeTree = ref([] as any);
const defaultProps = {
  children: 'children',
  label: 'typeName'
};
let action = '';
const currentName = ref('');
let currentNodeData = {} as any;
const dialogVisible = ref(false);

async function getTypeTree(userId: any) {
  const { code, msg, data } = (await listTypeTree({ userId })) as any;
  if (code === 200 && data) {
    typeTree.value = data;
  }
}

async function save(node: any) {
  const { code, msg, data } = (await saveBlogType(node)) as any;
  if (code === 200) {
    ElMessage.success('保存成功');
    getTypeTree(userStore.userId);
  }
}

async function del(id: string) {
  const { code, msg, data } = (await delBlogType(id)) as any;
  if (code === 200) {
    ElMessage.success('删除成功');
    getTypeTree(userStore.userId);
  }
}

function addFirst() {
  action = 'edit';
  currentNodeData = {
    typeId: '',
    typeName: '',
    children: [],
    userId: userStore.userId,
    parentId: '0'
  };
  dialogVisible.value = true;
}

function confirm() {
  if (action === 'edit') {
    currentNodeData.typeName = currentName.value;
    save(currentNodeData);
  } else if (action === 'add') {
    const newChild = {
      typeId: '',
      typeName: currentName.value,
      children: [],
      userId: userStore.userId,
      parentId: currentNodeData.typeId
    };
    // if (!currentNodeData.children) {
    //   currentNodeData.children = [];
    // }
    // currentNodeData.children.push(newChild);
    // typeTree.value = [...typeTree.value];
    save(newChild);
  }
  dialogVisible.value = false;
}

function edit(data: Tree) {
  dialogVisible.value = true;
  currentNodeData = data;
  action = 'edit';
  currentName.value = data.typeName;
}

function append(data: Tree) {
  action = 'add';
  currentNodeData = data;
  currentName.value = '';
  dialogVisible.value = true;
}

function remove(data: Tree) {
  action = 'del';
  ElMessageBox.confirm('是否删除该分类?', 'Warning', {
    confirmButtonText: '确实',
    cancelButtonText: '取消',
    type: 'warning'
  })
    .then(() => {
      del(data.typeId);
    })
    .catch(() => {});

  // const parent = node.parent;
  // const children: Tree[] = parent.data.children || parent.data;
  // const index = children.findIndex(d => d.typeId === data.typeId);
  // children.splice(index, 1);
  // typeTree.value = [...typeTree.value];
}

onMounted(() => {
  getTypeTree(userStore.userId);
});
</script>
<style lang="less">
.type-manage {
  width: 100%;
  height: 100%;
  .el-tree {
    padding: 15px;
    width: 50%;
    min-width: 300px;
    .custom-tree-node {
      display: flex;
      width: 100%;
      justify-content: space-between;
    }
  }
}
</style>
