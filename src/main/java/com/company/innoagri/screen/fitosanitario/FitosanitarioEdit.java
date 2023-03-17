package com.company.innoagri.screen.fitosanitario;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Fitosanitario;

@UiController("Fitosanitario.edit")
@UiDescriptor("fitosanitario-edit.xml")
@EditedEntityContainer("fitosanitarioDc")
public class FitosanitarioEdit extends StandardEditor<Fitosanitario> {
}