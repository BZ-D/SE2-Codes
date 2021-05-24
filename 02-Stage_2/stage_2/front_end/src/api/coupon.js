import axios from "axios";
import { COUPON_MODULE } from "./_prefix";

/**
 * 创建优惠策略
 * @param {*} payload
 * @returns
 */
export const createCoupon = payload => {
  return axios.post(`${COUPON_MODULE}/create`, payload).then(res => {
    return res.data;
  });
};

/**
 * 发放优惠券
 * @param {*} payload
 * @returns
 */
export const deliverCoupon = payload => {
  return axios.post(`${COUPON_MODULE}/deliver`, payload).then(res => {
    return res.data;
  });
};

/**
 * 获取可以发布的优惠券
 * @returns
 */
export const getCouponList = () => {
  return axios.get(`${COUPON_MODULE}/getAll`).then(res => {
    return res.data;
  });
  // return Promise.resolve([
  //   {
  //     "id": 8,
  //     "type": "减价型",
  //     "scope": "单个课程",
  //     "name": "测试满减优惠",
  //     "description": "测试满减优惠",
  //     "metadata": "{\"threshold\":1,\"cutDown\":1,\"courseId\":1}",
  //     "startTime": "2021-05-19 00:00:00",
  //     "endTime": "2021-05-20 00:00:00",
  //     "valid": true,
  //     "sharable": true
  //   },
  //   {
  //     "id": 1,
  //     "type": "减价型",
  //     "scope": "单个课程",
  //     "name": "测试满减优惠",
  //     "description": "测试满减优惠",
  //     "metadata": "{\"threshold\":1,\"cutDown\":1,\"courseId\":1}",
  //     "startTime": "2021-05-19 00:00:00",
  //     "endTime": "2021-05-20 00:00:00",
  //     "valid": true,
  //     "sharable": true
  //   },
  //   {
  //     "id": 4,
  //     "type": "减价型",
  //     "scope": "单个课程",
  //     "name": "测试满减优惠",
  //     "description": "测试满减优惠",
  //     "metadata": "{\"threshold\":1,\"cutDown\":1,\"courseId\":1}",
  //     "startTime": "2021-05-19 00:00:00",
  //     "endTime": "2021-05-20 00:00:00",
  //     "valid": true,
  //     "sharable": true
  //   }
  // ])
};

export const getAvailableCouponsForOrder = orderId => {
  return axios
    .get(`${COUPON_MODULE}/getForOrder`, {
      params: {
        orderId
      }
    })
    .then(res => res.data);
};
