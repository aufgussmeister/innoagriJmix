package com.company.innoagri.screen.avversita;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Avversita;

@UiController("Avversita.edit")
@UiDescriptor("avversita-edit.xml")
@EditedEntityContainer("avversitaDc")
public class AvversitaEdit extends StandardEditor<Avversita> {
}