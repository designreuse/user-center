<#macro page pageNo pageSize url total params="?">
    <#assign pageCount=((total + pageSize - 1) / pageSize)?int>
    <#if total==0><#return/></#if>

    <nav>
      <ul class="pagination">
          <li <#if pageNo == 1> class="disabled" </#if>><#if pageNo == 1> <span>&laquo;</span> <#else> <a href="${url}${params}&pageNo=${pageNo-1}&pageSize=${pageSize}">&laquo;</a> </#if></li>
          <#list 1..pageCount as index>
            <li <#if pageNo == index> class="active" </#if>><#if pageNo == index> <span>${index}</span> <#else> <a href="${url}${params}&pageNo=${index}&pageSize=${pageSize}">${index}</a> </#if></li>
          </#list>
          <li <#if pageNo == pageCount> class="disabled" </#if>><#if pageNo == pageCount> <span>&raquo;</span> <#else> <a href="${url}${params}&pageNo=${pageNo+1}&pageSize=${pageSize}">&raquo;</a> </#if></li>
        </ul>
    </nav>

</#macro>