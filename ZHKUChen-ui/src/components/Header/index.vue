<template>
    <header class="header clearfix">
        <!-- nav-bar -->
        <div class="header-nav clearfix">
          <div class="main-menu">
            <ul class="clearfix">
              <li class="menu-item">
                <img class="logo" src="@/assets/logo.png">
              </li>
              <li class="skip menu-item">
                <p>我是个人用户，请</p>
              </li>
                <router-link class="skiplink menu-item" index="1" tag="li" to="/"><p>点击这里</p></router-link>
            </ul>
          </div>
        </div>
      </header>
</template>
<script>
    import axios from 'axios'
    export default {
        data() {
            return {

            }
        }
    }
</script>

<style lang="scss" scoped>
    /* 头部 begin */ 
    .header {
        width: 100%;
        height: 70px;
        background-color: #f7f8f8;
        position: fixed;
        top: 0;
        left: 0;
        z-index: 999;
        .header-nav {
            width: 100%;
            height: 100%;
            background: #f7f8f8;
            .main-menu {
                text-align: center;
                .menu-item {
                    height: 70px;
                    line-height: 70px;
                    float: left;
                    margin-right: 50px;
                    list-style:none;
                    margin-top: -18px;
                    li {
                        font-size: 16px;
                        list-style:none;
                    }
                }  
                .skip{
                    float: right;
                    height: 70px;
                    line-height: 70px;
                    margin-right: 25%;  
                    p{
                        color:grey;
                    }
                }
                .skiplink{
                    list-style:none;
                    color:#000;
                    float: right;
                    height: 70px;
                    line-height: 70px;
                    margin-right: -18%;
                    &:hover {
                        cursor:pointer;
                    }
                } 
                  
            }
        }
        .logo {
          height: 88px;
          width: 310px;
        }
    }
    /* 头部 end */
    
    .el-menu.el-menu--horizontal {
        border-bottom: 0;
    }
    
    .el-menu--horizontal>.el-submenu.is-active .el-submenu__title {
        border-bottom: 0;
    }
</style>