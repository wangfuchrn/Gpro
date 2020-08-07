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

/**
 * 考试分类
 *
 */
// @Data
public class ExamAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 考试答案ID */
    // @Excel(name = "考试答案ID", cellType = ColumnType.NUMERIC)
    private Long id;

    private Long examRecordId;

    private Long subjectId;

    private String answer;

    private String answerType;

    private String markStatus;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public ExamAnswer()
    {
    }

    public ExamAnswer(Long id)
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

    public Long getExamRecordId()
    {
        return examRecordId;
    }

    public void setExamRecordId(Long examRecordId)
    {
        this.examRecordId = examRecordId;
    }

    public Long getSubjectId()
    {
        return subjectId;
    }

    public void setSubjectId(Long subjectId)
    {
        this.subjectId = subjectId;
    }

    public String getAnswer()
    {
        return answer;
    }

    public void setAnswer(String answer)
    {
        this.answer = answer;
    }

    public String getAnswerType()
    {
        return answerType;
    }

    public void setAnswerType(String answerType)
    {
        this.answerType = answerType;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("examRecordId", getExamRecordId())
            .append("subjectId", getSubjectId())
            .append("answer", getAnswer())
            .append("answerType", getAnswerType())
            .append("score", getScore())
            .append("markStatus", getMarkStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}