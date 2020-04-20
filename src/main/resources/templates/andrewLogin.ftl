<#import "parts/andrewLogReg.ftl" as lg>
<@lg.page>

    <script type="text/javascript" src="static/js/login.js" defer></script>

    <div class="logreg" >
        <h1>Вход</h1>
        <form style="max-width: 400px" action="login" method="post">
            <div  class="form-group">
                <label for="exampleInputName1"> Имя пользователя : </label>
                <input type="text" class="form-control" id="exampleInputName1" aria-describedby="emailHelp" placeholder="E-mail" name="username"/>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1"> Пароль: </label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="password"/>
            </div>

            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input class="btn btn-dark" type="submit" value="Sign In" id="submit_but"/>
            </div>
        </form>
        <div class="mt-3">
            <#if error??>
                <h4 style="color:red">Неверное имя пользователя или пароль</h4>
            </#if>
            <#if sucreg??>
                <h5 style="color:#21d90b">Регистрацаия прошла спешно, используйте свои данные для входа</h5>
            </#if>
            <h4 style="visibility: hidden; color: red" id="js_bad_login">В поле "Имя пользователя" должна быть введена эл. почта (например petrov@mail.ru)</h4>
        </div>

    </div>

</@lg.page>