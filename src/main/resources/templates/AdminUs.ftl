<#import "parts/andrewBase.ftl" as b>
<@b.page>
    <h1 class="text-center mt-10">Пользователи</h1>


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
                                    <span class="text-primary">Имя</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Дата регистрации</span>
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
                <#if users??>
                    <ul class="list-group">
                        <#list users as user>


                            <li class="list-group-item ">
                                <div class="container">
                                    <div class="row">
                                        <div class="col">
                                            <a href="/profile/#{user.getId()}">${user.name}</a>
                                        </div>
                                        <div class="col">
                                            ${user.registered}
                                        </div>
                                        <div class="col">
                                            <#if user.name!="Admin">
                                                <#if user.enabled>
                                                <form method="post">
                                                    <input type="hidden" name="id" value="#{user.id}" />
                                                    <input type="hidden" name="act" value="ban" />
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                                    <button class="btn btn-danger ml-3" role="button">BAN</button>
                                                </form>
                                            <#else>
                                                <form method="post">
                                                    <input type="hidden" name="id" value="#{user.id}" />
                                                    <input type="hidden" name="act" value="unban" />
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                                    <button class="btn btn-success ml-3" role="button">unban</button>
                                                </form>
                                            </#if>
                                            </#if>

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