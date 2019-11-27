<#import "parts/andrewBase.ftl" as b>
<@b.page>
    <h1 class="text-center mt-10">Все предложения</h1>


    <div class="container mt-17">
        <div class="row">
            <div class="col"></div>
            <div class="col-8"></div>
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
                                    <span class="text-primary">Описание</span>
                                </div>
                                <div class="col">
                                    <span class="text-dark">Цена</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Количество</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Категория</span>
                                </div>
                                <div class="col">
                                    Куплю/Продам
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Дата</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Действие</span>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>

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
                                            <a class="text-primary" href="/offer/#{offer.id}">${offer.description}</a>
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

                                        <div class="col">
                                                <form method="post">
                                                    <input type="hidden" name="offerId" value="#{offer.id}" />
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                                    <button class="btn btn-secondary ml-3" role="button">Х</button>
                                                </form>
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