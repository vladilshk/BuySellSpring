<#import "../template.ftlh" as t>
<@t.template user>
<h1 style="text-align: center">Edit product</h1>

<form action="/product/edit/${product.id}" method="post">
    Title: <input type="text" name="title"><br>
    Description: <input type="text" name="description"><br>
    Price: <input type="text" name="price"><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Save">
</form>
</@t.template>