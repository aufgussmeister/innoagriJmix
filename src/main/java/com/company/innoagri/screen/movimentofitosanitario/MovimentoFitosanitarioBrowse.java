package com.company.innoagri.screen.movimentofitosanitario;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.MovimentoFitosanitario;

@UiController("MovimentoFitosanitario.browse")
@UiDescriptor("movimento-fitosanitario-browse.xml")
@LookupComponent("movimentoFitosanitariosTable")
public class MovimentoFitosanitarioBrowse extends StandardLookup<MovimentoFitosanitario> {
}