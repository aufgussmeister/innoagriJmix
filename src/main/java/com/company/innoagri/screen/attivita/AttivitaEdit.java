package com.company.innoagri.screen.attivita;

import com.company.innoagri.entity.*;
import io.jmix.core.DataManager;
import io.jmix.core.common.util.ParamsMap;
import io.jmix.core.querycondition.LogicalCondition;
import io.jmix.core.querycondition.PropertyCondition;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.list.AddAction;
import io.jmix.ui.component.*;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.screen.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

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

    @Named("appezzamentiTable.add")
    private AddAction<Appezzamento> appezzamentiTableAdd;

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        if(Objects.isNull(getEditedEntity().getCliente())){
            clienteField.setVisible(true);
        }
        daFatturareField.setValue(true);
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
            valoreField.setCaption("prezzo " + prezzoLavorazione.getUnitaDiMisura());
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
        }else{
            lavorazioneField.setEditable(false);
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

    /*
    @Install(to = "appezzamentiTable.add", subject = "screenOptionsSupplier")
    private ScreenOptions appezzamentiTableAddScreenOptionsSupplier() {
        return new MapScreenOptions(ParamsMap.of("cliente", getEditedEntity().getCliente()));
    }
*/

}