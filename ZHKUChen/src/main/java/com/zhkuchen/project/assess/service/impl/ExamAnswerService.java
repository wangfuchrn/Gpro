package com.zhkuchen.project.assess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhkuchen.framework.aspectj.lang.annotation.DataScope;
import com.zhkuchen.framework.security.service.TokenService;
import com.zhkuchen.project.assess.domain.ExamAnswer;
import com.zhkuchen.project.assess.mapper.ExamAnswerMapper;
import com.zhkuchen.project.assess.service.IExamAnswerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 考试答案管理 服务实现
 * 
 */
@Service
@Slf4j
public class ExamAnswerService implements IExamAnswerService
{
    @Autowired
    private ExamAnswerMapper examAnswerMapper;

    /**
     * 查询考试答案管理数据
     * 
     * @param examAnswer 考试答案信息
     * @return 考试答案信息集合
     */
    @Override
    public List<ExamAnswer> findList(ExamAnswer examAnswer)
    {
      return examAnswerMapper.findList(examAnswer);
    }

    /**
     * 根据考试答案ID查询信息
     * 
     * @param id 考试答案ID
     * @return 考试答案信息
     */
    @Override
    public ExamAnswer getAnswerBySubjectAndExamRecordId(ExamAnswer examAnswer)
    {
        return examAnswerMapper.getAnswerBySubjectAndExamRecordId(examAnswer);
    }

    /**
     * 根据考试答案ID查询信息
     * 
     * @param id 考试答案ID
     * @return 考试答案信息
     */
    @Override
    public ExamAnswer findById(Long id)
    {
        return examAnswerMapper.findById(id);
    }

    /**
     * 查询考试答案管理数据
     * 
     * @param examAnswer 考试答案信息
     * @return 考试答案信息集合
     */
    @Override
    public List<ExamAnswer> getAnswerByExamRecordId(ExamAnswer examAnswer)
    {
      return examAnswerMapper.getAnswerByExamRecordId(examAnswer);
    }

    /**
     * 新增保存考试答案信息
     * 
     * @param examAnswer 考试答案信息
     * @return 结果
     */
    @Override
    public int insert(ExamAnswer examAnswer)
    {
        return examAnswerMapper.insert(examAnswer);
    }

    /**
     * 修改保存考试答案信息
     * 
     * @param examAnswer 考试答案信息
     * @return 结果
     */
    @Override
    public int update(ExamAnswer examAnswer)
    {
        return examAnswerMapper.update(examAnswer);
        
    }

    /**
     * 删除考试答案管理信息
     * 
     * @param id 考试答案ID
     * @return 结果
     */
    @Override
    public int delete(Long id)
    {
        return examAnswerMapper.delete(id);
    }

}