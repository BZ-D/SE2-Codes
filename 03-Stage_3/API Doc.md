### User

1、  用户注册  POST /user/register

```
    /**
     * @api {POST} /user/register register
     * @apiVersion 1.0.0
     * @apiGroup UserController
     * @apiName register
     *  body参数 (JSON)
     * @apiParam (请求体) {String} uname
     * @apiParam (请求体) {String} phone
     * @apiParam (请求体) {String} password
     * @apiParam (请求体) {String} picture
     * @apiParam (请求体) {String} userRole STUDENT,TEACHER,ADMIN
     * @apiParamExample 请求体示例
     * {
        "uname":"小张同学",
        "phone":"102123456789",
        "password":"123456",
        "picture":"1.png",
        "userRole":"STUDENT"
        }
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data
     * @apiSuccess (响应结果) {Number} data.id
     * @apiSuccess (响应结果) {String} data.uname
     * @apiSuccess (响应结果) {String} data.phone
     * @apiSuccess (响应结果) {String} data.password
     * @apiSuccess (响应结果) {String} data.picture
     * @apiSuccess (响应结果) {Number} data.balance
     * @apiSuccess (响应结果) {String} data.userRole STUDENT,TEACHER,ADMIN
     * @apiSuccess (响应结果) {String} data.createTime yyyy-MM-dd HH:mm:ss
     * @apiSuccessExample 响应结果示例
        {
            "code": 1,
            "msg": "账号注册成功！",
            "data": {
                "id": 3,
                "uname": "小张同学",
                "phone": "102123456789",
                "password": null,
                "picture": "1.png",
                "balance": 0,
                "userRole": "STUDENT",
                "createTime": "2021-03-13T01:45:48.246+00:00"
            }
        }
     */
```

2、  用户登录  POST /user/login

```
    /**
     * @api {POST} /user/login login
     * @apiVersion 1.0.0
     * @apiGroup UserController
     * @apiName login
     *  body参数 (JSON)
     * @apiParam (请求体) {String} phone
     * @apiParam (请求体) {String} password
     * @apiParamExample 请求体示例
     * {"phone":"10212345678", "password":"123456"}
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data
     * @apiSuccess (响应结果) {Number} data.id
     * @apiSuccess (响应结果) {String} data.uname
     * @apiSuccess (响应结果) {String} data.phone
     * @apiSuccess (响应结果) {String} data.password
     * @apiSuccess (响应结果) {String} data.picture
     * @apiSuccess (响应结果) {Number} data.balance
     * @apiSuccess (响应结果) {String} data.userRole STUDENT,TEACHER,ADMIN
     * @apiSuccess (响应结果) {Number} data.createTime yyyy-MM-dd HH:mm:ss
     * @apiSuccessExample 响应结果示例
        {
            "code": 1,
            "msg": "账号登陆成功！",
            "data": {
                "id": 2,
                "uname": "小明",
                "phone": "10212345678",
                "password": null,
                "picture": null,
                "balance": 0,
                "userRole": "STUDENT",
                "createTime": "2020-12-18T02:00:00.000+00:00"
            }
        }
     */
```

3、  用户充值 POST /user/recharge

```
    /**
     * @api {POST} /user/recharge recharge
     * @apiVersion 1.0.0
     * @apiGroup UserController
     * @apiName recharge
     *  body参数 (JSON)
     * @apiParam (请求体) {Number} userId
     * @apiParam (请求体) {Number} value
     * @apiParamExample 请求体示例
     * {"userId":1,"value":10}
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data
     * @apiSuccess (响应结果) {Number} data.id
     * @apiSuccess (响应结果) {String} data.uname
     * @apiSuccess (响应结果) {String} data.phone
     * @apiSuccess (响应结果) {String} data.password
     * @apiSuccess (响应结果) {String} data.picture
     * @apiSuccess (响应结果) {Number} data.balance
     * @apiSuccess (响应结果) {String} data.userRole STUDENT,TEACHER,ADMIN
     * @apiSuccess (响应结果) {String} data.createTime
     * @apiSuccessExample 响应结果示例
        {
            "code": 1,
            "msg": "充值成功",
            "data": {
                "id": 1,
                "uname": "刘钦",
                "phone": "10112345678",
                "password": null,
                "picture": null,
                "balance": 50,
                "userRole": "TEACHER",
                "createTime": "2020-12-18T02:00:00.000+00:00"
            }
        }
     */
```

4、 获取用户 GET /user/{uid}

```
    /**
     * @api {GET} /user/{uid} getUser
     * @apiVersion 1.0.0
     * @apiGroup UserController
     * @apiName getUser
     * @apiParam (请求参数) {Number} uid
     * @apiParamExample 请求参数示例
     * GET /user/2
     * @apiSuccess (响应结果) {Number} id
     * @apiSuccess (响应结果) {String} uname
     * @apiSuccess (响应结果) {String} phone
     * @apiSuccess (响应结果) {String} password
     * @apiSuccess (响应结果) {String} picture
     * @apiSuccess (响应结果) {Number} balance
     * @apiSuccess (响应结果) {String} userRole STUDENT,TEACHER,ADMIN
     * @apiSuccess (响应结果) {String} createTime
     * @apiSuccessExample 响应结果示例
        {
            "id": 2,
            "uname": "小明",
            "phone": "10212345678",
            "password": null,
            "picture": null,
            "balance": 0,
            "userRole": "STUDENT",
            "createTime": "2020-12-18T02:00:00.000+00:00"
        }
     */
```

