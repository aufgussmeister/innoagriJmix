package com.company.innoagri.screen.movimentofitosanitario;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.MovimentoFitosanitario;

@UiController("MovimentoFitosanitario.edit")
@UiDescriptor("movimento-fitosanitario-edit.xml")
@EditedEntityContainer("movimentoFitosanitarioDc")
public class MovimentoFitosanitarioEdit extends StandardEditor<MovimentoFitosanitario> {
}