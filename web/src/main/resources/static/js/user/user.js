function putOtherParams(params, dataset) {
}

function factoryChild(it) {
    return $('<tr>'
        + '<td>' + it['id'] + '</td>'
        + '<td>' + it['name'] + '</td>'
        + '<td>' + it['userName'] + '</td>'
        + '<td>'
        + '\r\n<a class="js-role-form" data-id=\'' + it['id'] + '\' href="#" data-toggle="modal" data-target="#userRoleModal">分配角色</a>'
        + '\r\n<a data-info=\'' + JSON.stringify(it) + '\' class="js-update-form" href="#" data-toggle="modal" data-target="#userModal">编辑</a>'
        + '\r\n<a class="js-del" href="#" data-id="' + it['id'] + '">删除</a>'
        + '</td>'
        + '</tr>');
}


var user_infos = $(".js-info-content");

user_infos.on("click",'.js-del', function(){del(this)});

function del(obj) {
    if(obj == undefined){
        return;
    }

    var id = obj.dataset['id'];

    $.ajax({
        url:'/api/user?id=' + id,
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
    $("input[name='userName']").val('');
    $("input[name='password']").val('');
    $("input[name='email']").val('');
    $("input[name='mobile']").val('');
    $("input[name='qq']").val('');
    $("input[name='outerId']").val('');
    $(".js-add").show();
    $(".js-update").hide();
});

var footer = $(".add-footer");

footer.on("click",'.js-add', function(){add()});

function add() {

    var data = JSON.stringify($(".data-info").serializeObject());

    $.ajax({
        url:'/api/user',
        method: 'post',
        data: data,
        contentType:'application/json',
        async: false,
        dataType: 'json',
        success: function (data) {
            alert('success');
            location.reload();
        },
        // error: function (error) {
        //     alert(error['responseJSON']['message'])
        // }
    });
}

user_infos.on("click",'.js-update-form', function(){
    var dataset = this.dataset;
    var userInfo = JSON.parse(dataset.info);
    $("input[name='id']").val(userInfo['id']);
    $("input[name='name']").val(userInfo['name']);
    $("input[name='userName']").val(userInfo['userName']);
    $("input[name='password']").val(userInfo['password']);
    $("input[name='email']").val(userInfo['email']);
    $("input[name='mobile']").val(userInfo['mobile']);
    $("input[name='qq']").val(userInfo['qq']);
    $("input[name='outerId']").val(userInfo['outerId']);
    $(".js-add").hide();
    $(".js-update").show();
});

footer.on("click",'.js-update', function(){update()});

function update() {

    var data = JSON.stringify($(".data-info").serializeObject());

    $.ajax({
        url:'/api/user',
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

