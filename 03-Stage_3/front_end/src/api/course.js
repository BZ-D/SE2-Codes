import axios from "axios";
import { COURSE_MODULE } from "./_prefix";

/**
 * 获取一门课程 GET /course/{courseId}?uid={uid} 会包含用户是否购买的信息
 * @param {*} payload
 * @returns
 */
export const getCourseById = payload => {
  const { uid, courseId } = payload;
  return axios.get(`${COURSE_MODULE}/${courseId}?uid=${uid}`).then(res => {
    return res.data;
  });
};

/**
 * 根据关键字 分页获取课程列表 GET /course/all/{page}?uid={uid}&key={key}
 * @param {*} payload
 * @returns
 */
export const getCoursesByKey = payload => {
  const { uid, key, page } = payload;
  return axios
    .get(`${COURSE_MODULE}/all/${page}?uid=${uid}&key=${key}`)
    .then(res => {
      return res.data;
    });
};

/**
 * 根据课程分类 分页获取课程列表 GET course/type/{page}?uid={uid}&type={type}
 * @param {*} payload
 * @returns
 */
export const getCoursesByType = payload => {
  const { uid, type, page } = payload;
  return axios
    .get(`${COURSE_MODULE}/type/${page}?uid=${uid}&type=${type}`)
    .then(res => {
      return res.data;
    });
};

/**
 * 获取用户已购买的课程列表 GET course/sid/{sid}
 * @param {*} uid
 * @returns
 */
export const getBoughtCourses = uid => {
  return axios.get(`${COURSE_MODULE}/sid/${uid}`).then(res => {
    return res.data;
  });
};

export const getTeacherCourses = uid => {
  return axios.get(`${COURSE_MODULE}/tid/${uid}`).then(res => {
    return res.data;
  });
};

/**
 * 创建一门课程 POST course/create
 * @param {*} payload
 * @returns
 */
export const createCourse = payload => {
  const {
    name,
    type,
    intro,
    picture,
    school,
    cost,
    teacherId,
    teacherName,
    bought,
    manageable
  } = payload;
  return axios
    .post(`${COURSE_MODULE}/create`, {
      name,
      type,
      intro,
      picture,
      school,
      cost,
      teacherId,
      teacherName,
      bought,
      manageable
    })
    .then(res => {
      return res.data;
    });
};

/**
 * 获取热门课程
 * @returns {Promise<*>}
 */
export const getHotCourses = uid => {
  return axios
    .get(`${COURSE_MODULE}/hot/1`, {
      params: { uid }
    })
    .then(res => {
      return res.data;
    });
};

/**
 * 点赞课程
 * @param uid
 * @param courseId
 * @returns {Promise<*>}
 */
export const setCourseLike = (uid, courseId) => {
  return axios
    .post(`${COURSE_MODULE}/like/${courseId}?uid=${uid}`)
    .then(res => {
      return res.data;
    });
};

export const setCourseDislike = (uid, courseId) => {
  return axios
    .post(`${COURSE_MODULE}/cancel_like/${courseId}?uid=${uid}`)
    .then(res => {
      return res.data;
    });
};
