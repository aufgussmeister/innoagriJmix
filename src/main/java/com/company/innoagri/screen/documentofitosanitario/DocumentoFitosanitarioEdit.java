package com.company.innoagri.screen.documentofitosanitario;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.DocumentoFitosanitario;

@UiController("DocumentoFitosanitario.edit")
@UiDescriptor("documento-fitosanitario-edit.xml")
@EditedEntityContainer("documentoFitosanitarioDc")
public class DocumentoFitosanitarioEdit extends StandardEditor<DocumentoFitosanitario> {
}