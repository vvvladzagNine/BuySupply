<#import "parts/andrewLogReg.ftl" as lg>
<@lg.page>
    <div class="logreg">
        <h1>Регистрация</h1>
        <form style="max-width: 400px" action="/registration" method="post">
            <div  class="form-group">
                <label for="exampleInputName1"> User Name : </label>
                <input type="text" class="form-control" id="exampleInputName1" aria-describedby="emailHelp" placeholder="User name" name="username"/>
            </div>

            <div  class="form-group">
                <label for="exampleInputName3"> User Name : </label>
                <input type="email" class="form-control" id="exampleInputName3" aria-describedby="emailHelp" placeholder="example@ex.com" name="email"/>
            </div>

            <div  class="form-group">
                <label for="exampleInputName4"> User Name : </label>
                <input type="text" class="form-control" id="exampleInputName4" aria-describedby="emailHelp" placeholder="City" name="city"/>
            </div>

            <div class="form-group">
                <label for="exampleInputPassword1"> Password: </label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password"/>
            </div>

            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input class="btn btn-primary" type="submit" value="Sign up"/></div>
        </form>
    </div>
</@lg.page>