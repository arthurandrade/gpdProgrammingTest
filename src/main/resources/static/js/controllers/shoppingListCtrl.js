angular.module("shoppingList").controller("shoppingListCtrl", function ($scope, $http, $window, checkoutService, ModalService) {
    $scope.app = "Shopping list";
    $scope.card;
    $scope.customers = [];
    $scope.products = [];

    var loadCustomers = function () {
        checkoutService.getCustomers().success(function (data) {
            $scope.customers = data;
        }).error(function (data, status) {
            $scope.message = "There was a problem: " + data;
        });
    };

    var loadProducts = function () {
        checkoutService.getProducts().success(function (data) {
            $scope.products = data;
        }).error(function (data, status) {
            $scope.message = "There was a problem: " + data;
        });
    };
    $scope.checkOut = function () {
        checkoutService.checkOut().success(function(data) {
            delete $scope.card;
            delete $scope.order;
			$scope.shoppingForm.$setPristine();
            $window.alert("Order completed!");
        });
    };

    $scope.clean = function () {
        checkoutService.clean().success(function(data) {
            delete $scope.card;
            delete $scope.order;
			$scope.shoppingForm.$setPristine();
        });
    };

    $scope.addItem = function (order) {
        checkoutService.addItem(order).success(function(data) {
            $scope.card = data;
        });
    };
    
    $scope.haveProducts = function() {
    	return $scope.card && $scope.products.length > 0;
    }
    $scope.checkForm = function(fild) {
    	return fild.$error.required && fild.$dirty
    }
    loadProducts();
    loadCustomers();
});