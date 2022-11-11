$(document).ready(function(){
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