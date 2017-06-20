$(".js-info-content").on("click",'.js-role-form', function(){loadRoleData(this)});

function loadRoleData(obj) {
    var params = {};
    var dataset;
    var tempDataset=$(".js-role-form")[0].dataset;
    if (obj == undefined) {
        dataset = tempDataset;
    } else {
        dataset = obj.dataset;
    }
    params.userId = dataset.id;
    var role_info = $(".role-info");
    $.get(
        '/api/user/roles',
        params,
        function(data){
            role_info.empty();
            var children = [];
            var child = $('<input type="hidden" name="userId" value="' + params.userId + '" />');
            children.push(child);
            data.forEach(function(it){
                child = factoryRoleChild(it);
                children.push(child);
            });
            role_info.html(children);
        }
    );
}


function factoryRoleChild(role) {
    if(role.isGrant){
        return $('<div class="checkbox">' +
            '<label>' +
            '<input type="checkbox" id="blankCheckbox" name="roleIds" value="' +
            role.appId + '" aria-label="' +
            role.name + '" checked="checked">' +
            role.name + '</label> ' +
            '</div>');
    } else {
        return $('<div class="checkbox">' +
            '<label>' +
            '<input type="checkbox" id="blankCheckbox" name="roleIds" value="' +
            role.appId + '" aria-label="' +
            role.name + '">' +
            role.name + '</label> ' +
            '</div>');
    }
}

$(".app-grant-footer").on("click",'.js-grant-role', function(){grantApp(this)});

function grantApp() {
    var roleId = $("input[name = 'userRoleId']").val();
    var apps = $("input[name = 'roleIds']");
    var paramStr = 'userId=' + roleId;
    for(var i = 0; i < apps.length; i++){
        if($(apps[i]).is(':checked')){
            paramStr = paramStr + "&roleIds=" + $(apps[i]).val();
        }
    }

    if(paramStr.indexOf('roleIds') == -1){
        paramStr = paramStr + "&roleIds=-1";
    }

    $.ajax({
        url:'/api/user/role?' + paramStr,
        method: 'put',
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

