package com.zhkuchen.project.assess.domain;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel;
import com.zhkuchen.framework.aspectj.lang.annotation.Excels;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel.ColumnType;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel.Type;
import com.zhkuchen.framework.web.domain.BaseEntity;
import com.zhkuchen.project.system.domain.SysDept;

/**
 * 考试分类
 *
 */
// @Data
public class Examination extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别ID */
    // @Excel(name = "考试序号", cellType = ColumnType.NUMERIC)
    private Long id;

    private Long deptId;

    /**
     * 考试名称
     */
    @Excel(name = "考试名称")
    @NotBlank(message = "考试名称不能为空")
    private String examinationName;

    /**
     * 考试注意事项
     */
    @Excel(name = "考试注意事项")
    private String attention;

    /**
     * 考试开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 考试结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /**
     * 总分
     */
    @Excel(name = "总分")
    private Integer totalScore;

    /**
     * 考试状态
     */
    @Excel(name = "考试状态")
    private String status;

    /**
     * 备注
     */
    private String remark;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 部门对象 */
    @Excels({
        @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
        @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    private SysDept dept;

    //----------------------------------------
    public Examination()
    {

    }

    public Examination(Long id)
    {
      this.id = id;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public String getExaminationName()
    {
        return examinationName;
    }

    public void setExaminationName(String examinationName)
    {
        this.examinationName = examinationName;
    }

    public String getAttention()
    {
        return attention;
    }

    public void setAttention(String attention)
    {
        this.attention = attention;
    }

    public Integer getTotalScore()
    {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore)
    {
        this.totalScore = totalScore;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public Date getStartTime()
    {
        return startTime;
    }

    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getFinishTime()
    {
        return finishTime;
    }

    public void setFinishTime(Date finishTime)
    {
        this.finishTime = finishTime;
    }

    public SysDept getDept()
    {
        return dept;
    }

    public void setDept(SysDept dept)
    {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptId", getDeptId())
            .append("examinationName", getExaminationName())
            .append("attention", getAttention())
            .append("startTime", getStartTime())
            .append("finishTime", getFinishTime())
            .append("totalScore", getTotalScore())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("dept", getDept())
            .toString();
    }
}