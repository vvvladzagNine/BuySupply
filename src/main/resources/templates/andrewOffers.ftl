<#import "parts/andrewBase.ftl" as b>
<#include "parts/security.ftl">
<@b.page>

    <script type="text/javascript" src="static/js/offers.js" defer></script>
    <h1 class="text-center mt-10">Рынок предложений</h1>

    <div class="container rounded">
        <br/>
        <div class="row mt-3">
            <div class="col"></div>
            <div class="col"><h4 class="text-center">Фильтр</h4></div>
            <div class="col"></div>
        </div>
        <div class="row mt-4">
            <div class="col-4">
                <form method="get" id="filter">
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
                                   placeholder=""/>
                        </div>

                    </div>

            </div>
            <div class="col-4 border-left border-right border-white">
                <label for="exampleFormControlSelect1">Цена за одну еденицу $</label>

                <div class="row">
                    <div class="col">
                        <input type="text" name="pricePerUnitFrom" value=""
                               class="form-control"
                               placeholder="От"/>
                    </div>
                    <div class="col">
                        <input type="text" name="pricePerUnitTo" value=""
                               class="form-control"
                               placeholder="До"/>
                    </div>
                </div>

                <div class="mt-3"><label for="exampleFormControlSelect1 ">Тип предложения</label></div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox1" value="buy" name="buy">
                    <label class="form-check-label" for="inlineCheckbox1"><i class="fas fa-cart-arrow-down"></i></label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="checkbox" id="inlineCheckbox2" value="supply" name="supply">
                    <label class="form-check-label" for="inlineCheckbox2"><i class="fas fa-store"></i></label>
                </div>

            </div>
            <div class="col-4">


                <label for="exampleFormControlSelect1 ml-2">Название поставщика</label>
                <div class="row ml-1 mr-1">
                    <input type="text" name="offererName" value=""
                           class="form-control"
                           placeholder="Сбербанк"/>
                </div>


                <label for="exampleFormControlSelect1">Город</label>
                <input type="text" name="city" value=""
                       class="form-control"
                       placeholder="Нур-Султан"/>

            </div>
        </div>
        <div class="row mt-3 mb-3">

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="col"><input type="submit" class="btn btn-dark btn-lg btn-block" value="Поиск"/></div>

        </div>
        <div class="row"></div>
        </form>
        <button class="btn btn-danger" onclick="updateFilteredTable()">ПОИСК</button>


    </div>

    <div class="container mt-17">
        <div class="row">
            <div class="col"></div>
            <div class="col-8"></div>
            <div class="col"></div>
        </div>


        <div class="row">
            <div class="col"></div>
            <div class="col-12">
                <table class="table table-striped" id="datatable">
                    <thead>
                    <tr>
                        <th>Описание</th>
                        <th>Цена</th>
                        <th>Количество</th>
                        <th>Категория</th>
                        <th>Дата</th>
                        <th>Подробнее</th>

                    </tr>
                    </thead>

                </table>
            </div>
            <div class="col"></div>
        </div>

    </div>

</@b.page>