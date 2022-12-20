<#import "../template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">User info</h1>
    <h3>Username: ${user.name}</h3>
    <h3>Username: ${user.email}</h3>
</@t.template>