<template>
  <header class="userHeader">
        <div class="leftLogo">
            <img src="@/assets/logo.png" >
        </div>
        <div class="right-menu">
            <div class="right-menu-item">
              <el-menu 
                :default-active="$route.path" 
                class="el-menu-demo" 
                mode="horizontal" 
                @select="handleSelect"
                router
                >
                  <el-menu-item index="/ordinaryUser/index">首页</el-menu-item>
                  <!-- <el-menu-item index="/ordinaryUser/exams">考试</el-menu-item> -->
                  <el-menu-item index="/ordinaryUser/exams">考试
                    <el-badge :value="total" v-show="total>0" id="elBadge"></el-badge>
                  </el-menu-item>
                  <el-submenu index="3" class="el-submenu-dropdown">
                    <template slot="title">记录</template>
                    <el-menu-item index="/ordinaryUser/examRecord">考试记录</el-menu-item>
                    <el-menu-item index="3-2">错题本</el-menu-item>
                  </el-submenu>
                  
                  <el-submenu index="4" class="el-submenu-dropdown">
                    <template slot="title">帮助</template>
                    <el-menu-item index="4-1">关于我们</el-menu-item>
                    <el-menu-item index="4-2">规划总览</el-menu-item>
                  </el-submenu>
              </el-menu>
            </div>
            <template v-if="device!=='mobile'">

              <search id="header-search" class="right-menu-item" />

              <screenfull id="screenfull" class="right-menu-item hover-effect" />

              <el-tooltip content="布局大小" effect="dark" placement="bottom">
                <size-select id="size-select" class="right-menu-item hover-effect" />
              </el-tooltip>

            </template>

            <el-dropdown class="avatar-container right-menu-item hover-effect" trigger="click">
              <div class="avatar-wrapper">
                <img :src="avatar" class="user-avatar">
                <i class="el-icon-caret-bottom" />
              </div>
              <el-dropdown-menu slot="dropdown">
                <router-link to="/user/profile">
                  <el-dropdown-item>个人中心</el-dropdown-item>
                </router-link>
                <el-dropdown-item>
                  <span @click="setting = true">布局设置</span>
                </el-dropdown-item>
                <el-dropdown-item divided>
                  <span @click="logout">退出登录</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
        </div>
    </header>
</template> 

