<#import "../template.ftlh" as t>
<@t.template user>
    <div class="row g-0 border">
        <div class="col-md-6">
            <img class="card-img-top" src="../images/${product.id}" width="400" height="350" alt="Card image">
        </div>
        <div class="col-md-6">
            <div class="card-body">
                <h5 class="card-title">${product.title}</h5>
                <p class="card-text">${product.description}</p>
                <p class="card-text">${product.price} руб.</p>
                <br>
                <p class="card-text">Объяение разместил: <a href="/user/${product.user.id}">${product.user.name}</a></p>
                <#if show>
                <p class="card-text">email: ${product.user.email}</p>
                <p class="card-text">Телефон: ${product.user.number}</p>
                </#if>
                <p class="card-text">Город: ${product.city}</p>

            </div>
            <br>
            <p class="card-text"><small class="text-muted">&nbsp&nbsp&nbsp&nbsp&nbspПросмотры: ${product.views}</small></p>
            <div class="d-flex  justify-content-end ">
                <p class="card-text" style="margin-top: 3px"><small class="text-muted">Размещено: ${product.dateOfCreation} &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</small></p>
                <form method="post" action="/favourites/add">
                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" class="btn btn-primary" value="Добавить в избранное">
                </form>
            </div>
        </div>
    </div>
</@t.template>