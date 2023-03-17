package com.company.innoagri.screen.lavorazione;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Lavorazione;

@UiController("Lavorazione.browse")
@UiDescriptor("lavorazione-browse.xml")
@LookupComponent("lavorazionesTable")
public class LavorazioneBrowse extends StandardLookup<Lavorazione> {
}