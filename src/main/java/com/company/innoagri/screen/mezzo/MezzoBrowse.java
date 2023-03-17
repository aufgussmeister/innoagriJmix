package com.company.innoagri.screen.mezzo;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Mezzo;

@UiController("Mezzo.browse")
@UiDescriptor("mezzo-browse.xml")
@LookupComponent("mezzoesTable")
public class MezzoBrowse extends StandardLookup<Mezzo> {
}