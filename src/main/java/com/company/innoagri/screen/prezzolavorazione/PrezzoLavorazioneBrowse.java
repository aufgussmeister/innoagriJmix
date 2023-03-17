package com.company.innoagri.screen.prezzolavorazione;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.PrezzoLavorazione;

@UiController("PrezzoLavorazione.browse")
@UiDescriptor("prezzo-lavorazione-browse.xml")
@LookupComponent("prezzoLavorazionesTable")
public class PrezzoLavorazioneBrowse extends StandardLookup<PrezzoLavorazione> {
}