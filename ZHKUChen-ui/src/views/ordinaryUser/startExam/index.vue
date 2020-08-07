<template>
  <div id="entry" class="main">
    <u-header></u-header>
    <section class="container-fluid">
      <template>
        <div class="subject-box">
          <el-row :gutter="30">
            <el-col :span="4">
              <div class="tool-bar">
                <div class="time-remain">
                  考试题目:
                  <!-- <div class="time">
                    <count-down v-on:start_callback="countDownS_cb(1)" v-on:end_callback="countDownE_cb(1)" :current-time="currentTime" :start-time="startTime" :end-time="endTime" :tip-text="'距离考试开始'" :tip-text-end="'距离考试结束'" :end-text="'考试结束'" :hourTxt="':'" :minutesTxt="':'" :secondsTxt="''">
                    </count-down>
                  </div> -->
                </div>
                <div class="answer-number">
                  <el-button class="number-btn" circle v-for="(value, index) in subjectIds" :key="index" @click="toSubject(subjectInfo.id, index, value)" >&nbsp;{{index + 1}}&nbsp;</el-button>
                </div> 
              </div>
            </el-col>
            <el-col :span="18">
              <div class="subject-box-card">
                <div class="subject-exam-title">考试名称：{{examInfo.data.examinationName}}（共{{subjectCount}}题，合计{{examInfo.data.totalScore}}分）</div>
                <!-- 题目内容 -->
                <div class="subject-content">
                  <div class="subject-title">
                    {{subjectIndex}}
                    <span class="subject-title-content" v-html="subjectInfo.subjectName"/>
                    <span class="subject-title-content" v-if="subjectInfo.score !== undefined && subjectInfo.score !== 0">&nbsp;({{subjectInfo.score}})分</span>
                  </div>
                  <ul class="subject-options" v-for="option in choicesoptions" :key="option.id" v-if="subjectInfo.type === 0">
                    <li class="subject-option">
                      <input class="toggle" type="checkbox" :checked="userAnswer === option.optionName" :id="'option' + option.id" @change="toggleOption(option)">
                      <label :for="'option' + option.id">
                        <span class="subject-option-prefix">{{ option.optionName }}&nbsp;</span>
                        <span v-html="option.optionContent" class="subject-option-prefix" />
                      </label>
                    </li>
                  </ul>
                  <div  class="subject-tinymce" v-if="subjectInfo.type === 1">
                    <tinymce ref="editor" :height="height" v-model="userAnswer"/>
                  </div>
                  <ul class="subject-options" v-for="option in judgeoptions" :key="option.id" v-if="subjectInfo.type === 2">
                    <li class="subject-option">
                      <input class="toggle" type="checkbox" :checked="userAnswer === option.optionName" :id="'option' + option.id" @change="toggleOption(option)">
                      <label :for="'option' + option.id">
                        <span class="subject-option-prefix">{{ option.optionName }}&nbsp;</span>
                      </label>
                    </li>
                  </ul>
                  <ul class="subject-options" v-for="option in choicesoptions" :key="option.id" v-if="subjectInfo.type === 3">
                    <li class="subject-option">
                      <input class="toggle" type="checkbox" :checked="isChecked(option.optionName)" :id="'option' + option.id" @change="toggleMultipleOption($event, option)">
                      <label :for="'option' + option.id">
                        <span class="subject-option-prefix">{{ option.optionName }}&nbsp;</span>
                        <span v-html="option.optionContent" class="subject-option-prefix" />
                      </label>
                    </li>
                  </ul>
                </div>

                <div class="subject-buttons" v-if="subjectIds !== ''">
                  <el-button plain @click="last">上一题</el-button>
                  <el-button plain @click="next">下一题</el-button>
                  <el-button type="success" @click="submitExam" v-bind:disabled="disableSubmit">提交</el-button>
                </div>
              </div>
            </el-col>
          </el-row>
        </div> 
      </template>
    </section>
  </div>
</template> 

