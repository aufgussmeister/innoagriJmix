package com.company.innoagri.screen.attivita;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Attivita;

@UiController("Attivita.browse")
@UiDescriptor("attivita-browse.xml")
@LookupComponent("attivitasTable")
public class AttivitaBrowse extends StandardLookup<Attivita> {
}