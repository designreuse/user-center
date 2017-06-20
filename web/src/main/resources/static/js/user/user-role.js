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
            role.roleId + '" aria-label="' +
            role.name + '" checked="checked">' +
            role.name + '</label> ' +
            '</div>');
    } else {
        return $('<div class="checkbox">' +
            '<label>' +
            '<input type="checkbox" id="blankCheckbox" name="roleIds" value="' +
            role.roleId + '" aria-label="' +
            role.name + '">' +
            role.name + '</label> ' +
            '</div>');
    }
}

$(".role-grant-footer").on("click",'.js-grant-role', function(){grantRole(this)});

function grantRole() {
    var userId = $("input[name = 'userId']").val();
    var roles = $("input[name = 'roleIds']");
    var paramStr = 'userId=' + userId;
    for(var i = 0; i < roles.length; i++){
        if($(roles[i]).is(':checked')){
            paramStr = paramStr + "&roleIds=" + $(roles[i]).val();
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

