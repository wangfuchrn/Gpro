package com.zhkuchen.project.assess.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhkuchen.project.assess.domain.JudgementSub;

/**
 * IJudgementSubService
 * 提供方法
 * IService 封装IService口，进一步封装 CRUD 
 * 采用 get 查询单行 remove 删除 list 查询集合 page 分页 前缀命名方式区分 Mapper 层避免混淆
 * 泛型 T 为任意实体对象
 * 建议如果存在自定义通用 Service 方法的可能，请创建自己的 IBaseService 继承 Mybatis-Plus 提供的基类
 * 对象 Wrapper 为 条件构造器
 */
public interface IJudgementSubService 
{ 
    /**
     * 根据ID查询
     *
     * @param id id
     * @return JudgementSub
     */
    public JudgementSub getById(Long id);

    /**
     * 根据ID查询
     *
     * @param judgementSub id
     * @return JudgementSub
     */
    public JudgementSub get(JudgementSub judgementSub);

    /**
     * 查询题目管理数据
     * 
     * @param judgementSub 题目信息
     * @return 题目信息集合
     */
    public List<JudgementSub> findList(JudgementSub judgementSub);

    /**
     * 根据ID查询
     *
     * @param id id
     * @return JudgementSub
     */
    public JudgementSub findListById(Long id);

    /**
     * 根据考试id查询题目id列表
     *
     * @param examinationId examinationId
     * @return List
     */
     public List<JudgementSub> findSubjectJudgesByExaminationId(Long examinationId);

    /**
     * 新增保存题目信息
     * 
     * @param judgementSub 题目信息
     * @return 结果
     */
    public int insert(JudgementSub judgementSub);

    /**
     * 修改保存题目信息
     * 
     * @param judgementSub 题目信息
     * @return 结果
     */
    public int update(JudgementSub judgementSub);

    /**
     * 删除题目管理信息
     * 
     * @param id 题目ID
     * @return 结果
     */
    public int delete(Long id);

    // /**
    //  * 批量删除题目信息
    //  * 
    //  * @param ids 题目信息
    //  * @return 结果
    //  */
    // public int deleteAll(Long[] ids);

    // /**
    //  * 批量删除题目信息
    //  * 
    //  * @param id 题目信息
    //  * @return 结果
    //  */
    // public int physicalDelete(Long id);

    // /**
    //  * 批量删除题目信息
    //  * 
    //  * @param ids 题目信息
    //  * @return 结果
    //  */
    // public int physicalDeleteAll(Long[] ids);
}
// public interface IJudgementSubService extends IService<JudgementSub> {

//     public List<JudgementSub> findList(JudgementSub judgementSub);

//     public JudgementSub findListById(Long id);
    
//     public int update(JudgementSub judgementSub);

//     //public int update(JudgementSub judgementSub);
// }