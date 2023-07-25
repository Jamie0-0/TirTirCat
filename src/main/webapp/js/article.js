// init
let urlArt_id = 0;
$(function() {
	
		
	if (window.location.search.includes("fromRule=true")){
		console.log("從rule進來")
		$(".from-rule").addClass(" d-none");
		$(".carousel-item").remove();
		$("#upload_img_label").removeClass("d-none")
		
		$('#upload_img').on('change', function() {
			let imgFiles = $(this)[0].files;
			$(".carousel-item").remove();
			if(imgFiles.length > 5){
				alert("最多只能上傳五張照片，請重新選擇")
				return;
			}
			for (i = 0; i < imgFiles.length; i++) {
				filePath = imgFiles[i].name
				fileFormat = filePath.split('.')[1].toLowerCase()
				src = window.URL.createObjectURL(imgFiles[i])
				let isActive = i === 0 ? " active" : "";  // 加入active才不會bootstrap一次顯示多張圖導致重疊
				let element = `<div class="carousel-item${isActive}"><img class="img-thumbnail card-img-top article d-block w-100" id="carouPic${i}" src="${src}"></div>`

				$(".carousel-inner").append(element);
			}
			
		});
		
		
	}else{
		for(let i = 1; i <= 5; i++){
			$(`#carouPic${i}`).attr("src",`/TirTirCat/carousel?picOrder=${i}`);
		}
		

	fetch("/TirTirCat/article")
		.then(response => response.json())
		.then(data => {
			if (data === null) {
				alert("這篇文章不存在，即將送您回交流天地");
				setTimeout(function() {
					$(window).attr('location', '/TirTirCat/forum.html');
				}, 2000);
				

			} else {
				$(".author").text(data[0].u_name);    // 登入的人  暫定抓發文人
				$(".author").attr("uid", data[0].uid);  // 登入的人 暫定抓發文人
				$("time.post-time").text(data[0].art_po_time);
				$("button.blog-button").attr("art_id", data[0].art_id);
				$("#article-title").text(data[0].art_title);
				$("#article-content").text(data[0].art_content);
				$("i.fa-heart").text(data[0].art_like);
				$("#ownerAvatar").attr("src", "/TirTirCat/avatar?uid=" + data[0].uid);
				urlArt_id = data[0].art_id;
				// Share button
				$("#share-tooltip").on("click", () => {

					let url = "localhost:8081/TirTirCat/articleXxx?art_id=" + urlArt_id;
					navigator.clipboard.writeText(url);
					alert(`文章網址${url}已複製成功`);
				})
				// Share button end
				
				
					// carousel控tag
	fetch("/TirTirCat/artDnone")
		.then(response => response.json())
		.then(data => {
			for (let i = 5; i > data; i--) {
				$(`#carouPic${i}`).parent('div').remove();
			}
		});
	let comTotal = 0;
	const comReplyWrapper = null;
	fetch("/TirTirCat/comment").then(response => response.json()).
		then(data => {

			for (let i = 0; i < data.length; i++) {
				let dataId = data[i].com_id;
				comTotal = data.length;
				const responseItem = `
					<div class="card w-100">
								<div class="card-body">
									<div class="card-title d-flex">
										<ul class="list-group list-group-horizontal d-flex justify-content-start">
											<li class="list-group-item border-0">
												<img alt="Avatar" class="avatar rounded-circle img-fluid col" src="/TirTirCat/avatar?uid=${data[i].com_user_id}">
											</li>
											<li class="list-group-item border-0 com_username">
													${data[i].user.u_name}
											</li>
											<li class="list-group-item com_time border-0">
													${data[i].com_date_time}
											</li>
											<li class="list-group-item  border-0">
													${data[i].com_content}
											</li>
										</ul>
									</div>
									<div class="comment-report position-absolute top-0 end-0 me-1">
										<i type="button" class="fa-solid fa-flag com-report"
											data-bs-toggle="modal" data-bs-target="#staticBackdrop"></i>
									</div>
									<div>
										<i type="button" class="fa-solid fa-reply com-reply-btn${dataId} fa-rotate-180 ms-1"></i>
									</div>
									<div class="com-reply-wrapper${dataId} d-none">
							</div>
    `;

				$("#com_wrapper").append(responseItem);

				// 擴充: 回覆的回覆
				$(`.com-reply-btn${dataId}`).on("click", function(e) {

					$(`div.com-reply-wrapper${dataId}`).toggleClass("d-none");
					// 要發送的 reply_com_id
					let reply_com_id = dataId;
					console.log("reply_com_id=" + reply_com_id)
					let url = "/TirTirCat/reply?reply_com_id=" + reply_com_id;
					const comReplyWrapper = $(`.com-reply-wrapper${dataId}`);
					comReplyWrapper.empty();


					//  添加reply的留言區塊
					const replybutt = `
							<form action="#" method="post">
									<textarea class="card-text w-100" placeholder="留言"
										></textarea>
									<div class="post-button-list2">
										<button class="card-link btn btn-primary post-button"
											type="submit">送出</button>
									</div>
								</form>
								`;


					let that = $(this).closest("div.card-body").find("div").last();
					if (that.find("button").length == 0) {
						console.log(that.find("div"))
						that.after(replybutt);
						that.find("button").attr("reply_com_id", reply_com_id);
					} else {
						$(this).closest("div.card-body").find("form").remove();
					}
					//  添加reply的留言區塊 end

					fetch(url)
						.then(response => response.json())
						.then(data => {
							for (let k = 0; k < data.length; k++) {

								const replyItem = `
    <div class="row card mb-3 ms-5">
      	<div class="col g-0 position-relative post-reply">
	        	<ul class="list-group list-group-horizontal border-0" style="vertical-align: center;">
			           <li class="list-group-item border-0">
			           			  <img alt="Avatar" class="avatar rounded-circle img-fluid col" alt="./images/Avatar.png" src="/TirTirCat/avatar?uid=${data[k].reply_user_id}">
			           </li>
			          <li class="list-group-item reply_username border-0">${data[k].user.u_name}</li>
			          <li class="list-group-item reply_time border-0">${data[k].reply_date_time}</li>
				       <li class="list-group-item reply border-0">${data[k].reply_content}</p>
	        	</ul>
		        <div>
		          <i type="button" class="fa-solid fa-flag reply-report position-absolute end-0 top-0 mt-1 me-1" data-bs-toggle="modal" data-bs-target="#staticBackdrop" com_id="${data[k].reply_id}"></i>
		        </div>
      	</div>
    </div>
  `;
								comReplyWrapper.append(replyItem);

							} //小迴圈 end
						});
				});  // 擴充 end						
			}  // 大迴圈 end
		});  // fetch end
				
			}
		});

		
		// 編輯文章
let content_value = "";
let title_value = "";
$("#article-edit").on("click", function() {
	let article_content = $("#article-content").text();
	let article_title = $("#article-title").text();
	let content_html = '<input type="text" class="content_update" placeholder="輸入欲更新的內容" value="' + article_content + '">';
	let title_html = '<input type="text" class="title_update" placeholder="輸入欲更新的標題" value="' + article_title + '">';
	if ($("input.content_update").length == 0) {
		$("#article-title").after(title_html);
		$("#article-content").after(content_html);
		$("#article-content, #article-title").toggleClass("d-none");
		$(this).text("送出");

	} else {
		content_value = $("input.content_update").val().trim();
		title_value = $("input.title_update").val().trim();
		if (content_value == "" || title_value == "") {
			alert("內容不可空白");
		}else if(content_value == article_content && title_value == article_title){
			alert("並未作任何修改");
		} else {

			$.ajax({
				url: "/TirTirCat/articleUpdate",
				type: "POST",
				data: {
					art_id: urlArt_id,
					art_title: title_value,
					art_content: content_value
				},
				dataType: "json",
				success: function(data) {
					if (data === 1) {
						// 将页面元素内容更新为 content_value 和 title_value
						$("#article-content").text(content_value);
						$("#article-title").text(title_value);
						alert("更新成功")
						$("#article-content,#article-title").toggleClass("d-none");
						$("#article-edit").text("編輯");
						$("input.content_update, input.title_update").remove();

					} else {
						alert("更新失敗")
					}
				}
			});
		}
	}
});
		} // if 
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

