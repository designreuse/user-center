$(function () {
    loadData();
});


function loadData(obj) {
    var params = {};
    var url;
    var dataset;
    var tempDataset=$(".pagination-params")[0].dataset;
    if (obj == undefined) {
        dataset = tempDataset;
    } else {
        dataset = obj.dataset;
    }
    params["pageNo"] = dataset.pageno;
    params["pageSize"] = dataset.pagesize;
    url=dataset.url;
    $.get(
        url,
        params,
        function(data){
            tempDataset["pageno"] = data.pageNo;
            tempDataset["pagesize"] = data.pageSize;
            tempDataset["total"] = data.total;
            var apps = data['datas'];
            var content = $(".js-app-info-content");
            content.empty();
            var children = [];
            apps.forEach(function(it){
                var child = $('<tr>'
                    + '<td>' + it['id'] + '</td>'
                    + '<td>' + it['name'] + '</td>'
                    + '<td>' + (it['key'] == undefined ? '': it['key']) + '</td>'
                    + '<td>' + it['domain'] + '</td>'
                    + '<td>'
                    + '<a href="/cms/perms?appId=' + it['id'] + '">权限列表</a>'
                    + '\r\n<a data-info="' + JSON.stringify(it) + '" href="#">编辑</a>'
                    + '\r\n<a href="#">删除</a>'
                    + '</td>'
                    + '</tr>');
                children.push(child);
            });
            content.html(children);
            rePaintPagination();
        }
    );
}
