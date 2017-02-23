$(".js-info-content").on("click",'.js-app-form', function(){loadAppData(this)});

function loadAppData(obj) {
    var params = {};
    var dataset;
    var tempDataset=$(".js-app-form")[0].dataset;
    if (obj == undefined) {
        dataset = tempDataset;
    } else {
        dataset = obj.dataset;
    }
    params.roleId = dataset.id;
    var app_info = $(".app-info");
    $.get(
        '/api/role/apps',
        params,
        function(data){
            app_info.empty();
            var children = [];
            var child = $('<input type="hidden" name="appRoleId" value="' + params.roleId + '" />');
            children.push(child);
            data.forEach(function(it){
                child = factoryAppChild(it);
                children.push(child);
            });
            app_info.html(children);
        }
    );
}


function factoryAppChild(app) {
    if(app.isGrant){
        return $('<div class="checkbox">' +
            '<label>' +
            '<input type="checkbox" id="blankCheckbox" name="appIds" value="' +
            app.appId + '" aria-label="' +
            app.name + '">' +
            app.name + '</label> ' +
            '</div>');
    } else {
        return $('<div class="checkbox">' +
            '<label>' +
            '<input type="checkbox" id="blankCheckbox" name="appIds" value="' +
            app.appId + '" aria-label="' +
            app.name + '" checked="checked">' +
            app.name + '</label> ' +
            '</div>');
    }
}

$(".app-grant-footer").on("click",'.js-grant-app', function(){grantApp(this)});

function grantApp() {
    var roleId = $("input[name = 'appRoleId']").val();
    var apps = $("input[name = 'appIds']");
    var paramStr = 'roleId=' + roleId;
    for(var i = 0; i < apps.length; i++){
        if($(apps[i]).is(':checked')){
            paramStr = paramStr + "&appIds=" + $(apps[i]).val();
        }
    }

    if(paramStr.indexOf('appIds') == -1){
        paramStr = paramStr + "&appIds=-1";
    }

    $.ajax({
        url:'/api/role/app?' + paramStr,
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

