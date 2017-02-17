<#import "../common/pagination-js.ftl" as pagination/>
<#import "./template/app-template.ftl" as appForm/>

<@override name="body">
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="row">
                <div class="col-md-8">
                    <ol class="breadcrumb transparent">
                        <li><a href="/index">首页</a></li>
                        <li class="active">应用管理</li>
                    </ol>
                </div>
                <div class="col-md-4 text-right">
                    <button type="button" class="btn btn-primary btn-sm js-add-form" data-toggle="modal" data-target="#appModal">添加</button>
                </div>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr><th>应用ID</th><th>应用名称</th><th>应用Key</th><th>应用密钥</th><th>应用域名</th><th class="operation">操作</th></tr>
            </thead>
            <tbody class="js-info-content">
            </tbody>
        </table>
        <@appForm.app_from/>

        <@pagination.pagination_js
        pageNo=pageNo
        pageSize=pageSize
        url="/api/apps"
        param_map=params?if_exists
        page_js="/static/js/auth/app.js" />
    </div>
</div>
</@override>
<@extends name="/common/base.ftl"/>