<script>
    import axios from 'axios'
    import homeCarousel from '@/components/bootstrapVue/Carousel/index.vue'
    import { mapGetters } from 'vuex'
    import Screenfull from '@/components/Screenfull'
    import SizeSelect from '@/components/SizeSelect'
    import Search from '@/components/HeaderSearch'
    import { getUserProfile } from "@/api/system/user";
    import { getExamByUserPhonenumber } from '@/api/assess/testTask';

    export default {
        name: 'entry',
        components: {
            homeCarousel,
            Screenfull,
            SizeSelect,
            Search,
        },
        computed: {
            ...mapGetters([
              'sidebar',
              'avatar',
              'device'
            ]),
            setting: {
              get() {
                return this.$store.state.settings.showSettings
              },
              set(val) {
                this.$store.dispatch('settings/changeSetting', {
                  key: 'showSettings',
                  value: val
                })
              }
            }
          },
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1',
                isActive: false,
                showFacts: false,
                showCourses: false,
                showBlog: false,
                // 查询参数
                examqueryParams: {
                  pageNum: 1,
                  pageSize: 10,
                  userPhonenumber: undefined,
                },
                // 考试任务总条数
                total: 0,
                //定时器
                timer:null,
                //使用messageId作为弹窗的key，用来获取弹窗的实例，以对对应弹窗进行操作
                notifications: {},
            };
        },
        created() {
            let vm = this
            window.onscroll = function () {
              vm.isActive = document.documentElement.scrollTop > 60
              if (document.documentElement.scrollTop > 300) {
                setTimeout(() => {
                  vm.showCourses = true
                }, 350)
              }

              if (document.documentElement.scrollTop > 800) {
                setTimeout(() => {
                  vm.showBlog = true
                }, 350)
              }
            }
            setTimeout(() => {
              vm.showFacts = true
            }, 350)
        },
        methods: {
            async logout() {
              this.$confirm('确定注销并退出系统吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
              }).then(() => {
                this.$store.dispatch('LogOut').then(() => {
                  location.reload()
                })
              })
            },
            // 选择事件
            handleSelect (item) {
            },
            open (path) {
              if (path.startsWith('http')) {
                window.open(path)
                return
              }
              if (path !== this.$route.fullPath) {
                if (this.$route.fullPath === '/start') {
                  this.$confirm('是否要结束当前考试?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                  }).then(() => {
                    // TODO 提交当前考试
                    this.$emit('handleSubmitExam')
                    this.$router.push({
                      path: path,
                      query: {}
                    })
                  }).catch(() => {})
                } else {
                  this.$router.push({
                    path: path,
                    query: {}
                  })
                }
              }
            },
            //要定时执行的函数
            setTimer: function() {
              if(this.timer == null) {
                this.timer = setInterval(()=>{
                  getUserProfile().then(response => {
                    //获取当前用户手机号
                    this.examqueryParams.userPhonenumber = response.data.phonenumber;
                    //获取当前用户的未答卷的考试任务
                    getExamByUserPhonenumber(this.examqueryParams).then(response => {
                      // console.log(response);
                      this.total = response.total;
                      // if(this.total>0){
                      //   // console.log(this.total);
                      //   var message = '你有考试任务待完成，点此跳转';
                      //   // this.openNotification();
                      //   this.openMessageTips('你有考试任务待完成，点此跳转');
                      // }
                    });
                  });
                },20000)
              }
            },
            //关闭单个通知
            closeNotification(id, operateCode, message){
              this.notifications[message.messageId].close();
              delete this.notifications[message.messageId];
            },
            //关闭所有通知
            closeAllNotification(){
              let _this = this;
              for (let key in _this.notifications) {
                _this.notifications[key].close();
                delete _this.notifications[key];
              }
            },
            //打开一个新的通知
            openMessageTips(message){
              let _this = this;
              this.closeAllNotification();
              let notify = this.$notify({
                title: '尊敬的用户',
                type: 'warning',
                offset: 100,
                dangerouslyUseHTMLString: true,
                message: this.$createElement('div',
                          {
                            style:{
                                  cursor:'pointer'
                            },
                            on: {
                              click: ()=>{
                                // console.log(1111100);
                                this.closeAllNotification();
                                this.$router.push({
                                  //row.id为考试id
                                  path: `/ordinaryUser/exams`
                                })
                              }
                            }
                          },
                          "你有考试任务待完成，点此跳转"
                ),
                duration: 0,
              });
              //将messageId和通知实例放入字典中
              this.notifications[message.messageId] = notify;
            }
        },
        mounted() {
          clearInterval(this.timer)
            this.timer = null
            this.setTimer()
        },
        distroyed: function () {
        　　// 每次离开当前界面时，清除定时器
            clearInterval(this.timer)
            this.timer = null
        },
    }
</script> 

<style rel="stylesheet/scss" lang="scss">
    .userHeader {
        z-index: 1000;
        height: 78px;
        overflow: hidden;
        position: fixed;
        top: 0;
        left: 0;
        width:100%;
        background: #fff;
        // box-shadow: 0 1px 4px rgba(0,21,41,.08);
        box-shadow: 0 5px 15px rgba(0,0,0,.15);
        padding:0 !important;
        .right-menu {
            // float: right;
            // height: 100%;
            line-height: 73px;
            position: fixed;
            right: 10%;
            &:focus {
              outline: none;
            }

            .right-menu-item {
              display: inline-block;
              padding: 0 8px;
              height: 100%;
              font-size: 18px;
              color: #5a5e66;
              vertical-align: text-bottom;

              &.hover-effect {
                cursor: pointer;
                transition: background .3s;

                &:hover {
                  background: rgba(0, 0, 0, .025)
                }
              }
              .el-menu-demo{
                height: 78px;
                .el-menu-item{
                  height:78px;
                  line-height:86px;
                  #elBadge{
                      height: 78px;
                      margin-top: -10px;
                      sup{
                        margin-top: -26px;
                      }
                  }
                }
                .el-submenu-dropdown{
                  height:78px;
                  padding-top:12px;
                }
              }

            }

        .avatar-container {
          margin-right: 30px;

          .avatar-wrapper {
            // margin-top: 5px;
            position: relative;

            .user-avatar {
              cursor: pointer;
              width: 40px;
              height: 40px;
              border-radius: 10px;
            }

            .el-icon-caret-bottom {
              cursor: pointer;
              position: absolute;
              right: -20px;
              top: 25px;
              font-size: 12px;
            }
          }
        }
      }
      .leftLogo{
        line-height: 46px;
        height: 100%;
        float: left;
        cursor: pointer;
        transition: background .3s;
        -webkit-tap-highlight-color:transparent;
        margin-left: 10%;
      }
    }
</style> 



