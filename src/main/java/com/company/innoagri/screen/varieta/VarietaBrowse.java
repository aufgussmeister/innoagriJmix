package com.company.innoagri.screen.varieta;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Varieta;

@UiController("Varieta.browse")
@UiDescriptor("varieta-browse.xml")
@LookupComponent("varietasTable")
public class VarietaBrowse extends StandardLookup<Varieta> {
}