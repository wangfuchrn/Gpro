<template>
  <div id="entry" class="main">
    <u-header></u-header>
    <section class="container-fluid">
      <template>
        <el-card>
          <el-row>
            <el-col :span="20" :offset="2">
              <el-divider>成绩详情</el-divider>
            </el-col>
          </el-row>
          <el-row>
              <el-col :span="20" :offset="2">
                <el-row style="margin-bottom: 10px;">
                  <el-col :span="8">
                    <span class="user-info-item" style="width:300px;">考试名称：{{examInfo.data.examinationName}}</span>
                  </el-col>
                  <el-col :span="8">
                    <span class="user-info-item" style="width:300px;">考生姓名：{{userInfo.userName}}</span>
                  </el-col>
                  <el-col :span="8">
                    <span class="user-info-item" style="width:300px;">总得分：{{totalScore}}</span>
                  </el-col>
                </el-row>
                <el-row style="margin-bottom: 10px;">
                  <el-col :span="8">
                    <span class="user-info-item" style="width:300px;">开始时间：{{examRecordInfomation.startTime}}</span>
                  </el-col>
                  <el-col :span="8">
                    <span class="user-info-item" style="width:300px;">结束时间：{{examRecordInfomation.finishTime}}</span>
                  </el-col>
                  <el-col :span="8">
                    <span class="user-info-item" style="width:300px;">耗时：20 min</span>
                  </el-col>
                </el-row>
                <el-row style="margin-bottom: 10px;">
                  <el-col :span="8">
                    <span class="user-info-item" style="width:300px;">评卷人：{{this.examInfo.data.dept.deptName}}</span>
                  </el-col>
                  <el-col :span="8">
                    <span class="user-info-item" style="width:300px;">正确题数：{{correctNumber}}</span>
                  </el-col>
                  <el-col :span="8">
                    <span class="user-info-item" style="width:300px;">错误题数：{{inCorrectNumber}}</span>
                  </el-col>
                </el-row>
              </el-col> 
          </el-row> 
          <el-row>
            <el-col :span="20" :offset="2">
              <el-divider>题目列表</el-divider>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="20" :offset="2">
              <div v-for="(value, index) in list" :key="value.id">
                <div class="subject-content" >
                  <div class="subject-content-option">
                    <div class="subject-title">
                      <span class="subject-title-number">{{index + 1}} .</span>
                      <span v-html="value.subjectName"></span>（{{value.score}}分）
                    </div>
                     <!-- 选择题 -->
                     <div v-for="(optionvalue, optionindex) in choicesoptions">
                      <ul class="subject-options" v-for="(optionvalue1, optionindex1) in optionvalue" v-if="optionvalue1.subjectChoicesId == value.id">
                        <li class="subject-option">
                          <label><span class="subject-option-prefix" style="font-size: 14px;line-height: 7px;">{{ optionvalue1.optionName }}&nbsp;</span><span v-html="optionvalue1.optionContent" class="subject-option-prefix" style="font-size: 14px;line-height: 7px;"></span></label>
                        </li>
                      </ul>
                     </div>
                  </div>
                  <!-- 简答题 -->
                  <div v-if="value.type === 1">
                    <p>
                      <span v-html="value.answer"></span>
                    </p>
                  </div>
                  <div>
                    <p class="subject-content-answer" v-if="value.userAnswer !== ''" style="margin-top: 1em;">
                      我的答案：<span v-html="value.userAnswer"></span>
                    </p>
                    <p class="subject-content-answer" v-if="value.answer !== ''">
                      参考答案：<span v-html="value.answer"></span>
                    </p>
                    <p class="subject-content-analysis" v-if="value.analysis !== ''">
                      解析：<span v-html="value.analysis"></span>
                    </p>
                  </div>
                </div>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </template> 
    </section>
  </div>
</template>

