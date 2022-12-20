<#macro template user>
    <!doctype html>
    <html lang="ru">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/static/style/style.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
              integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
              crossorigin="anonymous">

        <title>azito</title>
    </head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand" href="#"><img src="/static/images/logo.png" alt="logo" width="128"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText"
                aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/">Главная страница</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/favourites">Избранное</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/product/add">Выложить объявление</a>
                </li>
            </ul>
            <span class="navbar-text" style="margin-right: 30px">
                <#if user.id??>
                    <a href="/profile">Profile</a><br>
                    <#else>
                    <a href="/login">Login</a><br>
                </#if>
            </span>
        </div>
    </nav>
    <div class="container mt-4">
        <#nested/>
    </div>
    <br>
    <br>
    <footer class="text-center text-white fixed-bottom bg-primary">
        <!-- Grid container -->
        <!-- Grid container -->
        <!-- Copyright -->
        <div class="text-center p-3">
            <#--<p><a>home</a>   <a>home</a>   <a>home</a></p>-->
            © 2022 Copyright: vovan
        </div>
        <!-- Copyright -->
    </footer>
    <body>
</#macro>