<script>
  import UHeader from '../../../components/UserHeader/index.vue'
  import moment from 'moment'
  import CountDown from 'vue2-countdown'
  import Tinymce from '@/components/Tinymce'
  import { isNotEmpty, messageSuccess, messageFail, messageWarn, formatDate } from '@/utils/ruoyi'
  import { getAllSubjectByExaminationId } from '@/api/assess/examManage'
  import { getchoicesOptionsInfo } from '@/api/assess/subjectServices'
  import { getUserProfile } from '@/api/system/user'
  import { getId, getBySubjectId, addExamAnswer, updateExamAnswer } from '@/api/exam/examAnswer'
  import { getTestTaskId, updateTestTask } from '@/api/assess/testTask'
  import { updateExamRecord } from '@/api/exam/examRecord'

  export default{
    name:'entry',
    components: {
      UHeader,
      CountDown,
      Tinymce
    },
    props: {
      height: {
        type: Number,
        required: false,
        default: 300
      }
    },
    data () {
      return {
        loadingLast: false,
        loadingNext: false,
        loadingSubmit: false,
        currentTime: 0,
        startTime: 0,
        endTime: 0,
        disableSubmit: true,
        subjectIndex: null,
        query: {
          examinationId: undefined,
          examRecordId: undefined,
          subjectId: undefined,
          userId: undefined,
        },
        subject: {},
        option: '',
        answer: '',
        subjectIds: [],
        subjectStartTime: undefined,
        examInfo: {
          data: {},
          shortanswers: {},
          choices: {},
          judges: {}
        },
        subjectCount: 0,
        allSubjectsInfoArray: [],
        typeArray: [],
        subjectInfo: {},
        userAnswer: '',
        judgeoptions: [
          { id: 1, optionName: '正确' },
          { id: 2, optionName: '错误' }
        ],
        //选择题选项列表
        choicesoptions: [],
        //当前用户信息
        userInfo: {},
        //某个题目的选项答案信息
        examAnswerInfo: {},
        examAnswerForm: {},
        queryBysubjectIdAndExamRecordId: {
          examRecordId: undefined,
          subjectId: undefined,
        },
        queryToGetTestTask: {
          deptId: undefined,
          userPhonenumber: undefined,
          examinationId: undefined
        },
        taskTaskId: undefined,
      }
    },
    created () {
      const examInfo = this.$route.params.id
      if (isNotEmpty(examInfo)) {
        let examInfoArr = examInfo.split('-');
        this.query.examinationId = examInfoArr[0];
        this.query.examRecordId = examInfoArr[1];
        this.queryBysubjectIdAndExamRecordId.examRecordId = this.query.examRecordId;
      }
      this.getByExaminationId();
      this.getUserInfo();
      // this.getCurrentTime();
    },
    methods: {
      getCurrentTime() {
        let date = new Date();
        this.currentTime = date.getTime();
        this.subjectStartTime = date.toLocaleString();
        // console.log(this.currentTime);
        
        // myDate.toLocaleDateString();     
        // //获取当前日期
        // var mytime=myDate.toLocaleTimeString();     
        // //获取当前时间
        // myDate.toLocaleString( );        
        // //获取日期与时间
      },
      getUserInfo() {
        getUserProfile().then(response => {
          //获取当前用户信息
          this.userInfo = response.data;
          this.queryToGetTestTask.userPhonenumber = response.data.phonenumber;
        });
      },
      getByExaminationId() {
        this.disableSubmit = false
        getAllSubjectByExaminationId(this.query.examinationId).then(response => {
          this.examInfo = response;
          console.log(response);
          this.queryToGetTestTask.deptId = response.data.dept.deptId;
          this.queryToGetTestTask.examinationId = response.data.id;
          // console.log(this.queryToGetTestTask);
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
        });
      },
      countDownS_cb: function (x) {
        messageSuccess(this, '考试开始')
      },
      countDownE_cb: function (x) {
        messageWarn(this, '考试结束')
        this.disableSubmit = true
      },

      //跳转题目时获取该题目下的答案信息
      getAnswerBySubjectId() {
        let id = this.subjectInfo.id;
        this.queryBysubjectIdAndExamRecordId.subjectId = this.subjectInfo.id;
        getBySubjectId(this.queryBysubjectIdAndExamRecordId).then(response=>{
            if(isNotEmpty(response.data)){
              this.userAnswer = response.data.answer;
            }else{
              return;
            }
        });
      },

      // 获取答案信息，更新保存当前题目，同时根据序号加载下一题
      saveAndUpdateExamAnswerBySubjectId (subjectId) {
        this.resetExamAnswer();
        this.getAnswerBySubjectId();
        this.queryBysubjectIdAndExamRecordId.subjectId = subjectId;
        getBySubjectId(this.queryBysubjectIdAndExamRecordId).then(response=>{
            if(isNotEmpty(response.data)){
              let answerId = response.data.id;
              getId(answerId).then(response=>{ 
                this.examAnswerForm = response.data;
                let checkanswer = (this.answer!='') ? this.answer:this.examAnswerForm.answer;
                if ((this.examAnswerForm.id != undefined) && (this.examAnswerForm.answer != checkanswer)){
                  this.examAnswerForm.answer = checkanswer;
                  updateExamAnswer(this.examAnswerForm).then(response =>{
                    if (response.code === 200) {
                        // this.msgSuccess("修改成功");
                        this.userAnswer = '';
                        this.getAnswerBySubjectId();
                      } else {
                        this.msgError(response.msg);
                    }
                  });
                }else{
                  this.userAnswer = '';
                  this.getAnswerBySubjectId();
                }
              });
            }else{
              this.examAnswerInfo.id = undefined;
              this.examAnswerInfo.examRecordId = this.query.examRecordId;
              this.examAnswerInfo.subjectId = subjectId;
              this.examAnswerInfo.score = this.subjectInfo.score;
              this.examAnswerInfo.answer = (this.userAnswer!='') ? this.userAnswer:'';
        
              if(this.examAnswerInfo.answer!=''){
                // console.log(this.examAnswerInfo);
                addExamAnswer(this.examAnswerInfo).then(response => {
                  if (response.code === 200) {
                    // this.msgSuccess("新增成功");
                    this.userAnswer = '';
                    this.getAnswerBySubjectId();
                  } else {
                    this.msgError(response.msg);
                  }
                });
              }else{
                this.userAnswer = '';
                this.getAnswerBySubjectId();
              }
            }
        });
      },
      // 获取题目索引
      getSubjectIndex (targetId) {
        for (let subject of this.subjectIds) {
          let { subjectId, index } = subject
          if (subjectId === targetId) {
            return index
          }
        }
        return 1
      },
      // 更新题目索引
      updateSubjectIndex () {
        this.subjectIndex = this.getSubjectIndex(this.subjectInfo.id)
      },
      // 选中选项
      toggleOption (option) {
        this.userAnswer = option.optionName;
        this.answer = this.userAnswer;
      },
      // 选中选项
      toggleMultipleOption ($event, option) {
        if ($event.target.checked) {
          if (!this.userAnswer.includes(option.optionName)) {
            this.userAnswer.push(option.optionName)
          }
        } else {
          this.userAnswer.splice(this.userAnswer.findIndex(item => item === option.optionName), 1)
        }
      },
      isChecked (optionName) {
        return this.userAnswer.includes(optionName)
      },
      resetExamAnswer() {
        this.examAnswerInfo = {
          id: undefined,
          examRecordId: undefined,
          subjectId: undefined,
          answer: '',
          score: 0,
        }
        this.resetForm("examAnswerInfo");
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

        if(subjectId==undefined){
          return;
        }else{
          //获取上一个题目的id
          this.saveAndUpdateExamAnswerBySubjectId(subjectId);
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

                //获取上一个题目的id
                this.saveAndUpdateExamAnswerBySubjectId(this.allSubjectsInfoArray[i].id);

                // this.getAnswerBySubjectId(this.subjectInfo.id);
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
                this.saveAndUpdateExamAnswerBySubjectId(this.allSubjectsInfoArray[i].id);
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
              //获取上一个题目的id
              this.saveAndUpdateExamAnswerBySubjectId(this.allSubjectsInfoArray[i].id);
              // this.getAnswerBySubjectId(this.subjectInfo.id);
              break
            }
          }
        }else{
          return;
        }
      },

      // 提交
      submitExam () {     
        console.log(this.queryToGetTestTask);
        getTestTaskId(this.queryToGetTestTask).then(response=>{
          console.log(response);
          this.taskTaskId = response.data.id;
          this.$confirm('确定要提交吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            this.disableSubmit = true;
            updateTestTask(this.taskTaskId).then(response=>{
              if(response.code === 200) {
                // console.log(1);
              }else {
                this.msgError(response.msg);
              }
            });
            updateExamRecord(this.query.examRecordId).then(response=>{
              if(response.code === 200) {
                // console.log(2);
              }else {
                this.msgError(response.msg);
              }
            });
            this.$router.push({
              path: `/ordinaryUser/exams`
            })
          }).catch(() => {
          })
        });
      },
    },
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
  .tool-bar {
    margin-left: 20px;
    .answer-number {
      padding: 12px;
      .number-btn {
        margin: 6px;
      }
    }
  }
  .time-remain{
    font-size: 16px;
     .time {
      display: inline-block;
      font-size: 16px;
      line-height: 22px;
      color: #FF0000;
      font-weight: 400;
    }
  }

  /* 答题卡 */
  .answer-card-title {
    font-size: 13px;
    color: #3A3E51;
    line-height: 17px;
    padding: 10px 0;
  }
  .answer-card-split {
    width: 100%;
    border-bottom: 1px solid #E6E6E6;
  }
  .answer-card-content {
    padding-bottom: 10px;
    font-size: 0;
    margin-right: -15px;
    > button {
      margin-top: 5px;
    }
  }
  .subject-content{
    margin-top: 18px;
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
      .toggle {
        opacity: 0;
        text-align: center;
        width: 35px;
        /* auto, since non-WebKit browsers doesn't support input styling */
        height: auto;
        position: absolute;
        top: 0;
        bottom: 0;
        margin: auto 0;
        border: none;
        /* Mobile Safari */
        -webkit-appearance: none;
        appearance: none;
      }
      .toggle+label {
        background-image: url('data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23ededed%22%20stroke-width%3D%223%22/%3E%3C/svg%3E');
        background-repeat: no-repeat;
        background-position: center left;
        background-size: 30px;
      }
      .toggle:checked+label {
        background-size: 30px;
        background-image: url('data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23bddad5%22%20stroke-width%3D%223%22/%3E%3Cpath%20fill%3D%22%235dc2af%22%20d%3D%22M72%2025L42%2071%2027%2056l-4%204%2020%2020%2034-52z%22/%3E%3C/svg%3E');
      }
      label {
        word-break: break-all;
        padding: 10px 10px 10px 45px;
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