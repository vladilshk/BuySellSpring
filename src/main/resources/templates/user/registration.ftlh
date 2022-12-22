<#import "../template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Регистрация</h1>

<form action="/registration" method="post">
    Имя пользователя: <input type="text" name="name"><br>
    Номер телефона: <input type="number" name="number"><br>
    Email: <input type="email" name="email"><br>
    Пароль: <input type="password" name="password"><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться">
</form>

<a href="/login">Войти</a>
</@t.template>