### Course

1、 获取一门课程 GET /course/{courseId}?uid={uid}

```
    /**
     * @api {GET} /course/{courseId} getCoursesByKey
     * @apiVersion 1.0.0
     * @apiGroup CourseController
     * @apiName getCoursesByKey
     * @apiParam (请求参数) {Number} uid
     * @apiParam (请求参数) {Number} courseId
     * @apiParamExample 请求参数示例
     * GET /course/1?uid=1
     * @apiSuccess (响应结果) {Number} id
     * @apiSuccess (响应结果) {String} name
     * @apiSuccess (响应结果) {String} type
     * @apiSuccess (响应结果) {String} intro
     * @apiSuccess (响应结果) {String} picture
     * @apiSuccess (响应结果) {String} school
     * @apiSuccess (响应结果) {String} createTime
     * @apiSuccess (响应结果) {Number} cost
     * @apiSuccess (响应结果) {Number} teacherId
     * @apiSuccess (响应结果) {String} teacherName
     * @apiSuccess (响应结果) {Number} likes - 课程点赞数
     * @apiSuccess (响应结果) {Boolean} bought
     * @apiSuccess (响应结果) {Boolean} manageable
     * @apiSuccessExample 响应结果示例
        {
            "id": 1,
            "name": "软件工程与计算 I",
            "type": "中级",
            "intro": "通过Scheme、Python和Java语言，让你分别体会函数式编程范式、结构化编程范式和面向对象编程范式",
            "picture": "course1.png",
            "school": "南京大学",
            "createTime": "2020-12-20T02:00:00.000+00:00",
            "cost": 1,
            "teacherId": 1,
            "teacherName": "刘钦",
            "likes": 6,
            "bought": false,
            "manageable": true
        }
     */
```

2、 根据关键字 分页获取课程列表 GET /course/all/{page}?uid={uid}&key={key}

```
    /**
     * @api {GET} /course/all/{page} getCoursesByKey
     * @apiVersion 1.0.0
     * @apiGroup CourseController
     * @apiName getCoursesByKey
     * @apiParam (可选请求参数) {Number} uid
     * @apiParam (可选请求参数) {String} key
     * @apiParam (必选请求参数) {Number} page
     * @apiParamExample 请求参数示例
     * GET /course/all/1?uid=2&key=软件
     * @apiSuccess (响应结果) {Number} pageNum
     * @apiSuccess (响应结果) {Number} pageSize
     * @apiSuccess (响应结果) {Number} size
     * @apiSuccess (响应结果) {Number} startRow
     * @apiSuccess (响应结果) {Number} endRow
     * @apiSuccess (响应结果) {Number} total
     * @apiSuccess (响应结果) {Number} pages
     * @apiSuccess (响应结果) {Array} list
     * @apiSuccess (响应结果) {Number} list.id
     * @apiSuccess (响应结果) {String} list.name
     * @apiSuccess (响应结果) {String} list.type
     * @apiSuccess (响应结果) {String} list.intro
     * @apiSuccess (响应结果) {String} list.picture
     * @apiSuccess (响应结果) {String} list.school
     * @apiSuccess (响应结果) {String} list.createTime
     * @apiSuccess (响应结果) {Number} list.cost
     * @apiSuccess (响应结果) {Number} list.teacherId
     * @apiSuccess (响应结果) {String} list.teacherName
     * @apiSuccess (响应结果) {Boolean} list.bought
     * @apiSuccess (响应结果) {Boolean} list.manageable
     * @apiSuccess (响应结果) {Number} prePage
     * @apiSuccess (响应结果) {Number} nextPage
     * @apiSuccess (响应结果) {Boolean} isFirstPage
     * @apiSuccess (响应结果) {Boolean} isLastPage
     * @apiSuccess (响应结果) {Boolean} hasPreviousPage
     * @apiSuccess (响应结果) {Boolean} hasNextPage
     * @apiSuccess (响应结果) {Number} navigatePages
     * @apiSuccess (响应结果) {Array} navigatepageNums
     * @apiSuccess (响应结果) {Number} navigateFirstPage
     * @apiSuccess (响应结果) {Number} navigateLastPage
     * @apiSuccessExample 响应结果示例
        {
            "pageNum": 1,
            "pageSize": 3,
            "size": 3,
            "startRow": 1,
            "endRow": 3,
            "total": 4,
            "pages": 2,
            "list": [
                {
                    "id": 1,
                    "name": "软件工程与计算 I",
                    "type": "中级",
                    "intro": "通过Scheme、Python和Java语言，让你分别体会函数式编程范式、结构化编程范式和面向对象编程范式",
                    "picture": "course1.png",
                    "school": "南京大学",
                    "createTime": "2020-12-20T02:00:00.000+00:00",
                    "cost": 1,
                    "teacherId": 1,
                    "teacherName": "刘钦",
                    "bought": false,
                    "manageable": false
                },
                {
                    "id": 2,
                    "name": "软件工程与计算 II",
                    "type": "中级",
                    "intro": "基于DevOps培养团队开发中小规模软件系统的能力",
                    "picture": "course2.png",
                    "school": "南京大学",
                    "createTime": "2020-12-20T02:00:00.000+00:00",
                    "cost": 1,
                    "teacherId": 1,
                    "teacherName": "刘钦",
                    "bought": false,
                    "manageable": false
                },
                {
                    "id": 3,
                    "name": "程序猿与攻城狮",
                    "type": "初级",
                    "intro": "我们将由5位老师从不同的角度来说说他们对软件工程的理解。我们并非试图去覆盖所有软件工程的知识点，我们只是希望去让对软件工程有兴趣的你们了解到\"我要学什么\"\"我应如何学\"\"我今后能做什么\"这样一些基本的问题的部分答案。\r\n\r\n   刘钦老师会介绍软件工程的历史和软件工程建模；丁二玉老师会谈谈软件工程师和软件工程团队；冯桂焕老师会通过案例让大家体验人机交互的魅力；陈振宇老师会从概率统计讲到软件测试；刘嘉老师会娓娓道来他对最热门的移动互联网发展的本质的理解。",
                    "picture": "course3.png",
                    "school": "南京大学",
                    "createTime": "2020-12-20T02:00:00.000+00:00",
                    "cost": 1,
                    "teacherId": 1,
                    "teacherName": "刘钦",
                    "bought": false,
                    "manageable": false
                }
            ],
            "prePage": 0,
            "nextPage": 2,
            "isFirstPage": true,
            "isLastPage": false,
            "hasPreviousPage": false,
            "hasNextPage": true,
            "navigatePages": 8,
            "navigatepageNums": [
                1,
                2
            ],
            "navigateFirstPage": 1,
            "navigateLastPage": 2,
            "lastPage": 2,
            "firstPage": 1
        }     
     */
```

