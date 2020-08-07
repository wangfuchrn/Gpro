package com.zhkuchen.project.assess.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import io.lettuce.core.dynamic.annotation.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhkuchen.project.assess.domain.ChoicesOptionSub;



/**
 * 选择题题目
 * 
 */
public interface ChoicesOptionSubMapper
{
    /**
     * 根据题目ID查找
     *
     * @param subjectChoicesId subjectChoicesId
     * @return List
     */
    public List<ChoicesOptionSub> selectBySubjectChoicesId(Long subjectChoicesId);

    /**
     * 查询选项管理数据
     * 
     * @param choicesOptionSub 选项信息
     * @return 选项信息集合
     */
    public List<ChoicesOptionSub> findList(ChoicesOptionSub choicesOptionSub);

    /**
     * 根据选项ID查询考试信息
     * 
     * @param id 角色ID
     * @return 选中选项列表
     */
    public ChoicesOptionSub findListById(Long id);

    /**
     * 新增选项信息
     * 
     * @param choicesOptionSub 选项信息
     * @return 结果
     */
    public int insert(ChoicesOptionSub choicesOptionSub);

    /**
     * 新增选项信息
     * 
     * @param choicesOptionSub 选项信息
     * @return 结果
     */
    public int insertBatch(List<ChoicesOptionSub> choicesOptionSub);

    /**
     * 修改选项信息
     * 
     * @param choicesOptionSub 选项信息
     * @return 结果
     */
    public int update(ChoicesOptionSub choicesOptionSub);

    /**
     * 删除选项管理信息
     * 
     * @param id 选项ID
     * @return 结果
     */
    public int delete(Long id);

    /**
     * 根据选择题ID删除
     *
     * @param choicesOptionSub choicesOptionSub
     * @return int
     */
    public int deleteBySubjectChoicesId(ChoicesOptionSub choicesOptionSub);

    /**
     * 删除选项管理信息
     * 
     * @param ids 选项ID
     * @return 结果
     */
    public int deleteAll(Long[] ids);
    

}

/**
 * ChoicesOptionSubMapper
 * BaseMapper为MyBatis Plus内置方法，里面提供了多种数据库操作方法
 * BaseMapper口，为 Mybatis-Plus 启动时自动解析实体表关系映射转换为 Mybatis 内部对象注入容器
 */
// @Mapper
// @Repository
// @Component
// public interface ChoicesOptionSubMapper extends BaseMapper<ChoicesOptionSub>
// {
//     @Select({
//         "<script>",
//           "select * from exam_subject_option d",
//           "where d.del_flag = '0'",
//           "and d.subject_choices_id = #{subjectChoicesId}",
//         "</script>"
//         // "<if test="examinationName != null and examinationName != ''">",
//           // "and d.examination_name like CONCAT('%',#{examinationName},'%')",
//           // "</if>",
//           // "${dataScope}",
//     })
//     public List<ChoicesOptionSub> selectBySubjectChoicesId(@Param("subjectChoicesId") Long subjectChoicesId
//     );
// }