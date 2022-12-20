<#import "../template.ftlh" as t>
<@t.template user>
    <#--<h1 style="text-align: center">Product info</h1>
    <br><hr>
    <img class="card-img-top" src="/static/images/img.jpg" alt="Card image cap">
    <h3>Title: ${product.title}</h3><br>
    <h3>Description: ${product.description}</h3><br>
    <h3>Price: ${product.price}</h3><br>
    <h3>Author: ${product.user.name}</h3>
    <a href="/product/edit/${product.id}">Edit product</a>
    <form action="/product/delete/${product.id}" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="delete product">
    </form>
    <form action="/favourites/add" method="post">
        <input type="hidden" name="productId" value="${product.id}">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="add to a favourites">
    </form>
    <br>
    <br>-->
    <div class="row g-0 border">
        <div class="col-md-6">
            <img class="card-img-top" src="../images/${product.id}" width="400" height="350" alt="Card image">
        </div>
        <div class="col-md-6">
            <div class="card-body">
                <h5 class="card-title">${product.title}</h5>
                <p class="card-text">${product.description}</p>
                <br>
                <p class="card-text">Объяение разместил: ${product.user.name}</p>
                <p class="card-text">email: ${product.user.email}</p>
                <p class="card-text">Город: ${product.city}</p>

                <br>
                <br>
            </div>
            <br>
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