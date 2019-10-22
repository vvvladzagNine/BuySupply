<#import "parts/base.ftl" as b>
<@b.page>
    <div class="container mt-15">
        <div class="row">
            <div class="col"></div>
            <div class="col-8">
                <h2 class="text-center mt-10">Профиль ${user.name}</h2>
                <p>Город: ${user.city}</p>
                <p>Email: ${user.email}</p>

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
                    <a style="font-size: large" href="/profile/#{user.id}/requests">Запросы</a>
                </#if>

            </div>
            <div class="col"></div>
        </div>





    </div>
</@b.page>