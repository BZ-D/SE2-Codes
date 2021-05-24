<template>
  <div>
    <v-container class="pl-16 pr-16">
      <v-row>
        <v-col>
          <form class="pa-12 grey lighten-5 mt-8">
            <div class="d-flex justify-space-between">
              <v-text-field
                class="d-inline-block"
                v-model="courseInfo.name"
                label="课程名称"
                readonly
              ></v-text-field>
              <v-btn class="mx-2 align-self-center" fab dark small color="red">
                <v-icon dark>
                  mdi-heart
                </v-icon>
              </v-btn>
              <span class="align-self-center"
                >点赞： {{ courseInfo.likes }}</span
              >
            </div>

            <v-text-field
              v-model="courseInfo.id"
              label="课程ID"
              readonly
            ></v-text-field>
            <v-select
              :items="types"
              v-model="courseInfo.type"
              label="课程分类"
              readonly
            ></v-select>
            <v-textarea
              v-model="courseInfo.intro"
              label="课程简介"
              readonly
            ></v-textarea>
            <v-text-field
              v-model="courseInfo.school"
              label="所属学校"
              readonly
            ></v-text-field>
            <v-text-field
              v-model="courseInfo.createTime"
              label="创建时间"
              readonly
            ></v-text-field>
            <v-text-field
              v-model="courseInfo.teacherName"
              label="创建教师"
              readonly
            ></v-text-field>
            <v-switch
              v-model="isCharge"
              :label="`是否为付费课程: ${isCharge.toString()}`"
              readonly
            ></v-switch>
            <v-text-field
              v-show="isCharge"
              v-model="courseInfo.cost"
              label="课程价格(元)"
              readonly
            ></v-text-field>
          </form>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import { getCourseById } from "@/api/course";

export default {
  name: "CoursePeek",

  data() {
    return {
      courseInfo: {},
      types: ["初级", "中级", "高级"]
    };
  },

  computed: {
    isCharge: function() {
      return this.courseInfo.cost !== 0;
    }
  },

  methods: {
    loadCourse() {
      const { courseId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getCourseById({ uid, courseId }).then(res => {
        this.courseInfo = res;
      });
    }
  },

  mounted() {
    this.loadCourse();
  }
};
</script>

<style scoped></style>
