import request from '@/utils/request'

// 查询考试答案列表
export function listExamAnswer(query) {
  return request({
    url: '/exam/answer/list',
    method: 'get',
    params: query
  })
}

// 根据id查询考试答案列表
export function getBySubjectId(query) {
  return request({
    url: '/exam/answer/bySubjectAndExamRecordId',
    method: 'get',
    params: query
  })
}

// 查询考试记录列表
export function getListByExamRecordId(query) {
  return request({
    url: '/exam/answer/listByExamRecordId',
    method: 'get',
    params: query
  })
}

// 根据id查询考试答案信息
export function getId(id) {
  return request({
    url: '/exam/answer/get/' + id,
    method: 'get'
  })
}


// 新增考试答案
export function addExamAnswer(data) {
  return request({
    url: '/exam/answer',
    method: 'post',
    data: data
  })
}

// 修改考试答案
export function updateExamAnswer(data) {
  return request({
    url: '/exam/answer',
    method: 'put',
    data: data
  })
}

// 删除考试答案
export function delExamAnswer(id) {
  return request({
    url: '/exam/answer/' + id,
    method: 'delete'
  })
}