<template>
  <div class="app-container">
    <div>
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="选择题" name="choices" :key="'choices'" class="el-tab-pane-item">
                <choicesSub :choices-list="choicesList" :is-choices-total="isChoicesTotal"  v-if="isCoices" @choicesListEditData="choicesListEdit"/>
            </el-tab-pane>
            <el-tab-pane label="判断题" name="judge" :key="'judge'" class="el-tab-pane-item">
                <judgeSub :judge-list="judgeList"  :is-judge-total="isJudgeTotal" v-if="isJudge" 
                 @judgeListEditData="judgeListEdit"/>
            </el-tab-pane>
            <el-tab-pane label="简答题" name="shortAnswer" :key="'shortAnswer'" class="el-tab-pane-item">
                <shortAnswerSub :short-answer-list="shortAnswerList" :is-short-answer-total="isShortAnswerTotal" v-if="isShortAnswer" @shortanswerListEditData="shortanswerListEdit"/>
            </el-tab-pane>
          </el-tabs>
        </div>
  </div>
</template>

<script>
    import { judgelistSubject, shortAnswerlistSubject, choiceslistSubject} from "@/api/assess/subjectServices";
    import { choiceslistExamSubjects, judgementlistExamSubjects, shortAnswerlistExamSubjects } from "@/api/assess/examManage";
    import { treeselect } from "@/api/assess/subjectCategory";
    import { getToken } from "@/utils/auth";
    import Treeselect from "@riophae/vue-treeselect";
    import "@riophae/vue-treeselect/dist/vue-treeselect.css";
    import ChoicesSub from "./ChoicesSub/index.vue"
    import JudgeSub from "./JudgeSub/index.vue"
    import ShortAnswerSub from "./ShortAnswerSub/index.vue"
    import { isNotEmpty } from '@/utils/ruoyi'
    export default {
        name: "Subject",
        components: {
            Treeselect,
            ChoicesSub,
            JudgeSub,
            ShortAnswerSub
        },
        data() {
            return {
                // 遮罩层
                loading: true,
                //tab选项
                activeName: 'choices',
                isCoices : true,
                isJudge : false,
                isShortAnswer : false,
                //某题库下各个类型的题目数
                isChoicesTotal:0,
                isJudgeTotal:0,
                isShortAnswerTotal:0,
                // 用户表格数据
                choicesList: [],
                judgeList: [],
                shortAnswerList: [],
                // 题库名称
                categoryName: undefined,
                // 弹出层标题
                title: "",
                // 题目类别树选项
                subjectCategoryOptions: undefined,
                // 是否显示弹出层
                open: false,
                defaultProps: {
                    children: "children",
                    label: "label"
                },
                //查询参数
                queryParams: {
                    categoryId: undefined,
                },
                examinationId: 0,
            };
        },
        watch: {
            // 根据名称筛选部门树
            categoryName(val) {
                this.$refs.tree.filter(val);
            }
        },
        created() {
            this.getTreeselect();
        },
        methods: {
            handleClick(tab) {
                if (tab.name === 'choices') {
                    this.isCoices = true
                    this.isJudge = false
                    this.isShortAnswer = false
                } else if (tab.name === 'judge') {
                    this.isCoices = false
                    this.isShortAnswer = false
                    this.isJudge = true
                } else if (tab.name === 'shortAnswer') {
                    this.isCoices = false
                    this.isJudge = false
                    this.isShortAnswer = true
                }
            },
            getTreeselect() {
                treeselect().then(response => {
                    this.subjectCategoryOptions = response.data;
                });
            },
            // 筛选节点
            filterNode(value, data) {
                if (!value) return true;
                return data.label.indexOf(value) !== -1;
            },
            
            choicesListEdit(data) {
                this.choicesList = data;
                // this.isChoicesTotal = total;
            },
            judgeListEdit(data) {
                this.judgeList = data;
                // this.isChoicesTotal = total;
            },
            shortanswerListEdit(data) {
                this.shortAnswerList = data;
                // this.isChoicesTotal = total;
            },
        }
    }
</script>