const offersApiUrl = "/rest/offer";
let context, form;
let failedNote;
creds=$("#creds").text()



function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: offersApiUrl,
        data: $("#filter").serialize(),
        dataType: 'json',
        headers: {
            "Authorization": "Basic "+btoa(creds)
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
                        "Authorization": "Basic "+btoa(creds)
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

}

function updateTableByData(data) {
    context.datatableApi.clear().rows.add(data).draw();
}




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
                    "data": "cost"
                },
                {
                    "data": "amount"
                },
                {
                    "data": "category",
                    "render": function (data, type, full, meta) {
                        return '<span>' + data.name + '</span>'
                    }
                },
                {
                    "data": "dateTime"
                },
                {
                    "data": "id",
                    "render": function (data, type, full, meta) {
                        return '<a href="/offer/' + data + '" style="color:grey"><i class="fas fa-ellipsis-h fa-2x"></i></a>';
                    }
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


