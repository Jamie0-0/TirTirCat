<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description"
	content="Furrever admin is super flexible, powerful, clean &amp; modern responsive bootstrap 5 admin template with unlimited possibilities.">
<meta name="keywords"
	content="admin template, Furrever admin template, dashboard template, flat admin template, responsive admin template, web app">
<meta name="author" content="Tha102G3">
<link rel="icon" href="backEnd/assets/images/favicon.png"
	type="image/x-icon">
<link rel="shortcut icon" href="backEnd/assets/images/favicon.png"
	type="image/x-icon">
<title>Furrever - Talks</title>
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="backEnd/assets/css/linearicon.css">
<link rel="stylesheet" type="text/css"
	href="backEnd/assets/css/remixicon.css">
<link rel="stylesheet" type="text/css"
	href="backEnd/assets/css/vendors/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="backEnd/assets/css/vendors/themify.css">
<link rel="stylesheet" type="text/css"
	href="backEnd/assets/css/ratio.css">
<link rel="stylesheet" type="text/css"
	href="backEnd/assets/css/vendors/feather-icon.css">
<link rel="stylesheet" type="text/css"
	href="backEnd/assets/css/vendors/scrollbar.css">
<link rel="stylesheet" type="text/css"
	href="backEnd/assets/css/vendors/animate.css">
<link rel="stylesheet" type="text/css"
	href="backEnd/assets/css/vendors/bootstrap.css">
<link rel="stylesheet" href="backEnd/assets/css/vendors/slick.css">
<link rel="stylesheet" type="text/css"
	href="backEnd/assets/css/style.css">
<!-- <link rel="stylesheet" type="text/css" href="assets/css/vector-map.css"> -->
<!-- <link rel="stylesheet" type="text/css" href="assets/css/vendors/date-picker.css"> -->
<!-- <link rel="stylesheet" href="css/friendchat.css" type="text/css" /> -->
<style>
.panel {
  float: right;
  border: 2px solid #0078ae;
  border-radius: 5px;
  width: 50%;
}

.message-area {
  height: 70%;
  resize: none;
  box-sizing: border-box;
  overflow: auto;
  background-color: #ffffff;
}

.input-area {
  background: #0078ae;
  box-shadow: inset 0 0 10px #00568c;
}

.input-area input {
  margin: 0.5em 0em 0.5em 0.5em;
}

.text-field {
  border: 1px solid grey;
  padding: 0.2em;
  box-shadow: 0 0 5px #000000;
}

h1 {
  font-size: 1.5em;
  padding: 5px;
  margin: 5px;
}

#message {
  min-width: 50%;
  max-width: 60%;
}

.statusOutput {
  background: #0078ae;
  text-align: center;
  color: #ffffff;
  border: 1px solid grey;
  padding: 0.2em;
  box-shadow: 0 0 5px #000000;
  width: 30%;
  margin-top: 10%;
  margin-left: 60%;
}

#row {
  float: left;
  width: 50%;
}

.column {
  float: left;
  width: 50%;
  padding: 5%;
  margin-bottom: 5px;
  background-color: #ffffff;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

ul li {
  display: inline-block;
  clear: both;
  padding: 20px;
  border-radius: 30px;
  margin-bottom: 2px;
  font-family: Helvetica, Arial, sans-serif;
}

.friend {
  background: #eee;
  float: left;
}

.me {
  float: right;
  background: #0084ff;
  color: #fff;
}

.friend + .me {
  border-bottom-right-radius: 5px;
}

.me + .me {
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}

.me:last-of-type {
  border-bottom-right-radius: 30px;
}
/* styles.css */

/* Friend Chat Styles */
.chat-container {
  margin: auto;
  padding: 0px;
}

.html,
body {
  font: 15px verdana, Times New Roman, arial, helvetica, sans-serif, Microsoft JhengHei;
  width: 90%;
  height: 90%;
  background: #eeeeda;
}

.panel {
  float: right;
  border: 2px solid #0078ae;
  border-radius: 5px;
  width: 50%;
}

.message-area {
  height: 70%;
  resize: none;
  box-sizing: border-box;
  overflow: auto;
  background-color: #ffffff;
}

.input-area {
  background: #0078ae;
  box-shadow: inset 0 0 10px #00568c;
}

.input-area input {
  margin: 0.5em 0em 0.5em 0.5em;
}

.text-field {
  border: 1px solid grey;
  padding: 0.2em;
  box-shadow: 0 0 5px #000000;
}

h1 {
  font-size: 1.5em;
  padding: 5px;
  margin: 5px;
}

#message {
  min-width: 50%;
  max-width: 60%;
}

