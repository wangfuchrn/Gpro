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
                  >新增</el-button>
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
                          ">
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
                  <el-col :span="12" >
                      <el-form-item label="题目分值" prop="score">
                          <el-input-number v-model="form.score" controls-position="right" :min="0" />
                      </el-form-item>
                  </el-col>
                  <el-col :span="12">
                      <el-form-item label="难度" prop="level">
                          <el-rate v-model="form.level" :max="4 " :texts="[ '简单', '一般', '略难', '非常难'] " show-text/>
                      </el-form-item>
                  </el-col>
                  <el-col :span="24" style="height:30px;">
                       <el-form-item label="参考答案" prop="answer">
                        <el-row :gutter="20">
                           <el-col :span="10">
                            <el-radio-group v-model="form.type">
                              <el-radio :label="0">单选题</el-radio>
                              <el-radio :label="3">多选题</el-radio>
                            </el-radio-group>
                          </el-col>
                          <el-col :span="12">
                            <el-radio-group v-model="form.answer" v-if="form.type === 0">
                              <el-radio :label="'A'">A</el-radio>
                              <el-radio :label="'B'">B</el-radio>
                              <el-radio :label="'C'">C</el-radio>
                              <el-radio :label="'D'">D</el-radio>
                            </el-radio-group>
                            <el-checkbox-group v-model="form.answer" v-if="form.type === 3">
                                <el-checkbox :label="'A'" :key="'A'">A</el-checkbox>
                                <el-checkbox :label="'B'" :key="'B'">B</el-checkbox>
                                <el-checkbox :label="'C'" :key="'C'">C</el-checkbox>
                                <el-checkbox :label="'D'" :key="'D'">D</el-checkbox>
                            </el-checkbox-group>
                          </el-col>
                        </el-row>
                      </el-form-item>
                   </el-col>
                   <el-col :span="24">
                      <el-divider>选项列表</el-divider>
                      <el-form-item  v-for="option in form.optionsId"
                          :key="option.optionName"
                          >
                          <el-row :gutter="20">
                            <el-col :span="2" style="padding:10px;">
                              <label><span>{{option.optionName}}</span></label>
                            </el-col>
                            <el-col :span="14">
                                <el-input type="textarea" :rows="2" v-model="option.optionContent" placeholder="请输入选项描述" />
                            </el-col>
                            <el-col :span="4" style="padding:10px;">
                                <el-button >删 除</el-button>
                            </el-col>
                          </el-row>
                      </el-form-item>
                      <!-- <el-form-item 
                          v-model="form.optionsId"
                          v-for="option in form.optionsId"
                          :key="option.optionName"
                          >
                          <el-row :gutter="20">
                            <el-col :span="2" style="padding:10px;">
                              <label></label>
                              <label v-model="form.optionsId.optionName"><span>{{option.optionName}}</span></label>
                            </el-col>
                            <el-col :span="14">
                                <el-input type="textarea" :rows="2" v-model="form.optionsId.optionContent" placeholder="请输入选项描述" />
                            </el-col>
                            <el-col :span="4" style="padding:10px;">
                                <el-button >删除</el-button>
                            </el-col>
                          </el-row>
                      </el-form-item> -->
                  </el-col>
                   <el-col :span="24" style="height: 30px;">
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
        <ul v-if="form.type === 0 || form.type === 3" class="subject-options" style="padding:10px;">
          <li class="subject-option" v-for="(option) in form.optionsId" :key="option.id">
            <input class="toggle" type="checkbox">
            <label><span class="subject-option-prefix">{{option.optionName}}&nbsp;</span><span v-html="option.optionContent" class="subject-option-prefix"></span></label>
          </li>
        </ul>
        <ul v-if="form.type === 2" class="subject-options">
          <li class="subject-option">
            <input class="toggle" type="checkbox">
            <label><span class="subject-option-prefix">正确</span></label>
          </li>
          <li class="subject-option">
            <input class="toggle" type="checkbox">
            <label><span class="subject-option-prefix">错误</span></label>
          </li>
        </ul>
        <div slot="footer" class="dialog-footer" style="margin-right:12px;">
          <el-button type="primary" @click="dialogViewVisible = false">确 定</el-button>
        </div>
      </el-dialog>
    </div>
</template>

