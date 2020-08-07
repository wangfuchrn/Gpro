import request from '@/utils/request'

// 查询考试记录列表
export function listExamRecord(query) {
  return request({
    url: '/exam/examinationRecord/list',
    method: 'get',
    params: query
  })
}

// 根据id查询考试记录列表
export function findListById(id) {
  return request({
    url: '/exam/examinationRecord/' + id,
    method: 'get'
  })
}

// 根据id查询考试记录列表
export function getSubmitedInfoByUserId(query) {
  return request({
    url: '/exam/examinationRecord/recordList',
    method: 'get',
    params: query
  })
}

// 根据id查询考试记录列表
export function getExamRecordInfo(query) {
  return request({
    url: '/exam/examinationRecord/examRecordInfo',
    method: 'get',
    params: query
  })
}

// 新增考试记录
export function addExamRecord(data) {
  return request({
    url: '/exam/examinationRecord',
    method: 'post',
    data: data
  })
}

// 修改考试记录
export function updateExamRecord(data) {
  return request({
    url: '/exam/examinationRecord',
    method: 'put',
    data: data
  })
}

// 修改考试记录
export function updateMarkStatus(data) {
  return request({
    url: '/exam/examinationRecord/updateMarkStatus',
    method: 'put',
    data: data
  })
}

// 删除考试记录
export function delExamRecord(id) {
  return request({
    url: '/exam/examinationRecord/' + id,
    method: 'delete'
  })
}
