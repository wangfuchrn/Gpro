<template>
  <div id="entry" class="main">
    <header class="homeHeader">  
      <b-navbar toggleable="lg" type="dark" variant="info" class="b-navtoggle">
        <b-navbar-brand>
            <img src="@/assets/logo.png" >
        </b-navbar-brand>
        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
        <div class="btngroup">
            <el-button type="warning" icon="el-icon-coordinate"><router-link class="navBtn" to="/company/login">注册</router-link></el-button>
            <el-button type="primary" icon="el-icon-s-custom"><router-link class="navBtn" to="/company/login">登录</router-link></el-button>
        </div> 
        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="b-nav">
            <b-nav-item href="/" class="headnav-item">首页</b-nav-item>
            <b-nav-item href="/company/login" class="headnav-item">产品服务</b-nav-item>
            <b-nav-item href="/company/login" class="headnav-item">测评体验</b-nav-item>
            <b-nav-item href="/company/login" class="headnav-item">新闻资讯</b-nav-item>
            <b-nav-item href="/company/login" class="headnav-item">关于我们</b-nav-item>
          </b-navbar-nav>
          <b-navbar-nav >
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </header>
    <section class="container-fluid index_top" id="shadow">
      <div class="container">
        <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
            <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
          </ol>
          <div class="carousel-inner">
            <div class="carousel-item active">
              <div class="index_top_t1 wow fadeInUp d-none d-md-block" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;"> <span>企业人才招聘系统</span> </div>
              <div class="index_top_t2 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s;"> <span>根据您的真实需求，提供测评系统全方位的定制服务方案，让您轻松拥有专属的企业人才招聘系统</span> </div>
              <div class="index_top_t3 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.3s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.3s;"> <span>由ZHKUChen发布</span> </div>
              <div class="index_top_link wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.4s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.4s;"> <a href="/company/login">立即使用</a> </div>
            </div>
            <div class="carousel-item">
                <div class="index_top_t1 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;"> <span>企业人才招聘系统</span></div>
                <div class="index_top_t2 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s;"> <span>用技术发现天赋 用科学规划未来</span> </div>
                <div class="index_top_t3 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.3s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.3s;"> <span>由ZHKUChen发布</span> </div>
                <div class="index_top_link wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.4s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.4s;"> <a href="/ordinaryUser/login">开始测评</a> </div>
            </div>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
      </div>
    </section>
    <section class="container-fluid index_aDiv">
      <div class="container">
        <div class="index_aDiv_t1 wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;"><p>未来趋势已变<br><em>“变化与重塑”</em>成为企业发展关键词</p></div>
        <div class="index_aDiv_list">
          <ul class="row">
            <li class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;">
              <div class="index_div">
                <div class="index_a_pic"> <img src="../assets/background/ico_1.png"> </div>
                <div class="index_a_title">
                  <h1>工作任务全球化</h1>
                  <p>TASK GLOBALIZATION</p>
                </div>
              </div>
            </li>
            <li class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s;">
              <div class="index_div">
                <div class="index_a_pic"> <img src="../assets/background/ico_2.png"> </div>
                <div class="index_a_title">
                  <h1>人工智能大量运用</h1>
                  <p>ARTIFICIAL INTELLIGENCE IS WIDELY USED</p>
                </div>
              </div>
            </li>
            <li class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.3s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.3s;">
              <div class="index_div">
                <div class="index_a_pic"> <img src="../assets/background/ico_3.png"> </div>
                <div class="index_a_title">
                  <h1>模糊不确定的环境</h1>
                  <p>A VAGUE AND UNCERTAIN ENVIRONMENT</p>
                </div>
              </div>
            </li>
                  </ul>
        </div>
      </div>
    </section>
    <section class="container-fluid index_bDiv">
      <div class="container">
        <div class="index_b_title wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;"><p>近两年<br>HR在人力资源管理上遇到多重挑战</p> </div>
        <div class="index_b_ms wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s;">
          <p>数据来源于《2019中小企业人力资源数字化工具运用现状及挑战》调研</p>
        </div>
        <div class="index_b_butt wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.3s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.3s;"> <a href="/company/login" target="_blank">立即获取调研报告</a> </div>
        <div class="index_bb_list">
          <ul class="row">
                    <li class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;">
              <div class="index_b_div">
                <div class="index_b_sj"> <span><i class="animateNum" data-animatetarget="67.6">67.6</i>%</span> </div>
                <div class="index_b_text">
                  <p>人才保留难</p>
                </div>
              </div>
            </li>
                    <li class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s;">
              <div class="index_b_div">
                <div class="index_b_sj"> <span><i class="animateNum" data-animatetarget="62.9">62.9</i>%</span> </div>
                <div class="index_b_text">
                  <p>人力资源管理 效用提升慢</p>
                </div>
              </div>
            </li>
                    <li class="col-xl-4 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.3s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.3s;">
              <div class="index_b_div">
                <div class="index_b_sj"> <span><i class="animateNum" data-animatetarget="57.1">57.1</i>%</span> </div>
                <div class="index_b_text">
                  <p>无法形成体 系化建设</p>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </section>
    <section class="container-fluid index_E">
      <div class="container">
        <div class="index_tit wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;"><p>我们帮你用数据打造选用育留人才闭环<br>合理分配资源与精力</p></div>
        <div class="index_ms wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s;"><span>提升工作效率 实现管理价值</span></div>
        <div class="index_E_list">
          <ul class="row">
                    <li class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-6 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;">
              <div class="index_E_div">
                <div class="index_E_pic"><img src="../assets/background/ico_11.png"></div>
                <div class="index_E_title"><span>提升沟通效率</span></div>
                <div class="index_E_entit">
                  <p>IMPROVE COMMUNICATION EFFICIENCY</p>
                </div>
              </div>
            </li>
                    <li class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-6 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s;">
              <div class="index_E_div">
                <div class="index_E_pic"><img src="../assets/background/ico_12.png"></div>
                <div class="index_E_title"><span>加强人文关怀</span></div>
                <div class="index_E_entit">
                  <p>Branch report rendering</p>
                </div>
              </div>
            </li>
                    <li class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-6 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.3s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.3s;">
              <div class="index_E_div">
                <div class="index_E_pic"><img src="../assets/background/ico_13.png"></div>
                <div class="index_E_title"><span>海外员工管理</span></div>
                <div class="index_E_entit">
                  <p>Overseas staff management</p>
                </div>
              </div>
            </li>
                    <li class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-6 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.4s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.4s;">
              <div class="index_E_div">
                <div class="index_E_pic"><img src="../assets/background/ico_14.png"></div>
                <div class="index_E_title"><span>分支报表呈现</span></div>
                <div class="index_E_entit">
                  <p>Branch report rendering</p>
                </div>
              </div>
            </li>
                  </ul>
        </div>
        <div class="index_top_link wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.5s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.5s;"> <a href="/company/login">立即使用</a> </div>
        <div class="index_E_bott wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.6s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.6s;">
          <div class="index_Em"><img src="../assets/background/fd_2.png"></div>
        </div>
      </div>
    </section>
    <section class="container-fluid index_g">
      <div class="container">
        <div class="index_b_title wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;"> <p>我们汇集了各行业标杆客户<br>无论你身处任何行业</p> </div>
        <div class="D_ms wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s;">
          <p>这里都能找到适合你的解决方案</p>
        </div>
      </div>
      <div class="index_g_list">
        <ul class="row">
                <li class="col-xl-2 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;">
            <div class="index_g_div">
              <div class="index_g_pic"><img src="../assets/background/ico_19.png"></div>
              <div class="index_g_hx"><i></i></div>
              <div class="index_g_title"> <span>连锁/零售</span> </div>
              <div class="index_g_p">
                <p>结合“时间效率管理”、“人力资本管理”、“劳动合规管理” 解决“集权”与“放权”难题，统一管控又灵活；</p>
              </div>
            </div>
          </li>
                <li class="col-xl-2 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s;">
            <div class="index_g_div">
              <div class="index_g_pic"><img src="../assets/background/ico_20.png"></div>
              <div class="index_g_hx"><i></i></div>
              <div class="index_g_title"> <span>互联网/IT</span> </div>
              <div class="index_g_p">
                <p>人力资源管理系统化整合，高度契合互联网管理氛围与目标降低员工流动率；</p>
              </div>
            </div>
          </li>
                <li class="col-xl-2 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.3s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.3s;">
            <div class="index_g_div">
              <div class="index_g_pic"><img src="../assets/background/ico_21.png"></div>
              <div class="index_g_hx"><i></i></div>
              <div class="index_g_title"> <span>工业制造/化工/能源</span> </div>
              <div class="index_g_p">
                <p>解决繁琐考勤排班及特殊计薪规则，员工可通过APP自助查询增加员工归属感</p>
              </div>
            </div>
          </li>
                <li class="col-xl-2 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.4s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.4s;">
            <div class="index_g_div">
              <div class="index_g_pic"><img src="../assets/background/ico_22.png"></div>
              <div class="index_g_hx"><i></i></div>
              <div class="index_g_title"> <span>金融/地产</span> </div>
              <div class="index_g_p">
                <p>APP端移动办公，即时消息、通知、公告……更有效的沟通方式节约时间与管理成本；</p>
              </div>
            </div>
          </li>
                <li class="col-xl-2 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.5s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.5s;">
            <div class="index_g_div">
              <div class="index_g_pic"><img src="../assets/background/ico_23.png"></div>
              <div class="index_g_hx"><i></i></div>
              <div class="index_g_title"> <span>医疗/医药</span> </div>
              <div class="index_g_p">
                <p>手机端发起申请、审批、报销、客户拜访计划等，信息线上存储，保证企业运营合规化</p>
              </div>
            </div>
          </li>
                <li class="col-xl-2 col-lg-4 col-md-4 col-sm-12 col-12 wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.6s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.6s;">
            <div class="index_g_div">
              <div class="index_g_pic"><img src="../assets/background/ico_24.png"></div>
              <div class="index_g_hx"><i></i></div>
              <div class="index_g_title"> <span>教育培训</span> </div>
              <div class="index_g_p">
                <p>教师APP端上传课件，员工随时随地参与培训学习，服持续提高业务能力。</p>
              </div>
            </div>
          </li>
              </ul>
      </div>
      <div class="index_top_link wow fadeInUp" data-wow-duration="1s" data-wow-delay="0.7s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.7s;"> <a href="/company/login">立即使用</a> </div>
    </section>
    <section class="container-fluid index_h">
      <div class="container">
        <div class="index_h_left wow fadeInRight animated" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s; animation-name: fadeInRight;">
          <h1>ZHKUChen荣获</h1>
          <p>2016年度中国智能制造优秀推荐产品<br>2017优秀SaaS云人力资源管理技术服务商<br>HRTech2017人力资源科技500强</p> </div>
      </div>
    </section>
    <section class="container-fluid index_i">
      <div class="container">
        <div class="index_b_title wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: hidden; animation-duration: 1s; animation-delay: 0.1s; animation-name: inherit;"> <p>最新资讯<br>随时随地了解我们的最新资讯</p> </div>
        <div class="index_news">
          <div class="swiper_news wow fadeInDown swiper-container-horizontal" data-wow-duration="1s" data-wow-delay="0.2s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.2s; animation-name: fadeInDown;">
            <div class="swiper-wrapper" style="transform: translate3d(-5740px, 0px, 0px); transition-duration: 0ms;"><div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="17" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=119">工资管理系统的分类</a></div>
                <div class="swiper_news_time"><span>2019.11.13</span></div>
                <div class="swiper_news_p">
                  <p>&nbsp;&nbsp;工资管理系统：分为个人工资查询和工资管理&nbsp;&nbsp;一、个人工资查询的功能&nbsp;&nbsp;1、查看工资单&nbsp;&nbsp;2、打印工资单&nbsp;&nbsp;3、修改密码&nbsp;&nbsp;二、工资管理的功能&nbsp;&nbsp;1、按时间查看所工资单&nbsp;&nbsp;2、添加用户分为两块&nbsp;&nbsp;&nbsp;&nbsp;(a)单个的添加&nbsp;&nbsp;(b)用Datab</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=119"><img src="../assets/background/ico_25.png"></a></div>
              </div><div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="18" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=120">薪酬管理系统有什么功能？</a></div>
                <div class="swiper_news_time"><span>2019.11.27</span></div>
                <div class="swiper_news_p">
                  <p>&nbsp;&nbsp;&nbsp;&nbsp;对于企、事业单位的薪资发放来说，不需要太大型的数据库系统。只需要一个操作方便，功能实用，能同时满足财务部门、单位其他相关部门及代发单位三方对数据的管理及需求的系统。企业的目标就是在于利用VFP编程软件开发一个功能实用，用户操作方便，简单明了的工资管理程序。而利唐根据企、事业单位的需求定制了方便实用的工资管理系统。&nbsp;&nbsp;&nbsp;&nbsp;利唐薪酬管</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=120"><img src="../assets/background/ico_25.png"></a></div>
              </div><div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="19" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=121">好用又便宜的人事系统</a></div>
                <div class="swiper_news_time"><span>2019.11.27</span></div>
                <div class="swiper_news_p">
                  <p>通用版的一站式人力资源管理软件，入职转正，档案管理，自定义审批，一站式关怀、复杂薪资核算、员工就餐方面提供一站式服务。1、界面友好，图形界面帮您轻松上手2、完善的人事系统，方便的数据录入，档案项目设置齐全，留下了扩展空间。支持打印档案表、员工照片。人事报表支持表格列头自定义功能，您可根据您的报表要求自由设置人事报表格式，打印您需要的人事报</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=121"><img src="../assets/background/ico_25.png"></a></div>
              </div>           <div class="swiper-slide" data-swiper-slide-index="0" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=89">考勤管理系统是能够给企业带来巨大便利的</a></div>
                <div class="swiper_news_time"><span>2019.10.08</span></div>
                <div class="swiper_news_p">
                  <p>　　制造工厂人员较多，再加上无固定休息日，加班倒班较多，HR需要耗费大量的时间来管理员工的排班考勤问题，所以很需要一套适合的管理系统来提升相应的效率。　　1、制造工厂的考勤管理系统到底是什么呢?　　考勤管理系统是专门为制造业设计的，优势就是能够满足不同的排班需求，避免了企业的日常排班陷入混乱，尤其是在订单紧急的时候。　　2、智能化的考勤管</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=89"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="1" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=91">hr系统的优缺点详细解析您都清楚吗？</a></div>
                <div class="swiper_news_time"><span>2019.10.10</span></div>
                <div class="swiper_news_p">
                  <p>　　hr系统的优缺点都有什么呢?接下来为您介绍一下：　　hr系统难点在于大集团复杂的组织架构，与其他系统集成(尤其是绩效、员工门户)麻烦，从实施经验来说，系统成败不在于软件，而在于HR部门的管理能力。　　大公司建议还是本地布署，一是安全性问题，二是要与其它系统对接;　　SAAS模式的优点，上线快，员工体验好，基本功能和招聘，考勤工资请假，</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=91"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="2" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=92">人事系统的功能</a></div>
                <div class="swiper_news_time"><span>2019.10.10</span></div>
                <div class="swiper_news_p">
                  <p>数据管理功能：数据管理功能主要是指通过基础数据储存，利用系统处理大量数据统计，分类，排查等重复性工作，利用机械算数实现零失误与高效率的管理 ...</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=92"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="3" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=93">工资管理系统主要功能和特点都是什么呢？</a></div>
                <div class="swiper_news_time"><span>2019.10.16</span></div>
                <div class="swiper_news_p">
                  <p>　　工资管理系统主要功能和特点：　　1操作简单方便　　采用国际流行的下拉菜单，全WINDOWS操作形式，界面新颖，全面支持鼠标操作，功能齐全。　　2任意设定工资项目　　系统除姓名、人员编号、部门编号、部门名称、应发工资、实发工资、纳税等内定项目不能删除(长度可以改变)外，其它项目(如各种补贴及各种扣款)均可由用户自行定义(包括增加、删除、)</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=93"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="4" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=94">建立一个有效的绩效考核系统是很有必要的</a></div>
                <div class="swiper_news_time"><span>2019.10.17</span></div>
                <div class="swiper_news_p">
                  <p>　　一个优秀的绩效考核系统，既要关注结果，也要关注过程，既是人力资源管理，更是企业文化管理。　　建立一个有效的绩效考核系统是很有必要的　　中国的企业家们对于企业绩效的关注程度一直很高，而且非常好学，常常对国际流行管理方法趋之若鹜。很多人以此为豪，但这个方法是否有效，是否适用于自己?他们根本不在乎。　　一个典型的例证是对平衡记分卡的运用。平</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=94"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="5" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=95">集团型工厂如何正确选择人事管理系统呢？</a></div>
                <div class="swiper_news_time"><span>2019.10.17</span></div>
                <div class="swiper_news_p">
                  <p>　　对于集团型工厂，总部是企业集团管理决策、资源调配和控制的指挥中心。集团总部人力资源部门基于企业集团的发展战略，以制度为依托，通过为子公司提供高效的服务，创造和体现自身的价值。集团通过信息化人力资源管理，保证人力资源与日新月异的技术环境同步发展。eHR人力资源管理系统在企业的人力资源管理过程中起着重要作用。越来越多的集团企业正在进行人力</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=95"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="6" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=96">移动考勤软件的界面简单明快带来更便捷的服务</a></div>
                <div class="swiper_news_time"><span>2019.10.17</span></div>
                <div class="swiper_news_p">
                  <p>　　移动考勤，一个网络时代基于云端服务的新鲜产物，同时也是提高工作效率以及统计成本的全新工具。对于现代化快节奏的职场生活来说，移动考勤带来的不仅仅是技术统计参数方面的准确，同时也是提高员工效率、更有效利用和整合有限出勤时间的理想助手。今天笔者就以推出的免费移动考勤APP为例，来和大家谈谈移动考勤究竟在工作中能够为我们带来何种更好、更便捷的</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=96"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="7" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=97">ehr系统对于企业人员管理工作的重要意义</a></div>
                <div class="swiper_news_time"><span>2019.10.21</span></div>
                <div class="swiper_news_p">
                  <p>　　企业，特别是以工厂为代表的劳动密集型企业，拥有庞大的员工队伍和许多分支机构，遍及全国甚至全国。对于他们来说，很难进行统一的人员管理工作。这就是ehr系统。该系统应运而生，并发挥了重要作用!　　1，提高效率　　电子病历的最大作用是提高效率。各个国家/地区的各种规模的公司的每日出勤数据，计算工资，组织文件，请假，招聘面试，组织培训等，这些</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=97"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="8" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=98">考勤管理系统可以帮助企业建立有效的考勤管理机制</a></div>
                <div class="swiper_news_time"><span>2019.10.22</span></div>
                <div class="swiper_news_p">
                  <p>　　人力资源部门经常面临许多挑战，而最痛苦的事情之一就是出勤管理。随着业务的增长，此任务变得更加繁琐和复杂。一方面，企业需要雇用更多的人力资源，这增加了人工成本。另一方面，手工统计和大量数据容易出错。　　因此，有必要建立一个良好的考勤管理系统，以减少人员费用。大量员工的出勤处理效率更高，更快，这使公司能够走上正确的道路，并最终提高员工的生</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=98"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="9" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=99">工资计算系统与EXCEL有何区别?</a></div>
                <div class="swiper_news_time"><span>2019.10.22</span></div>
                <div class="swiper_news_p">
                  <p>　　一些人事专员使用传统的人事补偿管理工具EXCEL非常容易，突然改变一种方式，他们不习惯，甚至造成抵制。但是只有有了系统，才知道EXCEL和系统的功能还是有很大不同的。但是，对于拥有数万名员工的工厂或公司集团，Excel处理将大大增加工作时间成本和效率低下，甚至严重影响员工工资的正常分配，以人为本，并采用高效方法来改善工作质量。工资计算</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=99"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide swiper-slide-prev" data-swiper-slide-index="10" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=100">什么是人力资源管理系统?系统各项功能全面总结</a></div>
                <div class="swiper_news_time"><span>2019.10.23</span></div>
                <div class="swiper_news_p">
                  <p>　　无论公司的目标有多大，人力资源管理在业务管理中都起着决定性的作用?系统有多完善?产品的质量如何?营销方式有多好?如果没有才华，那就是空话!看似简单的人事部门，每天需要处理的事情非常繁琐，尤其是对于大型制造企业而言。因此，大中型企业的人事管理部门需要一套专业的人事管理系统来提高工作效率。　　让我们看一下：什么是人力资源管理系统?　　人力</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=100"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide swiper-slide-active" data-swiper-slide-index="11" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=106">hr系统的优势</a></div>
                <div class="swiper_news_time"><span>2019.10.28</span></div>
                <div class="swiper_news_p">
                  <p>&nbsp;&nbsp;hr系统的优势有以下几点：&nbsp;&nbsp;&nbsp;1、人力资源管理信息化的发展历程&nbsp;&nbsp;上世纪80年代初，随着计算机在管理领域的普遍应用，国外一些先进的应用软件企业开始将关注点聚焦于人力资源管理领域。首先利用应用软件进行的是人力资源管理中最复杂最繁重的薪资管理，这大大降低了该项工作的繁</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=106"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide swiper-slide-next" data-swiper-slide-index="12" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=107">企业中遇到的考勤问题会有哪些？</a></div>
                <div class="swiper_news_time"><span>2019.10.28</span></div>
                <div class="swiper_news_p">
                  <p>&nbsp;&nbsp;企业中遇到的考勤问题会有哪些？&nbsp;&nbsp;1、无考勤问题&nbsp;&nbsp;有些公司追求国外个别公司宽松的工作环境，基本上没有考勤，员工早退、迟到、翘班等等习以为常，企业只要求员工完成相应的工作任务即可，其它的都不在意。但是国情不同、人员素质不同，宽松管理的背后会滋养太多问题。&nbsp;&nbsp;</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=107"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="13" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=108">hr系统如何管理员工年休假？</a></div>
                <div class="swiper_news_time"><span>2019.11.07</span></div>
                <div class="swiper_news_p">
                  <p>&nbsp;&nbsp;对于企业来说，考勤管理一直都是重中之重，而年休假的管理就是其中的难点之一。那如何做到快速管理员工的年休假呢？利唐hr系统在这里做出解答！&nbsp;&nbsp;年休假，指法律规定的劳动者工作满一定的工作年限后，每年享有的保留工作带薪连续休假。年休假包括基本年休假和补加年休假。具体时间由国家根据不同的工种和劳动的繁重程度分别规定。年休假，是国家根据劳动者工</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=108"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="14" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=112">hr系统之绩效管理</a></div>
                <div class="swiper_news_time"><span>2019.11.07</span></div>
                <div class="swiper_news_p">
                  <p>&nbsp;&nbsp;绩效管理是人力资源管理的重点与难点，号称“困扰中国十大管理难题之首”，从理论界来看，世界一些管理权威人士对绩效管理的看法也不尽一致。其实，不管是哪种观点，共同的一点是对企业引入绩效管理都十分的谨慎。&nbsp;&nbsp;&nbsp;&nbsp;只有具备实施绩效管理条件的企业，实施的成功率才较高，而不是象现在一些人</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=112"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="15" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=113">工资管理系统的应用流程</a></div>
                <div class="swiper_news_time"><span>2019.11.08</span></div>
                <div class="swiper_news_p">
                  <p>&nbsp;&nbsp;工资管理系统的应用流程为：软件初始化--录入完工证--生成报表。软件初始化是指在使用正式使用工资管理系统前要先必须的数据输入到系统中，以备将来录入完工证或查询报表时调用。其中包括：新建账套、设置用户名、设置分项工程，设置工程名称、设置人员信息。&nbsp;&nbsp;1、软件登陆：选择所要登陆的账套名称、用户名，输入相应的登录密码。软件默认用户名及密码均</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=113"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="16" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=118">hr系统助力企业巩固人才</a></div>
                <div class="swiper_news_time"><span>2019.11.13</span></div>
                <div class="swiper_news_p">
                  <p>&nbsp;&nbsp;hr系统助力企业巩固人才&nbsp;&nbsp;企业发展离不开人才的帮助，特别是现有的人才资源市场特别匮乏，怎么巩固现有的人才和发展更多的人才是现有企业面临的最严峻的考验，企业近些年的方法也很多，有减少工作压力的，有优化人性化的管理环境的，有提供干部宿舍固定居住的，有提供积分资助购置商品房，有提供优化劳动报酬的，有入职三年提供红利股份等。特别是薪资奖金考</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=118"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="17" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=119">工资管理系统的分类</a></div>
                <div class="swiper_news_time"><span>2019.11.13</span></div>
                <div class="swiper_news_p">
                  <p>&nbsp;&nbsp;工资管理系统：分为个人工资查询和工资管理&nbsp;&nbsp;一、个人工资查询的功能&nbsp;&nbsp;1、查看工资单&nbsp;&nbsp;2、打印工资单&nbsp;&nbsp;3、修改密码&nbsp;&nbsp;二、工资管理的功能&nbsp;&nbsp;1、按时间查看所工资单&nbsp;&nbsp;2、添加用户分为两块&nbsp;&nbsp;&nbsp;&nbsp;(a)单个的添加&nbsp;&nbsp;(b)用Datab</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=119"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="18" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=120">薪酬管理系统有什么功能？</a></div>
                <div class="swiper_news_time"><span>2019.11.27</span></div>
                <div class="swiper_news_p">
                  <p>&nbsp;&nbsp;&nbsp;&nbsp;对于企、事业单位的薪资发放来说，不需要太大型的数据库系统。只需要一个操作方便，功能实用，能同时满足财务部门、单位其他相关部门及代发单位三方对数据的管理及需求的系统。企业的目标就是在于利用VFP编程软件开发一个功能实用，用户操作方便，简单明了的工资管理程序。而利唐根据企、事业单位的需求定制了方便实用的工资管理系统。&nbsp;&nbsp;&nbsp;&nbsp;利唐薪酬管</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=120"><img src="../assets/background/ico_25.png"></a></div>
              </div>
                        <div class="swiper-slide" data-swiper-slide-index="19" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=121">好用又便宜的人事系统</a></div>
                <div class="swiper_news_time"><span>2019.11.27</span></div>
                <div class="swiper_news_p">
                  <p>通用版的一站式人力资源管理软件，入职转正，档案管理，自定义审批，一站式关怀、复杂薪资核算、员工就餐方面提供一站式服务。1、界面友好，图形界面帮您轻松上手2、完善的人事系统，方便的数据录入，档案项目设置齐全，留下了扩展空间。支持打印档案表、员工照片。人事报表支持表格列头自定义功能，您可根据您的报表要求自由设置人事报表格式，打印您需要的人事报</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=121"><img src="../assets/background/ico_25.png"></a></div>
              </div>
               <div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="0" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=89">考勤管理系统是能够给企业带来巨大便利的</a></div>
                <div class="swiper_news_time"><span>2019.10.08</span></div>
                <div class="swiper_news_p">
                  <p>　　制造工厂人员较多，再加上无固定休息日，加班倒班较多，HR需要耗费大量的时间来管理员工的排班考勤问题，所以很需要一套适合的管理系统来提升相应的效率。　　1、制造工厂的考勤管理系统到底是什么呢?　　考勤管理系统是专门为制造业设计的，优势就是能够满足不同的排班需求，避免了企业的日常排班陷入混乱，尤其是在订单紧急的时候。　　2、智能化的考勤管</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=89"><img src="../assets/background/ico_25.png"></a></div>
              </div><div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="1" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=91">hr系统的优缺点详细解析您都清楚吗？</a></div>
                <div class="swiper_news_time"><span>2019.10.10</span></div>
                <div class="swiper_news_p">
                  <p>　　hr系统的优缺点都有什么呢?接下来为您介绍一下：　　hr系统难点在于大集团复杂的组织架构，与其他系统集成(尤其是绩效、员工门户)麻烦，从实施经验来说，系统成败不在于软件，而在于HR部门的管理能力。　　大公司建议还是本地布署，一是安全性问题，二是要与其它系统对接;　　SAAS模式的优点，上线快，员工体验好，基本功能和招聘，考勤工资请假，</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=91"><img src="../assets/background/ico_25.png"></a></div>
              </div><div class="swiper-slide swiper-slide-duplicate" data-swiper-slide-index="2" style="width: 380px; margin-right: 30px;">
                <div class="swiper_news_tit"><a href="/?show=92">人事系统的功能</a></div>
                <div class="swiper_news_time"><span>2019.10.10</span></div>
                <div class="swiper_news_p">
                  <p>数据管理功能：数据管理功能主要是指通过基础数据储存，利用系统处理大量数据统计，分类，排查等重复性工作，利用机械算数实现零失误与高效率的管理 ...</p>
                </div>
                <div class="swiper_news_link"><a href="/?show=92"><img src="../assets/background/ico_25.png"></a></div>
              </div></div>
            
            <!-- Add Pagination -->
            
            <div class="swiper-pagination"></div>
          <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
        </div>
      </div>
    </section>
    <section class="container-fluid index_l">
      <div class="container">
        <div class="index_b_title wow fadeInDown animated" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;"><p>立即注册，一起使用ZHKUChen<br>花一套软件的钱，Hold住企业管理方方面面</p> </div>
        <div class="index_top_link wow fadeInUp animated" data-wow-duration="1s" data-wow-delay="0.1s" style="visibility: visible; animation-duration: 1s; animation-delay: 0.1s;"> <a href="/company/login">立即使用</a> </div>
      </div>
    </section>
    <footer class="container-fluid footer">
      <div class="container-fluid footer_top">
        <div class="container">
          <ul>
            <li><a href="/" title="官网">首页</a></li>
                    <li> <a title="最新版本" href="/?list=1">最新版本</a> </li>
                    <li> <a title="企业风格" href="/?list=2">企业风格</a> </li>
                    <li> <a title="创建测评" href="/?list=3">创建测评</a> </li>
                    <li> <a title="在线考试" href="/?list=4">在线考试</a> </li>
                    <li> <a title="组织架构" href="/?list=5">组织架构</a> </li>
                    <li> <a title="关于我们" href="/?list=6">关于我们</a> </li>
                    <li> <a title="报价" href="/">报价</a> </li>
                  </ul>
        </div>
      </div>
      <div class="container-fluid footer_middle">
        <div class="container">
          <div class="db_logo" style="margin-top: 0 !important;"><img src="@/assets/logo.png"></div>
          <div class="db_by"><img src="../assets/background/d_1.png"></div>
          <div class="db_right">
            <div class="db_tel"><span>咨询热线</span><i>400-8062-822</i></div>
            <div class="db_share">
              <ul>
                <li><a href="http://wpa.qq.com/msgrd?v=3&amp;uin=123456&amp;site=qq&amp;menu=yes" target="_blank"><img src="../assets/background/foot_qq.png"></a></li>
                <li><a href="https://weibo.com/" target="_blank"><img src="../assets/background/foot_wb.png"></a></li>
                <li><a href="javascript:void(0);" data-toggle="modal" data-target="#myModal"><img src="../assets/background/foot_wx.png"></a></li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="container-fluid footer_bott">
        <div class="container"> <p>Copyright © 2008-2019 广州仲友信息科技有限公司 <a href="http://www.beian.miit.gov.cn" target="_blank">粤ICP备16020917号-3</a></p><a style="color:#7C7E80;" href="http://www.irenshi.cn/sitemap.xml" target="_blank">网站地图</a></div>
      </div>
    </footer>
  </div>
