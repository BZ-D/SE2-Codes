<template>
  <div>
    <v-container class="ma-8 pa-4">
      <v-card
        v-for="o in orderList"
        :key="o.id"
        :color="colorList[o.id % colorList.length]"
        class="ma-8 pa-4"
      >
        <v-card-title class="headline">
          {{ o.courseName }}
        </v-card-title>

        <v-card-subtitle class="mt-1">
          购买时间: {{ o.createTime }}
        </v-card-subtitle>
        <v-card-actions class="pa-0 pl-2">
          <v-btn text @click="handleStudy(o.courseId)">学习课程</v-btn>
        </v-card-actions>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import { getOrdersByUser } from "@/api/order";

export default {
  name: "HistoryOrder",
  data() {
    return {
      colorList: [
        "#FFAB91",
        "#26A69A",
        "#039BE5",
        "#546E7A",
        "#B39DDB",
        "#EF9A9A"
      ],
      orderList: []
    };
  },

  methods: {
    handleStudy(courseId) {
      this.$router.push(`/student/course/${courseId}`);
    }
  },

  mounted() {
    const uid = window.localStorage.getItem("userId");
    getOrdersByUser(uid).then(res => {
      console.log(res);
      this.orderList = res || [];
    });
  }
};
</script>
