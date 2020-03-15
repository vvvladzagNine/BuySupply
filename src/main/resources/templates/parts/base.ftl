<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>BuySupply</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous"/>
        <link rel="stylesheet" href="/static/style.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">



        <style>
            body {
                /* Цвет фона и путь к файлу */
                color: #000000; /* Цвет текста */
                background: #e9edea;
                background-size: 100%;
            }
        </style>
    </head>
    <body>
    <br/>
    <br/>
    <br class="mb-7"/>
    <#include "navbar.ftl"/>
    <div class="container mt-5">
        <#nested>
    </div>
    </body>
    </html>

</#macro>