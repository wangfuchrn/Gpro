package com.zhkuchen.project.assess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhkuchen.framework.aspectj.lang.annotation.DataScope;
import com.zhkuchen.framework.security.service.TokenService;
import com.zhkuchen.project.assess.domain.ExaminationRecord;
import com.zhkuchen.project.assess.mapper.ExaminationRecordMapper;
import com.zhkuchen.project.assess.service.IExaminationRecordService;

import lombok.extern.slf4j.Slf4j;


/**
 * 考试记录管理 服务实现
 * 
 */
@Service
@Slf4j
public class ExaminationRecordService implements IExaminationRecordService
{
    @Autowired
    private ExaminationRecordMapper examinationRecordMapper;

    /**
     * 查询考试记录管理数据
     * 
     * @param examinationRecord 考试记录信息
     * @return 考试记录信息集合
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<ExaminationRecord> findList(ExaminationRecord examinationRecord)
    {
      return examinationRecordMapper.findList(examinationRecord);
    }

    /**
     * 根据考试记录ID查询信息
     * 
     * @param id 考试记录ID
     * @return 考试记录信息
     */
    @Override
    public ExaminationRecord findListById(Long id)
    {
        return examinationRecordMapper.findListById(id);
    }

    /**
     * 根据考试记录ID查询信息
     * 
     * @param userId 考试记录ID
     * @return 考试记录信息
     */
    @Override
    public List<ExaminationRecord> findSubmitedByUserId(ExaminationRecord examinationRecord)
    {
        return examinationRecordMapper.findSubmitedByUserId(examinationRecord);
    }

    /**
     * 根据考试记录ID查询信息
     * 
     * @param id 考试记录ID
     * @return 考试记录信息
     */
    @Override
    public ExaminationRecord findByUserIdAndExamIdAndDeptId(ExaminationRecord examinationRecord){
        return examinationRecordMapper.findByUserIdAndExamIdAndDeptId(examinationRecord);
    }

    /**
     * 新增保存考试记录信息
     * 
     * @param examinationRecord 考试记录信息
     * @return 结果
     */
    @Override
    public int insert(ExaminationRecord examinationRecord)
    {
        return examinationRecordMapper.insert(examinationRecord);
    }

    /**
     * 修改保存考试记录信息
     * 
     * @param examinationRecord 考试记录信息
     * @return 结果
     */
    @Override
    public int update(ExaminationRecord examinationRecord)
    {
        return examinationRecordMapper.update(examinationRecord);
        
    }

    /**
     * 修改保存考试记录信息
     * 
     * @param id 考试记录信息
     * @return 结果
     */
    @Override
    public int updateMarkStatus(Long id)
    {
        return examinationRecordMapper.updateMarkStatus(id);
        
    }

    /**
     * 修改保存考试记录信息
     * 
     * @param id 考试记录信息
     * @return 结果
     */
    @Override
    public int updateSubmitStatus(Long id)
    {
        return examinationRecordMapper.updateSubmitStatus(id);
        
    }

    /**
     * 删除考试记录管理信息
     * 
     * @param id 考试记录ID
     * @return 结果
     */
    @Override
    public int delete(Long id)
    {
        return examinationRecordMapper.delete(id);
    }

}