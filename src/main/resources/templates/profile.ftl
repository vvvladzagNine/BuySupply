<#import "parts/base.ftl" as b>
<@b.page>
    <div class="container mt-15" xmlns="http://www.w3.org/1999/html">
        <div class="row">
            <div class="col"></div>
            <div class="col-8">
                <h2 class="text-center mt-10">${user.name}</h2>


            </div>
            <div class="col"></div>
        </div>
        <div class="row">
            <div class="col"></div>
            <div class="col">
                <#if user.ava??>
                    <img class="myImg"src="/img/${user.ava}" class="rounded float-left myImg">
                </#if>
            </div>
            <div class="col"></div>
        </div>
        <div class="row mt-5">
            <div class="col"></div>
            <div class="col-8">
                <div class="alert alert-secondary" role="alert">
                    <p>Город: ${user.city}</p>
                </div>
                <div class="alert alert-secondary" role="alert">
                    <p>Email: ${user.email}</p>
                </div>


                <#if isHome>
                    <p><a  href="/profile/#{user.id}/requests">Запросы</a></p>
                </#if>

            </div>
            <div class="col"></div>
        </div>

        <div class="row">
            <div class="col"></div>
            <div class="col-8">
                <#if offers??>
                    <h2>Предложения от ${user.name}</h2>
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
            <div class="col"></div>
        </div>






        <div class="row">
            <div class="col"></div>
            <div class="col-8">
                <#if estimates??>
                    <h2>Отзывы</h2>
                    <ul>
                        <#list estimates as est>
                            <li>
                                <p><span style="font-weight: bold">${est.estimator.name}: </span>${est.comment} | ${est.stars}/5</p>

                            </li>
                        </#list>
                    </ul>
                </#if>



                <#if isHome>
                <div class="mb-10">
                    <a style="font-size: large" href="/edit_profile/">Редактировать профиль</a>
                </div>
                </#if>

                <#if !isHome>
                    <h4>Ваш отзыв</h4>
                <form method="post" >
                    <div class="form-group">

                        <label class="col-sm-2 col-form-label">Stars</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="exampleFormControlSelect1" name="stars">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>

                            </select>
                        </div>

                        <label class="col-sm-2 col-form-label mt-3">Comment</label>
                        <div class="col-sm-6">
                            <textarea rows="3" name="comment"
                                   class="form-control">
                            </textarea>
                        </div>

                    </div>
                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                    <div class="col-sm-6"><input type="submit" class="btn btn-primary btn-bg" value="Save"/></div>
                </form>
                </#if>
            </div>
            <div class="col"></div>
        </div>
    </div class="mb-10">
</@b.page>