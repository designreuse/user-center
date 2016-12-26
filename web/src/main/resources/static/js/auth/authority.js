function putOtherParams(params, dataset) {
    params["appId"] = dataset['appid'];
}

function factoryChild(it) {
    return $('<tr>'
        + '<td>' + it['id'] + '</td>'
        + '<td>' + it['name'] + '</td>'
        + '<td>' + it['auth'] + '</td>'
        + '<td>' + (it['permKey'] == undefined ? '': it['permKey']) + '</td>'
        + '<td>' + it['url'] + '</td>'
        + '<td>'
        + '<a data-info="' + JSON.stringify(it) + '" href="#">详情</a>'
        + '<a data-info="' + JSON.stringify(it) + '" href="#">编辑</a>'
        + '\r\n<a href="#">删除</a>'
        + '</td>'
        + '</tr>');
}


