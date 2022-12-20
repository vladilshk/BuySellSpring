<#import "../template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Add product</h1>
    <form action="/product/add" method="post" enctype="multipart/form-data">
        Title: <input type="text" name="title"><br>
        Description: <input type="text" name="description"><br>
        Price: <input type="number" name="price"><br>
        <select class="form-control" name="city" style="width: 200px">
            <option value="Mosсow" selected>Москва</option>
            <option value="Nursultan">Нурсултан</option>
            <option value="Istanbul">Стамбул</option>
        </select>
        <div class="custom-file">
            <input required type="file" name="image" id="image">
            <label class="custom-file-label" for="image">Первая фотография к товару</label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Save">
    </form>
</@t.template>