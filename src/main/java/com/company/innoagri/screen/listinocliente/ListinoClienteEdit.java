package com.company.innoagri.screen.listinocliente;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.ListinoCliente;

@UiController("ListinoCliente.edit")
@UiDescriptor("listino-cliente-edit.xml")
@EditedEntityContainer("listinoClienteDc")
public class ListinoClienteEdit extends StandardEditor<ListinoCliente> {
}