<template>
  <div id="entry" class="main">
    <u-header></u-header>
    <section class="container-fluid sectionFirst">
        <div class="sectionFirstContain">
            <div class="index_top_t1 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;"> <span style="color:#fff;">企业人才招聘系统</span></div>
            <div class="index_top_t2 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s;"> <span style="color:#fff;">用技术发现天赋 用科学规划未来</span> </div>
            <div class="index_top_t3 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.3s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.3s;"> <span>由ZHKUChen发布</span> </div>
            <div class="index_top_link wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.4s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.4s;"> <a href="/ordinaryUser/exams">开始测评</a> </div>
        </div>
    </section>

    <section class="container-fluid sectionSecond">
           <div class="cool-facts-area padding-100-0">
              <el-row type="flex" justify="center" :gutter="50">
                <el-col :span="4">
                  <transition name="fade-transform" mode="out-in">
                    <div class="single-cool-facts-area mb-100" v-show="showFacts">
                      <div class="icon">
                        <img src="../../assets/image/star.png" alt="">
                      </div>
                      <h2>
                        <count-to :start-val="0" :end-val="200" :duration="2600" class="counter"/>
                      </h2>
                      <h5>用户数</h5>
                    </div>
                  </transition>
                </el-col>
                <el-col :span="4">
                  <transition name="fade-transform" mode="out-in">
                    <div class="single-cool-facts-area mb-100" v-show="showFacts">
                      <div class="icon">
                        <img src="../../assets/image/star.png" alt="">
                      </div>
                      <h2>
                        <count-to :start-val="0" :end-val="500" :duration="2600" class="counter"/>
                      </h2>
                      <h5>考试数</h5>
                    </div>
                  </transition>
                </el-col>
                <el-col :span="4">
                  <transition name="fade-transform" mode="out-in">
                    <div class="single-cool-facts-area mb-100" v-show="showFacts">
                      <div class="icon">
                        <img src="../../assets/image/star.png" alt="">
                      </div>
                      <h2>
                        <count-to :start-val="0" :end-val="233" :duration="2600" class="counter"/>
                      </h2>
                      <h5>题目数</h5>
                    </div>
                  </transition>
                </el-col>
                <el-col :span="4">
                  <transition name="fade-transform" mode="out-in">
                    <div class="single-cool-facts-area mb-100" v-show="showFacts">
                      <div class="icon">
                        <img src="../../assets/image/star.png" alt="">
                      </div>
                      <h2>
                        <count-to :start-val="0" :end-val="579" :duration="2600" class="counter"/>
                      </h2>
                      <h5>错题数</h5>
                    </div>
                  </transition>
                </el-col>
              </el-row>
            </div> 
    </section>

    <o-footer></o-footer>
    <!-- <el-button
      plain
      @click="open1">
      可自动关闭
    </el-button> -->
  </div>
</template>

