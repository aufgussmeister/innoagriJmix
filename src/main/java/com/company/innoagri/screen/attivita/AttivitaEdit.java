// TODO Bisogna finire la gestione della lavorazione con prezzo al cliente, e gli errori se non c'è un prezzo
package com.company.innoagri.screen.attivita;

import com.company.innoagri.entity.*;
import io.jmix.core.DataManager;
import io.jmix.core.common.event.Subscription;
import io.jmix.core.common.util.ParamsMap;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.App;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.list.AddAction;
import io.jmix.ui.component.*;
import io.jmix.ui.component.data.BindingState;
import io.jmix.ui.component.data.ValueSource;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import org.apache.commons.collections4.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import javax.inject.Named;
import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;

@UiController("Attivita.edit")
@UiDescriptor("attivita-edit.xml")
@EditedEntityContainer("attivitaDc")
public class AttivitaEdit extends StandardEditor<Attivita> {
    @Autowired
    private CheckBox daFatturareField;
    @Autowired
    private TimeField<Date> oraFineField;
    @Autowired
    private TimeField<Date> oraInizioField;

    @Autowired
    private TextField<Double> oreCollaboratoreField;
    @Autowired
    private TextField<Double> durataField;


    private static final Logger log = LoggerFactory.getLogger(AttivitaEdit.class);
    @Autowired
    private EntityPicker<Cliente> clienteField;
    @Autowired
    private ComboBox<UM> unitaMisuraField;

    private PrezzoLavorazione prezzoLavorazione;
    @Autowired
    private EntityPicker<Lavorazione> lavorazioneField;

    @Autowired
    private Notifications notifications;

    @Autowired
    private DataManager dataManager;
    @Autowired
    private DateField<LocalDate> dataField;
    @Autowired
    private TextField<Double> qtaField;
    @Autowired
    private CurrencyField<Number> valoreField;
    @Autowired
    private CurrencyField<Number> prezzoOraField;
    @Autowired
    private TwinColumn<Appezzamento> appezzamentiField;
    @Autowired
    private CollectionContainer<Appezzamento> appezzamentoesDc;
    @Autowired
    private DataContext dataContext;


    @Subscribe
    public void onInitEntity(InitEntityEvent<Attivita> event) {
        // viene eseguito solo nel caso di nuova entità
        event.getEntity().setDaFatturare(true);
    }
    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if(Objects.isNull(getEditedEntity().getCliente())){
            clienteField.setVisible(true);
        }else {
            if(Objects.nonNull(getEditedEntity().getCliente().getAppezzamenti())){
                log.info("Appezzamenti " + getEditedEntity().getCliente().getAppezzamenti().toString());
                List<Appezzamento> aps = dataManager.load(Appezzamento.class)
                                .query("select e from Appezzamento e where e.cliente = :cliente")
                                        .parameter("cliente",getEditedEntity().getCliente() )
                                                .list();
                appezzamentoesDc.setItems(aps);
                appezzamentiField.setValue(getEditedEntity().getAppezzamenti());
            }
        }
        //daFatturareField.setValue(true);
    }

    public void ricalcoloPrezzo() {
        if(Objects.isNull(getEditedEntity().getLavorazione())) {
            unitaMisuraField.setValue(null);
            valoreField.setCaption("Prezzo UM");
            qtaField.setCaption("Qta");
        }
        if(Objects.nonNull(getEditedEntity().getLavorazione()) && Objects.nonNull(dataField.getValue())){
            prezzoLavorazione = dataManager.load(PrezzoLavorazione.class)
                    .condition(
                            LogicalCondition.and(
                                    PropertyCondition.lessOrEqual("dataInizio",dataField.getValue()),
                                    PropertyCondition.greaterOrEqual("dataFine",dataField.getValue()),
                                    PropertyCondition.equal("lavorazione",getEditedEntity().getLavorazione())
                            )
                    )
                    .one();
            unitaMisuraField.setValue(prezzoLavorazione.getUnitaDiMisura());
            valoreField.setCaption("Prezzo " + prezzoLavorazione.getUnitaDiMisura());
            qtaField.setCaption("" + prezzoLavorazione.getUnitaDiMisura());
            if(Objects.nonNull(durataField.getValue()))
                prezzoOraField.setValue(prezzoLavorazione.getPrezzoOra()*durataField.getValue());

            if(Objects.nonNull(qtaField.getValue()))
                valoreField.setValue(prezzoLavorazione.getPrezzo()*qtaField.getValue());

        }
    }

    @Subscribe("oraInizioField")
    public void onOraInizioFieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        if(Objects.nonNull(event.getValue()) && Objects.nonNull(oraFineField.getValue())){
            Double time =  ((event.getValue().getTime() - oraFineField.getValue().getTime())/3600000.0);
            oreCollaboratoreField.setValue(Math.round(time*100.0)/100.0);
            durataField.setValue(Math.round(time*100.0)/100.0);
            ricalcoloPrezzo();
        }
    }

    @Subscribe("oraFineField")
    public void onOraFineFieldValueChange(HasValue.ValueChangeEvent<Date> event) {
        if(Objects.nonNull(event.getValue()) && Objects.nonNull(oraInizioField.getValue())){
        Double time =  ((event.getValue().getTime() - oraInizioField.getValue().getTime())/3600000.0);
            oreCollaboratoreField.setValue(Math.round(time*100.0)/100.0);
            durataField.setValue(Math.round(time*100.0)/100.0);
            ricalcoloPrezzo();
        }
    }

    @Subscribe("dataField")
    public void onDataFieldValueChange(HasValue.ValueChangeEvent<LocalDate> event) {
        ricalcoloPrezzo();
    }



    @Subscribe("lavorazioneField")
    public void onLavorazioneFieldValueChange(HasValue.ValueChangeEvent<Lavorazione> event) {
        if(Objects.nonNull(getEditedEntity().getCliente())){
            ricalcoloPrezzo();
        }else{
            notifications.create()
                    .withCaption("Selezione lavorazione")
                    .withDescription("Cliente non selezionato")
                    .show();
        }
    }

    @Subscribe("clienteField")
    public void onClienteFieldValueChange(HasValue.ValueChangeEvent<Cliente> event) {

        ricalcoloPrezzo();
        if(Objects.nonNull(event.getValue())){
            lavorazioneField.setEditable(true);
            appezzamentoesDc.setItems(event.getValue().getAppezzamenti());
        }else{
            lavorazioneField.setEditable(false);
            List<Appezzamento> empty = new ArrayList<>();
            appezzamentoesDc.setItems(empty);
        }
    }

    @Subscribe("qtaField")
    public void onQtaFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        ricalcoloPrezzo();
    }

    @Subscribe("durataField")
    public void onDurataFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        ricalcoloPrezzo();
    }


    @Subscribe("appezzamentiField")
    public void onAppezzamentiFieldValueChange(HasValue.ValueChangeEvent<Collection<Appezzamento>> event) {
        List<Appezzamento> app = new ArrayList(appezzamentiField.getValue());
        getEditedEntity().setAppezzamenti(app);
    }

    /*
    @Install(to = "appezzamentiTable.add", subject = "screenOptionsSupplier")
    private ScreenOptions appezzamentiTableAddScreenOptionsSupplier() {
        return new MapScreenOptions(ParamsMap.of("cliente", getEditedEntity().getCliente()));
    }
*/

}