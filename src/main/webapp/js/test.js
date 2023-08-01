let currentPage = 1;
let searchText = "";

// 上文章內容、照片、頭像
function addArt(data) {
	if (data != null) {
		for (let i = 0; i < data.length; i++) {
			$("p.author").eq(i).text(data[i].u_name);
			$("p.author").eq(i).attr("uid", data[i].uid);
			$("time.post-time").eq(i).text(data[i].art_po_time);
			$("button.blog-button").eq(i).attr("art_id", data[i].art_id);
			$("h5.card-title").eq(i).text(data[i].art_title);
			$("div.card-text").eq(i).empty();
			$("div.card-text").eq(i).append(data[i].art_content);
			$("i.fa-heart").eq(i).text(data[i].art_like);
			$("img.pic-content").eq(i).attr("art_id", data[i].art_id);
			
			// 點愛心
			$("i.fa-heart").eq(i).on("click", function(e){
				// 避免重複點擊
				e.stopPropagation();
				$(this).css("pointer-events", "none");
				fetch(`artLike?art_id=${data[i].art_id}&uid=3`)    // 登入的人 暫定uid=3
				.then(response => response.json())
				.then(data => {
					if(data === -1){
						
					}else{
						$("i.fa-heart").eq(i).text(data);
					}
				})
				.finally(() => {
				// 從元素中完全移除pointer-events屬性，恢復點擊事件
					$(this).removeAttr("style");
				});
			});
			
			// 上照片
			let artId = $("img.pic-content").eq(i).attr("art_id");

			$("img.pic-content").eq(i).attr("src", "forum" + "?order=getPic&art_id=" + artId);  // 封面圖綁src
					
			// Avatar Picture
			let uid = $("p.author").eq(i).attr("uid");
			$("img.avatar").eq(i).attr("src", "avatar?uid=" + uid);  // 替avatar上src
			
			// ComCount
			$.ajax({
				url: `artComCount?com_art_id=${data[i].art_id}`,           // 資料請求的網址
				type: "GET",
				dataType: "json",
				success: function(data) {
					$("i.fa-comment").eq(i).text(data);
				}
			});
		}
		
		for(let i= 0; i<3; i++){
			if($("iframe").eq(i) != null){
				$("iframe").eq(i).removeAttr("width").removeAttr("height");  // 避免YT影片跑版
				$("iframe").addClass("mw-100").css("aspect-ratio", "16/9");
			}
		}
	}
	
} // addArt()結束

function disControl(length) {
	$(".paragraph1, .paragraph2, .paragraph3").removeClass("d-none");
	$("#notFound").addClass("d-none");
	switch (length) {
		case 0:
			$(".paragraph1, .paragraph2, .paragraph3").addClass("d-none");
			$("#notFound").removeClass("d-none");
			break;
		case 1:
			$(".paragraph2, .paragraph3").addClass("d-none"); // 隱藏段落二和段落三
			break;
		case 2:
			$(".paragraph3").addClass("d-none"); // 隱藏段落三
			break;
		default:
			break;
	}
};  // disControl end

function pageFilter(data) {
	let page = 1;
	if (data > 3 && data % 3 == 0) {
		page = data / 3;
		disControl(3);  // length = 3 預設顯示三筆
	} else if (data > 3 && data % 3 != 0) {
		page = parseInt(data / 3) + 1; // 使用 parseInt 將結果轉換為整數
		disControl(3);
	} else {
		if (data == 0) {
			disControl(3)
		} else {
			disControl(data);   // data = 1 or 2 or 3
		}
	}
	return page;
}

// 點擊分頁標籤的事件 發頁數給後端
function clickPageTag(order, searchText, page) {
	$.ajax({
		url: "forum",
		pe: "GET",
		data: { "order": order, "searchText": searchText, "page": page },
		dataType: "json",
		success: function(data) {
			addArt(data);  // 渲染到畫面上
			if (data===null) {
				currentPage = page-1; // 請忽略
			}else{
				disControl(data.length);
				currentPage = page;
			}
		}
	});
};

