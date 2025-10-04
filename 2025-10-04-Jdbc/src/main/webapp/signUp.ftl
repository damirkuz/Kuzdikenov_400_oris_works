<html lang="en">
<#include "base.ftl">

<#macro title>SignUp page</#macro>

<#macro content>

    <form method="post" action="/signUp">
        New Login:
        <input type="text" name="login" placeholder="type your new login here">
        Password:
        <input type="password" name="password">
        <br>
        <input type="submit" value="login">
    </form>

</#macro>


</html>