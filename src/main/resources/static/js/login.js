var logButton = document.getElementById("submit_but");
var badLogText = document.getElementById("js_bad_login");
var loginText = document.getElementById("exampleInputName1");

var loginRegEx = /\S+@\S+\.\S+/;

logButton.type = "button";
badLogText.style.visibility = "hidden";


logButton.addEventListener('click', logClick);

function logClick() {
        if (loginRegEx.test(loginText.value)|| loginText.value == "u") {
                logButton.type = "submit";
                logButton.click()
        } else {
                badLogText.style.visibility = "visible";
        }
}