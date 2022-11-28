package fr.pantheonsorbonne.miage.game;

import fr.pantheonsorbonne.miage.enums.RoleValue;

public class Role {

    private final RoleValue role;

    public Role (RoleValue value){
        this.role= value;
    }

    public static Role HaveRole(String str) {
        RoleValue value;
        value = RoleValue.valueOfStr(str.substring(0, 1));
        return new Role(value);

    }

    public RoleValue getRole(){
        return role;
    }
}
