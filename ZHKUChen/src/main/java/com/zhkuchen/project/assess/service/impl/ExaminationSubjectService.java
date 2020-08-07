package com.zhkuchen.project.assess.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhkuchen.framework.aspectj.lang.annotation.DataScope;
import com.zhkuchen.project.assess.domain.ExaminationSubject;
import com.zhkuchen.project.assess.mapper.ExaminationSubjectMapper;
import com.zhkuchen.project.assess.service.IExaminationSubjectService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 考试题目表管理 服务实现
 * 
 */
@Service
public class ExaminationSubjectService implements IExaminationSubjectService
{
    @Autowired
    private ExaminationSubjectMapper examinationSubjectMapper;

    // /**
    //  * 查询考试题目表管理数据
    //  * 
    //  * @param examinationSubject 考试题目表信息
    //  * @return 考试题目表信息集合
    //  */
    // @Override
    // public List<ExaminationSubject> findSubjectsByExaminationId(Long examinationId)
    // {
    //     return examinationSubjectMapper.findSubjectsByExaminationId(examinationId);
    // }

    // /**
    //  * 查询考试题目表管理数据
    //  * 
    //  * @param examinationSubject 考试题目表信息
    //  * @return 考试题目表信息集合
    //  */
    // @Override
    // public List<ExaminationSubject> findSubjectJudgesByExaminationId(Long examinationId)
    // {
    //     return examinationSubjectMapper.findSubjectJudgesByExaminationId(examinationId);
    // }

    // /**
    //  * 查询考试题目表管理数据
    //  * 
    //  * @param examinationSubject 考试题目表信息
    //  * @return 考试题目表信息集合
    //  */
    // @Override
    // public List<ExaminationSubject> findSubjectShortAnswerByExaminationId(Long examinationId)
    // {
    //     return examinationSubjectMapper.findSubjectShortAnswerByExaminationId(examinationId);
    // }

    /**
     * 根据考试ID和题目序号查询
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    @Override
    public ExaminationSubject findByExaminationIdAndSubjectId(ExaminationSubject examinationSubject)
    {
        return examinationSubjectMapper.findByExaminationIdAndSubjectId(examinationSubject);
    }


    /**
     * 查询考试题目表管理数据
     * 
     * @param examinationSubject 考试题目表信息
     * @return 考试题目表信息集合
     */
    @Override
    // @DataScope(deptAlias = "d", userAlias = "u")
    public List<ExaminationSubject> findList(ExaminationSubject examinationSubject)
    {
        return examinationSubjectMapper.findList(examinationSubject);
    }

    /**
     * 根据考试题目表ID查询信息
     * 
     * @param id 考试题目表ID
     * @return 考试题目表信息
     */
    @Override
    public ExaminationSubject findListById(Long id)
    {
        return examinationSubjectMapper.findListById(id);
    }

    /**
     * 根据题目ID查询
     *
     * @param examinationSubject examinationSubject
     * @return List
     */
    @Override
    public List<ExaminationSubject> findListBySubjectId(ExaminationSubject examinationSubject)
    {
        return examinationSubjectMapper.findListBySubjectId(examinationSubject);
    }

    /**
     * 根据上一题ID查询下一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    @Override
    public ExaminationSubject getByPreviousId(ExaminationSubject examinationSubject)
    {
        return examinationSubjectMapper.getByPreviousId(examinationSubject);
    }

    /**
     * 根据当前题目ID查询上一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    @Override
    public ExaminationSubject getPreviousByCurrentId(ExaminationSubject examinationSubject)
    {
        return examinationSubjectMapper.getPreviousByCurrentId(examinationSubject);
    }

    /**
     * 根据分类id查询
     *
     * @param examinationSubject examinationSubject
     * @return List
     */
    @Override
    public List<ExaminationSubject> findListByCategoryId(ExaminationSubject examinationSubject)
    {
        return examinationSubjectMapper.findListByCategoryId(examinationSubject);
    }

    /**
     * 新增考试题目表信息
     * 
     * @param examinationSubject 考试题目表信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insert(ExaminationSubject examinationSubject)
    {
       return examinationSubjectMapper.insert(examinationSubject);
    }

    /**
     * 修改考试题目表信息
     * 
     * @param examinationSubject 考试题目表信息
     * @return 结果
     */
    @Override
    @Transactional
    public int update(ExaminationSubject examinationSubject)
    {
       return examinationSubjectMapper.update(examinationSubject);
    }

    // /**
    //  * 删除考试题目表管理信息
    //  * 
    //  * @param id 考试题目表ID
    //  * @return 结果
    //  */
    // @Override
    // public int delete(Long id)
    // {
    //    return examinationSubjectMapper.delete(id);
    // }

    /**
     * 删除题目管理信息
     * 
     * @param id 题目ID
     * @return 结果
     */
    @Override
    public int deleteBySubjectId(Long id)
    {
        return examinationSubjectMapper.deleteBySubjectId(id);
    }
    
    // /**
    //  * 根据题目ID删除
    //  *
    //  * @param examinationSubject examinationSubject
    //  * @return int
    //  */
    // @Override
    // public int deleteBySubjectId(ExaminationSubject examinationSubject)
    // {
    //    return examinationSubjectMapper.deleteBySubjectId(examinationSubject);
    // }
    
    /**
     * 删除考试题目表管理信息
     * 
     * @param ids 考试题目表ID
     * @return 结果
     */
    @Override
    public int deleteAll(Long[] ids)
    {
       return examinationSubjectMapper.deleteAll(ids);
    }
    
}
