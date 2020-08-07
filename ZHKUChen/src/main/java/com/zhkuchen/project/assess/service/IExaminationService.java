package com.zhkuchen.project.assess.service;

import java.util.List;

import com.zhkuchen.project.assess.domain.Examination;


public interface IExaminationService
{
    // /**
    //  * 查询考试数量
    //  *
    //  * @param examination examination
    //  * @return int
    //  */
    // public int findExaminationCount(Examination examination);

    /**
     * 查询考试管理数据
     * 
     * @param examination 考试信息
     * @return 考试信息集合
     */
    public List<Examination> findList(Examination examination);

    /**
     * 根据考试ID查询信息
     * 
     * @param id 考试ID
     * @return 考试信息
     */
    public Examination findListById(Long id);

    /**
     * 根据考试ID查询信息
     * 
     * @param id 考试ID
     * @return 考试信息
     */
    public Examination findById(Long id);

        /**
     * 新增保存考试信息
     * 
     * @param examination 考试信息
     * @return 结果
     */
    public int insert(Examination examination);

    /**
     * 修改保存考试信息
     * 
     * @param examination 考试信息
     * @return 结果
     */
    public int update(Examination examination);

    /**
     * 修改保存考试信息
     * 
     * @param examination 考试信息
     * @return 结果
     */
    public int updateExamStatus(Long id);

    /**
     * 删除考试管理信息
     * 
     * @param id 考试ID
     * @return 结果
     */
    public int delete(Long id);

    /**
     * 删除考试管理信息
     * 
     * @param ids 考试ID
     * @return 结果
     */
    public int deleteAll(Long[] ids);

}