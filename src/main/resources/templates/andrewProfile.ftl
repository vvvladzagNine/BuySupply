<#import "parts/andrewBase.ftl" as b>
<@b.page>

    <div class="personal-info">
        <div class="personal-photo">
            <#if user.ava??>
                <img class="myImg"src="/img/${user.ava}" class="rounded float-left myImg">
            </#if>
        </div>
        <div class="name-mail">
            <h2>${user.name}</h2>
            <p>${user.email}</p>
        </div>

        <div class="info">
            <p>Город: ${user.city}<br>
                Дата регистрации: 17.07.2019<br></p>
        </div>
    </div>

    <div class="offers">
        <#if offers??>
            <h2>Предложения:</h2>
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
                                <#if offer.id == me.id>
                                    <div class="col">
                                        <form method="post">
                                            <input type="hidden" name="id" value="${offer.id}" />
                                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                            <button class="btn btn-secondary ml-3" role="button">Х</button>
                                        </form>
                                    </div>
                                </#if>

                            </div>
                        </div>
                    </li>
                </#list>
            </ul>
        </#if>
    </div>

    <div class="estimates">

        <#if estimates??>
            <h2>Отзывы:</h2>

<#--            <ul>-->
<#--                <#list estimates as est>-->
<#--                    <li>-->
<#--                        <p><span style="font-weight: bold">${est.estimator.name}: </span>${est.comment} | ${est.stars}/5</p>-->

<#--                    </li>-->
<#--                </#list>-->
<#--            </ul>-->

            <#list estimates as est>
            <li>
                    <div class="estimate">
                        <div class="estimate-header">

                            <div class="estimator-info">
                                <#if est.estimator.ava??>
                                    <img src="/img/${est.estimator.ava}" class="rounded float-left estimate-image">
                                </#if>
                                <div class="name-rank">
                                    <div><p>${est.estimator.name}</p></div>
                                    <div><p>Звезды</p></div>
                                </div>
                            </div>

                            <div>
                                <p>12.09.2018</p>
                            </div>
                        </div>

                        <div class="estimate-body">
                            <span>${est.comment}</span>
                        </div>
                    </div>
            </li>
            </#list>
        </#if>
    </div>

</@b.page>