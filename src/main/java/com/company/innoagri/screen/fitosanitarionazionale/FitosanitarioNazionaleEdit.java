package com.company.innoagri.screen.fitosanitarionazionale;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.FitosanitarioNazionale;

@UiController("FitosanitarioNazionale.edit")
@UiDescriptor("fitosanitario-nazionale-edit.xml")
@EditedEntityContainer("fitosanitarioNazionaleDc")
public class FitosanitarioNazionaleEdit extends StandardEditor<FitosanitarioNazionale> {
}