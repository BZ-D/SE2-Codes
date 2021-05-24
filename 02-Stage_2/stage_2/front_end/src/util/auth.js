export const judgeLogin = () => {
  return !!window.localStorage.getItem("userId");
};

export const judgeStudent = () => {
  return judgeLogin() && window.localStorage.getItem("userRole") === "STUDENT";
};

export const judgeTeacher = () => {
  return judgeLogin() && window.localStorage.getItem("userRole") === "TEACHER";
};
