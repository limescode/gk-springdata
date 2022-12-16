var app = angular.module("app", []);

app.controller('productController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/products')
            .then(function (response) {
                $scope.productList = response.data;
            });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/delete/' + productId).then(function (response) {
            $scope.loadProducts();
        });
    }

    $scope.filterPrice = function () {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                min: $scope.minPrice,
                max: $scope.maxPrice
            }
        }).then(function (response) {
            $scope.productList = response.data;
        });
    }
    $scope.loadProducts();

});
