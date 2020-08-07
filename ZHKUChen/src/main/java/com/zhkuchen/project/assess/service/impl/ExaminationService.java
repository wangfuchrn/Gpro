package com.zhkuchen.project.assess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhkuchen.framework.aspectj.lang.annotation.DataScope;
import com.zhkuchen.framework.security.service.TokenService;
import com.zhkuchen.project.assess.domain.Examination;
import com.zhkuchen.project.assess.mapper.ExaminationMapper;
import com.zhkuchen.project.assess.service.IExaminationService;

import lombok.extern.slf4j.Slf4j;


/**
 * 测评管理 服务实现
 * 
 */
@Service
@Slf4j
public class ExaminationService implements IExaminationService
{
    @Autowired
    private ExaminationMapper examinationMapper;

    // @Autowired
    // private ExaminationSubjectMapper examinationSubjectMapper;


    /**
     * 查询测评管理数据
     * 
     * @param examination 测评信息
     * @return 测评信息集合
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<Examination> findList(Examination examination)
    {
    	return examinationMapper.findList(examination);
    }


	/**
     * 根据测评ID查询信息
     * 
     * @param id 测评ID
     * @return 测评信息
     */
    @Override
    public Examination findListById(Long id)
    {
        return examinationMapper.findListById(id);
    }

    /**
     * 根据测评ID查询信息
     * 
     * @param id 测评ID
     * @return 测评信息
     */
    @Override
    public Examination findById(Long id)
    {
        return examinationMapper.findById(id);
    }

    /**
     * 新增保存题目信息
     * 
     * @param examination 题目信息
     * @return 结果
     */
    @Override
    public int insert(Examination examination)
    {
        return examinationMapper.insert(examination);
    }

    /**
     * 修改保存题目信息
     * 
     * @param examination 题目信息
     * @return 结果
     */
    @Override
    public int update(Examination examination)
    {
        return examinationMapper.update(examination);
        
    }

    /**
     * 修改保存题目信息的状态
     * 
     * @param id 题目信息
     * @return 结果
     */
    @Override
    public int updateExamStatus(Long id)
    {
        return examinationMapper.updateExamStatus(id);
        
    }

    /**
     * 删除题目管理信息
     * 
     * @param id 题目ID
     * @return 结果
     */
    @Override
    public int delete(Long id)
    {
        return examinationMapper.delete(id);
    }

    /**
     * 删除题目管理信息
     * 
     * @param ids 题目ID
     * @return 结果
     */
    @Override
    public int deleteAll(Long[] ids)
    {
        return examinationMapper.deleteAll(ids);
    }

}