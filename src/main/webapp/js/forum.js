$(function() {
  console.log( "ready!" );
});





$("button.forum-toggle").on("click", function(){
    console.log("點到了")

    $.ajax({
        url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
        type: "GET",                  // GET | POST | PUT | DELETE | PATCH
        // data: 物件資料,             // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
        success: function(data){      // request 成功取得回應後執行
          $("button.forum-toggle").toggleClass("-off");
          console.log(data);
          console.log(data[0].u_name)
          $("p.author")[0].text(data[0].u_name)
        }
      });
    
});