3、 根据课程分类 分页获取课程列表 GET course/type/{page}?uid={uid}&type={type}

```
    /**
     * @api {GET} /course/type/{page} getCoursesByType
     * @apiVersion 1.0.0
     * @apiGroup CourseController
     * @apiName getCoursesByType
     * @apiParam (请求参数) {Number} uid
     * @apiParam (请求参数) {String} type
     * @apiParam (请求参数) {Number} page
     * @apiParamExample 请求参数示例
     * GET /course/type/1?uid=2&type=初级
     * @apiSuccess (响应结果) {Number} pageNum
     * @apiSuccess (响应结果) {Number} pageSize
     * @apiSuccess (响应结果) {Number} size
     * @apiSuccess (响应结果) {Number} startRow
     * @apiSuccess (响应结果) {Number} endRow
     * @apiSuccess (响应结果) {Number} total
     * @apiSuccess (响应结果) {Number} pages
     * @apiSuccess (响应结果) {Array} list
     * @apiSuccess (响应结果) {Number} list.id
     * @apiSuccess (响应结果) {String} list.name
     * @apiSuccess (响应结果) {String} list.type
     * @apiSuccess (响应结果) {String} list.intro
     * @apiSuccess (响应结果) {String} list.picture
     * @apiSuccess (响应结果) {String} list.school
     * @apiSuccess (响应结果) {String} list.createTime
     * @apiSuccess (响应结果) {Number} list.cost
     * @apiSuccess (响应结果) {Number} list.teacherId
     * @apiSuccess (响应结果) {String} list.teacherName
     * @apiSuccess (响应结果) {Boolean} list.bought
     * @apiSuccess (响应结果) {Boolean} list.manageable
     * @apiSuccess (响应结果) {Number} prePage
     * @apiSuccess (响应结果) {Number} nextPage
     * @apiSuccess (响应结果) {Boolean} isFirstPage
     * @apiSuccess (响应结果) {Boolean} isLastPage
     * @apiSuccess (响应结果) {Boolean} hasPreviousPage
     * @apiSuccess (响应结果) {Boolean} hasNextPage
     * @apiSuccess (响应结果) {Number} navigatePages
     * @apiSuccess (响应结果) {Array} navigatepageNums
     * @apiSuccess (响应结果) {Number} navigateFirstPage
     * @apiSuccess (响应结果) {Number} navigateLastPage
     * @apiSuccessExample 响应结果示例
        {
            "pageNum": 1,
            "pageSize": 3,
            "size": 2,
            "startRow": 1,
            "endRow": 2,
            "total": 2,
            "pages": 1,
            "list": [
                {
                    "id": 3,
                    "name": "程序猿与攻城狮",
                    "type": "初级",
                    "intro": "我们将由5位老师从不同的角度来说说他们对软件工程的理解。我们并非试图去覆盖所有软件工程的知识点，我们只是希望去让对软件工程有兴趣的你们了解到\"我要学什么\"\"我应如何学\"\"我今后能做什么\"这样一些基本的问题的部分答案。\r\n\r\n   刘钦老师会介绍软件工程的历史和软件工程建模；丁二玉老师会谈谈软件工程师和软件工程团队；冯桂焕老师会通过案例让大家体验人机交互的魅力；陈振宇老师会从概率统计讲到软件测试；刘嘉老师会娓娓道来他对最热门的移动互联网发展的本质的理解。",
                    "picture": "course3.png",
                    "school": "南京大学",
                    "createTime": "2020-12-20T02:00:00.000+00:00",
                    "cost": 1,
                    "teacherId": 1,
                    "teacherName": "刘钦",
                    "bought": false,
                    "manageable": false
                },
                {
                    "id": 4,
                    "name": "基于Java的面向对象编程范式",
                    "type": "初级",
                    "intro": "本课程完成了编程思维、结构化编程范式、面向对象编程方式的介绍，并且着重强调了面向对象编程中封装、协作和可修改性三个重要概念。通过客观题和6道编程题的训练，进一步加深对面向对象编程范式的理解，初步领略面向对象编程范式的风采。",
                    "picture": "course4.png",
                    "school": "南京大学",
                    "createTime": "2020-12-20T02:00:00.000+00:00",
                    "cost": 1,
                    "teacherId": 1,
                    "teacherName": "刘钦",
                    "bought": false,
                    "manageable": false
                }
            ],
            "prePage": 0,
            "nextPage": 0,
            "isFirstPage": true,
            "isLastPage": true,
            "hasPreviousPage": false,
            "hasNextPage": false,
            "navigatePages": 8,
            "navigatepageNums": [
                1
            ],
            "navigateFirstPage": 1,
            "navigateLastPage": 1,
            "lastPage": 1,
            "firstPage": 1
        }     
     */
```

