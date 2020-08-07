<template slot-scope="scope">
    <div>
      <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="74px">
                <el-form-item label="题目名称">
                  <el-input
                    v-model="queryParams.subjectName"
                    placeholder="请输入题目名称"
                    clearable
                    size="small"
                    @keyup.enter.native="handleQuery"
                  />
                </el-form-item>
                <el-form-item>
                  <el-button
                    class="filter-item"
                    type="info"
                    icon="el-icon-search"
                    size="mini"
                    plain
                    @click="handleQuery"
                  >搜索</el-button>
                  <el-button
                    class="filter-item"
                    type="primary"
                    icon="el-icon-plus"
                    size="mini"
                    @click="handleAdd"
                    plain
                  >新增</el-button>
                  <el-button
                    class="filter-item"
                    type="primary"
                    icon="el-icon-upload2"
                    size="mini"
                    @click="handleAddByCategory"
                  >从题库中新增</el-button>
                </el-form-item>
              </el-form>
              <el-table v-loading="loading" :data="subjectList" @selection-change="handleSelectionChange">
                    <el-table-column type="selection" width="40" align="center" />
                    <el-table-column label="题目名称" align="center" prop="subjectName" :show-overflow-tooltip="true" />
                    <el-table-column label="类型" align="center" prop="type" :show-overflow-tooltip="true" >
                        <template slot-scope="scope">
                          <el-tag 
                            :type="
                              scope.row.type=='0' ?'success'
                                :(scope.row.type=='1' ? 'info'
                                  :(scope.row.type=='2' ? 'warning' 
                                    : scope.row.type=='3'?'':'danger'))
                            "  
                            >
                            {{scope.row.type=='0'?'选择题'
                               :(scope.row.type=='1'?'简答题'
                                :(scope.row.type=='2'?'判断题'
                                  :(scope.row.type=='3'?'多选题':'')))

                            }}
                          </el-tag>
                        </template>
                          </el-table-column>
                          <el-table-column label="分值" align="center" prop="score" :show-overflow-tooltip="true" width="160px" />
                          <el-table-column label="创建时间" align="center" prop="createTime">
                              <template slot-scope="scope">
                                                    <span>{{ parseTime(scope.row.createTime) }}</span>
                                                  </template>
                          </el-table-column>
                          <el-table-column label="操作" align="center" width="180" class-name="small-padding fixed-width">
                              <template slot-scope="scope">
                        
                                <el-button
                                  size="mini"
                                  type="text"
                                  icon="el-icon-edit"
                                  @click="handleUpdate(scope.row)"
                                  v-hasPermi="['assess:judgement:edit']"
                                >修改</el-button>
                                <el-button
                                  size="mini"
                                  type="text"
                                  icon="el-icon-view"
                                  @click="handleViewSubject(scope.row)"
                                >预览</el-button>
                                <el-button
                                  v-if="scope.row.userId !== 1"
                                  size="mini"
                                  type="text"
                                  icon="el-icon-delete"
                                  @click="handleDelete(scope.row)"
                                  v-hasPermi="['assess:judgement:remove']"
                                >删除</el-button>
                              </template>
                          </el-table-column>
                          </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
    <el-dialog :title="title" :visible.sync="open" width="600px">
        <el-form ref="form" :model="form" :rules="rules" label-width="82px">
            <el-row>
                <el-col :span="24">
                    <el-form-item label="上级题库" prop="categoryId">
                        <treeselect v-model="form.categoryId" :options="subjectCategoryOptions" placeholder="选择上级题库" />
                    </el-form-item>
                </el-col>
                <el-col :span="24">
                    <el-form-item label="题目名称" prop="subjectName">
                        <el-input type="textarea" :rows="2" v-model="form.subjectName" placeholder="请输入题目名称" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="题目分值" prop="score">
                        <el-input-number v-model="form.score" controls-position="right" :min="0" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="难度" prop="level">
                            <el-rate v-model="form.level" :max="4 " :texts="[ '简单', '一般', '略难', '非常难'] " show-text/>
                    </el-form-item>
                </el-col>
                <el-col :span="24 ">
                     <el-form-item label="参考答案" prop="answer">
                        <el-input v-model="form.answer" placeholder="请输入参考答案" />
                    </el-form-item>
                 </el-col>
                 <el-col :span="24">
                    <el-form-item label="解析" prop="analysis">
                        <el-input v-model="form.analysis" placeholder="请输入解析" />
                    </el-form-item>
                </el-col>
              </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer">
           <el-button type="primary" @click="submitForm">确 定</el-button>
              <el-button @click="cancel">取 消</el-button>
          </div>
    </el-dialog>

    <!-- 预览题目 -->
      <el-dialog title="预览题目" :visible.sync="dialogViewVisible" width="60%" top="10vh">
        <div class="subject-title" style="padding:10px;">
          <span class="subject-title-content" v-html="form.subjectName"/>
          <span class="subject-title-content">&nbsp;({{form.score}})分</span>
        </div>
        <div slot="footer" class="dialog-footer" style="margin-right:12px;">
          <el-button type="primary" @click="dialogViewVisible = false">确 定</el-button>
        </div>
      </el-dialog>
    </div>
</template>

