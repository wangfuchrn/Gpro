package com.zhkuchen.project.assess.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhkuchen.common.utils.SubjectUtil;
import com.zhkuchen.project.assess.domain.Examination;
import com.zhkuchen.project.assess.domain.Subject;
import com.zhkuchen.project.assess.service.IChoicesSubService;
import com.zhkuchen.project.assess.service.IJudgementSubService;
import com.zhkuchen.project.assess.service.IShortAnswerSubService;
import com.zhkuchen.project.assess.service.ISubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 题目service
 */
// @AllArgsConstructor
@Service
public class SubjectService implements ISubjectService
{
    @Autowired
    private IChoicesSubService choicesSubService;

    @Autowired
    private IJudgementSubService judgementSubService;

    @Autowired
    private IShortAnswerSubService shortAnswerSubService;
    
    //private Logger log = LoggerFactory.getLogger(SubjectService.class);

    /**
     * 遍历关系集合，按类型分组题目ID，返回map
     *
     * @param subjects subjects
     * @return Map
     */
    @Override
    public List<Subject> getSubjectList(){
        // 查询题目信息，聚合
        List<Subject> subjectDtoList = new ArrayList<>();

        /*if(SubjectTypeEnum.CHOICES) {
            //List<ChoicesSub> subjectchoices = (List<ChoicesSub>) choicesSubService.getById(choicesSub.getId());
            if (CollectionUtils.isNotEmpty(choicesSubService.list())) {
                subjectDtoList.addAll(SubjectUtil.subjectChoicesToDto(choicesSubService.list()));
            }
        }*/

        //判空、转换、合并 SubjectUtil.subjectChoicesToDto(subjectChoice)
//        subjectDtoList.addAll( 
//            choicesSubService.list() .stream() //对象转stream元素
//            .filter(Objects::nonNull) //choice -> choice!=null 判断元素为空
//            //.filter(s -> SubjectTypeEnum.CHOICES ==SubjectTypeEnum.matchByValue(s.getType())) //判断某个元素的字段是否是该枚举
//            .map(choice ->{return SubjectUtil.subjectChoicesToDto(choice);} ) //转换
//            //.map(SubjectUtil::subjectChoicesToDto) //转换
//            .collect(Collectors.toList()) //收集
//        );
////        subjectDtoList.addAll(
//            judgementSubService.list().stream()
//            // .filter(Objects::nonNull) //choice -> choice!=null 判断元素为空
//            // .filter(s -> SubjectTypeEnum.JUDGEMENT ==SubjectTypeEnum.matchByValue(s.getType())) //判断某个元素的字段是否是该枚举
//            .map(SubjectUtil::subjectJudgementToDto )
//            .collect(Collectors.toList())
//        );
//        subjectDtoList.addAll(
//            shortAnswerSubService.list().stream()
//            // .filter(Objects::nonNull) //choice -> choice!=null 判断元素为空
//            // .filter(s -> SubjectTypeEnum.CHOICES ==SubjectTypeEnum.matchByValue(s.getType())) //判断某个元素的字段是否是该枚举
//            .map(SubjectUtil::subjectShortAnswerToDto )
//            .collect(Collectors.toList())
//        );
       
//        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
//    	queryWrapper.like("subjectName", "");
//    	return subjectMapper.selectList(queryWrapper);
        return subjectDtoList;   
        
    }
    
//    /**
//     * 修改保存题目信息
//     * 
//     * @param examination 题目信息
//     * @return 结果
//     */
//    @Override
//    public int update(Subject subject)
//    {
//    	return subjectMapper.
//    }
    /**
     * 删除题目管理信息
     * 
     * @param id 题目ID
     * @return 结果
     */
//    @Override
//    public int delete(Long id)
//    {
//        return subjectMapper.deleteById(id);
//    }

    /**
//     * 删除题目管理信息
//     * 
//     * @param ids 题目ID
//     * @return 结果
//     */
//    @Override
//    public int deleteAll(Collection<? extends Serializable> ids)
//    {
//        return subjectMapper.deleteBatchIds(ids);
//    }
}