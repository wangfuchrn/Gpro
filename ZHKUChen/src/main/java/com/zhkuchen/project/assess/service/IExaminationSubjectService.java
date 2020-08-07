package com.zhkuchen.project.assess.service;

import java.util.List;

import com.zhkuchen.project.assess.domain.ExaminationSubject;


public interface IExaminationSubjectService
{
    /**
     * 根据考试ID和题目序号查询
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    public ExaminationSubject findByExaminationIdAndSubjectId(ExaminationSubject examinationSubject);

    /**
     * 根据题目ID查询
     *
     * @param examinationSubject examinationSubject
     * @return List
     */
    public List<ExaminationSubject> findList(ExaminationSubject examinationSubject);

    /**
     * 根据题目ID查询
     *
     * @param examinationSubject examinationSubject
     * @return List
     */
    public ExaminationSubject findListById(Long id);

    /**
     * 根据题目ID查询
     *
     * @param examinationSubject examinationSubject
     * @return List
     */
    public List<ExaminationSubject> findListBySubjectId(ExaminationSubject examinationSubject);

    /**
     * 根据上一题ID查询下一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    public ExaminationSubject getByPreviousId(ExaminationSubject examinationSubject);

    /**
     * 根据当前题目ID查询上一题
     *
     * @param examinationSubject examinationSubject
     * @return ExaminationSubject
     */
    public ExaminationSubject getPreviousByCurrentId(ExaminationSubject examinationSubject);

    /**
     * 根据分类id查询
     *
     * @param examinationSubject examinationSubject
     * @return List
     */
    public List<ExaminationSubject> findListByCategoryId(ExaminationSubject examinationSubject);

    /**
     * 新增考试题目表信息
     * 
     * @param examinationSubject 考试题目表信息
     * @return 结果
     */
    public int insert(ExaminationSubject examinationSubject);

    /**
     * 修改考试题目表信息
     * 
     * @param examinationSubject 考试题目表信息
     * @return 结果
     */
    public int update(ExaminationSubject examinationSubject);

    // /**
    //  * 删除考试题目表管理信息
    //  * 
    //  * @param id 考试题目表ID
    //  * @return 结果
    //  */
    // public int delete(Long id);

    /**
     * 删除考试题目表管理信息
     * 
     * @param id 考试题目表ID
     * @return 结果
     */
    public int deleteBySubjectId(Long id);

    /**
     * 删除考试题目表管理信息
     * 
     * @param ids 考试题目表ID
     * @return 结果
     */
    public int deleteAll(Long[] ids);    

}