package com.zhkuchen.project.assess.service;

import java.util.List;

import com.zhkuchen.project.assess.domain.ExaminationRecord;


public interface IExaminationRecordService
{

    /**
     * 查询考试记录管理数据
     * 
     * @param examinationRecord 考试记录信息
     * @return 考试记录信息集合
     */
    public List<ExaminationRecord> findList(ExaminationRecord examinationRecord);

    /**
     * 根据考试记录ID查询信息
     * 
     * @param id 考试记录ID
     * @return 考试记录信息
     */
    public ExaminationRecord findListById(Long id);

    /**
     * 根据考试记录ID查询信息
     * 
     * @param userId 考试记录ID
     * @return 考试记录信息
     */
    public List<ExaminationRecord> findSubmitedByUserId(ExaminationRecord examinationRecord);

    /**
     * 根据ID查询信息
     * 
     * @param id 考试记录ID
     * @return 考试记录信息
     */
    public ExaminationRecord findByUserIdAndExamIdAndDeptId(ExaminationRecord examinationRecord);

        /**
     * 新增保存考试记录信息
     * 
     * @param examinationRecord 考试记录信息
     * @return 结果
     */
    public int insert(ExaminationRecord examinationRecord);

    /**
     * 修改保存考试记录信息
     * 
     * @param examinationRecord 考试记录信息
     * @return 结果
     */
    public int update(ExaminationRecord examinationRecord);

    /**
     * 修改保存考试记录信息
     * 
     * @param examinationRecord 考试记录信息
     * @return 结果
     */
    public int updateMarkStatus(Long id);

    /**
     * 修改保存考试记录信息
     * 
     * @param id 考试记录信息
     * @return 结果
     */
    public int updateSubmitStatus(Long id);

    /**
     * 删除考试记录管理信息
     * 
     * @param id 考试记录ID
     * @return 结果
     */
    public int delete(Long id);
}