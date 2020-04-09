package com.nonfallable.taskKnight.security.permissions;

import com.nonfallable.taskKnight.models.Role;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum PermissionMapping {
    UPDATE_OWN_PASSWORD(Role.ADMIN, Role.USER),
    READ_AUTHENTICATION(Role.ADMIN, Role.USER),
    READ_OWN_TASKS(Role.ADMIN, Role.USER),
    CREATE_OWN_TASKS(Role.ADMIN, Role.USER),
    UPDATE_OWN_TASKS(Role.ADMIN, Role.USER),
    DELETE_OWN_TASKS(Role.ADMIN, Role.USER),
    READ_ALL_PROFILES(Role.ADMIN),
    READ_OWN_PROFILE(Role.ADMIN, Role.USER),
    UPDATE_OWN_PROFILE(Role.ADMIN, Role.USER),
    DELETE_OWN_PROFILE(Role.ADMIN, Role.USER);

    private static Map<Role, List<PermissionMapping>> permissionsByRole = new HashMap<>();
    static {
        List<PermissionMapping> permissionMappings = Arrays.asList(PermissionMapping.values());
        permissionMappings.forEach(mapping -> {
            mapping.roles.forEach(role -> {
                List<PermissionMapping> permissionMappingsForRole = permissionsByRole.get(role);
                if(permissionMappingsForRole == null) {
                    LinkedList<PermissionMapping> n = new LinkedList<>();
                    n.add(mapping);
                    permissionsByRole.put(role, n);
                } else permissionMappingsForRole.add(mapping);
            });
        });
    }

    public static List<PermissionMapping> getMappingsByRole(Role role) {
        return permissionsByRole.get(role);
    }

    private List<Role> roles;

    PermissionMapping(Role ...roles) {
        this.roles = Arrays.asList(roles);
    }

    public List<Role> getRoles() {
        return roles;
    }
}
