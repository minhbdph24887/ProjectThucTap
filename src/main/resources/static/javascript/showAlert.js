window.onload = function () {
    // Kiểm tra và hiển thị thông báo từ phần tử HTML
    var errorMessageElement = document.getElementById('error-message');
    if (errorMessageElement && errorMessageElement.value) {
        alert(errorMessageElement.value);
    }

    // Kiểm tra và hiển thị thông báo từ localStorage
    var message = localStorage.getItem('message');
    if (message) {
        alert("Đăng Xuất Thành Công");
        localStorage.removeItem('message');  // Xóa thông báo khỏi localStorage sau khi hiển thị
    }
}
