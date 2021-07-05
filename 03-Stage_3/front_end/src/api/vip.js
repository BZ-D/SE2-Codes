import axios from "axios";
import { VIP_MODULE } from "./_prefix";

/**
 * 开通会员 POST /vip
 * @param {*} uid
 * @returns
 */
export const openVip = uid => {
  console.log(uid);
  return axios
    .post(`${VIP_MODULE}/`, {
      uid
    })
    .then(res => {
      return res.data;
    });
}
