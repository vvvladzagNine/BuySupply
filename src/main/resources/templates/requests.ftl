<#import "parts/andrewBase.ftl" as b>
<@b.page>
    <div class="container mt-15">
        <div class="row">
            <div class="col"></div>
            <div class="col-8"><h2 class="text-center mt-10">Запросы</h2></div>
            <div class="col"></div>
        </div>
    </div>

                <#if requests??>
    <div class="container">
                    <ul>
                        <#list requests as request>

                            <li>
                                <div class="row">
                                <div class="col-7">
                                        <p>${request.requester.name}: ${request.message}</p>
                                </div>
                                    <#if !responsed??>
                                        <#if request.offer.offerer.getId() == me.id>
                                            <div class="col-1">
                                                <form method="post">
                                                    <input type="hidden" name="requestId" value="#{request.id}" />
                                                    <input type="hidden" name="act" value="approve" />
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                                    <button class="btn btn-success ml-3" role="button">OK</button>
                                                </form>
                                            </div>
                                            <div class="col-1">
                                                <form method="post">
                                                    <input type="hidden" name="requestId" value="#{request.id}" />
                                                    <input type="hidden" name="act" value="reject" />
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                                    <button class="btn btn-danger ml-3" role="button">Х</button>
                                                </form>
                                            </div>

                                        </#if>
                                    <#else>
                                        <div class="col-1">
                                            <form method="post">
                                                <input type="hidden" name="requestId" value="#{request.id}" />
                                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                                <button class="btn btn-danger ml-3" role="button">Отменить</button>
                                            </form>
                                        </div>
                                    </#if>

                                </div>
                            </li>

                        </#list>
                    </ul>
    </div>
                </#if>






</@b.page>