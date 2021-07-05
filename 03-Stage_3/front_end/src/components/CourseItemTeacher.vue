<template>
  <v-card :color="courseColor" dark width="380" height="260" class="ma-4 pa-2">
    <v-card-title class="headline">
      {{ courseName }}
    </v-card-title>

    <v-card-text class="text">
      【{{ type }}课程】
      {{ text }}
    </v-card-text>

    <v-card-actions>
      <router-link :to="`/teacher/edit/${courseId}`">
        <v-btn text v-show="manageable">管理课程</v-btn>
      </router-link>
    </v-card-actions>
  </v-card>
</template>

<script lang="ts">
import Vue from "vue";
export default Vue.extend({
  name: "CourseItemTeacher",
  props: {
    courseName: {
      type: String,
      default: "SubjectName"
    },
    courseId: {
      type: Number,
      default: 0
    },
    description: {
      type: String,
      default: "课程简介"
    },
    type: {
      type: String,
      default: "初级"
    },
    cost: {
      type: Number,
      default: 0
    },
    bought: {
      type: Boolean,
      default: false
    },
    manageable: {
      type: Boolean,
      default: false
    },
    courseColor: {
      type: String,
      default: "#BCAAA4"
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
  height: 85px;
  overflow: hidden;
}
</style>
