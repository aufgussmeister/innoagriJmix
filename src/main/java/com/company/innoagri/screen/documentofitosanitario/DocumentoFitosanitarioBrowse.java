package com.company.innoagri.screen.documentofitosanitario;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.DocumentoFitosanitario;

@UiController("DocumentoFitosanitario.browse")
@UiDescriptor("documento-fitosanitario-browse.xml")
@LookupComponent("documentoFitosanitariosTable")
public class DocumentoFitosanitarioBrowse extends StandardLookup<DocumentoFitosanitario> {
}