4、 获取用户已购买的课程列表 GET course/sid/{sid}

```
GET course/sid/2
返回结果示例
[
    {
        "id": 1,
        "name": "软件工程与计算 I",
        "type": "中级",
        "intro": "通过Scheme、Python和Java语言，让你分别体会函数式编程范式、结构化编程范式和面向对象编程范式",
        "picture": "course1.png",
        "school": "南京大学",
        "createTime": "2020-12-20T02:00:00.000+00:00",
        "cost": 1,
        "teacherId": 1,
        "teacherName": "刘钦",
        "bought": true,
        "manageable": false
    },
    ...
]
```

5、 获取教师可管理的课程列表 GET course/tid/{tid}

```
GET course/tid/1
返回结果示例
[
    {
        "id": 1,
        "name": "软件工程与计算 I",
        "type": "中级",
        "intro": "通过Scheme、Python和Java语言，让你分别体会函数式编程范式、结构化编程范式和面向对象编程范式",
        "picture": "course1.png",
        "school": "南京大学",
        "createTime": "2020-12-20T02:00:00.000+00:00",
        "cost": 1,
        "teacherId": 1,
        "teacherName": "刘钦",
        "bought": false,
        "manageable": true
    },
    ...
]
```

6、 创建一门课程 POST course/create

```
    /**
     * @api {POST} /course/create createCourse
     * @apiVersion 1.0.0
     * @apiGroup CourseController
     * @apiName createCourse
     * @apiParam (请求体) {String} name
     * @apiParam (请求体) {String} type
     * @apiParam (请求体) {String} intro
     * @apiParam (请求体) {String} picture
     * @apiParam (请求体) {String} school
     * @apiParam (请求体) {Number} cost
     * @apiParam (请求体) {Number} teacherId
     * @apiParam (请求体) {String} teacherName
     * @apiParam (请求体) {Boolean} bought
     * @apiParam (请求体) {Boolean} manageable
     * @apiParamExample 请求体示例
     * {
           "name":"测试课程",
           "type":"初级",
           "intro":"介绍",
           "picture":"1.png",
           "school":"NJU",
           "cost":10,
           "teacherId":1,
           "teacherName":"刘钦",
           "bought":false,
           "manageable":true
       }
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data
     * @apiSuccess (响应结果) {Number} data.id
     * @apiSuccess (响应结果) {String} data.name
     * @apiSuccess (响应结果) {String} data.type
     * @apiSuccess (响应结果) {String} data.intro
     * @apiSuccess (响应结果) {String} data.picture
     * @apiSuccess (响应结果) {String} data.school
     * @apiSuccess (响应结果) {String} data.createTime
     * @apiSuccess (响应结果) {Number} data.cost
     * @apiSuccess (响应结果) {Number} data.teacherId
     * @apiSuccess (响应结果) {String} data.teacherName
     * @apiSuccess (响应结果) {Boolean} data.bought
     * @apiSuccess (响应结果) {Boolean} data.manageable
     * @apiSuccessExample 响应结果示例
         {
             "code": 1,
             "msg": "课程创建成功。",
             "data": {
                 "id": 6,
                 "name": "测试课程",
                 "type": "初级",
                 "intro": "介绍",
                 "picture": "1.png",
                 "school": "NJU",
                 "createTime": "2021-03-13T02:23:30.817+00:00",
                 "cost": 10,
                 "teacherId": 1,
                 "teacherName": "刘钦",
                 "bought": false,
                 "manageable": true
             }
         }
    */
```

### CourseWare

1、 获取某课件 GET course_ware/{courseWareId}?uid={uid}

