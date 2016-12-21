<#import "../../common/pagination-tag.ftl" as pagination/>
<@override name="body">
<div class="container-fluid">

    <div class="panel panel-default">
        <div class="panel-heading">
            <ol class="breadcrumb transparent">
                <li><a href="/cms/users">用户管理</a></li>
                <li class="active">用户列表</li>
            </ol>
        </div>
        <table class="table">
            <thead>
            <tr><th>ID</th><th>用户名</th><th>登录名</th><th class="operation">操作</th></tr>
            </thead>
            <tbody>
                <#list users.datas as user>
                <tr><td>${user.id}</td><td>${user.name}</td><td>${user.username}</td></tr>
                </#list>
            </tbody>
        </table>
        <@pagination.page pageNo=users.pageNo pageSize=users.pageSize total=users.total url="/cms/users" />
    </div>
</div>
</@override>
<@extends name="/common/base.ftl"/>

</html>