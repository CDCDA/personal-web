<!--
 * @Description: 个人信息管理
-->
<template>
  <div class="personal-page">
    <div class="user-info">
      <c-image class="user-info-back" src="http://1.92.159.74:8008/Ruins.jpg" />

      <div class="info">
        <c-image class="user-avatar" v-if="!isEdit" :src="userInfo.avatar" />
        <upload class="user-avatar-upload" v-else v-model="userInfo.avatar" />
        <div class="user-info-detail">
          <div class="nickName">{{ userInfo.nickName }}</div>
          <div class="account">{{ userInfo.account }}</div>
          <div class="createTime" style="margin-top: 5px">
            入站时间：{{ formatDate(new Date(userInfo.createTime), 'YY-MM') }}
          </div>
        </div>
      </div>
    </div>
    <!-- <el-divider style="width: calc(100% - 20px); margin: 0px 10px"></el-divider> -->
    <el-form ref="formRef" class="info-form" label-width="70" :rules="rules" :model="form">
      <el-tooltip content="编辑" placement="top">
        <el-icon class="edit" @click="showEdit">
          <Edit />
        </el-icon>
      </el-tooltip>
      <el-form-item label="昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称">
        <span v-if="!isEdit">{{ userInfo.nickName }}</span>
        <el-input v-else v-model="form.nickName"></el-input>
      </el-form-item>
      <el-form-item label="账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号">
        <span>{{ userInfo.account }}</span>
        <!-- <el-input v-else v-model="userInfo.account"></el-input> -->
      </el-form-item>
      <el-form-item label="联系电话">
        <span v-if="!isEdit">{{ userInfo.phone }}</span>
        <el-input v-else v-model="form.phone"></el-input>
      </el-form-item>
      <el-form-item label="邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱">
        <span v-if="!isEdit">{{ userInfo.email }}</span>
        <el-input v-else v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别">
        <span v-if="!isEdit">{{ userInfo.sex === '0' ? '男' : '女' }}</span>
        <el-radio-group v-else v-model="form.sex" style="margin-left: 15px">
          <el-radio label="0" size="small">男</el-radio>
          <el-radio label="1" size="small">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="个人简介">
        <span v-if="!isEdit">{{ userInfo.introduction }}</span>
        <el-input v-else type="textarea" v-model="form.introduction"></el-input>
      </el-form-item>
      <div class="user-footer" v-show="isEdit">
        <el-button @click="submit">保存</el-button> <el-button @click="cancel">取消</el-button>
      </div>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, reactive } from 'vue';
import { formatDate } from '@/utils/date';
import useUserStore from '@/store/modules/user';
import { getUserById, saveUser } from '@/api/system/user';
import { ElNotification } from 'element-plus';
import upload from '@/components/upload/upload.vue';
const userInfo = ref({} as any);
const userStore = useUserStore();
const isEdit = ref(false as any);
const form = ref({} as any);
const formRef = ref(null as any);
const rules = ref({
  nickName: [{ required: true, message: 'Please input Activity name', trigger: 'blur' }]
});
async function getUserInfo(userId: any) {
  const { code, msg, data } = (await getUserById(userId)) as any;
  if (code === 200 && data) {
    Object.assign(userInfo.value, data);
    form.value = JSON.parse(JSON.stringify(userInfo.value));
  }
}

async function submit() {
  await formRef.value.validate(async (valid: any) => {
    if (valid) {
      const { code, msg, data } = (await saveUser(form.value)) as any;
      if (code === 200) {
        ElNotification({
          title: 'Success',
          message: '修改成功',
          type: 'success'
        });
        getUserInfo(userStore.userId);
      }
    }
  });
}

function cancel() {
  form.value = JSON.parse(JSON.stringify(userInfo.value));
}

function showEdit() {
  isEdit.value = !isEdit.value;
}

onMounted(() => {
  getUserInfo(userStore.userId);
});
</script>
<style lang="scss" scoped>
@include theme() {
  .personal-page {
    height: 100%;
    color: get('font-color');
    .user-info {
      position: relative;
      display: flex;
      justify-content: start;
      align-items: center;
      position: relative;
      padding: 20px 20px 20px 0px;
      width: calc(100% - 19px);
      height: 30vh;
      color: white;
      .user-info-back {
        position: absolute;
        width: 100%;
        filter: brightness(0.6);
        height: 100%;
      }
      .user-info-detail {
        text-align: left;
        justify-content: space-between;
        display: flex;
        flex-direction: column;
      }
      .user-avatar,
      :deep(.c-uploader) {
        border-radius: 8px;
        margin-right: 40px;
        width: 130px;
        height: 130px;
      }
      .info {
        display: flex;
        flex-direction: row;
        width: 100%;
        position: absolute;
        filter: brightness(1.5);
        justify-content: start;
        margin-left: 50px;
      }
    }
    .info-form {
      margin: 20px;
      position: relative;
      height: calc(100% - 30vh - 80px);
      .el-input,
      .el-textarea {
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
      .edit {
        z-index: 1;
        position: absolute;
        right: 30px;
        top: -5px;
        margin: auto;
        font-size: 35px;

        cursor: pointer;
      }
    }
    .nickName {
      font-weight: bold;
      font-size: 25px;
    }
    .account {
      font-weight: bold;
      font-size: 22px;
    }
    .createTime {
      font-size: 16px;
    }
  }
  // .info-column {
  //   width: 30%;
  //   height: 100%;
  //   display: flex;
  //   flex-direction: column;
  //   justify-content: space-between;
  // }
  // .info-column:nth-child(1) {
  //   box-shadow: 0 1px 2px rgba(195, 195, 195, 0.6);
  //   background: transparent;
  //   backdrop-filter: blur(10px);
  //   border-radius: 8px;
  // }
  .blog-count,
  .type-count,
  .tag-count,
  .essay-count {
    width: calc(100% - 10px);
    height: calc(50% - 10px);
    box-shadow: 0 1px 2px rgba(195, 195, 195, 0.6);
    background: transparent;
    backdrop-filter: blur(10px);
    border-radius: 8px;
  }
  .blog-count {
    margin: 0px 10px 5px 10px;
  }
  .type-count {
    margin: 5px 10px 0px 10px;
  }
  .tag-count {
    margin: 0px 10px 5px 10px;
  }
  .essay-count {
    margin: 5px 10px 0px 10px;
  }
}
</style>
