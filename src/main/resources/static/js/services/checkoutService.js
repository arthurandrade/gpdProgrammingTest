angular.module("shoppingList").factory("checkoutService", function ($http, config) {
    
    
    var getCustomers = function () {
        return $http.get(config.url + "/customers")
    };
    
    var getProducts = function () {
        return $http.get(config.url + "/products")
    };

    var checkOut = function () {
        return $http.post(config.url + "/checkout")
    };

    var clean = function () {
        return $http.delete(config.url + "/clean")
    };

    var addItem = function (order) {
        return $http.post(config.url + "/addProduct", order)
    };

	return {
        getCustomers: getCustomers,
		getProducts: getProducts,
        checkOut: checkOut,
        clean: clean,
        addItem: addItem
	};
});