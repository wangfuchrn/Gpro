<template>
  <div>
    <!-- 考试卡片列表 -->
    <div class="exam-card-list">
      <transition name="fade-transform" mode="out-in" v-for="exam in examList" :key="exam.id">
        <div class="card-item" v-show="exam.show">
          <div>
            <a href="javascript: void(-1);" class="card-item-snapshoot"
                 :style="'background-image: url(' + examImage + ');'" @click="startExam(exam)">
            </a>
          </div>
          <div class="card-item-detail">
            <div>
              <h5>
                <div class="card-item-name mb-12">{{exam.examination.examinationName}}</div>
              </h5>
            </div>
            <div class="card-item-exam" >
              <div class="card-item-exam-detail mb-12">
                <a href="#">{{ exam.dept.deptName }}</a>
              </div>
              <div class="card-item-exam-detail mb-12">
                <a href="#">{{ exam.examination.startTime | timeFilter }}~{{ exam.examination.finishTime | timeFilter }}</a>
              </div>
            </div>
          </div>
        </div>
      </transition>
      <i v-if="examList !== undefined && examList.length > 0" v-for="count in (examList.length)" :key="count"></i>
    </div>
    <el-row style="text-align: center; margin-bottom: 50px;">
      <el-col :span="24">
        <el-button type="default" @click="scrollList" :loading="loading" style="margin-bottom: 100px;">加载更多</el-button>
      </el-col>
    </el-row>
  </div>
</template> 

