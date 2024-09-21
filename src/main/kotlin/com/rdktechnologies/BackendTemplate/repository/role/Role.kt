package com.rdktechnologies.BackendTemplate.repository.role

import com.rdktechnologies.BackendTemplate.repository.permission.Permission
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

enum class Role(var value:String,val permission: MutableSet<Permission>) {
    ADMIN("Admin",
        java.util.Set.of(
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.ADMIN_CREATE,
        )
    ),
    USER("User",
        java.util.Set.of(
            Permission.USER_READ,
            Permission.USER_UPDATE,
            Permission.USER_CREATE,
            Permission.USER_DELETE,
        )
    );

    val authorities: MutableCollection<SimpleGrantedAuthority>?
        get() {
            val authorities: MutableCollection<SimpleGrantedAuthority>? = permission
                .stream()
                .map { permission -> SimpleGrantedAuthority(permission.name) }
                ?.collect(Collectors.toList())
            authorities?.add(SimpleGrantedAuthority("ROLE_$name"))
            return authorities
        }
}
