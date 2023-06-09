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

import javax.naming.spi.DirObjectFactory;
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
    @Autowired
    private ProgressBar progressBar;

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
        progressBar.setIndeterminate(true);
        progressBar.setVisible(true);
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
                                        List<DocumentoFitosanitario> dfList = new ArrayList<>();
                                        AtomicReference<Boolean> find = new AtomicReference<>(false);
                                        prodottoPianificato.getAppezzamenti().forEach(appezzamento -> {
                                            find.set(false);
                                            // Calcolo delle quantità e del valore:
                                            Double qta = prodottoPianificato.getQuantita()*appezzamento.getSuperficie()/(prodottoPianificato.getTotEttari()*10000.0);
                                            Double qtaMin = prodottoPianificato.getQuantitaMin()*appezzamento.getSuperficie()/(prodottoPianificato.getQuantitaMin()*10000.0);
                                            Double costo = qta * prodottoPianificato.getFitosanitario().getPrezzo();
                                            dfList.forEach(documentoFitosanitario -> {
                                                if(documentoFitosanitario.getCliente() == appezzamento.getCliente()){
                                                    find.set(true);
                                                    // Creazione del movimento
                                                    MovimentoFitosanitario mf = dataManager.create(MovimentoFitosanitario.class);
                                                    mf.setData(documentoFitosanitario.getData());
                                                    mf.setFitosanitario(prodottoPianificato.getFitosanitario());
                                                    mf.setNote(prodottoPianificato.getNote());
                                                    mf.setAppezzamento(appezzamento);
                                                    mf.setTenant(getEditedEntity().getTenant());
                                                    mf.setCausale(cm);
                                                    mf.setQuantita(qta);
                                                    mf.setQuantitaMin(qtaMin);
                                                    mf.setValore(costo);
                                                    mf.setRiferimentoProdottoPianificato(prodottoPianificato);

                                                    //Aggiunta del movimento
                                                    mf.setDocumentoFitosanitario(documentoFitosanitario);
                                                    dataManager.save(mf);
                                                }
                                            });
                                            if(!find.get()){
                                                // Creazione del Documento
                                                DocumentoFitosanitario df = dataManager.create(DocumentoFitosanitario.class);
                                                df.setNote(prodottoPianificato.getPianificazioneTrattamento().getNote());
                                                df.setCliente(appezzamento.getCliente());
                                                df.setTipoDocumento(TipoDocumentoFitosanitario.TRATTAMENTO);
                                                df.setData(prodottoPianificato.getPianificazioneTrattamento().getData());
                                                df.setTenant(getEditedEntity().getTenant());
                                                dataManager.save(df);
                                                dfList.add(df);


                                                // Creazione del movimento
                                                MovimentoFitosanitario mf = dataManager.create(MovimentoFitosanitario.class);
                                                mf.setData(df.getData());
                                                mf.setFitosanitario(prodottoPianificato.getFitosanitario());
                                                mf.setNote(prodottoPianificato.getNote());
                                                mf.setAppezzamento(appezzamento);
                                                mf.setTenant(getEditedEntity().getTenant());
                                                mf.setCausale(cm);
                                                mf.setQuantita(qta);
                                                mf.setQuantitaMin(qtaMin);
                                                mf.setValore(costo);
                                                mf.setRiferimentoProdottoPianificato(prodottoPianificato);

                                                //Aggiunta del movimento
                                                mf.setDocumentoFitosanitario(df);
                                                dataManager.save(mf);

                                            }
                                       });
                                    });
                                    getEditedEntity().setIsScaricato(true);
                                    progressBar.setIndeterminate(false);
                                    progressBar.setVisible(false);
                                }),
                        new DialogAction(DialogAction.Type.NO)
                )
                .show();
    }
}