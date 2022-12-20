<#import "../template.ftlh" as t>
<@t.template user>

    <h1 style="text-align: center">Profile</h1><br>

    <div style="align-content: center; align-items: center">
        <img style="display: block;margin-left: auto; margin-right: auto" src="/static/images/spiderMan.png" alt="profilePhoto"  height="300"><br>
    </div>
    <h3>Username: ${user.name}</h3>
    <h3>Email: ${user.email}</h3>

    <a href="/favourites">Favourites</a>
    <a href="/my/products">My products</a>
    <a href="/product/add">Add product</a><br><br><br>
    <form method="post" action="/logout">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" class="btn btn-danger" value="Выйти">
    </form>
    <br><br>
</@t.template>