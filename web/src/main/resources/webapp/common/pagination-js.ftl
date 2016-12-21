<#macro pagination_js pageNo pageSize url param_map page_js >

<nav class="pagination-params"
     data-pageno="${pageNo}"
     data-pagesize="${pageSize}"
     <#if param_map??>
         <#list param_map?keys as key>
             data-${key}="${param_map[key]}"
         </#list>
     </#if>
     data-url="${url}"
     data-total="-1"
>
    <ul class="pagination">
    </ul>
</nav>

<script type="application/javascript" src="${page_js}"></script>
<script type="application/javascript" src="/static/js/common/pagination.js"></script>

</#macro>