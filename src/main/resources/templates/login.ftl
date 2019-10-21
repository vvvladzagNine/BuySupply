<#import "parts/base.ftl" as b>
<@b.page>
    <form action="login" method="post">
        <div  class="form-group">
            <label for="exampleInputName1"> User Name : </label>
            <input type="text" class="form-control" id="exampleInputName1" aria-describedby="emailHelp" placeholder="User name" name="username"/>
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1"> Password: </label>
            <input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password"/>
        </div>
        <div><input class="btn btn-dark" type="submit" value="Sign In"/></div>
    </form>
</@b.page>