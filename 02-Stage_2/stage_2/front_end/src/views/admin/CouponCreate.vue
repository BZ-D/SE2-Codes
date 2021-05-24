<template>
  <div>
    <!-- alert -->
    <v-alert
      class="alert"
      outlined
      type="success"
      text
      v-show="showSuccessDialog"
      transition="scroll-y-transition"
    >
      优惠创建成功！
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
    <v-container class="pl-16 pr-16">
      <form class="pa-12 grey lighten-5 mt-8">
        <v-text-field v-model="couponInfo.name" label="优惠名称"></v-text-field>
        <v-select
          :items="typeList"
          v-model="couponInfo.type"
          label="优惠分类"
        ></v-select>
        <v-text-field
          v-show="couponInfo.type === 'DISCOUNT'"
          v-model="meta.discount"
          label="折扣数额（0 ～ 1）"
        ></v-text-field>
        <v-text-field
          v-show="couponInfo.type === 'CUT_DOWN'"
          v-model="meta.threshold"
          label="满减门槛"
        ></v-text-field>
        <v-text-field
          v-show="couponInfo.type === 'CUT_DOWN'"
          v-model="meta.cutDown"
          label="满减金额"
        ></v-text-field>
        <v-select
          :items="scopeList"
          v-model="couponInfo.scope"
          label="使用范围"
        ></v-select>
        <v-text-field
          v-show="couponInfo.scope === 'SINGLE'"
          v-model="meta.teacherId"
          label="课程ID"
        ></v-text-field>
        <v-text-field
          v-show="couponInfo.scope === 'TEACHER'"
          v-model="meta.teacherId"
          label="教师ID"
        ></v-text-field>
        <v-text-field
          v-show="couponInfo.scope === 'SCHOOL'"
          v-model="meta.teacherId"
          label="教师ID"
        ></v-text-field>
        <v-text-field
          v-model="couponInfo.description"
          label="优惠说明"
        ></v-text-field>
        <v-text-field
          v-model="couponInfo.startTime"
          label="开始时间"
          messages="YYYY-MM-DD HH:MM:SS"
        ></v-text-field>
        <v-text-field
          v-model="couponInfo.endTime"
          label="结束时间"
          messages="YYYY-MM-DD HH:MM:SS"
        ></v-text-field>
        <v-switch
          v-model="couponInfo.sharable"
          :label="`是否可与其他优惠同时使用: ${couponInfo.sharable.toString()}`"
        ></v-switch>
        <v-btn class="ml-0 mt-8 info" @click="submit">
          确认
        </v-btn>
      </form>
    </v-container>
  </div>
</template>

<script>
import { createCoupon } from "@/api/coupon";

export default {
  name: "CouponCreate",

  data() {
    return {
      typeList_: ["CUT_DOWN", "DISCOUNT"],
      typeList: ["满减", "折扣"],
      scopeList_: ["SINGLE", "TEACHER", "SCHOOL", "COMMON"],
      scopeList: [
        "单一课程",
        "指定教师的课程",
        "指定学习的课程",
        "全站课程通用"
      ],
      couponInfo: {
        type: "满减", // "DISCOUNT"
        scope: "单一课程", // "TEACHER" "COMMON"
        name: "",
        description: "",
        metadata: "",
        startTime: "",
        endTime: "",
        sharable: false
      },
      meta: {
        threshold: 0,
        cutDown: 0,
        discount: 0,
        courseId: "",
        teacherId: "",
        schoolId: ""
      },

      showSuccessDialog: false,
      showFailDialog: false,
      msg: ""
    };
  },

  methods: {
    submit() {
      const payload = {
        ...this.couponInfo,
        type: this.typeList_[this.typeList.indexOf(this.couponInfo.type)],
        scope: this.scopeList_[this.scopeList.indexOf(this.couponInfo.scope)],
        metadata: JSON.stringify(this.meta)
      };
      console.log(payload);
      createCoupon(payload).then(res => {
        console.log(res);
        if (res.code === 1) {
          this.showSuccessDialog = true;
          setTimeout(() => {
            this.showSuccessDialog = false;
            this.$router.go(-1);
          }, 1000);
        } else {
          this.showFailDialog = true;
          this.msg = res.msg;
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
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>
