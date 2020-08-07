package com.zhkuchen.project.assess.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zhkuchen.common.utils.SecurityUtils;
import com.zhkuchen.framework.aspectj.lang.annotation.Log;
import com.zhkuchen.framework.aspectj.lang.enums.BusinessType;
import com.zhkuchen.framework.security.LoginUser;
import com.zhkuchen.framework.security.service.TokenService;
import com.zhkuchen.framework.web.controller.BaseController;
import com.zhkuchen.framework.web.domain.AjaxResult;
import com.zhkuchen.framework.web.page.TableDataInfo;
import com.zhkuchen.project.assess.domain.TestTask;
import com.zhkuchen.project.assess.service.ITestTaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * 考试任务信息
 * 
 */
@Slf4j
@RestController
@RequestMapping("/exam/testTask")
public class TestTaskController extends BaseController
{
    @Autowired
    private ITestTaskService testTaskService;

    /**
     * 获取题目类别列表
     */
    @PreAuthorize("@ss.hasPermi('exam:testTask:list')")
    @GetMapping("/list")
    public TableDataInfo list(TestTask testTask)
    {
        startPage();
        List<TestTask> list = testTaskService.findList(testTask);
        return getDataTable(list);
    }
    
    /**
     * 根据题目编号获取详细信息
     */
    // @PreAuthorize("@ss.hasPermi('exam:testTask:query')")
    @GetMapping(value = "/user/list")
    public TableDataInfo getExamByUserPhonenumber(TestTask testTask)
    {
        startPage();
        List<TestTask> list = testTaskService.findListByUserPhonenumber(testTask);
        return getDataTable(list);
    }

    /**
     * 根据题目编号获取详细信息
     */
    // @PreAuthorize("@ss.hasPermi('exam:testTask:query')")
    @GetMapping(value = "/user/writtenList")
    public TableDataInfo findWrittenListByUserPhonenumber(TestTask testTask)
    {
        startPage();
        List<TestTask> list = testTaskService.findWrittenListByUserPhonenumber(testTask);
        return getDataTable(list);
    }

    /**
     * 根据题目编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('exam:testTask:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(testTaskService.findListById(id));
    }

    /**
     * 根据题目编号获取详细信息
     */
    @GetMapping(value = "/getTestTaskId")
    public AjaxResult getTestTaskId(TestTask testTask)
    {
        return AjaxResult.success(testTaskService.findByDeptIdAndExaminationIdAndUserPhone(testTask));
    }


    /**
     * 修改题目
     */
    // @PreAuthorize("@ss.hasPermi('exam:testTask:edit')")
    @Log(title = "修改考试任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Long id)
    {
        return toAjax(testTaskService.update(id));
    }

}