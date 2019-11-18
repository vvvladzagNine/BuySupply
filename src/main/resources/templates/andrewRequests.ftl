<#import "parts/andrewBase.ftl" as b>
<@b.page>

    <div class="container mt-15">
        <div class="row">
            <div class="col"></div>
            <div class="col-8"><h2 class="text-center mt-10">Запросы</h2></div>
            <div class="col"></div>
        </div>



        <div class="row">
            <div class="col"></div>
            <div class="col-8">
                <#if requests??>

                    <ul>
                        <#list requests as request>
                            <li>
                                <p>${request.requester.name}: ${request.message}</p>
                            </li>
                        </#list>
                    </ul>
                </#if>
            </div>
            <div class="col"></div>
        </div>

    </div>

</@b.page>