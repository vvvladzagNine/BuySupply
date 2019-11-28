<#import "parts/andrewBase.ftl" as b>
<@b.page>
    <h1 class="text-center mt-10">Рынок предложений</h1>
    <div class="container rounded">
        <br/>
        <div class="row mt-3">
            <div class="col"></div>
            <div class="col"><h4 class="text-center">Фильтер</h4></div>
            <div class="col"></div>
        </div>
        <div class="row mt-4">
            <div class="col-4">
                <form method="get">
                    <div class="form-group">
                        <label for="exampleFormControlSelect1">Тип товара</label>
                        <select class="form-control" id="exampleFormControlSelect1" name="categoryName">
                            <option></option>
                            <#list types as g>
                                <option>${g}</option>
                            </#list>

                        </select>
                        <div class="mt-3"><label for="exampleFormControlSelect1">Описание</label></div>
                        <div class="row ml-1 mr-1">
                            <input type="text" name="fragment" value=""
                                   class="form-control"
                                   placeholder="" />
                        </div>

                    </div>

            </div>
            <div class="col-4 border-left border-right border-white">
                <label for="exampleFormControlSelect1">Цена за одну еденицу</label>

                <div class="row">
                    <div class="col">
                        <input type="text" name="pricePerUnitFrom" value=""
                               class="form-control"
                               placeholder="От" />
                    </div>
                    <div class="col">
                        <input type="text" name="pricePerUnitTo" value=""
                               class="form-control"
                               placeholder="До" />
                    </div>
                </div>

                <div class="mt-3"><label for="exampleFormControlSelect1 ">Тип предложения</label></div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1" checked value="buy" name="buy">
                    <label class="form-check-label" for="inlineCheckbox1"><i class="fas fa-cart-arrow-down"></i></label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="supply" name="supply">
                    <label class="form-check-label" for="inlineCheckbox2"><i class="fas fa-store"></i></label>
                </div>

            </div>
            <div class="col-4">

                <label for="exampleFormControlSelect1">Название поставщика</label>
                <div class="row ml-1 mr-1">
                    <input type="text" name="offererName" value=""
                           class="form-control"
                           placeholder="" />
                </div>
            </div>
        </div>
        <div class="row mt-3 mb-3">

            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="col"><input type="submit" class="btn btn-dark btn-lg btn-block" value="Поиск"/></div>

        </div>
        <div class="row"></div>
        </form>

    </div>

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
                                    <span class="text-primary">Описание</span>
                                </div>
                                <div class="col">
                                    <span class="text-dark">Цена</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Количество</span>
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Категория</span>
                                </div>
                                <div class="col">
                                    Куплю/Продам
                                </div>
                                <div class="col">
                                    <span class="text-secondary">Дата</span>
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
                <#if offers??>
                    <ul class="list-group">
                        <#list offers as offer>

                            <li class="list-group-item <#if offer.buyOffer>list-group-item-warning<#else>list-group-item-info</#if>">
                                <div class="container">
                                    <div class="row">
                                        <div class="col">
                                            <a class="text-primary" href="/offer/#{offer.id}">${offer.description}</a>
                                        </div>
                                        <div class="col">
                                            <span class="text-dark">${offer.cost} $</span>
                                        </div>
                                        <div class="col">
                                            <span class="text-secondary">${offer.amount} in ${offer.category.typeOfBatch}</span>
                                        </div>
                                        <div class="col">
                                            <span class="text-secondary">${offer.category.name}</span>
                                        </div>
                                        <div class="col">
                                            <#if offer.buyOffer>Buy<#else>Supply</#if>
                                        </div>
                                        <div class="col">
                                            <span class="text-secondary">${offer.dateTime.toLocalDate()}</span>
                                        </div>

                                        <div class="col">
                                            <#if offer.offerer.id==me.id>
                                                <form method="post">
                                                    <input type="hidden" name="offerId" value="#{offer.id}" />
                                                    <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                                    <button class="btn btn-secondary ml-3" role="button">Х</button>
                                                </form>
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