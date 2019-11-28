<#import "parts/base.ftl" as b>
<@b.page>


    <div class="container mt-15">
        <div class="row">
            <div class="col"></div>
            <div class="col-8">

            <#if offer.buyOffer>
                <div class="alert alert-warning" role="alert">
                    <h2 class="text-center mt-10">Куплю</h2>
            </div>
            <#else>
            <div class="alert alert-info" role="alert">
                <h2 class="text-center mt-10">Продам</h2>
            </div>

            </#if>
            </div>
            <div class="col">
                <#if offer.offerer.id==me.id>
                    <form method="post">
                        <input type="hidden" name="offerId" value="#{offer.id}" />
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button class="btn btn-secondary ml-3" role="button">Х</button>
                    </form>
                </#if>
            </div>
        </div>
        <ul class="list-group">
            <li class="list-group-item">Описание: ${offer.description}</li>
            <li class="list-group-item">Цена: ${offer.cost}</li>
            <li class="list-group-item">Категория: ${offer.category.name}</li>
            <li class="list-group-item">Количество: ${offer.amount} ${offer.category.unit} in ${offer.category.typeOfBatch} </li>
            <li class="list-group-item">Предлагающий: <a href="/profile/#{offer.offerer.id}">${offer.offerer.name}</a></li>
            <li class="list-group-item">Дата публикации: ${offer.dateTime.toLocalDate()}</li>
        </ul>
        <div class="row mt-4">
            <div class="col"></div>
            <div class="col-12">

                <#if !(offer.offerer.id==me.id)>
                    <#if !req??>
                        <form method="post">
                            <input type="hidden" name="offererId" value="#{me.id}" />
                            <label class="col-sm-2 col-form-label mt-3">Message</label>
                            <div class="col-sm-6 mb-2">
                            <textarea rows="3" name="message"
                                      class="form-control">
                            </textarea>
                            </div>
                            <input type="hidden" name="offerId" value="#{offer.id}" />
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button class="btn btn-secondary ml-3" role="button">Откликнуться</button>
                        </form>
                    <#else>
                        <h3 class="alert alert-success">Вы уже отправили запрос на это <a href="/request/#{req.id}"> предложение</a> </h3>
                    </#if>

                <#else>
                    <a href="/home/requests/#{offer.id}" class="btn btn-primary">Принятые запросы на это предложение</a>
                    <a href="/offer/edit/#{offer.id}">Редактировать</a>
                </#if>

            </div>
            <div class="col"></div>
        </div>

    </div>

</@b.page>