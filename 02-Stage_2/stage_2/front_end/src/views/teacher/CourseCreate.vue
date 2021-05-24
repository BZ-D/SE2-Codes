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
      课程创建成功！
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
        <v-text-field v-model="courseInfo.name" label="课程名称"></v-text-field>
        <v-select
          :items="types"
          v-model="courseInfo.type"
          label="课程分类"
        ></v-select>
        <v-textarea v-model="courseInfo.intro" label="课程简介"></v-textarea>
        <v-text-field
          v-model="courseInfo.school"
          label="所属学校"
        ></v-text-field>
        <v-switch
          v-model="isCharge"
          :label="`是否为付费课程: ${isCharge.toString()}`"
        ></v-switch>
        <v-text-field
          v-show="isCharge"
          v-model="courseInfo.cost"
          label="课程价格(元)"
        ></v-text-field>
        <v-btn class="ml-0 mt-8 info" @click="submit">
          确认
        </v-btn>
      </form>
    </v-container>

    <!-- 提示对话框 -->
    <v-dialog v-model="dialog" width="500">
      <v-card>
        <v-card-title> </v-card-title>

        <v-card-text>
          专题创建成功。
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="dialog = false">
            确认
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { createCourse } from "@/api/course";

export default {
  name: "CourseCreate",

  data() {
    return {
      courseInfo: {
        name: "",
        type: "初级",
        intro: "",
        picture: "course1.png",
        school: "",
        cost: 0
      },
      isCharge: false,
      types: ["初级", "中级", "高级"],
      dialog: false,
      showSuccessDialog: false,
      showFailDialog: false,
      msg: ""
    };
  },

  methods: {
    submit() {
      const uid = window.localStorage.getItem("userId");
      const uname = window.localStorage.getItem("username");
      const payload = {
        ...this.courseInfo,
        picture: null,
        teacherId: uid,
        teacherName: uname,
        bought: false,
        manageable: true
      };
      console.log(payload);
      createCourse(payload).then(res => {
        console.log(res);
        if (res.code === 1) {
          this.showSuccessDialog = true;
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

<style scoped>
.alert {
  position: fixed;
  left: 50%;
  top: 100px;
  z-index: 999;
}
</style>
