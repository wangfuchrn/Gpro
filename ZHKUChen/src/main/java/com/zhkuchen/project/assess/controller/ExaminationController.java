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
import com.zhkuchen.common.utils.StringUtils;
import com.zhkuchen.framework.aspectj.lang.annotation.Log;
import com.zhkuchen.framework.aspectj.lang.enums.BusinessType;
import com.zhkuchen.framework.security.LoginUser;
import com.zhkuchen.framework.security.service.TokenService;
import com.zhkuchen.framework.web.controller.BaseController;
import com.zhkuchen.framework.web.domain.AjaxResult;
import com.zhkuchen.framework.web.page.TableDataInfo;
import com.zhkuchen.project.assess.domain.ChoicesSub;
import com.zhkuchen.project.assess.domain.Examination;
import com.zhkuchen.project.assess.domain.ExaminationSubject;
import com.zhkuchen.project.assess.domain.JudgementSub;
import com.zhkuchen.project.assess.domain.ShortAnswerSub;
import com.zhkuchen.project.assess.domain.TestTask;
import com.zhkuchen.project.assess.service.IChoicesSubService;
import com.zhkuchen.project.assess.service.IExaminationService;
import com.zhkuchen.project.assess.service.IExaminationSubjectService;
import com.zhkuchen.project.assess.service.IJudgementSubService;
import com.zhkuchen.project.assess.service.IShortAnswerSubService;
import com.zhkuchen.project.assess.service.ITestTaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * 题目类别信息
 * 
 */
@Slf4j
@RestController
@RequestMapping("/assess/examManage")
public class ExaminationController extends BaseController
{
    @Autowired
    private IExaminationService examinationService;

    @Autowired
    private IExaminationSubjectService examinationSubjectService;

    @Autowired
    private IChoicesSubService choicesSubService;


    @Autowired
    private IJudgementSubService judgementSubService;

    
    @Autowired
    private IShortAnswerSubService shortAnswerSubService;

    @Autowired
    private ITestTaskService testTaskService;

    @Autowired
    TokenService tokenService;

    /**
     * 获取题目类别列表
     */
    @PreAuthorize("@ss.hasPermi('assess:examManage:list')")
    @GetMapping("/list")
    public TableDataInfo list(Examination examination)
    {
        startPage();
        List<Examination> list = examinationService.findList(examination);
        return getDataTable(list);
    }

    /**
     * 获取题目类别列表
     */
    /*@PreAuthorize("@ss.hasPermi('assess:examManage:list')")
    @GetMapping("/list")
    public TableDataInfo list(Examination examination,HttpServletRequest request)
    {
        startPage();
        logger.info("传入参数:{}",JSONObject.toJSONString(examination));
        if(examination.getDeptId()==null){
    		Long myDeptId = getMyDeptId(request);
			log.warn("参数未传deptId！从系统获取到的是：{}",myDeptId);
    		examination.setDeptId(myDeptId);
    	}
    	
        
        List<Examination> list = examinationService.findList(examination);
        return getDataTable(list);
    }
    
    //2020年4月6日01:44:50 获取当前部门id再插入数据库查询参数
    private Long getMyDeptId(HttpServletRequest request) {
		// TODO Auto-generated method stub
    	LoginUser me =tokenService.getLoginUser(request);
    	Long myDeptId = me.getUser().getDeptId();
    	log.info("当前登陆用户的部门id为",myDeptId);
		return myDeptId;
	}*/

	/**
     * 根据题目编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('assess:examManage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(examinationService.findListById(id));
    }

    /**
     * 根据题目编号获取详细信息
     */
    @GetMapping(value = "/exam/{id}")
    public AjaxResult getExamInfoById(@PathVariable Long id)
    {
        return AjaxResult.success(examinationService.findById(id));
    }

    /**
     * 新增题目
     */
    @PreAuthorize("@ss.hasPermi('assess:examManage:add')")
    @Log(title = "添加测评", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Examination examination)
    {
        examination.setCreateBy(SecurityUtils.getUsername());
        return toAjax(examinationService.insert(examination));
    }

