function addArt(data) {
  for (var i = 0; i < data.length; i++) {
    $("p.author").eq(i).text(data[i].u_name);
    $("p.author").eq(i).attr("uid",data[i].uid);
    $("time.post-time").eq(i).text(data[i].art_po_time);
    $("button.blog-button").eq(i).attr("art_id",data[i].art_id);
    $("h5.card-title").eq(i).text(data[i].art_title);
    $("p.card-text").eq(i).text(data[i].art_content);
    $("i.fa-heart").eq(i).text(data[i].art_like);
    // $("img.pic-content").eq(i).attr("src", 'data:image/jpeg;base64,'+data[i].pic_content);  //圖文一起載
    $("img.pic-content").eq(i).attr("art_id",data[i].art_id);
	}
	
	// Article Picture
  for(let i = 0; i < data.length; i++){
    let artId = $("img.pic-content").eq(i).attr("art_id");
	let that = $("img.pic-content").eq(i);
    $.ajax({
      url: "/TirTirCat/forum",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"getPic",
		  art_id:artId},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      beforeSend: function(){       // 在 request 發送之前執行
      $(that).closest("div").append('<div class="temp_loading"><span><i class="fas fa-spinner fa-spin"></i></span></div>');
      },
      success: function(data){      // request 成功取得回應後執行
      // $("img.pic-content").eq(i).attr("src", "data:image/jpeg;base64,"+data.pic_content);
      },
      complete: function(){      // request 完成之後執行(在 success / error 事件之後執行)
      $(that).closest("div").find("div.temp_loading").remove();
      }
    });
	$("img.pic-content").eq(i).attr("src","/TirTirCat/forum"+"?order=getPic&art_id="+artId);
  }
  
  // Avatar Picture
    for(let i = 0; i < data.length; i++){
    let uid = $("p.author").eq(i).attr("uid");
    $.ajax({
      url: "/TirTirCat/forum",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"getAvatar",
		  "uid":uid},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(data){      // request 成功取得回應後執行
      },
    });
	$("img.avatar").eq(i).attr("src","/TirTirCat/forum"+"?order=getAvatar&uid="+uid);
  }
};

// init
$(function() {
  $.ajax({
    url: "/TirTirCat/forum",           // 資料請求的網址
    type: "GET",                  // GET | POST | PUT | DELETE | PATCH
    data: {order:"hot",page:"1"},             // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    success: function(data){      // request 成功取得回應後執行
      addArt(data);
    }
  });
});

// 繼續閱讀
$("button.blog-button").on("click", function(e){
  let artId = $(this).attr("art_id");
  $.ajax({
    url: "/TirTirCat/forum",           // 資料請求的網址
    type: "GET",                  // GET | POST | PUT | DELETE | PATCH
    data: {order:"article",art_id:artId},             // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    success: function(){      // request 成功取得回應後執行
		$(window).attr('location','/TirTirCat/article.html');
    }
  });
});

// 熱門
$("#forum-hot").on("click", function(){
  var that = $(this);
  $.ajax({
      url: "/TirTirCat/forum",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"new",page:"1"},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(data){      // request 成功取得回應後執行
        $("#forum-new").toggleClass("-off");
        that.toggleClass("-off");
        addArt(data);
      }
    });
    
});

// 最新
$("#forum-new").on("click", function(){
  var that = $(this);
  $.ajax({
      url: "/TirTirCat/forum",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"hot",page:"1"},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(data){      // request 成功取得回應後執行
        $("#forum-hot").toggleClass("-off");
        that.toggleClass("-off");
        addArt(data);
      }
    });
    
});

// 搜尋
$("#forum-search-btn").on("click", function(){
  var text = $("#forum-search-input").val();
  $.ajax({
      url: "/TirTirCat/forum",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"search",
      searchText:text},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(data){      // request 成功取得回應後執行
        if(data.length == 0){
          alert("沒有相關搜尋結果")
        }else{
          addArt(data);
        }
        $("#forum-search-input").val("");
      }
    });
    
});

// 搜尋Enter

$("#forum-search-input").on("keydown", function(e){
  if(e.which == 13){
    e.preventDefault();
    let text = $("#forum-search-input").val();
  $.ajax({
      url: "/TirTirCat/forum",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"search",
        searchText:text},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(data){      // request 成功取得回應後執行
        if(data.length == 0){
          alert("沒有相關搜尋結果")
        }else{
          addArt(data);
        }
        $("#forum-search-input").val("");
      }
    });
  }
});

// 分頁功能