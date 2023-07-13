function createCard() {
  var divCol = $('<div>').addClass('col-12');
  var card = $('<div>').addClass('card mb-3');
  var row = $('<div>').addClass('row g-0');
  var colMd4 = $('<div>').addClass('col-md-4');
  var img = $('<img>').addClass('img-fluid rounded-start article1 w-100 pic-content').attr('alt', '載入失敗');
  var colMd8 = $('<div>').addClass('col-md-8');
  var cardBody = $('<div>').addClass('card-body');
  var profile = $('<div>').addClass('profile row m-0 d-flex align-items-center');
  var avatar = $('<img>').attr('src', './images/Avatar.png').attr('alt', 'Avatar').addClass('avatar rounded-circle img-fluid col');
  var author = $('<p>').addClass('author col mb-0');
  var div = $('<div>').addClass('row w-100');
  var postTime = $('<time>').addClass('post-time col');
  var cardTitle = $('<h5>').addClass('card-title').css('font-weight', 'bold');
  var cardText = $('<p>').addClass('card-text article');
  var blogContain = $('<div>').addClass('blog-contain blog-contain-2');
  var heartIcon = $('<i>').addClass('fa-solid fa-heart me-1');
  var commentIcon = $('<i>').addClass('fa-solid fa-comment me-1');
  var button = $('<button>').addClass('blog-button').text('繼續閱讀');

  // 將元素進行組合
  div.append(postTime);
  profile.append(avatar, author);
  blogContain.append(heartIcon, commentIcon, button);
  cardBody.append(profile, div, cardTitle, cardText, blogContain);
  colMd8.append(cardBody);
  colMd4.append(img);
  row.append(colMd4, colMd8);
  card.append(row);
  divCol.append(card);

  return divCol;
}



function addArt(data) {
  $("#article-area").empty();
  for (var i = 0; i < data.length; i++) {
    var generatedHTML = createCard();
    $("#article-area").append(generatedHTML);  // 將生成的 HTML 插入到目標元素中
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
      url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
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
	$("img.pic-content").eq(i).attr("src","/TirTirCat/articles/controller/ArticlesController"+"?order=getPic&art_id="+artId);
  }
  
  // Avatar Picture
    for(let i = 0; i < data.length; i++){
    let uid = $("p.author").eq(i).attr("uid");
    console.log(uid);
    $.ajax({
      url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
      type: "GET",                  // GET | POST | PUT | DELETE | PATCH
      data: {order:"getAvatar",
		  "uid":uid},             // 將物件資料(不用雙引號) 傳送到指定的 url
      dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
      success: function(data){      // request 成功取得回應後執行
      },
    });
	$("img.avatar").eq(i).attr("src","/TirTirCat/articles/controller/ArticlesController"+"?order=getAvatar&uid="+uid);
  }
  
	    // 繼續閱讀
  $("button.blog-button").on("click", function(e){
  var artId = $(this).attr("art_id");
  console.log("artId="+artId);
  $.ajax({
    url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
    type: "GET",                  // GET | POST | PUT | DELETE | PATCH
    data: {art_id:artId},             // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    success: function(data){      // request 成功取得回應後執行
    	console.log("得到art_id回應")
    }
  });
});
};

// load
$(function() {
  $.ajax({
    url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
    type: "GET",                  // GET | POST | PUT | DELETE | PATCH
    data: {order:"hot",page:"1"},             // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    success: function(data){      // request 成功取得回應後執行
      addArt(data);
      
    }
  });
});

// 熱門
$("#forum-hot").on("click", function(){
  var that = $(this);
  $.ajax({
      url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
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
      url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
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
      url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
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
    var text = $("#forum-search-input").val();
  $.ajax({
      url: "/TirTirCat/articles/controller/ArticlesController",           // 資料請求的網址
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