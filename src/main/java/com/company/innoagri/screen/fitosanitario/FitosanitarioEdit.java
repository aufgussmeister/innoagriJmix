package com.company.innoagri.screen.fitosanitario;

import com.company.innoagri.entity.FitosanitarioNazionale;
import io.jmix.ui.component.CheckBox;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.ScreenFacet;
import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Fitosanitario;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Fitosanitario.edit")
@UiDescriptor("fitosanitario-edit.xml")
@EditedEntityContainer("fitosanitarioDc")
public class FitosanitarioEdit extends StandardEditor<Fitosanitario> {
    @Autowired
    private TextField<String> prodottoField;
    @Autowired
    private TextField<String> impresaField;
    @Autowired
    private TextField<String> attivitaField;
    @Autowired
    private CheckBox attivoField;
    @Autowired
    private TextField<String> sostanzeAttiveField;

    @Subscribe("fitosanitarioNazionaleField")
    public void onFitosanitarioNazionaleFieldValueChange(HasValue.ValueChangeEvent<FitosanitarioNazionale> event) {
       FitosanitarioNazionale fn =  event.getValue();
       prodottoField.setValue(fn.getProdotto());
       impresaField.setValue(fn.getImpresa());
       attivitaField.setValue(fn.getAttivita());
       sostanzeAttiveField.setValue(fn.getSostanzeAttive());
    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<Fitosanitario> event) {
        // viene eseguito solo nel caso di nuova entità
        event.getEntity().setAttivo(true);
    }

}