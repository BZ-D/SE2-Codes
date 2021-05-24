<template>
  <div>
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
        >
        </coupon-item>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import CouponItem from "@/components/CouponItem.vue";
import { getCouponList } from "@/api/coupon";

export default {
  name: "CouponDeliver",
  components: {
    CouponItem
  },
  data() {
    return {
      colorList: ["#26A69A", "#00B0FF", "#5C6BC0", "#FFB300", "#E57373"],
      couponList: null
    };
  },
  mounted() {
    getCouponList().then(res => {
      this.couponList = res || [];
    });
  }
};
</script>
