<#import "../template.ftlh" as t>
<@t.template user>
    <div style="align-content: center; align-items: center">
        <img style="display: block;margin-left: auto; margin-right: auto" src="/static/images/spiderMan.png" alt="profilePhoto"  height="300"><br>
    </div>
    <h1 style="text-align: center">User info</h1>
    <h3>Username: ${user.name}</h3>
    <#if show>
    <h3>email: ${user.email}</h3>
    <h3>Номер: ${user.number}</h3>
    </#if>

    Объявления пользователя:

    <#list products as product>
        <a href="/product/${product.id}">
        <div class="row g-0 border">
            <div class="col-md-4">
                <img class="card-img-top" src="/images/${product.id}" alt="Card image">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title"><a href="/product/${product.id}">${product.title}</a></h5>
                    <p class="card-text">${product.description}</p>
                    <p class="card-text"><small class="text-muted">Размещено: ${product.dateOfCreation}</small></p>
                    <br>
                </div>
            </div>
        </div>
        <br>
    <#else>
        <h3>Здесь почему-то пусто(((</h3>
        <a class="btn btn-primary" href="/">На главную страницу</a>
    </#list>

</@t.template>