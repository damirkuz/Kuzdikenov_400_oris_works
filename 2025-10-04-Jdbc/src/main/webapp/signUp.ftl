<html lang="en">
<#include "base.ftl">

<#macro title>SignUp page</#macro>

<#macro content>

    <form method="post" action="/signUp">
        New Login:
        <input type="text" name="login" placeholder="type your new login here">
        Password:
        <input type="password" name="password">
        Name:
        <input type="text" name="name" placeholder="type your name here">
        Last name:
        <input type="text" name="lastname" placeholder="type your lastname here">
        <br>
        <input type="submit" value="login">
    </form>

    <#if error?has_content>
        ${error}
    </#if>
</#macro>


</html>