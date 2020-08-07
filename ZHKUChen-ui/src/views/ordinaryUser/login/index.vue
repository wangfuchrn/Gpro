<template>
  <div id="entry" class="main">
    <header class="header clearfix">
        <!-- nav-bar -->
        <div class="header-nav clearfix">
          <div class="main-menu">
            <ul class="clearfix">
              <li class="menu-item">
                <img id="logo" src="@/assets/logo.png">
              </li>
              <li class="skip menu-item">
                <p style="font-size:16px;">我是企业用户，请<router-link class="skiplink" index="1" tag="li" to="/company/login">点击这里</router-link></p>
              </li>      
            </ul>
          </div>
        </div>
      </header>
    <div class="content">
      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form">
        <h3 class="title">个人用户</h3>
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号"  class="elinputBackground">
            <svg-icon slot="prefix" icon-class="user" class="el-input__icon input-icon" />
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            auto-complete="off"
            placeholder="密码"
            @keyup.enter.native="handleLogin"
            class="elinputBackground"
          >
            <svg-icon slot="prefix" icon-class="password" class="el-input__icon input-icon" />
          </el-input>
        </el-form-item>
        <el-form-item prop="code">
          <el-input
            v-model="loginForm.code"
            auto-complete="off"
            placeholder="验证码"
            style="width: 63%;color:#fff;"
            @keyup.enter.native="handleLogin"
            class="elinputBackground"
          >
            <svg-icon slot="prefix" icon-class="validCode" class="el-input__icon input-icon" />
          </el-input>
          <div class="login-code">
            <img :src="codeUrl" @click="getCode" />
          </div>
        </el-form-item>
        <el-checkbox v-model="loginForm.rememberMe" style="margin:0px 0px 25px 0px;color: rgb(192, 196, 204);">记住密码</el-checkbox>
        <router-link class="forget" index="1" tag="li" to="/ordinaryUser/login" >忘记密码?</router-link>
        <el-form-item style="width:100%;">
            <el-button
              type="warning"
              size="medium"
              style="width:45%;"
            ><router-link index="1" tag="li" to="/ordinaryUser/register" style="list-style:none;">注 册</router-link></el-button>
          <el-button
            :loading="loading"
            size="medium"
            type="primary"
            style="width:49%;"
            @click.native.prevent="handleLogin"
          >
            <span v-if="!loading">登 录</span>
            <span v-else>登 录 中...</span>
          </el-button>
         <!--  <el-dialog :title="title" :visible.sync="open" width="600px">
                <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                    <el-row>
                      <el-col :span="12">
                        <el-form-item label="用户昵称" prop="nickName">
                          <el-input v-model="form.nickName" placeholder="请输入用户昵称" />
                        </el-form-item>
                      </el-col>
                      <el-col :span="12">
                        <el-form-item label="归属部门" prop="deptId">
                          <treeselect v-model="form.deptId" :options="deptOptions" placeholder="请选择归属部门" />
                        </el-form-item>
                      </el-col>
                      <el-col :span="12">
                        <el-form-item label="手机号码" prop="phonenumber">
                          <el-input v-model="form.phonenumber" placeholder="请输入手机号码" maxlength="11" />
                        </el-form-item>
                      </el-col>
                      <el-col :span="12">
                        <el-form-item label="邮箱" prop="email">
                          <el-input v-model="form.email" placeholder="请输入邮箱" maxlength="50" />
                        </el-form-item>
                      </el-col>
                      <el-col :span="12">
                        <el-form-item label="用户名称" prop="userName">
                          <el-input v-model="form.userName" placeholder="请输入用户名称" />
                        </el-form-item>
                      </el-col>
                      <el-col :span="12">
                        <el-form-item v-if="form.userId == undefined" label="用户密码" prop="password">
                          <el-input v-model="form.password" placeholder="请输入用户密码" type="password" />
                        </el-form-item>
                      </el-col>
                      <el-col :span="12">
                        <el-form-item label="用户性别">
                          <el-select v-model="form.sex" placeholder="请选择">
                            <el-option
                              v-for="dict in sexOptions"
                              :key="dict.dictValue"
                              :label="dict.dictLabel"
                              :value="dict.dictValue"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                      </el-col>
                      <el-col :span="12">
                        <el-form-item label="状态">
                          <el-radio-group v-model="form.status">
                            <el-radio
                              v-for="dict in statusOptions"
                              :key="dict.dictValue"
                              :label="dict.dictValue"
                            >{{dict.dictLabel}}</el-radio>
                          </el-radio-group>
                        </el-form-item>
                      </el-col>

                      <el-col :span="12">
                        <el-form-item label="岗位">
                          <el-select v-model="form.postIds" multiple placeholder="请选择">
                            <el-option
                              v-for="item in postOptions"
                              :key="item.postId"
                              :label="item.postName"
                              :value="item.postId"
                              :disabled="item.status == 1"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                      </el-col>
                      <el-col :span="12">
                        <el-form-item label="角色">
                          <el-select v-model="form.roleIds" multiple placeholder="请选择">
                            <el-option
                              v-for="item in roleOptions"
                              :key="item.roleId"
                              :label="item.roleName"
                              :value="item.roleId"
                              :disabled="item.status == 1"
                            ></el-option>
                          </el-select>
                        </el-form-item>
                      </el-col>
                      <el-col :span="24">
                        <el-form-item label="备注">
                          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"></el-input>
                        </el-form-item>
                      </el-col>
                    </el-row>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submitForm">确 定</el-button>
                    <el-button @click="cancel">取 消</el-button>
                  </div>
                </el-dialog> -->
        </el-form-item>
      </el-form>
    </div>
    <div class="el-login-footer">
      <span>Copyright © 2019-2020 ZHKU-Chen All Rights Reserved.</span>
    </div>
  </div>
