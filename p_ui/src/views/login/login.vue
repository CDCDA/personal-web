<template>
  <div class="logIn">
    <div class="box">
      <h2>Login</h2>
      <div class="input-box">
        <label>账号</label>
        <input type="text" v-model="account" />
      </div>
      <div class="input-box">
        <label>密码</label>
        <input type="password" v-model="password" />
      </div>
      <div class="btn-box">
        <a href="#">忘记密码?</a>
        <div>
          <button class="bt-logIn" @click="logIn">登录</button>
          <button class="bt-register" @click="register">注册</button>
        </div>
        <div style="width: 100%">
          <button class="bt-tourist-logIn" @click="handleTouristLogIn">游客登录</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts" name="login">
import { ref } from 'vue';
import jwtDecode from 'jwt-decode';
import { autoClearTimer } from '@/utils/timer';
import { ElMessage } from 'element-plus';
import { login, touristLogIn } from '@/api/system/login.ts';
import { useRouter } from 'vue-router';
import useUserStore from '@/store/modules/user';
import Cookies from 'js-cookie';
import { getUserById } from '@/api/system/user';
import useThemeStore from '@/store/modules/theme.ts';
var themeStore = useThemeStore();
const userStore = useUserStore();
const router = useRouter();
const account = ref('' as any);
const password = ref('' as any);

// 登录
async function logIn() {
  if (account.value && password.value) {
    const { code, data, msg } = (await login({
      account: account.value,
      password: password.value
    })) as any;
    console.log(code, data, msg);
    if (code == 200) {
      //缓存用户数据
      const token = jwtDecode(Cookies.get('token')) as any;
      userStore.token = Cookies.get('token');
      userStore.userId = token.aud;
      userStore.userName = token.username;

      userStore.permission = ['add', 'delete', 'show', 'operate'];
      getUserInfo(token.aud);

      logInFadeOut();
      autoClearTimer(() => {
        console.log('登录成功，跳转至首页');
        router.push('/home');
        const { mhFontColor } = themeStore.options;
        let header = document.querySelector('.common-header') as any;
        let homeTop = document.querySelector('.home-top') as any;
        let CycleUpDown = document.querySelector('.CycleUpDown') as any;
        if (header) {
          header.style.color = mhFontColor;
          let icons = header.querySelectorAll('.theme-icon');
          Object.keys(icons).forEach((e: any) => {
            icons[e].style.fill = mhFontColor;
          });
        }
        if (homeTop) homeTop.style.color = mhFontColor;
        if (CycleUpDown) {
          let themeIcon = CycleUpDown.querySelector('.theme-icon') as any;
          if (themeIcon) {
            themeIcon.style.fill = mhFontColor;
          }
        }
      }, 1200);
    }
  } else {
    ElMessage.error('请输入账号密码');
  }
}
async function register() {
  ElMessage.warning('暂不开放注册');
}

function logInFadeOut() {
  (document.querySelector('.box') as any).classList.add('top-fade-out');
  autoClearTimer(() => {
    (document.querySelector('.box') as any).classList.add('top-fade-out');
  });
}

// 获取用户信息
async function getUserInfo(userId: any) {
  const { code, msg, data } = (await getUserById(userId)) as any;
  if (code === 200 && data) {
    userStore.email = data.email;
    userStore.nickName = data.nickName;
    userStore.avatar = data.avatar;
    window.localStorage.setItem(
      'userData',
      JSON.stringify({
        token: userStore.token,
        userId: userStore.userId,
        userName: userStore.userName,
        permission: userStore.permission,
        email: userStore.email,
        nickName: userStore.nickName,
        avatar: userStore.avatar
      })
    );
  }
}

async function handleTouristLogIn() {
  const { code } = (await touristLogIn()) as any;
  if (code == 200) {
    const token = jwtDecode(Cookies.get('token')) as any;
    userStore.token = Cookies.get('token');
    userStore.userId = token.aud;
    userStore.userName = token.username;
    userStore.permission = [];
    window.localStorage.setItem(
      'userData',
      JSON.stringify({
        token: userStore.token,
        userId: userStore.userId,
        userName: userStore.userName,
        permission: userStore.permission
      })
    );

    router.push('/home');
  }
}
</script>
<style lang="scss" scoped>
@include theme() {
  .logIn {
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
  }
  .box {
    width: 500px;
    height: 400px;
    border-top: 1px solid rgba(255, 255, 255, 0.5);
    border-left: 1px solid rgba(255, 255, 255, 0.5);
    border-bottom: 1px solid rgba(255, 255, 255, 0.3);
    border-right: 1px solid rgba(255, 255, 255, 0.3);
    backdrop-filter: blur(20px);
    background: rgba(50, 50, 50, 0.2);
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 10px;
    margin: 0 0 100px 0;
  }

  .box > h2 {
    color: rgba(255, 255, 255, 0.9);
    margin: 0px 0px 20px 0px;
  }

  .box .input-box {
    display: flex;
    flex-direction: column;
    box-sizing: border-box;
    margin-bottom: 10px;
  }

  .box .input-box label {
    font-size: 13px;
    color: rgba(255, 255, 255, 0.9);
    margin-bottom: 5px;
  }

  .box .input-box input {
    letter-spacing: 1px;
    font-size: 14px;
    box-sizing: border-box;
    width: 250px;
    height: 35px;
    border-radius: 5px;
    border: 1px solid rgba(255, 255, 255, 0.5);
    background: rgba(255, 255, 255, 0.2);
    outline: none;
    padding: 0 12px;
    color: rgba(255, 255, 255, 0.9);
    transition: 0.2s;
  }

  .box .input-box input:focus {
    border: 1px solid rgba(255, 255, 255, 0.8);
  }

  .box .btn-box {
    width: 250px;
    display: flex;
    flex-direction: column;
    align-items: start;
  }

  .box .btn-box > a {
    outline: none;
    display: block;
    width: 250px;
    text-align: end;
    text-decoration: none;
    font-size: 13px;
    color: rgba(255, 255, 255, 0.9);
  }

  .box .btn-box > a:hover {
    color: rgba(255, 255, 255, 1);
  }

  .box .btn-box > div {
    margin-top: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .box .btn-box > div > button {
    outline: none;
    margin-top: 10px;
    display: block;
    font-size: 14px;
    border-radius: 5px;
    transition: 0.2s;
    cursor: pointer;
  }

  .bt-logIn {
    width: 120px;
    height: 35px;
    color: rgba(255, 255, 255, 0.9);
    border: 1px solid get('bk');
    background: get('bk');
  }

  .bt-register {
    width: 120px;
    height: 35px;
    margin-left: 10px;
    color: rgba(255, 255, 255, 0.9);
    border: 1px solid rgba(192, 119, 91, 0.7);
    background: rgba(192, 119, 91, 0.5);
  }
  .bt-tourist-logIn {
    width: 100%;
    height: 35px;
    color: rgba(255, 255, 255, 0.9);
    border: 1px solid rgba(192, 119, 91, 0.7);
    background: rgba(192, 119, 91, 0.5);
  }

  .box .btn-box > div > button:hover {
    border: 1px solid rgba(251, 128, 71, 0.7);
    background: rgba(251, 128, 71, 0.5);
  }
}
button:active {
  transform: translateY(2px);
  transition: all ease-in-out linear 2s;
}
</style>