// 製造分頁標籤，綁定事件
function pageTagCreator(order, searchText, page) { //先傳請求查總共有幾筆，數學運算後回傳頁數傳進方法裡
	$("a.btn-page").slice(0).remove(); // 移除先前已存在的頁籤
	for (let i = 1; i <= page; i++) { // 看有幾頁就用迴圈跑幾次
		let newButton = `<a class="btn btn-secondary btn-page">${i}</a>`; // i是頁數
		$("#forum-page").append(newButton); // ->插入一個分頁按鈕
		$("a.btn-page").eq(i - 1).on("click", function() {
			
			clickPageTag(order, searchText, i);  
			// 綁點擊事件 傳i(頁數)到方法 方法用ajax發頁數給後端
		});
	}
};


function fctSearch(data) {
	let length = data.length;
	if (length == 0) {
		disControl(length);
		alert("沒有相關搜尋結果")
	} else {
		addArt(data);
	}
};
// init
function init() {
	
	
	
	$.ajax({
		url: "forum",           // 資料請求的網址
		type: "GET",                  // GET | POST | PUT | DELETE | PATCH
		data: { order: "hot", page: "1" },             // 將物件資料(不用雙引號) 傳送到指定的 url
		dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		success: function(data) {      // request 成功取得回應後執行
			addArt(data);
		}
	});
	
	// 存全部文章去Redis
	fetch("saveAllArt");

	// 添加分頁
	$.ajax({
		url: "forumPage",
		type: "GET",
		data: {},
		dataType: "json",
		success: function(data) {
			let page = pageFilter(data);
			pageTagCreator("hot", "", page);
		}
	});
};// init end

// 載入頁面
$(function() {
	init();
});


// 繼續閱讀
$("button.blog-button").on("click", function() {
	let artId = $(this).attr("art_id");
	$(window).attr('location', 'article.html');
	$.ajax({
		url: "forum",
		type: "GET",
		data: { order: "article", art_id: artId },
		dataType: "json",
		success: function(data) {
			
		}
	});
});

// 熱門按鈕點擊變最新
$("#forum-hot").on("click", function() {
	var that = $(this);
	$.ajax({
		url: "forum",
		type: "GET",
		data: { order: "new", page: "1" },
		dataType: "json",
		success: function(data) {
			$("#forum-new").toggleClass("-off");
			that.toggleClass("-off");
			addArt(data);
			currentPage = 1;
			$("#forum-search-input").val("");
		}
	});
	$.ajax({
		url: "forumPage",
		type: "GET",
		data: {},
		dataType: "json",
		success: function(data) {
			let page = pageFilter(data);
			pageTagCreator("new", "", page);
		}
	});
});


// 最新按鈕點擊變熱門
$("#forum-new").on("click", function() {
	var that = $(this);
	$("#forum-hot").toggleClass("-off");
	that.toggleClass("-off");
	init();
	currentPage = 1;
});

// 搜尋
$("#forum-search-btn").on("click", function() {
	currentPage = 1;
	searchText = $("#forum-search-input").val();
	$.ajax({
		url: "forum",
		type: "GET",
		data: {
			order: "search",
			"searchText": searchText
		},
		dataType: "json",
		success: function(data) {
			fctSearch(data);
		}
	});
	$.ajax({
		url: "forumPage",
		type: "GET",
		data: { "searchText": searchText },
		dataType: "json",
		success: function(data) {
			let page = pageFilter(data);
			pageTagCreator("search", searchText, page);
			if ($("#forum-hot").hasClass("-off")) {
				$("#forum-new").toggleClass("-off");
				$("#forum-hot").toggleClass("-off");
			}
		}
	});
});

// 搜尋Enter

$("#forum-search-input").on("keydown", function(e) {
	if (e.which == 13) {
		e.preventDefault();
		currentPage = 1;
		searchText = $("#forum-search-input").val();
		$.ajax({
			url: "forum",
			type: "GET",
			data: {
				order: "search",
				"searchText": searchText
			},
			dataType: "json",
			success: function(data) {
				fctSearch(data);
			}
		});
		$.ajax({
			url: "forumPage",
			type: "GET",
			data: { "searchText": searchText },
			dataType: "json",
			success: function(data) {
				let page = pageFilter(data);
				pageTagCreator("search", searchText, page);
				if ($("#forum-hot").hasClass("-off")) {
					$("#forum-new").toggleClass("-off");
					$("#forum-hot").toggleClass("-off");
				}
			}
		});
	}
});