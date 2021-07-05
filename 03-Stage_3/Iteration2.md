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
代码中的某些类对应的包似乎有些不正确，请你将这些类归类到对应的包。

Tips
- 你可以参考service中是如何归类这些类的，显然，一个包下只有相关功能的类不是吗？
