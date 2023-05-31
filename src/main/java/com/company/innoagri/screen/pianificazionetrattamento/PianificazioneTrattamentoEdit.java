package com.company.innoagri.screen.pianificazionetrattamento;

import com.company.innoagri.entity.User;
import com.company.innoagri.screen.attivita.AttivitaEdit;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.PianificazioneTrattamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

@UiController("PianificazioneTrattamento.edit")
@UiDescriptor("pianificazione-trattamento-edit.xml")
@EditedEntityContainer("pianificazioneTrattamentoDc")

public class PianificazioneTrattamentoEdit extends StandardEditor<PianificazioneTrattamento> {
    @Autowired
    private SystemAuthenticator systemAuthenticator;
    @Autowired
    private CurrentAuthentication currentAuthentication;
    private static final Logger log = LoggerFactory.getLogger(AttivitaEdit.class);
    @Autowired
    private TextField<String> tenantField;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        systemAuthenticator.withUser("admin", () -> {
            UserDetails user = currentAuthentication.getUser();
            //System.out.println("User: " + user.getUsername()); // admin
            tenantField.setVisible(true);
            return true;
        });
    }
}