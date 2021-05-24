import axios from "axios";
import { COURSE_WARE } from "./_prefix";

/**
 * 获取某课件 GET course_ware/{courseWareId}?uid={uid}
 * @param {*} payload
 * @returns
 */
export const getCourseWare = payload => {
  const { courseWareId, uid } = payload;
  return axios.get(`${COURSE_WARE}/${courseWareId}?uid=${uid}`).then(res => {
    return res.data;
  });
};

/**
 * 获取一门课程的所有课件 GET course_ware/course/{courseId}?uid={uid}
 * @param {*} payload
 * @returns
 */
export const getCourseWareByCourse = payload => {
  const { courseId, uid } = payload;
  return axios.get(`${COURSE_WARE}/course/${courseId}?uid=${uid}`).then(res => {
    return res.data;
  });
};

/**
 * 创建课件 POST course_ware/create
 * @param {*} payload
 * @returns
 */
export const createCourseWare = payload => {
  const {
    courseId,
    number,
    title,
    fileName,
    fileType,
    fileSize,
    freeFlag,
    downloadFlag,
    availableFlag
  } = payload;
  return axios
    .post(`${COURSE_WARE}/create`, {
      courseId,
      number,
      title,
      fileName,
      fileType,
      fileSize,
      freeFlag,
      downloadFlag,
      availableFlag
    })
    .then(res => {
      return res.data;
    });
};

/**
 * 更新课件 POST course_ware/update
 * @param {*} payload id: 课件id
 * @returns
 */
export const updateCourseWare = payload => {
  const {
    id,
    courseId,
    number,
    title,
    fileName,
    fileType,
    fileSize,
    freeFlag,
    downloadFlag,
    availableFlag
  } = payload;
  return axios
    .post(`${COURSE_WARE}/update`, {
      id,
      courseId,
      number,
      title,
      fileName,
      fileType,
      fileSize,
      freeFlag,
      downloadFlag,
      availableFlag
    })
    .then(res => {
      return res.data;
    });
};

/**
 * 删除课件 POST course_ware/delete/{courseWareId}
 * @param {*} courseWareId
 * @returns
 */
export const deleteCourseWare = courseWareId => {
  return axios.post(`${COURSE_WARE}/delete/${courseWareId}`).then(res => {
    return res.data;
  });
};
