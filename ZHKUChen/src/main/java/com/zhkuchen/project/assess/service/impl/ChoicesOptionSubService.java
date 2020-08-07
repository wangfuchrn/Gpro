package com.zhkuchen.project.assess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhkuchen.project.assess.domain.ChoicesOptionSub;
import com.zhkuchen.project.assess.mapper.ChoicesOptionSubMapper;
import com.zhkuchen.project.assess.service.IChoicesOptionSubService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * IShortAnswerSubService具体实现类 要使用IService，impl文件就要继承ServiceImpl
 */
@Service
public class ChoicesOptionSubService implements IChoicesOptionSubService 
// public class ChoicesOptionSubService extends ServiceImpl<ChoicesOptionSubMapper, ChoicesOptionSub> implements IChoicesOptionSubService 
{
  @Autowired
  private ChoicesOptionSubMapper choicesOptionSubMapper;

  // @Override
  //   public List<ChoicesOptionSub> selectBySubjectChoicesId(Long subjectChoicesId){
  //       return choicesOptionSubMapper.selectBySubjectChoicesId(subjectChoicesId);
  //   }

   /**
    * 查询题目管理数据
    * 
    * @param subjectChoicesId 题目信息
    * @return 题目信息集合
    */
   @Override
   public List<ChoicesOptionSub> selectBySubjectChoicesId(Long subjectChoicesId){
       return choicesOptionSubMapper.selectBySubjectChoicesId(subjectChoicesId);
   }

    /**
     * 查询测评管理数据
     * 
     * @param choicesOptionSub 测评信息
     * @return 测评信息集合
     */
    @Override
    public List<ChoicesOptionSub> findList(ChoicesOptionSub choicesOptionSub)
    {
        return choicesOptionSubMapper.findList(choicesOptionSub);
    }

    /**
     * 根据测评ID查询信息
     * 
     * @param id 测评ID
     * @return 测评信息
     */
    @Override
    public ChoicesOptionSub findListById(Long id)
    {
        return choicesOptionSubMapper.findListById(id);
    }

    /**
     * 新增
     *
     * @param choicesOptionSub choicesOptionSub
     * @return int
     */
    @Override
    @Transactional
    public int insert(ChoicesOptionSub choicesOptionSub) {
        return choicesOptionSubMapper.insert(choicesOptionSub);
    }

    /**
     * 批量保存
     *
     * @param choicesOptionSub choicesOptionSub
     * @return int
     */
    @Override
    @Transactional
    public int insertBatch(List<ChoicesOptionSub> choicesOptionSub) {
        return choicesOptionSubMapper.insertBatch(choicesOptionSub);
    }

    /**
     * 更新题目
     *
     * @param choicesOptionSub choicesOptionSub
     * @return int
     */
    @Override
    @Transactional
    public int update(ChoicesOptionSub choicesOptionSub) {
        return choicesOptionSubMapper.update(choicesOptionSub);
    }

    /**
     * 删除题目
     *
     * @param choicesOptionSub choicesOptionSub
     * @return int
     */
    @Override
    @Transactional
    public int delete(Long id) {
        return choicesOptionSubMapper.delete(id);
    }

    /**
     * 根据选择题ID删除
     *
     * @param choicesOptionSub choicesOptionSub
     * @return int
     */
    @Override
    @Transactional
    public int deleteBySubjectChoicesId(ChoicesOptionSub choicesOptionSub) {
        return choicesOptionSubMapper.deleteBySubjectChoicesId(choicesOptionSub);
    }

    /**
     * 批量删除题目
     *
     * @param ids ids
     * @return int
     */
    @Override
    @Transactional
    public int deleteAll(Long[] ids) {
        return choicesOptionSubMapper.deleteAll(ids);
    }

    // /**
    //  * 物理批量删除
    //  *
    //  * @param ids ids
    //  * @return int
    //  */
    // @Transactional
    // public int physicalDeleteAll(Long[] ids) {
    //     return choicesOptionSubMapper.physicalDeleteAll(ids);
    // }


}