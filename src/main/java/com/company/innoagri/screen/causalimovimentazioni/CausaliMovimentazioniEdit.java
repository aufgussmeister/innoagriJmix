package com.company.innoagri.screen.causalimovimentazioni;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.CausaliMovimentazioni;

@UiController("CausaliMovimentazioni.edit")
@UiDescriptor("causali-movimentazioni-edit.xml")
@EditedEntityContainer("causaliMovimentazioniDc")
public class CausaliMovimentazioniEdit extends StandardEditor<CausaliMovimentazioni> {
}