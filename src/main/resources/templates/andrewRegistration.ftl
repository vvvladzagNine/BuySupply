<#import "parts/andrewLogReg.ftl" as lg>
<@lg.page>

    <script type="text/javascript" src="static/js/registration.js" defer></script>

    <div class="logreg">
        <h1>Регистрация</h1>
        <form style="max-width: 400px" action="/registration" method="post">
            <div  class="form-group">
                <label for="exampleInputName1">Название вашей компании: </label>
                <input type="text" class="form-control" id="exampleInputName1" aria-describedby="emailHelp" placeholder="BuySupplyUser" name="username"/>
            </div>

            <div  class="form-group">
                <label for="exampleInputName3">Почта: </label>
                <input type="email" class="form-control" id="exampleInputName3" aria-describedby="emailHelp" placeholder="example@ex.com" name="email"/>
            </div>

            <div  class="form-group">
                <label for="exampleInputName4">Город: </label>
                <input type="text" class="form-control" id="exampleInputName4" aria-describedby="emailHelp" placeholder="City" name="city"/>
            </div>

            <div class="form-group">
                <label for="exampleInputPassword1">Пароль: </label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password"/>
            </div>

            <div class="form-group">
                <label for="exampleInputPassword2">Еще раз паролик: </label>
                <input type="password" class="form-control" id="exampleInputPassword2" placeholder="Password"/>
            </div>

            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input class="btn btn-primary" type="submit" value="Sign up" id="singup_but"/></div>
        </form>
        <div class="mt-3">
            <h4 style="color: red" id="error_message"></h4>
        </div>
    </div>
</@lg.page>