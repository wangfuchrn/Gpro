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
                    icon="el-icon-check"
                    @click="addSubject(scope.row)"
                  >选择</el-button>
                </template>
            </el-table-column>
      </el-table>

      <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
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
    import { isNotEmpty } from '@/utils/ruoyi'
    import { addSubjects } from '@/api/assess/examManage'

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
                //当前试卷ID
                examinationId: undefined,
                examSubjectInfo: {},
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
          this.examinationId = this.$route.params.id;
          //this.getList();
          this.getTreeselect();
        },
        methods: {
            getTreeselect() {
              treeselect().then(response => {
                this.subjectCategoryOptions = response.data;
                this.loading = false;
              });
            },
            /** 查询题库列表 */
            getList() {
                this.loading = true;
                this.queryParams.categoryId = this.clickQueryParams.categoryId;
                choiceslistSubject(this.queryParams).then(response => {
                    this.subjectList = response.rows;
                    this.$emit('choicesListEditData',response.rows);
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
            //表单重置
            reset() {
              this.examSubjectInfo = {
                id:undefined,
                examinationId: parseInt(this.examinationId),
                categoryId: undefined,
                subjectId: undefined,
              }
            },
            addSubject(row) {
              // console.log(row);
              this.reset();
              this.examSubjectInfo.categoryId = parseInt(row.categoryId);
              this.examSubjectInfo.subjectId = row.id;
              // console.log(this.examSubjectInfo);
              addSubjects(this.examSubjectInfo).then(response => {
                if(response.code === 200) {
                    //this.msgSuccess("从题库中新增成功");
                    this.$confirm('新增成功! 是否回到考试题目管理页面? ',"提示",{
                        confirmButtonText: "确定 ",
                        cancelButtonText: "取消 ",
                        type: "warning "
                    }).then(function() {
                      return;
                    }).then(() =>{
                      this.$router.push({
                        path: `/assess/examManage/subjects/${this.examinationId}`
                      })
                    })
                } else {
                    this.msgError(response.msg);
                }
              })
            },
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