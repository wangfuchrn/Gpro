package com.zhkuchen.project.assess.service.impl;

import java.util.List;

import javax.annotation.Resource;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhkuchen.framework.aspectj.lang.annotation.DataScope;
import com.zhkuchen.project.assess.domain.ShortAnswerSub;
import com.zhkuchen.project.assess.mapper.ShortAnswerSubMapper;
import com.zhkuchen.project.assess.service.IShortAnswerSubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 题目管理 服务实现
 * 
 */
@Service
public class ShortAnswerSubService implements IShortAnswerSubService
{

    @Autowired
    private ShortAnswerSubMapper shortAnswerMapper;

    /**
     * 查询题目管理数据
     * 
     * @param shortAnswerSub 题目信息
     * @return 题目信息集合
     */
    @Override
    // @DataScope(deptAlias = "d", userAlias = "u")
    public List<ShortAnswerSub> findList(ShortAnswerSub shortAnswerSub)
    {
        return shortAnswerMapper.findList(shortAnswerSub);
    }

    /**
     * 根据题目ID查询信息
     * 
     * @param id 题目ID
     * @return 题目信息
     */
    @Override
    public ShortAnswerSub findListById(Long id)
    {
        return shortAnswerMapper.findListById(id);
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
    // public SubjectShortAnswer getNextByCurrentIdAndType(Long examinationId, Long previousId, Integer nextType) {
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
    //     return SubjectUtil.SubjectShortAnswerToDto(this.get(id));
    // }

    // /**
    //  * 根据ID批量查询
    //  *
    //  * @param ids ids
    //  * @return List<SubjectDto>
    //  */
    // @Override
    // public List<SubjectDto> findSubjectListById(Long[] ids) {
    //     return SubjectUtil.SubjectShortAnswerToDto(this.findListById(ids));
    // }

    //---------------------------------------------------

    /**
     * 查询考试题目表管理数据
     * 
     * @param examinationSubject 考试题目表信息
     * @return 考试题目表信息集合
     */
    @Override
    public List<ShortAnswerSub> findSubjectShortAnswerByExaminationId(Long examinationId)
    {
        return shortAnswerMapper.findSubjectShortAnswerByExaminationId(examinationId);
    }

    /**
     * 新增保存题目信息
     * 
     * @param shortAnswerSub 题目信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insert(ShortAnswerSub shortAnswerSub)
    {
        return shortAnswerMapper.insert(shortAnswerSub);
    }

    /**
     * 修改保存题目信息
     * 
     * @param shortAnswerSub 题目信息
     * @return 结果
     */
    @Override
    @Transactional
    public int update(ShortAnswerSub shortAnswerSub)
    {
        return shortAnswerMapper.update(shortAnswerSub);
        
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
        return shortAnswerMapper.delete(id);
    }

    // /**
    //  * 删除题目管理信息
    //  * 
    //  * @param ids 题目ID
    //  * @return 结果
    //  */
    // public int deleteAll(Long[] ids)
    // {
    //     return shortAnswerMapper.deleteAll(ids);
    // }

    // /**
    //  * 删除题目管理信息
    //  * 
    //  * @param id 题目ID
    //  * @return 结果
    //  */
    // public int physicalDelete(Long id)
    // {
    //     return shortAnswerMapper.physicalDelete(id);
    // }

    // /**
    //  * 删除题目管理信息
    //  * 
    //  * @param ids 题目ID
    //  * @return 结果
    //  */
    // public int physicalDeleteAll(Long[] ids)
    // {
    //     return shortAnswerMapper.physicalDeleteAll(ids);
    // }

    /**
     * 获取题目管理信息
     * 
     * @param id 题目ID
     * @return 结果
     */
    @Override
    public ShortAnswerSub getById(Long id) {
        return null;
    }

    /**
     * 修改保存题目信息
     * 
     * @param shortAnswerSub 题目信息
     * @return 结果
     */
    @Override
    public ShortAnswerSub get(ShortAnswerSub shortAnswerSub) {
        return null;
    }


}
// /**
//  * IShortAnswerSubService具体实现类
//  * 要使用IService，impl文件就要继承ServiceImpl
//  */
// @Service
// public class ShortAnswerSubService extends ServiceImpl<ShortAnswerSubMapper, ShortAnswerSub> 
// implements IShortAnswerSubService 
// {
//     @Resource
//     ShortAnswerSubMapper shortAnswerMapper; 
// }