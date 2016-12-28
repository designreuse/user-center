<#import "../../common/pagination-js.ftl" as pagination/>
<#import "./template/authority-template.ftl" as authority/>
<@override name="body">
<div class="container-fluid">

    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col-md-8">
                    <ol class="breadcrumb transparent">
                        <li><a href="/cms/index">后台首页</a></li>
                        <li><a href="/cms/apps">应用管理</a></li>
                        <li class="active">权限管理</li>
                    </ol>
                </div>
                <div class="col-md-4 text-right">
                    <button type="button" class="btn btn-primary btn-sm js-add-form" data-toggle="modal" data-target="#authorityModal">添加</button>
                </div>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr><th>ID</th><th>名称</th><th>是否需要权限</th><th>权限key</th><th>url</th><th class="operation">操作</th></tr>
            </thead>
            <tbody class="js-info-content">
            </tbody>
        </table>

        <@authority.authority_from currentAppId=params.appId />

        <@pagination.pagination_js
        pageNo=pageNo
        pageSize=pageSize
        url="/api/cms/perms"
        param_map=params?if_exists
        page_js="/static/js/auth/authority.js" />
    </div>
</div>

</@override>
<@extends name="/common/base.ftl"/>

</html>