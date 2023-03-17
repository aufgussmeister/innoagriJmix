package com.company.innoagri.screen.attivita;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Attivita;

@UiController("Attivita.edit")
@UiDescriptor("attivita-edit.xml")
@EditedEntityContainer("attivitaDc")
public class AttivitaEdit extends StandardEditor<Attivita> {
}