<script>
  import UHeader from '../../../components/UserHeader/index.vue'
  import { isNotEmpty } from '@/utils/ruoyi'
  import { getAllSubjectByExaminationId } from '@/api/assess/examManage'
  import { getListByExamRecordId } from '@/api/exam/examAnswer'
  import { getUserProfile } from '@/api/system/user'
  import { findListById } from '@/api/exam/examRecord'
  import { getchoicesOptionsInfo } from '@/api/assess/subjectServices'

  export default{
    name:'entry',
    components: {
      UHeader
    },
    data () {
      return {
        query: {
          examinationId: undefined,
          examRecordId: undefined,
        },
        examInfo: {
          data: {
            dept:{},
          },
          shortanswers: {},
          choices: {},
          judges: {},
          userName:undefined,
        },
        subjectIds: [],
        allSubjectsInfoArray: [],
        //当前用户信息
        userInfo: {},
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
        list: [],
        examSubjectList: [],
        judgeoptions: [
          { id: 1, optionName: '正确' },
          { id: 2, optionName: '错误' }
        ],
        //选择题选项列表
        choicesoptions: [],
        //查询参数
        query: {
          // sort: 'subject_id',
          order: ' asc',
          pageNum: 1,
          pageSize: 10,
        },
      }
    },
    created () {
      const examRecordInfo = this.$route.params.id
      if (isNotEmpty(examRecordInfo)) {
        let examInfoArr = examRecordInfo.split('-');
        this.query.examinationId = examInfoArr[0];
        this.query.examRecordId = examInfoArr[1];
        this.getExamRecordInfo();
      }
      this.getInfoByExaminationId();
      this.getUserInfo();
    },
    methods: {
      getUserInfo() {
        getUserProfile().then(response => {
          //获取当前用户信息
          this.userInfo = response.data;
        });
      },
      getInfoByExaminationId() {
          getAllSubjectByExaminationId(this.query.examinationId).then(response =>{
            this.examInfo = response;
            let slehgth = 0;
            let jlehgth = 0;
            let clehgth = 0;

            if(response.choices != undefined){
              clehgth = response.choices.length;
              for(var a in this.examInfo.choices)
                // console.log(this.examInfo.choices[a]);
              this.subjectIds.push(this.examInfo.choices[a].id);

              for(var i=0;i< this.subjectIds.length;i++){
                getchoicesOptionsInfo(this.subjectIds[i]).then(response => {
                  this.choicesoptions.push(response.data);

                });
              }
              // console.log(this.choicesoptions);

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
            // console.log(this.allSubjectsInfoArray);
            // console.log(this.allSubjectsInfoArray.length);
          });
      },
      getExamRecordInfo() {
        findListById(this.query.examRecordId).then(response=>{
          this.examRecordInfomation = response.data;
          this.queryExamRecord.examRecordId = response.data.id;
          getListByExamRecordId(this.queryExamRecord).then(response=>{
            if(isNotEmpty(response.rows)){
              this.getNewSubjectInfoWithAnswer(response.rows);
            }
          });
        });
      },
      getNewSubjectInfoWithAnswer(answerListByExamRecordId) {
        if(isNotEmpty(this.allSubjectsInfoArray)){
          let oldSubjectInfo = this.allSubjectsInfoArray;
          // console.log(answerListByExamRecordId);
          for(var i = 0;i<oldSubjectInfo.length;i++){
            oldSubjectInfo[i].userAnswer = "/";
            oldSubjectInfo[i].markScore = 0;
            oldSubjectInfo[i].answerType = 1;
            oldSubjectInfo[i].markStatus = 0;
                
            if(answerListByExamRecordId != undefined){
              for(var j = 0;j<answerListByExamRecordId.length;j++){
                if(oldSubjectInfo[i].id == answerListByExamRecordId[j].subjectId){
                  oldSubjectInfo[i].userAnswer = answerListByExamRecordId[j].answer;
                  oldSubjectInfo[i].markScore = answerListByExamRecordId[j].score;
                  oldSubjectInfo[i].answerType = answerListByExamRecordId[j].answerType;
                  oldSubjectInfo[i].markStatus = answerListByExamRecordId[j].markStatus;
                }
              }
            }
          }

          for(var a=0; a<oldSubjectInfo.length; a++){
            if(oldSubjectInfo[a].answerType == 0 && oldSubjectInfo[a].markStatus ==1){
              this.totalScore += oldSubjectInfo[a].markScore;
                  this.correctNumber ++;
            }
          }
          this.allSubjectsInfoArray = oldSubjectInfo;
          this.inCorrectNumber = this.allSubjectsInfoArray.length - this.correctNumber;
          this.list = oldSubjectInfo;
        }else{
          return;
        }
      },
    },
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .container-fluid {
    margin-top: 82px;
    color:#606266;
  }
  .subject-content {
    background: #F6F7FA;
    border-radius: 4px;
    margin-bottom: 21px;
    padding: 12px 0 12px 22px;
    position: relative;
    min-height: 240px;
    color: #666666;
    text-align: left;
  }
  .subject-options {
    margin: 0;
    padding: 0;
    list-style: none;
    > li {
      position: relative;
      font-size: 24px;
      label {
        word-break: break-all;
        display: block;
        line-height: 1.0;
        transition: color 0.4s;
        font-weight: normal;
      }
      /* 选项名称 */
      .subject-option-prefix {
        font-size: 16px;
        display: inline-block
      }
    }
  }

</style>