```
    /**
     * @api {GET} /course_ware/{courseWareId} getCourseWare
     * @apiVersion 1.0.0
     * @apiGroup CourseWareController
     * @apiName getCourseWare
     * @apiParam (可选请求参数) {Number} uid
     * @apiParam (请求参数) {Number} courseWareId
     * @apiParamExample 请求参数示例
     * course_ware/2?uid=2
     * @apiSuccess (响应结果) {Number} id
     * @apiSuccess (响应结果) {Number} courseId
     * @apiSuccess (响应结果) {Number} number
     * @apiSuccess (响应结果) {String} title
     * @apiSuccess (响应结果) {String} fileName
     * @apiSuccess (响应结果) {String} fileType
     * @apiSuccess (响应结果) {String} fileSize
     * @apiSuccess (响应结果) {Boolean} freeFlag
     * @apiSuccess (响应结果) {Boolean} downloadFlag
     * @apiSuccess (响应结果) {Boolean} availableFlag
     * @apiSuccess (响应结果) {String} uploadTime
     * @apiSuccessExample 响应结果示例
       {
           "id": 2,
           "courseId": 1,
           "number": 1,
           "title": "00 - Overview",
           "fileName": null,
           "fileType": null,
           "fileSize": null,
           "freeFlag": false,
           "downloadFlag": true,
           "availableFlag": false,
           "uploadTime": "2020-12-18T02:00:00.000+00:00"
       }
     */
```

2、 获取一门课程的所有课件 GET course_ware/course/{courseId}?uid={uid}

```
GET course_ware/course/1
响应结果示例
[
    {
        "id": 2,
        "courseId": 1,
        "number": 1,
        "title": "00 - Overview",
        "fileName": "33c70532-551f-43a9-b18f-5bdf2d71b77d.png",
        "fileType": "png",
        "fileSize": "1.12 MB",
        "freeFlag": false,
        "downloadFlag": true,
        "availableFlag": false,
        "uploadTime": "2020-12-18T02:00:00.000+00:00"
    },
    ...
]
```

3、 创建课件 POST course_ware/create

```
    /**
     * @api {POST} /course_ware/create createCourseWare
     * @apiVersion 1.0.0
     * @apiGroup CourseWareController
     * @apiName createCourseWare
     * @apiParam (请求体) {Number} courseId
     * @apiParam (请求体) {Number} number 课件在本课程内的序号
     * @apiParam (请求体) {String} title
     * @apiParam (请求体) {String} fileName
     * @apiParam (请求体) {String} fileType
     * @apiParam (请求体) {String} fileSize
     * @apiParam (请求体) {Boolean} freeFlag
     * @apiParam (请求体) {Boolean} downloadFlag
     * @apiParam (请求体) {String} uploadTime
     * @apiParamExample 请求体示例
        {
            "courseId": 1,
            "number": 53,
            "title": "测试课件",
            "fileName": "123.pdf",
            "fileType": "pdf",
            "fileSize": "11 MB",
            "freeFlag": false,
            "downloadFlag": true,
            "availableFlag": true
        }
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data
     * @apiSuccess (响应结果) {Number} data.id 课件Id
     * @apiSuccess (响应结果) {Number} data.courseId
     * @apiSuccess (响应结果) {Number} data.number
     * @apiSuccess (响应结果) {String} data.title
     * @apiSuccess (响应结果) {String} data.fileName
     * @apiSuccess (响应结果) {String} data.fileType
     * @apiSuccess (响应结果) {String} data.fileSize
     * @apiSuccess (响应结果) {Boolean} data.freeFlag
     * @apiSuccess (响应结果) {Boolean} data.downloadFlag
     * @apiSuccess (响应结果) {Boolean} data.availableFlag
     * @apiSuccess (响应结果) {String} data.uploadTime
     * @apiSuccessExample 响应结果示例
        {
            "code": 1,
            "msg": "课件创建成功",
            "data": {
                "id": 198,
                "courseId": 1,
                "number": 53,
                "title": "测试课件",
                "fileName": "123.pdf",
                "fileType": "pdf",
                "fileSize": "11 MB",
                "freeFlag": false,
                "downloadFlag": true,
                "availableFlag": false,
                "uploadTime": "2021-03-13T03:18:03.730+00:00"
            }
        }
     */
```

4、更新课件 POST course_ware/update

```
    /**
     * @api {POST} /course_ware/create createCourseWare
     * @apiVersion 1.0.0
     * @apiGroup CourseWareController
     * @apiName createCourseWare
     * @apiParam (请求体) {Number} id 课件id
     * @apiParam (请求体) {Number} courseId
     * @apiParam (请求体) {Number} number 课件在本课程内的序号
     * @apiParam (请求体) {String} title
     * @apiParam (请求体) {String} fileName
     * @apiParam (请求体) {String} fileType
     * @apiParam (请求体) {String} fileSize
     * @apiParam (请求体) {Boolean} freeFlag
     * @apiParam (请求体) {Boolean} downloadFlag
     * @apiParam (请求体) {String} uploadTime
     * @apiParamExample 请求体示例
        {
            "id": 198,
            "courseId": 1,
            "number": 53,
            "title": "测试课件",
            "fileName": "123.pdf",
            "fileType": "pdf",
            "fileSize": "11 MB",
            "freeFlag": false,
            "downloadFlag": true,
            "availableFlag": true
        }
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data
     * @apiSuccess (响应结果) {Number} data.id
     * @apiSuccess (响应结果) {Number} data.courseId
     * @apiSuccess (响应结果) {Number} data.number
     * @apiSuccess (响应结果) {String} data.title
     * @apiSuccess (响应结果) {String} data.fileName
     * @apiSuccess (响应结果) {String} data.fileType
     * @apiSuccess (响应结果) {String} data.fileSize
     * @apiSuccess (响应结果) {Boolean} data.freeFlag
     * @apiSuccess (响应结果) {Boolean} data.downloadFlag
     * @apiSuccess (响应结果) {Boolean} data.availableFlag
     * @apiSuccess (响应结果) {String} data.uploadTime
     * @apiSuccessExample 响应结果示例
        {
            "code": 1,
            "msg": "课件更新成功",
            "data": {
                "id": 198,
                "courseId": 1,
                "number": 53,
                "title": "测试课件abc",
                "fileName": "123.pdf",
                "fileType": "pdf",
                "fileSize": "11 MB",
                "freeFlag": false,
                "downloadFlag": true,
                "availableFlag": false,
                "uploadTime": "2021-03-13T03:25:59.948+00:00"
            }
        }
     */
```

