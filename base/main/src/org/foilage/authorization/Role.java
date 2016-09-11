package org.foilage.authorization;

public enum Role {

    NOT_AUTHENTICATED(0), DISMISSED(1), LOCKED(2), MUST_CHANGE_PASSWORD(3),

    USER(50), SUPER_USER(51),

    CUSTOMER_SERVICE(150), ADVANCED_CUSTOMER_SERVICE(151),

    USER_CREATOR(199), ADMIN(200), SUPER_ADMIN(201)
    ;

    private final int id;

    Role(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Role getById(int id) {

        for(Role role: Role.values()) {

            if(role.getId()==id) {

                return role;
            }
        }

        throw new IllegalArgumentException("No role exists with id "+id);
    }

}
