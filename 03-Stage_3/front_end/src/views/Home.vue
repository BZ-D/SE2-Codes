<template>
  <div>
    <v-container class="ma-8 pa-4">
      <template>
        <!-- 分类课程 -->
        <v-card>
          <v-tabs color="deep-purple accent-4" right v-model="currentTab">
            <v-tab>初级课程</v-tab>
            <v-tab>中级课程</v-tab>
            <v-tab>高级课程</v-tab>

            <v-tab-item v-for="c in [primary, medium, advanced]" :key="c.name">
              <v-container fluid>
                <v-row class="ma-4">
                  <course-item
                    cols="12"
                    md="4"
                    v-for="course in c.list"
                    :key="course.id"
                    :courseName="course.name"
                    :courseId="course.id"
                    :description="course.intro"
                    :type="course.type"
                    :cost="course.cost"
                    :bought="false"
                    :manageable="course.manageable"
                    :course-color="colorList[course.id % colorList.length]"
                    :hasLogin="false"
                    :course-likes="course.likes"
                    @buy-course="showDialog"
                  >
                  </course-item>
                </v-row>
                <v-pagination
                  v-model="c.currentPage"
                  :length="c.totalPage"
                  circle
                ></v-pagination>
              </v-container>
            </v-tab-item>
          </v-tabs>
        </v-card>
      </template>

      <v-row class="ma-12">
        <v-divider></v-divider>
      </v-row>

      <!-- 搜索课程 -->
      <v-row class="mt-10">
        <v-col cols="5">
          <v-text-field
            v-model="searchText"
            outlined
            label="搜索课程"
            append-icon="mdi-magnify"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row>
        <course-item
          cols="12"
          md="4"
          v-for="course in searchCourse"
          :key="course.id"
          :courseName="course.name"
          :courseId="course.id"
          :description="course.intro"
          :type="course.type"
          :cost="course.cost"
          :bought="false"
          :manageable="course.manageable"
          :course-color="colorList[course.id % colorList.length]"
          :hasLogin="false"
          :course-likes="course.likes"
          @buy-course="showDialog"
        >
        </course-item>
      </v-row>
      <v-row class="d-flex justify-center">
        <v-pagination
          v-model="searchCurrentPage"
          :length="searchTotalPage"
          circle
        ></v-pagination>
      </v-row>
    </v-container>

    <!-- 购买结果 -->
    <v-dialog v-model="dialog" width="500">
      <v-card>
        <v-card-title>请先登录</v-card-title>

        <v-card-text>
          登录后可以学习更多课程哦
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
import CourseItem from "@/components/CourseItem.vue";
import { getCoursesByType, getCoursesByKey } from "@/api/course";

export default {
  name: "Home",
  components: {
    CourseItem
  },
  data() {
    return {
      dialog: false,
      dialog2: false,
      dialog3: false,

      colorList: ["#26A69A", "#00B0FF", "#5C6BC0", "#FFB300", "#E57373"],
      currentTab: 0, // 0 1 2
      primary: {
        name: "初级",
        totalPage: 1,
        currentPage: 1,
        list: []
      },
      medium: {
        name: "中级",
        totalPage: 1,
        currentPage: 1,
        list: []
      },
      advanced: {
        name: "高级",
        totalPage: 1,
        currentPage: 1,
        list: []
      },
      searchText: "",
      searchCourse: [],
      searchTotalPage: 1,
      searchCurrentPage: 1
    };
  },
  watch: {
    "primary.currentPage": {
      handler: function(val) {
        this.handleGetCoursesByType(val, "primary");
      }
    },

    "medium.currentPage": {
      handler: function(val) {
        this.handleGetCoursesByType(val, "medium");
      }
    },

    "advance.currentPage": {
      handler: function(val) {
        this.handleGetCoursesByType(val, "advanced");
      }
    },

    searchCurrentPage: function() {
      this.handleSearchCourse();
    },

    searchText: function() {
      this.handleSearchCourse();
    }
  },
  methods: {
    showDialog() {
      this.dialog = true;
    },

    handleBuyCourse() {
      Promise.resolve(1).then(res => {
        this.dialog = false;
        if (res) {
          this.dialog2 = true;
        } else {
          this.dialog3 = true;
        }
      });
    },

    handleGetCoursesByType(page, type) {
      const typeMap = { primary: "初级", medium: "中级", advanced: "高级" };
      getCoursesByType({ uid: "", type: typeMap[type], page }).then(res => {
        console.log(res);
        this[type].totalPage = res.pages;
        this[type].list = res.list;
      });
    },

    handleSearchCourse() {
      getCoursesByKey({
        uid: "",
        key: this.searchText,
        page: this.searchCurrentPage
      }).then(res => {
        console.log(res);
        this.searchCourse = res.list;
        this.searchTotalPage = res.pages;
      });
    }
  },

  mounted() {
    ["primary", "medium", "advanced"].forEach(t => {
      this.handleGetCoursesByType(1, t);
    });
    this.handleSearchCourse();
  }
};
</script>
