function putOtherParams(params, dataset) {
    return;
}

function factoryChild(it) {
    return $('<tr>'
        + '<td>' + it['id'] + '</td>'
        + '<td>' + it['name'] + '</td>'
        + '<td>' + (it['key'] == undefined ? '': it['key']) + '</td>'
        + '<td>' + (it['secret'] == undefined ? '': it['secret']) + '</td>'
        + '<td>' + it['domain'] + '</td>'
        + '<td>'
        + '<a href="/cms/perms?appId=' + it['id'] + '">权限列表</a>'
        + '\r\n<a data-info="' + JSON.stringify(it) + '" class="js-update-form" href="javascript:void(0)" data-toggle="modal" data-target="#updateModal">编辑</a>'
        + '\r\n<a class="js-app-del" href="javascript:void(0)" data-id="' + it['id'] + '">删除</a>'
        + '</td>'
        + '</tr>');
}

$(".js-app-info-content").on("click",'.js-app-del', function(){del(this)});

function del(obj) {
    if(obj == undefined){
        return;
    }

    var id = obj.dataset['id'];

    $.ajax({
        url:'/api/cms/app/del',
        method: 'put',
        data: {'appId': id},
        async: false,
        dataType: 'json',
        success: function () {
            alert('success')
        },
        error: function () {
            alert('error')
        }
    });
}

$(".panel-heading").on("click",'.js-add-form', function(){
    $("#addName").val('');
    $("#addDomain").val('');
});

$(".add-footer").on("click",'.js-app-add', function(){add()});

function add() {

    var data = JSON.stringify($(".app-add").serializeObject());

    $.ajax({
        url:'/api/cms/app/add',
        method: 'put',
        data: data,
        contentType:'application/json',
        async: false,
        dataType: 'json',
        success: function () {
            alert('success');
            location.reload();
        },
        error: function (error) {
            alert(error['responseJSON']['error'])
        }
    });
}

function update() {

    var data = $(".app-update").serializeArray();

    $.ajax({
        url:'/api/cms/app/update',
        method: 'put',
        data: data,
        context:'application/json',
        async: false,
        dataType: 'json',
        success: function () {
            alert('success')
        },
        error: function () {
            alert('error')
        }
    });
}


