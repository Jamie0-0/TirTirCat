function addArt(data) {
  for (var i = 0; i < data.length; i++) {
    $("p.author").eq(i).text(data[i].u_name);
    $("time.post-time").eq(i).text(data[i].art_po_time);
    $("h5.card-title").eq(i).text(data[i].art_title);
    $("p.card-text").eq(i).text(data[i].art_content);
    $("i.fa-heart").eq(i).text(data[i].art_like);
  }
}


$(function() {
  $.ajax({
    url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
    type: "GET",                  // GET | POST | PUT | DELETE | PATCH
    data: {order:"hot"},             // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    success: function(data){      // request 成功取得回應後執行
      addArt(data);
      
    }
  });
});


$("#forum-hot").on("click", function(){
  var that = $(this);
  $.ajax({
      url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"new"},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(data){      // request 成功取得回應後執行
        $("#forum-new").toggleClass("-off");
        that.toggleClass("-off");
        addArt(data);
      }
    });
    
});


$("#forum-new").on("click", function(){
  var that = $(this);
  $.ajax({
      url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"hot"},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(data){      // request 成功取得回應後執行
        $("#forum-hot").toggleClass("-off");
        that.toggleClass("-off");
        addArt(data);
      }
    });
    
});