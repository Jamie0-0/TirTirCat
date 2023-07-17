// 上文章內容、照片、頭像
function addArt(data) {
	for (var i = 0; i < data.length; i++) {
		$("p.author").eq(i).text(data[i].u_name);
		$("p.author").eq(i).attr("uid", data[i].uid);
		$("time.post-time").eq(i).text(data[i].art_po_time);
		$("button.blog-button").eq(i).attr("art_id", data[i].art_id);
		$("h5.card-title").eq(i).text(data[i].art_title);
		$("p.card-text").eq(i).text(data[i].art_content);
		$("i.fa-heart").eq(i).text(data[i].art_like);
		$("img.pic-content").eq(i).attr("art_id", data[i].art_id);
	}
	// 上照片
	for (let i = 0; i < data.length; i++) {
		let artId = $("img.pic-content").eq(i).attr("art_id");
		let that = $("img.pic-content").eq(i);
		$.ajax({
			url: "/TirTirCat/forum",           // 資料請求的網址
			type: "GET",
			data: {
				order: "getPic",
				art_id: artId
			},
			dataType: "json",
			beforeSend: function() {
				$(that).closest("div").append('<div class="temp_loading"><span><i class="fas fa-spinner fa-spin"></i></span></div>');
			},
			complete: function() {
				$(that).closest("div").find("div.temp_loading").remove();
			}
		});
		$("img.pic-content").eq(i).attr("src", "/TirTirCat/forum" + "?order=getPic&art_id=" + artId);  // 封面圖綁src
	}

	// Avatar Picture
	for (let i = 0; i < data.length; i++) {
		let uid = $("p.author").eq(i).attr("uid");
		$.ajax({
			url: "/TirTirCat/forum",
			type: "GET",
			data: {
				order: "getAvatar",
				"uid": uid
			},
			dataType: "json",
		});
		$("img.avatar").eq(i).attr("src", "/TirTirCat/forum" + "?order=getAvatar&uid=" + uid);  // 替avatar上src
	}
} // addArt()結束

function disControl(length) {
	$(".paragraph2,.paragraph3").removeClass("d-none");
	switch (length) {
		case 1:
			console.log("執行1")
			$(".paragraph2, .paragraph3").addClass("d-none"); // 隱藏段落二和段落三
			break;
		case 2:
			console.log("執行2")
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

// 點擊分頁標籤的事件
function clickPageTag(order, searchText, pageIdx) {
	$.ajax({
		url: "/TirTirCat/forum",
		pe: "GET",
		data: { "order": order, "searchText": searchText, "page": pageIdx },
		dataType: "json",
		success: function(data) {
			addArt(data);
			disControl(data.length);
		}
	});
}

// 製造分頁標籤，綁定事件
function pageTagCreator(order, searchText, page) {
	$("a.btn-page").slice(0).remove();
	for (let i = 1; i <= page; i++) {
		let newButton = `<a class="btn btn-secondary btn-page">${i}</a>`;
		$("#forum-page").before(newButton);
		$("a.btn-page").eq(i - 1).on("click", function() {
			clickPageTag(order, searchText, i);
		});
	}
}

function fctSearch(data) {
	let length = data.length;
	if (length == 0) {
		alert("沒有相關搜尋結果")
	} else {
		addArt(data);
		disControl(length);
	}
};
// init
function init() {
	$.ajax({
		url: "/TirTirCat/forum",           // 資料請求的網址
		type: "GET",                  // GET | POST | PUT | DELETE | PATCH
		data: { order: "hot", page: "1" },             // 將物件資料(不用雙引號) 傳送到指定的 url
		dataType: "json",             // 預期會接收到回傳資料的格式： json | xml | html
		success: function(data) {      // request 成功取得回應後執行
			addArt(data);
		}
	});

	// 添加分頁
	$.ajax({
		url: "/TirTirCat/forumPage",
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
$("button.blog-button").on("click", function(e) {
	let artId = $(this).attr("art_id");
	$.ajax({
		url: "/TirTirCat/forum",
		type: "GET",
		data: { order: "article", art_id: artId },
		dataType: "json",
		success: function() {
			$(window).attr('location', '/TirTirCat/article.html');
		}
	});
});

// 熱門按鈕點擊變最新
$("#forum-hot").on("click", function() {
	var that = $(this);
	$.ajax({
		url: "/TirTirCat/forum",
		type: "GET",
		data: { order: "new", page: "1" },
		dataType: "json",
		success: function(data) {
			$("#forum-new").toggleClass("-off");
			that.toggleClass("-off");
			addArt(data);
		}
	});
	$.ajax({
		url: "/TirTirCat/forumPage",
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
});

// 搜尋
$("#forum-search-btn").on("click", function(e) {

	let searchText = $("#forum-search-input").val();
	$.ajax({
		url: "/TirTirCat/forum",
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
		url: "/TirTirCat/forumPage",
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
	$("#forum-search-input").val("");
});

// 搜尋Enter

$("#forum-search-input").on("keydown", function(e) {
	if (e.which == 13) {
		e.preventDefault();
		let searchText = $("#forum-search-input").val();
		$.ajax({
			url: "/TirTirCat/forum",
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
			url: "/TirTirCat/forumPage",
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
		$("#forum-search-input").val("");
	}
});