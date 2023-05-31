package com.company.innoagri.screen.prodottopianificato;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.ProdottoPianificato;

@UiController("ProdottoPianificato.edit")
@UiDescriptor("prodotto-pianificato-edit.xml")
@EditedEntityContainer("prodottoPianificatoDc")
public class ProdottoPianificatoEdit extends StandardEditor<ProdottoPianificato> {
}