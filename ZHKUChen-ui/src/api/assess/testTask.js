import request from '@/utils/request'

// 查询考试列表
export function listTestTask(query) {
  return request({
    url: '/exam/testTask/list',
    method: 'get',
    params: query
  })
}

// 根据id查询题库类别列表
export function getByTestTaskId(id) {
  return request({
    url: '/exam/testTask/' + id,
    method: 'get'
  })
}

// 根据id查询题库类别列表
export function getExamByUserPhonenumber(query) {
  return request({
    url: '/exam/testTask/user/list',
    method: 'get',
    params: query
  })
}

// 根据id查询题库类别列表
export function getTestTaskId(query) {
  return request({
    url: '/exam/testTask/getTestTaskId',
    method: 'get',
    params: query
  })
}

// 根据id查询题库类别列表
export function getWrittenExamByUserPhonenumber(query) {
  return request({
    url: '/exam/testTask/user/writtenList',
    method: 'get',
    params: query
  })
}

// 修改考试任务
export function updateTestTask(data) {
  return request({
    url: '/exam/testTask',
    method: 'put',
    data: data
  })
}



