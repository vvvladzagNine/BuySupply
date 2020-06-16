<#include "security.ftl">
<div class="header-logo">
    <a  style="font-size: 25px; color: #e9edea;" class="navbar-brand" href="/">Buy&Supply</a>
</div>

<div class="header-buttons">
    <#if user?? || me??>
        <a class="btn btn-primary" href="/">Выход</a>
        <a class="btn btn-primary ml-2" href="/edit_profile/">Редактировать профиль</a>
        <#else>
            <a  class="btn btn-primary" href="/login">Вход</a>
            <a  class="btn btn-primary ml-2" href="/registration" >Регистрация</a>
    </#if>


</div>