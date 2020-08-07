<template>
  <div class="app-container">
    <div>
      <el-row :gutter="40">
        <el-col :span="24">
          <el-card class="score-info">
            <div slot="header">
              <span>成绩详情</span>
            </div>
            <el-row>
              <el-col :span="6">
                <div class="user-info">
                  <span class="user-info-item" style="width:300px;">考试名称：{{examInfo.data.examinationName}}</span>
                  <span class="user-info-item" style="width:300px;">考生姓名：{{examRecordInfomation.userName}}</span>
                  <span class="user-info-item" style="width:300px;">所属部门：{{examInfo.data.dept.deptName}}</span>
                  <span class="user-info-item" style="width:300px;">开始时间：{{examRecordInfomation.startTime}}</span>
                  <span class="user-info-item" style="width:300px;">结束时间：{{examRecordInfomation.finishTime}}</span>
                </div>
              </el-col>
              <el-col :span="3">
                <div class="description">
                  <div>成绩</div>
                  <div class="description-score">{{totalScore}}</div>
                </div>
              </el-col>
              <el-col :span="3">
                <div class="description">
                  <div>总耗时</div>
                  <div class="description-score">20分钟</div>
                </div>
              </el-col>
              <el-col :span="6">
                <div class="score-chart">
                  <div id="myChart" ref="myChart" style="height: 150px; width: 100%;"></div>
                </div>
              </el-col>
            </el-row> 
          </el-card>
        </el-col>
      </el-row> 
      <el-row>
        <el-col :span="24">
          <el-card class="subject-list">
            <el-table
              ref="multipleTable"
              :key="tableKey"
              :data="allSubjectsInfoArray"
              :default-sort="{ prop: 'id', order: 'descending' }"
              highlight-current-row
              style="width: 100%;">
                  <el-table-column label="题目名称" min-width="120" align="center" prop="subjectName" :show-overflow-tooltip="true"/>
                  <el-table-column label="类型" align="center" prop="type" :show-overflow-tooltip="true" >
                      <template slot-scope="scope">
                          <el-tag 
                            :type="
                              scope.row.type=='0' ?'success'
                                :(scope.row.type=='1' ? 'info'
                                  :(scope.row.type=='2' ? 'warning' 
                                    : scope.row.type=='3'?'':'danger'))
                                ">
                                {{scope.row.type=='0'?'选择题'
                                  :(scope.row.type=='1'?'简答题'
                                    :(scope.row.type=='2'?'判断题'
                                      :(scope.row.type=='3'?'多选题':'')))
                                  }}
                          </el-tag>
                      </template>
                  </el-table-column>
                  <el-table-column label="考生答案" align="center" prop="userAnswer" :show-overflow-tooltip="true"/>
                  <el-table-column label="参考答案" align="center" prop="answer" :show-overflow-tooltip="true"/>
                  <el-table-column label="批改状态" align="center" prop="markStatus" :show-overflow-tooltip="true">
                    <template slot-scope="scope">
                      <el-tag 
                            :type="
                              scope.row.markStatus=='0' ?'warning'
                                    : scope.row.markStatus=='1'?'':'info'
                                ">
                                {{scope.row.markStatus=='0'?'未批改'
                                      :(scope.row.markStatus=='1'?'已批改':'')
                                  }}
                          </el-tag>
                    </template>
                  </el-table-column>
                  <el-table-column label="答题结果" align="center" prop="answerType" :show-overflow-tooltip="true">
                    <!-- answer_type 答题类型，0：正确，1：错误  -->
                    <template slot-scope="scope">

                          <el-tag 
                            :type="
                              scope.row.answerType=='0' ?'success'
                                    : scope.row.answerType=='1'?'danger':''
                                ">
                                {{scope.row.answerType=='0'?'正确'
                                      :(scope.row.answerType=='1'?'错误':'')
                                  }}
                          </el-tag>
                      </template>
                  </el-table-column>
                  <el-table-column label="得分" align="center" prop="markScore" :show-overflow-tooltip="true">
                  </el-table-column>
            </el-table>
            <pagination
              v-show="subjectCount>0"
              :total="subjectCount"
              :page.sync="queryParams.pageNum"
              :limit.sync="queryParams.pageSize"
              @pagination="getInfoByExaminationId"
            />
          </el-card> 
      </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  import CountTo from 'vue-count-to'
  import echarts from 'echarts'
  import { getByTestTaskId } from '@/api/assess/testTask'
  require('echarts/theme/macarons')
  import { getAllSubjectByExaminationId } from '@/api/assess/examManage'
  import { isNotEmpty, messageSuccess, messageFail, messageWarn, formatDate } from '@/utils/ruoyi'
  import { findListById } from '@/api/exam/examRecord'
  import { getUserNameByUserId } from '@/api/system/user'
  import { getListByExamRecordId } from '@/api/exam/examAnswer'

  export default {
    name: 'scoreDetails',
    components: {
      CountTo,
    },
    data () {
      return {
        chart: null,
        chartData: [],
        tableKey: 0,
        scoreDetailsList: [],
        allSubjectsInfoArray: [],
        answerListByExamRecordId: [],
        subjectIds: [],
        subjectCount: 0,
        examInfo: {
          data: {
            dept:{},
          },
          shortanswers: {},
          choices: {},
          judges: {},
          userName:undefined,
        },
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        query: {
          examinationId: undefined,
          examRecordId: undefined,
          teskTaskId: undefined
        },
        examRecordInfomation: {
          examination:{},
        },
        queryExamRecord: {
          examRecordId: undefined,
          userName:undefined,
        },
        totalScore: 0,
        correctNumber: 0,
        inCorrectNumber: 0,
      }
    },
    created () {
      const examRecordInfo = this.$route.params.id
      if (isNotEmpty(examRecordInfo)) {
        let examInfoArr = examRecordInfo.split('-');
        this.query.examinationId = examInfoArr[0];
        this.query.examRecordId = examInfoArr[1];
        this.getExamRecordInfo();
        this.query.teskTaskId = examInfoArr[2];
      }
      this.getInfoByExaminationId()
    },
    mounted() {
      this.$nextTick(() => {
        this.initChart()
      })
    },
    methods: {
      initChart () {
        this.chart = echarts.init(this.$refs.myChart, 'macarons')
        let option = {
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          series: [
              {
                  name: '访问来源',
                  type: 'pie',
                  radius: ['50%', '70%'],
                  avoidLabelOverlap: false,
                  animationEasing: 'cubicInOut',
                  animationDuration: 2600,
                  label: {
                      normal: {
                      formatter: function (param) {
                        return param.name + ': ' + Math.round(param.value)
                      }
                    }
                  },
                  data: [
                      //加入itemStyle可以改变饼图某个块的颜色
                      {value: 9, name: '正确数', itemStyle:{color:'#67e0e3'}},
                      {value: 3, name: '错误数', itemStyle:{color:'#ff9f7f'}},
                  ]
              }
          ]
        };
        this.chart.setOption(option);
      },
      getExamRecordInfo() {
        findListById(this.query.examRecordId).then(response=>{
          this.examRecordInfomation = response.data;
          this.queryExamRecord.examRecordId = response.data.id;
          getUserNameByUserId(response.data.userId).then(response=>{
            this.examRecordInfomation.userName = response.data.userName;
          });
          getListByExamRecordId(this.queryExamRecord).then(response=>{
            this.answerListByExamRecordId = response.rows;
          });
        });
      },
      getInfoByExaminationId() {
        getByTestTaskId(this.query.teskTaskId).then(response =>{
          getAllSubjectByExaminationId(response.data.examinationId).then(response =>{
            this.examInfo = response;
            let slehgth = 0;
            let jlehgth = 0;
            let clehgth = 0;

            if(response.choices != undefined){
              clehgth = response.choices.length;
              for(var a in this.examInfo.choices)
              this.subjectIds.push(this.examInfo.choices[a].id);
              //合并含有题目的数组
              this.allSubjectsInfoArray = this.allSubjectsInfoArray.concat(this.examInfo.choices);
            }
            if(response.judges != undefined){
              jlehgth = response.judges.length;
              for(var b in this.examInfo.judges)
              this.subjectIds.push(this.examInfo.judges[b].id);
              this.allSubjectsInfoArray = this.allSubjectsInfoArray.concat(this.examInfo.judges);
            }
            if(response.shortanswers != undefined){
              slehgth = response.shortanswers.length;
              for(var c in this.examInfo.shortanswers)
              this.subjectIds.push(this.examInfo.shortanswers[c].id);
              this.allSubjectsInfoArray = this.allSubjectsInfoArray.concat(this.examInfo.shortanswers);
            }
            this.subjectCount = slehgth + jlehgth + clehgth;

            if(isNotEmpty(this.allSubjectsInfoArray)){
              this.getNewSubjectInfoWithAnswer(this.allSubjectsInfoArray);
            }
          });
        });
      },
      getNewSubjectInfoWithAnswer(oldSubjectInfo) {
        for(var i = 0;i<oldSubjectInfo.length;i++){
          oldSubjectInfo[i].userAnswer = "/";
          oldSubjectInfo[i].markScore = 0;
          oldSubjectInfo[i].answerType = 1;
          oldSubjectInfo[i].markStatus = 0;

          if(this.answerListByExamRecordId!=undefined){
              for(var j = 0;j<this.answerListByExamRecordId.length;j++){
              if(oldSubjectInfo[i].id == this.answerListByExamRecordId[j].subjectId){
                oldSubjectInfo[i].userAnswer = this.answerListByExamRecordId[j].answer;
                oldSubjectInfo[i].markScore = this.answerListByExamRecordId[j].score;
                oldSubjectInfo[i].answerType = this.answerListByExamRecordId[j].answerType;
                oldSubjectInfo[i].markStatus = this.answerListByExamRecordId[j].markStatus;
              }
            }
          }
        }

        this.allSubjectsInfoArray = oldSubjectInfo;
        // console.log(this.allSubjectsInfoArray);
        for(var a=0; a<this.allSubjectsInfoArray.length; a++){
          if(this.allSubjectsInfoArray[a].answerType == 0 && this.allSubjectsInfoArray[a].markStatus ==1){
            this.totalScore += this.allSubjectsInfoArray[a].markScore;
            // this.correctNumber ++;
          }
        }
        // this.inCorrectNumber = this.allSubjectsInfoArray.length - this.correctNumber;
        // console.log(this.totalScore);
        // console.log(this.correctNumber);
        // console.log(this.inCorrectNumber);
      },
    },
  }
</script> 

<style lang="scss" scoped>
  .app-container {
    padding: 12px;
    .score-info {
      height: 250px;
    }
  }
  .user-info {
    font-size: 13px;
    .user-info-item {
      display:inline-block;
      padding:5px 10px;
      cursor:pointer;
    }
  }
  .description {
    display: inline-block;
    padding: 20px 0 0 20px;
    font-size: 18px;
    opacity: .9;
    .description-score {
      padding-top: 8px;
      font-size: 24px;
    }
  }
  .subject-list {
    padding: 12px;
    margin-top: 12px;
  }
</style>
