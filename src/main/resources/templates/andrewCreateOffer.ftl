<#import "parts/andrewBase.ftl" as b>
<@b.page>

    <div class="offerCreation">

        <h1>Создание вашего предложения</h1>
        <form method="post" >
            <div class="form-group">
                <label class="col-sm-2 col-form-label mt-3">Buy Or Supply</label>
                <div class="form-check mt-1 col-sm-6">
                    <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="B"<#if offer??><#if offer.buyOffer>checked</#if></#if>>
                    <label class="form-check-label" for="exampleRadios1">
                        <i class="fas fa-cart-arrow-down "></i>
                    </label>
                </div>
                <div class="form-check col-sm-6">
                    <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="S" <#if offer??><#if !offer.buyOffer>checked</#if><#else >checked</#if>>
                    <label class="form-check-label" for="exampleRadios2">
                        <i class="fas fa-store"></i>
                    </label>
                </div>
                <label class="col-sm-2 col-form-label mt-3">Description</label>
                <div class="col-sm-6">
                    <input type="text" name="description" value="<#if offer??>${offer.description}</#if>"
                           class="form-control"
                           placeholder="Delicious cake with cream and cranberries" />
                </div>
                <label class="col-sm-2 col-form-label mt-3">Cost in $</label>
                <div class="col-sm-6">
                    <input type="text" name="cost" value="<#if offer??>${offer.cost}</#if>"
                           class="form-control"
                           placeholder="50" />
                </div>
                <label class="col-sm-2 col-form-label">Amount in Batch</label>
                <div class="col-sm-6">
                    <input type="text" name="amount" value="<#if offer??>${offer.amount}<#else></#if>"
                           class="form-control"
                           placeholder="100" />
                </div>
                <label class="col-sm-2 col-form-label">Category</label>
                <div class="col-sm-6">
                    <select class="form-control" id="exampleFormControlSelect1" name="category">
                        <#list categories as cat>
                            <option>${cat.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="col-sm-6"><input type="submit" class="btn btn-primary btn-bg" value="Save"/></div>
        </form>
    </div>

</@b.page>