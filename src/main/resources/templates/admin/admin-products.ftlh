<#import "../template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Пользователи</h1>
    <br>

    <form action="/admin/users" method="get" id="searchForm">
        <div class="form-row">
            <div class="form-group col-md-10">
                <input type="text" name="searchWorld" style="height: 40px">
                <input type="submit" class="btn btn-primary" value="Поиск по имени" style="height: 40px; margin-bottom: 4px">
            </div>
        </div>
    </form><br>

    <table class="table table-primary table-border">
        <tr>
            <th>#id</th>
            <th>Название</th>
            <th>Цена</th>
            <th>Автор</th>
            <th>Просмотры</th>
            <th>Город</th>
            <th>Удалить</th>
        </tr>
        <#list products as product>
            <tr>
                <th>${product.id}</th>
                <th><a href="/product/${product.id}">${product.title}</a></th>
                <th>${product.price}</th>
                <th><a href="/user/${product.user.id}">${product.user.name}</a></th>
                <th>50</th>
                <th>${product.city}</th>
                <th>
                    <form action="/product/delete" method="post">
                        <input type="hidden" value="${product.id}" name="userId">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input class="btn btn-danger"  type="submit" value="удалить">
                    </form>
                </th>
            </tr>
        </#list>
    </table>


</@t.template>