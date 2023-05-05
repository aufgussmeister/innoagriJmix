package com.company.innoagri.screen.appezzamento;

import com.company.innoagri.entity.Cliente;
import io.jmix.ui.App;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Appezzamento;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;

@UiController("Appezzamento.browse")
@UiDescriptor("appezzamento-browse.xml")
@LookupComponent("appezzamentoesTable")
public class AppezzamentoBrowse extends StandardLookup<Appezzamento> {
    @Autowired
    private CollectionContainer<Appezzamento> appezzamentoesDc;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {

    }

    @Subscribe
    public void onInit(InitEvent event) {
        ScreenOptions options = event.getOptions();
        if (options instanceof Cliente) {
            String message = ((Cliente) options).getCliente();
           List<Appezzamento> aps = appezzamentoesDc.getItems();
           //appezzamentoesDc.
        }
    }
}