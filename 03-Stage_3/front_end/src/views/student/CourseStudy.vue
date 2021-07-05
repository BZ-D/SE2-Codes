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

      <!-- 课件 dialog-->
      <template>
        <v-row justify="center">
          <v-dialog v-model="dialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">课件详情</span>
              </v-card-title>
              <v-card-text>
                <v-container>
                  <v-row>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        label="文件类型"
                        v-model="currentFile.fileType"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6" md="4">
                      <v-text-field
                        label="文件大小"
                        v-model="currentFile.fileSize"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="文件名"
                        v-model="currentFile.fileName"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12">
                      <v-text-field
                        label="课件标题"
                        v-model="currentFile.title"
                        readonly
                      ></v-text-field>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否免费"
                        v-model="currentFile.freeFlag"
                        readonly
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否可下载"
                        v-model="currentFile.downloadFlag"
                        readonly
                      ></v-select>
                    </v-col>
                    <v-col cols="12" sm="6">
                      <v-select
                        :items="[true, false]"
                        label="是否有权查看"
                        v-model="currentFile.availableFlag"
                        readonly
                      ></v-select>
                    </v-col>
                  </v-row>
                </v-container>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="dialog = false">
                  Close
                </v-btn>
                <v-btn
                  v-show="currentFile.downloadFlag"
                  color="blue darken-1"
                  text
                  @click="handleDownloadFile"
                >
                  下载课件
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- 测试列表 dialog -->
      <template>
        <v-row justify="center">
          <v-dialog v-model="takeExamDialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">选择一场考试</span>
              </v-card-title>

              <v-card-text v-if="allExams.length===0">
                此课程目前没有测试！请等待老师发布。
              </v-card-text>

              <v-container class="px-0" fluid>
                <v-radio-group v-model="chosenExam">
                  <v-radio v-for="(exam, i) in allExams" :key="i"
                           :label="exam.title + ' ' +'('+exam.questions.length+'道题)  '+ (exam.isValid ? '进行中' : '已过期')"
                           :value="i" :readonly="!exam.isValid">

                  </v-radio>
                </v-radio-group>
              </v-container>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="takeExamDialog = false; chosenExam = -1"
                       v-if="allExams.length!==0">
                  取消
                </v-btn>
                <v-btn color="blue darken-1" text @click="handleChooseExam" v-if="allExams.length!==0">
                  参加考试
                </v-btn>
                <v-btn color="blue darken-1" text @click="takeExamDialog = false" v-if="allExams.length===0">
                  好的
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- 已做测试列表 dialog -->
      <template>
        <v-row justify="center">
          <v-dialog v-model="checkExamDialog" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">选择一场考试</span>
              </v-card-title>

              <v-card-text v-if="finishedExams.length===0">
                你还没有完成该课程的相关测试哦，去参加一次测试吧！
              </v-card-text>

              <v-container class="px-0" fluid>
                <v-radio-group v-model="chosenFinishedExam">
                  <v-radio v-for="(exam, i) in finishedExams" :key="i"
                           :label="exam.title"
                           :value="i">

                  </v-radio>
                </v-radio-group>
              </v-container>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="checkExamDialog = false; chosenFinishedExam = -1"
                       v-if="finishedExams.length!==0">
                  取消
                </v-btn>
                <v-btn color="blue darken-1" text
                       @click="handleCheckFinishedExam"
                       v-if="finishedExams.length!==0">
                  查看考试结果
                </v-btn>
                <v-btn color="blue darken-1" text @click="checkExamDialog = false" v-if="finishedExams.length===0">
                  好的
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- 参加测试 dialog -->
      <template>
        <v-row justify="center">
          <v-dialog v-model="takingExam" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">考试中</span>
              </v-card-title>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="takingExam = false;chosenExam=-1">
                  中止考试
                </v-btn>
                <v-btn color="blue darken-1" text @click="handleSubmitExam">
                  提交试卷
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <!-- 查看考试结果 dialog -->
      <template>
        <v-row justify="center">
          <v-dialog v-model="checkExamResult" persistent max-width="600px">
            <v-card>
              <v-card-title>
                <span class="headline">考试结果</span>
              </v-card-title>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="checkExamResult = false; checkExamDialog=true">
                  返回上层
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-row>
      </template>

      <v-row class="take-exam-btn">
        <v-btn color="green" dark @click.stop="takeExamDialog = true">
          参加课程考试
        </v-btn>
        <v-btn color="blue" dark @click.stop="checkExamDialog = true">
          查看已做试卷
        </v-btn>
      </v-row>

      <v-row>
        <!-- 课件 -->
        <v-col>
          <v-card max-width="600" class="mx-auto mt-8">
            <v-toolbar color="indigo lighten-2" dark>
              <v-app-bar-nav-icon></v-app-bar-nav-icon>
              <v-toolbar-title>下载课件</v-toolbar-title>
              <v-spacer></v-spacer>
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
                      @click="showFileDialog(file.id)"
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
          <form
            class="pa-12 grey lighten-5 mt-8"
            style="position: sticky;top: 0"
          >
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
import { getCourseWareByCourse } from "@/api/courseWare";
import { getCourseById } from "@/api/course";
import { FILE_BASE_PATH } from "@/api/file";