<script>
  import {
          shortAnswerlistSubject,
          shortAnswergetSubject,
          shortAnsweraddSubject,
          shortAnswerputSubject,
          shortAnswerdelSubject
    } from "@/api/assess/subjectServices";
    import { shortAnswerlistExamSubjects, removeExamSubjectById } from  "@/api/assess/examManage";
    import { treeselect } from "@/api/assess/subjectCategory";
    import { getToken } from "@/utils/auth";
    import Treeselect from "@riophae/vue-treeselect";
    import "@riophae/vue-treeselect/dist/vue-treeselect.css";
    import { isNotEmpty } from '@/utils/ruoyi'

    export default {
        name: "ShortAnswerSub",
        components: {
            Treeselect,
        },
        props: {
          shortAnswerList: {
            type: Array
          },
          isShortAnswerTotal: {
            type: Number
          },
          clickQueryParams: {
            type: Object
          }
        },
        data() {
            return {
                // 用户表格数据
                subjectList:this.shortAnswerList,
                // 总条数
                total: this.isShortAnswerTotal,
                // 遮罩层
                loading: true,
                // 选中数组
                ids: [],
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 题库名称
                categoryName: undefined,
                // 弹出层标题
                title: " ",
                // 题目类别树选项
                subjectCategoryOptions: undefined,
                // 预览窗口状态
                dialogViewVisible: false,
                // 是否显示弹出层
                open: false,
                // 表单参数
                form: {},
                //查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 5,
                    subjectName: undefined,
                    categoryId: undefined,
                },
                currentCategoryId: '',
                // 表单校验
                rules: {
                    categoryId: [{
                        required: true,
                        message: "上级题库不能为空 ",
                        trigger: "blur "
                    }],
                    subjectName: [{
                        required: true,
                        message: "题目名称不能为空 ",
                        trigger: "blur "
                    }],
                    score: [{
                        required: true,
                        message: "题目分值不能为空 ",
                        trigger: "blur "
                    }],
                },
            }
        },
        watch:{ 
          shortAnswerList(val) {
            this.subjectList = val
          },
          isShortAnswerTotal(val) {
            this.total =val
          }
        },
        created() {
          this.getList();
          this.getTreeselect();
        },
        methods: {
            getTreeselect() {
              treeselect().then(response => {
                this.subjectCategoryOptions = response.data;
              });
            },
            /** 查询题库列表 */
            getList() {
                this.loading = true;

                let subjectInfo = this.$route.params.id;
                if (!isNotEmpty(subjectInfo)) {
                  return
                }
                let subjectInfoArr = subjectInfo.split('-');
                var examid = subjectInfoArr[0];
                // console.log(examid);

                shortAnswerlistExamSubjects(examid).then(response => {
                    this.subjectList = response.rows;
                    this.$emit('shortanswerListEditData',response.rows);
                    this.total = response.total;
                    this.loading = false;
                });
            },
            handleQuery() {
              this.queryParams.page = 1;
              this.getList();
            },
            // 多选框选中数据
            handleSelectionChange(selection) {
              this.ids = selection.map(item => item.id);
              this.single = selection.length != 1;
              this.multiple = !selection.length;
            },
            /** 新增按钮操作 */
            handleAdd() {
              this.reset();
              this.getTreeselect();
              this.open = true;
              this.title = "添加题目";
            },
            // 从题库中新增题目
            handleAddByCategory() {
              this.$router.push({
                path: `/assess/examManage/addSubjects/${this.examinationId}`
              })
            },
            //预览题目
            handleViewSubject (row) {
            // 加载题目信息
              shortAnswergetSubject(row.id).then(response => {
                  this.form = response.data;
                  this.dialogViewVisible = true;
              });
            },
            // 取消按钮
            cancel() {
                this.open = false;
                this.reset();
            },
            //表单重置
            reset() {
                this.form = {
                    id: undefined,
                    categoryId: undefined,
                    subjectName: undefined,
                    score: undefined,
                };
                this.resetForm("form ");
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                this.getTreeselect();
                shortAnswergetSubject(row.id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改题目 ";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            shortAnswerputSubject(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功 ");
                                    this.open = false;
                                    this.getList();
                                } else {
                                    this.msgError(response.msg);
                                }
                            });
                        } else {
                            shortAnsweraddSubject(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("新增成功 ");
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
            /** 删除按钮操作 */
            handleDelete(row) {
                const ids = row.id || this.ids;
                this.$confirm('是否确认从试卷中删除名称为" ' + row.subjectName + ' "的题目?', "警告 ", {
                    confirmButtonText: "确定 ",
                    cancelButtonText: "取消 ",
                    type: "warning "
                }).then(function() {
                    return removeExamSubjectById(ids);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功 ");
                }).catch(function() {});
            }
        }
    }

</script>

<style lang="scss" rel="stylesheet/scss" scoped>
  .subject-title {
    font-size: 18px;
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

      .toggle + label {
        background-image: url('data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23ededed%22%20stroke-width%3D%223%22/%3E%3C/svg%3E');
        background-repeat: no-repeat;
        background-position: center left;
        background-size: 30px;
      }

      .toggle:checked + label {
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
        display: inline-block
      }
    }
  }
</style>