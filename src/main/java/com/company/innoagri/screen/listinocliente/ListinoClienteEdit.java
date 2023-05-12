package com.company.innoagri.screen.listinocliente;

import com.company.innoagri.entity.Lavorazione;
import com.company.innoagri.entity.PrezzoLavorazione;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.component.ValuePicker;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.ListinoCliente;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@UiController("ListinoCliente.edit")
@UiDescriptor("listino-cliente-edit.xml")
@EditedEntityContainer("listinoClienteDc")
public class ListinoClienteEdit extends StandardEditor<ListinoCliente> {
    @Autowired
    private TextField prezzoOraLavField;
    @Autowired
    private TextField prezzoUmLavField;
    @Autowired
    private TextField prezzoUmField;
    @Autowired
    private TextField<Double> prezzoClienteField;


    @Subscribe("lavorazioneField")
    public void onLavorazioneFieldValueChange(HasValue.ValueChangeEvent<Lavorazione> event) {
        if(Objects.nonNull(event.getValue())){
            List<PrezzoLavorazione> pl =  event.getValue().getPrezzi();
            pl.sort(new Comparator<PrezzoLavorazione>() {
                @Override
                public int compare(PrezzoLavorazione o1, PrezzoLavorazione o2) {
                    return (int) (o2.getDataInizio().toEpochDay() - o1.getDataInizio().toEpochDay());
                }
            });
            prezzoOraLavField.setValue(pl.get(0).getPrezzoOra().toString());
            prezzoUmLavField.setValue(pl.get(0).getPrezzo().toString());
            prezzoUmLavField.setCaption("Prezzo " + pl.get(0).getUnitaDiMisura());
            prezzoUmField.setCaption("Prezzo " + pl.get(0).getUnitaDiMisura());
            prezzoClienteField.setEditable(true);
            prezzoUmField.setEditable(true);
        }else{
            prezzoClienteField.setEditable(false);
            prezzoUmField.setEditable(false);
            prezzoClienteField.setValue(null);
            prezzoOraLavField.setValue(null);
            prezzoUmLavField.setValue(null);
            prezzoUmField.setValue(null);
            prezzoUmLavField.setCaption("Prezzo UM");
        }
    }



}