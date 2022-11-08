$(document).ready(function(){

    //전체메뉴
    $("#menu1_wrap").hide();
    $("#main_navi ul li").eq(0).click(function(){
        $("#menu1_wrap").toggle();
    });

    // .toggleClass("emphasized");

    //메인navi
    $(".hide1").hide();
    $("#menu_wrap").hide();
    $("#main_navi ul li+li.sel").mouseover(function () {
        // $(".hide1", this).stop().slideDown();
        $(".hide1", this).css({"position":"absolute","left":"660px","top":"65px"});
        $(".hide1", this).show();
        $("#menu_wrap").show();
    }).mouseout(function () {
        // $(".hide1", this).stop().slideUp();
        $(".hide1", this).hide();
        $("#menu_wrap").hide();
    });

});

$(window).on('load', function() {
    //메인이미지
    var slide = $(".bxslider").bxSlider({
        auto:true,mode:'horizontal',
    });
});