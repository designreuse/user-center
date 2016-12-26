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
    var pages = [];
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

    pages.push(pre);
    var page;
    for(var i=1; i<=pageCount; i++){
        page = $('<li><a class="js-page-event" data-pageNo="' + i + '"'
            + 'data-pageSize="' + pageSize + '"'
            + 'data-url="' + url + '"'
            + 'href="javascript:void(0)">' + i + '</a>'
            + '</li>');
        if(pageNo == i){
            page = $('<li class="active"><span>' + i + '</a></li>');
        }
        pages.push(page)
    }

    var next = $('<li><a class="js-page-event" data-pageNo="' + (pageNo + 1) + '"'
        + 'data-pageSize="' + pageSize + '"'
        + 'data-url="' + url + '"'
        + 'href="javascript:void(0)">&raquo;</a>'
        + '</li>');

    if(pageNo == pageCount){
        next = $('<li class="disabled"><span>&raquo;</span></li>');
    }

    pages.push(next);

    var pageSizeDiv = '<div class="ml-10 pull-right"><select class="form-control js-page-size-set">';

    var sizeArr = [5, 10, 20, 50];
    for(sizeItem in sizeArr){
        var item = sizeArr[sizeItem];
        if(item == pageSize){
            pageSizeDiv += '<option value="' + item +'" selected>' + item + '</option> ';
        } else {
            pageSizeDiv += '<option value="' + item +'">' + item + '</option> ';
        }
    }

    pageSizeDiv += '</select></div>';

    pages.push(pageSizeDiv);

    $(".pagination").html(pages);

}

function rePaintPagination() {
    $(".pagination").empty();
    initPagination()
}

$(".pagination").on("change",'.js-page-size-set', function(){changePageSize(this)});

function changePageSize(obj) {
    if(obj == undefined){
        return;
    }

    $(".pagination-params")[0].dataset['pagesize'] = obj.value;

    loadData();
}
