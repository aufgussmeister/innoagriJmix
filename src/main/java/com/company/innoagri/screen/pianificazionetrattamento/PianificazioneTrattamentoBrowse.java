package com.company.innoagri.screen.pianificazionetrattamento;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.PianificazioneTrattamento;

@UiController("PianificazioneTrattamento.browse")
@UiDescriptor("pianificazione-trattamento-browse.xml")
@LookupComponent("pianificazioneTrattamentoesTable")
public class PianificazioneTrattamentoBrowse extends StandardLookup<PianificazioneTrattamento> {
}