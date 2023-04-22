package com.company.innoagri.security;

import com.company.innoagri.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securitydata.entity.ResourceRoleEntity;
import io.jmix.securitydata.entity.RoleAssignmentEntity;

import javax.annotation.Nonnull;

@Nonnull
@ResourceRole(name = "Tecnico", code = "tecnico")
public interface TecnicoRole {
    @EntityAttributePolicy(entityClass = Appezzamento.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Appezzamento.class, actions = EntityPolicyAction.READ)
    void appezzamento();

    @EntityAttributePolicy(entityClass = Attivita.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Attivita.class, actions = {EntityPolicyAction.CREATE, EntityPolicyAction.READ, EntityPolicyAction.UPDATE})
    void attivita();

    @EntityAttributePolicy(entityClass = Avversita.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Avversita.class, actions = EntityPolicyAction.READ)
    void avversita();

    @EntityAttributePolicy(entityClass = Cliente.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Cliente.class, actions = EntityPolicyAction.READ)
    void cliente();

    @EntityAttributePolicy(entityClass = EventoCalendario.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void eventoCalendario();

    @EntityAttributePolicy(entityClass = Fitosanitario.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Fitosanitario.class, actions = EntityPolicyAction.READ)
    void fitosanitario();

    @EntityAttributePolicy(entityClass = FitosanitarioNazionale.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = FitosanitarioNazionale.class, actions = EntityPolicyAction.READ)
    void fitosanitarioNazionale();

    @EntityAttributePolicy(entityClass = Lavorazione.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Lavorazione.class, actions = EntityPolicyAction.READ)
    void lavorazione();

    @EntityAttributePolicy(entityClass = Mezzo.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Mezzo.class, actions = EntityPolicyAction.READ)
    void mezzo();

    @EntityAttributePolicy(entityClass = ToDo.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ToDo.class, actions = EntityPolicyAction.ALL)
    void toDo();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.READ)
    void user();

    @EntityAttributePolicy(entityClass = Varieta.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Varieta.class, actions = EntityPolicyAction.READ)
    void varieta();

    @EntityAttributePolicy(entityClass = RoleAssignmentEntity.class, attributes = {"username", "roleCode"}, action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = RoleAssignmentEntity.class, actions = EntityPolicyAction.READ)
    void roleAssignmentEntity();

    @EntityAttributePolicy(entityClass = ResourceRoleEntity.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void resourceRoleEntity();
}