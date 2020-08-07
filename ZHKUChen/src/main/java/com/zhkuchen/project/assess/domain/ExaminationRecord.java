package com.zhkuchen.project.assess.domain;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel;
import com.zhkuchen.framework.aspectj.lang.annotation.Excels;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel.ColumnType;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel.Type;
import com.zhkuchen.framework.web.domain.BaseEntity;
import com.zhkuchen.project.assess.domain.Examination;
import com.zhkuchen.project.system.domain.SysDept;

/**
 * 考试记录分类
 *
 */
// @Data
public class ExaminationRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 类别ID */
    // @Excel(name = "考试序号", cellType = ColumnType.NUMERIC)
    private Long id;

    private Long deptId;

    private Long userId;

    private Long examinationId;

    /**
     * 考试开始时间
     */
    // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 考试结束时间
     */
    // @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /**
     * 分值
     */
    @Excel(name = "分值")
    private Integer score;

    /**
     * 正确题数
     */
    @Excel(name = "正确题数")
    private Integer correctNumber;

    /**
     * 错误题数
     */
    @Excel(name = "错误题数")
    private Integer incorrectNumber;

    /**
     * 提交状态
     */
    @Excel(name = "提交状态")
    private String submitStatus;

    /**
     * 批改状态
     */
    @Excel(name = "批改状态")
    private String markStatus;


    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 部门对象 */
    @Excels({
        @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
        @Excel(name = "部门负责人", targetAttr = "leader", type = Type.EXPORT)
    })
    private SysDept dept;

    /** 考试对象 */
    @Excels({
        @Excel(name = "考试名称", targetAttr = "examinationName", type = Type.EXPORT),
    })
    private Examination examination;

    //----------------------------------------------------
    
    public ExaminationRecord()
    {

    }

    public ExaminationRecord(Long id)
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

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getExaminationId()
    {
        return examinationId;
    }

    public void setExaminationId(Long examinationId)
    {
        this.examinationId = examinationId;
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

    public Integer getScore()
    {
        return score;
    }

    public void setScore(Integer score)
    {
        this.score = score;
    }

    public Integer getCorrectNumber()
    {
        return correctNumber;
    }

    public void setCorrectNumber(Integer correctNumber)
    {
        this.correctNumber = correctNumber;
    }

    public Integer getIncorrectNumber()
    {
        return incorrectNumber;
    }

    public void setIncorrectNumber(Integer incorrectNumber)
    {
        this.incorrectNumber = incorrectNumber;
    }

    public String getSubmitStatus()
    {
        return submitStatus;
    }

    public void setSubmitStatus(String submitStatus)
    {
        this.submitStatus = submitStatus;
    }

    public String getMarkStatus()
    {
        return markStatus;
    }

    public void setMarkStatus(String markStatus)
    {
        this.markStatus = markStatus;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public SysDept getDept()
    {
        return dept;
    }

    public void setDept(SysDept dept)
    {
        this.dept = dept;
    }

    public Examination getExamination()
    {
        return examination;
    }

    public void setExamination(Examination examination)
    {
        this.examination = examination;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deptId", getDeptId())
            .append("userId", getUserId())
            .append("examinationId", getExaminationId())
            .append("startTime", getStartTime())
            .append("finishTime", getFinishTime())
            .append("markStatus", getMarkStatus())
            .append("score", getScore())
            .append("correctNumber", getCorrectNumber())
            .append("incorrectNumber", getIncorrectNumber())
            .append("submitStatus", getSubmitStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("dept", getDept())
            .append("examination", getExamination())
            .toString();
    }

}