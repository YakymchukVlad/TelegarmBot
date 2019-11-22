package com.khnu.yakymchuk.enums;

public enum Role {

    ADMIN("Admin"),
    WAITER("Waiter"),
    UNKNOWN("Unknown");

    private String name;

    Role(String name){
        this.name = name;
    }

    public static Role fromValue(String roleName){
        if (roleName == null){
            return UNKNOWN;
        }
        for (Role role : values()){
            if (role.name.equalsIgnoreCase(roleName)){
                return role;
            }
        }
        return UNKNOWN;
    }

}
