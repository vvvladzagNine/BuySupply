<#import "parts/base.ftl" as b>
<@b.page>
    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label class="col-sm-2 col-form-label">User Name</label>
            <div class="col-sm-6">
                <input type="text" name="username" value="<#if user.name??>${user.name}</#if>"
                       class="form-control"
                       placeholder="NickName" />
            </div>
            <label class="col-sm-2 col-form-label">City</label>
            <div class="col-sm-6">
                <input type="text" name="city" value="<#if user.city??>${user.city}<#else></#if>"
                       class="form-control"
                       placeholder="Old" />
            </div>
            <label class="col-sm-2 col-form-label">E-mail</label>
            <div class="col-sm-6">
                <input type="email" name="email" value="<#if user.email??>${user.email}<#else></#if>"
                       class="form-control"
                       placeholder="Email" />
            </div>


            <label class="col-sm-2 col-form-label">Profile photo</label>
            <div class="form-group col-sm-6">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile" />
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="col-sm-6"><input type="submit" class="btn btn-primary btn-bg" value="Save"/></div>
    </form>

</@b.page>