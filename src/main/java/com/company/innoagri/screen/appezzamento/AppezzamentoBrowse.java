package com.company.innoagri.screen.appezzamento;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Appezzamento;

@UiController("Appezzamento.browse")
@UiDescriptor("appezzamento-browse.xml")
@LookupComponent("appezzamentoesTable")
public class AppezzamentoBrowse extends StandardLookup<Appezzamento> {
    @Subscribe
    public void onAfterShow(AfterShowEvent event) {

    }
}