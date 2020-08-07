package com.zhkuchen.project.assess.domain;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhkuchen.framework.model.BaseModel;
import com.zhkuchen.framework.web.domain.BaseEntity;

import lombok.Data;
/**
 * ChoicesOptionSub
 * 与数据库表相对应，一步步实现Restful API查询数据库
 */
@Data
// @EqualsAndHashCode(callSuper = true)
@TableName("exam_subject_option")
public class ChoicesOptionSub extends BaseModel
{
	private static final long serialVersionUID = 1L;

	@TableId(value = "id",type = IdType.AUTO)
    /** 类别ID */
    private Long id;

    /**
     * 选项分类ID
     */
    @TableField("subject_choices_id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long subjectChoicesId;
    
    /**
     * 选项名称
     */
    @TableField("option_name")
    @NotBlank(message = "选项名称不能为空")
    private String optionName;

    /**
     * 选项内容
     */
    @TableField("option_content")
    @NotBlank(message = "选项名称不能为空")
    private String optionContent;

    // public Long getId()
    // {
    //   return id;
    // }

    // public void setId(Long id)
    // {
    //   this.id = id;
    // }

    // public Long getSubjectChoicesId()
    // {
    //   return subjectChoicesId;
    // }

    // public void setSubjectChoicesId(Long subjectChoicesId)
    // {
    //   this.subjectChoicesId = subjectChoicesId;
    // }

    // public String getOptionName()
    // {
    //   return optionName;
    // }

    // public void setOptionName(String optionName)
    // {
    //   this.optionName = optionName;
    // }

    // public String getOptionContent()
    // {
    //   return optionContent;
    // }

    // public void setOptionContent(String optionContent)
    // {
    //   this.optionContent = optionContent;
    // }
    
}