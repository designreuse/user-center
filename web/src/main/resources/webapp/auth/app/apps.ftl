<#import "../../common/pagination-js.ftl" as pagination/>

<@override name="body">
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
            <ol class="breadcrumb transparent">
                <li><a href="/cms/apps">应用管理</a></li>
                <li class="active">应用列表</li>
            </ol>
        </div>
        <table class="table">
            <thead>
            <tr><th>应用ID</th><th>应用名称</th><th>应用Key</th><th>应用域名</th><th>操作</th></tr>
            </thead>
            <tbody class="js-app-info-content">
            </tbody>
        </table>
        <@pagination.pagination_js
        pageNo=pageNo
        pageSize=pageSize
        url="/api/cms/apps"
        param_map=params?if_exists
        page_js="/static/js/auth/app.js" />
    </div>
</div>
</@override>
<@extends name="/common/base.ftl"/>