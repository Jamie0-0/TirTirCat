<%@page import="product.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<%
List<ProductVO> list = (List<ProductVO>) request.getAttribute("list");

if (list == null) {
		ProductJDBCDAO dao = new ProductJDBCDAO();
		list = dao.getAll();
		
	}
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">

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
<title>Furrever - 產品</title>

<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/font-awesome.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/backEnd/assets/css/linearicon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/themify.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/feather-icon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/remixicon.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/datatables.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/scrollbar.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/animate.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/vendors/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/backEnd/assets/css/style.css">
<style>
.table-container {
	width: 100%;
	height: 350px;
	margin: 0 auto;
	overflow-x: auto;
}

table {
	border-collapse: collapse;
	width: 1000px;
	background-color: #f5f5f5;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

th, td {
	border: 1px solid #ccc;
	padding: 8px;
	font-size: 14px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
}

td {
	background-color: #fff;
}

tr:hover {
	background-color: #f9f9f9;
	transition: background-color 0.3s ease;
}

.custom-select {
	border: none;
	outline: none;
	background-color: transparent;
	font-size: 14px;
}

.header-search {
	border: 5px solid black;
}

.product-search {
	width: 100px;
}

.search-button {
	border: none;
	background-color: lightgreen;
	border-radius: 50%;
	padding: 5px;
	cursor: pointer;
	width: 40px;
	height: 40px;
}

.search-button:active {
	background-color: lightyellow;
}

.product-search {
	border: none;
	border-radius: 20px;
	outline: none;
	padding: 10px;
	font-size: 16px;
	background-color: #f8f8f8;
	transition: background-color 0.3s ease-in-out;
}

.product-search:focus {
	background-color: lightyellow;
}

.input-width {
	width: 80px;
	text-align: center;
	border: none;
}

#searchBtn {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	border: none;
	background-color: #ccc;
	color: #fff;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
}

#sForm {
	display: flex;
	align-items: center;
}

#iForm1, #iForm2, #searchBtn {
	margin: 5px;
}

#iForm1 {
	width: 100px;
	height: 40px;
	border: 2px solid #ccc;
	border-radius: 5px;
	padding: 5px;
	font-size: 16px;
	/* 添加其他樣式規則，例如文字顏色、背景色等 */
}

#searchBtn {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	border: none;
	background-color: #ccc;
	color: #fff;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
}

select {
	border: none; /* 移除邊框 */
	background-color: transparent; /* 背景透明，不顯示白色背景 */
	appearance: none; /* 移除系統默認樣式，例如下拉箭頭 */
	-webkit-appearance: none; /* 適用於某些瀏覽器的前綴 */
	-moz-appearance: none; /* 適用於某些瀏覽器的前綴 */
}

