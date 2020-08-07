import request from '@/utils/request'
//获取路由
/*export const getRouters = () => {
    return request({
        url: '/company/login',
        method: 'get'
    })
}*/
//登录方法
export function comLogin(username, password, code, uuid) {
    const data = {
        username,
        password,
        code,
        uuid
    }
    return request({
        url: '/company/login',
        method: 'post',
        params: data
    })
}
// 获取用户详细信息
export function comGetInfo() {
    return request({
        url: '/getInfo',
        method: 'get'
    })
}
// 退出方法
export function comLogout() {
    return request({
        url: '/logout',
        method: 'post'
    })
}
//获取验证码
export function getCodeImg() {
    return request({
        url: '/captchaImage',
        method: 'get'
    })
}

export function addUser(data) {
  return request({
    url: '/company/login',
    method: 'post',
    data: data
  })
}