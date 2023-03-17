package com.company.innoagri.screen.prezzolavorazione;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.PrezzoLavorazione;

@UiController("PrezzoLavorazione.edit")
@UiDescriptor("prezzo-lavorazione-edit.xml")
@EditedEntityContainer("prezzoLavorazioneDc")
public class PrezzoLavorazioneEdit extends StandardEditor<PrezzoLavorazione> {
}