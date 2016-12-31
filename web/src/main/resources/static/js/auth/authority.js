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
        + '<a data-info=\'' + JSON.stringify(it) + '\' class="js-detail-form" href="#" data-toggle="modal" data-target="#authorityModal">详情</a>'
        + '\r\n<a data-info=\'' + JSON.stringify(it) + '\' class="js-update-form" href="#" data-toggle="modal" data-target="#authorityModal">编辑</a>'
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
        url:'/api/cms/perm/del',
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
    $("input:radio[name='auth']").eq(0).attr("checked",'checked');
    changeAuthType('anon');
    $("input[name='permKey']").val('');
    $("input[name='roleKey']").val('');
    $("input[name='url']").val('');
    $(".js-add").show();
    $(".js-cancel").show();
    $(".js-update").hide();
});

var footer = $(".add-footer");

footer.on("click",'.js-add', function(){add()});

function add() {

    var data = JSON.stringify($(".data-info").serializeObject());

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
            alert(error['responseJSON']['message'])
        }
    });
}

app_infos.on("click",'.js-update-form', function(){
    var dataset = this.dataset;
    setValueOnForm(dataset);
    enableForm();
    var info = JSON.parse(dataset.info);
    changeAuthType(info['auth']);
    $(".js-add").hide();
    $(".js-cancel").show();
    $(".js-update").show();
});

footer.on("click",'.js-update', function(){update()});

function update() {

    var data = JSON.stringify($(".data-info").serializeObject());

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
            alert(error['responseJSON']['message'])
        }
    });
}

$(".modal-body").on("change","input:radio[name='auth']", function(){
    $("input:radio[name='auth'][checked]").removeAttr("checked");
    $("input:radio[name='auth'][value='" + this.value + "']").attr("checked",'checked');
    changeAuthType(this.value);
});

function changeAuthType(authVal) {
    if(authVal == 'anon'){
        $($("input[name='permKey']")[0].parentNode.parentNode).hide();
        $($("input[name='roleKey']")[0].parentNode.parentNode).hide();
    } else {
        $($("input[name='permKey']")[0].parentNode.parentNode).show();
        $($("input[name='roleKey']")[0].parentNode.parentNode).show();
    }
}

app_infos.on("click",'.js-detail-form', function(){
    var dataset = this.dataset;
    setValueOnForm(dataset);
    disableForm();
    $(".js-add").hide();
    $(".js-cancel").hide();
    $(".js-update").hide();
});


function setValueOnForm(dataset) {
    var info = JSON.parse(dataset.info);
    $("input[name='id']").val(info['id']);
    $("input[name='name']").val(info['name']);
    if(info['auth']=='anon'){
        $("input:radio[name='auth']").eq(0).attr("checked",'checked');
    } else {
        $("input:radio[name='auth']").eq(1).attr("checked",'checked');
    }
    changeAuthType(info['auth']);
    $("input[name='permKey']").val(info['permKey']);
    $("input[name='roleKey']").val(info['roleKey']);
    $("input[name='url']").val(info['url']);
}

function enableForm() {
    $("input[name='id']").attr("disabled",false);
    $("input[name='name']").attr("disabled",false);
    $("input:radio[name='auth']").eq(0).attr("disabled",false);
    $("input:radio[name='auth']").eq(1).attr("disabled",false);
    $("input[name='permKey']").attr("disabled",false);
    $("input[name='roleKey']").attr("disabled",false);
    $("input[name='url']").attr("disabled",false);
}

function disableForm() {
    $("input[name='id']").attr("disabled",true);
    $("input[name='name']").attr("disabled",true);
    $("input:radio[name='auth']").eq(0).attr("disabled",true);
    $("input:radio[name='auth']").eq(1).attr("disabled",true);
    $("input[name='permKey']").attr("disabled",true);
    $("input[name='roleKey']").attr("disabled",true);
    $("input[name='url']").attr("disabled",true);
}