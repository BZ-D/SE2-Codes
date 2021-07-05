<template>
  <div>
    <v-container class="ma-8 pa-4">
      <v-row>
        <router-link :to="`/teacher/create`">
          <v-btn class="ma-4 mb-8" fab dark color="indigo">
            <v-icon dark large>
              mdi-plus
            </v-icon>
          </v-btn>
        </router-link>
      </v-row>
      <v-row>
        <course-item-teacher
          v-for="course in courseList"
          :key="course.id"
          :courseName="course.name"
          :courseId="course.id"
          :description="course.intro"
          :type="course.type"
          :cost="course.cost"
          :bought="course.bought"
          :manageable="course.manageable"
          :course-color="colorList[course.id % colorList.length]"
        >
        </course-item-teacher>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import CourseItemTeacher from "@/components/CourseItemTeacher.vue";
import { getTeacherCourses } from "@/api/course";

export default {
  name: "CourseList",
  components: {
    CourseItemTeacher
  },
  data() {
    return {
      colorList: ["#26A69A", "#00B0FF", "#5C6BC0", "#FFB300", "#E57373"],
      courseList: null
    };
  },
  mounted() {
    const uid = window.localStorage.getItem("userId");
    if (uid) {
      getTeacherCourses(uid).then(res => {
        console.log(res);
        this.courseList = res || [];
      });
    }
  }
};
</script>
