package com.maids.backendquiz.productsmanagement.helpers;

import org.springframework.security.core.context.SecurityContextHolder;

public class GetPrincipalUser {
    public static String principalUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
