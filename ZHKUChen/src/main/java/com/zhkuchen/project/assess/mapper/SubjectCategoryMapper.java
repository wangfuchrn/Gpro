package com.zhkuchen.project.assess.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhkuchen.project.assess.domain.SubjectCategory;

// import com.github.tangyi.common.persistence.CrudMapper;

/**
 * 题目类型表 数据层
 * 
 */
public interface SubjectCategoryMapper
{
    /**
     * 查询题库管理数据
     * 
     * @param subjectCategory 题库信息
     * @return 题库信息集合
     */
    public List<SubjectCategory> selectSubjectCategoryList(SubjectCategory subjectCategory);

   //  /**
   //   * 根据题库ID查询题库树信息
   //   * 
   //   * @param deptId 角色ID
   //   * @return 选中题库列表
   //   */
    
   // public List<Integer> selectSubjectCategoryListByDeptId(Long deptId);

    /**
     * 根据ID查询题库树信息
     * 
     * @param id 角色ID
     * @return 选中题库列表
     */
    public SubjectCategory selectSubjectCategoryById(Long id);

    /**
     * 根据ID查询所有子题库
     * 
     * @param id 题库ID
     * @return 题库列表
     */
    public List<SubjectCategory> selectChildrenSubjectCategoryById(Long id);

    /**
     * 是否存在子节点
     * 
     * @param id 题库ID
     * @return 结果
     */
    public int hasChildById(Long id);

    // /**
    //  * 查询题库是否存在用户
    //  * 
    //  * @param id 题库ID
    //  * @return 结果
    //  */
    // public int checkSubjectCategoryExistSubject(Long id);

    /**
     * 校验题库名称是否唯一
     * 
     * @param categoryName 题库名称
     * @param parentId 父题库ID
     * @return 结果
     */
    public SubjectCategory checkCategoryNameUnique(@Param("categoryName") String categoryName, @Param("parentId") Long parentId);

    /**
     * 新增题库信息
     * 
     * @param subjectCategory 题库信息
     * @return 结果
     */
    public int insertSubjectCategory(SubjectCategory subjectCategory);

    /**
     * 修改题库信息
     * 
     * @param subjectCategory 题库信息
     * @return 结果
     */
    public int updateSubjectCategory(SubjectCategory subjectCategory);

    /**
     * 修改子元素关系
     * 
     * @param subjectCategorys 子元素
     * @return 结果
     */
    public int updateSubjectCategoryChildren(@Param("subjectCategorys") List<SubjectCategory> subjectCategorys);

    /**
     * 删除题库管理信息
     * 
     * @param id 题库ID
     * @return 结果
     */
    public int deleteSubjectById(Long id);
}
