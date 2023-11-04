<!--
 * @Description:  子菜单
-->
<template>
  <el-menu-item
    v-if="!item.children"
    :index="item.index"
    @click="clickMenu(item)"
    class="nav-menu-item"
  >
    <!-- <el-icon>
      <component :is="item.icon" />
    </el-icon> -->
    <span class="first-menu-name" slot="title">{{ item.label }}</span>
  </el-menu-item>
  <el-sub-menu :index="item.index" :class="[item.className]" v-else>
    <template #title>
      <!-- <el-icon>
        <component :is="item.icon" />
      </el-icon> -->
      <span>{{ item.label }}</span>
    </template>
    <nav-menu-item @open="open" v-for="(child, i) in item.children" :item="child" :key="i" />
  </el-sub-menu>
</template>
<script setup lang="ts">
import { ElMessageBox } from 'element-plus';
import { reactive, ref } from 'vue';
import { useRouter } from 'vue-router';
import useUserStore from '@/store/modules/user';
const userStore = useUserStore();
const emit = defineEmits(['open']);
const itemData = reactive({});
const props = defineProps({
  item: null
});
const router = useRouter();

function open() {
  emit('open');
}
function clickMenu(item: any) {
  if (item.name === 'skinPeeler') {
    emit('open');
  } else if (item.name === 'logOut') {
    ElMessageBox.confirm('确定注销并退出系统吗？', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
      .then(() => {
        userStore.userId = '';
        userStore.userName = '';
        window.localStorage.setItem('token', '');
        router.push({ name: 'login' });
      })
      .catch(() => {});
  } else router.push({ name: item.name });
}
</script>
<style>
:root {
  --borderColor: #888888;
}
</style>
<style lang="scss" scoped>
@include theme() {
  .setting-menu {
    i {
      color: get('font-color');
    }
    ::v-deep .el-sub-menu__icon-arrow {
      color: transparent;
    }
  }
}
.nav-menu-item {
  li:hover {
    background-color: transparent !important;
    z-index: 1;
    display: flex;
    align-items: center;
    justify-content: center;
  }
}

.setting-menu {
  width: 64px;

  :v-deep(.el-icon.el-sub-menu__icon-arrow) {
    display: none;
  }
}
</style>
