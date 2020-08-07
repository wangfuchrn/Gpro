package com.zhkuchen.project.assess.service;

import java.util.List;

import com.zhkuchen.project.assess.domain.TestTask;


public interface ITestTaskService
{
    /**
     * 查询考试记录管理数据
     * 
     * @param testTask 考试记录信息
     * @return 考试记录信息集合
     */
    public List<TestTask> findList(TestTask testTask);

    /**
     * 根据考试任务分配的用户手机号查询考试信息
     * 
     * @param userPhonenumber 用户手机号
     * @return 考试记录信息
     */
    public List<TestTask> findListByUserPhonenumber(TestTask testTask);

    /**
     * 根据考试任务分配的用户手机号查询考试信息
     * 
     * @param userPhonenumber 用户手机号
     * @return 考试记录信息
     */
    public List<TestTask> findWrittenListByUserPhonenumber(TestTask testTask);

    public TestTask findByDeptIdAndExaminationIdAndUserPhone(TestTask testTask);

    /**
     * 根据考试记录ID查询信息
     * 
     * @param id 考试记录ID
     * @return 考试记录信息
     */
    public TestTask findListById(Long id);

    /**
     * 新增保存考试记录信息
     * 
     * @param testTask 考试记录信息
     * @return 结果
     */
    public int insert(TestTask testTask);

    /**
     * 修改保存考试记录信息
     * 
     * @param testTask 考试记录信息
     * @return 结果
     */
    public int update(Long id);
}