</template>

<script>
    import 'bootstrap'
    import $ from 'jquery'
    import 'animate.css'
    import axios from 'axios'
    import {WOW} from 'wowjs';
    new WOW({live: false}).init();
    import Swiper from "swiper"
    import homeCarousel from '@/components/bootstrapVue/Carousel/index.vue'
    import 'bootstrap/dist/css/bootstrap.min.css'
    import 'bootstrap/dist/js/bootstrap.min.js'
    import '../utils/homejs/docs.js'
    import '../utils/homejs/Effect.js'
    import '../utils/homejs/jquery.running.min.js'
    import '../utils/homejs/jquery.SuperSlide.js'
    import '@/assets/styles/style.css'
    import '@/assets/styles/adaptive.css'
    import '@/assets/styles/swiper.min.css'
    import '@/assets/styles/JS5.css'
    export default {
        name: 'entry',
        components: {
            homeCarousel,
        },
        data() {
            return {
                activeIndex: '1',
                activeIndex2: '1'
            };
        },
        methods: {
            handleSelect(key, keyPath) {
                console.log(key, keyPath);
            },
            initSwiper() {
                var mySwiper = new Swiper(".swiper-box", {
                    direction: 'horizontal',
                    loop: true, //循环
                    loopFillGroupWithBlank: true,
                    observer: true, //修改swiper自己或子元素时，自动初始化swiper
                    　　observeParents: true, //修改swiper的父元素时，自动初始化swiper
                    paginationClickable: true, //使用分页器
                    　　pagination: {
                        el: '.swiper-pagination',
                    },
                    //触碰后自动切换停止
                    autoplay: {
                        disableOnInteraction: false,
                    },
                })
            }
        },
        mounted() {
            var wow = new WOW({
                boxClass: 'wow',
                animateClass: 'animated',
                offset: 0,
                mobile: true
            })
            wow.init();
            this.initSwiper();
        }
    }
</script>

<style rel="stylesheet/scss" lang="scss">
    .homeHeader {
        height: 78px;
        width: 100%;
        background-color: #545c64;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999999;
        .b-navtoggle {
            margin-left: 10%;
            background-color: #545c64 !important;
            height: 78px;
            .btngroup {
                position: fixed;
                right: 10%;
                .el-button a {
                    color: #fff;
                }
            }
            ;
            #nav-collapse {
                .b-nav {
                    .headnav-item {
                        height: 78px;
                        line-height: 78px;
                        font-size: 15px;
                        width: 86px;
                        text-align: center;
                        font-weight: bold;
                         :hover {
                            color: rgb(255, 208, 75);
                            // border-bottom-color: rgb(255, 208, 75);
                            background-color: rgb(67, 74, 80);
                            -webkit-transition: color .6s ease;
                            -moz-transition: color .6s ease;
                            transition: color .6s ease;
                        }
                        ;
                        a {
                            color: #fff;
                            padding: 0;
                        }
                    }
                }
            }
        }
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
        background: linear-gradient(to right, rgba(30, 67, 86, 0.8), rgba(30, 67, 86, 0.6)), url(../assets/background/hero-bg.jpg) center top no-repeat fixed;
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
</style>