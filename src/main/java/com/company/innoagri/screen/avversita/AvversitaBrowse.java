package com.company.innoagri.screen.avversita;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Avversita;

@UiController("Avversita.browse")
@UiDescriptor("avversita-browse.xml")
@LookupComponent("avversitasTable")
public class AvversitaBrowse extends StandardLookup<Avversita> {
}