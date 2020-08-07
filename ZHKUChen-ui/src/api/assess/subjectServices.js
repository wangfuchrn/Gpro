import request from '@/utils/request'

//----------------判断题-------------------------------------------


// 查询题目类别列表
export function judgelistSubject(query) {
  return request({
    url: '/assess/judgement/list',
    method: 'get',
    params: query
  })
}

// 根据id查询题目类别列表
export function judgegetSubject(id) {
  return request({
    url: '/assess/judgement/' + id,
    method: 'get'
  })
}

// 新增题目
export function judgeaddSubject(data) {
  return request({
    url: '/assess/judgement',
    method: 'post',
    data: data
  })
}

// 修改题目
export function judgeputSubject(data) {
  return request({
    url: '/assess/judgement',
    method: 'put',
    data: data
  })
}

// 删除题目
export function judgedelSubject(id) {
  return request({
    url: '/assess/judgement/' + id,
    method: 'delete'
  })
}

//----------------简答题-------------------------------------------

// 查询题目类别列表
export function shortAnswerlistSubject(query) {
  return request({
    url: '/assess/shortAnswer/list',
    method: 'get',
    params: query
  })
}

// 根据id查询题目类别列表
export function shortAnswergetSubject(id) {
  return request({
    url: '/assess/shortAnswer/' + id,
    method: 'get'
  })
}

// 新增题目
export function shortAnsweraddSubject(data) {
  return request({
    url: '/assess/shortAnswer',
    method: 'post',
    data: data
  })
}

// 修改题目
export function shortAnswerputSubject(data) {
  return request({
    url: '/assess/shortAnswer',
    method: 'put',
    data: data
  })
}

// 删除题目
export function shortAnswerdelSubject(id) {
  return request({
    url: '/assess/shortAnswer/' + id,
    method: 'delete'
  })
}


//----------------选择题-------------------------------------------

// 查询题目类别列表
export function choiceslistSubject(query) {
  return request({
    url: '/assess/choices/list',
    method: 'get',
    params: query
  })
}

// 根据id查询题目类别列表
export function choicesgetSubject(id) {
  return request({
    url: '/assess/choices/' + id,
    method: 'get'
  })
}

// 根据id查询题目类别列表
export function getchoicesOptionsInfo(id) {
  return request({
    url: '/assess/choicesoptions/' + id,
    method: 'get'
  })
}

// 新增题目
export function choicesaddSubject(data) {
  return request({
    url: '/assess/choices',
    method: 'post',
    data: data
  })
}

// 修改题目
export function choicesputSubject(data) {
  return request({
    url: '/assess/choices',
    method: 'put',
    data: data
  })
}

// 删除题目
export function choicesdelSubject(id) {
  return request({
    url: '/assess/choices/' + id,
    method: 'delete'
  })
}

export function choicesSubjectByIds(id) {
  return request({
    url: '/assess/choices/listByids',
    method: 'get',
    // params: query
  })
}

//----------------选择题选项-------------------------------------------

// // 查询题目类别列表
// export function choiceOptionslistSubject(query) {
//   return request({
//     url: '/assess/choices/list',
//     method: 'get',
//     params: query
//   })
// }

// 根据id查询题目类别列表
// export function choiceOptionsgetSubject(subjectChoicesId) {
//   return request({
//     url: '/assess/choiceOptions',
//     method: 'get'
//   })
// }

// 新增题目
export function choiceOptionsaddSubject(data) {
  return request({
    url: '/assess/choiceOptions',
    method: 'post',
    data: data
  })
}

// 修改题目
export function choiceOptionsputSubject(data) {
  return request({
    url: '/assess/choiceOptions',
    method: 'put',
    data: data
  })
}


