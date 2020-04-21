var regButton = document.getElementById("singup_but");
var companyName = document.getElementById("exampleInputName1");
var mail = document.getElementById("exampleInputName3");
var city = document.getElementById("exampleInputName4");
var password1 = document.getElementById("exampleInputPassword1");
var password2 = document.getElementById("exampleInputPassword2");
var errorMes = document.getElementById("error_message");

var companyRegEx = /[A-Za-zА-Яа-яЁё\d&\-.,!?:;]{2,20}/;
var mailRegEx = /\S+@\S+\.\S+/;
var cityRegEx = /([А-ЯЁа-яё\-\s\d]{3,19})|([A-Za-z\-\s\d]{3,19})/;
var passwordRegEx = /(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}/g;
errorMes.value = "";
regButton.type = "button";
regButton.addEventListener('click', regClick);
city.addEventListener('blur', cityUpperCase);


function regClick() {
    if (!companyRegEx.test(companyName.value)) {
        errorMes.innerHTML = "Вы неверно ввели компанию (Разрешенные символы: -.,!?:;)";
    } else if (!mailRegEx.test(mail.value)) {
        errorMes.innerHTML = "Вы неверно ввели почту";
    }  else if(!cityRegEx.test(city.value)) {
        errorMes.innerHTML = "Вы неверно ввели город (название города начинается с большой буквы)";
    } else if (!passwordRegEx.test(password1.value)) {
        errorMes.innerHTML = "Вы неверно ввели пароль (Должен быть на английском, иметь хотя бы 1 заглавную букву, хотя бы 1 символ)";
    } else if (password1.value != password2.value) {
        errorMes.innerHTML = "Ваши пароли не совпадают";
        console.log(errorMes.value);
    } else {
        regButton.type = "submit";
        companyName.value = '"' + companyName.value + '"';
        regButton.click()
    }
}

function cityUpperCase() {
    if (city.value != "") {
        city.value = city.value.substring(0,1).toUpperCase() + city.value.substring(1);
        console.log(city.value);
    }
}