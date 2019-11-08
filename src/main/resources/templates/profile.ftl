<#import "parts/base.ftl" as b>
<@b.page>
    <div class="container mt-15">
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
                    <p><a class="btn btn-primary" href="/profile/#{user.id}/requests">Запросы</a></p>
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

                <div class="mb-10">
                    <a style="font-size: large" href="/edit_profile/">Редактировать профиль</a>
                </div>


            </div>
            <div class="col"></div>
        </div>





    </div class="mb-10">
</@b.page>