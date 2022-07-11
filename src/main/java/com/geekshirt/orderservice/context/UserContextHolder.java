package com.geekshirt.orderservice.context;

public class UserContextHolder {
    private static final ThreadLocal<UserContext> userContext = new ThreadLocal<UserContext>();

    public static final UserContext getContext() {
        UserContext userCtx = userContext.get();

        if (userCtx == null) {
            userCtx = new UserContext();
            userContext.set(userCtx);
        }

        return userCtx;
    }

    public static final void setContext(UserContext context) {
        userContext.set(context);
    }
}
