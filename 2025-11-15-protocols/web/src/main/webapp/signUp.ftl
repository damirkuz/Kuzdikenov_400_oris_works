<html lang="en">
<#include "base.ftl">

<#macro title>SignUp page</#macro>

<#macro content>

    <form method="post" id="signup-form" action="/signUp" enctype="multipart/form-data">
        New Login:
        <input type="text" name="login" id="signup-form-login" placeholder="type your new login here">
        Password:
        <input type="password" name="password">
        Name:
        <input type="text" name="name" placeholder="type your name here">
        Last name:
        <input type="text" name="lastname" placeholder="type your lastname here">
        <br>
        <P>Upload file</p>
        <input type="file" name="file">
        <br>
        <input type="submit" id="signup-form-submit" value="login">


    <script>
        const submit = document.getElementById('signup-form-submit');
        $('#signup-form-login').on('input', function () {
            submit.disabled = false;
            const value = this.value;
            $.get('/user/check', { login: value }, function (response) {
                $('#ajax-response').text(response);

                // disable submit button
                if (response !== "") {
                    submit.disabled = true;
                }
            });
        })
    </script>
</#macro>


</html>