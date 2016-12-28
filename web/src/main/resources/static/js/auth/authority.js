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
        + '<a data-info=\'' + JSON.stringify(it) + '\' href="#">详情</a>'
        + '\r\n<a data-info=\'' + JSON.stringify(it) + '\' class="js-update-form" href="#" data-toggle="modal" data-target="#authorityModal">编辑</a>'
        + '\r\n<a class="js-del" href="#" data-id="' + it['id'] + '">删除</a>'
        + '</td>'
        + '</tr>');
}

var app_infos = $(".js-info-content");

app_infos.on("click",'.js-app-del', function(){del(this)});

function del(obj) {
    if(obj == undefined){
        return;
    }

    var id = obj.dataset['id'];

    $.ajax({
        url:'/api/perm/app/del',
        method: 'put',
        data: {'appId': id},
        async: false,
        dataType: 'json',
        success: function () {
            alert('success');
            location.reload();
        },
        error: function () {
            alert('error')
        }
    });
}

var panel_head = $(".panel-heading");

panel_head.on("click",'.js-add-form', function(){
    $("input[name='id']").val('');
    $("input[name='name']").val('');
    $("input:radio[name='auth']").eq(0).attr("checked",'checked');
    $("input[name='permKey']").val('');
    $("input[name='roleKey']").val('');
    $("input[name='url']").val('');
    $(".js-add").show();
    $(".js-update").hide();
});

var footer = $(".add-footer");

footer.on("click",'.js-app-add', function(){add()});

function add() {

    var data = JSON.stringify($(".app-info").serializeObject());

    $.ajax({
        url:'/api/cms/perm/add',
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

app_infos.on("click",'.js-update-form', function(){
    var dataset = this.dataset;
    var info = JSON.parse(dataset.info);
    $("input[name='id']").val(info['id']);
    $("input[name='name']").val(info['name']);
    if(info['auth']=='anon'){
        $("input:radio[name='auth']").eq(0).attr("checked",'checked');
    } else {
        $("input:radio[name='auth']").eq(1).attr("checked",'checked');
    }
    $("input[name='permKey']").val(info['permKey']);
    $("input[name='roleKey']").val(info['roleKey']);
    $("input[name='url']").val(info['url']);
    $(".js-add").hide();
    $(".js-update").show();
});

footer.on("click",'.js-app-update', function(){update()});

function update() {

    var data = JSON.stringify($(".app-info").serializeObject());

    $.ajax({
        url:'/api/cms/perm/update',
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


