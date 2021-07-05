<template>
  <div>
    <!-- alert -->
    <v-alert
      class="alert"
      outlined
      type="success"
      text
      v-show="showAlert"
      transition="scroll-y-transition"
    >
      {{ msg }}
    </v-alert>
    <template>
      <v-dialog v-model="dialog" max-width="400">
        <v-card>
          <v-card-title class="headline">
            账户充值
          </v-card-title>
          <v-text-field
            v-model="value"
            label="充值金额"
            class="pa-6"
          ></v-text-field>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn color="green darken-1" text @click="handleRecharge">
              充值
            </v-btn>

            <v-btn color="green darken-1" text @click="dialog = false">
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </template>

    <v-container class="pl-16 pr-16">
      <form class="pa-12 grey lighten-5 mt-8">
        <v-text-field v-model="userInfo.id" label="ID" readonly></v-text-field>
        <v-text-field
          v-model="userInfo.uname"
          label="用户名"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="userInfo.phone"
          label="手机号"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="userInfo.userRole"
          label="用户角色"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="userInfo.createTime"
          label="注册时间"
          readonly
        ></v-text-field>
        <v-text-field
          v-model="userInfo.balance"
          label="账户余额"
          readonly
        ></v-text-field>
        <v-btn color="primary" dark @click.stop="dialog = true">
          充值
        </v-btn>
      </form>
    </v-container>
  </div>
</template>

<script>
import { getUser } from "@/api/user";
import { recharge } from "@/api/recharge";

export default {
  name: "TeacherCenter",

  data() {
    return {
      userInfo: {
        id: 0,
        uname: "",
        phone: "",
        password: null,
        picture: null,
        balance: 0,
        userRole: "",
        createTime: ""
      },
      dialog: false,
      showAlert: false,
      value: 0,
      msg: ""
    };
  },

  methods: {
    handleRecharge() {
      recharge({ userId: this.userInfo.id, value: this.value }).then(res => {
        console.log(res);
        if (res && res.code === 1) {
          this.msg = res.msg;
          this.dialog = false;
          this.showAlert = true;
          this.refreshUserInfo();
          setTimeout(() => {
            this.showAlert = false;
          }, 1000);
        }
      });
    },

    refreshUserInfo() {
      const userId = window.localStorage.getItem("userId");
      getUser(userId).then(res => {
        this.userInfo = res || {};
      });
    }
  },

  mounted() {
    this.refreshUserInfo();
  }
};
</script>

<style scoped>
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>
