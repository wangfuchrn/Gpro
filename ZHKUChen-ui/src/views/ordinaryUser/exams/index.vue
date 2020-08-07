<template>
  <div id="entry" class="main">
    <u-header></u-header>
    <section class="container-fluid section">
      <div class="sectionFirstContain">
        <!-- 搜索框 -->
        <div class="search-form">
          <el-form ref="examForm" :inline="true" :model="query" label-width="100px" class="examForm">
            <el-form-item label="考试名称" prop="examinationName">
              <el-input v-model="query.examinationName" autocomplete="off" placeholder="考试名称" />
            </el-form-item>
            <el-form-item>
              <el-button type="primary">搜索</el-button>
              <!-- <el-button type="primary" @click="submitForm('examForm')">搜索</el-button> -->
              <el-button>重置</el-button>
              <!-- <el-button @click="resetForm('examForm')">重置</el-button> -->
            </el-form-item>
          </el-form>
        </div>
        <div class="exams-list">
          <el-tabs v-model="activeName" @tab-click="handleClick" class="examsTab">
            <el-tab-pane label="未完成" name="todo" :key="'todo'" class="el-tab-pane-item">
              <u-processed v-if="isTodo"/>
            </el-tab-pane>
            <el-tab-pane label="已完成" name="finish" :key="'finish'">
              <processed v-if="isFinish" />
            </el-tab-pane>
          </el-tabs>
        </div>
      </div>
    </section>
  </div>
</template> 
<script>
   import UHeader from '../../../components/UserHeader/index.vue'
   import Processed from './processed/index.vue'
   import UProcessed from './unProcessed/index.vue'
   export default {
      name: 'entry',
      components: {
        UHeader,
        Processed,
        UProcessed,
      },
      filters : {
        timeFilter (time) {
          return formatDate(new Date(time), 'yyyy-MM-dd hh:mm')
        }
      },
      data() {
        return {
          activeName: 'todo',
          isTodo: true,
          isFinish: false,
          total: 0,
          loading: true,
          examList: [],
          isLastPage: false,
          query: {
            sort: 'id',
            order: ' asc',
            pageNum: 1,
            pageSize: 6,
            examinationName: '',
            status: 0
          },
          tempExamRecord: {
            id: null,
            userId: null,
            examinationId: null
          },
          
        }
      },
      methods: {
        handleClick(tab) {
          if (tab.name === 'todo') {
            this.isTodo = true
            this.isFinish = false
          } else if (tab.name === 'finish') {
            this.isTodo = false
            this.isFinish = true
          }
        },
        resetForm () {
          this.query.examinationName = ''
        },
        submitForm () {
          this.query.pageNum = 1
          this.getExamList()
        },
      },
   }
</script> 

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" rel="stylesheet/scss" scoped>
  .exam-empty {
    font-size: 13px;
    color: #999;
    text-align: center;
  }
  .section{
        width: 100%;
        height: 100%;
        margin-top: 10%;
        .sectionFirstContain{
        }
    }
  .exams-list {
    margin: 0 auto 30px;
    padding: 15px 10px;
    width: 98%;
    box-shadow: 0 5px 15px 0 rgba(82,94,102,.1);
    border-radius: 4px;
    .examsTab .el-tabs__header{
      margin: 0 auto 30px;
      padding: 15px 10px;
      box-shadow: 0 5px 15px 0 rgba(82,94,102,.1);
      border-radius: 4px;
      .el-tabs__nav-wrap .el-tabs__nav-scroll:after{
        background-color:#fff !important;
      }
    }
    ul {
      margin: 0;
      overflow: hidden;
    }
    .active {
      color: #fff;
      background: #409eff;
    }
    li  {
      list-style: none;
      display: block;
      float: left;
      margin: 10px;
      padding: 0 15px;
      height: 24px;
      line-height: 24px;
      color: #666;
      font-size: 13px;
      border-radius: 5px;
      cursor: pointer;
    }
    .el-tabs__item{
      list-style: none;
      display: block;
      float: left;
      margin: 10px;
      padding: 0 15px;
      height: 24px;
      line-height: 24px;
      color: #666;
      font-size: 13px;
      border-radius: 5px;
      cursor: pointer;
    }
  }

  .exam-card-list {
    width: 100%;
    height: auto;
    overflow: auto;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
    flex-direction: row;
    .card-item {
      width: 30%;
      position: relative;
      margin-bottom: 100px;
      border-radius: 6px;
      box-shadow: 0 3px 8px rgba(0, 0, 0, 0.15);
      .card-item-snapshoot {
        border: 1px solid rgba(0,0,0,.15);
        background-origin: border-box;
        background-size: cover;
        background-color: #f0f0f0;
        background-position: 49% 38% ;
        height: 172px;
        display: block;
        border-radius: 6px 6px 0 0;
      }
      .card-item-detail {
        padding: 20px;
        .card-item-name {
          display: -webkit-box;
          overflow: hidden;
        }
        .card-item-course {
          // --x-height-multiplier: 0.342;
          // --baseline-multiplier: 0.22;
          font-weight: 300;
          font-style: normal;
          letter-spacing: 0;
          .card-item-course-detail {
            color: rgba(0,0,0,.54);
            fill: rgba(0,0,0,.54);
          }
        }
      }
    }
    >i {
      width: 30%;
      padding-right: 12px;
    }
  }
</style>