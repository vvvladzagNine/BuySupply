<#import "parts/andrewBase.ftl" as b>
<@b.page>
    <h1 class="text-center mt-10">Все Запросы</h1>


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
                                    <span class="text-primary">Сообщение</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Пользователь</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Предложение</span>
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
                <#if reqs??>
                    <ul class="list-group">
                        <#list reqs as req>

                            <li class="list-group-item ">
                                <div class="container">
                                    <div class="row">
                                        <div class="col">
                                            ${req.message}
                                        </div>
                                        <div class="col">
                                            <a href="/profile/#{req.requester.getId()}">${req.requester.name}</a>
                                        </div>
                                        <div class="col">
                                           <a href="/offer/#{req.offer.getId()}">К предложению</a>
                                        </div>
                                        <div class="col">
                                            <form method="post">
                                                <input type="hidden" name="id" value="#{req.id}" />
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