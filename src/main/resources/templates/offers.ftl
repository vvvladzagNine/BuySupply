<#import "parts/base.ftl" as b>
<@b.page>
    <div class="container mt-15">
        <div class="row">
            <div class="col"></div>
            <div class="col-8"><h2 class="text-center mt-10">Заказы</h2></div>
            <div class="col"></div>
        </div>



    <div class="row">
            <div class="col"></div>
            <div class="col-8">
                <#if offers??>
                <ul>

                </ul>
                <#list offers as offer>
                    <li>
                        ${offer.description}/${offer.cost}/${offer.amount}/${offer.category.name}
                    </li>
                </#list>
                </#if>
            </div>
            <div class="col"></div>
        </div>

    </div>
</@b.page>