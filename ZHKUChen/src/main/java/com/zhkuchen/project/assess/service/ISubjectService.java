package com.zhkuchen.project.assess.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhkuchen.project.assess.domain.Subject;

/**
 * IChoicesOptionSubService
 * 提供方法
 * IService 封装IService口，进一步封装 CRUD 
 * 采用 get 查询单行 remove 删除 list 查询集合 page 分页 前缀命名方式区分 Mapper 层避免混淆
 * 泛型 T 为任意实体对象
 * 建议如果存在自定义通用 Service 方法的可能，请创建自己的 IBaseService 继承 Mybatis-Plus 提供的基类
 * 对象 Wrapper 为 条件构造器
 */
public interface ISubjectService
{
  public List<Subject> getSubjectList();
//  public List<Subject> findList(Object[] objects);
  //public int delete(Long id);
  //public int deleteAll(Collection<? extends Serializable> ids);
}