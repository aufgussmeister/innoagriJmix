package com.company.innoagri.screen.cliente;

import io.jmix.ui.component.Label;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("Cliente.edit")
@UiDescriptor("cliente-edit.xml")
@EditedEntityContainer("clienteDc")
public class ClienteEdit extends StandardEditor<Cliente> {


    @Autowired
    private Label clienteLbl;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if(!getEditedEntity().getCliente().contains("null null"))
            clienteLbl.setValue(getEditedEntity().getCliente());
    }
}