package com.company.innoagri.screen.cliente;

import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.Label;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;

@UiController("Cliente.edit")
@UiDescriptor("cliente-edit.xml")
@EditedEntityContainer("clienteDc")
public class ClienteEdit extends StandardEditor<Cliente> {


    @Autowired
    private Label clienteLbl;
    @Autowired
    private Notifications notifications;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if(!getEditedEntity().getCliente().contains("null null"))
            clienteLbl.setValue(getEditedEntity().getCliente());
    }

    @Subscribe("listiniTable.importaLavorazioni")
    public void onImportaLavorazioni(Action.ActionPerformedEvent event) {
        notifications.create()
                .withCaption("Funzione non ancora implementata")
                .withType(Notifications.NotificationType.HUMANIZED)
                .show();
    }

}