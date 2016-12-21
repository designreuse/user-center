$(function () {
    loadData();
});


function loadData(obj) {
    var params = {};
    var url;
    var dataset;
    if (obj == undefined) {
        dataset = $(".pagination-params")[0].dataset;
    } else {
        dataset = obj.dataset;
    }
    params["pageNo"] = dataset.pageno;
    params["pageSize"] = dataset.pagesize;
    params["appId"] = dataset.appid;
    url=dataset.url;
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
                    + '<td>' + it.auth + '</td>'
                    + '<td>' + it.permKey + '</td>'
                    + '<td>' + it.url + '</td>'
                    + '<td>'
                    + '<a data-info="' + JSON.stringify(it) + '" href="#">编辑</a>'
                    + '\r\n<a href="#">删除</a>'
                    + '</td>'
                    + '</tr>');

                $(".js-app-info-content").append(child)
            });
            rePaintPagination();
        }
    );
}
