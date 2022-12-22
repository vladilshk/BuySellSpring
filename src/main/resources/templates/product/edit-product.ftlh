<#import "../template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Изменить продукт</h1>

<form action="/product/edit/${product.id}" method="post">
    Заголовок: <input type="text" name="title"><br>
    Описание: <input type="text" name="description"><br>
    Цена: <input type="text" name="price" min="0" max="100000"><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Сохранить">
</form>
</@t.template>