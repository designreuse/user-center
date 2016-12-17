$(function () {
    loadData();
});


function loadData(obj) {
    var params = {};
    var url;
    if (obj == undefined) {
        params["pageNo"] = $(".pagination-params")[0].dataset.pageno;
        params["pageSize"] = $(".pagination-params")[0].dataset.pagesize;
        url=$(".pagination-params")[0].dataset.url;
    } else {
        var dataset = obj.dataset;
        params["pageNo"] = dataset.pageno;
        params["pageSize"] = dataset.pagesize;
        url=dataset.url;
    }
    $.get(
        url,
        params,
        function(data){
            $(".pagination-params")[0].dataset["pageno"] = data.pageNo;
            $(".pagination-params")[0].dataset["pagesize"] = data.pageSize;
            $(".pagination-params")[0].dataset["total"] = data.total;
            var apps = data.datas
            $(".js-app-info-content").empty()
            apps.forEach(function(it){
                var child = $('<tr>'
                    + '<td>' + it.id + '</td>'
                    + '<td>' + it.name + '</td>'
                    + '<td>' + it.key + '</td>'
                    + '<td>' + it.domain + '</td>'
                    + '<td>'
                    + '<a href="/cms/perms?appId=${app.id}">权限列表</a>'
                    + '<a data-info="' + JSON.stringify(it) + '" href="#">编辑</a>'
                    + '<a href="#">删除</a>'
                    + '</td>'
                    + '</tr>');
                $(".js-app-info-content").append(child)
            });
            rePaintPagination();
        }
    );
}
