package com.zhkuchen.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.zhkuchen.common.enums.SubjectTypeEnum;
import com.zhkuchen.project.assess.domain.ChoicesSub;
import com.zhkuchen.project.assess.domain.JudgementSub;
import com.zhkuchen.project.assess.domain.ShortAnswerSub;
import com.zhkuchen.project.assess.domain.Subject;


/**
 * 题目工具类
 *
 */
public class SubjectUtil {

    /**
     * SubjectChoices转SubjectDto
     *
     * @param subjectChoice subjectChoice
     * @param findAnswer findAnswer
     * @return List
     */
    public static Subject subjectChoicesToDto(ChoicesSub subjectChoice) {
        if (subjectChoice == null)
            return null;
        Subject subjectDto = new Subject();
        subjectDto.setId(subjectChoice.getId());
        subjectDto.setSubjectName(subjectChoice.getSubjectName());
        subjectDto.setScore(subjectChoice.getScore());
        subjectDto.setAnalysis(subjectChoice.getAnalysis());
        subjectDto.setLevel(subjectChoice.getLevel());
        subjectDto.setCategoryId(subjectChoice.getCategoryId());
        // 选择题类型匹配
        SubjectTypeEnum subjectTypeEnum = SubjectTypeEnum.matchByValue(subjectChoice.getType());
        if (subjectTypeEnum != null)
            subjectDto.setType(subjectTypeEnum.getValue());
        // subjectDto.setOptions(subjectChoice.getOptions());
        //subjectDto.setChoicesType(subjectChoice.getChoicesType());
        subjectDto.setCreateBy(subjectChoice.getCreateBy());
        subjectDto.setCreateTime(subjectChoice.getCreateTime());
        subjectDto.setUpdateBy(subjectChoice.getUpdateBy());
        subjectDto.setUpdateTime(subjectChoice.getUpdateTime());
        subjectDto.setAnswer(subjectChoice.getAnswer());
        return subjectDto;
    }

    /**
     * SubjectChoices转SubjectDto
     *
     * @param subjectChoices subjectChoices
     * @param findAnswer findAnswer
     * @return List
     */
    public static List<Subject> subjectChoicesToDto(List<ChoicesSub> subjectChoices) {
        List<Subject> subjectDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(subjectChoices)) {
            subjectDtoList = subjectChoices.stream()
            .map(subjectChoice -> SubjectUtil.subjectChoicesToDto(subjectChoice))
            .collect(Collectors.toList());
        }
        return subjectDtoList;
    }

    /**
     * SubjectShortAnswer转SubjectDto
     *
     * @param subjectShortAnswer subjectShortAnswer
     * @param findAnswer findAnswer
     * @return List
     */
    public static Subject subjectShortAnswerToDto(ShortAnswerSub subjectShortAnswer) {
        if (subjectShortAnswer == null)
            return null;
        Subject subjectDto = new Subject();
        subjectDto.setId(subjectShortAnswer.getId());
        subjectDto.setSubjectName(subjectShortAnswer.getSubjectName());
        subjectDto.setScore(subjectShortAnswer.getScore());
        // subjectDto.setType(subjectShortAnswer.getType());
        subjectDto.setAnalysis(subjectShortAnswer.getAnalysis());
        subjectDto.setLevel(subjectShortAnswer.getLevel());
        // subjectDto.setType(SubjectTypeEnum.SHORT_ANSWER.getValue());
        subjectDto.setCreateBy(subjectShortAnswer.getCreateBy());
        subjectDto.setCreateTime(subjectShortAnswer.getCreateTime());
        subjectDto.setUpdateBy(subjectShortAnswer.getUpdateBy());
        subjectDto.setUpdateTime(subjectShortAnswer.getUpdateTime());
        subjectDto.setCategoryId(subjectShortAnswer.getCategoryId());
        // 题目类型
        subjectDto.setType(SubjectTypeEnum.SHORT_ANSWER.getValue());
        subjectDto.setAnswer(subjectShortAnswer.getAnswer());
        return subjectDto;
    }

    /**
     * SubjectShortAnswer转SubjectDto
     *
     * @param subjectShortAnswers subjectShortAnswers
     * @param findAnswer findAnswer
     * @return List
     */
    public static List<Subject> subjectShortAnswerToDto(List<ShortAnswerSub> subjectShortAnswers) {
        List<Subject> subjectDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(subjectShortAnswers)) {
            subjectDtoList = subjectShortAnswers.stream().map(subjectShortAnswer -> SubjectUtil.subjectShortAnswerToDto(subjectShortAnswer))
                    .collect(Collectors.toList());
        }
        return subjectDtoList;
    }

    /**
     * SubjectJudgement转SubjectDto
     *
     * @param subjectJudgement subjectJudgement
     * @param findAnswer findAnswer
     * @return List
     */
    public static Subject subjectJudgementToDto(JudgementSub subjectJudgement) {
        if (subjectJudgement == null)
            return null;
        Subject subjectDto = new Subject();
        subjectDto.setId(subjectJudgement.getId());
        subjectDto.setSubjectName(subjectJudgement.getSubjectName());
        subjectDto.setScore(subjectJudgement.getScore());
        //subjectDto.setType(subjectJudgement.getType());
        subjectDto.setAnalysis(subjectJudgement.getAnalysis());
        subjectDto.setLevel(subjectJudgement.getLevel());
        // subjectDto.setType(SubjectTypeEnum.JUDGEMENT.getValue());
        subjectDto.setCreateBy(subjectJudgement.getCreateBy());
        subjectDto.setCreateTime(subjectJudgement.getCreateTime());
        subjectDto.setUpdateBy(subjectJudgement.getUpdateBy());
        subjectDto.setUpdateTime(subjectJudgement.getUpdateTime());
        subjectDto.setCategoryId(subjectJudgement.getCategoryId());
        // 题目类型
        subjectDto.setType(SubjectTypeEnum.JUDGEMENT.getValue());
        subjectDto.setAnswer(subjectJudgement.getAnswer());
        return subjectDto;
    }

    /**
     * SubjectJudgement转SubjectDto
     *
     * @param subjectJudgements subjectJudgements
     * @param findAnswer findAnswer
     * @return List
     */
    public static List<Subject> subjectJudgementToDto(List<JudgementSub> subjectJudgements) {
        List<Subject> subjectDtoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(subjectJudgements)) {
            subjectDtoList = subjectJudgements.stream().map(subjectJudgement -> SubjectUtil.subjectJudgementToDto(subjectJudgement))
                    .collect(Collectors.toList());
        }
        return subjectDtoList;
    }

}