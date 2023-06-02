package com.company.innoagri.screen.prodottopianificato;

import com.company.innoagri.entity.*;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import io.jmix.ui.App;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import org.aspectj.weaver.ast.Var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@UiController("ProdottoPianificato.edit")
@UiDescriptor("prodotto-pianificato-edit.xml")
@EditedEntityContainer("prodottoPianificatoDc")
public class ProdottoPianificatoEdit extends StandardEditor<ProdottoPianificato> {
    @Autowired
    private TwinColumn<Appezzamento> appezzamentiField;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionContainer<Appezzamento> appezzamentoesDc;

    private static final Logger log = LoggerFactory.getLogger(ProdottoPianificatoEdit.class);


    @Autowired
    private SystemAuthenticator systemAuthenticator;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Autowired
    private TextField<String> tenantField;

    @Autowired
    private ComboBox<TipologiaAppezzamento> tipologiaAppezzamentoField;
    @Autowired
    private TextField<Double> doseEttaroField;
    @Autowired
    private TextField<Double> doseEttaroMinField;
    @Autowired
    private TextField<Double> quantitaField;
    @Autowired
    private TextField<Double> quantitaMinField;
    @Autowired
    private TextField<String> totEttariField;
    @Autowired
    private TwinColumn<Varieta> varietaField;
    @Autowired
    private TwinColumn<Avversita> avversitaField;


    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        systemAuthenticator.withUser("admin", () -> {
            UserDetails user = currentAuthentication.getUser();
            //System.out.println("User: " + user.getUsername()); // admin
            tenantField.setVisible(true);
            return true;
        });

        log.info("Campagna trattamento " + getEditedEntity().getPianificazioneTrattamento().getCampagnaTrattamento().toString());
        /*List<Appezzamento> aps = dataManager.load(Appezzamento.class)
                .query("select e from Appezzamento e where e.campagnaTrattamenti = :campagnaTrattamenti")
                .parameter("campagnaTrattamenti",getEditedEntity().getPianificazioneTrattamento().getCampagnaTrattamento() )
                .list();
        appezzamentoesDc.setItems(aps);*/
        appezzamentiField.setValue(getEditedEntity().getAppezzamenti());
        varietaField.setValue(getEditedEntity().getVarieta());

        tipologiaAppezzamentoField.setValue(TipologiaAppezzamento.TUTTI);
        avversitaField.setValue(getEditedEntity().getAvversita());

        if(Objects.nonNull(getEditedEntity().getFitosanitario())){
            doseEttaroField.setCaption(getEditedEntity().getFitosanitario().getUnitaMisua() + "/Ettaro");
            doseEttaroMinField.setCaption(getEditedEntity().getFitosanitario().getUnitaMisua() + "/Ettaro Min");
        }

        tipologiaAppezzamentoField.setValue(getEditedEntity().getTipologiaAppezzamento());
    }

    public void updateAppezzamenti() {
        Collection<Varieta> var = getEditedEntity().getVarieta();
        TipologiaAppezzamento ta = tipologiaAppezzamentoField.getValue();
        CampagnaTrattamenti ct = getEditedEntity().getPianificazioneTrattamento().getCampagnaTrattamento();
        List<Appezzamento> aps = new ArrayList<>();
        if(Objects.nonNull(var) && Objects.nonNull(ta) && Objects.nonNull(ct)){
            if(ta.equals(TipologiaAppezzamento.TUTTI)){
               aps = dataManager.load(Appezzamento.class)
                        .query("select e from Appezzamento e where e.campagnaTrattamenti = :campagnaTrattamenti and e.varieta in :varieta")
                        .parameter("campagnaTrattamenti",ct )
                        .parameter("varieta",var )
                        .list();
            } else if (ta.equals(TipologiaAppezzamento.IN_PRODUZIONE)) {
               aps = dataManager.load(Appezzamento.class)
                        .query("select e from Appezzamento e where e.campagnaTrattamenti = :campagnaTrattamenti and e.nuovoImpianto=false and e.varieta in :varieta")
                        .parameter("campagnaTrattamenti",ct )
                        .parameter("varieta",var )
                        .list();
            } else if (ta.equals(TipologiaAppezzamento.NON_PRODUTTIVI)) {
                aps = dataManager.load(Appezzamento.class)
                        .query("select e from Appezzamento e where e.campagnaTrattamenti = :campagnaTrattamenti and e.nuovoImpianto=true and e.varieta in :varieta")
                        .parameter("campagnaTrattamenti",ct )
                        .parameter("varieta",var )
                        .list();
            }

            appezzamentoesDc.setItems(aps);
            log.info("Appezzamenti " + getEditedEntity().getAppezzamenti());
            appezzamentiField.setValue(getEditedEntity().getAppezzamenti());
        }

    }

    @Subscribe("appezzamentiField")
    public void onAppezzamentiFieldValueChange(HasValue.ValueChangeEvent<Collection<Appezzamento>> event) {
        AtomicReference<Integer> superficie = new AtomicReference<>(0);
        List<Appezzamento> aps = new ArrayList(event.getValue());

        if(Objects.nonNull(event.getValue()))
            aps.forEach(appezzamento -> superficie.set(superficie.get() + appezzamento.getSuperficie()));

        getEditedEntity().setAppezzamenti(aps);

        getEditedEntity().setTotEttari(String.valueOf(superficie.get()/10000.0));

        if(Objects.nonNull(doseEttaroField.getValue()))
        quantitaField.setValue(doseEttaroField.getValue()* Double.parseDouble(totEttariField.getValue()));
        if(Objects.nonNull(doseEttaroMinField.getValue()))
        quantitaMinField.setValue(doseEttaroMinField.getValue()* Double.parseDouble(totEttariField.getValue()));
    }

    @Subscribe("tipologiaAppezzamentoField")
    public void onTipologiaAppezzamentoFieldValueChange(HasValue.ValueChangeEvent<TipologiaAppezzamento> event) {
        getEditedEntity().setTipologiaAppezzamento(event.getValue());
        updateAppezzamenti();
    }


    @Subscribe("varietaField")
    public void onVarietaFieldValueChange(HasValue.ValueChangeEvent<Collection<Varieta>> event) {
        List<Varieta> var = new ArrayList(event.getValue());
        getEditedEntity().setVarieta(var);
        updateAppezzamenti();
    }

    @Subscribe("avversitaField")
    public void onAvversitaFieldValueChange(HasValue.ValueChangeEvent<Collection<Avversita>> event) {
        List<Avversita> var = new ArrayList(event.getValue());
        getEditedEntity().setAvversita(var);
    }

    @Subscribe("doseEttaroField")
    public void onDoseEttaroFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        if(Objects.nonNull(totEttariField.getValue()))
            quantitaField.setValue(event.getValue()* Double.parseDouble(totEttariField.getValue()));
        else
            quantitaField.setValue(0.0);
    }

    @Subscribe("doseEttaroMinField")
    public void onDoseEttaroMinFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        if(Objects.nonNull(totEttariField.getValue()))
            quantitaMinField.setValue(event.getValue()* Double.parseDouble(totEttariField.getValue()));
        else
            quantitaMinField.setValue(0.0);
    }

    @Subscribe("fitosanitarioField")
    public void onFitosanitarioFieldValueChange(HasValue.ValueChangeEvent<Fitosanitario> event) {
        doseEttaroField.setCaption(event.getValue().getUnitaMisua() + " Ettaro");
        doseEttaroMinField.setCaption(event.getValue().getUnitaMisua() + " Ettaro Min");
    }
}