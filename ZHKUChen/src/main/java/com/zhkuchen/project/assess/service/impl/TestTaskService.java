package com.zhkuchen.project.assess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhkuchen.framework.aspectj.lang.annotation.DataScope;
import com.zhkuchen.framework.security.service.TokenService;
import com.zhkuchen.project.assess.domain.TestTask;
import com.zhkuchen.project.assess.mapper.TestTaskMapper;
import com.zhkuchen.project.assess.service.ITestTaskService;

import lombok.extern.slf4j.Slf4j;


/**
 * 考试记录管理 服务实现
 * 
 */
@Service
@Slf4j
public class TestTaskService implements ITestTaskService
{
    @Autowired
    private TestTaskMapper testTaskMapper;

    /**
     * 查询考试记录管理数据
     * 
     * @param examination 考试记录信息
     *
     * 
     * @return 考试记录信息集合
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<TestTask> findList(TestTask testTask)
    {
      return testTaskMapper.findList(testTask);
    }

    /**
     * 根据考试任务分配的用户手机号查询考试信息
     * 
     * @param userPhonenumber 用户手机号
     * @return 考试记录信息
     */
    @Override
    public List<TestTask> findListByUserPhonenumber(TestTask testTask)
    {
        return testTaskMapper.findListByUserPhonenumber(testTask);
    }

    /**
     * 根据考试任务分配的用户手机号查询考试信息
     * 
     * @param userPhonenumber 用户手机号
     * @return 考试记录信息
     */
    @Override
    public List<TestTask> findWrittenListByUserPhonenumber(TestTask testTask)
    {
        return testTaskMapper.findWrittenListByUserPhonenumber(testTask);
    }

    /**
     * 根据考试记录ID查询信息
     * 
     * @param id 考试记录ID
     * @return 考试记录信息
     */
    @Override
    public TestTask findListById(Long id)
    {
        return testTaskMapper.findListById(id);
    }

    /**
     * 根据考试任务分配的用户手机号查询考试信息
     * 
     * @param userPhonenumber 用户手机号
     * @return 考试记录信息
     */
    @Override
    public TestTask findByDeptIdAndExaminationIdAndUserPhone(TestTask testTask)
    {
        return testTaskMapper.findByDeptIdAndExaminationIdAndUserPhone(testTask);
    }

    

    /**
     * 新增保存题目信息
     * 
     * @param testTask 题目信息
     * @return 结果
     */
    @Override
    public int insert(TestTask testTask)
    {
        return testTaskMapper.insert(testTask);
    }

    /**
     * 修改保存题目信息
     * 
     * @param id 题目信息
     * @return 结果
     */
    @Override
    public int update(Long id)
    {
        return testTaskMapper.update(id);
        
    }
}