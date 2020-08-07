import request from '@/utils/request'
//对应后台权限相关文件com.zhkuchen.framework.config.SecurityConfig
// 获取路由
// export const getRouters = () => {
//     return request({
//         url: '/user/login',
//         method: 'get'
//     })
// }
export function comLogin(username, password, code, uuid) {
    const data = {
        username,
        password,
        code,
        uuid
    }
    return request({
        url: '/user/login',
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