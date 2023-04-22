package com.company.innoagri.security;

import com.company.innoagri.entity.*;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.security.role.annotation.SpecificPolicy;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

import javax.annotation.Nonnull;

@Nonnull
@ResourceRole(name = "Amministrazione", code = "amministrazione")
public interface AmministrazioneRole {
    @EntityAttributePolicy(entityClass = Appezzamento.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Appezzamento.class, actions = EntityPolicyAction.ALL)
    void appezzamento();

    @EntityAttributePolicy(entityClass = Attivita.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Attivita.class, actions = EntityPolicyAction.ALL)
    void attivita();

    @EntityAttributePolicy(entityClass = Avversita.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Avversita.class, actions = EntityPolicyAction.ALL)
    void avversita();

    @EntityAttributePolicy(entityClass = Cliente.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Cliente.class, actions = EntityPolicyAction.ALL)
    void cliente();

    @EntityAttributePolicy(entityClass = Fitosanitario.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Fitosanitario.class, actions = EntityPolicyAction.ALL)
    void fitosanitario();

    @EntityAttributePolicy(entityClass = FitosanitarioNazionale.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = FitosanitarioNazionale.class, actions = EntityPolicyAction.ALL)
    void fitosanitarioNazionale();

    @EntityAttributePolicy(entityClass = Lavorazione.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Lavorazione.class, actions = EntityPolicyAction.ALL)
    void lavorazione();

    @EntityAttributePolicy(entityClass = Mezzo.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Mezzo.class, actions = EntityPolicyAction.ALL)
    void mezzo();

    @EntityAttributePolicy(entityClass = ToDo.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ToDo.class, actions = EntityPolicyAction.ALL)
    void toDo();

    @EntityAttributePolicy(entityClass = User.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = User.class, actions = EntityPolicyAction.ALL)
    void user();

    @EntityAttributePolicy(entityClass = Varieta.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Varieta.class, actions = EntityPolicyAction.ALL)
    void varieta();

    @SpecificPolicy(resources = {"rest.enabled", "rest.fileDownload.enabled", "rest.fileUpload.enabled"})
    void specific();

    @EntityAttributePolicy(entityClass = Fattura.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Fattura.class, actions = EntityPolicyAction.ALL)
    void fattura();

    @EntityAttributePolicy(entityClass = EventoCalendario.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = EventoCalendario.class, actions = EntityPolicyAction.ALL)
    void eventoCalendario();

    @EntityAttributePolicy(entityClass = ListinoCliente.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = ListinoCliente.class, actions = EntityPolicyAction.ALL)
    void listinoCliente();

    @EntityAttributePolicy(entityClass = PrezzoLavorazione.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = PrezzoLavorazione.class, actions = EntityPolicyAction.ALL)
    void prezzoLavorazione();

    @MenuPolicy(menuIds = {"User.browse", "themeSettingsScreen", "Cliente.browse", "Appezzamento.browse", "Varieta.browse", "Lavorazione.browse", "ListinoCliente.browse", "Mezzo.browse", "PrezzoLavorazione.browse", "Attivita.browse", "Fattura.browse", "FitosanitarioNazionale.browse", "Avversita.browse", "Fitosanitario.browse"})
    @ScreenPolicy(screenIds = {"User.browse", "themeSettingsScreen", "Cliente.browse", "Appezzamento.browse", "Varieta.browse", "Lavorazione.browse", "ListinoCliente.browse", "Mezzo.browse", "PrezzoLavorazione.browse", "Attivita.browse", "Fattura.browse", "FitosanitarioNazionale.browse", "Avversita.browse", "Fitosanitario.browse", "Appezzamento.edit", "Attivita.edit", "Avversita.edit", "Cliente.edit", "Fattura.edit", "Fitosanitario.edit", "FitosanitarioNazionale.edit", "Lavorazione.edit", "ListinoCliente.edit", "LoginScreen", "MainScreen", "Mezzo.edit", "PrezzoLavorazione.edit", "User.edit", "Varieta.edit"})
    void screens();
}