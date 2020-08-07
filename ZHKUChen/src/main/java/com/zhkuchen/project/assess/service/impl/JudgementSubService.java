package com.zhkuchen.project.assess.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhkuchen.framework.aspectj.lang.annotation.DataScope;
import com.zhkuchen.project.assess.domain.JudgementSub;
import com.zhkuchen.project.assess.mapper.JudgementSubMapper;
import com.zhkuchen.project.assess.service.IJudgementSubService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * IJudgementSubService具体实现类
 * 要使用IService，impl文件就要继承ServiceImpl
 */
@Service
public class JudgementSubService implements IJudgementSubService 
{
    @Autowired
    private JudgementSubMapper judgeMapper;

    /**
     * 获取题目管理信息
     * 
     * @param id 题目ID
     * @return 结果
     */
    @Override
    public JudgementSub getById(Long id) {
        return null;
    }

    /**
     * 获取题目管理信息
     * 
     * @param judgementSub 题目信息
     * @return 结果
     */
    @Override
    public JudgementSub get(JudgementSub judgementSub) {
        return null;
    }

    /**
     * 查询题目管理数据
     * 
     * @param judgementSub 题目信息
     * @return 题目信息集合
     */
    @Override
    // @DataScope(deptAlias = "d", userAlias = "u")
    public List<JudgementSub> findList(JudgementSub judgementSub)
    {
        return judgeMapper.findList(judgementSub);
    }

    /**
     * 根据题目ID查询信息
     * 
     * @param id 题目ID
     * @return 题目信息
     */
    @Override
    public JudgementSub findListById(Long id)
    {
        return judgeMapper.findListById(id);
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
    // public SubjectJudgement getNextByCurrentIdAndType(Long examinationId, Long previousId, Integer nextType) {
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
    //     return SubjectUtil.subjectJudgementToDto(this.get(id));
    // }

    // /**
    //  * 根据ID批量查询
    //  *
    //  * @param ids ids
    //  * @return List<SubjectDto>
    //  */
    // @Override
    // public List<SubjectDto> findSubjectListById(Long[] ids) {
    //     return SubjectUtil.subjectJudgementToDto(this.findListById(ids));
    // }

    //---------------------------------------------------

    /**
     * 查询考试题目表管理数据
     * 
     * @param examinationSubject 考试题目表信息
     * @return 考试题目表信息集合
     */
    @Override
    public List<JudgementSub> findSubjectJudgesByExaminationId(Long examinationId)
    {
        return judgeMapper.findSubjectJudgesByExaminationId(examinationId);
    }

    /**
     * 新增保存题目信息
     * 
     * @param judgementSub 题目信息
     * @return 结果
     */
    @Override
    // @Transactional
    public int insert(JudgementSub judgementSub)
    {
        return judgeMapper.insert(judgementSub);
    }

    /**
     * 修改保存题目信息
     * 
     * @param judgementSub 题目信息
     * @return 结果
     */
    @Override
    // @Transactional
    public int update(JudgementSub judgementSub)
    {
        return judgeMapper.update(judgementSub);
        
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
        return judgeMapper.delete(id);
    }

    

    // /**
    //  * 删除题目管理信息
    //  * 
    //  * @param ids 题目ID
    //  * @return 结果
    //  */
    // public int deleteAll(Long[] ids)
    // {
    //     return subjectJudgementMapper.deleteAll(ids);
    // }

    // /**
    //  * 删除题目管理信息
    //  * 
    //  * @param id 题目ID
    //  * @return 结果
    //  */
    // public int physicalDelete(Long id)
    // {
    //     return subjectJudgementMapper.physicalDelete(id);
    // }

    // /**
    //  * 删除题目管理信息
    //  * 
    //  * @param ids 题目ID
    //  * @return 结果
    //  */
    // public int physicalDeleteAll(Long[] ids)
    // {
    //     return subjectJudgementMapper.physicalDeleteAll(ids);
    // }

}


/**
 * IJudgementSubService具体实现类
 * 要使用IService，impl文件就要继承ServiceImpl
 */
// @Service
// public class JudgementSubService extends ServiceImpl<JudgementSubMapper, JudgementSub> 
// implements IJudgementSubService 
// {
//     @Resource
//     JudgementSubMapper judgeMapper;

//     /**
//      * 查询选择题列表
//      * 
//      * @param judgementSub 选择题信息
//      * @return 选择题信息集合
//      */
//     @Override
//     @DataScope(deptAlias = "d", userAlias = "u")
//     public List<JudgementSub> findList(JudgementSub judgementSub){
//         return judgeMapper.findList(judgementSub);
//     }

//     /**
//      * 根据选择题ID查询信息
//      * 
//      * @param id 选择题ID
//      * @return 选择题信息
//      */
//     @Override
//     public JudgementSub findListById(Long id)
//     {
//         return judgeMapper.findListById(id);
//     }


//     /**
//      * 修改保存选择题信息
//      * 
//      * @param judgementSub 选择题信息
//      * @return 结果
//      */
//     @Override
//     public int update(JudgementSub judgementSub)
//     {
//         return judgeMapper.update(judgementSub);
        
//     }
//     // 
//     // @Override
//     // public int update(JudgementSub judgementSub) {


//     //     return judgeMapper.updateById(judgementSub);
//     // }

// }