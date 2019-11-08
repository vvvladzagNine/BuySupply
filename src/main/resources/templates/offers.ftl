<#import "parts/base.ftl" as b>
<@b.page>
    <div class="container mt-15">
        <div class="row">
            <div class="col"></div>
            <div class="col-8"><h2 class="text-center mt-10">Рынок предложений</h2></div>
            <div class="col"></div>
        </div>






        <div class="row mb-3">
            <div class="col"></div>
            <div class="col-12">

                <div>

                </div>
                <ul class="list-group">
                    <li class="list-group-item ">
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <span class="text-primary">Description</span>
                                </div>
                                <div class="col">
                                    <span class="text-dark">Cost</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Amount in batch</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Category</span>
                                </div>
                                <div class="col">
                                    Buy or supply
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Date</span>
                                </div>
                            </div>
                        </div>

                    </li>
                    <ul/>


            </div>
            <div class="col"></div>
        </div>






    <div class="row">
            <div class="col"></div>
            <div class="col-12">
                <#if offers??>
                <ul class="list-group">
                <#list offers as offer>

                    <li class="list-group-item <#if offer.buyOffer>list-group-item-warning<#else>list-group-item-info</#if>">
                        <div class="container">
                            <div class="row">
                                <div class="col">
                                    <span class="text-primary">${offer.description}</span>
                                </div>
                                <div class="col">
                                    <span class="text-dark">${offer.cost} $</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">${offer.amount} in ${offer.category.typeOfBatch}</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">${offer.category.name}</span>
                                </div>
                                <div class="col">
                                    <#if offer.buyOffer>Buy<#else>Supply</#if>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">${offer.dateTime.toLocalDate()}</span>
                                </div>
                            </div>
                        </div>

                    </li>
                </#list>
                </ul>
                </#if>
            </div>
            <div class="col"></div>
        </div>

    </div>
</@b.page>