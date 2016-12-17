$(function () {
    initPagination();
});

$(".pagination").on("click",'.js-page-event', function(){loadData(this)});

function initPagination() {
    var dataset = $(".pagination-params")[0].dataset;
    var pageNo = parseInt(dataset.pageno);
    var pageSize = parseInt(dataset.pagesize);
    var url = dataset.url;
    var total = parseInt(dataset.total);
    var event = dataset.event;
    var pageCount = parseInt((total + pageSize - 1) / pageSize);
    if(total == 0){
        return;
    }

    var pre = $('<li><a class="js-page-event" data-pageNo="' + (pageNo - 1) + '"'
        + 'data-pageSize="' + pageSize + '"'
        + 'data-url="' + url + '"'
        + 'href="javascript:void(0)">&laquo;</a>'
        + '</li>');

    if(pageNo == 1){
        pre = $('<li class="disabled"><span>&laquo;</span></li>');
    }

    $(".pagination").append(pre);

    var page
    for(var i=1; i<=pageCount; i++){
        page = $('<li><a class="js-page-event" data-pageNo="' + i + '"'
            + 'data-pageSize="' + pageSize + '"'
            + 'data-url="' + url + '"'
            + 'href="javascript:void(0)">' + i + '</a>'
            + '</li>');
        if(pageNo == i){
            page = $('<li class="active"><span>' + i + '</a></li>');
        }
        $(".pagination").append(page);
    }

    var next = $('<li><a class="js-page-event" data-pageNo="' + (pageNo + 1) + '"'
        + 'data-pageSize="' + pageSize + '"'
        + 'data-url="' + url + '"'
        + 'href="javascript:void(0)">&raquo;</a>'
        + '</li>');

    if(pageNo == pageCount){
        next = $('<li class="disabled"><span>&raquo;</span></li>');
    }

    $(".pagination").append(next);

}

function rePaintPagination() {
    $(".pagination").empty();
    initPagination()
}