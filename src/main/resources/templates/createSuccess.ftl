<#import "parts/base.ftl" as b>
<@b.page>
    <div class="container mt-15">
        <div class="row">
            <div class="col"></div>
            <div class="col-8">
                <div class="alert alert-success">
                    <h2 class="text-center mt-10">Предложение успешно создано!</h2>
                </div>
            </div>
            <div class="col"></div>
        </div>
        <div class="row">
            <div class="col"></div>
            <div class="col-8">
                <div class="list-group-6">

                    <ul class="list-group list-group-horizontal-xl">
                        <li class="list-group-item">
                            <div class="container">
                                <div class="row">
                                    <div class="col">
                                        <span style="color: black">Description</span>
                                    </div>
                                    <div class="col">
                                    </div>
                                    <div class="col">
                                        <span class="text-primary">${created.description}</span>
                                    </div>

                                </div>
                            </div>

                        </li>
                        <li class="list-group-item">
                            <div class="container">
                                <div class="row">
                                    <div class="col">
                                        <span style="color: black">DateTime of creation</span>
                                    </div>
                                    <div class="col">
                                    </div>
                                    <div class="col">
                                        <span class="text-primary">${created.dateTime.toLocalDate()}  ${created.dateTime.toLocalTime().hour} : ${created.dateTime.toLocalTime().minute}</span>
                                    </div>

                                </div>
                            </div>

                        </li>
                    </ul>
                </div>

            </div>
            <div class="col"></div>
        </div>

        <div class="row">
            <div class="col"></div>
            <div class="col-8">
                <div class="alert alert-primary" style="text-align: center">
                  <h4> <a href="/offers">К списку предложений</a> </h4>
                </div>
            </div>
            <div class="col"></div>
        </div>

    </div>
</@b.page>