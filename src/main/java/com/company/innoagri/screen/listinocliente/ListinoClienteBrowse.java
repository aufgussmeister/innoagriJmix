package com.company.innoagri.screen.listinocliente;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.ListinoCliente;

@UiController("ListinoCliente.browse")
@UiDescriptor("listino-cliente-browse.xml")
@LookupComponent("listinoClientesTable")
public class ListinoClienteBrowse extends StandardLookup<ListinoCliente> {
}