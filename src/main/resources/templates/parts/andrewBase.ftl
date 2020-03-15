<#macro page>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8"/>
        <title>BuySupply</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous"/>
        <link rel="stylesheet" href="/static/andrewStyle.css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script type="text/javascript" src="webjars/jquery/3.4.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/4.3.1/js/bootstrap.min.js" defer></script>
        <script type="text/javascript" src="webjars/datatables/1.10.19/js/jquery.dataTables.min.js" defer></script>
        <script type="text/javascript" src="webjars/datatables/1.10.19/js/dataTables.bootstrap4.min.js" defer></script>
        <script type="text/javascript" src="webjars/noty/3.1.4/lib/noty.min.js" defer></script>
        <script type="text/javascript" src="webjars/datetimepicker/2.5.20/build/jquery.datetimepicker.full.min.js" defer></script>
        <style>
            body {
                /* Цвет фона и путь к файлу */
                color: #000000; /* Цвет текста */
                background: #e7e7e7 fixed;
                background-size: 100%;
            }
        </style>
    </head>
    <body>
    <div class="header">
        <#include "header.ftl"/>

    </div>

    <div class="main">
        <div class="main-menu">
            <#include "mainMenu.ftl"/>
        </div>

        <div class="main-panel">
            <#nested>
        </div>
    </div>


    <div class="footer">

    </div>
    <script  src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    </body>
    </html>

</#macro>