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
            <#if avg??>
                <p>${avg}</p>
            <#else>
                Отзывов нет
            </#if>

        </div>

        <div class="info">
            <p>Город: ${user.city}<br>
                Дата регистрации: 17.07.2019<br></p>
        </div>
    </div>

    <div>
        <#if isHome>
            <div class="mb-10">
                <a style="font-size: large" href="/edit_profile/">Редактировать профиль</a>
            </div>
            <div>
                <#if user.name="Admin">
                    <a href="/admin">Панель администратора</a>
                </#if>
            </div>

        </#if>
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
                                    <a href="profile/#{est.estimator.getId()}">
                                        <img src="/img/${est.estimator.ava}" class="rounded float-left estimate-image">
                                    </a>
                                </#if>
                                    <div class="name-rank">
                                        <div><p><a href="profile/#{est.estimator.getId()}">${est.estimator.name}</a></p></div>
                                        <div>
                                            <#if (est.stars >= 1)><span class="fa fa-star checked"></span> <#else> <span class="fa fa-star"></span> </#if>
                                            <#if (est.stars >= 2)><span class="fa fa-star checked"></span> <#else> <span class="fa fa-star"></span> </#if>
                                            <#if (est.stars >= 3)><span class="fa fa-star checked"></span> <#else> <span class="fa fa-star"></span> </#if>
                                            <#if (est.stars >= 4)><span class="fa fa-star checked"></span> <#else> <span class="fa fa-star"></span> </#if>
                                            <#if (est.stars == 5)><span class="fa fa-star checked"></span> <#else> <span class="fa fa-star"></span> </#if>
                                        </div>
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



        <#if isHome>
            <div class="mb-10">
                <a style="font-size: large" href="/edit_profile/">Редактировать профиль</a>
            </div>
        </#if>

        <#if !isHome>

            <#if estAv>
                <h4 style="margin: auto">Ваш отзыв</h4>
                <form method="post"  class="estimate">
                    <div class="form-group">

                        <label class="col-sm-4 col-form-label">Количество звезд</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="exampleFormControlSelect1" name="stars">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>

                            </select>
                        </div>

                        <label class="col-sm-2 col-form-label mt-3">Комментарий</label>
                        <div class="col-sm-6">
                            <textarea rows="3" name="comment"
                                      class="form-control">
                            </textarea>
                        </div>

                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="col-sm-6"><input type="submit" class="btn btn-primary btn-bg" value="Save"/></div>
                </form>
            <#else>
                <h4>Оставить отзыв можно только после сотрудничества</h4>
            </#if>
        </#if>
    </div>

</@b.page>