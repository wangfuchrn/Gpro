package com.zhkuchen.project.assess.service;

import java.util.List;

import com.zhkuchen.framework.web.domain.TreeSelect;
import com.zhkuchen.project.assess.domain.SubjectCategory;

/**
 * 题库管理 服务层
 * 
 */
public interface SubjectCategoryService
{
    /**
     * 查询部门管理数据
     * 
     * @param subjectCategory 部门信息
     * @return 部门信息集合
     */
    public List<SubjectCategory> selectSubjectCategoryList(SubjectCategory subjectCategory);

    /**
     * 构建前端所需要树结构
     * 
     * @param subjectCategorys 部门列表ServiceImpl.java
     * @return 树结构列表
     */
    public List<SubjectCategory> buildSubjectCategoryTree(List<SubjectCategory> subjectCategorys);

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param subjectCategorys 部门列表
     * @return 下拉树结构列表
     */
    public List<TreeSelect> buildSubjectCategoryTreeSelect(List<SubjectCategory> subjectCategorys);

    //  /**
    //  * 根据角色ID查询部门树信息
    //  * 
    //  * @param deptId 角色ID
    //  * @return 选中部门列表
    //  */
    // public List<Integer> selectSubjectCategoryListByDeptId(Long deptId);

    /**
     * 根据部门ID查询信息
     * 
     * @param id 部门ID
     * @return 部门信息
     */
    public SubjectCategory selectSubjectCategoryById(Long id);

    /**
     * 是否存在部门子节点
     * 
     * @param id 部门ID
     * @return 结果
     */
    public boolean hasChildById(Long id);

    // /**
    //  * 查询部门是否存在用户
    //  * 
    //  * @param id 部门ID
    //  * @return 结果 true 存在 false 不存在
    //  */
    // public boolean checkSubjectCategoryExistSubject(Long id);

    /**
     * 校验部门名称是否唯一
     * 
     * @param subjectCategory 部门信息
     * @return 结果
     */
    public String checkCategoryNameUnique(SubjectCategory subjectCategory);

    /**
     * 新增保存部门信息
     * 
     * @param subjectCategory 部门信息
     * @return 结果
     */
    public int insertSubjectCategory(SubjectCategory subjectCategory);

    /**
     * 修改保存部门信息
     * 
     * @param subjectCategory 部门信息
     * @return 结果
     */
    public int updateSubjectCategory(SubjectCategory subjectCategory);

    /**
     * 删除部门管理信息
     * 
     * @param id 部门ID
     * @return 结果
     */
    public int deleteSubjectById(Long id);
}