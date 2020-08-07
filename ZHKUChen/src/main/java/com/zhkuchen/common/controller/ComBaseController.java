package com.zhkuchen.common.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhkuchen.framework.web.domain.BaseEntity;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

public class ComBaseController<M extends BaseEntity,S extends IService<?>> {

    @Resource
    public IService<M> service;

    @GetMapping("/list")
    public List<M> findAll(){
        return service.list();
    }

    @PostMapping("edit")
    public boolean edit(@RequestBody M m){
        return service.saveOrUpdate(m);
    }

    @DeleteMapping("/{id}")
    public boolean remove(@PathVariable Serializable id){
        return service.removeById(id);
    }

}