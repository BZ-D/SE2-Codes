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
        登录成功，欢迎{{ username }} !
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
        <v-text-field v-model="phone" label="手机号码"></v-text-field>
        <v-text-field
          v-model="password"
          label="密码"
          type="password"
        ></v-text-field>
        <v-btn class="ml-0 mt-8 info" @click="handleLogin">
          登录
        </v-btn>
      </form>
      <v-btn
        text
        color="primary"
        class="float-right ma-4"
        @click="
          () => {
            this.$router.push('/register');
          }
        "
      >
        去注册
      </v-btn>
    </v-container>
  </div>
</template>

<script>
import { login } from "@/api/user";

export default {
  name: "Login",
  data() {
    return {
      phone: "",
      password: "",
      showSuccessDialog: false,
      showFailDialog: false,
      msg: "",
      username: ""
    };
  },
  methods: {
    handleLogin() {
      login({ phone: this.phone, password: this.password }).then(res => {
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
          if (res.data.userRole === "STUDENT") {
            setTimeout(() => {
              this.$router.push("/student");
            }, 800);
          } else if (res.data.userRole === "TEACHER") {
            setTimeout(() => {
              this.$router.push("/teacher");
            }, 800);
          } else if (res.data.userRole === "ADMIN") {
            setTimeout(() => {
              this.$router.push("/admin");
            }, 800);
          }
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
