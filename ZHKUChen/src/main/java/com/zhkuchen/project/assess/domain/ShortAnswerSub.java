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
import com.zhkuchen.framework.web.domain.BaseEntity;

import lombok.Data;

/**
 * ShortAnswerSub
 * 与数据库表相对应，一步步实现Restful API查询数据库
 */
@Data
// @EqualsAndHashCode(callSuper = true)
@TableName("exam_subject_short_answer")
public class ShortAnswerSub extends BaseEntity
{
	 private static final long serialVersionUID = 1L;

	 @TableId(value = "id",type = IdType.AUTO)
    /** 类别ID */
    private Long id;

    /**
     * 题目分类ID
     */
    @TableField("category_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;
    
    /**
     * 题目名称
     */
    @TableField("subject_name")
    @NotBlank(message = "题目名称不能为空")
    private String subjectName;

    //----------------------------------
    
    // public Long getCategoryId()
    // {
    //   return categoryId;
    // }

    // public void setCategoryId(Long categoryId)
    // {
    //   this.categoryId = categoryId;
    // }

    // public Long getId()
    // {
    //   return id;
    // }

    // public void setId(Long id)
    // {
    //   this.id = id;
    // }

    // public String getSubjectName()
    // {
    //   return subjectName;
    // }

    // public void setSubjectName(String subjectName)
    // {
    //   this.subjectName = subjectName;
    // }
}