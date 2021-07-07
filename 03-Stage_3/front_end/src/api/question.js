import axios from "axios";
import { QUESTION_MODULE } from "@/api/_prefix";

/**
 * 添加题目 POST /question
 * @param payload
 * @returns
 */
export const addQuestion = payload => {
  console.log(payload);
  const { courseID, title, answer, analysis } = payload;
  return axios
    .post(`${QUESTION_MODULE}/add_question`, { courseID, title, answer, analysis })
    .then(res => {
      return res.data;
    });
};

/**
 * 展示题库中题目 get /question
 * @param courseID
 * @returns
 */
export const getQuestionsByCourseID = courseID => {
  console.log(courseID);
  return axios
    .get(`${QUESTION_MODULE}/${courseID}`)
    .then(res => {
      return res.data;
    });
};
