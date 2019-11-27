<#import "parts/andrewLogReg.ftl" as lg>
<@lg.page>
    <div class="logreg" >
        <h1>Вход</h1>
        <form style="max-width: 400px" action="login" method="post">
            <div  class="form-group">
                <label for="exampleInputName1"> User Name : </label>
                <input type="text" class="form-control" id="exampleInputName1" aria-describedby="emailHelp" placeholder="u" name="username"/>
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1"> Password: </label>
                <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="p"/>
            </div>

            <div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <input class="btn btn-dark" type="submit" value="Sign In"/></div>
        </form>
    </div>
</@lg.page>