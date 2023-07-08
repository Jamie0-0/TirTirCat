$(function() {
  $.ajax({
    url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
    type: "GET",                  // GET | POST | PUT | DELETE | PATCH
    data: {order:"hot"},             // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    success: function(data){      // request 成功取得回應後執行
      $("p.author").text(data[0].u_name)
      $("time.post-time").text(data[0].art_po_time)
      $("h5.card-title").text(data[0].art_title)
      $("p.card-text").text(data[0].art_content)
      $("i.fa-heart").text(data[0].art_like)
    }
  });
});


$("#forum-hot").on("click", function(){
  console.log("點到了")
  var that = $(this);
  $.ajax({
      url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"new"},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(data){      // request 成功取得回應後執行
        $("#forum-new").toggleClass("-off");
        that.toggleClass("-off");
        console.log("==========================")
        $("p.author").text(data[0].u_name)
        $("time.post-time").text(data[0].art_po_time)
        $("h5.card-title").text(data[0].art_title)
        $("p.card-text").text(data[0].art_content)
        $("i.fa-heart").text(data[0].art_like)
      }
    });
    
});


$("#forum-new").on("click", function(){
  console.log("點到了")
  var that = $(this);
  $.ajax({
      url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"hot"},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(data){      // request 成功取得回應後執行
        $("#forum-hot").toggleClass("-off");
        that.toggleClass("-off");
        console.log("==========================")
        $("p.author").text(data[0].u_name)
        $("time.post-time").text(data[0].art_po_time)
        $("h5.card-title").text(data[0].art_title)
        $("p.card-text").text(data[0].art_content)
        $("i.fa-heart").text(data[0].art_like)
      }
    });
    
});

                            // <img src="./images/article1.jpg" class="img-fluid rounded-start article1 w-100" alt="...">
            
                            //         <img src="./images/Avatar.png" alt="Avatar" class="avatar">
                            //         <p class="author">作者Tony</p>
                            //         <time class="post-time">2023-06-17</time>
            
                            //     <h5 class="card-title" style="font-weight: bold;">專業獸醫師教您如何讓狗狗遠離過敏</h5>
                            //     <p class="card-text article">內文</p>
                            //     <div class="blog-contain blog-contain-2">
                            //         <i class="fa-solid fa-heart me-1">案讚數</i>
                            //         <i class="fa-solid fa-comment me-1">留言數</i>