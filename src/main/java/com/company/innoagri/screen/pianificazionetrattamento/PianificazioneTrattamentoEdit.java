package com.company.innoagri.screen.pianificazionetrattamento;

import com.company.innoagri.entity.*;
import com.company.innoagri.screen.attivita.AttivitaEdit;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import io.jmix.ui.Dialogs;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.DialogAction;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

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
    @Autowired
    private Dialogs dialogs;

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

    @Subscribe("generaTrattamentoBtn")
    public void onGeneraTrattamentoBtnClick(Button.ClickEvent event) {
        dialogs.createOptionDialog()
                .withCaption("Generazione Trattamento")
                .withMessage("Sei sicuro di voler generare il trattamento? Questo verrà sccaricato in ogni singolo cliente")
                .withActions(
                        new DialogAction(DialogAction.Type.YES, Action.Status.PRIMARY)
                                .withHandler(e -> {
                                    CausaliMovimentazioni cm = dataManager.load(CausaliMovimentazioni.class)
                                                    .query("select e from CausaliMovimentazioni e where e.causale='Trattamento'")
                                                            .one();
                                    getEditedEntity().getProdottiPianificati().forEach(prodottoPianificato -> {
                                        Collection<DocumentoFitosanitario> dfList = Collections.emptyList();
                                        AtomicReference<Boolean> find = new AtomicReference<>(false);
                                        prodottoPianificato.getAppezzamenti().forEach(appezzamento -> {
                                            find.set(false);
                                            dfList.forEach(documentoFitosanitario -> {
                                                if(documentoFitosanitario.getCliente().equals(appezzamento.getCliente())){
                                                    find.set(true);
                                                }
                                            });
                                            if(!find.get()){
                                                DocumentoFitosanitario df = dataManager.create(DocumentoFitosanitario.class);
                                                df.setCliente(appezzamento.getCliente());
                                                df.setTipoDocumento(TipoDocumentoFitosanitario.TRATTAMENTO);
                                                df.setData(prodottoPianificato.getPianificazioneTrattamento().getData());
                                                df.setTenant(getEditedEntity().getTenant());
                                                // Creazione del movimento
                                                MovimentoFitosanitario mf = dataManager.create(MovimentoFitosanitario.class);
                                                mf.setData(df.getData());
                                                mf.setFitosanitario(prodottoPianificato.getFitosanitario());
                                                mf.setNote(prodottoPianificato.getNote());
                                                mf.setAppezzamento(appezzamento);
                                                mf.setTenant(getEditedEntity().getTenant());
                                                mf.setCausale(cm);


                                            }
                                       });
                                    });
                                }),
                        new DialogAction(DialogAction.Type.NO)
                )
                .show();
    }
}