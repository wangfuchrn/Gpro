import request from '@/utils/request'

// 查询题库类别列表
export function listSubjectCategory(query) {
  return request({
    url: '/assess/subjectCategory/list',
    method: 'get',
    params: query
  })
}


// 根据id查询题库类别列表
export function getId(id) {
  return request({
    url: '/assess/subjectCategory/' + id,
    method: 'get'
  })
}

// 查询题库类别下拉树结构
export function treeselect() {
  return request({
    url: '/assess/subjectCategory/treeselect',
    method: 'get'
  })
}

// // 根据角色ID查询部门树结构
// export function roleSubjectCategoryTreeselect(deptId) {
//   return request({
//     url: '/assess/subjectCategory/roleSubjectCategoryTreeselect/' + deptId,
//     method: 'get'
//   })
// }

// 新增部门
export function addSubjectCategory(data) {
  return request({
    url: '/assess/subjectCategory',
    method: 'post',
    data: data
  })
}

// 修改部门
export function updateSubjectCategory(data) {
  return request({
    url: '/assess/subjectCategory',
    method: 'put',
    data: data
  })
}

// 删除部门
export function delSubjectCategory(id) {
  return request({
    url: '/assess/subjectCategory/' + id,
    method: 'delete'
  })
}

