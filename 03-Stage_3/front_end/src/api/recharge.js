import axios from "axios";
import { RECHARGE_MODULE } from "@/api/_prefix";

/**
 * 用户充值 POST /user/recharge
 * @param {*} payload
 * @returns
 */
export const recharge = payload => {
  console.log(payload);
  const { userId, value } = payload;
  return axios.post(`${RECHARGE_MODULE}/`, { userId, value }).then(res => {
    return res.data;
  });
};
