package com.zhkuchen.framework.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
/**
 * JudgementSub
 * 与数据库表相对应，一步步实现Restful API查询数据库
 */
@Data
// @EqualsAndHashCode(callSuper = true)
public class BaseModel {
	
	private Integer type;
	
	private String answer;
	
	private Integer score;
	
	private String analysis;
	
	private Integer level;

    /** 创建者 */ 
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;
}