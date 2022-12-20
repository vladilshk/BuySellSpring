<#import "../template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Login page</h1>

    <form action="/login" method="post">
        <input type="email" name="username"><br>
        <input type="password" name="password"><br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="login">
    </form>

<a href="/registration">Registration</a>
</@t.template>