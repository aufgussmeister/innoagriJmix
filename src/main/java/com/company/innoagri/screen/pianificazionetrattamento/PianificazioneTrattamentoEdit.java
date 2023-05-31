package com.company.innoagri.screen.pianificazionetrattamento;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.PianificazioneTrattamento;

@UiController("PianificazioneTrattamento.edit")
@UiDescriptor("pianificazione-trattamento-edit.xml")
@EditedEntityContainer("pianificazioneTrattamentoDc")
public class PianificazioneTrattamentoEdit extends StandardEditor<PianificazioneTrattamento> {
}