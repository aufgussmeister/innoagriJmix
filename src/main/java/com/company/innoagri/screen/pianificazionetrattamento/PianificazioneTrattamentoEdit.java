package com.company.innoagri.screen.pianificazionetrattamento;

import com.company.innoagri.entity.*;
import com.company.innoagri.screen.attivita.AttivitaEdit;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import io.jmix.ui.Notifications;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.Table;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private DateField<LocalDate> dataField;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Table<ProdottoPianificato> prodottiPianificatiTable;
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        systemAuthenticator.withUser("admin", () -> {
            UserDetails user = currentAuthentication.getUser();
            //System.out.println("User: " + user.getUsername()); // admin
            tenantField.setVisible(true);
            return true;
        });
    }

    @Subscribe("dataField")
    public void onDataFieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        Integer year =   event.getValue().getYear();
        Optional<CampagnaTrattamenti> cp = dataManager.load(CampagnaTrattamenti.class)
                .query("select e from CampagnaTrattamenti e where e.anno= :year ")
                .parameter("year", year).optional();
        cp.ifPresentOrElse(campagnaTrattamenti -> {
            getEditedEntity().setCampagnaTrattamento(campagnaTrattamenti);
            prodottiPianificatiTable.setEditable(true);
        }, ()->{
            prodottiPianificatiTable.setEditable(false);
            notifications.create()
                    .withCaption("Non esiste una campagna Trattamenti per l'anno " + year)
                    .show();
        });

    }
}