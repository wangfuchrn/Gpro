<!-- 测评中心-题目管理 -->
<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--题库数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="categoryName"
            placeholder="请输入题库名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="subjectCategoryOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <!--题目数据-->
      <el-col :span="20" :xs="24">
        <div>
          <el-tabs v-model="activeName" @tab-click="handleClick" type="border-card" style="border:1px solid #fff;"> 
            <el-tab-pane label="选择题" name="choices" :key="'choices'" class="el-tab-pane-item">
                <choicesSub :choices-list="choicesList" :is-choices-total="isChoicesTotal" :click-query-params="clickQueryParams" v-if="isCoices" @choicesListEditData="choicesListEdit"/>
            </el-tab-pane>
            <el-tab-pane label="判断题" name="judge" :key="'judge'" class="el-tab-pane-item">
                <judgeSub :judge-list="judgeList"  :is-judge-total="isJudgeTotal" v-if="isJudge" :click-query-params="clickQueryParams"
                 @judgeListEditData="judgeListEdit"/>
            </el-tab-pane>
            <el-tab-pane label="简答题" name="shortAnswer" :key="'shortAnswer'" class="el-tab-pane-item">
                <shortAnswerSub :short-answer-list="shortAnswerList" :is-short-answer-total="isShortAnswerTotal" :click-query-params="clickQueryParams" v-if="isShortAnswer" @shortanswerListEditData="shortanswerListEdit"/>
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
    import { judgelistSubject, shortAnswerlistSubject, choiceslistSubject} from "@/api/assess/subjectServices";
    import { treeselect } from "@/api/assess/subjectCategory";
    import { getToken } from "@/utils/auth";
    import Treeselect from "@riophae/vue-treeselect";
    import "@riophae/vue-treeselect/dist/vue-treeselect.css";
    import ChoicesSub from "./ChoicesSub/index.vue"
    import JudgeSub from "./JudgeSub/index.vue"
    import ShortAnswerSub from "./ShortAnswerSub/index.vue"

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
                clickQueryParams: {
                    categoryId: undefined,
                },
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
            // 节点单击事件
            handleNodeClick(data) {
                this.queryParams.categoryId = data.id;
                this.clickQueryParams.categoryId  =  data.id;
                choiceslistSubject(this.queryParams).then(response => {
                    this.choicesList = response.rows;
                    this.isChoicesTotal = response.total;
                    this.loading = false;
                });
                judgelistSubject(this.queryParams).then(response => {
                    this.judgeList = response.rows;
                    this.isJudgeTotal = response.total;
                    this.loading = false;
                });
                shortAnswerlistSubject(this.queryParams).then(response => {
                    this.shortAnswerList = response.rows;
                    this.isShortAnswerTotal = response.total;
                    this.loading = false;
                });
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