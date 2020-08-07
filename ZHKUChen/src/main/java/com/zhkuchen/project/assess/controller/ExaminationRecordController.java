package com.zhkuchen.project.assess.controller;

import java.time.LocalDateTime;
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
import com.zhkuchen.project.assess.domain.ExaminationRecord;
import com.zhkuchen.project.assess.service.IExaminationRecordService;

import lombok.extern.slf4j.Slf4j;

/**
 * 考试任务信息
 * 
 */
@Slf4j
@RestController
@RequestMapping("/exam/examinationRecord")
public class ExaminationRecordController extends BaseController
{
    @Autowired
    private IExaminationRecordService examinationRecordService;

    /**
     * 获取考试记录类别列表
     */
    // @PreAuthorize("@ss.hasPermi('assess:examManage:list')")
    @GetMapping("/list")
    public TableDataInfo list(ExaminationRecord examinationRecord)
    {
        startPage();
        List<ExaminationRecord> list = examinationRecordService.findList(examinationRecord);
        return getDataTable(list);
    }

    /**
     * 根据考试记录编号获取详细信息
     */
    // @PreAuthorize("@ss.hasPermi('assess:examManage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(examinationRecordService.findListById(id));
    }

    /**
     * 根据考试记录编号获取详细信息
     */
    @GetMapping(value = "/recordList")
    public TableDataInfo getSubmitedInfoByUserId(ExaminationRecord examinationRecord)
    {
        startPage();
        List<ExaminationRecord> list = examinationRecordService.findSubmitedByUserId(examinationRecord);
        return getDataTable(list);
    }

    

    /**
     * 根据编号获取详细信息
     */
    // @PreAuthorize("@ss.hasPermi('assess:examManage:query')")
    @GetMapping(value = "/examRecordInfo")
    public AjaxResult getExamRecordInfo(ExaminationRecord examinationRecord)
    {
        return AjaxResult.success(examinationRecordService.findByUserIdAndExamIdAndDeptId(examinationRecord));
    }

    /**
     * 新增考试记录
     */
    // @PreAuthorize("@ss.hasPermi('assess:examManage:add')")
    @Log(title = "添加考试记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody ExaminationRecord examinationRecord)
    {
        examinationRecord.setCreateBy(SecurityUtils.getUsername());
        return toAjax(examinationRecordService.insert(examinationRecord));
    }

    /**
     * 修改考试记录
     */
    // @PreAuthorize("@ss.hasPermi('assess:examManage:edit')")
    // @Log(title = "修改考试记录", businessType = BusinessType.UPDATE)
    // @PutMapping
    // public AjaxResult edit(@Validated @RequestBody ExaminationRecord examinationRecord)
    // {
    //     examinationRecord.setUpdateBy(SecurityUtils.getUsername());
    //     return toAjax(examinationRecordService.update(examinationRecord));
    // }

    /**
     * 修改考试记录
     */
    // @PreAuthorize("@ss.hasPermi('assess:examManage:edit')")
    @Log(title = "修改考试记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateSubmitStatus(@Validated @RequestBody Long id)
    {
        // examinationRecord.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(examinationRecordService.updateSubmitStatus(id));
    }

    /**
     * 修改考试记录
     */
    // @PreAuthorize("@ss.hasPermi('assess:examManage:edit')")
    @PutMapping("/updateMarkStatus")
    public AjaxResult updateMarkStatus(@Validated @RequestBody Long id)
    {
        // examinationRecord.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(examinationRecordService.updateMarkStatus(id));
    }

    /**
     * 删除考试记录
     */
    // @PreAuthorize("@ss.hasPermi('assess:examManage:remove')")
    @Log(title = "删除考试记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(examinationRecordService.delete(id));
    }

}