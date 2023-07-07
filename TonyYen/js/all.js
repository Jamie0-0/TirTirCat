$("img.avatar").on("click",function(){
    console.log("點到頭像");
});

$("button.forum-toggle").on("click", function(){
    $("button.forum-toggle").toggleClass("-off");
    console.log("點到喔")
})