package com.zhkuchen.project.assess.domain;

import java.util.List;

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
 * ChoicesSub
 * 与数据库表相对应，一步步实现Restful API查询数据库
 */
@Data
// @EqualsAndHashCode(callSuper = true)
@TableName("exam_subject_choices")
public class ChoicesSub extends BaseEntity
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

    // /**
    //  * 选择题选项
    //  */
    // @TableField(exist = false)
    // private List<ChoicesOptionSub> options;
}