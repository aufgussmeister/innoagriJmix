package com.company.innoagri.screen.lavorazione;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Lavorazione;

@UiController("Lavorazione.edit")
@UiDescriptor("lavorazione-edit.xml")
@EditedEntityContainer("lavorazioneDc")
public class LavorazioneEdit extends StandardEditor<Lavorazione> {
}