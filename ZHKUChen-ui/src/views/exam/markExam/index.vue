<template>
  <div class="app-container">
    <el-card>
      <el-row :gutter="20" style="padding: 10px;margin-top: 20px;">
        <el-col :span="4" style="text-align: center;">
          <span>选择题目</span>
          <div class="answer-number">
            <el-button class="number-btn" circle v-for="(value, index) in subjectIds" :key="index" @click="toSubject(subjectInfo.id, index, value)" >&nbsp;{{index + 1}}&nbsp;</el-button>
          </div> 
        </el-col>
        <el-col :span="18">
            <div class="subject-box-card">
              <div class="user-info">
                <el-row >
                  <el-col :span="8">
                    <div style="font-size: 12px;">测评名称：{{examRecordInfomation.examination.examinationName}}</div>
                  </el-col>
                  <el-col :span="5">
                    <div style="font-size: 12px;">考生姓名：{{examRecordInfomation.userName}}</div>
                  </el-col>
                  <el-col :span="5">
                    <div style="font-size: 12px;">得分：{{examRecordInfomation.score}}</div>
                  </el-col>
                  <el-col :span="6">
                   <div style="font-size: 12px;">批改状态：
                    <el-tag 
                      :type="
                        examRecordInfomation.markStatus=='0' ?'warning'
                          :(examRecordInfomation.markStatus=='1' ? 'success':'info')
                          ">
                          {{examRecordInfomation.markStatus=='0'?'未批改'
                            :(examRecordInfomation.markStatus=='1'?'已批改':'')
                            }}
                    </el-tag>
                   </div>
                  </el-col>
                </el-row>
              </div>
              <el-divider></el-divider>
              <div class="subject-content">
                <div class="subject-title">
                  {{subjectIndex}}
                  <span class="subject-title-content" v-html="subjectInfo.subjectName"/>
                  <span class="subject-title-content" v-if="subjectInfo.score !== undefined && subjectInfo.score !== 0">&nbsp;({{subjectInfo.score}})分</span>
                </div>
                <!-- 单选题 -->
                <div v-if="subjectInfo.type === 0 || subjectInfo.type === 3">
                  <ul class="subject-options" v-for="option in choicesoptions" :key="option.id">
                    <li class="subject-option">
                      <label><span class="subject-option-prefix">{{ option.optionName }}&nbsp;</span><span v-html="option.optionContent" class="subject-option-prefix"></span></label>
                    </li>
                  </ul>
                </div>
                <!-- 判断题 -->
                <div v-if="subjectInfo.type === 2">
                  <ul class="subject-options" v-for="option in judgeoptions" :key="option.id">
                    <li class="subject-option">
                      <label><span class="subject-option-prefix">{{ option.optionName }}&nbsp;</span><span v-html="option.optionContent" class="subject-option-prefix"></span></label>
                    </li>
                  </ul>
                </div>
              </div>
              <el-row style="font-size: 12px;">
                <el-col :span="24" v-if="subjectInfo.type === 0 || subjectInfo.type === 1 || subjectInfo.type === 2 || subjectInfo.type === 3">考生答案：
                  <span v-html="subjectInfo.userAnswer"/>
                </el-col>
              </el-row>
              <el-row style="font-size: 12px;margin-top:18px;">
                <el-col :span="24" v-if="subjectInfo.type === 0 || subjectInfo.type === 1 || subjectInfo.type === 2 || subjectInfo.type === 3">参考答案：
                  <span v-html="subjectInfo.answer"/>
                </el-col>
              </el-row>
              <el-row style="font-size: 12px;margin-top:18px;">
                <el-col :span="24" v-if="subjectInfo.type === 0 || subjectInfo.type === 1 || subjectInfo.type === 2 || subjectInfo.type === 3">解析：
                  <span v-html="subjectInfo.analysis" style="display: inline-block;"/>
                </el-col>
              </el-row>
              <el-row style="font-size: 12px;margin-top:18px;">
                <el-col :span="24" v-if="subjectInfo.type === 0 || subjectInfo.type === 1 || subjectInfo.type === 2 || subjectInfo.type === 3">批改结果：
                  <el-switch
                    v-model="correct"
                    active-color="#13ce66"
                    inactive-color="#ff4949"
                    active-text="正确"
                    inactive-text="错误"
                    >
                  </el-switch>
                </el-col>
              </el-row>
            </div> 
            <div class="subject-buttons" v-if="subjectIds !== ''">
              <el-button plain @click="last">上一题</el-button>
              <el-button plain @click="next">下一题</el-button>
              <el-button type="success" icon="el-icon-check" @click="completeMarking" v-bind:disabled="examRecordInfomation.markStatus=='1'">批改完成</el-button>
            </div>
        </el-col> 
      </el-row> 
    </el-card>
  </div>
