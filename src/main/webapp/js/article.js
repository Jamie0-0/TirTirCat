// init
$(function() {
  $.ajax({
    url: "/TirTirCat/articles/controller/TheArticleController",           // 資料請求的網址
    type: "GET",                  // GET | POST | PUT | DELETE | PATCH
    data: {},             // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
    success: function(data){      // request 成功取得回應後執行
    	    if(data === null){
				    $(window).attr('location','/TirTirCat/forum.html');
			    }else {
            $("p.author").text(data[0].u_name);  
            $("p.author").attr("uid",data[0].uid);
            $("time.post-time").text(data[0].art_po_time);
            $("button.blog-button").attr("art_id",data[0].art_id);
            $("#article-title").text(data[0].art_title);
            $("#article-content").text(data[0].art_content);
            $("i.fa-heart").text(data[0].art_like);
		      }
    }
  });

  fetch("/TirTirCat/articles/controller/TheArtDnoneController").then(function(response){
    return response.json();
  }).then(function(data){
	  console.log(data)
    for(let i = 5; i> data; i--){
      $(`#carouPic${i}`).parent('div').remove();
    }
  });
});

// Share button with tooltip
const myTooltipTriggerList = document.querySelectorAll('[data-bs-toggle="myTooltip"]')
const myTooltipList = [...myTooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

const myTooltipEl = document.getElementById('share-tooltip');
const tooltip = bootstrap.Tooltip.getOrCreateInstance(myTooltipEl);

myTooltipEl.addEventListener('click', () => {
  let url = myTooltipEl.dataset.url;
  navigator.clipboard.writeText(url);
  alert(`文章網址${url}已複製成功`);
})

tooltip.hide()
// Share button with tooltip end

// article like button
$("#article-like").on("click",function(){
    console.log("喜歡");
});

// article comment button
$("#article-comment").on("click",function(){
    console.log("跳到留言");
});

// comment report button
$("i.comment-report").on("click", function(){
    console.log("留言檢舉");
});