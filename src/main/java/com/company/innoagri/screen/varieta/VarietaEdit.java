package com.company.innoagri.screen.varieta;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Varieta;

@UiController("Varieta.edit")
@UiDescriptor("varieta-edit.xml")
@EditedEntityContainer("varietaDc")
public class VarietaEdit extends StandardEditor<Varieta> {
}