5、 删除课件 POST course_ware/delete/{courseWareId}

```
    /**
     * @api {POST} /course_ware/delete/{courseWareId} deleteCourseWare
     * @apiVersion 1.0.0
     * @apiGroup CourseWareController
     * @apiName deleteCourseWare
     * @apiParam (请求参数) {Number} courseWareId
     * @apiParamExample 请求参数示例
     * POST course_ware/delete/198
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {String} data
     * @apiSuccessExample 响应结果示例
        {
            "code": 1,
            "msg": "课件删除成功",
            "data": null
        }
     */
```

### File
 
1、 上传文件 POST  file/upload
 
    /**
     * @api {POST} /file/upload upload
     * @apiVersion 1.0.0
     * @apiGroup FileController
     * @apiName upload
     * form-data
     * @apiParam (请求参数) {File} file
        key:"file"  value:"android.jpg"
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data
     * @apiSuccess (响应结果) {String} data.name
     * @apiSuccess (响应结果) {String} data.type
     * @apiSuccess (响应结果) {String} data.size
     * @apiSuccessExample 响应结果示例  
         {
             "code": 1,
             "msg": "文件上传成功",
             "data": {
                 "name": "33c70532-551f-43a9-b18f-5bdf2d71b77d.jpg",
                 "type": "jpg",
                 "size": "0.01 MB"
             }
         }
     */
     
2、 下载文件 GET file/download/{originName}

```
    /**
     * @api {GET} /file/download/{originName:.+} download
     * @apiVersion 1.0.0
     * @apiGroup FileController
     * @apiName download
     * @apiParam (请求参数) {String} originName
     * @apiParam (请求参数) {String} newName
     * @apiParamExample 请求参数示例
     * GET /file/download/c6248c34-370f-40b6-91de-b0c3fa540a38.pdf?newName=测试文件.pdf
     * @apiSuccess (响应结果) {Object} response
     * @apiSuccessExample 响应结果示例
     * null
     */
```     
     
### CourseOrder

1、 生成课程订单（可用于购买课程或赠送课程） POST course_order/create

```
    /**
     * @api {POST} /course_order/create createCourseOrder
     * @apiVersion 1.0.0
     * @apiGroup CourseOrderController
     * @apiName createCourseOrder
     * @apiParam (请求体) {Number} cost
     * @apiParam (请求体) {Number} courseId
     * @apiParam (请求体) {String} courseName
     * @apiParam (请求体) {Number} userId
     * @apiParam (请求体) {Number} status 1 - 已支付成功； 2 - 待支付（若余额足够则直接付款，并将status转为1）； 3 - 已支付等待发货
     * @apiParamExample 请求体示例
         {
             "courseId": 1,
             "courseName": "软件工程与计算 I",
             "cost": 1,
             "userId": 2,
             "status": 1
         }
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data
     * @apiSuccess (响应结果) {Number} data.id 订单Id
     * @apiSuccess (响应结果) {Number} data.cost
     * @apiSuccess (响应结果) {Number} data.courseId
     * @apiSuccess (响应结果) {String} data.courseName
     * @apiSuccess (响应结果) {Number} data.createTime
     * @apiSuccess (响应结果) {Number} data.userId
     * @apiSuccess (响应结果) {Number} data.status 1 - 已支付成功； 2 - 待支付（若余额足够则直接付款，并将status转为1）； 3 - 已支付等待发货；
     * @apiSuccessExample 响应结果示例
         {
             "code": 0,
             "msg": "当前用户已经购买过此课程，或者有上一笔未支付的订单",
             "data": {
                 "id": 1,
                 "cost": 1,
                 "courseId": 1,
                 "courseName": "软件工程与计算 I",
                 "createTime": "2021-03-13T07:50:23.000+00:00",
                 "userId": 2,
                 "status": 1
             }
         }
     */
```

2、 更新订单状态  POST course_order/update

```
    /**
     * @api {POST} /course_order/update updateCourseOrderStatus
     * @apiVersion 1.0.0
     * @apiGroup CourseOrderController
     * @apiName updateCourseOrderStatus
     * @apiParam (请求参数) {Number} orderId
     * @apiParam (请求参数) {Number} orderStatus 1 - 已支付成功（原本未支付的订单会尝试支付）； 3 - 已支付等待发货（原本未支付的订单会尝试支付）； 4 - 取消
     * @apiParamExample 请求参数示例
     * POST course_order/update?orderId=3&orderStatus=1
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data
     * @apiSuccess (响应结果) {Number} data.id
     * @apiSuccess (响应结果) {Number} data.cost
     * @apiSuccess (响应结果) {Number} data.courseId
     * @apiSuccess (响应结果) {String} data.courseName
     * @apiSuccess (响应结果) {Number} data.createTime
     * @apiSuccess (响应结果) {Number} data.userId
     * @apiSuccess (响应结果) {Number} data.status
     * @apiSuccessExample 响应结果示例
         {
             "code": 1,
             "msg": "课程购买成功",
             "data": {
                 "id": 3,
                 "cost": 1,
                 "courseId": 1,
                 "courseName": "软件工程与计算 I",
                 "createTime": "2021-03-13T08:08:02.000+00:00",
                 "userId": 2,
                 "status": 1
             }
         } 
     */
```

