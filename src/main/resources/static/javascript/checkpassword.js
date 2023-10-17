window.onload = function () {
    const pwd = document.getElementById("pwd");
    const chk = document.getElementById("chk");

    chk.addEventListener('change', function(e){
        pwd.type = this.checked ? "text" : "password";
    });
};