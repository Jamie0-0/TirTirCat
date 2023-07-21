<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="product.model.*"%>

<%
ProductVO proVO = (ProductVO) request.getAttribute("proVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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
<style>
#preView, #send, #bSpace {
	display: inline-block;
}

#bSpace {
	width: 130px;
}
</style>
</head>

<body>


	<div class="page-wrapper compact-wrapper" id="pageWrapper">

		<div class="page-body-wrapper">
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

											<form METHOD="post" ACTION="pro.do" name="form1"
												class="theme-form theme-form-2 mega-form" enctype="multipart/form-data">

												<div class="mb-4 row align-items-center">
													<label class="form-label-title col-sm-3 mb-0">商品類別</label>
													<div class="col-sm-9">
														<jsp:useBean id="pMapSvc" scope="page" class="pMapping.model.PMappingService" />
														<select size="1" name="p_class">
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
														<input class="form-control" type="text" id="p_name" name="p_name" value="<%=proVO.getP_name()%>">
													</div>
												</div>

												<div class="mb-4 row align-items-center">
													<label class="form-label-title col-sm-3 mb-0">商品價格</label>
													<div class="col-sm-9">
														<input class="form-control" type="text" id="p_price" name="p_price" value="<%=proVO.getP_price()%>">
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
														<jsp:useBean id="pTypeSvc" scope="page" class="pType.model.PTypeService" />
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
														<jsp:useBean id="pStatusSvc" scope="page" class="pStatus.model.PStatusService" />
														<select size="1" name="p_status">
															<c:forEach var="pStatusVO" items="${pStatusSvc.getAll()}">
																<option value="${pStatusVO.ps_id}"
																	${(proVO.p_status==pStatusVO.ps_id)?'selected':'' }>${pStatusVO.ps_name}</option>
															</c:forEach>
														</select>
													</div>
												</div>

												<div class="mb-4 row align-items-center">
													<div class="col-sm-9">
														<input class="form-control form-choose" type="file" id="p_pic_one" name="p_pic_one"> <label
															class="form-label-title col-sm-3 mb-0">圖1</label>
														<div>
															<img id="p_pic_one_preview_1"
																src="data:image/jpeg;base64, <%=request.getAttribute("p1")%>"
																width="200" height="200" alt="產品圖片">
														</div>
													</div>

													<div class="col-sm-9">
														<input class="form-control form-choose" type="file" id="p_pic_two" name="p_pic_two"> <label
															class="form-label-title col-sm-3 mb-0">圖2</label>
														<div>
															<img id="p_pic_one_preview_2"
																src="data:image/jpeg;base64, <%=request.getAttribute("p2")%>"
																width="200" height="200" alt="產品圖片">
														</div>
													</div>

													<div class="col-sm-9">
														<input class="form-control form-choose" type="file" id="p_pic_three" name="p_pic_three"> <label
															class="form-label-title col-sm-3 mb-0">圖3</label>
														<div>
															<img id="p_pic_one_preview_3"
																src="data:image/jpeg;base64, <%=request.getAttribute("p3")%>"
																width="200" height="200" alt="產品圖片">
														</div>
													</div>

													<div class="col-sm-9">
														<input class="form-control form-choose" type="file" id="p_pic_four" name="p_pic_four"> <label
															class="form-label-title col-sm-3 mb-0">圖4</label>
														<div>
															<img id="p_pic_one_preview_4"
																src="data:image/jpeg;base64, <%=request.getAttribute("p4")%>"
																width="200" height="200" alt="產品圖片">
														</div>
													</div>
												</div>


												<div class="card">
													<div class="card-body">
														<div class="row">
															<div class="col-12">
																<div class="row">
																	<label class="form-label-title col-sm-3 mb-0">商品描述</label>
																	<div class="col-sm-9">
																		<div id="editor" name="p_des"><%=proVO.getP_des()%></div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div>
													<div class="button-space" id="bSpace"></div>
													<div class="button-space" id="bSpace"></div>
													<input type="hidden" name="action" value="update">
													<input type="hidden" name="p_id" value="<%=proVO.getP_id()%>">
													<button type="submit" class="btn  btn--yes btn-primary" id="send">送出修改</button>
													<div class="button-space" id="bSpace"></div>
												</div>
											</form>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
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
</body>

</html>