.statusOutput {
  background: #0078ae;
  text-align: center;
  color: #ffffff;
  border: 1px solid grey;
  padding: 0.2em;
  box-shadow: 0 0 5px #000000;
  width: 30%;
  margin-top: 10%;
  margin-left: 60%;
}

#row {
  float: left;
  width: 50%;
}

.column {
  float: left;
  width: 50%;
  padding: 5%;
  margin-bottom: 5px;
  background-color: #ffffff;
}

ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

ul li {
  display: inline-block;
  clear: both;
  padding: 20px;
  border-radius: 30px;
  margin-bottom: 2px;
  font-family: Helvetica, Arial, sans-serif;
}

.friend {
  background: #eee;
  float: left;
}

.me {
  float: right;
  background: #0084ff;
  color: #fff;
}

.friend + .me {
  border-bottom-right-radius: 5px;
}

.me + .me {
  border-top-right-radius: 5px;
  border-bottom-right-radius: 5px;
}

.me:last-of-type {
  border-bottom-right-radius: 30px;
}

/* Furrever Admin Template Styles (Please Remove or Modify as Needed) */
body {
  font-family: 'Public Sans', sans-serif;
}

/* Rest of the styles... */


</style>
</head>
<body onload="connect();" onunload="disconnect();">

	<div class="tap-top">
		<span class="lnr lnr-chevron-up"></span>
	</div>

	<div class="page-wrapper compact-wrapper" id="pageWrapper">
		<div class="page-header">
			<div class="header-wrapper m-0">
				<div class="header-logo-wrapper p-0">
					<div class="logo-wrapper">
						<a href="back_index.html"> <img class="img-fluid main-logo"
							src="backEnd/assets/images/logo/1.png" alt="logo"> <img
							class="img-fluid white-logo"
							src="backEnd/assets/images/logo/1-white.png" alt="logo">
						</a>
					</div>
					<div class="toggle-sidebar">
						<i class="status_toggle middle sidebar-toggle"
							data-feather="align-center"></i> <a href="back_index.html"> <img
							src="backEnd/assets/images/logo/1.png" class="img-fluid" alt="">
						</a>
					</div>
				</div>

				<div class="nav-right col-6 pull-right right-header p-0">
					<ul class="nav-menus">
						<li><span class="header-search"> <i
								class="ri-search-line"></i>
						</span></li>
						<li class="onhover-dropdown">
							<div class="notification-box">
								<i class="ri-notification-line"></i> <span
									class="badge rounded-pill badge-theme">4</span>
							</div>
							<ul class="notification-dropdown onhover-show-div">
								<li><i class="ri-notification-line"></i>
									<h6 class="f-18 mb-0">通知</h6></li>
								<li>
									<p>
										<i class="fa fa-circle me-2 font-primary"></i>交貨加工 <span
											class="pull-right">10 min.</span>
									</p>
								</li>
								<li>
									<p>
										<i class="fa fa-circle me-2 font-success"></i>訂單完成<span
											class="pull-right">1 hr</span>
									</p>
								</li>
								<li>
									<p>
										<i class="fa fa-circle me-2 font-info"></i>訂單生成<span
											class="pull-right">3 hr</span>
									</p>
								</li>
								<li>
									<p>
										<i class="fa fa-circle me-2 font-danger"></i>交付完成<span
											class="pull-right">6 hr</span>
									</p>
								</li>
								<li></li>
							</ul>
						</li>

						<li class="profile-nav onhover-dropdown pe-0 me-0">
							<div class="media profile-media">
								<img class="user-profile rounded-circle"
									src="backEnd/assets/images/users/4.jpg" alt="">
								<div class="user-name-hide media-body">
									<span>埃梅·沃爾特</span>
									<p class="mb-0 font-roboto">
										行政<i class="middle ri-arrow-down-s-line"></i>
									</p>
								</div>
							</div>
							<ul class="profile-dropdown onhover-show-div">
								<li><a href="order-list.html"> <i
										data-feather="archive"></i> <span>訂單</span>
								</a></li>
								<li><a href="profile-setting.html"> <i
										data-feather="settings"></i> <span>設置</span>
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
						<a href="back_index.html" data-bs-original-title="" title="">
							<img class="img-fluid for-white"
							src="backEnd/assets/images/logo/full-white.png" alt="logo">
						</a>
						<div class="back-btn">
							<i class="fa fa-angle-left"></i>
						</div>
					</div>
					<div class="logo-icon-wrapper">
						<a href="back_index.html"> <img
							class="img-fluid main-logo main-white"
							src="backEnd/assets/images/logo/1-white.png" alt="logo"> <img
							class="img-fluid main-logo main-dark"
							src="backEnd/assets/images/logo/logo-white.png" alt="logo">
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
									href="back_index.html"> <i class="ri-home-line"></i> <span>主頁</span>
								</a></li>

								<li class="sidebar-list"><a
									class="linear-icon-link sidebar-link sidebar-title"
									href="javascript:void(0)"> <i class="ri-store-3-line"></i>
										<span>產品</span>
								</a>
									<ul class="sidebar-submenu">
										<li><a href="products.html">所有產品</a></li>

										<li><a href="add-new-product.html">添加產品</a></li>

										<li><a href="add-new-product2.html">添加團購</a></li>
									</ul></li>

								<li class="sidebar-list"><a
									class="sidebar-link sidebar-title" href="javascript:void(0)">
										<i class="ri-archive-line"></i> <span>訂單</span>
								</a>
									<ul class="sidebar-submenu">
										<li><a href="order-list.html">訂單列表</a></li>
										<li><a href="order-detail.html">訂單明細</a></li>
										<li><a href="order-list2.html">團購訂單</a></li>
										<li><a href="order-list3.html">團購訂單明細</a></li>
									</ul></li>

								<li class="sidebar-list"><a
									class="linear-icon-link sidebar-link sidebar-title"
									href="javascript:void(0)"> <i class="ri-settings-line"></i>
										<span>編輯</span>
								</a>
									<ul class="sidebar-submenu">
										<li><a href="profile-setting.html"> 個人設定</a></li>
									</ul></li>

								<li class="sidebar-list"><a
									class="sidebar-link sidebar-title link-nav" href="talk.html">
										<i class="fas fa-comments"></i> <span>聊天室</span>
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


				<h3 id="statusOutput" class="statusOutput"></h3>
				<div id="row"></div>
				<div id="messagesArea" class="panel message-area"></div>
				<div class="panel input-area">
					<input id="message" class="text-field" type="text"
						placeholder="Message"
						onkeydown="if (event.keyCode == 13) sendMessage();" /> <input
						type="submit" id="sendMessage" class="button" value="Send"
						onclick="sendMessage();" /> <input type="button" id="connect"
						class="button" value="Connect" onclick="connect();" /> <input
						type="button" id="disconnect" class="button" value="Disconnect"
						onclick="disconnect();" />
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
					<h5 class="modal-title" id="staticBackdropLabel">Logging Out</h5>
					<p>Are you sure you want to log out?</p>
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
</body>
<script src="backEnd/assets/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="backEnd/assets/js/icons/feather-icon/feather.min.js"></script>
<script src="backEnd/assets/js/icons/feather-icon/feather-icon.js"></script>
<script src="backEnd/assets/js/jquery-3.6.0.min.js"></script>
<script src="backEnd/assets/js/scrollbar/simplebar.js"></script>
<script src="backEnd/assets/js/scrollbar/custom.js"></script>
<script src="backEnd/assets/js/config.js"></script>
<script src="backEnd/assets/js/sidebar-menu.js"></script>
<script src="backEnd/assets/js/notify/bootstrap-notify.min.js"></script>
<script src="backEnd/assets/js/chart/apex-chart/apex-chart1.js"></script>
<script src="backEnd/assets/js/chart/apex-chart/moment.min.js"></script>
<script src="backEnd/assets/js/chart/apex-chart/apex-chart.js"></script>
<script src="backEnd/assets/js/chart/apex-chart/stock-prices.js"></script>
<script src="backEnd/assets/js/ratio.js"></script>
<script src="backEnd/assets/js/sidebareffect.js"></script>
<script src="backEnd/assets/js/script.js"></script>
<script>
	var MyPoint = "/FriendWS/${userName}";
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
	var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;

	var statusOutput = document.getElementById("statusOutput");
	var messagesArea = document.getElementById("messagesArea");
	var self = '${userName}';
	var webSocket;

	function connect() {
		// create a websocket
		webSocket = new WebSocket(endPointURL);

		webSocket.onopen = function(event) {
			console.log("Connect Success!");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var jsonObj = JSON.parse(event.data);
			if ("open" === jsonObj.type) {
				refreshFriendList(jsonObj);
			} else if ("history" === jsonObj.type) {
				messagesArea.innerHTML = '';
				var ul = document.createElement('ul');
				ul.id = "area";
				messagesArea.appendChild(ul);
				// 這行的jsonObj.message是從redis撈出跟好友的歷史訊息，再parse成JSON格式處理
				var messages = JSON.parse(jsonObj.message);
				for (var i = 0; i < messages.length; i++) {
					var historyData = JSON.parse(messages[i]);
					var showMsg = historyData.message;
					var li = document.createElement('li');
					// 根據發送者是自己還是對方來給予不同的class名, 以達到訊息左右區分
					historyData.sender === self ? li.className += 'me'
							: li.className += 'friend';
					li.innerHTML = showMsg;
					ul.appendChild(li);
				}
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("chat" === jsonObj.type) {
				var li = document.createElement('li');
				jsonObj.sender === self ? li.className += 'me'
						: li.className += 'friend';
				li.innerHTML = jsonObj.message;
				console.log(li);
				document.getElementById("area").appendChild(li);
				messagesArea.scrollTop = messagesArea.scrollHeight;
			} else if ("close" === jsonObj.type) {
				refreshFriendList(jsonObj);
			}

		};

		webSocket.onclose = function(event) {
			console.log("Disconnected!");
		};
	}

	function sendMessage() {
		var inputMessage = document.getElementById("message");
		var friend = statusOutput.textContent;
		var message = inputMessage.value.trim();

		if (message === "") {
			alert("Input a message");
			inputMessage.focus();
		} else if (friend === "") {
			alert("Choose a friend");
		} else {
			var jsonObj = {
				"type" : "chat",
				"sender" : self,
				"receiver" : friend,
				"message" : message
			};
			webSocket.send(JSON.stringify(jsonObj));
			inputMessage.value = "";
			inputMessage.focus();
		}
	}

	// 有好友上線或離線就更新列表
	function refreshFriendList(jsonObj) {
		var friends = jsonObj.users;
		var row = document.getElementById("row");
		row.innerHTML = '';
		for (var i = 0; i < friends.length; i++) {
			if (friends[i] === self) {
				continue;
			}
			row.innerHTML += '<div id=' + i + ' class="column" name="friendName" value=' + friends[i] + ' ><h2>'
					+ friends[i] + '</h2></div>';
		}
		addListener();
	}
	// 註冊列表點擊事件並抓取好友名字以取得歷史訊息
	function addListener() {
		var container = document.getElementById("row");
		container.addEventListener("click", function(e) {
			var friend = e.srcElement.textContent;
			updateFriendName(friend);
			var jsonObj = {
				"type" : "history",
				"sender" : self,
				"receiver" : friend,
				"message" : ""
			};
			webSocket.send(JSON.stringify(jsonObj));
		});
	}

	function disconnect() {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	function updateFriendName(name) {
		statusOutput.innerHTML = name;
	}
</script>
</body>
</html>