<#import "../template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Добавить продукт</h1>
    <form action="/product/add" method="post" enctype="multipart/form-data">
        Заголовок: <input type="text" name="title" required><br>
        Описание: <input type="text" name="description" required><br>
        Цена: <input type="number" name="price" min="10" max="100000"><br>
        Город: <select class="form-control" name="city" style="width: 200px">
            <option value="Mosсow" selected>Москва</option>
            <option value="Nursultan">Нурсултан</option>
            <option value="Istanbul">Стамбул</option>
        </select>
        <div class="custom-file">
            <input required type="file" name="image" id="image" style="width: 200px">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input class="btn btn-primary" type="submit" value="Выложить">
    </form>
</@t.template>