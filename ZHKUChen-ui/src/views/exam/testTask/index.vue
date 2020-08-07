<!-- 评估中心-成绩管理 -->
<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="88px">
      <el-form-item label="用户手机号" prop="userPhonenumber">
        <el-input
          v-model="queryParams.userPhonenumber"
          placeholder="请输入用户手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="testTaskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="考试名称" align="center" prop="examination.examinationName" :show-overflow-tooltip="true"/>
      <el-table-column label="发布时间" align="center" prop="createTime" />
       />
       <el-table-column label="手机号码" align="center" prop="userPhonenumber" />
       />
      <el-table-column label="部门" align="center" prop="dept.deptName" :show-overflow-tooltip="true" />
      <el-table-column label="答卷状态" align="center" prop="isWritten" :show-overflow-tooltip="true">
        <template slot-scope="scope">
          <el-tag 
            :type="
              scope.row.isWritten=='0' ?'warning'
              : scope.row.isWritten=='1'?'':'success'
            ">
                {{scope.row.isWritten=='0'?'未答'
                :(scope.row.isWritten=='1'?'已答':'')
                }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleDetails(scope.row)"
          >详情</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handleMarking(scope.row)"
            v-bind:disabled="scope.row.isWritten=='0'"
          >批改</el-button>
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
  </div>
</template>

<script>
  import { listTestTask, getByTestTaskId, addTestTask, updateTestTask, getExamByUserPhonenumber} from '@/api/assess/testTask'
  import { getId, choiceslistExamSubjects, judgementlistExamSubjects, shortAnswerlistExamSubjects } from "@/api/assess/examManage";
  import { getuserIdByPhoneNumber } from '@/api/system/user'
  import { getExamRecordInfo } from '@/api/exam/examRecord'

  export default {
    name: "Post",
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 总条数
        total: 0,
        // 考试任务表格数据
        testTaskList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 状态数据字典
        statusOptions: [],
        
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          //考试名称
          userPhonenumber: undefined,
        },
        examRecordParams: {
          examinationId: undefined,
          deptId: undefined,
          userId: undefined,
        },
        examRecordId: undefined,
        // 表单参数
        form: {},
        // 表单校验
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询考试任务列表 */
      getList() {
        this.loading = true;
        listTestTask(this.queryParams).then(response => {
            this.testTaskList = response.rows;
            this.total = response.total;
            this.loading = false;
          }
        );
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!=1
        this.multiple = !selection.length
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 详情按钮操作，跳转到scoreDetails页面 */
      handleDetails(row) {
        // row.id为考试任务id
        this.examRecordParams.examinationId = row.examinationId;
        this.examRecordParams.deptId = row.deptId;
        getuserIdByPhoneNumber(row.userPhonenumber).then(response=>{
          if(response.data != undefined){
            this.examRecordParams.userId = response.data.userId;
            getExamRecordInfo(this.examRecordParams).then(response=>{
              if(response.data!=undefined){
                this.examRecordId = response.data.id;
                this.$router.push({
                  path: `/exam/score/detail/${row.examinationId}-${this.examRecordId}-${row.id}`
                })
              }else{
                this.msgWarning("找不到对应的考试记录")
              }
            });
           }else{
            this.msgWarning("找不到对应的用户");
          }
        });
      },
      handleMarking(row) {
        // row.id为考试任务id
        this.examRecordParams.examinationId = row.examinationId;
        this.examRecordParams.deptId = row.deptId;
        getuserIdByPhoneNumber(row.userPhonenumber).then(response=>{
          if(response.data != undefined){
            this.examRecordParams.userId = response.data.userId;
            getExamRecordInfo(this.examRecordParams).then(response=>{
              if(response.data!=undefined){
                this.examRecordId = response.data.id;
                this.$router.push({
                  path: `/exam/mark/${row.examinationId}-${this.examRecordId}-${row.id}`
                })
              }else{
                this.msgWarning("找不到对应的考试记录")
              }
            });
          }else{
            this.msgWarning("找不到对应的用户");
          }
        });
      },
    },
  }
</script>


