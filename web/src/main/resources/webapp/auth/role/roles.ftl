<#import "../../common/pagination-js.ftl" as pagination/>
<#import "./template/role-template.ftl" as roleForm/>
<#import "./template/app-template.ftl" as appForm/>
<#import "./template/authority-template.ftl" as authorityForm/>
<@override name="body">
<div class="container-fluid">

    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col-md-8">
                    <ol class="breadcrumb transparent">
                        <li><a href="/cms/index">后台首页</a></li>
                        <li class="active">角色管理</li>
                    </ol>
                </div>
                <div class="col-md-4 text-right">
                    <button type="button" class="btn btn-primary js-add-form btn-sm" data-toggle="modal" data-target="#roleModal">添加</button>
                </div>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr><th>ID</th><th>角色名</th><th class="operation">操作</th></tr>
            </thead>
            <tbody class="js-info-content">
            </tbody>
        </table>

        <script src="/static/js/serialize.js"></script>

        <@roleForm.role_from/>
        <@appForm.role_app_from/>
        <@authorityForm.role_authority_from/>

        <@pagination.pagination_js
        pageNo=pageNo
        pageSize=pageSize
        url="/api/cms/roles"
        param_map=params?if_exists
        page_js="/static/js/role/role.js" />
    </div>
</div>
</@override>
<@extends name="/common/base.ftl"/>