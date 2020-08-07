package com.zhkuchen.project.assess.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhkuchen.project.assess.domain.ChoicesOptionSub;

/**
 * 判断题题目Service
 *
 */
public interface IChoicesOptionSubService 
{
    /**
     * 查询题目管理数据
     * 
     * @param choicesOptionSub 题目信息
     * @return 题目信息集合
     */
    public List<ChoicesOptionSub> selectBySubjectChoicesId(Long subjectChoicesId);

    /**
     * 查询题目管理数据
     * 
     * @param choicesOptionSub 题目信息
     * @return 题目信息集合
     */
    public List<ChoicesOptionSub> findList(ChoicesOptionSub choicesOptionSub);

    /**
     * 根据题目ID查询信息
     * 
     * @param id 题目ID
     * @return 题目信息
     */
    public ChoicesOptionSub findListById(Long id);

    /**
     * 新增保存题目信息
     * 
     * @param choicesOptionSub 题目信息
     * @return 结果
     */
    public int insert(ChoicesOptionSub choicesOptionSub);

    /**
     * 新增保存题目信息
     * 
     * @param choicesOptionSub 题目信息
     * @return 结果
     */
    public int insertBatch(List<ChoicesOptionSub> choicesOptionSub);

    /**
     * 修改保存题目信息
     * 
     * @param choicesOptionSub 题目信息
     * @return 结果
     */
    public int update(ChoicesOptionSub choicesOptionSub);

    /**
     * 删除题目管理信息
     * 
     * @param id 题目ID
     * @return 结果
     */
    public int deleteBySubjectChoicesId(ChoicesOptionSub choicesOptionSub);

    /**
     * 删除题目管理信息
     * 
     * @param id 题目ID
     * @return 结果
     */
    public int delete(Long id);

    /**
     * 删除题目管理信息
     * 
     * @param ids 题目ID
     * @return 结果
     */
    public int deleteAll(Long[] ids);

    // /**
    //  * 删除题目管理信息
    //  * 
    //  * @param ids 题目ID
    //  * @return 结果
    //  */
    // public int physicalDeleteAll(Long[] ids);
  
    }  
// /**
//  * IChoicesOptionSubService
//  * 提供方法
//  * IService 封装IService口，进一步封装 CRUD 
//  * 采用 get 查询单行 remove 删除 list 查询集合 page 分页 前缀命名方式区分 Mapper 层避免混淆
//  * 泛型 T 为任意实体对象
//  * 建议如果存在自定义通用 Service 方法的可能，请创建自己的 IBaseService 继承 Mybatis-Plus 提供的基类
//  * 对象 Wrapper 为 条件构造器
//  */
// public interface IChoicesOptionSubService extends IService<ChoicesOptionSub> 
// {
//     public List<ChoicesOptionSub> selectBySubjectChoicesId(Long subjectChoicesId);
    
// }