<script>
  import PanThumb from '@/components/PanThumb'
  import { getUserProfile } from "@/api/system/user"
  import { getExamByUserPhonenumber } from '@/api/assess/testTask'
  import { getExamInfoId, getAllSubjectByExaminationId } from '@/api/assess/examManage'
  import moment from 'moment'
  import { isNotEmpty, messageWarn, formatDate } from '@/utils/ruoyi'
  import { addExamRecord, getExamRecordInfo } from '@/api/exam/examRecord'

  export default {
    name: "unWrittenExamsList",
    components: { PanThumb },
    filters: {
      timeFilter (time) {
        return formatDate(new Date(time), 'yyyy-MM-dd hh:mm')
      }
    },
    data() {
      return {
        examImage: require('@/assets/exams/' + 2 + '.jpeg'),
        //examImage: require('@/assets/exams/' + Math.floor(Math.random()*15) + '.jpeg'),
        // 考试任务总条数
        total: 0,
        loading: true,
        examList: [],
        isLastPage: false,
        //查询参数
        query: {
          sort: 'id',
          order: ' asc',
          pageNum: 1,
          pageSize: 6,
          examinationName: '',
          userPhonenumber: undefined,
          status: 0
        },
        examRecordForm: {},
        userInfo: {},
        currentTime: 0,
        queryParams: {
            examinationId: undefined,
            deptId: undefined,
            userId: undefined
        },
        examRecordId:undefined,
      }
    },
    created() {
      // 加载考试列表
      this.getExamList();
      if (this.$route.query.query !== '') {
        this.query.examinationName = this.$route.query.query
      }
      this.query.pageNum = 1
    },
    methods: {
      getExamList(){
        this.loading = true
        getUserProfile().then(response => {
          //获取当前用户手机号
          this.query.userPhonenumber = response.data.phonenumber;
          this.userInfo = response.data;
          //获取当前用户的未答卷的考试任务
          getExamByUserPhonenumber(this.query).then(response => {
            this.total = response.total;
            this.updateExamList(response.rows);
            this.loading = false;
          });
        });
      },
      scrollList() {
          if (this.loading) {
            messageWarn(this, '正在拼命加载！')
            return
          }
          this.loading = true
          setTimeout(() => {
            this.query.pageNum++
            getUserProfile().then(response => {
              this.query.userPhonenumber = response.data.phonenumber;

              getExamByUserPhonenumber(this.query).then(response => {
                  this.total = response.total;
                  this.updateExamList(response.rows);
                  this.loading = false
                }).catch(() => {
                  messageWarn(this, '加载考试失败！')
                })
              });
            }, 1000)
      },
      updateExamList (list) {
        if (list === undefined || list.length === 0) {
          messageWarn(this, '暂无更多数据！')
          return list
        }
        list.forEach(item => {
          item.show = false
        })
        if (this.examList.length === 0) {
          this.examList = list
        } else {
          list.forEach(item => {
            let exist = false
            for (let i = 0; i < this.examList.length; i++) {
              if (this.examList[i].id === item.id) {
                exist = true
              }
            }
            if (!exist) {
              this.examList.push(item)
            }
          })
        }
        for (let i = 0; i < list.length; i++) {
          setTimeout(() => {
            list[i].show = true;
          }, 250 + (100 * i))
        }
      },
      reset() {
        this.examRecordForm = {
          id: undefined,
          deptId: undefined,
          userId: this.userInfo.userId,
          examinationId: undefined,
          startTime: 0,
        };
      },

      //标记，此方法能获取到当前时间
      getCurrentTime() {
        var d = new Date();
        var time = d.getFullYear() + "-" + ((d.getMonth() + 1) < 10 ? "0" + (d.getMonth() + 1) : (d.getMonth() + 1)) + "-" + (d.getDate() < 10 ? "0" + d.getDate() : d.getDate()) + " " + (d.getHours() < 10 ? "0" + d.getHours() : d.getHours()) + ":" + (d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes())+ ":" + (d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds());
        this.currentTime = time;
        // console.log(this.currentTime);
      },

      startExam(exam) {
        this.reset();
        this.examRecordForm.deptId = exam.deptId;
        this.examRecordForm.examinationId = exam.examinationId;
        this.queryParams.userId =this.userInfo.userId;
        this.queryParams.deptId = exam.deptId;
        this.queryParams.examinationId = exam.examinationId;
        
        this.$confirm('确定要开始吗?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {
            addExamRecord(this.examRecordForm).then(response=>{
              if (response.code === 200) {
                // this.msgSuccess("新增成功");
                getExamRecordInfo(this.queryParams).then(response=>{
                  // console.log(response);
                  this.examRecordId = response.data.id;
                  this.$router.push({
                    //row.id为考试id
                    path: `/ordinaryUser/exams/start/${exam.examinationId}-${this.examRecordId}`
                  })
                });
              } else {
                this.msgError(response.msg);
              }
            }); 
          }).catch(() => {
            //console.log('取消考试');
          })
      },
    },
    // watch: {
    //   $route(){
    //       this.getExamList();
    //   }
    // },
  }
</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .exam-card-list {
    a{
      text-decoration: none;
    }
    width: 100%;
    height: auto;
    overflow: auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    flex-direction: row;
    .card-item {
      width: 30%;
      position: relative;
      margin-bottom: 100px;
      border-radius: 6px;
      box-shadow: 0 3px 8px rgba(0, 0, 0, 0.15);
      .card-item-snapshoot {
        border: 1px solid rgba(0,0,0,.15);
        background-origin: border-box;
        background-size: cover;
        background-color: #f0f0f0;
        background-position: 49% 38% ;
        height: 172px;
        display: block;
        border-radius: 6px 6px 0 0;
      }
      .card-item-detail {
        padding: 20px;
        .card-item-name {
          display: -webkit-box;
          overflow: hidden;
        }
        .card-item-exam {
          font-weight: 300;
          font-style: normal;
          letter-spacing: 0;
          .card-item-exam-detail {
            color: rgba(0,0,0,.54);
            fill: rgba(0,0,0,.54);
            a {
              color: rgba(0, 0, 0, 0.4);
              display: inline-block;
              font-size: 14px;
              font-weight: 400;
              margin-right: 10px;
              &:hover {
                color: #000;
              }
            }
          }
        }
      }
    }
    >i {
      width: 30%;
      padding-right: 12px;
    }
  }
</style>