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
            <th>username</th>
            <th>email</th>
            <th>номер</th>
            <th>Роль</th>
            <th>Дать/забрать админку</th>
            <th>Активность</th>
            <th>Забанить!!!</th>
        </tr>
        <#list users as user>
            <tr>
                <th>${user.id}</th>
                <th><a href="/user/${user.id}">${user.name}</th>
                <th>${user.email}</th>
                <th>89113170099</th>
                <th><#list user.roles as role>
                    ${role}
                    </#list>
                </th>
                <th>
                    <form action="/admin/user/changerole" method="post">
                        <input type="hidden" value="${user.id}" name="userId">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input class="btn btn-primary"  type="submit" value="Поменять">
                    </form>
                </th>
                <th><#if user.active>
                        активен
                        <#else>
                        забанен
                    </#if>
                </th>
                <th>
                    <form action="/admin/user/ban" method="post">
                        <input type="hidden" value="${user.id}" name="userId">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input class="btn btn-danger"  type="submit" value="БАН">
                    </form>
                </th>
            </tr>
        </#list>
    </table>


</@t.template>