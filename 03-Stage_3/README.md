## 项目说明

这是 courselearning 后端项目，使用了MySQL数据库。

本项目默认使用的 MySQL 用户为 `root` 密码为 `123456`
> 相关配置可参考文件 /src/main/resouces/application.yml `spring.datasource` 字段
> 请根据实际情况进行调整

项目部署前：
> 请使用 /sql/courselearning.sql 内的脚本初始化数据库和表
（可以直接打开MySQL命令行界面，在登录用户后，将 courselearning.sql 文件拷贝到命令行后按下回车）
>
> application.yml 文件中的 `web.file-upload-path` 字段为自定义的用于上传文件的目录路径，请根据实际情况进行调整
（建议部署前先创建目录专门用于存放上传的文件）

本地热部署：可以使用 IntelliJ IDEA 打开本项目，然后点击 Run 'CourseLearningApplication' 。

服务器部署：首先配置好服务器环境（JDK1.8 + MySQL），使用 maven 打包命令生成 jar 包，通过 shell 命令运行。

### 部分数据结构说明

项目中使用了 com.github.pagehelper 分页插件，其中 PageInfo 重要的属性如下所示：
```java
public class PageInfo<T> {
    // 当前页码（无论请求中传入的页码数有多大，在返回结果中，pageNum 都是不超过总页数的）
    private int pageNum;
    // 每页包含的最大记录数
    private int pageSize;
    // 当前页包含的记录数
    private int size;
    // 总记录数
    private long total;
    // 总页数
    private int pages;
    // 结果集
    private List<T> list;
    ...
}
```


### 文件说明

