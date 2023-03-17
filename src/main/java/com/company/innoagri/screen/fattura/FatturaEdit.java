package com.company.innoagri.screen.fattura;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Fattura;

@UiController("Fattura.edit")
@UiDescriptor("fattura-edit.xml")
@EditedEntityContainer("fatturaDc")
public class FatturaEdit extends StandardEditor<Fattura> {
}