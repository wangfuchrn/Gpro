package com.zhkuchen.project.assess.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhkuchen.common.constant.UserConstants;
import com.zhkuchen.common.utils.StringUtils;
import com.zhkuchen.framework.aspectj.lang.annotation.DataScope;
import com.zhkuchen.framework.web.domain.TreeSelect;
import com.zhkuchen.project.assess.domain.SubjectCategory;
import com.zhkuchen.project.assess.mapper.SubjectCategoryMapper;
import com.zhkuchen.project.assess.service.SubjectCategoryService;

/**
 * 题库管理 服务实现
 * 
 */
@Service
public class SubjectCategoryServiceImpl implements SubjectCategoryService
{
    @Autowired
    private SubjectCategoryMapper subjectCategoryMapper;

     /**
     * 查询题库管理数据
     * 
     * @param subjectCategory 题库信息
     * @return 题库信息集合
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    public List<SubjectCategory> selectSubjectCategoryList(SubjectCategory subjectCategory)
    {
        return subjectCategoryMapper.selectSubjectCategoryList(subjectCategory);
    }

    /**
     * 构建前端所需要树结构
     * 
     * @param subjectCategorys 题库列表
     * @return 树结构列表
     */
    @Override
    public List<SubjectCategory> buildSubjectCategoryTree(List<SubjectCategory> subjectCategorys)
    {
        List<SubjectCategory> returnList = new ArrayList<SubjectCategory>();
        List<Long> tempList = new ArrayList<Long>();
        for (SubjectCategory subjectCategory : subjectCategorys)
        {
            tempList.add(subjectCategory.getId());
        }
        for (Iterator<SubjectCategory> iterator = subjectCategorys.iterator(); iterator.hasNext();)
        {
            SubjectCategory subjectCategory = (SubjectCategory) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(subjectCategory.getParentId()))
            {
                recursionFn(subjectCategorys, subjectCategory);
                returnList.add(subjectCategory);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = subjectCategorys;
        }
        return returnList;
    }

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param subjectCategorys 题库列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildSubjectCategoryTreeSelect(List<SubjectCategory> subjectCategorys)
    {
        List<SubjectCategory> subjectCategoryTrees = buildSubjectCategoryTree(subjectCategorys);
        return subjectCategoryTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }

//     /**
//      * 根据角色ID查询题库树信息
//      * 
//      * @param deptId 角色ID
//      * @return 选中题库列表
//      */
//     @Override
// //    @Deprecated
//     public List<Integer> selectSubjectCategoryListByDeptId(Long deptId)
//     {
//      // throw new IllegalStateException("未实现");
//        return subjectCategoryMapper.selectSubjectCategoryListByDeptId(deptId);
//     }

    /**
     * 根据题库ID查询信息
     * 
     * @param id 题库ID
     * @return 题库信息
     */
    @Override
    public SubjectCategory selectSubjectCategoryById(Long id)
    {
        return subjectCategoryMapper.selectSubjectCategoryById(id);
    }

    /**
     * 是否存在子节点
     * 
     * @param id 题库ID
     * @return 结果
     */
    @Override
    public boolean hasChildById(Long id)
    {
        int result = subjectCategoryMapper.hasChildById(id);
        return result > 0 ? true : false;
    }

    // /**
    //  * 查询题库是否存在用户
    //  * 
    //  * @param id 题库ID
    //  * @return 结果 true 存在 false 不存在
    //  */
    // @Override
    // public boolean checkSubjectCategoryExistSubject(Long id)
    // {
    //     int result = subjectCategoryMapper.checkSubjectCategoryExistSubject(id);
    //     return result > 0 ? true : false;
    // }

    /**
     * 校验题库名称是否唯一
     * 
     * @param subjectCategory 题库信息
     * @return 结果
     */
    @Override
    public String checkCategoryNameUnique(SubjectCategory subjectCategory)
    {
        Long id = StringUtils.isNull(subjectCategory.getId()) ? -1L : subjectCategory.getId();
        SubjectCategory info = subjectCategoryMapper.checkCategoryNameUnique(subjectCategory.getCategoryName(), subjectCategory.getParentId());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 新增保存题库信息
     * 
     * @param subjectCategory 题库信息
     * @return 结果
     */
    @Override
    public int insertSubjectCategory(SubjectCategory subjectCategory)
    {
        SubjectCategory info = subjectCategoryMapper.selectSubjectCategoryById(subjectCategory.getParentId());
        subjectCategory.setAncestry(info.getAncestry() + "," + subjectCategory.getParentId());
        return subjectCategoryMapper.insertSubjectCategory(subjectCategory);
    }

    /**
     * 修改保存题库信息
     * 
     * @param subjectCategory 题库信息
     * @return 结果
     */
    @Override
    public int updateSubjectCategory(SubjectCategory subjectCategory)
    {
        SubjectCategory newParentSubjectCategory = subjectCategoryMapper.selectSubjectCategoryById(subjectCategory.getParentId());
        SubjectCategory oldSubjectCategory = subjectCategoryMapper.selectSubjectCategoryById(subjectCategory.getId());
        if (StringUtils.isNotNull(newParentSubjectCategory) && StringUtils.isNotNull(oldSubjectCategory))
        {
            String newAncestry = newParentSubjectCategory.getAncestry() + "," + newParentSubjectCategory.getId();
            String oldAncestry = oldSubjectCategory.getAncestry();
            subjectCategory.setAncestry(newAncestry);
            updateSubjectCategoryChildren(subjectCategory.getId(), newAncestry, oldAncestry);
        }
        int result = subjectCategoryMapper.updateSubjectCategory(subjectCategory);
        return result;
    }

    /**
     * 修改子元素关系
     * 
     * @param id 被修改的题库ID
     * @param newAncestry 新的父ID集合
     * @param oldAncestry 旧的父ID集合
     */
    public void updateSubjectCategoryChildren(Long id, String newAncestry, String oldAncestry)
    {
        List<SubjectCategory> children = subjectCategoryMapper.selectChildrenSubjectCategoryById(id);
        for (SubjectCategory child : children)
        {
            child.setAncestry(child.getAncestry().replace(oldAncestry, newAncestry));
        }
        if (children.size() > 0)
        {
            subjectCategoryMapper.updateSubjectCategoryChildren(children);
        }
    }

    /**
     * 删除题库管理信息
     * 
     * @param id 题库ID
     * @return 结果
     */
    @Override
    public int deleteSubjectById(Long id)
    {
        return subjectCategoryMapper.deleteSubjectById(id);
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SubjectCategory> list, SubjectCategory t)
    {
        // 得到子节点列表
        List<SubjectCategory> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SubjectCategory tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<SubjectCategory> it = childList.iterator();
                while (it.hasNext())
                {
                    SubjectCategory n = (SubjectCategory) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<SubjectCategory> getChildList(List<SubjectCategory> list, SubjectCategory t)
    {
        List<SubjectCategory> tlist = new ArrayList<SubjectCategory>();
        Iterator<SubjectCategory> it = list.iterator();
        while (it.hasNext())
        {
            SubjectCategory n = (SubjectCategory) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SubjectCategory> list, SubjectCategory t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

}