<template>
  <v-card width="380" class="ma-4 pa-2 blue lighten-2">
    <v-card-title class="headline">
      {{ name }}
      <v-chip small class="ml-4">
        {{ type }}
      </v-chip>
    </v-card-title>

    <v-card-text class="text">
      <v-row class="pa-2 pl-4"> 优惠ID: {{ id }} </v-row>
      <v-row class="pa-2 pl-4"> 优惠描述: {{ text }} </v-row>
      <v-row class="pa-2 pl-4"> 开始时间: {{ startTime }} </v-row>
      <v-row class="pa-2 pl-4"> 结束时间: {{ endTime }} </v-row>
      <v-row class="pa-2 pl-4"> 详细策略: {{ metadata }} </v-row>
      <v-row class="pa-2 pl-4">
        是否可与其他优惠同用: {{ sharable ? "是" : "否" }}
      </v-row>
    </v-card-text>

    <v-card-actions>
      <v-btn text @click="handleDeliver">发放优惠券</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "CouponItem",
  props: {
    id: {
      type: Number,
      default: 0
    },
    type: {
      type: String,
      default: ""
    },
    scope: {
      type: String,
      default: ""
    },
    name: {
      type: String,
      default: ""
    },
    description: {
      type: String,
      default: ""
    },
    metadata: {
      type: String,
      default: ""
    },
    startTime: {
      type: String,
      default: ""
    },
    endTime: {
      type: String,
      default: ""
    },
    valid: {
      type: Boolean,
      default: false
    },
    sharable: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {};
  },
  methods: {
    handleDeliver: function() {
      this.$emit("deliver-coupon", this.id);
    }
  },
  computed: {
    text: function() {
      return this.description.length < 60
        ? this.description
        : this.description.substring(0, 60) + "...";
    }
  }
});
</script>

<style scoped>
.text {
  min-height: 85px;
  overflow: hidden;
}
</style>
