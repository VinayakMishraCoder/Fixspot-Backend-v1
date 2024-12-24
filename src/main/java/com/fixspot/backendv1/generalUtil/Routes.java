package com.fixspot.backendv1.generalUtil;

import java.util.HashSet;
import java.util.List;

/**
 * All the routes/end-points for the project.
 */
public class Routes {

    public static HashSet<String> permitted;

    public static final String API_V1 = "api/v1";
    public static final String AUTH_ROUTES = "/auth/**";
    public static final String AUTH_LOGIN = "auth/login";
    public static final String AUTHORITY_REGISTRATION = "auth/authority/register";
    public static final String REPORTER_REGISTRATION = "auth/reporter/register";

    public static final String AUTHORITY_ROUTES = "/authority/**";
    public static final String AUTHORITY_GET_USER = "authority/get-user";

    public static final String REPORTER_ROUTES = "/reporter/**";
    public static final String REPORTER_GET_USER = "reporter/get-user";

    // Initializing the permitted routes in a static block
    static {
        permitted = new HashSet<>();
        permitted.add("/"+Routes.API_V1+AUTH_ROUTES);
        permitted.add("/"+Routes.API_V1+"/"+AUTH_LOGIN);
        permitted.add("/"+Routes.API_V1+"/"+AUTHORITY_REGISTRATION);
        permitted.add("/"+Routes.API_V1+"/"+REPORTER_REGISTRATION);
    }

    // Util functions.
    /**
     * Checks if a route is always permitted.
     * @param route the route to check.
     * @return true if the route is always permitted, false otherwise.
     */
    public static Boolean isAlwaysPermitted(String route) {
        System.out.println("Route: " + route);
        permitted.forEach(e -> {
            System.out.println(e);
        });
        if (route == null || route.isEmpty()) return false;
        return permitted.contains(route);
    }

    /**
     * Checks if all the routes in the list are always permitted.
     * @param routes the list of routes to check.
     * @return true if all routes are always permitted, false otherwise.
     */
    public static Boolean areAlwaysPermitted(List<String> routes) {
        if (routes == null || routes.isEmpty()) return false;
        return new HashSet<>(permitted).containsAll(routes);
    }
}
