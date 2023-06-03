package com.company.innoagri.screen.fornitore;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Fornitore;

@UiController("Fornitore.edit")
@UiDescriptor("fornitore-edit.xml")
@EditedEntityContainer("fornitoreDc")
public class FornitoreEdit extends StandardEditor<Fornitore> {
}