<html lang="en">
<#include "base.ftl">

<#macro title>Main page</#macro>

<#macro content>

    <h3>
        Hello, ${sessionUser}! Login successful<br>
        Session ID = ${sessionId}<br>
        Cookie user = ${cookieUser}

        <#if profilePictureUrl??>
            <img src="${profilePictureUrl}" alt="Аватарка" />
        </#if>

    </h3>

</#macro>


</html>