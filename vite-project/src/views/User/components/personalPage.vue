<!--
 * @Description: 个人信息管理
-->
<template>
  <div class="personal-page">
    <div class="user-info">
      <el-image
        style="width: 100px; height: 100px"
        src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
        fit="['fill', 'contain', 'cover', 'none', 'scale-down']"
      />
      <div class="info">
        <div>{{ userInfo.nickName }}</div>
        <div>{{ userInfo.account }}</div>
        <div style="margin-top: 5px; font-size: 13px">
          入站时间：{{ formateDate(new Date(userInfo.createTime), 'YY-MM') }}
        </div>
      </div>
    </div>
    <el-divider style="width: calc(100% - 20px); margin: 0px 10px"></el-divider>
    <el-form class="info-form" label-width="70">
      <el-form-item label="联系电话">
        <span v-if="!isEdit[1]">{{ userInfo.phone }}</span>
        <el-input v-else v-model="userInfo.phone"></el-input
        ><el-icon @click="showEdit(1)"><Edit></Edit></el-icon>
      </el-form-item>
      <el-form-item label="邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱">
        <span v-if="!isEdit[2]">{{ userInfo.email }}</span>
        <el-input v-else v-model="userInfo.email"></el-input
        ><el-icon @click="showEdit(2)"><Edit></Edit></el-icon>
      </el-form-item>
      <el-form-item label="性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别">
        <span v-if="!isEdit[3]">{{ userInfo.sex === '0' ? '男' : '女' }}</span>
        <el-input v-else v-model="userInfo.sex"></el-input
        ><el-icon @click="showEdit(3)"><Edit></Edit></el-icon>
      </el-form-item>
      <el-form-item label="个人简介">
        <span v-if="!isEdit[4]">{{ userInfo.introduction }}</span>
        <el-input v-else type="textarea" v-model="userInfo.introduction"></el-input
        ><el-icon @click="showEdit(4)"><Edit></Edit></el-icon>
      </el-form-item>
      <div class="user-footer"><el-button>保存</el-button> <el-button>取消</el-button></div>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue';
import { formateDate } from '@/utils/date';
import useUserStore from '@/store/modules/user';
import { getUserById } from '@/api/user';

const userInfo = ref({} as any);
const userStore = useUserStore();
const isEdit = ref([] as any);
async function getUserInfo(userId: any) {
  const { code, msg, data } = (await getUserById(userId)) as any;
  if (code === 200 && data) {
    Object.assign(userInfo.value, data);
  }
}

function showEdit(index: any) {
  isEdit.value = [];
  isEdit.value[index] = true;
}

function clearEdit() {
  isEdit.value = [];
}

onMounted(() => {
  getUserInfo(userStore.userId);
});
</script>
<style lang="scss">
@include theme() {
  .personal-page {
    height: 100%;
    .user-info {
      display: flex;
      justify-content: start;
      align-items: center;
      margin: 20px;
      .el-image {
        border-radius: 5px;
        margin-right: 20px;
      }
      .info {
        display: flex;
        flex-direction: column;
      }
    }
    .info-form {
      margin: 20px;
      position: relative;
      height: calc(100% - 180px);
      .el-input {
        width: 250px;
        margin-left: 15px;
      }
      .el-textarea {
        width: calc(100% - 100px);
      }
      .el-icon {
        font-size: 25px;
        margin-left: 10px;
        // visibility: hidden;
      }
      .el-icon:hover {
        visibility: visible;
      }
      .el-form-item__content > span {
        margin-left: 15px;
      }
      // .el-button {
      //   background: transparent;
      //   color: get('font-color');
      // }
      .user-footer {
        bottom: 0px;
        position: absolute;
        width: 100%;
      }
      .el-pagination {
        display: flex;
        justify-content: center;
      }
    }
  }
}
</style>
