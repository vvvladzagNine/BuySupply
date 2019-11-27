<#import "parts/andrewBase.ftl" as b>
<@b.page>


        <form method="post" >
            <div class="form-group">

                <label class="col-sm-2 col-form-label mt-3">Название</label>
                <div class="col-sm-6">
                    <input type="text" name="name" value="<#if cat??>${cat.name}</#if>"
                           class="form-control"
                            />
                </div>
                <label class="col-sm-2 col-form-label mt-3">Еденица</label>
                <div class="col-sm-6">
                    <input type="text" name="unit" value="<#if cat??>${cat.unit}</#if>"
                           class="form-control"
                            />
                </div>
                <label class="col-sm-2 col-form-label">Тип партии</label>
                <div class="col-sm-6">
                    <input type="text" name="typeOfBatch" value="<#if cat??>${cat.typeOfBatch}<#else></#if>"
                           class="form-control"
                            />
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="col-sm-6">
                <input type="submit" class="btn btn-primary btn-bg" value="Сохранить"/>
            </div>
        </form>




</@b.page>