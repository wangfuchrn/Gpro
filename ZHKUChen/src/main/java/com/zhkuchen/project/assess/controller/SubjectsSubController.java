package com.zhkuchen.project.assess.controller;

import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.zhkuchen.common.constant.UserConstants;
import com.zhkuchen.common.utils.SecurityUtils;
import com.zhkuchen.common.utils.ServletUtils;
import com.zhkuchen.common.utils.StringUtils;
import com.zhkuchen.common.utils.poi.ExcelUtil;
import com.zhkuchen.framework.aspectj.lang.annotation.Log;
import com.zhkuchen.framework.aspectj.lang.enums.BusinessType;
import com.zhkuchen.framework.security.LoginUser;
import com.zhkuchen.framework.security.service.TokenService;
import com.zhkuchen.framework.web.controller.BaseController;
import com.zhkuchen.framework.web.domain.AjaxResult;
import com.zhkuchen.framework.web.page.TableDataInfo;
import com.zhkuchen.project.assess.domain.ChoicesOptionSub;
import com.zhkuchen.project.assess.domain.ChoicesSub;
import com.zhkuchen.project.assess.domain.JudgementSub;
import com.zhkuchen.project.assess.domain.ShortAnswerSub;
import com.zhkuchen.project.assess.service.IChoicesOptionSubService;
import com.zhkuchen.project.assess.service.IChoicesSubService;
import com.zhkuchen.project.assess.service.IJudgementSubService;
import com.zhkuchen.project.assess.service.IShortAnswerSubService;


/**
 * 题目controller
 * 
 */
@RestController
@RequestMapping("/assess")
public class SubjectsSubController extends BaseController
{
    @Autowired
    private IJudgementSubService judgementSubService;

    @Autowired
    private IShortAnswerSubService shortAnswerSubService;

    @Autowired
    private IChoicesSubService choicesSubService;

    @Autowired
    private IChoicesOptionSubService choicesOptionSubService;

    /**
     * 获取列表
     */
    @PreAuthorize("@ss.hasPermi('assess:judgement:list')")
    @GetMapping("/judgement/list")
    public TableDataInfo subjectjudgeList(JudgementSub judgementSub) {
        startPage();
        List<JudgementSub> list = judgementSubService.findList(judgementSub);
        return getDataTable(list);
    }

    /**
     * 根据题目编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('assess:judgement:query')")
    @GetMapping(value = "/judgement/{id}")
    public AjaxResult getjudgeInfo(@PathVariable Long id)
    {

        return AjaxResult.success(judgementSubService.findListById(id));
    }

    /**
     * 增加题目
     */
    @PreAuthorize("@ss.hasPermi('assess:judgement:add')")
    @Log(title = "添加题目", businessType = BusinessType.INSERT)
    @PostMapping("/judgement")
    public AjaxResult addjudge(@Validated @RequestBody JudgementSub judgementSub)
    {
        judgementSub.setCreateBy(SecurityUtils.getUsername());
        return toAjax(judgementSubService.insert(judgementSub));
    }

    /**
     * 修改题目
     */
    @PreAuthorize("@ss.hasPermi('assess:judgement:edit')")
    @Log(title = "修改题目", businessType = BusinessType.UPDATE)
    @PutMapping("/judgement")
    public AjaxResult editjudge(@Validated @RequestBody JudgementSub judgementSub)
    {
        judgementSub.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(judgementSubService.update(judgementSub));
    }

    /**
     * 删除题目
     */
    @PreAuthorize("@ss.hasPermi('assess:judgement:remove')")
    @Log(title = "题目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/judgement/{id}")
    public AjaxResult removejudge(@PathVariable Long id)
    {
        return toAjax(judgementSubService.delete(id));
    }

    //-------------------------------------------------------

    /**
     * 获取列表
     */
    @PreAuthorize("@ss.hasPermi('assess:shortAnswer:list')")
    @GetMapping("/shortAnswer/list")
    public TableDataInfo subjectshortAnswerList(ShortAnswerSub shortAnswerSub) {
        startPage();
        List<ShortAnswerSub> list = shortAnswerSubService.findList(shortAnswerSub);
        return getDataTable(list);
    }

    /**
     * 根据题目编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('assess:shortAnswer:query')")
    @GetMapping(value = "/shortAnswer/{id}")
    public AjaxResult getshortAnswerInfo(@PathVariable Long id)
    {
        return AjaxResult.success(shortAnswerSubService.findListById(id));
    }

    /**
     * 增加题目
     */
    @PreAuthorize("@ss.hasPermi('assess:shortAnswer:add')")
    @Log(title = "增加题目", businessType = BusinessType.INSERT)
    @PostMapping("/shortAnswer")
    public AjaxResult addshortAnswer(@Validated @RequestBody ShortAnswerSub shortAnswerSub)
    {
        shortAnswerSub.setCreateBy(SecurityUtils.getUsername());
        return toAjax(shortAnswerSubService.insert(shortAnswerSub));
    }

    /**
     * 修改题目
     */
    @PreAuthorize("@ss.hasPermi('assess:shortAnswer:edit')")
    @Log(title = "修改题目", businessType = BusinessType.UPDATE)
    @PutMapping("/shortAnswer")
    public AjaxResult editshortAnswer(@Validated @RequestBody ShortAnswerSub shortAnswerSub)
    {
        shortAnswerSub.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(shortAnswerSubService.update(shortAnswerSub));
    }

