const offersApiUrl = "/rest/offer";
let context, form;


function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: offersApiUrl,
        data: $("#filter").serialize(),
        headers: {
            "Authorization": "Basic dTpw"
        },

    },).done(updateTableByData);
}

function makeEditable(ctx) {
    context = ctx;
    context.datatableApi = $("#datatable").DataTable(
        //https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
        $.extend(true, ctx.datatableOpts,
            {
                "ajax": {
                    "url": context.ajaxUrl,
                    "dataSrc": "",
                    "headers": {
                        "Authorization": "Basic dTpw"
                    },
                }
            }
        ));

    form = $('#detailsForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

    const token = $("meta[name='_csrf']").attr("content");
    const header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function updateTableByData(data) {
    context.datatableApi.clear().rows.add(data).draw();
}


let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}


function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;error" + "<br>error",
        type: "error",
        layout: "bottomRight"
    }).show();
}


$(function () {
    makeEditable({
        ajaxUrl: offersApiUrl,
        datatableOpts: {
            "columns": [
                {
                    "data": "description"
                },
                {
                    "data": "description"
                },
                {
                    "data": "description"
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        },
        updateTable: updateFilteredTable
    });

});