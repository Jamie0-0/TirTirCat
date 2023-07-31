<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="product.model.*"%>

<%
ProductVO proVO = (ProductVO) request.getAttribute("proVO");
%>


<!DOCTYPE html>
<html lang="en" dir="ltr">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Furrever admin is super flexible, powerful, clean &amp; modern responsive bootstrap 5 admin template with unlimited possibilities.">
<meta name="keywords"
	content="admin template, Furrever admin template, dashboard template, flat admin template, responsive admin template, web app">
<meta name="author" content="Tha102G3">
<link rel="icon"
	href="<%=request.getContextPath()%>/backEnd/assets/images/favicon.png"
	type="image/x-icon">
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/backEnd/assets/images/favicon.png"
	type="image/x-icon">
<title>Frever - Update Product</title>
<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap" -->
<!-- 	rel="stylesheet"> -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backEnd/assets/css/linearicon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/themify.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/feather-icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/remixicon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/select2.min.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/scrollbar.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/animate.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/chartist.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/bootstrap-tagsinput.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/style.css">



<style>
#preView, #send {
	display: inline-block;
}
</style>
</head>

<body>
	<div class="tap-top">
		<span class="lnr lnr-chevron-up"></span>
	</div>

	<div class="page-wrapper compact-wrapper" id="pageWrapper">
		<div class="page-header">
			<div class="header-wrapper m-0">
				<div class="header-logo-wrapper p-0">
					<div class="logo-wrapper">
						<a href="<%=request.getContextPath()%>/backEnd/back_index.jsp">
							<img class="img-fluid main-logo"
							src="<%=request.getContextPath()%>/backEnd/assets/images/logo/1.png"
							alt="logo"> <img class="img-fluid white-logo"
							src="<%=request.getContextPath()%>/backEnd/assets/images/logo/1-white.png"
							alt="logo">
						</a>
					</div>
					<div class="toggle-sidebar">
						<i class="status_toggle middle sidebar-toggle"
							data-feather="align-center"></i> <a
							href="<%=request.getContextPath()%>/backEnd/back_index.jsp">
							<img
							src="<%=request.getContextPath()%>/backEnd/assets/images/logo/1.png"
							class="img-fluid" alt="">
						</a>
					</div>
				</div>

				<div class="nav-right col-6 pull-right right-header p-0">
					<ul class="nav-menus">
						<li><span class="header-search"> <i
								class="ri-search-line"></i>
						</span></li>
						
						<li class="profile-nav onhover-dropdown pe-0 me-0">
							<div class="media profile-media">
								<img class="user-profile rounded-circle"
									src="<%=request.getContextPath()%>/backEnd/assets/images/users/4.jpg"
									alt="">
								<div class="user-name-hide media-body">
									<span>埃梅·沃爾特</span>
									<p class="mb-0 font-roboto">
										行政<i class="middle ri-arrow-down-s-line"></i>
									</p>
								</div>
							</div>
							<ul class="profile-dropdown onhover-show-div">
								<li><a
									href="<%=request.getContextPath()%>/backEnd/order-list.html">
										<i data-feather="archive"></i> <span>訂單</span>
								</a></li>
								<li><a
									href="<%=request.getContextPath()%>/backEnd/profile-setting.html">
										<i data-feather="settings"></i> <span>設置</span>
								</a></li>
								<li><a data-bs-toggle="modal"
									data-bs-target="#staticBackdrop" href="javascript:void(0)">
										<i data-feather="log-out"></i> <span>登出</span>
								</a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>

		<div class="page-body-wrapper">
			<div class="sidebar-wrapper">
				<div id="sidebarEffect"></div>
				<div>
					<div class="logo-wrapper logo-wrapper-center">
						<a href="<%=request.getContextPath()%>/backEnd/back_index.jsp"
							data-bs-original-title="" title=""> <img
							class="img-fluid for-white"
							src="<%=request.getContextPath()%>/backEnd/assets/images/logo/full-white.png"
							alt="logo">
						</a>
						<div class="back-btn">
							<i class="fa fa-angle-left"></i>
						</div>
					</div>
					<div class="logo-icon-wrapper">
						<a href="<%=request.getContextPath()%>/backEnd/back_index.jsp">
							<img class="img-fluid main-logo main-white"
							src="<%=request.getContextPath()%>/backEnd/assets/images/logo/logo.png"
							alt="logo"> <img class="img-fluid main-logo main-dark"
							src="<%=request.getContextPath()%>/backEnd/assets/images/logo/logo-white.png"
							alt="logo">
						</a>
					</div>
					<nav class="sidebar-main">
						<div class="left-arrow" id="left-arrow">
							<i data-feather="arrow-left"></i>
						</div>

						<div id="sidebar-menu">
							<ul class="sidebar-links" id="simple-bar">
								<li class="back-btn"></li>

								<li class="sidebar-list"><a
									class="sidebar-link sidebar-title link-nav"
									href="<%=request.getContextPath()%>/backEnd/back_index.jsp">
										<i class="ri-home-line"></i> <span>主頁</span>
								</a></li>

								<li class="sidebar-list"><a
									class="linear-icon-link sidebar-link sidebar-title"
									href="javascript:void(0)"> <i class="ri-store-3-line"></i>
										<span>產品</span>
								</a>
									<ul class="sidebar-submenu">
										<li><a
											href="<%=request.getContextPath()%>/backEnd/products.jsp">所有產品</a></li>

										<li><a
											href="<%=request.getContextPath()%>/backEnd/add-new-product.jsp">添加產品</a></li>
									</ul></li>

								<li class="sidebar-list"><a
									class="sidebar-link sidebar-title" href="javascript:void(0)">
										<i class="ri-archive-line"></i> <span>訂單</span>
								</a>
									<ul class="sidebar-submenu">
										<li><a
											href="<%=request.getContextPath()%>/backEnd/order-list.html">訂單列表</a></li>
										<li><a
											href="<%=request.getContextPath()%>/backEnd/order-detail.html">訂單明細</a></li>
										<li><a
											href="<%=request.getContextPath()%>/backEnd/order-list2.html">團購訂單</a></li>
										<li><a
											href="<%=request.getContextPath()%>/backEnd/order-list3.html">團購訂單明細</a></li>
									</ul></li>

								<li class="sidebar-list"><a
									class="linear-icon-link sidebar-link sidebar-title"
									href="javascript:void(0)"> <i class="ri-settings-line"></i>
										<span>編輯</span>
								</a>
									<ul class="sidebar-submenu">
										<li><a
											href="<%=request.getContextPath()%>/backEnd/profile-setting.html">
												個人設定</a></li>
									</ul></li>

								<li class="sidebar-list"><a
									class="sidebar-link sidebar-title link-nav"
									href="<%=request.getContextPath()%>/backEnd/chat.jsp"> <i
										class="fas fa-comments"></i> <span>聊天室</span>
								</a></li>
							</ul>
						</div>

						<div class="right-arrow" id="right-arrow">
							<i data-feather="arrow-right"></i>
						</div>
					</nav>
				</div>
			</div>

			<div class="page-body">
				<div class="container-fluid">
					<div class="row">
						<div class="col-12">
							<div class="row">
								<div class="col-sm-8 m-auto">
									<div class="card">
										<div class="card-body">
											<div class="card-header-2">
												<h5>產品資訊</h5>
												<%-- 錯誤表列 --%>
												<c:if test="${not empty errorMsgs}">
													<font style="color: red">請修正以下錯誤:</font>
													<ul>
														<c:forEach var="message" items="${errorMsgs}">
															<li style="color: red">${message}</li>
														</c:forEach>
													</ul>
												</c:if>
											</div>

											<form METHOD="post" ACTION="<%=request.getContextPath()%>/pro.do" name="form1"
												class="theme-form theme-form-2 mega-form"
												enctype="multipart/form-data">
												<!-- 								 enctype="multipart/form-data"	  autocomplete="off" -->
												<div class="mb-4 row align-items-center">
													<label class="form-label-title col-sm-3 mb-0">商品類別</label>
													<div class="col-sm-9">
														<jsp:useBean id="pMapSvc" scope="page"
															class="pMapping.model.PMappingService" />
														<select size="1" name="p_class" id="p_class">
															<c:forEach var="pMappingVO" items="${pMapSvc.getAll()}">
																<option value="${pMappingVO.pm_id}"
																	${(proVO.p_class==pMappingVO.pm_id)?'selected':'' }>${pMappingVO.pm_name}</option>
															</c:forEach>
														</select>
													</div>
												</div>

												<div class="mb-4 row align-items-center">
													<label class="form-label-title col-sm-3 mb-0">商品名稱</label>
													<div class="col-sm-9">
														<input class="form-control" type="text" id="p_name"
															name="p_name" value="<%=proVO.getP_name()%>">
													</div>
												</div>

												<div class="mb-4 row align-items-center">
													<label class="form-label-title col-sm-3 mb-0">商品價格</label>
													<div class="col-sm-9">
														<input class="form-control" type="text" id="p_price"
															name="p_price" value="<%=proVO.getP_price()%>">
													</div>
												</div>

												<div class="mb-4 row align-items-center">
													<label class="form-label-title col-sm-3 mb-0">商品數量</label>
													<div class="col-sm-9">
														<input class="form-control" type="text" id="p_stock"
															name="p_stock" value="<%=proVO.getP_stock()%>">
													</div>
												</div>

												<div class="mb-4 row align-items-center">
													<label class="form-label-title col-sm-3 mb-0">貓狗商品</label>
													<div class="col-sm-9">
														<jsp:useBean id="pTypeSvc" scope="page"
															class="pType.model.PTypeService" />
														<select size="1" name="p_type">
															<c:forEach var="pTypeVO" items="${pTypeSvc.getAll()}">
																<option value="${pTypeVO.pt_id}"
																	${(proVO.p_type==pTypeVO.pt_id)?'selected':'' }>${pTypeVO.pt_name}</option>
															</c:forEach>
														</select>
													</div>
												</div>

												<div class="mb-4 row align-items-center">
													<label class="form-label-title col-sm-3 mb-0">商品狀態</label>
													<div class="col-sm-9">
														<jsp:useBean id="pStatusSvc" scope="page"
															class="pStatus.model.PStatusService" />
														<select size="1" name="p_status">
															<c:forEach var="pStatusVO" items="${pStatusSvc.getAll()}">
																<option value="${pStatusVO.ps_id}"
																	${(proVO.p_status==pStatusVO.ps_id)?'selected':'' }>${pStatusVO.ps_name}</option>
															</c:forEach>
														</select>
													</div>
												</div>


												<div class="card">
													<div class="card-body">
														<div class="row">
															<div class="col-12">
																<label class="form-label-title col-sm-3 mb-0">商品描述</label>
																<div class="col-sm-9">
																	<div id="editor"><%=proVO.getP_des()%></div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<input type="hidden" name="p_des" id="p_des_hidden">
												<input type="hidden" name="p_id" value="<%=proVO.getP_id()%>"> 
												<input type="hidden" name="action" value="update">


												<div class="col-sm-9">
													<input class="form-control form-choose" type="file"
														id="p_pic_one" name="p_pic_one"> 
													<label class="form-label-title col-sm-3 mb-0">圖1</label>
													<div>
														<img id="p_pic_one_preview_1"
															src="data:image/jpeg;base64, <%=request.getAttribute("p1")%>"
															width="200" height="200" alt="產品圖片">
													</div>
												</div>

												<div class="col-sm-9">
													<input class="form-control form-choose" type="file"
														id="p_pic_two" name="p_pic_two"> 
													<label class="form-label-title col-sm-3 mb-0">圖2</label>
													<div>
														<img id="p_pic_one_preview_2"
															src="data:image/jpeg;base64, <%=request.getAttribute("p2")%>"
															width="200" height="200" alt="產品圖片">
													</div>
												</div>

												<div class="col-sm-9">
													<input class="form-control form-choose" type="file"
														id="p_pic_three" name="p_pic_three"> 
													<label class="form-label-title col-sm-3 mb-0">圖3</label>
													<div>
														<img id="p_pic_one_preview_3"
															src="data:image/jpeg;base64, <%=request.getAttribute("p3")%>"
															width="200" height="200" alt="產品圖片">
													</div>
												</div>

												<div class="col-sm-9">
													<input class="form-control form-choose" type="file"
														id="p_pic_four" name="p_pic_four"> 
													<label class="form-label-title col-sm-3 mb-0">圖4</label>
													<div>
														<img id="p_pic_one_preview_4"
															src="data:image/jpeg;base64, <%=request.getAttribute("p4")%>"
															width="200" height="200" alt="產品圖片">
													</div>
												</div>
											</form>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


				<div style="display: flex; justify-content: center; width: 900px;">
					<button class="btn btn--yes btn-primary" id="sendInfo" 
						onclick="handleButtonClick()">送出修改</button>
				</div>



				<div class="container-fluid">
					<footer class="footer">
						<div class="row">
							<div class="col-md-12 footer-copyright text-center">
								<p class="mb-0">Copyright 2022 © FURREVER theme by THA102G3</p>
							</div>
						</div>
					</footer>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
		data-bs-keyboard="false" tabindex="-1"
		aria-labelledby="staticBackdropLabel" aria-hidden="true">
		<div class="modal-dialog  modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<h5 class="modal-title" id="staticBackdropLabel">登出</h5>
					<p>是否登出?</p>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
					<div class="button-box">
						<button type="button" class="btn btn--no" data-bs-dismiss="modal">No</button>
						<button type="button" class="btn  btn--yes btn-primary">Yes</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/jquery-3.6.0.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/scrollbar/simplebar.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/scrollbar/custom.js"></script>
	<script src="<%=request.getContextPath()%>/backEnd/assets/js/config.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/sidebar-menu.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/notify/bootstrap-notify.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/sidebareffect.js"></script>
	<script src="<%=request.getContextPath()%>/backEnd/assets/js/script.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/dropzone/dropzone.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/dropzone/dropzone-script.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/select2.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/select2-custom.js"></script>

	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/ckeditor.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/ckeditor-custom.js"></script>

	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/bootstrap/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/icons/feather-icon/feather.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/icons/feather-icon/feather-icon.js"></script>

	<script>
		function handleButtonClick() {
			const editorElement = document
					.querySelector(".ck-editor__editable");
			const pContent = editorElement.querySelector("p").innerText;
			const hiddenInput = document.getElementById("p_des_hidden");
			hiddenInput.value = pContent;

			const selectedValue = document.getElementById("p_class").value;
			console.log(selectedValue);
		}

		document.getElementById("p_pic_one").addEventListener(
				"change",
				function(event) {
					var input = event.target;
					var preview = document
							.getElementById("p_pic_one_preview_1");
					var file = input.files[0];
					var reader = new FileReader();

					reader.onload = function(e) {
						preview.setAttribute("src", e.target.result);
					};

					if (file) {
						reader.readAsDataURL(file);
					}
				});

		document.getElementById("p_pic_two").addEventListener(
				"change",
				function(event) {
					var input = event.target;
					var preview = document
							.getElementById("p_pic_one_preview_2");
					var file = input.files[0];
					var reader = new FileReader();

					reader.onload = function(e) {
						preview.setAttribute("src", e.target.result);
					};

					if (file) {
						reader.readAsDataURL(file);
					}
				});

		document.getElementById("p_pic_three").addEventListener(
				"change",
				function(event) {
					var input = event.target;
					var preview = document
							.getElementById("p_pic_one_preview_3");
					var file = input.files[0];
					var reader = new FileReader();

					reader.onload = function(e) {
						preview.setAttribute("src", e.target.result);
					};

					if (file) {
						reader.readAsDataURL(file);
					}
				});

		document.getElementById("p_pic_four").addEventListener(
				"change",
				function(event) {
					var input = event.target;
					var preview = document
							.getElementById("p_pic_one_preview_4");
					var file = input.files[0];
					var reader = new FileReader();

					reader.onload = function(e) {
						preview.setAttribute("src", e.target.result);
					};

					if (file) {
						reader.readAsDataURL(file);
					}
				});
	</script>
	<script>
		// 在按鈕被點擊時觸發 submitForm 函數
		document.getElementById("sendInfo").addEventListener("click",
				submitForm);

		function submitForm() {
			// 取得 form 表單元素
			const form = document.querySelector("form[name='form1']");

			// 執行 form 表單的提交動作
			form.submit();
		}
	</script>
</body>

</html>