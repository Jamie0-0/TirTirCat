$("#rule-checked").on("click",function(){
	
  let isChecked = $(this).prop("checked");
  if(isChecked){
	$("#rule-submit").removeClass("disabled");
}else{
	$("#rule-submit").addClass(" disabled");
}
  
});

$("#rule-submit").on("click", function() {
    window.location.href = "article.html" + "?fromRule=true";
});
