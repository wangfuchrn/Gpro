package com.zhkuchen.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 题目类型枚举
 *
 */
@Getter
public enum SubjectTypeEnum {

	CHOICES("选择题", 0),

	SHORT_ANSWER("简答题", 1),

	JUDGEMENT("判断题", 2),

	MULTIPLE_CHOICES("多选题", 3);

	private String name;

	private Integer value;

	  SubjectTypeEnum(String name, Integer value)
     {
         this.name = name;
         this.value = value;
         // this.service = service;
     }

	public Integer getValue() {
		return value;
	}
	/**
	 * 根据类型返回具体的SubjectType
	 *
	 * @param value value
	 * @return SubjectType
	 */
	public static SubjectTypeEnum matchByValue(Integer value) {
		for (SubjectTypeEnum item : SubjectTypeEnum.values()) {
			if (item.value.equals(value)) {
				return item;
			}
		}
		return CHOICES;
	}

	/**
	 * 根据描述返回具体的SubjectType
	 *
	 * @param name name
	 * @return SubjectType
	 */
	public static SubjectTypeEnum matchByName(String name) {
		for (SubjectTypeEnum item : SubjectTypeEnum.values()) {
			if (item.name.equals(name)) {
				return item;
			}
		}
		return CHOICES;
	}
}
