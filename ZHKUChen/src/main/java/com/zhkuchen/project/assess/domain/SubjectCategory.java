package com.zhkuchen.project.assess.domain;
// 实体-封装数据
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
// import lombok.Data;
import com.zhkuchen.framework.web.domain.BaseEntity;
import com.zhkuchen.project.system.domain.SysDept;

/**
 * 题目分类
 *
 */
public class SubjectCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别ID */
    private Long id;

    /** 所属部门ID */
    private Long deptId;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空")
    private String categoryName;

    /**
     * 分类描述
     */
    private String categoryDesc;

    /**
     * 父分类id
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long parentId;

    /** 祖级列表 */
    private String ancestry;

    /**
     * 排序号
     */
    private Integer orderNum;

    /**
     * 类型: 0-私共,1-公有
     */
    private Integer type;


    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 部门对象 */
    // @Excels({
    //     @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
    //     @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
    // })
    // private SysDept dept;

    public SubjectCategory()
    {

    }

    public SubjectCategory(Long id)
    {
      this.id = id;
    }

    /** 子类别 */
    private List<SubjectCategory> children = new ArrayList<SubjectCategory>();

    public String getCategoryName()
    {
      return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
      this.categoryName = categoryName;
    }

    public String getCategoryDesc()
    {
      return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc)
    {
      this.categoryDesc = categoryDesc;
    }

    public Long getParentId()
    {
      return parentId;
    }

    public void setParentId(long parentId)
    {
      this.parentId = parentId;
    }

    public Long getId()
    {
      return id;
    }

    public void setId(long id)
    {
      this.id = id;
    }

    public Long getDeptId()
    {
      return deptId;
    }

    public void setDeptId(long deptId)
    {
      this.deptId = deptId;
    }
    
    public String getAncestry()
    {
        return ancestry;
    }

    public void setAncestry(String ancestry)
    {
        this.ancestry = ancestry;
    }

    public Integer getOrderNum()
    {
      return orderNum;
    }

    public void setOrderNum(Integer orderNum)
    {
      this.orderNum = orderNum;
    }   

    public Integer getType()
    {
      return type;
    }

    public void setType(Integer type)
    {
      this.type = type;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }


    public List<SubjectCategory> getChildren()
    {
        return children;
    }

    public void setChildren(List<SubjectCategory> children)
    {
        this.children = children;
    }

    // public SysDept getDept()
    // {
    //     return dept;
    // }

    // public void setDept(SysDept dept)
    // {
    //     this.dept = dept;
    // }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptId", getDeptId())
            .append("categoryName", getCategoryName())
            .append("categoryDesc", getCategoryDesc())
            .append("parentId", getParentId())
            .append("ancestry", getAncestry())
            .append("orderNum", getOrderNum())
            .append("type", getType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            // .append("dept", getDept())
            .toString();
    }
}
