package com.zhkuchen.project.assess.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zhkuchen.project.assess.domain.JudgementSub;
/**
 * JudgementSubMapper
 * BaseMapper为MyBatis Plus内置方法，里面提供了多种数据库操作方法
 * BaseMapper口，为 Mybatis-Plus 启动时自动解析实体表关系映射转换为 Mybatis 内部对象注入容器
 */

public interface JudgementSubMapper
{

     /**
     * 查询判断题管理数据
     * 
     * @param id 判断题信息
     * @return 判断题信息集合
     */
    public JudgementSub getById(Long id);

    /**
     * 查询判断题管理数据
     * 
     * @param judgementSub 判断题信息
     * @return 判断题信息集合
     */
    public List<JudgementSub> get(JudgementSub judgementSub);

    /**
     * 查询判断题管理数据
     * 
     * @param judgementSub 判断题信息
     * @return 判断题信息集合
     */
    public List<JudgementSub> findList(JudgementSub judgementSub);

    /**
     * 根据ID查询题库树信息
     * 
     * @param id 角色ID
     * @return 选中判断题列表
     */
    public JudgementSub findListById(Long id);

    // /**
    //  * 是否存在子节点
    //  * 
    //  * @param id 判断题ID
    //  * @return 结果
    //  */
    // public int hasChildById(Long id);

    /**
     * 根据考试id查询判断题题目id列表
     *
     * @param examinationId examinationId
     * @return List
     */
    public List<JudgementSub> findSubjectJudgesByExaminationId(Long examinationId);
    
    /**
     * 新增判断题信息
     * 
     * @param judgementSub 判断题信息
     * @return 结果
     */
    public int insert(JudgementSub judgementSub);

    /**
     * 修改判断题信息
     * 
     * @param judgementSub 判断题信息
     * @return 结果
     */
    public int update(JudgementSub judgementSub);

    /**
     * 删除判断题信息
     * 
     * @param id 判断题信息
     * @return 结果
     */
    public int delete(Long id);

// @Repository
// @Mapper
// @Component
// public interface JudgementSubMapper extends BaseMapper<JudgementSub>{


   /* @Select({
        "<script>",
            "select * from exam_subject_judgement d",
            "left join exam_subject_category dc on d.category_id = dc.id",
            "where d.del_flag = '0'",
            "<if test='subjectName != null'>",
                "and d.subject_name like CONCAT('%',#{subjectName},'%')",
            "</if>",
            "<if test='categoryId != null and categoryId != 0'>",
                "and (d.category_id = #{categoryId} OR d.category_id in ( SELECT t.id FROM exam_subject_category t WHERE FIND_IN_SET (#{id},ancestry) ))",
            "</if>",
           "${dataScope}",
        "</script>"
    })
    public List<JudgementSub> findList(JudgementSub judgementSub);

    @Select({
        "<script>",
            "select * from exam_subject_judgement d",
            "left join exam_subject_category dc on d.category_id = dc.id",
            "where d.del_flag = '0' and d.id = #{id}",
        "</script>"
    })
    public JudgementSub findListById(Long id);

    @Select({
        "<script>",
            "update exam_subject_judgement",
            "<set>",
                "<if test='categoryId != null'>",
                    "category_id = #{categoryId},",
                "</if>",
                "<if test='subjectName != null'>",
                    "subject_name = #{subjectName},",
                "</if>",
                "<if test='type != null'>",
                    "type = #{type},",
                "</if>",
                "<if test='answer != null'>",
                    "answer = #{answer},",
                "</if>",
                "<if test='score != null'>",
                    "score = #{score},",
                "</if>",
                "<if test='analysis != null'>",
                    "analysis = #{analysis},",
                "</if>",
                "<if test='level != null'>",
                    "level = #{level},",
                "</if>",
                "<if test='updateBy != null'>",
                    "update_by = #{updateBy},",
                "</if>",
                "update_time = sysdate()",
            "</set>",
            "where id = #{id}",
        "</script>"
    })
    public Integer update(JudgementSub judgementSub);*/
}