package com.company.innoagri.screen.campagnatrattamenti;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.CampagnaTrattamenti;

@UiController("CampagnaTrattamenti.browse")
@UiDescriptor("campagna-trattamenti-browse.xml")
@LookupComponent("campagnaTrattamentisTable")
public class CampagnaTrattamentiBrowse extends StandardLookup<CampagnaTrattamenti> {
}