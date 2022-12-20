<#import "../template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Registration page</h1>

<form action="/registration" method="post">
    Username: <input type="text" name="name"><br>
    Email: <input type="email" name="email"><br>
    Password: <input type="password" name="password"><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="registration">
</form>

<a href="/registration">Registration</a>
</@t.template>