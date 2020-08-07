package com.zhkuchen.project.assess.mapper;

import java.util.List;

import com.zhkuchen.project.assess.domain.Examination;
/**
 * 测评类型表 数据层
 * 
 */
public interface ExaminationMapper
{
    /**
     * 查询测评管理数据
     * 
     * @param examination 测评信息
     * @return 测评信息集合
     */
    public List<Examination> findList(Examination examination);

    /**
     * 根据测评ID查询考试信息
     * 
     * @param id 角色ID
     * @return 选中测评列表
     */
    public Examination findListById(Long id);

    /**
     * 根据测评ID查询考试信息
     * 
     * @param id 角色ID
     * @return 选中测评列表
     */
    public Examination findById(Long id);

    /**
     * 新增测评信息
     * 
     * @param examination 测评信息
     * @return 结果
     */
    public int insert(Examination examination);

    /**
     * 修改测评信息
     * 
     * @param examination 测评信息
     * @return 结果
     */
    public int update(Examination examination);

    /**
     * 删除测评管理信息的状态
     * 
     * @param id 测评ID
     * @return 结果
     */
    public int updateExamStatus(Long id);

    /**
     * 删除测评管理信息
     * 
     * @param id 测评ID
     * @return 结果
     */
    public int delete(Long id);

    /**
     * 删除测评管理信息
     * 
     * @param ids 测评ID
     * @return 结果
     */
    public int deleteAll(Long[] ids);

}