<script>
    import {
          choiceslistSubject,
          choicesgetSubject,
          choicesaddSubject,
          choicesputSubject,
          choicesdelSubject,
          choiceOptionsaddSubject,
          choiceOptionsputSubject,
          
    } from "@/api/assess/subjectServices";
    import { treeselect } from "@/api/assess/subjectCategory";
    import { getToken } from "@/utils/auth";
    import Treeselect from "@riophae/vue-treeselect";
    import "@riophae/vue-treeselect/dist/vue-treeselect.css";
    export default {
        name: "ChoicesSub",
        components: {
            Treeselect,
        },
        props: {
          choicesList: {
            type: Array
          },
          isChoicesTotal: {
            type: Number
          },
          clickQueryParams: {
            type: Object
          }
        },
        data() {
            return {
                // 用户表格数据
                subjectList:this.choicesList,
                // 总条数
                total:this.isChoicesTotal,
                // 遮罩层
                loading: true,
                // 选中数组
                ids: [],
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 弹出层标题
                title: " ",
                // 题目类别树选项
                subjectCategoryOptions: undefined,
                // 预览窗口状态
                dialogViewVisible: false,
                // 是否显示弹出层
                open: false,
                // 表单参数
                form: {
                  answer: [],
                },
                // answers:{},
                //查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 5,
                    subjectName: undefined,
                    categoryId: undefined,
                },
                currentCategoryId: '',
                //选择题选项
                choicesOptions:[],
                // choicesOptions: [
                //   {optionName: 'A', optionContent: '' },
                //   {optionName: 'B', optionContent: '' },
                //   {optionName: 'C', optionContent: '' },
                //   {optionName: 'D', optionContent: '' },
                // ],
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
          choicesList(val) {
            this.subjectList = val
          },
          isChoicesTotal(val) {
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
                //console.log(this.clickQueryParams);
                this.queryParams.categoryId = this.clickQueryParams.categoryId;
                choiceslistSubject(this.queryParams).then(response => {
                    this.subjectList = response.rows;
                    this.$emit('choicesListEditData',response.rows);
                    // this.$emit(isChoicesTotal,total);
                    this.total = response.total;
                    this.loading = false;
                    // console.log(this.queryParams);
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
            //预览题目
            handleViewSubject (row) {
            // 加载题目信息
              choicesgetSubject(row.id).then(response => {
                  this.form = response.data;
                  this.form.optionsId = response.optionsId;
                  // this.choicesOptions = this.form.optionsId;
                  // console.log(this.form.optionsId);
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
                    level: undefined,
                    answer: undefined,
                    analysis: undefined,
                    optionsId: [
                      {optionName: 'A', optionContent: '' },
                      {optionName: 'B', optionContent: '' },
                      {optionName: 'C', optionContent: '' },
                      {optionName: 'D', optionContent: '' },
                    ],

                };
                this.resetForm("form");
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                this.getTreeselect();
                choicesgetSubject(row.id).then(response => {
                   console.log(response);
                    this.form = response.data;
                    this.form.optionsId = response.optionsId;
                    // console.log(this.form.answer);
                    if(this.form.type===3){

                      //将answer(String形式)依据逗号划分，
                      //划分后answerSplit变为Object形式
                      var answerSplit = this.form.answer.split(",");

                      // console.log(typeof(answerSplit[Object.keys(answerSplit)[0]]));
                      // for(var i=0;i<answerSplit.length;i++){
                      //   console.log(answerSplit[Object.keys(answerSplit)[i]]);
                      // }
                      // this.form["answers"] = answerSplit[Object.keys(answerSplit)[0]];
                      // this.form["answers"] = answerSplit;

                      //给表单form的answer插入新值，即把string "" 换成object []
                      //因为el-checkbox-group识别的是object
                      this.form.answer = answerSplit;
                      // console.log(this.form.answer);
                    }
                  
                    // console.log(this.form);
                    this.open = true;
                    this.title = "修改题目";
                });
            },
            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                          //要把多选题答案answer从Object [] 改成 String ""
                          if(this.form.type===3){
                            this.form.answer = this.form.answer.toString();
                          }
                            // console.log(typeof(this.form.answer));
                            choicesputSubject(this.form).then(response => {
                                // console.log(this.form);
                                this.choicesOptions = this.form.optionsId;
                                // console.log(this.choicesOptions);
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功 ");
                                    this.open = false;
                                    var options = {};
                                    options = this.form.optionsId;
                                    // delete options.level;
                                    delete options.categoryId;
                                    delete options.type;
                                    delete options.answer;
                                    delete options.score;
                                    delete options.analysis;
                                    delete options.level;
                                    console.log(options);
                                    console.log(typeof(options));
                                    for(var i=0;i<options.length;i++){
                                      choiceOptionsputSubject(options[i]);
                                    }
                                    this.getList();
                                } else {
                                    this.msgError(response.msg);
                                }
                            });

                        } else {
                            choicesaddSubject(this.form).then(response => {
                               
                                if (response.code === 200) {
                                    this.msgSuccess("新增成功 ");
                                    this.open = false;
                                    this.getList();
                                    console.log(this.form);
                                    var options = {};
                                    options = this.form.optionsId;
                                    
                                    for(var i=0;i<options.length;i++){
                                      options.subjectChoicesId = this.form.id;
                                      console.log(options);

                                      // delete options.categoryId;
                                      // delete options.type;
                                      // delete options.answer;
                                      // delete options.score;
                                      // delete options.analysis;
                                      // delete options.level;
                                      // choiceOptionsaddSubject(options[i]);
                                    }
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
                this.$confirm('是否确认删除名称为" ' + row.subjectName + ' "的数据项?', "警告 ", {
                    confirmButtonText: "确定 ",
                    cancelButtonText: "取消 ",
                    type: "warning "
                }).then(function() {
                    return choicesdelSubject(row.id);
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