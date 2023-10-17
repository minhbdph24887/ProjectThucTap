const app = angular.module("shopping-cart-app", []);
app.controller("shopping-cart-ctrl", function ($scope, $http) {
    /*
        QUẢN LÝ GIỎ HÀNG
    */
    $scope.cart = {
        listProduct: [],
        // Thêm sản phẩm vào giỏ hàng
        add(idProduct) {
            // Gọi API để kiểm tra xem người dùng đã đăng nhập chưa
            $http.get("/api/check-login").then(resp => {
                if (resp.data.loggedIn) {
                    // Nếu người dùng đã đăng nhập, tiếp tục như bình thường
                    var item = this.listProduct.find(item => item.idProductItems == idProduct);
                    var urlAdd = "http://localhost:8080/product/" + idProduct;
                    if (item) {
                        if (isNaN(item.qty)) {
                            item.qty = 0;
                        }
                        item.qty++;
                        this.saveToLoaclStorage();
                    } else {
                        $http.get(urlAdd).then(resp => {
                            if (isNaN(resp.data.availableQuantity)) {
                                resp.data.availableQuantity = 0;
                            }
                            resp.data.availableQuantity = 1;
                            this.listProduct.push(resp.data);
                            this.saveToLoaclStorage();
                        })
                    }
                } else {
                    // Nếu người dùng chưa đăng nhập, hiển thị thông báo và chuyển hướng họ đến trang đăng nhập
                    alert("Vui lòng đăng nhập để thêm sản phẩm vào giỏ hàng");
                    window.location.href = "/login/form";
                }
            });
        },


        // Lưu giỏ hàng vào local storage
        saveToLoaclStorage() {
            var json = JSON.stringify(angular.copy(this.listProduct));
            localStorage.setItem("cart", json);
        },

        // Tính tổng số lượng mặt hàng trong giỏ
        get count() {
            return this.listProduct
                .map(listProduct => listProduct.availableQuantity)
                .reduce((total, availableQuantity) => total += availableQuantity, 0);
        },

        // Tổng số tiền các mặt hàng trong giỏ
        get totalCost() {
            return this.listProduct
                .map(listProduct => listProduct.availableQuantity * listProduct.costPrice)
                .reduce((total, availableQuantity) => total += availableQuantity, 0);
        },

        // Đọc giỏ hàng từ Local Storage
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.listProduct = json ? JSON.parse(json) : [];
        },

        // Xoá từng sản phẩm trong giỏ hàng
        remove(idProduct) {
            var index = this.listProduct.findIndex(listProduct => listProduct.idProduct == idProduct);
            this.listProduct.splice(index, 1);
            this.saveToLoaclStorage();
        },

        // Xoá toàn bộ sản phẩm trong giỏ hàng
        clear() {
            this.listProduct = [];
            this.saveToLoaclStorage();
        }
    }
    $scope.cart.loadFromLocalStorage();
});