</template>
<script>
    import axios from 'axios'
    import { getCodeImg, addUser } from "@/api/company/login.js";
    import Cookies from "js-cookie";
    import { encrypt, decrypt } from '@/utils/jsencrypt';
    export default {
        name: "entry",
        data() {
            return {
                codeUrl: "",
                cookiePassword: "",
                //backgroundImg: require('@/assets/imgs/bg0' + new Date().getDay() + '.jpg'),
                loginForm: {
                    username: "王小明",
                    password: "admin123",
                    rememberMe: false,
                    code: "",
                    uuid: ""
                },
                loginRules: {
                    username: [{
                        required: true,
                        trigger: "blur",
                        message: "用户名不能为空"
                    }],
                    password: [{
                        required: true,
                        trigger: "blur",
                        message: "密码不能为空"
                    }],
                    code: [{
                        required: true,
                        trigger: "change",
                        message: "验证码不能为空"
                    }]
                },
                loading: false,
                redirect: undefined,
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 默认密码
                //initPassword: undefined,
                 // 部门树选项
                deptOptions: undefined,
                // 状态数据字典
                statusOptions: [],
                // 性别状态字典
                sexOptions: [],
                // 岗位选项
                postOptions: [],
                // 角色选项
                roleOptions: [],
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                    userName: [
                      { required: true, message: "用户名称不能为空", trigger: "blur" }
                    ],
                    nickName: [
                      { required: true, message: "用户昵称不能为空", trigger: "blur" }
                    ],
                    deptId: [
                      { required: true, message: "归属部门不能为空", trigger: "blur" }
                    ],
                    password: [
                      { required: true, message: "用户密码不能为空", trigger: "blur" }
                    ],
                    email: [
                      { required: true, message: "邮箱地址不能为空", trigger: "blur" },
                      {
                        type: "email",
                        message: "'请输入正确的邮箱地址",
                        trigger: ["blur", "change"]
                      }
                    ],
                    phonenumber: [
                      { required: true, message: "手机号码不能为空", trigger: "blur" },
                      {
                        pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
                        message: "请输入正确的手机号码",
                        trigger: "blur"
                      }
                    ]
                  }
            };
        },
        watch: {
            $route: {
                handler: function(route) {
                    this.redirect = route.query && route.query.redirect;
                },
                immediate: true
            }
        },
        created() {
            this.getCode();
            this.getCookie();
            // this.getDicts("sys_normal_disable").then(response => {
            //   this.statusOptions = response.data;
            // });
            // this.getDicts("sys_user_sex").then(response => {
            //   this.sexOptions = response.data;
            // });
            // this.getConfigKey("sys.user.initPassword").then(response => {
            //   this.initPassword = response.msg;
            // });
        },
        methods: {
            // cancel() {
            //   this.open = false;
            //   this.reset();
            // },
            // // 表单重置
            // reset() {
            //   this.form = {
            //     userId: undefined,
            //     deptId: undefined,
            //     userName: undefined,
            //     nickName: undefined,
            //     password: undefined,
            //     phonenumber: undefined,
            //     email: undefined,
            //     sex: undefined,
            //     status: "0",
            //     remark: undefined,
            //     postIds: [],
            //     roleIds: []
            //   };
            //   this.resetForm("form");
            // },
            // /** 新增按钮操作 */
            // handleAdd() {
            //   this.reset();
            //   //this.getTreeselect();
            //   // getUser().then(response => {
            //   //   this.postOptions = response.posts;
            //   //   this.roleOptions = response.roles;
            //   //   this.open = true;
            //   //   this.title = "添加用户";
            //   //   this.form.password = this.initPassword;
            //   // });
            //   this.open = true;
            //   this.title = "添加用户";
            // },
            // submitForm: function() {
            //   this.$refs["form"].validate(valid => {
            //     if (valid) {
            //         addUser(this.form).then(response => {
            //           if (response.code === 200) {
            //             this.msgSuccess("新增成功");
            //             this.open = false;
            //             //this.getList();
            //           } else {
            //             this.msgError(response.msg);
            //           }
            //         });
            //     }
            //   });
            // },
            getCode() {
                getCodeImg().then(res => {
                    this.codeUrl = "data:image/gif;base64," + res.img;
                    this.loginForm.uuid = res.uuid;
                });
            },
            getCookie() {
                const username = Cookies.get("username");
                const password = Cookies.get("password");
                const rememberMe = Cookies.get('rememberMe')
                this.loginForm = {
                    username: username === undefined ? this.loginForm.username : username,
                    password: password === undefined ? this.loginForm.password : decrypt(password),
                    rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
                };
            },
            handleLogin() {
                this.$refs.loginForm.validate(valid => {
                    if (valid) {
                        this.loading = true;
                        if (this.loginForm.rememberMe) {
                            Cookies.set("username", this.loginForm.username, {
                                expires: 30
                            });
                            Cookies.set("password", encrypt(this.loginForm.password), {
                                expires: 30
                            });
                            Cookies.set('rememberMe', this.loginForm.rememberMe, {
                                expires: 30
                            });
                        } else {
                            Cookies.remove("username");
                            Cookies.remove("password");
                            Cookies.remove('rememberMe');
                        }
                        this.$store
                            .dispatch("Login", this.loginForm)
                            .then(() => {
                                this.$router.push({
                                    path: "/ordinaryUser/index"
                                    // path: this.redirect || "/"
                                });
                            })
                            .catch(() => {
                                this.loading = false;
                                this.getCode();
                            });
                    }
                });
            }
        }
    };
