<template>
  <div class="logIn">
    <!-- <div>
      <random-slogan></random-slogan>
    </div> -->
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
        <!-- <div>
          <button @click="login">登录</button>
          <button @click="register">注册</button>
        </div> -->
      </div>
    </div>
    <!-- <button class="bt-login">LOG IN</button> -->
  </div>
</template>

<script lang="ts">
// import RandomSlogan from '@/components/RandomSlogan.vue';
// import { login } from '@/api/login';
export default {
  components: {
    // RandomSlogan
  },
  data() {
    return {
      account: '',
      password: ''
    };
  },
  methods: {
    // login() {
    //   if (this.account && this.password) {
    //     login({ account: this.account, password: this.password }).then(res => {
    //       if (res == 1) {
    //         this.$router.push('/Main');
    //       }
    //     });
    //   } else {
    //     this.$message.error('请输入账号密码');
    //   }
    // },
    // register() {}
  },
  mounted() {}
};
</script>
<style lang="less" scoped>
.logIn {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.box {
  width: 500px;
  height: 350px;
  border-top: 1px solid rgba(255, 255, 255, 0.5);
  border-left: 1px solid rgba(255, 255, 255, 0.5);
  border-bottom: 1px solid rgba(255, 255, 255, 0.3);
  border-right: 1px solid rgba(255, 255, 255, 0.3);
  -webkit-backdrop-filter: blur(10px);
  backdrop-filter: blur(2px);
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
}

.box .btn-box > div > button:nth-of-type(1) {
  width: 120px;
  height: 35px;
  color: rgba(255, 255, 255, 0.9);
  border: 1px solid rgba(192, 119, 91, 0.7);
  background: rgba(192, 119, 91, 0.5);
}

.box .btn-box > div > button:nth-of-type(2) {
  width: 120px;
  height: 35px;
  margin-left: 10px;
  color: rgba(255, 255, 255, 0.9);
  border: 1px solid rgba(192, 119, 91, 0.7);
  background: rgba(192, 119, 91, 0.5);
}

.box .btn-box > div > button:hover {
  border: 1px solid rgba(251, 128, 71, 0.7);
  background: rgba(251, 128, 71, 0.5);
}
</style>
