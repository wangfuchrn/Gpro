<!-- 测评中心-考试管理 -->
<template>
  <div class="app-container">
  <!-- <el-row :gutter="20"> -->
    <!--部门数据-->
    <el-col :span="4" :xs="24" >
        <div class="head-container">
          <el-input
            v-model="deptName"
            placeholder="请输入部门名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container">
          <el-tree
            :data="deptOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            default-expand-all
            @node-click="handleNodeClick"
          />
        </div>
    </el-col>
    <el-col :span="20" :xs="24">
      <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="73px">
        <el-form-item label="考试名称" prop="examinationName">
          <el-input
            v-model="queryParams.examinationName"
            placeholder="请输入考试名称"
            clearable
            size="small"
            style="width: 240px"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-button
            class="filter-item"
            type="primary"
            icon="el-icon-search"
            size="mini"
            @click="handleQuery"
          >搜索</el-button>
          <el-button
            class="filter-item"
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['assess:examManage:add']"
          >新增</el-button>
      </el-form>

      <el-table v-loading="loading" :data="examList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="考试名称" align="center" prop="examinationName" :show-overflow-tooltip="true"/>
        <el-table-column label="部门" align="center" prop="dept.deptName" :show-overflow-tooltip="true" />
        <el-table-column label="总分" align="center" prop="totalScore" width="160px"/>
        <el-table-column label="状态" align="center" prop="status">
          <template slot-scope="scope">
            <el-tag 
              :type="scope.row.status=='1' ?'success':'warning'" 
              effect="dark">
              {{scope.row.status=='1'?'已发布':'未发布'}}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['assess:examManage:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-document"
              @click="handleSubjectManagement(scope.row)"
            >题目管理</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-s-promotion"
              @click="handlePublic(scope.row)"
              v-hasPermi="['assess:examManage:testTaskAdd']"
            >发布试卷</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['assess:examManage:remove']"
            >删除</el-button>
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

    <!-- 添加或修改试卷对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="82px">
        <el-form-item label="归属部门" prop="deptId">
          <treeselect v-model="form.deptId" :options="deptOptions" placeholder="请选择归属部门" />
        </el-form-item>
        <el-form-item label="考试名称" prop="examinationName">
          <el-input v-model="form.examinationName" placeholder="请输入考试名称" />
        </el-form-item>
        <el-form-item label="总分" prop="totalScore">
          <el-input v-model="form.totalScore" placeholder="100" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker v-model="form.startTime" type="datetime" format="yyyy-MM-dd HH:mm" value-format="timestamp"/>
        </el-form-item>
        <el-form-item label="结束时间" prop="finishTime">
          <el-date-picker v-model="form.finishTime" type="datetime" format="yyyy-MM-dd HH:mm" value-format="timestamp"/>
        </el-form-item>
        <el-form-item label="考试状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio :label="1">已发布</el-radio>
            <el-radio :label="0">未发布</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="注意事项" prop="attention">
          <el-input v-model="form.attention" type="textarea" placeholder="注意事项" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改试卷对话框 -->
    <el-dialog :title="title" :visible.sync="openPubilc" width="500px">
      
      <el-form ref="publicForm" :model="publicForm" :rules="publicRules" label-width="95px">
        <el-form-item label="考试名称" prop="deptId">
          <el-input v-model="publicForm.examinationName" placeholder="请选择归属部门" readonly />
        </el-form-item>
        <el-form-item label="用户手机号" prop="userPhonenumber">
          <el-input v-model="publicForm.userPhonenumber" placeholder="请输入待测评用户手机号" maxlength="11"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitPublicForm">确 定</el-button>
        <el-button @click="cancelPublic">取 消</el-button>
      </div>
    </el-dialog>
  </div>  
</template> 
<script>
    import {
        listExam,
        getId,
        addExam,
        updateExam,
        delExam,
        listExamSubjects,
        addTestTask,
    } from "@/api/assess/examManage";
    import { treeselect } from "@/api/system/dept";
    import Treeselect from "@riophae/vue-treeselect";
    import "@riophae/vue-treeselect/dist/vue-treeselect.css";
    import { listTestTask} from "@/api/assess/testTask";

    export default {
      name: "examManage",
      components: {
            Treeselect
      },
      data() {
        return {
          // 遮罩层
          loading: true,
          // 部门名称
          deptName: undefined,
          // 选中数组
          ids: [],
          // 非单个禁用
          single: true,
          // 非多个禁用
          multiple: true,
          // 总条数
          total: 0,
          // 岗位表格数据
          examList: null,
          // 弹出层标题
          title: "",
          // 是否显示弹出层
          open: false,
          // 是否显示发布任务弹出层
          openPubilc: false,
          // 状态数据字典
          statusOptions: [],
          // 部门树选项
          deptOptions: undefined,
          // 查询参数
          queryParams: {
            pageNum: 1,
            pageSize: 10,
            examinationName: undefined,
            deptId: undefined
          },
          defaultProps: {
            children: "children",
            label: "label"
          },
          // 表单参数
          form: {},
          //发布表单参数
          publicForm: {},
          rules: {
            examinationName: [
              { required: true, message: "考试名称不能为空", trigger: "blur" }
            ],
            totalScore: [
              { required: true, message: "总分不能为空", trigger: "blur" }
            ],
            deptId: [
              { required: true, message: "归属部门不能为空", trigger: "blur" }
            ],
            startTime: [
              { required: true, message: "开始时间不能为空", trigger: "blur" }
            ],
            finishTime: [
              { required: true, message: "结束时间不能为空", trigger: "blur" }
            ],
          },
          publicRules: {
            userPhonenumber: [
              { required: true, message: "手机号码不能为空", trigger: "blur" },
              {
                pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
                message: "请输入正确的手机号码",
                trigger: "blur"
              }
            ]
          }
        }
      },
      watch: {
        // 根据名称筛选部门树
        deptName(val) {
          this.$refs.tree.filter(val);
        }
      },
      created() {
        // this.get();
        this.getList();
        this.getTreeselect();
      },
      methods: {
        /** 查询题库列表 */
        getList() {
            this.loading = true;
            listExam(this.queryParams).then(response => {
                this.examList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
            
        },
        /** 查询部门下拉树结构 */
        getTreeselect() {
          treeselect().then(response => {
            this.deptOptions = response.data;
          });
        },
        // 取消按钮
        cancel() {
          this.open = false;
          this.reset();
        },
        // 取消发布按钮
        cancelPublic() {
          this.openPubilc = false;
          this.PublicReset();
        },
        // 表单重置
        reset() {
          this.form = {
            id: undefined,
            deptId: undefined,
            examinationName: undefined,
            totalScore: 0,
            status: 0,
            startTime: '',
            attention: '',
            remark: '',
          };
          this.resetForm("form");
        },
        // 发布表单重置
        PublicReset() {
          this.publicForm = {
            id: undefined,
            deptId: undefined,
            examinationId: undefined,
            examinationName: undefined,
          };
          this.resetForm("publicForm");
        },
        // 筛选节点
        filterNode(value, data) {
          if (!value) return true;
          return data.label.indexOf(value) !== -1;
        },
        /** 搜索按钮操作 */
        handleQuery() {
          this.queryParams.pageNum = 1;
          this.getList();
        },
        // 节点单击事件
        handleNodeClick(data) {
          this.queryParams.deptId = data.id;
          this.getList();
        },
        // 多选框选中数据
        handleSelectionChange(selection) {
          this.ids = selection.map(item => item.id)
          this.single = selection.length!=1
          this.multiple = !selection.length
        },
        /** 新增按钮操作 */
        handleAdd() {
          this.reset();
          this.getTreeselect();
          this.open = true;
          this.title = "添加试卷";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
          this.reset();
          this.getTreeselect();
          const id = row.id || this.ids;
          getId(id).then(response => {
            this.form = response.data;
            this.open = true;
            this.title = "修改试卷";
          });
        },
        handlePublic(row) {
          this.PublicReset();
          this.getTreeselect();

          const id = row.id || this.ids;
          this.publicForm.examinationId = id;

          this.publicForm.examinationName = row.examinationName;
          this.publicForm.deptId = row.deptId;

          this.openPubilc = true;
          this.title = "发布任务";
        },
        /** 提交按钮 */
        submitForm: function() {
          this.$refs["form"].validate(valid => {
            if (valid) {
              if (this.form.id != undefined) {
                updateExam(this.form).then(response => {
                  if (response.code === 200) {
                    this.msgSuccess("修改成功");
                    this.open = false;
                    this.getList();
                  } else {
                    this.msgError(response.msg);
                  }
                });
              } else {
                // console.log(this.form);
                addExam(this.form).then(response => {
                  if (response.code === 200) {
                    this.msgSuccess("新增成功");
                    this.open = false;
                    this.getList();
                  } else {
                    this.msgError(response.msg);
                  }
                });
              }
            }
          });
        },
        /** 发布提交按钮 */
        submitPublicForm: function() {
          this.$refs["publicForm"].validate(valid => {
              if (valid) {
                if (this.publicForm.id != undefined) {
                  console.log(this.publicForm.id);
                }else {
                // console.log(this.publicForm);
                addTestTask(this.publicForm).then(response => {
                  if (response.code === 200) {
                    this.msgSuccess("发布成功");
                    this.openPubilc = false;
                    this.getList();
                  } else {
                    this.msgError(response.msg);
                  }
                });
              }
            }
          });
        },
        /** 删除按钮操作 */
        handleDelete(row) {
          const ids = row.id || this.ids;
          this.$confirm('是否确认删除考试编号为"' +ids + '"的数据项?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }).then(function() {
              return delExam(ids);
            }).then(() => {
              this.getList();
              this.msgSuccess("删除成功");
            }).catch(function() {});
        },
        // 加载题目
        handleSubjectManagement(row) {
          this.$router.push({
            //row.id为考试id
            path: `/assess/examManage/subjects/${row.id}`
          })
        },
      }
    }
</script> 