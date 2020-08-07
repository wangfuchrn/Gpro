package com.zhkuchen.project.assess.domain;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhkuchen.framework.aspectj.lang.annotation.Excel;
import com.zhkuchen.framework.model.BaseModel;
import com.zhkuchen.framework.web.domain.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * 与数据库表相对应，一步步实现Restful API查询数据库
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subject extends BaseModel
{
	private static final long serialVersionUID = 1L;
	
	 @Excel(name = "题目序号")
    private Long id;
    /**
     * 题目分类ID
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long categoryId;
    
    /**
     * 题目名称
     */
    @Excel(name = "题目名称")
    private String subjectName;

    /**
     * 分值
     */
    @Excel(name = "分值")
    private Integer score;

    /**
     * 题目类型
     */
    @Excel(name = "类型")
    private Integer type;

    /**
     * 选择题选项
     */
    //private List<ChoicesOptionSub> options;

    public Long getCategoryId()
    {
      return categoryId;
    }

    public void setCategoryId(Long categoryId)
    {
      this.categoryId = categoryId;
    }

    public Long getId()
    {
      return id;
    }

    public void setId(Long id)
    {
      this.id = id;
    }

    public String getSubjectName()
    {
      return subjectName;
    }

    public void setSubjectName(String subjectName)
    {
      this.subjectName = subjectName;
    }

    public Integer getScore()
    {
      return score;
    }

    public void setScore(Integer score)
    {
      this.score = score;
    }

    public Integer getType()
    {
      return type;
    }

    public void setType(Integer type)
    {
      this.type = type;
    }

    // public List<ChoicesOptionSub> getOptions()
    // {
    //     return options;
    // }

    // public void setOptions(List<ChoicesOptionSub> options)
    // {
    //     this.options = options;
    // }

}