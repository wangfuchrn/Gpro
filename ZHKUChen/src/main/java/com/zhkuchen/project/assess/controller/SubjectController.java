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

import com.zhkuchen.common.constant.UserConstants;
import com.zhkuchen.common.utils.SecurityUtils;
import com.zhkuchen.framework.aspectj.lang.annotation.Log;
import com.zhkuchen.framework.aspectj.lang.enums.BusinessType;
import com.zhkuchen.framework.web.controller.BaseController;
import com.zhkuchen.framework.web.domain.AjaxResult;
import com.zhkuchen.project.assess.domain.SubjectCategory;
import com.zhkuchen.project.assess.service.SubjectCategoryService;

/**
 * 题库类别信息
 * 
 */
@RestController
@RequestMapping("/assess/subjectCategory")
public class SubjectController extends BaseController
{
    @Autowired
    private SubjectCategoryService subjectCategoryService;

    /**
     * 获取题库类别列表
     */
    @PreAuthorize("@ss.hasPermi('assess:subjectCategory:list')")
    @GetMapping("/list")
    public AjaxResult list(SubjectCategory subjectCategory)
    {
        List<SubjectCategory> subjectCategorys = subjectCategoryService.selectSubjectCategoryList(subjectCategory);
        return AjaxResult.success(subjectCategorys);
    }

    /**
     * 根据题库编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('assess:subjectCategory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return AjaxResult.success(subjectCategoryService.selectSubjectCategoryById(id));
    }

    /**
     * 获取题库下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(SubjectCategory subjectCategory)
    {
        List<SubjectCategory> subjectCategorys = subjectCategoryService.selectSubjectCategoryList(subjectCategory);
        return AjaxResult.success(subjectCategoryService.buildSubjectCategoryTreeSelect(subjectCategorys));
    }

    // /**
    //  * 加载对应角色题库列表树
    //  */
    // @GetMapping(value = "/roleSubjectCategoryTreeselect/{deptId}")
    // public AjaxResult roleSubjectCategoryTreeselect(@PathVariable("deptId") Long deptId)
    // {
    //     List<SubjectCategory> subjectCategorys = subjectCategoryService.selectSubjectCategoryList(new SubjectCategory());
    //     AjaxResult ajax = AjaxResult.success();
    //     ajax.put("checkedKeys", subjectCategoryService.selectSubjectCategoryListByDeptId(deptId));
    //     ajax.put("subjectCategorys", subjectCategoryService.buildSubjectCategoryTreeSelect(subjectCategorys));
    //     return ajax;
    // }

    /**
     * 新增题库
     */
    @PreAuthorize("@ss.hasPermi('assess:subjectCategory:add')")
    @Log(title = "题库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SubjectCategory subjectCategory)
    {
        if (UserConstants.NOT_UNIQUE.equals(subjectCategoryService.checkCategoryNameUnique(subjectCategory)))
        {
            return AjaxResult.error("新增题库'" + subjectCategory.getCategoryName() + "'失败，题库名称已存在");
        }
        subjectCategory.setCreateBy(SecurityUtils.getUsername());
        return toAjax(subjectCategoryService.insertSubjectCategory(subjectCategory));
    }

    /**
     * 修改题库
     */
    @PreAuthorize("@ss.hasPermi('assess:subjectCategory:edit')")
    @Log(title = "题库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SubjectCategory subjectCategory)
    {
        if (UserConstants.NOT_UNIQUE.equals(subjectCategoryService.checkCategoryNameUnique(subjectCategory)))
        {
            return AjaxResult.error("修改题库'" + subjectCategory.getCategoryName() + "'失败，题库名称已存在");
        }
        else if (subjectCategory.getParentId().equals(subjectCategory.getId()))
        {
            return AjaxResult.error("修改题库'" + subjectCategory.getCategoryName() + "'失败，上级题库不能是自己");
        }
        subjectCategory.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(subjectCategoryService.updateSubjectCategory(subjectCategory));
    }

    /**
     * 删除题库
     */
    @PreAuthorize("@ss.hasPermi('assess:subjectCategory:remove')")
    @Log(title = "题库管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{id}")
    public AjaxResult remove(@PathVariable Long id)
    {
        if (subjectCategoryService.hasChildById(id))
        {
            return AjaxResult.error("存在下级题库,不允许删除");
        }
        // if (subjectCategoryService.checkDeptExistUser(id))
        // {
        //     return AjaxResult.error("题库存在题目,不允许删除");
        // }
        return toAjax(subjectCategoryService.deleteSubjectById(id));
    }
}