export default {
  name: "CourseEdit",

  data() {
    return {
      courseInfo: {},
      types: ["初级", "中级", "高级"],
      msg: "",
      files: [],
      showAlert: false,
      alertMsg: "",
      dialog: false,

      currentFile: {
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
      takeExamDialog: false,
      allExams: [
        // 存放该课程所有的测试
        {
          id: 1,
          title: "基础夯实测试",
          questions: [
            "1+2=?",
            "3*2=?",
            "4/2=?"
          ],
          isValid: true // 是否过期
        },
        {
          id: 2,
          title: "提高测试",
          questions: [
            "11+21=?",
            "32*2=?",
            "43/2=?"
          ],
          isValid: false
        }
      ],
      chosenExam: -1,
      checkExamDialog: false,
      finishedExams: [
        {
          id: 1,
          title: "基础夯实测试",
          questions: [
            {
              title: "1+2=?",
              answer: "3",
              studentAnswer: "3",
              analysis: "基本点1"
            },
            {
              title: "3*2=?",
              answer: "6",
              studentAnswer: "5",
              analysis: "基本点2"
            },
            {
              title: "4/2=?",
              answer: "2",
              studentAnswer: "2",
              analysis: "基本点3"
            }
          ]
        },
        {
          id: 2,
          title: "提高测试",
          questions: [

            {
              title: "11+21=?",
              answer: "32",
              studentAnswer: "33",
              analysis: "提升点1"
            },
            {
              title: "32*2=?",
              answer: "64",
              studentAnswer: "64",
              analysis: "提升点2"
            },
            {
              title: "43/2=?",
              answer: "21.5",
              studentAnswer: "21",
              analysis: "提升点3"
            }

          ]
        }
      ],
      chosenFinishedExam: -1,
      checkExamResult: false,
      takingExam: false
    };
  },

  computed: {
    isCharge: function() {
      return this.courseInfo.cost !== 0;
    }
  },

  methods: {
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

    showFileDialog(courseWareId) {
      const currentFile = this.files.find(f => f.id === courseWareId);
      console.log(currentFile);
      this.currentFile = currentFile;
      this.dialog = true;
    },

    handleDownloadFile() {
      // downloadFile(this.currentFile.fileName).then((res) => {
      //   console.log(res);
      //   this.alertMsg = "下载成功！";
      //   this.showAlert = true;
      //   this.dialog = false;
      //   setTimeout(() => {
      //     this.showAlert = false;
      //   }, 1000);
      // });
      window.open(`${FILE_BASE_PATH}/${this.currentFile.fileName}`);
    },

    handleChooseExam() {
      // 学生选择参加该课程的某个考试
      if (this.chosenExam >= 0) {
        this.takeExamDialog = false;
        this.takingExam = true;
      } else {
        window.alert("请选择一项考试！");
      }

    },

    handleCheckFinishedExam() {
      // 查看已做试卷
      if (this.chosenFinishedExam >= 0) {
        this.checkExamDialog = false;
        this.checkExamResult = true;
      } else {
        window.alert("请选择一项考试！");
      }
    },

    handleSubmitExam() {
      // 提交试卷
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

.take-exam-btn {
  padding-top: 50px;
}

.take-exam-btn * {
  margin-right: 20px;
}

.px-0 {
  margin-left: 20px;
}
</style>
