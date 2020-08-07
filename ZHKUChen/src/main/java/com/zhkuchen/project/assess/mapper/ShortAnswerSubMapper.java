package com.zhkuchen.project.assess.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhkuchen.project.assess.domain.ShortAnswerSub;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 简答题
 * 
 */
public interface ShortAnswerSubMapper
{
    /**
     * 查询简答题管理数据
     * 
     * @param id 简答题信息
     * @return 简答题信息集合
     */
    public ShortAnswerSub getById(Long id);

    /**
     * 查询简答题管理数据
     * 
     * @param shortAnswerSub 简答题信息
     * @return 简答题信息集合
     */
    public List<ShortAnswerSub> get(ShortAnswerSub shortAnswerSub);

    /**
     * 查询简答题管理数据
     * 
     * @param shortAnswerSub 简答题信息
     * @return 简答题信息集合
     */
    public List<ShortAnswerSub> findList(ShortAnswerSub shortAnswerSub);

    /**
     * 根据ID查询题库树信息
     * 
     * @param id 角色ID
     * @return 选中简答题列表
     */
    public ShortAnswerSub findListById(Long id);

    // /**
    //  * 是否存在子节点
    //  * 
    //  * @param id 简答题ID
    //  * @return 结果
    //  */
    // public int hasChildById(Long id);

    /**
     * 根据考试id查询简答题题目id列表
     *
     * @param examinationId examinationId
     * @return List
     */
    public List<ShortAnswerSub> findSubjectShortAnswerByExaminationId(Long examinationId);

    /**
     * 新增简答题信息
     * 
     * @param shortAnswerSub 简答题信息
     * @return 结果
     */
    public int insert(ShortAnswerSub shortAnswerSub);

    /**
     * 修改简答题信息
     * 
     * @param shortAnswerSub 简答题信息
     * @return 结果
     */
    public int update(ShortAnswerSub shortAnswerSub);

    /**
     * 删除简答题信息
     * 
     * @param id 简答题信息
     * @return 结果
     */
    public int delete(Long id);

    // /**
    //  * 删除简答题管理信息
    //  * 
    //  * @param ids 简答题ID
    //  * @return 结果
    //  */
    // public int deleteAll(Long[] ids);

    // /**
    //  * 物理删除
    //  *
    //  * @param id SubjectShortAnswer
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

/**
 * IShortAnswerSubService
 * BaseMapper为MyBatis Plus内置方法，里面提供了多种数据库操作方法
 * BaseMapper口，为 Mybatis-Plus 启动时自动解析实体表关系映射转换为 Mybatis 内部对象注入容器
 */
// @Repository
// @Mapper
// public interface ShortAnswerSubMapper extends BaseMapper<ShortAnswerSub>{

    
// }