3、 获取某用户的所有订单 GET course_order/uid/{uid} 

```
    /**
     * @api {GET} /course_order/uid/{uid} getCourseOrders
     * @apiVersion 1.0.0
     * @apiGroup CourseOrderController
     * @apiName getCourseOrders
     * @apiParam (请求参数) {Number} uid
     * @apiParamExample 请求参数示例
     * GET course_order/uid/2
     * @apiSuccess (响应结果) {Array} response
     * @apiSuccess (响应结果) {Number} response.id
     * @apiSuccess (响应结果) {Number} response.cost
     * @apiSuccess (响应结果) {Number} response.courseId
     * @apiSuccess (响应结果) {String} response.courseName
     * @apiSuccess (响应结果) {Number} response.createTime
     * @apiSuccess (响应结果) {Number} response.userId
     * @apiSuccess (响应结果) {Number} response.status
     * @apiSuccessExample 响应结果示例
        [
            {
                "id": 3,
                "cost": 1,
                "courseId": 1,
                "courseName": "软件工程与计算 I",
                "createTime": "2021-03-13T08:08:02.000+00:00",
                "userId": 2,
                "status": 1
            }
        ]
    */
```

### 课程点赞功能

1、 用户点赞某课程

```
    /**
     * @api {POST} /course/like/{courseId} setCourseLike
     * @apiVersion 1.0.0
     * @apiGroup CourseController
     * @apiName setCourseLike
     * @apiParam (请求参数) {Number} uid
     * @apiParam (请求参数) {Number} courseId
     * @apiParamExample 请求参数示例
     * POST course/like/1?uid=1
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data - 点赞后的课程信息
     * @apiSuccess (响应结果) {Number} data.id
     * @apiSuccess (响应结果) {String} data.name
     * @apiSuccess (响应结果) {String} data.type
     * @apiSuccess (响应结果) {String} data.intro
     * @apiSuccess (响应结果) {String} data.picture
     * @apiSuccess (响应结果) {String} data.school
     * @apiSuccess (响应结果) {Number} data.createTime
     * @apiSuccess (响应结果) {Number} data.cost
     * @apiSuccess (响应结果) {Number} data.teacherId
     * @apiSuccess (响应结果) {String} data.teacherName
     * @apiSuccess (响应结果) {Number} data.likes - 课程点赞数
     * @apiSuccess (响应结果) {Boolean} data.bought - 是否为已购买课程
     * @apiSuccess (响应结果) {Boolean} data.manageable - 是否为可管理课程（教师权限）
     * @apiSuccessExample 响应结果示例
         示例1:
         {
             "code": 1,
             "msg": "操作成功",
             "data": {
                 "id": 1,
                 "name": "软件工程与计算 I",
                 "type": "中级",
                 "intro": "通过Scheme、Python和Java语言，让你分别体会函数式编程范式、结构化编程范式和面向对象编程范式",
                 "picture": "course1.png",
                 "school": "南京大学",
                 "createTime": "2020-12-20 10:00:00",
                 "cost": 1,
                 "teacherId": 100,
                 "teacherName": "刘钦",
                 "likes": 7,
                 "bought": false,
                 "manageable": false
             }
         }
        示例2:
        {
            "code": 0,
            "msg": "您已经为当前课程点赞过，请勿重复操作！",
            "data": null
        }
     */
```

2、 用户取消点赞某课程

```
    /**
     * @api {POST} /course/cancel_like/{courseId} cancelCourseLike
     * @apiVersion 1.0.0
     * @apiGroup CourseController
     * @apiName setCourseLike
     * @apiParam (请求参数) {Number} uid
     * @apiParam (请求参数) {Number} courseId
     * @apiParamExample 请求参数示例
     * POST course/cancel_like/1?uid=1
     * @apiSuccess (响应结果) {Number} code
     * @apiSuccess (响应结果) {String} msg
     * @apiSuccess (响应结果) {Object} data
     * @apiSuccess (响应结果) {Number} data.id
     * @apiSuccess (响应结果) {String} data.name
     * @apiSuccess (响应结果) {String} data.type
     * @apiSuccess (响应结果) {String} data.intro
     * @apiSuccess (响应结果) {String} data.picture
     * @apiSuccess (响应结果) {String} data.school
     * @apiSuccess (响应结果) {Number} data.createTime
     * @apiSuccess (响应结果) {Number} data.cost
     * @apiSuccess (响应结果) {Number} data.teacherId
     * @apiSuccess (响应结果) {String} data.teacherName
     * @apiSuccess (响应结果) {Number} data.likes - 课程点赞数
     * @apiSuccess (响应结果) {Boolean} data.bought - 是否为已购买课程
     * @apiSuccess (响应结果) {Boolean} data.manageable - 是否为可管理课程（教师权限）
     * @apiSuccessExample 响应结果示例
         {
             "code": 1,
             "msg": "操作成功",
             "data": {
                 "id": 1,
                 "name": "软件工程与计算 I",
                 "type": "中级",
                 "intro": "通过Scheme、Python和Java语言，让你分别体会函数式编程范式、结构化编程范式和面向对象编程范式",
                 "picture": "course1.png",
                 "school": "南京大学",
                 "createTime": "2020-12-20 10:00:00",
                 "cost": 1,
                 "teacherId": 100,
                 "teacherName": "刘钦",
                 "likes": 6,
                 "bought": false,
                 "manageable": false
             }
         }
     */
```

