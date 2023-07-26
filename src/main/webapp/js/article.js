let content_html = '<textarea class="content_update" id="summernote1"></textarea>';
let title_html = '<input type="text" class="title_post w-50" placeholder="輸入標題">';
let imgFiles = null;
let imgFilesLength = 0;
let formData = new FormData();
let urlArt_id = 0;

function fetchComment() {
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
											<li class="list-group-item border-0">
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

function addUpload() {
	$("#upload_img_label").removeClass(" d-none");

	$('#upload_img').on('change', function() {
		imgFiles = $(this)[0].files;

		imgFilesLength = imgFiles.length
		console.log("imgFilesLength=" + imgFilesLength)
		$(".carousel-item").remove();
		if (imgFiles.length > 5) {
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
	return imgFiles;
}

function buildArticle() {
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
				$("#article-content").append(data[0].art_content);
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
				fetchComment();
			}
		});
}

// init

$(function() {

	$("#summer2").on("click", function() {
		var markupStr = $('#summernote2').val();
		$.ajax({
			url: "/TirTirCat/commentInsert",
			type: "POST",
			data: {
				com_art_id: urlArt_id
				, com_user_id: "1"  // 屆時填入發文者id
				, com_content: markupStr
			},
			dataType: "json",
			success: function(data) {
				console.log(data)
				if (data === 1) {
					fetchComment();
					$('.note-editable').empty();
				} else {
					alert("新增留言失敗");
				}

			}
		});
	});

	if (window.location.search.includes("fromRule=true")) {
		$(".from-rule").addClass(" d-none");
		$(".carousel-item").remove();
		$("#article-content").after(content_html);
		$("#article-title").after(title_html);

		imgFiles = addUpload();

		$("#upload_img_label").after('<button type="button" class="btn btn-primary position-absolute end-0 top-0" id="post-new">送出</button>')
		$('#summernote1').summernote({
			placeholder: '請輸入文章內容',
			tabsize: 2,
			height: 120,
			toolbar: [
				['style', ['style']],
				['font', ['bold', 'underline', 'clear']],
				['color', ['color']],
				['para', ['ul', 'ol', 'paragraph']],
				['table', ['table']],
				['insert', ['link', 'picture', 'video']],
				['view', ['fullscreen', 'codeview', 'help']]
			]
		});


		$("#post-new").on("click", function() {
	
			content_value = $('#summernote1').val();
			title_value = $(".title_post").val();
			formData = new FormData();  // 洗掉之前的formData; 因為是全域變數 不new會越來越多
			formData.append('art_user_id', "1"); // 暫定1 應為登入者
			formData.append('art_title', title_value);
			formData.append('art_content', content_value);

			for (let i = 0; i < imgFilesLength; i++) {
				formData.append('image', imgFiles[i]);
			}
	
			$.ajax({
				url: "/TirTirCat/ArtInsert",
				type: "POST",
				data: formData,
				processData: false,
				contentType: false,
				success: function(data) {

					if (data === 1) {
						alert("發文成功")
						fetch("/TirTirCat/refresh")
							.then(response => response.json())
							.then(data => {
								console.log("refresh=" + data);
							});
						$(window).attr("location", "/TirTirCat/forum.html")

					} else {
						alert("更新失敗")
					}
				},
			});
		});


	} else {
		for (let i = 1; i <= 5; i++) {
			$(`#carouPic${i}`).attr("src", `/TirTirCat/carousel?picOrder=${i}`);
		}

		buildArticle();

		// 編輯文章
		let content_value = "";
		let title_value = "";
		$("#article-edit").on("click", function() {

			let article_content = $("#article-content").find("*");
			let article_title = $("#article-title").text();
			content_html = `<textarea class="content_update" id="summernote1" value="${article_content}"></textarea>`;
			title_html = '<input type="text" class="title_update w-50" placeholder="輸入標題" value="' + article_title + '">';

			imgFiles = addUpload();

			if ($("textarea.content_update").length == 0) {
				$("#article-title").after(title_html);
				$("#article-content").after(content_html);
				$("#article-content, #article-title").toggleClass("d-none");
				$(".forSummer2").toggleClass(" d-none");
				$(this).text("送出");

				$('#summernote1').summernote({
					placeholder: '請輸入文章內容',
					tabsize: 2,
					height: 120,
					toolbar: [
						['style', ['style']],
						['font', ['bold', 'underline', 'clear']],
						['color', ['color']],
						['para', ['ul', 'ol', 'paragraph']],
						['table', ['table']],
						['insert', ['link', 'picture', 'video']],
						['view', ['fullscreen', 'codeview', 'help']]
					]
				});

			} else {

				title_value = $("input.title_update").val().trim();
				if ($(".note-editable").find("*").length == 0 || title_value == "") {
					alert("內容不可空白");
				} else if ($(".note-editable").find("*") == article_content && title_value == article_title && imgFilesLength == 0) {
					alert("並未作任何修改");
				} else {
					content_value = $('#summernote1').val();
					formData = new FormData();  // 洗掉之前的formData; 因為是全域變數 不new會越來越多
					formData.append('art_id', urlArt_id);
					formData.append('art_title', title_value);
					formData.append('art_content', content_value);

					for (let i = 0; i < imgFilesLength; i++) {
						formData.append('image', imgFiles[i]);
					}

					$.ajax({
						url: "/TirTirCat/articleUpdate",
						type: "POST",
						data: formData,
						processData: false,
						contentType: false,
						dataType: "json",
						success: function(data) {
							if (data === 1) {

								alert("更新成功")
								$("#article-content,#article-title").toggleClass(" d-none");
								$("#summernote1").remove();
								$("#article-content").empty();
								$("#article-edit").text("編輯");
								$("input.title_update").remove();
								$(".note-editor").eq(0).remove();
								$("#upload_img_label").addClass(" d-none")
								$(".forSummer2").toggleClass(" d-none");
								buildArticle();

							} else {
								alert("更新失敗")

								for (let i = 1; i <= 5; i++) {
									$(`#carouPic${i}`).attr("src", `/TirTirCat/carousel?picOrder=${i}`);
								}
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

