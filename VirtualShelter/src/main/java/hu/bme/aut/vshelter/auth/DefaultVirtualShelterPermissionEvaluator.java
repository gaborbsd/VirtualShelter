package hu.bme.aut.vshelter.auth;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DefaultVirtualShelterPermissionEvaluator implements PermissionEvaluator {

    private Map<String, Permission> permissionNameToPermissionMap = new HashMap<String, Permission>();

    protected DefaultVirtualShelterPermissionEvaluator(Map<String, Permission> permissions) {
        this.permissionNameToPermissionMap = permissions;
    }

    private boolean canHandle(Authentication authentication, Object permission) {
        return authentication != null && permission instanceof String;
    }

    private boolean checkPermission(Authentication authentication, Object object, String permissionKey) {
        Permission permission = this.permissionNameToPermissionMap.get(permissionKey);
        return permission.isAllowed(authentication, object);
    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Object targetDomainObject, Object permission) {
        boolean hasPermission = false;
        if (this.canHandle(authentication, permission)) {
            hasPermission = this.checkPermission(authentication, targetDomainObject, (String) permission);
        }
        return hasPermission;
    }

    @Override
    @Deprecated
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId, String targetType, Object permission) {
        return false;
    }

}
