<#import "../template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Favourites</h1><br>
    <#list products as product>
        <div class="row g-0 border">
            <div class="col-md-4">
                <img class="card-img-top" src="/static/images/img.jpg" alt="Card image">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">${product.title}</h5>
                    <p class="card-text">${product.description}</p>
                    <p class="card-text"><small class="text-muted">Размещено: 05.05.2022 13:23:12</small></p>
                    <br>
                </div>
                <div class="<#--card-footer-->d-flex justify-content-end">
                    <form method="post" action="/favourites/delete">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-danger" value="Удалить из избранного">
                    </form>
                </div>
            </div>
        </div>
        <br>
        <#else>
            <h3>Здесь почему-то пусто(((</h3>
            <a class="btn btn-primary" href="/">На главную страницу</a>
    </#list>
</@t.template>