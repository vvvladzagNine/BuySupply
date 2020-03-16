<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    currentUser = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    currentUserId = currentUser.getId()
    >
    <span hidden id="creds">${currentUser.getEmail()}:${currentUser.getPassword()}</span>
<#else>
    <#assign
    name = "unknown"
    isAdmin = false
    currentUserId=-1
    >
</#if>

