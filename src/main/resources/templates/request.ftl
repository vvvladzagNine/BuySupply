<#import "parts/andrewBase.ftl" as b>
<@b.page>
    <div class="container mt-15">
        <div class="row">
            <div class="col"></div>
            <div class="col-8"><h2 class="text-center mt-10"> Мой запрос</h2></div>
            <div class="col"></div>
        </div>
    </div>


        <div class="container">
            <#if request??>
                <div class="row">
                    <div class="col-7">
                        <p>${request.requester.name}: ${request.message}</p>
                    </div>

                    <div class="col-1">
                        <form method="post">
                            <input type="hidden" name="requestId" value="#{request.id}" />
                            <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            <button class="btn btn-danger ml-3" role="button">Отозвать</button>
                        </form>
                    </div>
                </div>
                <#else>
                    <h3 class="alert alert-warning">Запрос отозван</h3>
            </#if>
        </div>






</@b.page>