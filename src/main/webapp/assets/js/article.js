// Share button with tooltip
const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))

const myTooltipEl = document.getElementById('share-tooltip');
const tooltip = bootstrap.Tooltip.getOrCreateInstance(myTooltipEl)

myTooltipEl.addEventListener('click', () => {
  let url = myTooltipEl.dataset.url;
  navigator.clipboard.writeText(url);
  alert(`文章網址${url}已複製成功`);
})

tooltip.hide()
// Share button with tooltip end

// article like button
$("#article-like").on("click",function(){
    console.log("喜歡");
});

// article comment button
$("#article-comment").on("click",function(){
    console.log("跳到留言");
});

// comment report button
$("i.comment-report").on("click", function(){
    console.log("留言檢舉");
});