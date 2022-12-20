<#import "../template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">UserProducts</h1><br>

<#list products as product>
        <div class="row g-0 border">
            <div class="col-md-4">
                <img class="card-img-top" src="../images/${product.id}" width="400" height="300" alt="Card image">
            </div>
            <div class="col-md-8">
                <div class="card-body">
                    <h5 class="card-title">${product.title}</h5>
                    <p class="card-text">${product.description}</p>
                    <p class="card-text"><small class="text-muted">Размещено: 05.05.2022 13:23:12</small></p>
                    <br>
                </div>
                <div class="<#--card-footer-->d-flex justify-content-end">
                    <p>
                        <a class="btn btn-primary" href="/product/edit/${product.id}">Редактировать</a>&nbsp
                        <form method="post" action="/product/delete">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <input type="submit" class="btn btn-danger" value="Удалить">
                        </form>
                    </p>
                </div>
            </div>
        </div>
    <#--</a>-->
    <br>
</#list>
</@t.template>