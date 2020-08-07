package com.zhkuchen.project.assess.service;

import java.util.List;

import com.zhkuchen.project.assess.domain.ExamAnswer;


public interface IExamAnswerService
{
    /**
     * 查询考试答案管理数据
     * 
     * @param examAnswer 考试答案信息
     * @return 考试答案信息集合
     */
    public List<ExamAnswer> findList(ExamAnswer examAnswer);

    /**
     * 根据考试答案ID查询信息
     * 
     * @param id 考试答案ID
     * @return 考试答案信息
     */
    public ExamAnswer getAnswerBySubjectAndExamRecordId(ExamAnswer examAnswer);

    /**
     * 根据考试答案ID查询信息
     * 
     * @param id 考试答案ID
     * @return 考试答案信息
     */
    public ExamAnswer findById(Long id);

    public List<ExamAnswer> getAnswerByExamRecordId(ExamAnswer examAnswer);

        /**
     * 新增保存考试答案信息
     * 
     * @param examAnswer 考试答案信息
     * @return 结果
     */
    public int insert(ExamAnswer examAnswer);

    /**
     * 修改保存考试答案信息
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
 