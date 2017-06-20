<#import "../common/pagination-js.ftl" as pagination/>
<#import "./template/user-template.ftl" as userForm/>
<#import "./template/role-template.ftl" as roleForm/>
<@override name="body">
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col-md-8">
                    <ol class="breadcrumb transparent">
                        <li><a href="/index">后台首页</a></li>
                        <li class="active">用户管理</li>
                    </ol>
                </div>
                <div class="col-md-4 text-right">
                    <button type="button" class="btn btn-primary js-add-form btn-sm" data-toggle="modal" data-target="#userModal">添加</button>
                </div>
            </div>
        </div>
        <table class="table">
            <thead>
                <tr><th>ID</th><th>用户名</th><th>登录名</th><th class="operation">操作</th></tr>
            </thead>
            <tbody class="js-info-content">
            </tbody>
        </table>
        <script src="/static/js/serialize.js"></script>
        <@userForm.user_from/>
        <@roleForm.user_role_from/>


        <@pagination.pagination_js
        pageNo=pageNo
        pageSize=pageSize
        url="/api/users"
        param_map=params?if_exists
        page_js="/static/js/user/user.js" />
    </div>
</div>
</@override>
<@extends name="/common/base.ftl"/>