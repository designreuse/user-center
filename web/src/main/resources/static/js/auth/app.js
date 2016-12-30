function putOtherParams(params, dataset) {
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
        + '\r\n<a data-info=\'' + JSON.stringify(it) + '\' class="js-update-form" href="#" data-toggle="modal" data-target="#appModal">编辑</a>'
        + '\r\n<a class="js-del" href="#" data-id="' + it['id'] + '">删除</a>'
        + '</td>'
        + '</tr>');
}

var app_infos = $(".js-info-content");

app_infos.on("click",'.js-del', function(){del(this)});

function del(obj) {
    if(obj == undefined){
        return;
    }

    var id = obj.dataset['id'];

    $.ajax({
        url:'/api/cms/app/del',
        method: 'put',
        data: {'id': id},
        async: false,
        dataType: 'json',
        success: function () {
            alert('success');
            location.reload();
        },
        error: function () {
            alert(error['responseJSON']['message'])
        }
    });
}

var panel_head = $(".panel-heading");

panel_head.on("click",'.js-add-form', function(){
    $("input[name='id']").val('');
    $("input[name='name']").val('');
    $("input[name='domain']").val('');
    $(".js-add").show();
    $(".js-update").hide();
});

var footer = $(".add-footer");

footer.on("click",'.js-add', function(){add()});

function add() {

    var data = JSON.stringify($(".data-info").serializeObject());

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
            alert(error['responseJSON']['message'])
        }
    });
}

app_infos.on("click",'.js-update-form', function(){
    var dataset = this.dataset;
    var appInfo = JSON.parse(dataset.info);
    $("input[name='id']").val(appInfo['id']);
    $("input[name='name']").val(appInfo['name']);
    $("input[name='domain']").val(appInfo['domain']);
    $(".js-add").hide();
    $(".js-update").show();
});

footer.on("click",'.js-update', function(){update()});

function update() {

    var data = JSON.stringify($(".data-info").serializeObject());

    $.ajax({
        url:'/api/cms/app/update',
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
            alert(error['responseJSON']['message'])
        }
    });
}
