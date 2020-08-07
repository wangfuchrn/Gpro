<template>
  <div id="entry" class="main">
    <u-header></u-header>
    <section class="container-fluid">
      <template>
        <div class="recondContainer">
          <el-row>
            <el-col :span="20" :offset="2">
              <el-table 
                v-loading="loading" 
                :data="examRecordList" 
                highlight-current-row
                style="width: 100%;"
                >
                <el-table-column label="考试名称" align="center" prop="examination.examinationName" />
                <el-table-column label="考试时间" align="center" prop="startTime" />
                <el-table-column label="批改状态" align="center" prop="markStatus"  >
                  <template slot-scope="scope">
                    <el-tag 
                      :type="
                      scope.row.markStatus=='0' ?'warning'
                      : scope.row.markStatus=='1'?'success':''
                    ">
                        {{scope.row.markStatus=='0'?'待批改'
                        :(scope.row.markStatus=='1'?'已批改':'')
                        }}
                    </el-tag>
                  </template>
                </el-table-column>
                <el-table-column label="操作" align="center">
                  <template slot-scope="scope">
                    <el-button type="success" size="mini" @click="handleDetail(scope.row)" :disabled="scope.row.markStatus !== '1'">成绩详情</el-button>
                  </template>
                </el-table-column>
              </el-table>

              <pagination
                v-show="total>0"
                :total="total"
                :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize"
                @pagination="getList"
              />
            </el-col>
          </el-row>
        </div>
      </template>
    </section>
  </div>
</template> 

<script>
  import UHeader from '../../../components/UserHeader/index.vue'
  import { getUserProfile } from "@/api/system/user"
  import { getSubmitedInfoByUserId, getExamRecordInfo } from '@/api/exam/examRecord'

  export default{
    name:'examRecord',
    components: {
      UHeader,
    },
    data() {
      return {
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 考试记录表格数据
        examRecordList: null,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          userId: undefined
        },
        userInfo: {},
        examRecordParams: {
          examinationId: undefined,
          deptId: undefined,
          userId: undefined,
        },
        examRecordId: undefined,
      }
    },
    created() {
      this.getList();
    },
    methods: {
      getList() {
        this.loading = true;
        getUserProfile().then(response => {
          this.userInfo = response.data;
          this.queryParams.userId = this.userInfo.userId;
          // console.log(this.queryParams.userId);
          getSubmitedInfoByUserId(this.queryParams).then(response=>{
            // console.log(response.rows);
            this.examRecordList = response.rows;
            this.total = response.total;
            this.loading = false;
          });
        });
      },
      // 查看成绩详情
      handleDetail (row) {
          // console.log(row);
          // row.id为考试任务id
          this.examRecordParams.examinationId = row.examinationId;
          this.examRecordParams.deptId = row.deptId;
          this.examRecordParams.userId = this.userInfo.userId;

          getExamRecordInfo(this.examRecordParams).then(response=>{
            if(response.data!=undefined){
              this.examRecordId = response.data.id;
              this.$router.push({
                path: `/ordinaryUser/examRecord/detail/${row.examinationId}-${this.examRecordId}`
              })
            }else{
              this.msgWarning("找不到对应的考试记录")
            }
          });
        }
    },
  }
</script> 

<style  rel="stylesheet/scss" lang="scss">
  .recondContainer {
    // margin-top: 50px;
    // margin-left: 20px;
    margin-top: 150px;
    color:#606266;
  }
</style>