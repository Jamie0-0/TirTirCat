// $.ajax改fetch as a practice

// AddComment
function addComment(data) {

	for (let i = 0; i < data.length; i++) {

		// 這裡動態生成留言
	}

	// Avatar  data.length應從留言數獲得
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
};

// init
$(function() {
	fetch("/TirTirCat/article")
		.then(response => response.json())
		.then(data => {
			if (data === null) {
				alert("這篇文章不存在，即將送您回交流天地");
				setTimeout(function() {
					$(window).attr('location', '/TirTirCat/forum.html');
				}, 2000);
			} else {
				$("p.author").text(data[0].u_name);
				$("p.author").attr("uid", data[0].uid);
				$("time.post-time").text(data[0].art_po_time);
				$("button.blog-button").attr("art_id", data[0].art_id);
				$("#article-title").text(data[0].art_title);
				$("#article-content").text(data[0].art_content);
				$("i.fa-heart").text(data[0].art_like);
				// Share button
				$("#share-tooltip").on("click", () => {
					let urlArt_id = data[0].art_id;
					let url = "localhost:8081/TirTirCat/articleXxx?art_id=" + urlArt_id;
					navigator.clipboard.writeText(url);
					alert(`文章網址${url}已複製成功`);
				})
				// Share button end
			}
		});
	// carousel控tag
	fetch("/TirTirCat/artDnone")
		.then(response => response.json())
		.then(data => {
			for (let i = 5; i > data; i--) {
				$(`#carouPic${i}`).parent('div').remove();
			}
		});

	fetch("/TirTirCat/comment").then(response => response.json()).
	then(data => {
		for(let i = 0; i < data.length; i++){
			
			
		}
	});
	
	
}); // init end


// article like button
$("#article-like").on("click", function() {
	console.log("喜歡");
});

// article comment button
$("#article-comment").on("click", function() {
	console.log("跳到留言");
});

// comment report button
$("i.comment-report").on("click", function() {
	console.log("留言檢舉");
});