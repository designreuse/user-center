<#import "../../common/pagination-tag.ftl" as pagination/>
<@override name="body">
<div class="container-fluid">

    <div class="panel panel-default">
        <div class="panel-heading">
            <ol class="breadcrumb transparent">
                <li><a href="/cms/apps">应用管理</a></li>
                <li class="active">权限列表</li>
            </ol>
        </div>
        <table class="table">
            <thead>
            <tr><th>ID</th><th>名称</th><th>是否需要权限</th><th>权限key</th><th>url</th><th>操作</th></tr>
            </thead>
            <tbody class="js-app-info-content">
            </tbody>
        </table>
        <nav class="pagination-params"
             data-pageno="${pageNo}"
             data-pagesize="${pageSize}"
             data-appid="${appId}"
             data-url="/api/cms/perms"
             data-total="-1"
        >
            <ul class="pagination">
            </ul>
        </nav>
    </div>
</div>

<script type="application/javascript" src="/static/js/auth/authority.js"></script>
<script type="application/javascript" src="/static/js/common/pagination.js"></script>
</@override>
<@extends name="/common/base.ftl"/>

</html>