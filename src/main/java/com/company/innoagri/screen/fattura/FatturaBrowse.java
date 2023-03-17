package com.company.innoagri.screen.fattura;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Fattura;

@UiController("Fattura.browse")
@UiDescriptor("fattura-browse.xml")
@LookupComponent("fatturasTable")
public class FatturaBrowse extends StandardLookup<Fattura> {
}