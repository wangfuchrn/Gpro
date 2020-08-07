import $ from 'jquery'
import Swiper from "swiper"
//导航二级下拉
$(document).ready(function () {
    var pc=$(window).width();
    if(pc>1200){
        $(".head_nav_m ul li").mouseover(function () {
            $(this).children(".navTwo").slideDown();
        });
        $(".head_nav_m ul li").mouseleave(function () {
            $(this).children(".navTwo").slideUp();
        });
    }else{
        $(".top_down").click(function () {
            $(".top_down").addClass("fa-angle-down");
            if($(this).siblings(".navTwo").is(":hidden")){
                $(".navTwo").slideUp();
                $(".top_down").removeClass("fa-angle-up");
                $(this).siblings(".navTwo").slideDown();
                $(this).removeClass("fa-angle-down");
                $(this).addClass("fa-angle-up");
            }else{
                $(this).siblings(".navTwo").slideUp();
                $(this).removeClass("fa-angle-up");
                $(this).addClass("fa-angle-down");

            }
        });
    }
});
$(document).ready(function () {
    $(".top_butt").click(function () {
        if(!$(".head_nav_wap").hasClass('head_nav_on')){
            $(".head_nav_wap").addClass('head_nav_on');
            $(this).addClass('cross');
        }else {
            $(".head_nav_wap").removeClass('head_nav_on');
            $(this).removeClass('cross');
        }

    });
});


//首页切换卡
$(document).ready(function () {
    $(".D_qh_card dd:nth-child(1)").css('display','block');
    $(".D_qh_butt dd").click(function () {
        var _index = $(this).index();
        $(".D_qh_card dd").eq(_index).show().siblings().hide();
        $(this).addClass("D_qh_on").siblings().removeClass("D_qh_on");
    });
});

//首页新闻
$(document).ready(function () {
    var swiper = new Swiper('.swiper_news', {
        slidesPerView: 3,
        spaceBetween: 30,
        autoplay:{
            disableOnInteraction: false,
        },
        loop : true,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        breakpoints: {
            1024: {
                slidesPerView: 3,
                spaceBetween: 10,
            },
            768: {
                slidesPerView: 2,
                spaceBetween: 10,
            },
            576: {
                slidesPerView: 1,
            },
        },
    });
});

$(document).ready(function(){
    $('.more_jtx i').click(function() {
        $('html,body').animate({ scrollTop: $(".about_a").offset().top - 86 }, 500);
        var more_web=$(window).width();
        if(more_web<=576){
            $('html,body').animate({ scrollTop: $(".about_a").offset().top - 60 }, 500);
        }
    });
});







