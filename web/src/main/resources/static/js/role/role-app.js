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
        '/api/cms/role/appList',
        params,
        function(data){
            app_info.empty();
            var children = [];
            data.forEach(function(it){
                var child = factoryAppChild(it);
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
            '<input type="checkbox" id="blankCheckbox" name="app" value="' +
            app.appId + '" aria-label="' +
            app.name + '">' +
            app.name + '</label> ' +
            '</div>');
    } else {
        return $('<div class="checkbox">' +
            '<label>' +
            '<input type="checkbox" id="blankCheckbox" name="app" value="' +
            app.appId + '" aria-label="' +
            app.name + '" checked="checked">' +
            app.name + '</label> ' +
            '</div>');
    }
}

$(".app-grant-footer").on("click",'.js-grant-app', function(){grantApp()});

function grantApp() {
    alert("");
}

