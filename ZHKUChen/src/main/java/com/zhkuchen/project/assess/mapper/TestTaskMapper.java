package com.zhkuchen.project.assess.mapper;

import java.util.List;

import com.zhkuchen.project.assess.domain.TestTask;
/**
 * 考试任务类型表 数据层
 * 
 */
public interface TestTaskMapper
{
    /**
     * 查询考试任务管理数据
     * 
     * @param testTask 考试任务信息
     * @return 考试任务信息集合
     */
    public List<TestTask> findList(TestTask testTask);

    /**
     * 根据考试任务分配的用户手机号查询考试信息
     * 
     * @param userPhonenumber 用户手机号
     * @return 选中考试任务列表
     */
    public List<TestTask> findListByUserPhonenumber(TestTask testTask);

    /**
     * 根据考试任务分配的用户手机号查询考试信息
     * 
     * @param userPhonenumber 用户手机号
     * @return 选中考试任务列表
     */
    public List<TestTask> findWrittenListByUserPhonenumber(TestTask testTask);

    /**
     * 根据考试任务ID查询考试信息
     * 
     * @param id 考试任务ID
     * @return 选中考试任务列表
     */
    public TestTask findListById(Long id);

    /**
     * 根据考试任务分配的用户手机号查询考试信息
     * 
     * @param testTask 
     * @return 选中考试任务列表
     */
    public TestTask findByDeptIdAndExaminationIdAndUserPhone(TestTask testTask);


    /**
     * 新增考试任务信息
     * 
     * @param testTask 考试任务信息
     * @return 结果
     */
    public int insert(TestTask testTask);

    /**
     * 修改考试任务信息
     * 
     * @param id 考试任务信息
     * @return 结果
     */
    public int update(Long id);

}
