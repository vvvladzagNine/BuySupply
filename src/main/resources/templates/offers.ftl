<#import "parts/base.ftl" as b>
<@b.page>
    <div class="container mt-15">
        <div class="row">
            <div class="col"></div>
            <div class="col-8"><h2 class="text-center mt-10">Рынок предложений</h2></div>
            <div class="col"></div>
        </div>



    <div class="row">
            <div class="col"></div>
            <div class="col-8">
                <#if offers??>
                    описание/цена/количество/категория
                <ul>
                <#list offers as offer>
                    <li>
                        ${offer.description}/${offer.cost}/${offer.amount}/${offer.category.name}<#if offer.buyOffer>/куплю <#else>/продам</#if>
                    </li>
                </#list>
                </ul>
                </#if>
            </div>
            <div class="col"></div>
        </div>

    </div>
</@b.page>