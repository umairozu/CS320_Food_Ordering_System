package FOS_UI;

import FOS_CORE.*;

import FOS_CORE.*;
//worked on by Umair Ahmad
public final class ServiceContext {

    private static final IAccountService accountService = new AccountService();
    private static final IRestaurantService restaurantService = new RestaurantService();
    private static final ICartService cartService = new CartService();
    private static final IOrderService orderService = new OrderService();
    private static final IManagerService managerService = new ManagerService();

    private ServiceContext() {}

    public static IAccountService getAccountService() {
        return accountService;
    }

    public static IRestaurantService getRestaurantService() {
        return restaurantService;
    }

    public static ICartService getCartService() {
        return cartService;
    }

    public static IOrderService getOrderService() {
        return orderService;
    }

    public static IManagerService getManagerService() {
        return managerService;
    }
}
