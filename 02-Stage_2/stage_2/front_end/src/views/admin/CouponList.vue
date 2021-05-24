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
      优惠发放成功！
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

    <v-container class="ma-8 pa-4">
      <v-row>
        <router-link :to="`/admin/coupon/create`">
          <v-btn class="ma-4 mb-8" fab dark color="indigo">
            <v-icon dark large>
              mdi-plus
            </v-icon>
          </v-btn>
        </router-link>
      </v-row>
      <v-row>
        <coupon-item
          v-for="c in couponList"
          :key="c.id"
          :id="c.id"
          :type="c.type"
          :scope="c.scope"
          :name="c.name"
          :description="c.description"
          :metadata="c.metadata"
          :startTime="c.startTime"
          :endTime="c.endTime"
          :vaild="c.vaild"
          :sharable="c.sharable"
          @deliver-coupon="handleDeliverClick"
        >
        </coupon-item>
      </v-row>
    </v-container>

    <!-- 分发对话框 -->
    <v-dialog v-model="showDeliverDialog" width="500">
      <form class="pa-12 grey lighten-5 mt-8">
        <v-select
          :items="deliverList"
          v-model="deliver"
          label="分发方式"
        ></v-select>
        <v-text-field
          v-show="deliver === 'SINGLE'"
          v-model="userId"
          label="用户ID"
        ></v-text-field>
        <v-text-field
          v-show="deliver === 'COURSE_LIKE'"
          v-model="userId"
          label="课程ID"
        ></v-text-field>
        <v-switch
          v-model="canDuplicate"
          :label="
            `是否可以发放给已经收到过该优惠券的用户: ${canDuplicate.toString()}`
          "
        ></v-switch>
        <v-text-field v-model="nums" label="发放数量"></v-text-field>
      </form>
      <v-btn class="ma-0 info" @click="handleDeliverCoupon">
        确认
      </v-btn>
    </v-dialog>
  </div>
</template>

<script>
import CouponItem from "@/components/CouponItem.vue";
import { getCouponList, deliverCoupon } from "@/api/coupon";

export default {
  name: "CouponList",
  components: {
    CouponItem
  },
  data() {
    return {
      couponList: [],

      couponId: "",
      deliverList_: ["SINGLE", "COURSE_LIKE", "ALL"],
      deliverList: ["指定单一用户", "为某课程点赞的用户", "所有用户"],
      deliver: "SINGLE",
      userId: "",
      courseId: "",
      canDuplicate: false,
      nums: 1,

      showDeliverDialog: false,
      showSuccessDialog: false,
      showFailDialog: false,
      msg: ""
    };
  },
  mounted() {
    getCouponList().then(res => {
      this.couponList = res.data || [];
    });
  },
  methods: {
    handleDeliverClick: function(couponId) {
      console.log("****", couponId);
      this.couponId = couponId;
      this.showDeliverDialog = true;
    },

    handleDeliverCoupon() {
      const extra = {
        userId: this.userId,
        courseId: this.courseId
      };
      const payload = {
        couponId: this.couponId,
        deliver: this.deliverList_[this.deliverList.indexOf(this.deliver)],
        canDuplicate: this.canDuplicate,
        nums: Number.parseInt(this.nums),
        extra: extra
      };
      console.log(payload);
      deliverCoupon(payload).then(res => {
        console.log(res);
        if (res.code === 1) {
          this.showSuccessDialog = true;
          this.showDeliverDialog = false;

          setTimeout(() => {
            this.showSuccessDialog = false;
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
