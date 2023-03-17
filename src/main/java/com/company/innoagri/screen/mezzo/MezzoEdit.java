package com.company.innoagri.screen.mezzo;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Mezzo;

@UiController("Mezzo.edit")
@UiDescriptor("mezzo-edit.xml")
@EditedEntityContainer("mezzoDc")
public class MezzoEdit extends StandardEditor<Mezzo> {
}