<template>
  <div class="float-item-container">
    <el-dropdown
      trigger="click"
      ref="dropDown"
      v-drag
      class="float-item"
      v-dialogDrag
      placement="top"
    >
      <el-button icon="Menu" circle class="float-button"></el-button>
      <template #dropdown>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="routerTo('BlogEditor')">
            <!-- <el-tooltip class="item" effect="dark" content="" placement="left"> -->
            <span>发布博客</span>
            <!-- </el-tooltip> -->
          </el-dropdown-item>
          <el-dropdown-item
            @click.native="routerTo('BlogEditor')"
            :disabled="disabled ? true : false"
          >
            <!-- <el-tooltip class="item" effect="dark" content="" placement="left"> -->
            <span>编辑博客</span>
            <!-- </el-tooltip> -->
          </el-dropdown-item>
          <el-dropdown-item @click.native="routerTo('BlogEditor')">
            <!-- <el-tooltip class="item" effect="dark" content="" placement="left"> -->
            <span>我的博客</span>
            <!-- </el-tooltip> -->
          </el-dropdown-item>
          <el-dropdown-item @click.native="routerTo('BlogEditor')">
            <!-- <el-tooltip class="item" effect="dark" content="" placement="left"> -->
            <span>修改记录</span>
            <!-- </el-tooltip> -->
          </el-dropdown-item>
        </el-dropdown-menu>
      </template>
    </el-dropdown>
    <el-dialog> </el-dialog>
  </div>
</template>
<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
// import { createWebRecord } from '@/api/web';
const router = useRouter();
const webRecord = ref({
  time: '',
  operation: '',
  type: ''
});
const blogData = ref();
const disabled = ref(true);

function routerTo(path: string) {
  router.push({ name: path });
}
function toMyBlogs() {
  //
}
function addWebRecord() {
  //
}

onMounted(() => {
  let dropDown = document.querySelector('.float-item') as any;
  dropDown.style.right = '50px';
  dropDown.style.bottom = '50px';
});
</script>
<style lang="scss">
.float-item {
  position: fixed;
  cursor: pointer;
  .el-button.is-circle {
    padding: 6px;
    background: rgba(17, 17, 17, 0.8);
    color: #fff;
    i {
      font-size: 20px;
    }
  }
  .el-button.is-circle:hover {
    // background: #fff;
    border: 1px solid #dcdfe6;
    background: rgba(17, 17, 17, 0.8);
    color: #fff;
    // color: #606266;
  }
}
.el-dropdown-menu {
  background-color: rgba(29, 32, 33, 0.6);
  box-shadow: 0 2px 12px 0 #000000;
  border: none;
  padding: 0px;
  .el-dropdown-menu__item {
    background-color: transparent;
    span {
      color: #fff;
    }
    padding: 0px 12px;
  }
  .el-dropdown-menu__item:hover {
    background-color: rgba(54, 55, 56, 0.9);
    span {
      color: #fff;
    }
  }
}
</style>
