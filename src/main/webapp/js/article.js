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
				$("#ownerAvatar").attr("src", "/TirTirCat/avatar?uid=" + data[0].uid);

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

			for (let i = 0; i < data.length; i++) {
	//			$(".com_username").text(data[i].user.u_name);
	//			$(".com-time").text(data[i].com_date_time);
				$("button.com-button").attr("com_id", data[i].com_id);
			//	$(".com-report").attr("com_id", data[i].com_id);
		//		$(".comment").text(data[i].com_content);
				$(".com-reply-btn").attr("com_id", data[i].com_id);
				
				 const responseItem = `
					<div class="card w-100">
								<div class="card-body">
									<h5 class="card-title">
										<div class="d-flex">
											<div>
												<img alt="Avatar" class="avatar rounded-circle img-fluid col">
											</div>
											<div class="list-group-item" style="border: none; background-color: #F6F0ED;">
												<p class="com_username"></p>
											</div>
											<div class="list-group-item" style="border: none; background-color: #F6F0ED;">
												<time class="com_time"></time>
											</div>
										</div>
									</h5>
									<div class="article-comment-pic position-relative ms-1">
										<!-- <img src="./images/article6.jpg" class="card-text w-25"> -->
									</div>
									<div class="article-comment-text position-relative ms-5 end-0 center">
										<p class="card-text w-100 com-content">來點文字內容來點文字內容來點文字內容來點文字內容</p>
									</div>
									<div class="comment-report position-absolute top-0 end-0 me-1">
										<i type="button" class="fa-solid fa-flag com-report"
											data-bs-toggle="modal" data-bs-target="#staticBackdrop"></i>
									</div>
									<div>
										<i type="button" class="fa-solid fa-reply com-reply-btn fa-rotate-180 ms-1"></i>
									</div>
									<div class="com-reply-wrapper d-none">
							</div>
    `;

    	$("#com_wrapper").append(responseItem);
    	
			}
		});



	// 擴充: 回覆的回覆
	$(".com-reply-btn").on("click", function() {

		if ($("div.com-reply-wrapper").hasClass("d-none")) {
			$("div.com-reply-wrapper").removeClass("d-none");
			// 要發送的 reply_com_id

			let reply_com_id = $(".com-reply-btn").attr("com_id");
			let url = "/TirTirCat/reply?reply_com_id=" + reply_com_id;

			fetch(url)
				.then(response => response.json())
				.then(data => {
					for (let i = 0; i < data.length; i++) {

						const comReplyWrapper = $(".com-reply-wrapper");
						const replyItem = `
    <div class="card mb-3">
      <div class="row g-0">
        <ul class="list-group list-group-horizontal" style="vertical-align: center;">
          <img alt="Avatar" class="avatar rounded-circle img-fluid col" alt="./images/Avatar.png" src="/TirTirCat/avatar?uid=${data[i].reply_user_id}">
          <li class="list-group-item reply_username" style="border: none; background-color: #F6F0ED;">${data[i].user.u_name}</li>
          <li class="list-group-item reply_time d-none" style="border: none; background-color: #F6F0ED;">${data[i].reply_date_time}</li>
        </ul>
        <div class="article-comment-text">
          <p class="card-text w-100 reply">${data[i].reply_content}</p>
        </div>
        <div>
          <i type="button" class="fa-solid fa-flag reply-report position-absolute end-0 top-0 mt-1 me-1" data-bs-toggle="modal" data-bs-target="#staticBackdrop" com_id="${data[i].reply_id}"></i>
        </div>					      
      </div>
    </div>
  `;
						comReplyWrapper.append(replyItem);
					}
				});
		} else {
			$("div.com-reply-wrapper").addClass("d-none").empty();
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