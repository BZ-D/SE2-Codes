<template>
  <div>
    <v-container class="pl-16 pr-16">
      <!-- alert-->
      <v-alert
        class="alert"
        outlined
        type="success"
        text
        v-show="showAlert"
        transition="scroll-y-transition"
      >
        {{ alertMsg }}
      </v-alert>

      <!-- 删除 dialog-->
      <template>
        <v-row justify="center">
          <v-dialog v-model="deleteDialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">删除课件</span>
              </v-card-title>
              <v-card-text>
                是否确认删除课件 {{ currentEditFile.name }} ?
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">
                  取消
                </v-btn>
                <v-btn
                  color="red darken-1"
                  text
                  @click="handleDeleteCourseWare"
                >
                  删除
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- 编辑 dialog-->
      <template>
        <v-row justify="center">
          <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">编辑课件</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        label="文件类型"
                        v-model="currentEditFile.fileType"
                        disabled
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        label="文件大小"
                        v-model="currentEditFile.fileSize"
                        disabled
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="文件名"
                        v-model="currentEditFile.fileName"
                        disabled
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="课件标题"
                        v-model="currentEditFile.title"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否免费"
                        v-model="currentEditFile.freeFlag"
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否可下载"
                        v-model="currentEditFile.downloadFlag"
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否有权查看"
                        v-model="currentEditFile.availableFlag"
                      ></v-select>
                    </v-col>
                  </v-row>
                  <v-row class="pa-2">
                    <v-btn color="error" @click="showDeleteFileDialog">
                      删除课件
                    </v-btn>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">
                  Close
                </v-btn>
                <v-btn color="blue darken-1" text @click="handleEditSubmit">
                  Save
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <v-row>
        <!-- 课件列表 -->
        <v-col>
          <v-card max-width="600" class="mx-auto mt-8">
            <v-toolbar color="primary" dark>
              <v-app-bar-nav-icon></v-app-bar-nav-icon>
              <v-toolbar-title>课程课件</v-toolbar-title>
              <v-spacer></v-spacer>

              <v-file-input
                hide-input
                prepend-icon="mdi-plus"
                v-model="instructionFile"
                @change="handleUploadFile"
              ></v-file-input>
            </v-toolbar>

            <v-list subheader two-line>
              <v-list-item v-for="file in files" :key="file.title">
                <v-list-item-avatar>
                  <v-icon class="grey lighten-1" dark>
                    mdi-file
                  </v-icon>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title v-text="file.title"></v-list-item-title>

                  <v-list-item-subtitle
                    v-text="file.fileSize"
                  ></v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-action>
                  <v-btn icon>
                    <v-icon
                      color="grey lighten-1"
                      @click="showEditFileDialog(file.id)"
                      >mdi-more</v-icon
                    >
                  </v-btn>
                </v-list-item-action>
              </v-list-item>
            </v-list>
          </v-card>
        </v-col>
        <!-- 课程信息 -->
        <v-col>
          <form class="pa-12 grey lighten-5 mt-8">
            <v-text-field
              v-model="courseInfo.name"
              label="课程名称"
              readonly
            ></v-text-field>
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
import { uploadFile } from "@/api/file";
import {
  getCourseWareByCourse,
  createCourseWare,
  updateCourseWare,
  deleteCourseWare
} from "@/api/courseWare";
import { getCourseById } from "@/api/course";

export default {
  name: "CourseEdit",

  data() {
    return {
      courseInfo: {},
      instructionFile: null,
      types: ["初级", "中级", "高级"],
      msg: "",
      files: [],
      showAlert: false,
      alertMsg: "",
      dialog: false,
      deleteDialog: false,
      currentEditFile: {
        id: 0,
        courseId: 0,
        number: 0,
        title: "",
        fileName: "",
        fileType: "",
        fileSize: "",
        freeFlag: false,
        downloadFlag: true,
        availableFlag: false,
        uploadTime: ""
      }
    };
  },

  computed: {
    isCharge: function() {
      return this.courseInfo.cost !== 0;
    }
  },

  methods: {
    async handleUploadFile() {
      const formData = new window.FormData();
      formData.append("file", this.instructionFile);
      const res = await uploadFile(formData);
      if (res.code === 1) {
        const { courseId } = this.$route.params;
        const fileInfo = {
          courseId: courseId,
          number: this.files.length,
          title: res.data.name,
          fileName: res.data.name,
          fileType: res.data.type,
          fileSize: res.data.size,
          freeFlag: true,
          downloadFlag: true,
          availableFlag: true
        };
        createCourseWare(fileInfo).then(res => {
          console.log(res);
          this.alertMsg = res.msg;
          this.showAlert = true;
          setTimeout(() => {
            this.showAlert = false;
            this.refreshFile();
          }, 1000);
        });
      }
    },

    refreshFile() {
      const { courseId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getCourseWareByCourse({ courseId, uid }).then(res => {
        this.files = res || [];
      });
    },

    loadCourse() {
      const { courseId } = this.$route.params;
      const uid = window.localStorage.getItem("userId");
      getCourseById({ uid, courseId }).then(res => {
        this.courseInfo = res;
      });
    },

    showEditFileDialog(courseWareId) {
      const currentFile = this.files.find(f => f.id === courseWareId);
      console.log(currentFile);
      this.currentEditFile = currentFile;
      this.dialog = true;
    },

    handleEditSubmit() {
      updateCourseWare(this.currentEditFile).then(res => {
        this.dialog = false;
        console.log(res);
        this.alertMsg = res.msg;
        this.showAlert = true;
        setTimeout(() => {
          this.showAlert = false;
          this.refreshFile();
        }, 1000);
      });
    },

    showDeleteFileDialog() {
      this.deleteDialog = true;
    },

    handleDeleteCourseWare() {
      deleteCourseWare(this.currentEditFile.id).then(res => {
        this.alertMsg = res.msg;
        this.deleteDialog = false;
        this.dialog = false;
        this.showAlert = true;
        this.refreshFile();
        setTimeout(() => {
          this.showAlert = false;
        }, 1000);
      });
    }
  },

  mounted() {
    this.refreshFile();
    this.loadCourse();
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
