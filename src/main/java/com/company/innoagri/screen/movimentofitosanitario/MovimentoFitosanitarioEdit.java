package com.company.innoagri.screen.movimentofitosanitario;

import com.company.innoagri.entity.CausaliMovimentazioni;
import com.company.innoagri.entity.Fitosanitario;
import com.company.innoagri.entity.TipoDocumentoFitosanitario;
import com.company.innoagri.screen.attivita.AttivitaEdit;
import io.jmix.core.DataManager;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.MovimentoFitosanitario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Objects;

@UiController("MovimentoFitosanitario.edit")
@UiDescriptor("movimento-fitosanitario-edit.xml")
@EditedEntityContainer("movimentoFitosanitarioDc")
public class MovimentoFitosanitarioEdit extends StandardEditor<MovimentoFitosanitario> {

    @Autowired
    private DataManager dataManager;
    @Autowired
    private EntityPicker<CausaliMovimentazioni> causaleField;
    @Autowired
    private DateField<LocalDate> dataField;
    @Autowired
    private TextField<Double> quantitaMinField;
    @Autowired
    private EntityPicker<Fitosanitario> fitosanitarioField;

    private static final Logger log = LoggerFactory.getLogger(AttivitaEdit.class);
    @Autowired
    private TextField<Double> quantitaField;
    @Autowired
    private TextField<Double> prezzoUnitario;
    @Autowired
    private TextField<Double> valoreField;


    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
     if(getEditedEntity().getDocumentoFitosanitario().getTipoDocumento() == TipoDocumentoFitosanitario.DDT_FORNITORE){
         CausaliMovimentazioni cm = dataManager.load(CausaliMovimentazioni.class)
                 .query("select e from CausaliMovimentazioni e where e.causale='Acquisto'")
                 .one();
         if(Objects.nonNull(cm))
            causaleField.setValue(cm);
         getEditedEntity().setData(getEditedEntity().getDocumentoFitosanitario().getData());
         quantitaMinField.setVisible(false);
         dataField.setEditable(false);
     }

    }

    @Subscribe("fitosanitarioField")
    public void onFitosanitarioFieldValueChange(HasValue.ValueChangeEvent<Fitosanitario> event) {
        if(Objects.nonNull(event.getValue()) && Objects.nonNull(event.getValue().getUnitaMisua())){
            quantitaField.setCaption(event.getValue().getUnitaMisua().toString());
            prezzoUnitario.setCaption("€/"+event.getValue().getUnitaMisua());
        }else{
            quantitaField.setCaption("Quantità");
        }

    }

    @Subscribe("quantitaField")
    public void onQuantitaFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        if(Objects.nonNull(getEditedEntity().getQuantita()) && Objects.nonNull(getEditedEntity().getValore()) && getEditedEntity().getQuantita() > 0 && getEditedEntity().getValore() > 0)
            prezzoUnitario.setValue(getEditedEntity().getValore()/getEditedEntity().getQuantita());
    }

    @Subscribe("valoreField")
    public void onValoreFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        if(Objects.nonNull(getEditedEntity().getQuantita()) && Objects.nonNull(getEditedEntity().getValore()) && getEditedEntity().getQuantita() > 0 && getEditedEntity().getValore() > 0)
            prezzoUnitario.setValue(getEditedEntity().getValore()/getEditedEntity().getQuantita());
    }
}