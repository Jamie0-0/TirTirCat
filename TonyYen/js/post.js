
// 公開/朋友
$("a.dropdown-item").on("click",function(){
    $("button.dropdown-toggle").text($(this).text());
});


// upload_img
$('#upload_img').on('change', function(){

    var imgFiles = $(this)[0].files
    var preview = document.getElementById("previewImg")
    var nodes = document.getElementsByClassName("carousel-item");
    while (nodes.length > 0) {
    nodes[0].parentNode.removeChild(nodes[0]);
    }


    for (i=0;i<imgFiles.length;i++){
        filePath = imgFiles[i].name
        fileFormat = filePath.split('.')[1].toLowerCase()  
        src = window.URL.createObjectURL(imgFiles[i])

        
        var div = document.createElement('div')
        if(i==0){
            div.className = 'carousel-item active'
        }else{
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
})