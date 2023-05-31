package com.company.innoagri.screen.campagnatrattamenti;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.CampagnaTrattamenti;

@UiController("CampagnaTrattamenti.edit")
@UiDescriptor("campagna-trattamenti-edit.xml")
@EditedEntityContainer("campagnaTrattamentiDc")
public class CampagnaTrattamentiEdit extends StandardEditor<CampagnaTrattamenti> {
}