    /**
     * 删除题目
     */
    @PreAuthorize("@ss.hasPermi('assess:shortAnswer:remove')")
    @Log(title = "题目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/shortAnswer/{id}")
    public AjaxResult removeshortAnswer(@PathVariable Long id)
    {
        return toAjax(shortAnswerSubService.delete(id));
    }    

    //-------------------选择题------------------------------------
    
    /**
     * 获取列表
     */
    @PreAuthorize("@ss.hasPermi('assess:choices:list')")
    @GetMapping("/choices/list")
    public TableDataInfo subjectchoicesSubList(ChoicesSub choicesSub) {
        startPage();
        List<ChoicesSub> list = choicesSubService.findList(choicesSub);
        return getDataTable(list);
    }

    /**
     * 根据题目编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('assess:choices:query')")
    @GetMapping(value = "/choices/{id}")
    public AjaxResult getchoicesSubInfo(@PathVariable Long id)
    {
        // return AjaxResult.success(choicesSubService.findListById(id));
        AjaxResult ajax = AjaxResult.success();
        if(StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG, choicesSubService.findListById(id));
            ajax.put("optionsId", choicesOptionSubService.selectBySubjectChoicesId(id));
        }
        return ajax;
    }

    /**
     * 根据题目编号获取选项详细信息
     */
    @GetMapping(value = "/choicesoptions/{subjectChoicesId}")
    public AjaxResult getchoicesOptionsInfo(@PathVariable Long subjectChoicesId)
    {
        return AjaxResult.success(choicesOptionSubService.selectBySubjectChoicesId(subjectChoicesId));
    }

    /**
     * 增加题目
     */
    @PreAuthorize("@ss.hasPermi('assess:choices:add')")
    @Log(title = "增加题目", businessType = BusinessType.INSERT)
    @PostMapping("/choices")
    public AjaxResult addchoicesSub(@Validated @RequestBody ChoicesSub choicesSub)
    {
        choicesSub.setCreateBy(SecurityUtils.getUsername());
        return toAjax(choicesSubService.insert(choicesSub));
    }


    /**
     * 修改题目
     */
    @PreAuthorize("@ss.hasPermi('assess:choices:edit')")
    @Log(title = "修改题目", businessType = BusinessType.UPDATE)
    @PutMapping("/choices")
    public AjaxResult editchoicesSub(@Validated @RequestBody ChoicesSub choicesSub)
    {
        choicesSub.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(choicesSubService.update(choicesSub));
    }

    /**
     * 删除题目
     */
    @PreAuthorize("@ss.hasPermi('assess:choices:remove')")
    @Log(title = "题目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/choices/{id}")
    public AjaxResult removechoicesSub(@PathVariable Long id)
    {
        return toAjax(choicesSubService.delete(id));
    }   

    /**
     * 根据题目ids查询列表
     */
    @PreAuthorize("@ss.hasPermi('assess:choices:listByids')")
    @GetMapping("/choices/listByids")
    public TableDataInfo subjectchoicesSubList(Long[] ids) {
        startPage();
        List<ChoicesSub> list = choicesSubService.getById(ids);
        return getDataTable(list);
    }

    //-------------------选择题选项------------------------------------

    // /**
    //  * 获取列表
    //  */
    // @PreAuthorize("@ss.hasPermi('assess:choiceOptions:list')")
    // @GetMapping("/choiceOptions/list")
    // public TableDataInfo subjectshortAnswerList(ShortAnswerSub shortAnswerSub) {
    //     startPage();
    //     List<ShortAnswerSub> list = shortAnswerSubService.findList(shortAnswerSub);
    //     return getDataTable(list);
    // }

    /**
     * 根据题目编号获取详细信息
     */
    // @PreAuthorize("@ss.hasPermi('assess:choiceOptions:query')")
    // @GetMapping("/choiceOptions")
    // public AjaxResult getChoiceOptionsInfo(@PathVariable Long subjectChoicesId)
    // {
    //     return AjaxResult.success(choicesOptionSubService.selectBySubjectChoicesId(subjectChoicesId));
    //     // return AjaxResult.success(choicesOptionSubService.selectBySubjectChoicesId(subjectChoicesId));
    // }

    /**
     * 增加题目
     */
    @PreAuthorize("@ss.hasPermi('assess:choiceOptions:add')")
    @Log(title = "增加选择题选项", businessType = BusinessType.INSERT)
    @PostMapping("/choiceOptions")
    public AjaxResult addChoiceOptions(@Validated @RequestBody ChoicesOptionSub choicesOptionSub)
    {
        choicesOptionSub.setCreateBy(SecurityUtils.getUsername());
        return toAjax(choicesOptionSubService.insert(choicesOptionSub));
    }

    /**
     * 修改题目
     */
    @PreAuthorize("@ss.hasPermi('assess:choiceOptions:edit')")
    @Log(title = "修改选择题选项", businessType = BusinessType.UPDATE)
    @PutMapping("/choiceOptions")
    public AjaxResult editChoiceOptions(@Validated @RequestBody ChoicesOptionSub choicesOptionSub)
    {
        choicesOptionSub.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(choicesOptionSubService.update(choicesOptionSub));
    }

    /**
     * 删除题目
     */
    @PreAuthorize("@ss.hasPermi('assess:choiceOptions:remove')")
    @Log(title = "题目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/choiceOptions/{id}")
    public AjaxResult removeChoiceOptions(@PathVariable Long id)
    {
        return toAjax(choicesOptionSubService.delete(id));
    }    
}