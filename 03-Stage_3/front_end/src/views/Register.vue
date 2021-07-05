<template>
  <div>
    <v-container class="pl-16 pr-16">
      <!-- alert -->
      <v-alert
        class="alert"
        outlined
        type="success"
        text
        v-show="showSuccessDialog"
        transition="scroll-y-transition"
      >
        注册成功，欢迎{{ username }} !
      </v-alert>
      <!-- alert -->
      <v-alert
        class="alert"
        outlined
        type="warning"
        text
        v-show="showFailDialog"
        transition="scroll-y-transition"
      >
        {{ msg }}
      </v-alert>
      <form class="pa-12 grey lighten-5 mt-10 form">
        <v-text-field v-model="username" label="用户名"></v-text-field>
        <v-text-field v-model="phone" label="手机号码"></v-text-field>
        <v-text-field
          v-model="password"
          type="password"
          label="密码"
        ></v-text-field>
        <v-select v-model="userRole" :items="roles" label="用户角色"></v-select>
        <v-btn class="ml-0 mt-8 info" @click="handleRegister">
          注册
        </v-btn>
      </form>
      <v-btn
        text
        color="primary"
        class="float-right ma-4"
        @click="
          () => {
            this.$router.push('/login');
          }
        "
      >
        去登录
      </v-btn>
    </v-container>
  </div>
</template>

<script>
import { register } from "@/api/user";

export default {
  name: "Register",
  data() {
    return {
      username: "",
      phone: "",
      password: "",
      userRole: "student",
      showSuccessDialog: false,
      showFailDialog: false,
      msg: "",
      roles: ["teacher", "student", "admin"]
    };
  },
  methods: {
    handleRegister() {
      register({
        uname: this.username,
        phone: this.phone,
        password: this.password,
        picture: null,
        userRole: this.userRole.toUpperCase()
      }).then(res => {
        console.log(res);
        if (res.code === 1) {
          window.localStorage.setItem("userId", res.data.id);
          window.localStorage.setItem("userPhone", res.data.phone);
          window.localStorage.setItem("username", res.data.uname);
          window.localStorage.setItem("userRole", res.data.userRole);
          this.username = res.data.uname;
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
          }, 1000);
        } else {
          this.msg = res.msg;
          this.showFailDialog = true;
          setTimeout(() => {
            this.showFailDialog = false;
          }, 1000);
        }
      });
    }
  }
};
</script>

<style scoped>
.form {
  position: relative;
  top: 50px;
}
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>