#p_status {
	width: 150px;
	height: 40px;
	border: 2px solid #ccc;
	border-radius: 5px;
	padding: 5px;
	font-size: 16px;
	/* 添加其他樣式規則，例如文字顏色、背景色等 */
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
											href="<%=request.getContextPath()%>/backEnd/products.jsp">所有產品</a>
										</li>

										<li><a
											href="<%=request.getContextPath()%>/backEnd/add-new-product.jsp">添加產品</a>
										</li>
									</ul></li>

								<li class="sidebar-list"><a
									class="sidebar-link sidebar-title" href="javascript:void(0)">
										<i class="ri-archive-line"></i> <span>訂單</span>
								</a>
									<ul class="sidebar-submenu">
										<li><a
											href="<%=request.getContextPath()%>/backEnd/order-list.html">訂單列表</a>
										</li>
										<li><a
											href="<%=request.getContextPath()%>/backEnd/order-detail.html">訂單明細</a>
										</li>
										<li><a
											href="<%=request.getContextPath()%>/backEnd/order-list2.html">團購訂單</a>
										</li>
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
				<h2>${pathInfo}</h2>
				<div class="container-fluid">
					<div class="row">
						<div class="col-sm-12">
							<div class="card card-table">
								<div class="card-body">
									<div class="title-header option-title d-sm-flex d-block">
										<div>
											<h5>產品列表</h5>
											<div>
												<%-- 錯誤表列 --%>
												<c:if test="${not empty errorMsgs}">
													<ul>
														<c:forEach var="message" items="${errorMsgs}">
															<li style="color: red">${message}</li>
														</c:forEach>
													</ul>
												</c:if>

												<FORM id="sForm" METHOD="post"
													ACTION="<%=request.getContextPath()%>/pro.do">
													<b style="font-size: 20px;">輸入商品編號:</b> <input id="iForm1"
														type="text" name="p_id"
														style="width: 70px; height: 35px; border: 2px solid #ccc; border-radius: 5px;"
														pattern="[0-9]+">

													<jsp:useBean id="pMapSvc" scope="page"
														class="pMapping.model.PMappingService" />
													<select id="p_class" size="1" name="p_class"
														style="width: 90px; height: 35px; border: 2px solid #ccc; border-radius: 5px; padding: 5px; font-size: 16px; margin-right: 6px;">
														<option value="0">輸入類別</option>
														<c:forEach var="pMappingVO" items="${pMapSvc.getAll()}">
															<option value="${pMappingVO.pm_id}"
																${productVO.p_class==pMappingVO.pm_id ? 'selected' : ''}>${pMappingVO.pm_name}</option>
														</c:forEach>
													</select>

													<jsp:useBean id="pStatusSvc" scope="page"
														class="pStatus.model.PStatusService" />
													<select id="p_status" size="1" name="p_status"
														style="width: 90px; height: 35px; border: 2px solid #ccc; border-radius: 5px; padding: 5px; font-size: 16px;">
														<option value="0">輸入狀態</option>
														<c:forEach var="pStatusVO" items="${pStatusSvc.getAll()}">
															<option value="${pStatusVO.ps_id}"
																${productVO.p_status == pStatusVO.ps_id ? 'selected' : ''}>${pStatusVO.ps_name}</option>
														</c:forEach>
													</select> <input id="iForm2" type="hidden" name="action"
														value="getOneSearch">
													<button id="searchBtn" type="submit">
														<i class="fas fa-search"></i>
													</button>
												</FORM>
											</div>

										</div>
										<div class="right-options">
											<ul>
												<li><a class="btn btn-solid"
													href="<%=request.getContextPath()%>/backEnd/add-new-product.jsp">新增產品</a>
												</li>
											</ul>
										</div>
									</div>
									<div>
										<div class="table-container">
											<table>
												<tr>
													<th>商品編號</th>
													<th>商品類別</th>
													<th>商品名稱</th>
													<th>商品價格</th>
													<th>商品數量</th>
													<th>收藏數量</th>
													<th>貓狗商品</th>
													<th>商品狀態</th>
													<th>修改時間</th>
													<th>修改</th>
													<th>刪除</th>


												</tr>
												<%@ include file="page1.file"%>
												<c:forEach var="productVO" items="${list}"
													begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
													<tr>
														<td>${productVO.p_id}</td>

														<td><c:forEach var="pMappingVO1"
																items="${pMapSvc.getAll()}">
																<c:if test="${productVO.p_class == pMappingVO1.pm_id}">
														            ${pMappingVO1.pm_name}
														        </c:if>
															</c:forEach></td>

														<td>${productVO.p_name}</td>
														<td>${productVO.p_price}</td>
														<td>${productVO.p_stock}</td>
														<td>${productVO.p_count}</td>

														<jsp:useBean id="pTypeSvc" scope="page"
															class="pType.model.PTypeService" />
														<td><c:forEach var="pTypeVO"
																items="${pTypeSvc.getAll()}">
																<c:if test="${productVO.p_type == pTypeVO.pt_id}">
														            ${pTypeVO.pt_name}
														        </c:if>
															</c:forEach></td>

														<td><c:forEach var="pStatusVO"
																items="${pStatusSvc.getAll()}">
																<c:if test="${productVO.p_status == pStatusVO.ps_id}">
														            ${pStatusVO.ps_name}
														        </c:if>
															</c:forEach></td>
														<td>${productVO.p_upload_time}</td>
														<td>
															<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pro.do"
																style="margin-bottom: 0px;">
																<input type="hidden" name="action" value="getUpdate">
																<input type="hidden" name="p_id" value="${productVO.p_id}"> 
																<button type="submit" class="btn btn-primary">
																  <i class="fas fa-pencil-alt"></i>
																</button>

															</FORM>
														</td>
														<td>
															<FORM METHOD="post"
																ACTION="<%=request.getContextPath()%>/pro.do"
																style="margin-bottom: 0px;">
																<input type="hidden" name="action" value="delete">
																<input type="hidden" name="p_id" value="${productVO.p_id}"> 
																<button type="submit" class="btn btn-danger">
																  <i class="fas fa-trash"></i>
																</button>

															</FORM>
														</td>
													</tr>
												</c:forEach>
											</table>
											<%@ include file="page2.file"%>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
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

	<div class="modal fade theme-modal remove-coupon"
		id="exampleModalToggle" aria-hidden="true" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header d-block text-center">
					<h5 class="modal-title w-100" id="exampleModalLabel22">請確認 ?</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close">
						<i class="fas fa-times"></i>
					</button>
				</div>
				<div class="modal-body">
					<div class="remove-box">
						<p>The permission for the use/group, preview is inherited from
							the object, object will create a new permission for this object</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-animation btn-md fw-bold"
						data-bs-dismiss="modal"></button>
					<button type="button" class="btn btn-animation btn-md fw-bold"
						data-bs-target="#exampleModalToggle2" data-bs-toggle="modal"
						data-bs-dismiss="modal">是</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade theme-modal remove-coupon"
		id="exampleModalToggle2" aria-hidden="true" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title text-center" id="exampleModalLabel12">完成!</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close">
						<i class="fas fa-times"></i>
					</button>
				</div>
				<div class="modal-body">
					<div class="remove-box text-center">
						<div class="wrapper">
							<svg class="checkmark" xmlns="http://www.w3.org/2000/svg"
								viewBox="0 0 52 52">
                                <circle class="checkmark__circle"
									cx="26" cy="26" r="25" fill="none" />
                                <path class="checkmark__check"
									fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8" />
                            </svg>
						</div>
						<h4 class="text-content">已移除</h4>
					</div>
				</div>
				<div class="modal-footer">
					<button class="btn btn-primary" data-bs-toggle="modal"
						data-bs-dismiss="modal">Close</button>
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
		src="<%=request.getContextPath()%>/backEnd/assets/js/jquery.dataTables.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/custom-data-table.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/sidebareffect.js"></script>
	<script src="<%=request.getContextPath()%>/backEnd/assets/js/script.js"></script>
	<script>
		var errmsg = '';

		function validateNumberInput(input) {
			input.value = input.value.replace(/[^0-9]/g, '');
		}

		function validateInputValue(input) {
			var value = input.value.trim();
			var regex = /^[a-zA-Z0-9\u4E00-\u9FFF]+$/;

			if (value === '') {
				input.classList.add('error');
				alert('請填寫該欄位！');
				errmsg = '請填寫該欄位！';
			} else if (!regex.test(value)) {
				input.classList.add('error');
				alert('商品名稱禁止輸入特殊符號和全形字元！');
				errmsg = '商品名稱禁止輸入特殊符號和全形字元！';
			} else {
				input.classList.remove('error');
				errmsg = '';
				console.log("clean1");
			}
		}

		var times = 0;
		function modifyData() {
			var inputs = document.querySelectorAll('input.input-width');
			var regex = /^[a-zA-Z0-9\u4E00-\u9FFF]+$/;
			var hasError = false;
			var errmsg = '';

			inputs.forEach(function(input) {
				var value = input.value.trim();

				if (value === '') {
					input.classList.add('error');
					errmsg += '請填寫該欄位！\n';
					hasError = true;
				} else if (!regex.test(value)) {
					input.classList.add('error');
					errmsg += '商品名稱禁止輸入特殊符號和全形字元！\n';
					hasError = true;
				} else {
					input.classList.remove('error');
				}
			});

			if (hasError) {
				alert('請確認輸入資料是否完整');
			} else {

			}
		}
	</script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/bootstrap/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/icons/feather-icon/feather.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/backEnd/assets/js/icons/feather-icon/feather-icon.js"></script>


	<!-- <script src="assets/js/icons/feather-icon/feather-icon.js"></script> -->
	<!-- <script src="assets/js/customizer.js"></script> -->
	<!-- <script src="assets/js/notify/index.js"></script> -->
	<!-- <script src="assets/js/bootstrap/bootstrap.bundle.min.js"></script> -->
	<!-- <script src="assets/js/icons/feather-icon/feather.min.js"></script> -->
</body>

</html>