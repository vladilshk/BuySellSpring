<#import "../template.ftlh" as t>
<@t.template user>

    <h1 style="text-align: center">Профиль</h1><br>

    <div style="align-content: center; align-items: center">
        <img style="display: block;margin-left: auto; margin-right: auto" src="/static/images/spiderMan.png" alt="profilePhoto"  height="300"><br>
    </div>
    <h3>Username: ${user.name}</h3>
    <h3>Email: ${user.email}</h3>
    <h3>Телефон: ${user.number}</h3><br>

    <a class="btn btn-primary" href="/favourites">Избранное</a><br>
    <a class="btn btn-primary"  href="/my/products">Мои продукты</a><br>
    <a class="btn btn-primary"  href="/product/add">Добавить продукт</a><br><br><br>
    <form method="post" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" class="btn btn-danger" value="Выйти">
    </form>
    <br><br>
</@t.template>