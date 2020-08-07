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
import com.zhkuchen.project.assess.domain.ExamAnswer;
import com.zhkuchen.project.assess.service.IExamAnswerService;

import lombok.extern.slf4j.Slf4j;

/**
 * 考试任务信息
 * 
 */
@Slf4j
@RestController
@RequestMapping("/exam/answer")
public class ExamAnswerController extends BaseController
{
    @Autowired
    private IExamAnswerService examAnswerService;

    /**
     * 获取考试答案类别列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ExamAnswer examAnswer)
    {
        startPage();
        List<ExamAnswer> list = examAnswerService.findList(examAnswer);
        return getDataTable(list);
    }

    /**
     * 根据题目编号获取详细信息
     */
    @GetMapping(value = "/bySubjectAndExamRecordId")
    public AjaxResult getAnswer(ExamAnswer examAnswer)
    {
        return AjaxResult.success(examAnswerService.getAnswerBySubjectAndExamRecordId(examAnswer));
    }

    /**
     * 根据题目编号获取详细信息
     */
    @GetMapping(value = "/get/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(examAnswerService.findById(id));
    }

    /**
     * 获取考试答案类别列表
     */
    @GetMapping("/listByExamRecordId")
    public TableDataInfo getAnswerByExamRecordId(ExamAnswer examAnswer)
    {
        startPage();
        List<ExamAnswer> list = examAnswerService.getAnswerByExamRecordId(examAnswer);
        return getDataTable(list);
    }

    /**
     * 新增题目
     */
    @Log(title = "添加考试答案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ExamAnswer examAnswer)
    {
        examAnswer.setCreateBy(SecurityUtils.getUsername());
        return toAjax(examAnswerService.insert(examAnswer));
    }

    /**
     * 修改考试答案
     */
    @Log(title = "修改考试答案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody ExamAnswer examAnswer)
    {
        examAnswer.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(examAnswerService.update(examAnswer));
    }

    /**
     * 删除题目
     */
    @Log(title = "删除考试答案", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(examAnswerService.delete(id));
    }


}