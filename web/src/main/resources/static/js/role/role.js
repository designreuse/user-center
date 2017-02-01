function putOtherParams(params, dataset) {
}

function factoryChild(it) {
    return $('<tr>'
        + '<td>' + it['id'] + '</td>'
        + '<td>' + it['name'] + '</td>'
        + '<td>'
        + '\r\n<a href="#">应用授权</a>'
        + '\r\n<a data-info=\'' + JSON.stringify(it) + '\' class="js-update-form" href="#" data-toggle="modal" data-target="#roleModal">编辑</a>'
        + '\r\n<a class="js-del" href="#" data-id="' + it['id'] + '">删除</a>'
        + '</td>'
        + '</tr>');
}

var role_infos = $(".js-info-content");

role_infos.on("click",'.js-del', function(){del(this)});

function del(obj) {
    if(obj == undefined){
        return;
    }

    var id = obj.dataset['id'];

    $.ajax({
        url:'/api/cms/role?id=' + id,
        method: 'DELETE',
        success: function (data) {
            alert('success');
            location.reload();
        },
        error: function (error) {
            alert(error['responseJSON']['message'])
        }
    });
}

var panel_head = $(".panel-heading");

panel_head.on("click",'.js-add-form', function(){
    $("input[name='id']").val('');
    $("input[name='name']").val('');
    $(".js-add").show();
    $(".js-update").hide();
});

var footer = $(".add-footer");

footer.on("click",'.js-add', function(){add()});

function add() {

    var data = JSON.stringify($(".data-info").serializeObject());

    $.ajax({
        url:'/api/cms/role',
        method: 'post',
        data: data,
        contentType:'application/json',
        async: false,
        dataType: 'json',
        success: function (data) {
            alert('success');
            location.reload();
        },
        error: function (error) {
            alert(error['responseJSON']['message'])
        }
    });
}

role_infos.on("click",'.js-update-form', function(){
    var dataset = this.dataset;
    var roleInfo = JSON.parse(dataset.info);
    $("input[name='id']").val(roleInfo['id']);
    $("input[name='name']").val(roleInfo['name']);
    $(".js-add").hide();
    $(".js-update").show();
});

footer.on("click",'.js-update', function(){update()});

function update() {

    var data = JSON.stringify($(".data-info").serializeObject());

    $.ajax({
        url:'/api/cms/role',
        method: 'put',
        data: data,
        contentType:'application/json',
        async: false,
        dataType: 'json',
        success: function (data) {
            alert('success');
            location.reload();
        },
        error: function (error) {
            alert(error['responseJSON']['message'])
        }
    });
}