    /**
     * 从题库中新增
     */
    @Log(title = "从题库中新增", businessType = BusinessType.INSERT)
    @PostMapping("/addSubjects")
    public AjaxResult addSubjects(@Validated @RequestBody ExaminationSubject examinationSubject)
    {
        return toAjax(examinationSubjectService.insert(examinationSubject));
    }

    /**
     * 修改题目
     */
    @PreAuthorize("@ss.hasPermi('assess:examManage:edit')")
    @Log(title = "修改测评", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Examination examination)
    {
        examination.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(examinationService.update(examination));
    }

    /**
     * 修改测评状态
     */
    @PreAuthorize("@ss.hasPermi('assess:examManage:updateStatus')")
    @Log(title = "修改测评状态", businessType = BusinessType.UPDATE)
    @PutMapping("/updateStatus/{id}")
    public AjaxResult updateExaminationStatus(@Validated @RequestBody Long id)
    {
        // examination.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(examinationService.updateExamStatus(id));
    }

    /**
     * 删除题目
     */
    @PreAuthorize("@ss.hasPermi('assess:examManage:remove')")
    @Log(title = "删除测评", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        return toAjax(examinationService.delete(id));
    }

    /**
     * 获取题目类别列表
     */
    @PreAuthorize("@ss.hasPermi('assess:examManage:choices:list')")
    @GetMapping("/choices/list/{examinationId}")
    public TableDataInfo getchoicesListByExaminationIdAndSubjectId(@PathVariable Long examinationId)
    {
        startPage();
        List<ChoicesSub> list = choicesSubService.findSubjectsByExaminationId(examinationId);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('assess:examManage:judgement:list')")
    @GetMapping("/judgement/list/{examinationId}")
    public TableDataInfo getjudgeListByExaminationIdAndSubjectId(@PathVariable Long examinationId)
    {
        startPage();
        List<JudgementSub> list = judgementSubService.findSubjectJudgesByExaminationId(examinationId);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('assess:examManage:shortAnswer:list')")
    @GetMapping("/shortAnswer/list/{examinationId}")
    public TableDataInfo getshortAnswerListByExaminationIdAndSubjectId(@PathVariable Long examinationId)
    {
        startPage();
        List<ShortAnswerSub> list = shortAnswerSubService.findSubjectShortAnswerByExaminationId(examinationId);
        return getDataTable(list);
    }

    /**
     * 发布试卷
     */
    @PreAuthorize("@ss.hasPermi('assess:examManage:testTaskAdd')")
    @Log(title = "添加考试任务", businessType = BusinessType.INSERT)
    @PostMapping("/testTask")
    public AjaxResult addTestTask(@Validated @RequestBody TestTask testTask)
    {
        testTask.setCreateBy(SecurityUtils.getUsername());
        return toAjax(testTaskService.insert(testTask));
    }

    /**
     * 根据考试id获取全部题目
     */
    // @PreAuthorize("@ss.hasPermi('assess:examManage:query')")
    @GetMapping(value = "/subjects/{id}")
    public AjaxResult getAllSubjectByExaminationId(@PathVariable Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        if(StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG, examinationService.findListById(id));
            ajax.put("choices", choicesSubService.findSubjectsByExaminationId(id));
            ajax.put("judges", judgementSubService.findSubjectJudgesByExaminationId(id));
            ajax.put("shortanswers", shortAnswerSubService.findSubjectShortAnswerByExaminationId(id));
        }
        return ajax;
    }

    /**
     * 删除试卷中的题目
     */
    // @PreAuthorize("@ss.hasPermi('assess:subjects:remove')")
    @Log(title = "删除试卷中的题目", businessType = BusinessType.DELETE)
    @DeleteMapping("/deleteExamSubject/{id}")
    public AjaxResult removeExamSubjectById(@PathVariable Long id)
    {
        return toAjax(examinationSubjectService.deleteBySubjectId(id));
    }  
}