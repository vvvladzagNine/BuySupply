<#import "parts/andrewBase.ftl" as b>
<@b.page>
    <#if cats??>
        <h3>Категории товаров</h3>
        <ul>

            <hr/>
            <#list cats as cat >
                <li>
                    <a href="/admin/categories/#{cat.id}"> ${cat.name} </a> ${cat.unit} ${cat.typeOfBatch}

                    <form method="post">
                        <input type="hidden" name="id" value="#{cat.id}" />
                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                        <button  role="button">удалить</button>
                    </form>
                </li>
            </#list>
        </ul>
        <a href="/admin/add_category" class="btn btn-primary">Добавить категорию</a>

    </#if>
</@b.page>