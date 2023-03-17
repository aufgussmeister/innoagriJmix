package com.company.innoagri.screen.cliente;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Cliente;

@UiController("Cliente.browse")
@UiDescriptor("cliente-browse.xml")
@LookupComponent("clientesTable")
public class ClienteBrowse extends StandardLookup<Cliente> {
}