package com.company.innoagri.security;

import io.jmix.security.role.annotation.ResourceRole;

import javax.annotation.Nonnull;

@Nonnull
@ResourceRole(name = "Responsabile", code = "responsabile")
public interface ResponsabileRole {
}