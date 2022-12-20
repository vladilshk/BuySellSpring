<#import "template.ftlh" as t>
<@t.template user>

    <h1 style="text-align: center">Azito</h1>
    <br>

        <form action="/" method="get" id="searchForm">
            <div class="form-row">
                <div class="form-group col-md-2">
                    <select form="searchForm" class="form-control" name="city" aria-label="Default select example" style="height: 40px">
                        <option value="no" selected>Город не выбран</option>
                        <option value="Mosсow">Москва</option>
                        <option value="Nursultan">Нурсултан</option>
                        <option value="Istanbul">Стамбул</option>
                    </select>
                </div>
                <div class="form-group col-md-10">
                    <input type="text" name="searchWorld" style="height: 40px">
                    <input type="submit" class="btn btn-primary" value="search" style="height: 40px; margin-bottom: 4px">
                </div>
            </div>
        </form><br><br>

<#--<div class="card-columns">-->
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
        <#list products as product>
            <div class="col">
                <a href="/product/${product.getId()}" style="text-decoration: none">
                    <div class="card btn btn-light product" style="width: 18rem;">
                        <img class="card-img-top" src="/images/${product.id}" height="200" width="300" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title">${product.getTitle()}</h5>
                            <p class="card-text">${product.getPrice()} ₽</p>
                            <#--<a href="#" class="btn btn-primary">Подробнее</a>-->
                        </div>
                        <div class="card-footer text-muted">
                            ${product.city}
                            ${product.dateOfCreation}
                        </div>
                    </div>
                </a>
            </div>
        <#else>
            <h2>NO product</h2>
        </#list>
    </div>
    <br>
    <br>

</@t.template>
