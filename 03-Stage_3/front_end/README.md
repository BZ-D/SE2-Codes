# 第二阶段任务

## 实现课程的点赞和取消点赞

目前学生的课程列表界面中，可以展示热门课程，课程的点赞数量，和一个点赞按钮，点赞按钮同时承担了点赞与取消点赞的功能

在 src/views/student/CourseList.vue 中的 setLikeOrDislike() 方法中进行代码填写，具体的操作步骤可以去方法中查看注释

注意前端项目中的接口定义可能不全，因此自己要仿照已有接口来定义缺失的接口；后端也需要添加相应接口来完成任务

# course-learning

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).

# 项目结构说明

## /src/api
封装后端接口。本demo使用了axios库向后端服务发送HTTP请求，这部分的功能就是将调用后端controller提供的接口封装成易于前端页面使用的方法，供页面引入和使用。

## /src/components
公用的vue组件，用于在页面中引入和使用。

## /src/layouts
也是vue组件，与/src/components中的组件在本质上是相同的。不同之处在于，这部分的组件是和前端路由相关的，利用前端[嵌套路由](https://router.vuejs.org/zh/api/#router-view)来实现不同用户的页面使用不同的基础布局。

## /src/router
配置前端路由。
See[Vue Router](https://router.vuejs.org/)

## /src/views
实现每个前端页面，页面会引入需要的组件、api等，实现项目的UI、交互、前后端通信等。

## /src/App.vue
根组件。

## /src/main.js
入口文件，引入Vue框架与相关插件，定义Vue实例。

## /public/index.html
项目主页面，一般只定义一个空的根节点，在main.js里定义的Vue实例会挂在在这个根节点上。

## 其他
本demo使用了CSS框架Vuetify，同学们不必在意，可自行选用CSS框架、组件库或自己实现样式。

参考
[Vue官方文档](https://cn.vuejs.org/)
