package com.company.innoagri.screen.prodottopianificato;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.ProdottoPianificato;

@UiController("ProdottoPianificato.browse")
@UiDescriptor("prodotto-pianificato-browse.xml")
@LookupComponent("prodottoPianificatoesTable")
public class ProdottoPianificatoBrowse extends StandardLookup<ProdottoPianificato> {
}