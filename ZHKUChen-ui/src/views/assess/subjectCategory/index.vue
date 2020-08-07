<!-- 测评中心-题库管理 -->
<template>
  <div class="app-container">
    <el-form :inline="true">
      <el-form-item label="题库名称">
        <el-input
          v-model="queryParams.categoryName"
          placeholder="请输入题库名称"
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
          v-hasPermi="['assess:subjectCategory:add']"
        >新增</el-button>
      </el-form-item>
    </el-form>

    <el-table
          v-loading="loading"
          :data="subjectCategoryList"
          row-key="id"
          default-expand-all
          :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
        >
        <el-table-column prop="categoryName" label="题库名称" width="260"></el-table-column>
        <el-table-column prop="orderNum" label="排序" width="200"></el-table-column>
        <el-table-column prop="categoryDesc" label="题库描述" width="260"></el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime" width="200">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
                  <el-button 
                    size="mini" 
                    type="text" 
                    icon="el-icon-edit" 
                    @click="handleUpdate(scope.row)"
                    v-hasPermi="['assess:subjectCategory:edit']"
                  >修改</el-button>
                  <el-button 
                    size="mini" 
                    type="text" 
                    icon="el-icon-plus" 
                    @click="handleAdd(scope.row)"
                    v-hasPermi="['assess:subjectCategory:add']"
                  >新增</el-button>
                  <el-button
                    v-if="scope.row.parentId != 0"
                    size="mini"
                    type="text"
                    icon="el-icon-delete"
                    @click="handleDelete(scope.row)"
                    v-hasPermi="['assess:subjectCategory:remove']"
                  >删除</el-button>
                </template>
        </el-table-column>
    </el-table>

    <!-- 添加或修改题库对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px">
        <el-form ref="form" :model="form" :rules="rules" label-width="82px">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="归属部门" prop="deptId">
                      <treeselect v-model="form.deptId" :options="deptOptions" placeholder="请选择归属部门" />
                    </el-form-item>
                </el-col>
                <el-col :span="12" v-if="form.parentId !== 0">
                    <el-form-item label="上级题库" prop="parentId">
                        <treeselect 
                            v-model="form.parentId" 
                            :options="subjectCategoryOptions" 
                            :normalizer="normalizer" 
                            placeholder="选择上级题库" 
                        />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="题库名称" prop="categoryName">
                        <el-input v-model="form.categoryName" placeholder="请输入题库名称" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="题库描述" prop="categoryDesc">
                        <el-input v-model="form.categoryDesc" placeholder="请输入题库描述" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="显示排序" prop="orderNum">
                        <el-input-number v-model="form.orderNum" controls-position="right" :min="0" />
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
        </div>
    </el-dialog>
</div>

</template>
<script>
    import {
        listSubjectCategory,
        getId,
        addSubjectCategory,
        updateSubjectCategory,
        delSubjectCategory
    } from "@/api/assess/subjectCategory";
    import { treeselect } from "@/api/system/dept";
    import Treeselect from "@riophae/vue-treeselect";
    import "@riophae/vue-treeselect/dist/vue-treeselect.css";

    export default {
        name: "subjectCategory",
        components: {
            Treeselect
        },
        data() {
            return {
                // 遮罩层
                loading: true,
                // 表格树数据
                subjectCategoryList: [],
                // 弹出层标题
                title: "",
                // 题库类别树选项
                subjectCategoryOptions: [],
                // 是否显示弹出层
                open: false,
                // 表单参数
                form: {},
                // 部门树选项
                deptOptions: undefined,
                //查询参数
                queryParams: {
                    categoryName: undefined
                },
                // 表单校验
                rules: {
                    parentId: [{
                        required: true,
                        message: "上级题库不能为空",
                        trigger: "blur"
                    }],
                    categoryName: [{
                        required: true,
                        message: "题库名称不能为空",
                        trigger: "blur"
                    }],
                    deptId: [{ 
                        required: true, 
                        message: "归属部门不能为空", 
                        trigger: "blur" 
                    }],
                    orderNum: [{
                        required: true,
                        message: "菜单顺序不能为空",
                        trigger: "blur"
                    }],
                }
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询题库列表 */
            getList() {
                this.loading = true;
                listSubjectCategory(this.queryParams).then(response => {
                    this.subjectCategoryList = this.handleTree(response.data, "id");
                    this.loading = false;
                });
            },

            /** 转换题库数据结构 */
            normalizer(node) {
                if (node.children && !node.children.length) {
                    delete node.children;
                }
                return {
                    id: node.id,
                    label: node.categoryName,
                    children: node.children
                };
            },

            /** 查询菜单下拉树结构 */
            getDeptTreeselect() {
                treeselect().then(response => {
                    this.deptOptions = response.data;
                });
            },
            getTreeselect() {
                listSubjectCategory().then(response => {
                    this.subjectCategoryOptions = [];
                    const subjectCategory = {
                        id: 0,
                        categoryName: '主类目',
                        children: []
                    };
                    subjectCategory.children = this.handleTree(response.data, "id");
                    this.subjectCategoryOptions.push(subjectCategory);
                });
            },

            // 取消按钮
            cancel() {
                this.open = false;
                this.reset();
            },
            // 表单重置
            reset() {
                this.form = {
                    id: undefined,
                    deptId: undefined,
                    parentId: undefined,
                    categoryName: undefined,
                    orderNum: undefined,
                    categoryDesc: undefined,
                };
                this.resetForm("form");
            },

            /** 搜索按钮操作 */
            handleQuery() {
                this.getList();
            },

            /** 新增按钮操作 */
            handleAdd(row) {
                this.reset();
                this.getTreeselect();
                this.getDeptTreeselect();
                if (row != undefined) {
                    this.form.parentId = row.id;
                }
                this.open = true;
                this.title = "添加题库";
            },

            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                this.getTreeselect();
                this.getDeptTreeselect();
                getId(row.id).then(response => {
                    this.form = response.data;
                    console.log(this.form.ancestry);
                    console.log(typeof(this.form.ancestry));
                    this.open = true;
                    this.title = "修改题库";
                });
            },

            /** 提交按钮 */
            submitForm: function() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != undefined) {
                            updateSubjectCategory(this.form).then(response => {
                                if (response.code === 200) {
                                    this.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                } else {
                                    this.msgError(response.msg);
                                }
                            });
                        } else {
                            addSubjectCategory(this.form).then(response => {
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

            /** 删除按钮操作 */
            handleDelete(row) {
                this.$confirm('是否确认删除名称为"' + row.categoryName + '"的数据项?', "警告", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(function() {
                    return delSubjectCategory(row.id);
                }).then(() => {
                    this.getList();
                    this.msgSuccess("删除成功");
                }).catch(function() {});
            }
        }

    }
</script>