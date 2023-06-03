package com.company.innoagri.screen.fornitore;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Fornitore;

@UiController("Fornitore.browse")
@UiDescriptor("fornitore-browse.xml")
@LookupComponent("fornitoresTable")
public class FornitoreBrowse extends StandardLookup<Fornitore> {
}