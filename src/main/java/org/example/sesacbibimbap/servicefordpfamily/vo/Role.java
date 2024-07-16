package org.example.sesacbibimbap.servicefordpfamily.vo;
public enum Role {
    USER("ROLE_USER"),
    ;

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public static boolean isValidRole(String role) {
        for (Role r : Role.values()) {
            if (r.name().equals(role)) {
                return true;
            }
        }
        return false;
    }
    public String getRoleName() {
        return roleName;
    }
}

