package com.zhkuchen.project.assess.mapper;

import java.util.List;

import com.zhkuchen.project.assess.domain.ExamAnswer;
/**
 * 考试答案表 数据层
 * 
 */
public interface ExamAnswerMapper
{
    /**
     * 查询考试答案管理数据
     * 
     * @param ExamAnswer 考试答案信息
     * @return 考试答案信息集合
     */
    public List<ExamAnswer> findList(ExamAnswer examAnswer);

    /**
     * 根据考试答案ID查询考试信息
     * 
     * @param subjectId 题目ID
     * @return 选中考试答案列表
     */
    public ExamAnswer getAnswerBySubjectAndExamRecordId(ExamAnswer examAnswer);

    /**
     * 根据考试答案ID查询考试信息
     * 
     * @param id 题目ID
     * @return 选中考试答案列表
     */
    public ExamAnswer findById(Long id);

    /**
     * 查询考试答案管理数据
     * 
     * @param ExamAnswer 考试答案信息
     * @return 考试答案信息集合
     */
    public List<ExamAnswer> getAnswerByExamRecordId(ExamAnswer examAnswer);
    
    /**
     * 新增考试答案信息
     * 
     * @param examAnswer 考试答案信息
     * @return 结果
     */
    public int insert(ExamAnswer examAnswer);

    /**
     * 修改考试答案信息
     * 
     * @param examAnswer 考试答案信息
     * @return 结果
     */
    public int update(ExamAnswer examAnswer);

    /**
     * 删除考试答案管理信息
     * 
     * @param id 考试答案ID
     * @return 结果
     */
    public int delete(Long id);
}