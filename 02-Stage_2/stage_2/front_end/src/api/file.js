import axios from "axios";
import { FILE_MODULE } from "./_prefix";

export const FILE_BASE_PATH = "http://courselearning.seec.seecoder.cn/file";

export const uploadFile = payload => {
  return axios.post(`${FILE_MODULE}/upload`, payload).then(res => {
    return res.data;
  });
};
