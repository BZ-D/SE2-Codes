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
      <v-dialog v-model="recharge_dialog" max-width="400">
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

            <v-btn color="green darken-1" text @click="recharge_dialog = false">
              取消
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

      <v-dialog v-model="vip_dialog" max-width="400">
        <v-card>
          <v-card-title class="headline">
            会员开通
          </v-card-title>

          <v-card-text>
            是否花费15元开通一个月的网站会员？<br>
            开通成功后，您可以在会员有效期内无限制学习所有课程。
          </v-card-text>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn color="green darken-1" text @click="handleVIP">
              确定
            </v-btn>

            <v-btn color="green darken-1" text @click="vip_dialog = false">
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
        <v-btn class="bottom-btn" color="primary" dark @click.stop="recharge_dialog = true">
          充值
        </v-btn>
        <v-btn class="bottom-btn" color="green" dark @click.stop="vip_dialog = true">
          开通会员
        </v-btn>
      </form>
    </v-container>
  </div>
</template>

<script>
import { getUser } from "@/api/user";
import { recharge } from "@/api/recharge";

export default {
  name: "UserCenter",

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
      recharge_dialog: false,
      vip_dialog: false,
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
          this.recharge_dialog = false;
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
    },

    // 开通会员
    handleVIP() {

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

.pl-16 .pa-12 .bottom-btn {
  margin-right: 20px;
}
</style>