<script>
    import 'bootstrap'
    import $ from 'jquery'
    import 'animate.css'
    import axios from 'axios'
    import homeCarousel from '@/components/bootstrapVue/Carousel/index.vue'
    import 'bootstrap/dist/css/bootstrap.min.css'
    import 'bootstrap/dist/js/bootstrap.min.js'
    import '../../utils/homejs/docs.js'
    import '../../utils/homejs/Effect.js'
    import '../../utils/homejs/jquery.running.min.js'
    import '../../utils/homejs/jquery.SuperSlide.js'
    import '../../assets/styles/style.css'
    import '../../assets/styles/adaptive.css'
    import '../../assets/styles/swiper.min.css'
    import '../../assets/styles/JS5.css'
    import { mapGetters } from 'vuex'
    import CountTo from 'vue-count-to'
    import OFooter from '../../components/Footer/footer.vue'
    import UHeader from '../../components/UserHeader/index.vue'
    import { getUserProfile } from "@/api/system/user";
    import { getExamByUserPhonenumber } from '@/api/assess/testTask';

    export default {
        name: 'entry',
        components: {
            UHeader,
            OFooter,
            CountTo,
            homeCarousel,
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
                // examqueryParams: {
                //   pageNum: 1,
                //   pageSize: 10,
                //   userPhonenumber: undefined,
                // },
                // // 考试任务总条数
                // total: 0,
                // //定时器
                // timer:null,
                // //使用messageId作为弹窗的key，用来获取弹窗的实例，以对对应弹窗进行操作
                // notifications: {},
            };
        },
        created() {
            let vm = this;
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
            //要定时执行的函数
            // setTimer: function() {
            //   if(this.timer == null) {
            //     this.timer = setInterval(()=>{
            //       getUserProfile().then(response => {
            //         //获取当前用户手机号
            //         this.examqueryParams.userPhonenumber = response.data.phonenumber;
            //         //获取当前用户的未答卷的考试任务
            //         getExamByUserPhonenumber(this.examqueryParams).then(response => {
            //           // console.log(response);
            //           this.total = response.total;
            //           if(this.total>0){
            //             // console.log(this.total);
            //             var message = '你有考试任务待完成，点此跳转';
            //             // this.openNotification();
            //             this.openMessageTips('你有考试任务待完成，点此跳转');
            //           }
            //         });
            //       });
            //     },10000)
            //   }
            // },
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
            //关闭单个通知
            // closeNotification(id, operateCode, message){
            //   this.notifications[message.messageId].close();
            //   delete this.notifications[message.messageId];
            // },
            // //关闭所有通知
            // closeAllNotification(){
            //   let _this = this;
            //   for (let key in _this.notifications) {
            //     _this.notifications[key].close();
            //     delete _this.notifications[key];
            //   }
            // },
            // //打开一个新的通知
            // openMessageTips(message){
            //   let _this = this;
            //   this.closeAllNotification();
            //   let notify = this.$notify({
            //     title: '尊敬的用户',
            //     type: 'warning',
            //     offset: 100,
            //     dangerouslyUseHTMLString: true,
            //     message: this.$createElement('div',
            //               {
            //                 style:{
            //                       cursor:'pointer'
            //                 },
            //                 on: {
            //                   click: ()=>{
            //                     // console.log(1111100);
            //                     this.closeAllNotification();
            //                     this.$router.push({
            //                       //row.id为考试id
            //                       path: `/ordinaryUser/exams`
            //                     })
            //                   }
            //                 }
            //               },
            //               "你有考试任务待完成，点此跳转"
            //     ),
            //     duration: 0,
            //   });
            //   //将messageId和通知实例放入字典中
            //   this.notifications[message.messageId] = notify;
            // }
        },
        // mounted() {
        //   clearInterval(this.timer)
        //     this.timer = null
        //     this.setTimer()
        // },
        // distroyed: function () {
        // 　　// 每次离开当前界面时，清除定时器
        //     clearInterval(this.timer)
        //     this.timer = null
        // },
    }
</script>

<style rel="stylesheet/scss" lang="scss">

    .sidebar-container{
        display: none;
    }
    .main-container{
        margin-left:0 !important;
    }
    .tags-view-container{
        display: none;
    }
    .navbar{
        display: none;
    } 
    #shadow {
        margin-top: 68px;
        height: 700px;
        position: relative;
        .index_top_t2 {
          width:70%;
          span {
              font-size: 27px;
          }
        }
        .container{
          margin-top:137px;
        }
    }
    
    #shadow:before {
        content: '';
        position: absolute;
        left: 50%;
        top: -24px;
        width: 130%;
        height: 100%;
        background: #212529;
        opacity: 0.3;
        z-index: -1;
        border-radius: 0 0 50% 50%;
        transform: translateX(-50%) translateY(18px) rotate(2deg);
    }
    
    ;
    #shadow:after {
        content: '';
        position: absolute;
        left: 50%;
        top: 0;
        width: 130%;
        height: 95%;
        background: linear-gradient(to right, rgba(30, 67, 86, 0.8), rgba(30, 67, 86, 0.6)), url(../../assets/background/hero-bg.jpg) center top no-repeat fixed;
        z-index: -1;
        border-radius: 0 0 50% 50%;
        transform: translateX(-50%) rotate(0deg);
    }
    
    ;
    .carousel-indicators {
        display: none;
    }
    .index_E_bott {
        height: 354px;
    }
    .sectionFirst{
        width: 100%;
        height: 600px;
        background: url('../../assets/exams/12.jpeg') no-repeat;
        background-size: 100% 100%; 
        .sectionFirstContain{
           padding-top:11%;
        }
    }
    .sectionSecond{
        width: 100%;
        height: 100%;
        margin-top: 57px;
        // margin-left: 4%;
        .single-cool-facts-area {
            text-align: center;
            position: relative;
            z-index: 1;
            padding: 30px;
            border: 1px solid #ebebeb;
            border-radius: 6px;
            margin-bottom: 80px!important;
            cursor: pointer;
            h2,h5{
                color: rgba(0,0,0,.8);
                line-height: 1.3;
                font-weight: 700;
            }
            img{
                height: auto;
                max-width: 100%;
            }
        }
    }
</style>