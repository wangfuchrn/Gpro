package com.zhkuchen.project.assess.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhkuchen.framework.aspectj.lang.annotation.DataScope;
import com.zhkuchen.project.assess.domain.ChoicesOptionSub;
import com.zhkuchen.project.assess.domain.ChoicesSub;
import com.zhkuchen.project.assess.mapper.ChoicesSubMapper;
import com.zhkuchen.project.assess.service.IChoicesOptionSubService;
import com.zhkuchen.project.assess.service.IChoicesSubService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 题目管理 服务实现
 * 
 */
@Service
public class ChoicesSubService implements IChoicesSubService
{

    @Autowired
    private ChoicesSubMapper choicesSubMapper;

    /**
     * 查询判断题管理数据
     * 
     * @param id 判断题信息
     * @return 判断题信息集合
     */
    @Override
    public List<ChoicesSub> getById(Long[] ids)
    {
        return choicesSubMapper.getById(ids);
    }
    // public ChoicesSub getById(Long id)
    // {
    //     return choicesSubMapper.getById(id);
    // }

    /**
     * 查询题目管理数据
     * 
     * @param choicesSub 题目信息
     * @return 题目信息集合
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<ChoicesSub> findList(ChoicesSub choicesSub)
    {
        return choicesSubMapper.findList(choicesSub);
    }

    /**
     * 根据题目ID查询信息
     * 
     * @param id 题目ID
     * @return 题目信息
     */
    @Override
    public ChoicesSub findListById(Long id)
    {
        return choicesSubMapper.findListById(id);
    }

    //-----------------------------------------------------

    // /**
    //  * 根据上一题ID查询下一题
    //  *
    //  * @param examinationId examinationId
    //  * @param previousId    previousId
    //  * @param nextType      0：下一题，1：上一题
    //  * @return SubjectDto
    //  */
    // @Override
    // public SubjectChoices getNextByCurrentIdAndType(Long examinationId, Long previousId, Integer nextType) {
    //     return null;
    // }

    // /**
    //  * 根据ID查询
    //  *
    //  * @param id id
    //  * @return SubjectDto
    //  */
    // @Override
    // public SubjectDto getSubject(Long id) {
    //     return SubjectUtil.SubjectChoicesToDto(this.get(id));
    // }

    // /**
    //  * 根据ID批量查询
    //  *
    //  * @param ids ids
    //  * @return List<SubjectDto>
    //  */
    // @Override
    // public List<SubjectDto> findSubjectListById(Long[] ids) {
    //     return SubjectUtil.SubjectChoicesToDto(this.findListById(ids));
    // }

    //---------------------------------------------------

    /**
     * 查询考试题目表管理数据
     * 
     * @param examinationSubject 考试题目表信息
     * @return 考试题目表信息集合
     */
    @Override
    public List<ChoicesSub> findSubjectsByExaminationId(Long examinationId)
    {
        return choicesSubMapper.findSubjectsByExaminationId(examinationId);
    }


    /**
     * 新增保存题目信息
     * 
     * @param choicesSub 题目信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insert(ChoicesSub choicesSub)
    {
        return choicesSubMapper.insert(choicesSub);
    }

    /**
     * 修改保存题目信息
     * 
     * @param choicesSub 题目信息
     * @return 结果
     */
    @Override
    @Transactional
    public int update(ChoicesSub choicesSub)
    {
        return choicesSubMapper.update(choicesSub);
        
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
        return choicesSubMapper.delete(id);
    }

    // /**
    //  * 删除题目管理信息
    //  * 
    //  * @param ids 题目ID
    //  * @return 结果
    //  */
    // public int deleteAll(Long[] ids)
    // {
    //     return choicesSubMapper.deleteAll(ids);
    // }

    // /**
    //  * 删除题目管理信息
    //  * 
    //  * @param id 题目ID
    //  * @return 结果
    //  */
    // public int physicalDelete(Long id)
    // {
    //     return choicesSubMapper.physicalDelete(id);
    // }

    // /**
    //  * 删除题目管理信息
    //  * 
    //  * @param ids 题目ID
    //  * @return 结果
    //  */
    // public int physicalDeleteAll(Long[] ids)
    // {
    //     return choicesSubMapper.physicalDeleteAll(ids);
    // }

}