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
    putOtherParams(params, tempDataset);
    url=dataset.url;
    $.get(
        url,
        params,
        function(data){
            var datas = data['datas'];
            var content = $(".js-app-info-content");
            content.empty();
            var children = [];
            tempDataset["pageno"] = data.pageNo;
            tempDataset["pagesize"] = data.pageSize;
            tempDataset["total"] = data.total;
            datas.forEach(function(it){
                var child = factoryChild(it);
                children.push(child);
            });
            content.html(children);
            rePaintPagination();
        }
    );
}



