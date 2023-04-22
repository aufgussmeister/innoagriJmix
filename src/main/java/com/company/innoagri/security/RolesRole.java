package com.company.innoagri.security;

import io.jmix.security.role.annotation.RowLevelRole;

import javax.annotation.Nonnull;

@Nonnull
@RowLevelRole(name = "Roles", code = "roles")
public interface RolesRole {
}