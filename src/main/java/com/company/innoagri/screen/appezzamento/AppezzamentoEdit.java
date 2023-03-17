package com.company.innoagri.screen.appezzamento;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Appezzamento;

@UiController("Appezzamento.edit")
@UiDescriptor("appezzamento-edit.xml")
@EditedEntityContainer("appezzamentoDc")
public class AppezzamentoEdit extends StandardEditor<Appezzamento> {
}