</template>

<script>
  import { isNotEmpty, messageSuccess, messageFail, messageWarn, formatDate } from '@/utils/ruoyi'
  import { getAllSubjectByExaminationId } from '@/api/assess/examManage'
  import { getchoicesOptionsInfo } from '@/api/assess/subjectServices'
  import { getUserProfile } from '@/api/system/user'
  import { findListById, updateMarkStatus } from '@/api/exam/examRecord'
  import { getListByExamRecordId, getBySubjectId, getId, updateExamAnswer, addExamAnswer } from '@/api/exam/examAnswer'
  import { getUserNameByUserId } from '@/api/system/user'

  export default {
    name: 'markExam',
    data() {
      return {
        subjectIds: [],
        query: {
          examinationId: undefined,
          examRecordId: undefined,
          teskTaskId: undefined
        },
        queryExamRecord: {
          examRecordId: undefined,
          userName:undefined,
        },
        examInfo: {
          data: {},
          shortanswers: {},
          choices: {},
          judges: {}
        },
        allSubjectsInfoArray: [],
        typeArray: [],
        subjectIds: [],
        subjectCount: 0,
        subjectIndex: null,
        subjectInfo: {},
        examRecordInfomation: {
          examination:{},
        },
        answerListByExamRecordId: [],
        newSubjectInfo: [],
        //选择题选项列表
        choicesoptions: [],
        judgeoptions: [
          { id: 1, optionName: '正确' },
          { id: 2, optionName: '错误' }
        ],
        correct: true,
        queryBysubjectIdAndExamRecordId: {
          examRecordId: undefined,
          subjectId: undefined,
        },
        //某个题目的选项答案信息
        examAnswerInfo: {},
        examAnswerForm: {},
        newSubjectInfoWithAnswer: {},
        newAnswerList: [],
      }
    },
    created() {
      // path: `/exam/mark/${row.examinationId}-${this.examRecordId}-${row.id}`
      const examRecordInfo = this.$route.params.id
      if (isNotEmpty(examRecordInfo)) {
        let examInfoArr = examRecordInfo.split('-');
        this.query.examinationId = examInfoArr[0];
        this.query.examRecordId = examInfoArr[1];
        this.getExamRecordInfo();
        this.query.teskTaskId = examInfoArr[2];
        this.queryBysubjectIdAndExamRecordId.examRecordId = this.query.examRecordId;
      }
      
      this.getByExaminationId();
      // this.getSubjectAndAnswerByExamRecordId();
    },
    methods: {
      getExamRecordInfo() {
        findListById(this.query.examRecordId).then(response=>{
          // this.getSubjectAndAnswerByExamRecordId(response.data.id);
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
      getByExaminationId() {
        
        getAllSubjectByExaminationId(this.query.examinationId).then(response => {
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

          if(this.allSubjectsInfoArray != null){
            for(var d in this.allSubjectsInfoArray)
            //获取到全部题目的类型
            this.typeArray.push(this.allSubjectsInfoArray[d].type);
          }

          // if(isNotEmpty(this.allSubjectsInfoArray)){
          //     this.getNewSubjectInfoWithAnswer(this.allSubjectsInfoArray);
          //   }
        });
      },

      // 跳转题目
      toSubject (subjectId, index, value) {
        //this.subjectIndex比index多1
        this.subjectIndex = index + 1;
        this.subjectIndex = this.subjectIndex + ".";
        this.subjectInfo = this.allSubjectsInfoArray[index];
        
        //当选中的题目类型为选择题时，获取相关选项
        if(this.subjectInfo.type===0 || this.subjectInfo.type===3){
          var choicesid = this.subjectInfo.id;
          getchoicesOptionsInfo(choicesid).then(response => {
            this.choicesoptions = response.data;
          });
        }

        this.subjectInfo.userAnswer = "";
        for(let j = 0; j<this.answerListByExamRecordId.length;j++){
          if(this.answerListByExamRecordId[j].subjectId==this.subjectInfo.id){
            // console.log(this.answerListByExamRecordId[j]);
            this.subjectInfo.userAnswer = this.answerListByExamRecordId[j].answer;
          }
        }

        if(subjectId==undefined){
          return;
        }else{
          // 获取上一个题目的id
          this.saveAndUpdateMarkStatusBySubjectId(subjectId);
        }
      },
      
      last() {
        if(this.subjectInfo.id!=undefined){
          for (let i = 0; i < this.subjectIds.length; i++) {
            if (this.subjectIds[i] === this.subjectInfo.id) {
              if (i === 0) {
                messageSuccess(this, '已经是第一题了')
                break
              }
                this.subjectIndex = i;
                this.subjectIndex = this.subjectIndex + ".";
                this.subjectInfo = this.allSubjectsInfoArray[i-1];

                //当选中的题目类型为选择题时，获取相关选项
                if(this.subjectInfo.type===0 || this.subjectInfo.type===3){
                var choicesid = this.subjectInfo.id;
                getchoicesOptionsInfo(choicesid).then(response => {
                  this.choicesoptions = response.data;
                });

                this.subjectInfo.userAnswer = "";
                for(let j = 0; j<this.answerListByExamRecordId.length;j++){
                  if(this.answerListByExamRecordId[j].subjectId==this.subjectInfo.id){
                    // console.log(this.answerListByExamRecordId[j]);
                    this.subjectInfo.userAnswer = this.answerListByExamRecordId[j].answer;
                  }
                }
                //获取上一个题目的id
                this.saveAndUpdateMarkStatusBySubjectId(this.allSubjectsInfoArray[i].id);
              }
              break
            }
          }
        }else{
          return;
        }
      },

      next() {
        if(this.subjectInfo.id!=undefined){
          for (let i = 0; i < this.subjectIds.length; i++) {
            if (this.subjectIds[i] === this.subjectInfo.id) {
              if (i === this.subjectIds.length - 1) {
                this.saveAndUpdateMarkStatusBySubjectId(this.allSubjectsInfoArray[i].id);
                messageSuccess(this, '已经是最后一题了')
                break
              }
              this.subjectIndex = i + 2;
              this.subjectIndex = this.subjectIndex + ".";
              this.subjectInfo = this.allSubjectsInfoArray[i+1];

              //当选中的题目类型为选择题时，获取相关选项
              if(this.subjectInfo.type===0 || this.subjectInfo.type===3){
                var choicesid = this.subjectInfo.id;
                getchoicesOptionsInfo(choicesid).then(response => {
                  this.choicesoptions = response.data;
                });
              }
              this.subjectInfo.userAnswer = "";
              for(let j = 0; j<this.answerListByExamRecordId.length;j++){
                if(this.answerListByExamRecordId[j].subjectId==this.subjectInfo.id){
                  // console.log(this.answerListByExamRecordId[j]);
                  this.subjectInfo.userAnswer = this.answerListByExamRecordId[j].answer;
                }
              }
              //获取上一个题目的id
              this.saveAndUpdateMarkStatusBySubjectId(this.allSubjectsInfoArray[i].id);
              break
            }
          }
        }else{
          return;
        }
      },

      saveAndUpdateMarkStatusBySubjectId(subjectId){
        // console.log(this.correct);
        this.queryBysubjectIdAndExamRecordId.subjectId = subjectId;
        getBySubjectId(this.queryBysubjectIdAndExamRecordId).then(response=>{
          // console.log(response);
          if(isNotEmpty(response.data)){
            let answerId = response.data.id;
            getId(answerId).then(response=>{ 
              this.examAnswerForm = response.data;
              // answer_type 答题类型，0：正确，1：错误 
              if(this.correct == true){
                this.examAnswerForm.answerType = 0;
                this.examAnswerForm.markStatus = 1;
              }else if(this.correct == false){
                this.examAnswerForm.answerType = 1;
                this.examAnswerForm.score = 0;
                this.examAnswerForm.markStatus = 1;
              }
              updateExamAnswer(this.examAnswerForm).then(response =>{
                if (response.code === 200) {
                  //this.msgSuccess("修改成功");
                } else {
                  this.msgError(response.msg);
                  }
              });
              // console.log(this.examAnswerForm);
            });
          }else{
            this.examAnswerInfo.id = undefined;
            this.examAnswerInfo.examRecordId = this.query.examRecordId;
            this.examAnswerInfo.subjectId = subjectId;
            this.examAnswerInfo.score = this.subjectInfo.score;
            if(this.correct == true){
              this.examAnswerInfo.answerType = 0;
              this.examAnswerInfo.markStatus = 1;
            }else if(this.correct == false){
              this.examAnswerInfo.answerType = 1;
              this.examAnswerInfo.score = 0;
              this.examAnswerInfo.markStatus = 1;
            }
            addExamAnswer(this.examAnswerInfo).then(response => {
              if (response.code === 200) {
                // this.msgSuccess("新增成功");
              } else {
                this.msgError(response.msg);
              }
            });
          }
        });
        
      },

      // 完成批改
      completeMarking () {
        this.$confirm('确定要提交吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            updateMarkStatus(this.query.examRecordId).then(response=>{
              if(response.code === 200) {
                this.msgSuccess("批改成功");
                this.$router.push({
                  path: `/exam/testTask`
                })
              }else {
                this.msgError(response.msg);
              }
            });
          });
      },
  }
}
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .subject-box {
    // margin-top: 50px;
    // margin-left: 20px;
    margin-top: 150px;
    color:#606266;
  }
  .subject-box-card {
    margin-bottom: 30px;
    min-height: 400px;
    font-size: 18px;
    font-family: Open Sans,sans-serif;
    font-weight: 400;
  }
  .subject-buttons {
    text-align: center;
  }
  .answer-number {
    padding: 12px;
    .number-btn {
      margin: 6px;
    }
  }
  .subject-content{
    margin-top: 18px;
    margin-bottom: 18px;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    font-weight: 300;
    background: #fff;
    z-index: 1;
    position: relative;
    font-size:16px;

  }
  /* 题目 */
  .subject-title {
    font-size: 16px;
    line-height: 22px;
    .subject-title-number {
      display: inline-block;
      line-height: 22px;
    }
    .subject-title-content {
      display: inline-block;
    }
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
        padding: 10px 10px 10px 20px;
        display: block;
        line-height: 1.0;
        transition: color 0.4s;
      }
      /* 选项名称 */
      .subject-option-prefix {
        font-size: 16px;
        display: inline-block;
        font-weight:normal;
        color:#606266;
      }
    }
  }
  .subject-answer {
    padding: 16px;
  }
  .subject-tinymce {
    margin: 12px;
    .tinymce-container{
        border-left: 1px solid #000;
      }
    }
</style>