</script>
<style rel="stylesheet/scss" lang="scss" scoped>
    .main {
        // background-color: #abc9e4;
        background: url('../../../assets/background/seaBackground.jpg') no-repeat;
        background-size: 100%;
        height: 100%;
    }

    /* 头部 begin */
    
    .header {
        width: 100%;
        height: 78px;
        background-color: transparent;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
        .header-nav {
            width: 100%;
            height: 100%;
            background: #f7f8f861;
            .main-menu {
                margin: auto;
                width: 80%;
                text-align: center;
                .menu-item {
                    height: 70px;
                    line-height: 70px;
                    float: left;
                    margin-right: 50px;
                    list-style: none;
                    // margin-top: -18px;
                    li {
                        font-size: 16px;
                        list-style: none;
                    }
                }
                .skip {
                    float: right;
                    height: 70px;
                    line-height: 70px;
                    margin-right: 10%;
                    font-size: bold;
                    p {
                        color: #606266;
                    }
                }
                .skiplink {
                    list-style: none;
                    // color: #34afc6;
                    color: #f1dc71;
                    float: right;
                    height: 70px;
                    line-height: 70px;
                    margin-left: 10px;
                    &:hover {
                        cursor: pointer;
                    }
                }
            }
        }
         #logo {
            height: 78px;
            width: 90px;
            margin-top:0 !important;
        }
    }
    /* 头部 end */
    
    .el-menu.el-menu--horizontal {
        border-bottom: 0;
    }
    
    .el-menu--horizontal>.el-submenu.is-active .el-submenu__title {
        border-bottom: 0;
    }
    
    .content {
        padding-top: 5%;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
        // background: url('../../../assets/background/bg1.jpg');
    }
    
    .title {
        margin: 0px auto 30px auto;
        text-align: center;
        color: #fff;
    }
    
    .login-form {
        margin-bottom: 100px;
        border-radius: 6px;
        background: #3031337a;
        width: 430px;
        padding: 25px 25px 5px 25px;
        .el-input {
            height: 38px;
            input {
                height: 38px;
                background-color:transparent;
                color:#fff;
            }
        }
        .input-icon {
            height: 39px;
            width: 14px;
            margin-left: 2px;
        }
        #usernameInput{
          background-color:transparent !important;
        }
    }
    
    .login-tip {
        font-size: 13px;
        text-align: center;
        color: #bfbfbf;
    }
    
    .login-code {
        width: 33%;
        height: 38px;
        float: right;
        img {
            cursor: pointer;
            vertical-align: middle;
        }
    }
    
    .forget {
        list-style: none;
        color: rgb(192, 196, 204);
        float: right;
        font-size: 13px;
        margin-right: 5px;
        &:hover {
            cursor: pointer;
        }
    }
    .el-checkbox .el-checkbox__label{
      font-size:13px;
    }
    
    .el-login-footer {
        height: 40px;
        line-height: 40px;
        position: fixed;
        bottom: 0;
        width: 100%;
        text-align: center;
        color: #fff;
        font-family: Arial;
        font-size: 12px;
        letter-spacing: 1px;
    }
</style>