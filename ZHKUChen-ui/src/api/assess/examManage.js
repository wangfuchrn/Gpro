import request from '@/utils/request'

// 查询考试列表
export function listExam(query) {
  return request({
    url: '/assess/examManage/list',
    method: 'get',
    params: query
  })
}

// 根据id查询题库类别列表
export function getId(id) {
  return request({
    url: '/assess/examManage/' + id,
    method: 'get'
  })
}

// 根据id查询题库类别列表
export function getExamInfoId(id) {
  return request({
    url: '/assess/examManage/exam/' + id,
    method: 'get'
  })
}

// 新增测评
export function addExam(data) {
  return request({
    url: '/assess/examManage',
    method: 'post',
    data: data
  })
}

// 从题库中新增
export function addSubjects(data) {
  return request({
    url: '/assess/examManage/addSubjects',
    method: 'post',
    data: data
  })
}

// 修改测评
export function updateExam(data) {
  return request({
    url: '/assess/examManage',
    method: 'put',
    data: data
  })
}

// 删除测评
export function delExam(id) {
  return request({
    url: '/assess/examManage/' + id,
    method: 'delete'
  })
}

// 查询考试题目列表
export function choiceslistExamSubjects(examinationId) {
  return request({
    url: '/assess/examManage/choices/list/'+ examinationId,
    method: 'get',
  })
}
// 查询考试题目列表
export function judgementlistExamSubjects(examinationId) {
  return request({
    url: '/assess/examManage/judgement/list/'+ examinationId,
    method: 'get',
  })
}

// 查询考试题目列表
export function shortAnswerlistExamSubjects(examinationId) {
  return request({
    url: '/assess/examManage/shortAnswer/list/'+ examinationId,
    method: 'get',
  })
}

// 新增考试任务
export function addTestTask(data) {
  return request({
    url: '/assess/examManage/testTask',
    method: 'post',
    data: data
  })
}

// 根据考试id获取全部题目
export function getAllSubjectByExaminationId(id) {
  return request({
    url: '/assess/examManage/subjects/' + id,
    method: 'get'
  })
}

//根据examSubjectId删除试卷下的题目
export function removeExamSubjectById(id) {
  return request({
    url: '/assess/examManage/deleteExamSubject/' + id,
    method: 'delete'
  })
}