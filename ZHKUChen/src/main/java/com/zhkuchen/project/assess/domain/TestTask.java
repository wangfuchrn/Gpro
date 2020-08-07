package com.zhkuchen.project.assess.domain;

import java.sql.Date;

import javax.validation.constraints.NotBlank;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel;
import com.zhkuchen.framework.aspectj.lang.annotation.Excels;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel.Type;
import com.zhkuchen.framework.model.BaseModel;
import com.zhkuchen.framework.web.domain.BaseEntity;
import com.zhkuchen.project.system.domain.SysDept;

import lombok.Data;
/**
 * TestTask
 * 与数据库表相对应，一步步实现Restful API查询数据库
 */
// @Data
// @EqualsAndHashCode(callSuper = true)
// @TableName("exam_test_task")
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class TestTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    // @TableId(value = "id",type = IdType.AUTO)
    /** 考试任务ID */
    private Long id;

    /**
     * 所属部门ID
     */
    // @TableField("dept_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long deptId;

    /**
     * 考试ID
     */
    // @TableField("examination_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long examinationId;

    /**
     * 用户手机号
     */
    // @TableField("user_phonenumber")
    @Excel(name = "用户手机号")
    @NotBlank(message = "用户手机密码不能为空")
    private String userPhonenumber;

    /**
     * 是否已答卷
     */
    // @TableField("is_written")
    @Excel(name = "答卷状态")
    private String isWritten;

    // /**
    //  * 发布者
    //  */
    // // @TableField("public_by")
    // @Excel(name = "发布者")
    // private String publicBy;

    // /**
    //  * 发布时间
    //  */
    // // @TableField("public_time")
    // @Excel(name = "发布时间")
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // private Date publicTime;

    /** 考试对象 */
    @Excels({
        @Excel(name = "考试名称", targetAttr = "examinationName", type = Type.EXPORT),
    })
    private Examination examination;

    /** 考试对象 */
    @Excels({
        @Excel(name = "部门名称", targetAttr = "deptName", type = Type.EXPORT),
    })
    private SysDept dept;

    //------------------------------------

    public TestTask()
    {

    }

    public TestTask(Long id)
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

    public Long getExaminationId()
    {
        return examinationId;
    }

    public void setExaminationId(Long examinationId)
    {
        this.examinationId = examinationId;
    }

    public String getUserPhonenumber()
    {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber)
    {
        this.userPhonenumber = userPhonenumber;
    }

    public String getIsWritten()
    {
        return isWritten;
    }

    public void setIsWritten(String isWritten)
    {
        this.isWritten = isWritten;
    }

    // public String getPublicBy()
    // {
    //     return publicBy;
    // }

    // public void setPublicBy(String publicBy)
    // {
    //     this.publicBy = publicBy;
    // }

    // public Date getPublicTime()
    // {
    //     return publicTime;
    // }

    // public void setPublicTime(Date publicTime)
    // {
    //     this.publicTime = publicTime;
    // }

    public Examination getExamination()
    {
        return examination;
    }

    public void setExamination(Examination examination)
    {
        this.examination = examination;
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
            .append("examinationId", getExaminationId())
            .append("userPhonenumber", getUserPhonenumber())
            .append("isWritten", getIsWritten())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("examination", getExamination())
            .append("dept", getDept())
            .toString();
    }

}
