<%@page import="product.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%
	ProductJDBCDAO dao = new ProductJDBCDAO();
    List<ProductVO> list = dao.getAll();       // 此行的list變數(物件)將提供page1.file的第11行取得查詢到的總筆數，再由page1.file進行分頁的需要
    pageContext.setAttribute("list",list); // 將上一行的list變數(物件)存入當前頁面pageContext，再由底下的第83行由JSTL的forEach列印出結果
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
    <link rel="icon" href="assets/images/favicon.png" type="image/x-icon">
    <link rel="shortcut icon" href="assets/images/favicon.png" type="image/x-icon">
    <title>Furrever - 產品</title>

    <link
        href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
        rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/font-awesome.css">
    <link rel="stylesheet" href="assets/css/linearicon.css">
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/themify.css">
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/feather-icon.css">
    <link rel="stylesheet" type="text/css" href="assets/css/remixicon.css">
    <link rel="stylesheet" type="text/css" href="assets/css/datatables.css">
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/scrollbar.css">
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/animate.css">
    <link rel="stylesheet" type="text/css" href="assets/css/vendors/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="assets/css/style.css">
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

        th,
        td {
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
                        <a href="back_index.html">
                            <img class="img-fluid main-logo" src="assets/images/logo/1.png" alt="logo">
                            <img class="img-fluid white-logo" src="assets/images/logo/1-white.png" alt="logo">
                        </a>
                    </div>
                    <div class="toggle-sidebar">
                        <i class="status_toggle middle sidebar-toggle" data-feather="align-center"></i>
                        <a href="back_index.html">
                            <img src="assets/images/logo/1.png" class="img-fluid" alt="">
                        </a>
                    </div>
                </div>

                <div class="nav-right col-6 pull-right right-header p-0">
                    <ul class="nav-menus">
                        <li>
                            <span class="header-search">
                                <i class="ri-search-line"></i>
                            </span>
                        </li>
                        <li class="onhover-dropdown">
                            <div class="notification-box">
                                <i class="ri-notification-line"></i>
                                <span class="badge rounded-pill badge-theme">4</span>
                            </div>
                            <ul class="notification-dropdown onhover-show-div">
                                <li>
                                    <i class="ri-notification-line"></i>
                                    <h6 class="f-18 mb-0">通知</h6>
                                </li>
                                <li>
                                    <p>
                                        <i class="fa fa-circle me-2 font-primary"></i>交貨加工 <span class="pull-right">10
                                            min.</span>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <i class="fa fa-circle me-2 font-success"></i>訂單完成<span class="pull-right">1
                                            hr</span>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <i class="fa fa-circle me-2 font-info"></i>訂單生成<span class="pull-right">3
                                            hr</span>
                                    </p>
                                </li>
                                <li>
                                    <p>
                                        <i class="fa fa-circle me-2 font-danger"></i>交付完成<span class="pull-right">6
                                            hr</span>
                                    </p>
                                </li>
                                <li>

                                </li>
                            </ul>
                        </li>

                        <li class="profile-nav onhover-dropdown pe-0 me-0">
                            <div class="media profile-media">
                                <img class="user-profile rounded-circle" src="assets/images/users/4.jpg" alt="">
                                <div class="user-name-hide media-body">
                                    <span>埃梅·沃爾特</span>
                                    <p class="mb-0 font-roboto">行政<i class="middle ri-arrow-down-s-line"></i></p>
                                </div>
                            </div>
                            <ul class="profile-dropdown onhover-show-div">
                                <li>
                                    <a href="order-list.html">
                                        <i data-feather="archive"></i>
                                        <span>訂單</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="profile-setting.html">
                                        <i data-feather="settings"></i>
                                        <span>設置</span>
                                    </a>
                                </li>
                                <li>
                                    <a data-bs-toggle="modal" data-bs-target="#staticBackdrop"
                                        href="javascript:void(0)">
                                        <i data-feather="log-out"></i>
                                        <span>登出</span>
                                    </a>
                                </li>
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
                        <a href="back_index.html" data-bs-original-title="" title="">
                            <img class="img-fluid for-white" src="assets/images/logo/full-white.png" alt="logo">
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

                                <li class="sidebar-list">
                                    <a class="sidebar-link sidebar-title link-nav" href="back_index.html">
                                        <i class="ri-home-line"></i>
                                        <span>主頁</span>
                                    </a>
                                </li>

                                <li class="sidebar-list">
                                    <a class="linear-icon-link sidebar-link sidebar-title" href="javascript:void(0)">
                                        <i class="ri-store-3-line"></i>
                                        <span>產品</span>
                                    </a>
                                    <ul class="sidebar-submenu">
                                        <li>
                                            <a href="products.html">所有產品</a>
                                        </li>

                                        <li>
                                            <a href="add-new-product.html">添加產品</a>
                                        </li>

                                        <li>
                                            <a href="add-new-product2.html">添加團購</a>
                                        </li>
                                    </ul>
                                </li>

                                <li class="sidebar-list">
                                    <a class="sidebar-link sidebar-title" href="javascript:void(0)">
                                        <i class="ri-archive-line"></i>
                                        <span>訂單</span>
                                    </a>
                                    <ul class="sidebar-submenu">
                                        <li>
                                            <a href="order-list.html">訂單列表</a>
                                        </li>
                                        <li>
                                            <a href="order-detail.html">訂單明細</a>
                                        </li>
                                        <li>
                                            <a href="order-list2.html">團購訂單</a>
                                        </li>
                                    </ul>
                                </li>


                                <li class="sidebar-list">
                                    <a class="linear-icon-link sidebar-link sidebar-title" href="javascript:void(0)">
                                        <i class="ri-settings-line"></i>
                                        <span>編輯</span>
                                    </a>
                                    <ul class="sidebar-submenu">
                                        <li>
                                            <a href="profile-setting.html"> 個人設定</a>
                                        </li>
                                    </ul>
                                </li>

                                <li class="sidebar-list">
                                    <a class="sidebar-link sidebar-title link-nav" href="chat.jsp">
                                        <i class="fas fa-comments"></i>
                                        <span>聊天室</span>
                                    </a>
                                </li>
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
                        <div class="col-sm-12">
                            <div class="card card-table">
                                <div class="card-body">
                                    <div class="title-header option-title d-sm-flex d-block">
                                        <div>
                                            <h5>產品列表</h5>
                                            <input class="product-search" type="text" placeholder="產品編號:">
                                            <select class="product-search"">
                                                <option value=" option1">商品類別</option>
                                                <option value="option2">點心</option>
                                                <option value="option3">玩具</option>
                                            </select>
                                            <select class="product-search">
                                                <option value="option1">貓狗商品</option>
                                                <option value="option2">貓</option>
                                                <option value="option3">狗</option>
                                            </select>
                                            <select class="product-search">
                                                <option value="option2">團購</option>
                                                <option value="option3">單品</option>
                                            </select>
                                            <select class="product-search">
                                                <option value="option1">商品狀態</option>
                                                <option value="option2">上架</option>
                                                <option value="option3">下架</option>
                                            </select>
                                            <button class="search-button"><i class="fas fa-search"></i></button>
                                        </div>
                                        <div class="right-options">
                                            <ul>
                                                <li>
                                                    <a class="btn btn-solid" href="add-new-product.html">新增產品</a>
                                                </li>

                                                <li>
                                                    <button type="button" class="btn  btn--yes btn-primary" onclick="modifyData()">修改</button>
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
													<th>商品型態</th>
													<th>商品狀態</th>
													<th>上架時間</th>


												</tr>
												<%@ include file="page1.file"%>
												<c:forEach var="productVO" items="${list}"
													begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
													<tr>
														<td>${productVO.p_id}</td>
														<td>${productVO.p_type}</td>
														<td>${productVO.p_name}</td>
														<td>${productVO.p_price}</td>
														<td>${productVO.p_stock}</td>
														<td>${productVO.p_count}</td>
														<td>${productVO.p_class}</td>
														<td>${productVO.p_status}</td>
														<td>${productVO.p_status}</td>
														<td>${productVO.p_upload_time}</td>
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

    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
        aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog  modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <h5 class="modal-title" id="staticBackdropLabel">登出</h5>
                    <p>是否登出?</p>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    <div class="button-box">
                        <button type="button" class="btn btn--no" data-bs-dismiss="modal">No</button>
                        <button type="button" class="btn  btn--yes btn-primary">Yes</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade theme-modal remove-coupon" id="exampleModalToggle" aria-hidden="true" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header d-block text-center">
                    <h5 class="modal-title w-100" id="exampleModalLabel22">請確認 ?</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="remove-box">
                        <p>The permission for the use/group, preview is inherited from the object, object will create a
                            new permission for this object</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-animation btn-md fw-bold" data-bs-dismiss="modal">å¦</button>
                    <button type="button" class="btn btn-animation btn-md fw-bold" data-bs-target="#exampleModalToggle2"
                        data-bs-toggle="modal" data-bs-dismiss="modal">是</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal fade theme-modal remove-coupon" id="exampleModalToggle2" aria-hidden="true" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title text-center" id="exampleModalLabel12">完成!</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                        <i class="fas fa-times"></i>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="remove-box text-center">
                        <div class="wrapper">
                            <svg class="checkmark" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 52 52">
                                <circle class="checkmark__circle" cx="26" cy="26" r="25" fill="none" />
                                <path class="checkmark__check" fill="none" d="M14.1 27.2l7.1 7.2 16.7-16.8" />
                            </svg>
                        </div>
                        <h4 class="text-content">已移除</h4>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <script src="assets/js/jquery-3.6.0.min.js"></script>
    <script src="assets/js/scrollbar/simplebar.js"></script>
    <script src="assets/js/scrollbar/custom.js"></script>
    <script src="assets/js/config.js"></script>
    <script src="assets/js/sidebar-menu.js"></script>
    <script src="assets/js/notify/bootstrap-notify.min.js"></script>
    <script src="assets/js/jquery.dataTables.js"></script>
    <script src="assets/js/custom-data-table.js"></script>
    <script src="assets/js/sidebareffect.js"></script>
    <script src="assets/js/script.js"></script>
    <script>
        var errmsg = '';

        function validateNumberInput(input) {
            // åªæ¥åæ¸å­è¼¸å¥
            input.value = input.value.replace(/[^0-9]/g, '');
        }

        function validateInputValue(input) {
            var value = input.value.trim();
            var regex = /^[a-zA-Z0-9\u4E00-\u9FFF]+$/; // åªåè¨±è±æå­æ¯ãæ¸å­åä¸­æå­

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
            var regex = /^[a-zA-Z0-9\u4E00-\u9FFF]+$/; // è±æå­æ¯ãæ¸å­åä¸­æå­çæ­£åè¡¨éå¼
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
                // è¼¸å¥è³ææ­£ç¢ºï¼å·è¡ç¸æçæä½
                // ...
            }
            }



    </script>
    <script src="assets/js/bootstrap/bootstrap.bundle.min.js"></script>
    <script src="assets/js/icons/feather-icon/feather.min.js"></script>
    <script src="assets/js/icons/feather-icon/feather-icon.js"></script>


    <!-- <script src="assets/js/icons/feather-icon/feather-icon.js"></script> -->
    <!-- <script src="assets/js/customizer.js"></script> -->
    <!-- <script src="assets/js/notify/index.js"></script> -->
    <!-- <script src="assets/js/bootstrap/bootstrap.bundle.min.js"></script> -->
    <!-- <script src="assets/js/icons/feather-icon/feather.min.js"></script> -->
</body>

</html>