### 查看热门课程

用户查看热门课程列表（根据点赞数likes倒序排列）
分页查询返回结果

```
    /**
     * @api {GET} /course/hot/{page} getHotCourses
     * @apiVersion 1.0.0
     * @apiGroup CourseController
     * @apiName getHotCourses
     * @apiParam (请求参数) {Number} page
     * @apiParamExample 请求参数示例
     * course/hot/1
     * @apiSuccess (响应结果) {Number} pageNum
     * @apiSuccess (响应结果) {Number} pageSize
     * @apiSuccess (响应结果) {Number} size
     * @apiSuccess (响应结果) {Number} startRow
     * @apiSuccess (响应结果) {Number} endRow
     * @apiSuccess (响应结果) {Number} total
     * @apiSuccess (响应结果) {Number} pages
     * @apiSuccess (响应结果) {Array} list
     * @apiSuccess (响应结果) {Number} list.id
     * @apiSuccess (响应结果) {String} list.name
     * @apiSuccess (响应结果) {String} list.type
     * @apiSuccess (响应结果) {String} list.intro
     * @apiSuccess (响应结果) {String} list.picture
     * @apiSuccess (响应结果) {String} list.school
     * @apiSuccess (响应结果) {Number} list.createTime
     * @apiSuccess (响应结果) {Number} list.cost
     * @apiSuccess (响应结果) {Number} list.teacherId
     * @apiSuccess (响应结果) {String} list.teacherName
     * @apiSuccess (响应结果) {Number} list.likes
     * @apiSuccess (响应结果) {Boolean} list.bought
     * @apiSuccess (响应结果) {Boolean} list.manageable
     * @apiSuccess (响应结果) {Number} prePage
     * @apiSuccess (响应结果) {Number} nextPage
     * @apiSuccess (响应结果) {Boolean} isFirstPage
     * @apiSuccess (响应结果) {Boolean} isLastPage
     * @apiSuccess (响应结果) {Boolean} hasPreviousPage
     * @apiSuccess (响应结果) {Boolean} hasNextPage
     * @apiSuccess (响应结果) {Number} navigatePages
     * @apiSuccess (响应结果) {Array} navigatepageNums
     * @apiSuccess (响应结果) {Number} navigateFirstPage
     * @apiSuccess (响应结果) {Number} navigateLastPage
     * @apiSuccessExample 响应结果示例
         {
             "pageNum": 1,
             "pageSize": 3,
             "size": 3,
             "startRow": 1,
             "endRow": 3,
             "total": 5,
             "pages": 2,
             "list": [
                 {
                     "id": 4,
                     "name": "基于Java的面向对象编程范式",
                     "type": "初级",
                     "intro": "本课程完成了编程思维、结构化编程范式、面向对象编程方式的介绍，并且着重强调了面向对象编程中封装、协作和可修改性三个重要概念。通过客观题和6道编程题的训练，进一步加深对面向对象编程范式的理解，初步领略面向对象编程范式的风采。",
                     "picture": "course4.png",
                     "school": "南京大学",
                     "createTime": "2020-12-20 10:00:00",
                     "cost": 1,
                     "teacherId": 400,
                     "teacherName": "刘钦",
                     "likes": 6,
                     "bought": false,
                     "manageable": false
                 },
                 {
                     "id": 1,
                     "name": "软件工程与计算 I",
                     "type": "中级",
                     "intro": "通过Scheme、Python和Java语言，让你分别体会函数式编程范式、结构化编程范式和面向对象编程范式",
                     "picture": "course1.png",
                     "school": "南京大学",
                     "createTime": "2020-12-20 10:00:00",
                     "cost": 1,
                     "teacherId": 100,
                     "teacherName": "刘钦",
                     "likes": 5,
                     "bought": false,
                     "manageable": false
                 },
                 {
                     "id": 2,
                     "name": "软件工程与计算 II",
                     "type": "中级",
                     "intro": "基于DevOps培养团队开发中小规模软件系统的能力",
                     "picture": "course2.png",
                     "school": "南京大学",
                     "createTime": "2020-12-20 10:00:00",
                     "cost": 1,
                     "teacherId": 200,
                     "teacherName": "刘钦",
                     "likes": 3,
                     "bought": false,
                     "manageable": false
                 }
             ],
             "prePage": 0,
             "nextPage": 2,
             "isFirstPage": true,
             "isLastPage": false,
             "hasPreviousPage": false,
             "hasNextPage": true,
             "navigatePages": 8,
             "navigatepageNums": [
                 1,
                 2
             ],
             "navigateFirstPage": 1,
             "navigateLastPage": 2,
             "lastPage": 2,
             "firstPage": 1
         }
     */
```