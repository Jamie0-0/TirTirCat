
// 公開/朋友
$("a.dropdown-item").on("click", function() {
	$("button.dropdown-toggle").text($(this).text());
});


// upload_img
$('#upload_img').on('change', function() {

	let imgFiles = $(this)[0].files;
	let preview = document.getElementById("previewImg")
	let nodes = document.getElementsByClassName("carousel-item");
	while (nodes.length > 0) {
		nodes[0].parentNode.removeChild(nodes[0]);
	}


	for (i = 0; i < imgFiles.length; i++) {
		filePath = imgFiles[i].name
		fileFormat = filePath.split('.')[1].toLowerCase()
		src = window.URL.createObjectURL(imgFiles[i])


		var div = document.createElement('div')
		if (i == 0) {
			div.className = 'carousel-item active'
		} else {
			div.className = 'carousel-item'
		}
		var img = document.createElement('img')
		img.width = 200
		img.height = 200
		img.src = src
		img.className = 'img-thumbnail card-img-top article d-block w-100'

		div.appendChild(img)
		preview.appendChild(div)
	}
});


$(document).ready(function() {
  $("#post-form").on("submit", function(e) {
    e.preventDefault();

    let formData = new FormData(this);  // 收集所有form表單裡的input值
    $.ajax({
      url: "/TirTirCat/ArtInsert",
      type: "POST",
      data: formData,
      processData: false,
      contentType: false,
      success: function(data) {
        alert(data);
   		fetch("/TirTirCat/refresh")
   			.then(response => response.json())
   			.then(data =>{
				  	 console.log(data);
			   });
		$(window).attr("location","/TirTirCat/forum.html")
      },
    });
  });
});




