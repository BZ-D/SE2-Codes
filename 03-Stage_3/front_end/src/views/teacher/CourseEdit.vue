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

      <!-- failed alert-->
      <v-alert
        class="alert"
        outlined
        type="error"
        text
        v-show="showFailAlert"
        transition="scroll-y-transition"
      >
        {{ failAlertMsg }}
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

      <!-- 添加题目 dialog -->
      <template>
        <v-row justify="center">
          <v-dialog v-model="addQuestionDialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">添加题目</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="题目名称"
                        v-model="questionInfo.title"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="答案内容"
                        v-model="questionInfo.answer"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="题目解析"
                        v-model="questionInfo.analysis"
                      ></v-text-field>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="addQuestionDialog = false">
                  取消
                </v-btn>
                <v-btn color="blue darken-1" text @click="handleAddQuestion">
                  添加
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- 创建课程测试 dialog -->
      <template>
        <v-row justify="center">
          <v-dialog v-model="createTestDialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">创建测试</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12">
                      <v-text-field
                        label="测试名称"
                        v-model="examInfo.title"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="开始时间"
                        v-model="examInfo.startTime"
                        messages="YYYY-MM-DD HH:MM:SS"
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="结束时间"
                        v-model="examInfo.endTime"
                        messages="YYYY-MM-DD HH:MM:SS"
                      ></v-text-field>
                    </v-col>

                    <v-chip
                      class="ma-2"
                      color="deep-purple lighten-3"
                      label
                      text-color="white"
                    >
                      已选题目👇
                    </v-chip>
                    <br>

                    <v-col cols="12" v-show="examInfo.examQuestions.length === 0">
                      当前尚未选择题目！
                    </v-col>

                    <v-col cols="12" v-for="(question, i) in examInfo.examQuestions" :key="i">
                      <v-text-field
                        :label='"第"+(i+1)+"题"'
                        v-model="question.title"
                        readonly
                      ></v-text-field>
                      <v-btn color="red" text @click="deleteOneQuestion(i)">
                        删除该题
                      </v-btn>
                    </v-col>

                  </v-row>
                </v-container>
              </v-card-text>

              <v-btn class="choose-question-btn" color="green" dark
                     @click.stop="chooseQuestionDialog = true; createTestDialog = false">
                从题库中选择题目
              </v-btn>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="confirmCancelCreatingTest">
                  取消
                </v-btn>
                <v-btn color="blue darken-1" text @click="handleCreateTest">
                  发布
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- 从题库中选题 dialog -->
      <template>
        <v-row justify="center">
          <v-dialog v-model="chooseQuestionDialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">选择题目</span>
              </v-card-title>

              <v-container fluid>
                <v-checkbox
                  v-for="(ques, i) in questionsOfThisCourse"
                  v-model="questionsChosen[i]"
                  :label="ques.title"
                  value="selected"
                  :key="i"
                  :disabled="questionsOfThisCourse[i].selected"
                ></v-checkbox>
              </v-container>


              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="chooseQuestionDialog = false; createTestDialog = true">
                  取消
                </v-btn>
                <v-btn color="blue darken-1" text @click="handleChooseQuestion">
                  选择
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- 创建课程测试、向题库中添加一道题，button -->
      <v-row class="add-question">
        <v-btn color="green" dark @click.stop="createTestDialog = true">
          创建课程测试
        </v-btn>
        <v-btn color="purple" dark @click.stop="addQuestionDialog = true">
          向题库中添加一道题
        </v-btn>
      </v-row>

      <!-- 课件列表 -->
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
                    >mdi-more
                    </v-icon
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
import { addQuestion } from "@/api/question";
import { releaseExam } from "@/api/exam";

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
      },
      addQuestionDialog: false,
      questionInfo: {
        // 只考虑填空题
        title: "",   // 题干
        answer: "",  // 题目答案
        analysis: "" // 题目解析
      },  // 用于向题库中添加一道题
      createTestDialog: false,
      questionsOfThisCourse: [
        {
          questionID: 1,
          title: "什么是地球？",
          selected: false
        },
        {
          questionID: 2,
          title: "什么是月球？",
          selected: false
        },
        {
          questionID: 3,
          title: "什么是火星？",
          selected: false
        },
        {
          questionID: 4,
          title: "什么是木星？",
          selected: false
        },
        {
          questionID: 5,
          title: "什么是金星？",
          selected: false
        }
      ], // 该课程的题库，用于在选择题目页面展示
      chooseQuestionDialog: false,
      questionsChosen: [],

      examInfo: {
        title: "",
        examQuestions: [
          // 被选择用作测试的题目
          // {
          //   questionID: 1,
          //   title: "什么是地球？"
          // },
          // {
          //   questionID: 2,
          //   title: "什么是月球？"
          // },
          // {
          //   questionID: 3,
          //   title: "什么是火星？"
          // },
          // {
          //   questionID: 4,
          //   title: "什么是木星？"
          // },
          // {
          //   questionID: 5,
          //   title: "什么是金星？"
          // }
        ],
        startTime: "",
        endTime: ""
      },   // 发布的测试信息
      showFailAlert: false,
      failAlertMsg: ""
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
    },

    handleAddQuestion() {
      // 添加一道题
      if (this.questionInfo.title === "") {
        window.alert("题目不能为空，请填写后再提交！");
        return;
      } else if (this.questionInfo.answer === "") {
        window.alert("答案不能为空，请填写后再提交！");
        return;
      } else if (this.questionInfo.analysis === "") {
        window.alert("解析不能为空，请填写后再提交！");
        return;
      }

      const payload = {
        courseID: this.$route.params.courseId,
        title: this.questionInfo.title,
        answer: this.questionInfo.answer,
        analysis: this.questionInfo.analysis
      };


      addQuestion(payload).then(res => {
        console.log(res);
        if (res.code === 1) {
          this.alertMsg = "题目添加成功！";
          this.addQuestionDialog = false;
          this.showAlert = true;
          setTimeout(() => {
            this.showAlert = false;
          }, 1000);
          this.questionInfo.analysis = "";
          this.questionInfo.answer = "";
          this.questionInfo.title = "";

        } else {
          this.failAlertMsg = "题目添加失败！请重新尝试";
          this.addQuestionDialog = false;
          this.showFailAlert = true;
          setTimeout(() => {
            this.showFailAlert = false;
          }, 1000);
        }

      });
    },

    handleCreateTest() {
      // 创建课程测试
      if (this.examInfo.title === "") {
        window.alert("请输入测试名称！");
        return;
      } else if (this.examInfo.startTime === "") {
        window.alert("请输入开始时间！");
        return;
      } else if (this.examInfo.endTime === "") {
        window.alert("请输入结束时间！");
        return;
      } else if (this.examInfo.examQuestions.length === 0) {
        window.alert("请至少选择一道题目！");
        return;
      }

      const payload = {
        courseID: this.$route.params.courseId,
        title: this.examInfo.title,
        startTime: this.examInfo.startTime,
        endTime: this.examInfo.endTime,
        questions: this.examInfo.examQuestions
      };

      releaseExam(payload).then(res => {
        console.log(res);
        if (res.code === 1) {
          this.examInfo.title = ''
          this.examInfo.examQuestions.length = 0
          this.examInfo.startTime = ''
          this.examInfo.endTime = ''

          this.alertMsg = "发布成功！";
          this.createTestDialog = false;
          this.showAlert = true;
          setTimeout(() => {
            this.showAlert = false;
          }, 1000);

        } else {
          this.failAlertMsg = "发布失败！请重新尝试";
          this.createTestDialog = false;
          this.showFailAlert = true;
          setTimeout(() => {
            this.showFailAlert = false;
          }, 1000);
        }
      });

    },

    confirmCancelCreatingTest() {
      const b = window.confirm("确认取消创建测试吗？");
      if (b) {
        this.createTestDialog = false;
      }
    },

    handleChooseQuestion() {
      // 从题库中选题
      let chooseOrNot = false;
      for (let i = 0; i < this.questionsChosen.length; ++i) {
        if (this.questionsChosen[i] === "selected") {
          this.questionsOfThisCourse[i].indInOrigionArr = i
          this.examInfo.examQuestions.push(this.questionsOfThisCourse[i]);
          this.questionsChosen[i] = null;
          this.questionsOfThisCourse[i].selected = true;
          chooseOrNot = true;
        }
      }

      if (chooseOrNot) {
        this.alertMsg = "题目选择成功！";
        this.chooseQuestionDialog = false;
        this.createTestDialog = true;
        this.showAlert = true;
        setTimeout(() => {
          this.showAlert = false;
        }, 1000);
      } else {
        window.alert("请选择题目！");
      }
    },

    deleteOneQuestion(ind) {
      this.questionsOfThisCourse[this.examInfo.examQuestions[ind].indInOrigionArr].selected = false
      this.examInfo.examQuestions.splice(ind, 1)
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

.add-question {
  padding-top: 50px;
}

.add-question * {
  margin-right: 20px;
}

.choose-question-btn {
  margin-left: 20px;
  margin-top: 7px;
}

.px-0 {
  margin-left: 20px;
}

</style>
