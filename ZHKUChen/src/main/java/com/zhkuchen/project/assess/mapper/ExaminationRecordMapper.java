package com.zhkuchen.project.assess.mapper;

import java.util.List;

import com.zhkuchen.project.assess.domain.ExaminationRecord;
/**
 * 考试记录表 数据层
 * 
 */
public interface ExaminationRecordMapper
{

    /**
     * 查询考试记录管理数据
     * 
     * @param ExaminationRecord 考试记录信息
     * @return 考试记录信息集合
     */
    public List<ExaminationRecord> findList(ExaminationRecord examinationRecord);

    /**
     * 根据考试记录ID查询考试信息
     * 
     * @param id 题目ID
     * @return 选中考试记录列表
     */
    public ExaminationRecord findListById(Long id);

    /**
     * 根据ID查询考试记录信息
     * 
     * @param id 题目ID
     * @return 选中考试记录列表
     */
    public ExaminationRecord findByUserIdAndExamIdAndDeptId(ExaminationRecord examinationRecord);

        /**
     * 根据考试记录ID查询考试信息
     * 
     * @param userId 题目ID
     * @return 选中考试记录列表
     */
    public List<ExaminationRecord> findSubmitedByUserId(ExaminationRecord examinationRecord);
     
    /**
     * 新增考试记录信息
     * 
     * @param examinationRecord 考试记录信息
     * @return 结果
     */
    public int insert(ExaminationRecord examinationRecord);

    /**
     * 修改考试记录信息
     * 
     * @param examinationRecord 考试记录信息
     * @return 结果
     */
    public int update(ExaminationRecord examinationRecord);

    /**
     * 修改考试记录信息
     * 
     * @param examinationRecord 考试记录信息
     * @return 结果
     */
    public int updateMarkStatus(Long id);

    /**
     * 修改考试记录信息
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