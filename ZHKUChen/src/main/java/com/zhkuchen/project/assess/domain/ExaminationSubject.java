package com.zhkuchen.project.assess.domain;

import java.util.List;

import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel;
import com.zhkuchen.framework.aspectj.lang.annotation.Excels;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel.ColumnType;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel.Type;
import com.zhkuchen.framework.model.BaseModel;
import com.zhkuchen.framework.web.domain.BaseEntity;

import lombok.Data;
/**
 * ExaminationSubject
 * 与数据库表相对应，一步步实现Restful API查询数据库
 */
@Data
@TableName("exam_examination_subject")
public class ExaminationSubject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 题目序号
     */
    @TableId(value = "id",type = IdType.AUTO)
    // @Excel(name = "考试题目序号", cellType = ColumnType.NUMERIC)
    private Long id;

    /**
     * 考试ID
     */
    @TableField("examination_id")
    // @Excel(name = "考试ID", cellType = ColumnType.NUMERIC)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long examinationId;

    /**
     * 分类ID
     */
    @TableField("category_id")
    // @Excel(name = "分类ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;

    /**
     * 题目ID
     */
    @TableField("subject_id")
    // @Excel(name = "题目ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long subjectId;

}