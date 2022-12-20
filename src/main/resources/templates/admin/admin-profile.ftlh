<#import "../template.ftlh" as t>
<@t.template user>

    <h1 style="text-align: center">Профиль админа</h1><br>
    <div class="row g-0" style="text-align: center">
        <div class="col-md-4">
            <h3>Адиминские фитчи</h3><br><br>
            <a class="btn btn-primary" style="width: 200px" href="/admin/users">Пользователи</a><br><br>
            <a class="btn btn-primary" style="width: 200px" href="/admin/products">Продукты</a><br>
            <br><br>
        </div>

        <div class="col-md-4">
            <div style="align-content: center; align-items: center">
                <img style="display: block;margin-left: auto; margin-right: auto" src="/static/images/spiderMan.png"
                     alt="profilePhoto" height="300"><br>
            </div>
        </div>

        <div class="col-md-4">
            <h3>Пользовательские ссылки</h3><br><br>
            <a class="btn btn-primary" style="width: 200px" href="/favourites">Избранное</a><br><br>
            <a class="btn btn-primary" style="width: 200px" href="/my/products">Мои объявления</a><br><br>
            <a class="btn btn-primary" style="width: 200px" href="/product/add">Выложить объявление</a><br><br>

        </div>
    </div>
    <h3>Username: ${user.name}</h3>
    <h3>Email: ${user.email}</h3>
    <form method="post" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" class="btn btn-danger" value="Выйти">
    </form>
    <br><br>
</@t.template>