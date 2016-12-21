<#import "../../common/pagination-tag.ftl" as pagination/>

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
        <nav class="pagination-params"
             data-pageno="${pageNo}"
             data-pagesize="${pageSize}"
             data-url="/api/cms/apps"
             data-total="-1"
        >
            <ul class="pagination">
            </ul>
        </nav>
    </div>
</div>
<script type="application/javascript" src="/static/js/auth/app.js"></script>
<script type="application/javascript" src="/static/js/common/pagination.js"></script>
</@override>
<@extends name="/common/base.ftl"/>