$(".js-info-content").on("click",'.js-authority-form', function(){loadAuthData(this)});

function loadAuthData(obj) {
    var params = {};
    var dataset;
    var tempDataset=$(".js-authority-form")[0].dataset;
    if (obj == undefined) {
        dataset = tempDataset;
    } else {
        dataset = obj.dataset;
    }
    params.roleId = dataset.id;
    var auth_info = $(".auth-info");
    $.get(
        '/api/role/perms',
        params,
        function(data){
            auth_info.empty();
            var children = [];
            children.push($('<input type="hidden" name="authRoleId" value="' + params.roleId + '" />'));
            data.forEach(function(it){
                children.push( $('<div class="checkbox"><label>' + it.appName + '</label></div>'));
                children.push( $('<hr/>'));
                if(it.roleAuthDtos != undefined){
                    it.roleAuthDtos.forEach(function(auth){
                        children.push(factoryAuthChild(auth));
                    });
                }
                children.push( $('<hr/>'));
            });
            auth_info.html(children);
        }
    );
}

function factoryAuthChild(auth) {
    if(auth.isGrant){
        return $('<div class="checkbox lp-50">' +
            '<label>' +
            '<input type="checkbox" id="blankCheckbox" name="permIds" value="' +
            auth.permId + '" aria-label="' +
            auth.name + '" checked="checked">' +
            auth.name + '</label> ' +
            '</div>');
    } else {
        return $('<div class="checkbox lp-50">' +
            '<label>' +
            '<input type="checkbox" id="blankCheckbox" name="permIds" value="' +
            auth.permId + '" aria-label="' +
            auth.name + '">' +
            auth.name + '</label> ' +
            '</div>');
    }
}

$(".auth-grant-footer").on("click",'.js-grant-auth', function(){grantAuth(this)});

function grantAuth() {
    var roleId = $("input[name = 'authRoleId']").val();
    var apps = $("input[name = 'permIds']");
    var paramStr = 'roleId=' + roleId;
    for(var i = 0; i < apps.length; i++){
        if($(apps[i]).is(':checked')){
            paramStr = paramStr + "&permIds=" + $(apps[i]).val();
        }
    }

    if(paramStr.indexOf('permIds') == -1){
        paramStr = paramStr + "&permIds=-1";
    }

    $.ajax({
        url:'/api/role/perm?' + paramStr,
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

