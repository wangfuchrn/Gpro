package com.zhkuchen.project.assess.mapper;

import com.zhkuchen.project.assess.domain.ChoicesSub;

import java.util.List;

/**
 * 判断题
 * 
 */
public interface ChoicesSubMapper
{
    /**
     * 查询判断题管理数据
     * 
     * @param id 判断题信息
     * @return 判断题信息集合
     */
    public List<ChoicesSub> getById(Long[] ids);


    /**
     * 查询判断题管理数据
     * 
     * @param choicesSub 判断题信息
     * @return 判断题信息集合
     */
    public List<ChoicesSub> get(ChoicesSub choicesSub);

    /**
     * 查询判断题管理数据
     * 
     * @param choicesSub 判断题信息
     * @return 判断题信息集合
     */
    public List<ChoicesSub> findList(ChoicesSub choicesSub);

    /**
     * 根据ID查询题库树信息
     * 
     * @param id 角色ID
     * @return 选中判断题列表
     */
    public ChoicesSub findListById(Long id);

    // /**
    //  * 是否存在子节点
    //  * 
    //  * @param id 判断题ID
    //  * @return 结果
    //  */
    // public int hasChildById(Long id);

    /**
     * 根据考试id查询选择题题目id列表
     *
     * @param examinationId examinationId
     * @return List
     */
    public List<ChoicesSub> findSubjectsByExaminationId(Long examinationId);
    
    /**
     * 新增判断题信息
     * 
     * @param choicesSub 判断题信息
     * @return 结果
     */
    public int insert(ChoicesSub choicesSub);

    /**
     * 修改判断题信息
     * 
     * @param choicesSub 判断题信息
     * @return 结果
     */
    public int update(ChoicesSub choicesSub);

    /**
     * 删除判断题信息
     * 
     * @param id 判断题信息
     * @return 结果
     */
    public int delete(Long id);

    // /**
    //  * 删除判断题管理信息
    //  * 
    //  * @param ids 判断题ID
    //  * @return 结果
    //  */
    // public int deleteAll(Long[] ids);

    // /**
    //  * 物理删除
    //  *
    //  * @param id SubjectChoices
    //  * @return int
    //  */
    // public int physicalDelete(Long id);

    // /**
    //  * 物理批量删除
    //  *
    //  * @param ids ids
    //  * @return int
    //  */
    // public int physicalDeleteAll(Long[] ids);
}
// /**
//  * ChoicesSubMapper
//  * BaseMapper为MyBatis Plus内置方法，里面提供了多种数据库操作方法
//  * BaseMapper口，为 Mybatis-Plus 启动时自动解析实体表关系映射转换为 Mybatis 内部对象注入容器
//  */
// @Repository
// @Mapper
// public interface ChoicesSubMapper extends BaseMapper<ChoicesSub>{

    
// }