SpringBoot 的配置文件可参考 /src/main/resouces/application.yml 以及 代码部分 /src/main/java/cn.seecoder.courselearning/config/*，请根据实际情况调整。
> 目前所有 controller 暴露的 API 都需要添加 /api 前缀才能正常访问，可参考 cn.seecoder.courselearning/config/WebResourceConfig 中 `configurePathMatch` 方法

/src/main/resouces/static 目录下存储了部分课程的图片文件，建议可以将static目录内的图片文件全部拷贝至服务器上传文件的目录（在application.yml定义的）。

/sql/courselearning.sql 存储了项目初始化需要的sql文件（包含了用于展示的部分数据），初次使用时，可将该文件内的所有sql语句在mysql命令行内执行后初始化数据库。

## 迭代二说明
### 课程点赞
现需要增加点赞功能，用户可以给课程点赞，也可以取消点赞，每个用户对每个课程只能点赞一次。  
你需要从上到下实现点赞和取消点赞的逻辑，从Controller层到底层的数据层。  
1. CourseController中的setCourseLike和cancelCourseLike分别为用户点赞课程和用户取消点赞课程。
2. Service层的接口需要你自己去定义，相关的实现也需要自己去实现，可以参考Controller用户传入的数据是什么来思考该怎样定义这两个接口。
3. 数据层新增了course_likes表，具体的结构可以参考sql文件中的定义，其中为了实现用户点赞和取消点赞的效果，数据层的相关方法需要你去实现，其中，接口的定义写在了CourseLikesMapper，而相关的sql实现在CourseLikesMapper.xml中是错误的，需要你更改并替换为正确的sql语句。

### 优惠券策略
现需要你实现优惠券的按用户点赞某课程来分发优惠券的策略，你可以考虑如下场景：
1. 用户点赞了A课程，且A课程和B课程有一定关系，如（同个老师，同个学校），且B课程用户没买过，那么是不是有一定理由相信用户可能去购买并学习B课程呢？那么我们就可以将优惠券发放给该用户。

你需要实现的方法在CourseLikeDeliverStrategy，它实现了CouponDeliverStrategy接口，用来判断是否能把优惠券发放给该用户，其中extra为发放的依据。
如：需要实现用户只要点赞了某个课程A，那就发放优惠券给课程B，那么实现中显然需要课程A的id和课程B的id，那么这里就由extra传入相关的数据。

Tips:
- 数据的格式可以由你自定义，如key为likeCourseId和deliverCourseId分别表示用户点赞的课程和分发的课程。
- 你甚至可以不需要deliverCourseId，比如考虑另一种场景，只要用户点赞了A课程，那么只要优惠券适用的课程范围中包含和A课程同学校或同老师的其他课程，则发放。而具体需要什么，由你的业务需求去定义。
- 优惠券相关的代码说明：
    - Coupon为优惠券实体类，相关字段的解释见代码注释。
    - CouponType为优惠券类型，包括满减、折扣，你可以在这里定义你的优惠券类型，并给出该类型的优惠券的使用策略CouponStrategy，并与你的优惠券类型绑定。
    - CouponScope为优惠券的使用范围，你也可以在这里定义优惠券的适用范围，并给出怎么判断某个课程能否使用优惠券的判断。
    - 如果你感兴趣优惠券的相关API的实现，参考CouponController，CouponService，同时，在CourseOrderService中也涉及到了Coupon的相关操作，如订单支付后需要消耗掉用户的优惠券、优惠券的使用顺序决定了订单的价格，那么系统应该给出最优价格，那么如何搜索该最优的价格，也在该类下实现了。

### 循环依赖
在实现代码的过程中，某学生发现代码中存在循环依赖的情况，即CourseOrderService依赖了CourseService和CouponService，且
CourseService和CouponService也依赖了CourseOrderService，该学生为了消除依赖，将CourseService和CouponService用到的方法抽离到了QueryOrderService接口，
使得CourseService和CouponService并不直接依赖CourseOrderService，而是依赖QueryOrderService来消除循环依赖，但是相关的实现代码他没有完成，请你帮他完成这些代码。

### 项目结构
代码中的某些类对应的包似乎有些不正确，请你将这些类归类到对应的包。这里需要归类的是位于controller大包下的类。

Tips
- 你可以参考service中是如何归类这些类的，显然，一个包下只有相关功能的类不是吗？


## 附录
### 优惠券需求说明
#### 网站管理员角色
#### 创建发布优惠券
1. 网站管理员可以创建并发布优惠券给用户。  
2. 网站管理员创建优惠券可以选择优惠券类型，包括满减型、折扣型等。对于满减型，需给出满减的门槛和满减的金额，对于折扣型的优惠券，需给出折扣比例。
3. 网站管理员创建优惠券时需指定优惠券作用的课程，包括作用于单个课程，作用于某个学校的课程，作用于某个老师的课程等。
4. 网站管理员创建优惠券可以指定优惠券的发放对象，包括发放到某个用户、根据用户对某个课程的点赞量发放、按用户购买过的课程发放（同校/同老师的其他未购买过的课程）优惠券。
5. 优惠券的其他数据包括优惠券的起止时间，是否能够与其他优惠券一同使用等。
6. 发放优惠券需指定数量。
#### 查看可发放优惠券
1. 网站管理员可以查看现有可以进行发放的优惠券。
2. 可发放优惠券指优惠券的结束时间在当前时间之后的优惠券。
#### 发放优惠券
1. 网站管理员可以选择某个现有的优惠券进行发放，发放用户策略见上述第四点。
2. 发放的优惠券需选择发放的数量以及是否发放给重复用户（即已经拥有该优惠券的用户是否需要再发放）
#### 用户角色
#### 查看订单可用优惠券
1. 用户可以查看某个订单可以使用的优惠券
2. 某个订单某种类型的优惠券最多只能使用一张。
#### 订单使用优惠券
1. 用户可以选择订单的某个可用的优惠券进行使用。
2. 使用时系统计算订单目前已使用的优惠券使用顺序的最优策略，并给出最优价格。
3. 如果订单已经使用过该类型的优惠券，则无法使用。
#### 订单取消使用优惠券
1. 用户可以选择订单已经使用的某个优惠券取消在该订单的使用。
2. 取消使用时系统计算订单目前已使用的优惠券使用顺序的最优策略，并给出最优价格。
#### 优惠券结算
1. 订单结算后优惠券用户的自动扣除。

