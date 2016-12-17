<#macro page pageNo pageSize url total params="?" event="js-default-event" >
    <#assign pageCount=((total + pageSize - 1) / pageSize)?int>
    <#if total==0><#return/></#if>

<nav>
    <ul class="pagination">
        <li <#if pageNo == 1> class="disabled" </#if>>
            <#if pageNo == 1>
                <span>&laquo;</span>
            <#else>
                <a class="${event}"
                   data-pageNo="${pageNo-1}"
                   data-pageSize="${pageSize}"
                   data-params="${params}"
                   data-url="${url}"
                   href="javascript:void(0)"
                >&laquo;</a>
            </#if>
        </li>
        <#list 1..pageCount as index>
            <li <#if pageNo == index> class="active" </#if>>
                <#if pageNo == index>
                    <span>${index}</span>
                <#else>
                    <a class="${event}"
                       data-pageNo="${index}"
                       data-pageSize="${pageSize}"
                       data-params="${params}"
                       data-url="${url}"
                       href="javascript:void(0)"
                    >${index}</a>
                </#if>
            </li>
        </#list>
        <li <#if pageNo == pageCount> class="disabled" </#if>>
            <#if pageNo == pageCount>
                <span>&raquo;</span>
            <#else>
                <a class="${event}"
                   data-pageNo="${pageNo+1}"
                   data-pageSize="${pageSize}"
                   data-params="${params}"
                   data-url="${url}"
                   href="javascript:void(0)"
                >&raquo;</a>
            </#if>
        </li>
    </ul>
</nav>

</#macro>