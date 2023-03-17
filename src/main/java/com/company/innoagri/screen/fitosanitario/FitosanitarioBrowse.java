package com.company.innoagri.screen.fitosanitario;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Fitosanitario;

@UiController("Fitosanitario.browse")
@UiDescriptor("fitosanitario-browse.xml")
@LookupComponent("fitosanitariosTable")
public class FitosanitarioBrowse extends StandardLookup<Fitosanitario> {
}