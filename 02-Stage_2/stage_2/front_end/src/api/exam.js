import axios from "axios";
import { EXAM_MODULE } from "@/api/_prefix";

/**
 * 发布测试 POST /exam
 * @param payload
 * @returns
 */
export const releaseExam = payload => {
  console.log(payload);
  const { courseID, title, questions } = payload;
  return axios
    .post(`${EXAM_MODULE}/release`, { courseID, title, questions })
    .then(res => {
      return res.data;
    });
};

/**
 * 提交试卷 POST /exam
 * @param payload
 * @returns
 */
export const submitExam = payload => {
  console.log(payload);
  const { uid, examID, answers } = payload;
  return axios
    .post(`${EXAM_MODULE}/submit`, { uid, examID, answers })
    .then(res => {
      return res.data;
    });
};

/**
 * 查看某课程的所有测试 get /exam
 * @param courseID
 * @returns
 */
export const getExamsByCourseId = courseID => {
  console.log(courseID);
  return axios
    .get(`${EXAM_MODULE}/${courseID}`)
    .then(res => {
      return res.data;
    });
};

/**
 * 查看某学生做完的所有测试 get /exam
 * @param uid
 * @returns
 */
export const getExamsByUserID = uid => {
  console.log(uid);
  return axios
    .get(`${EXAM_MODULE}/${uid}`)
    .then(res => {
      return res.data;
    });
};

/**
 * 查看学生在某测试中的答题得分情况 get /exam
 * @param payload
 * @returns
 */
export const getExamResults = payload => {
  console.log(payload);
  const { uid, examID } = payload;
  return axios
    .get(`${EXAM_MODULE}/${uid}?exam_id=${examID}`)
    .then(res => {
      return res.data;
    });
};
