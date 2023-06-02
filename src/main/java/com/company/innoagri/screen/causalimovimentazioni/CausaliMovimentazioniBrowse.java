package com.company.innoagri.screen.causalimovimentazioni;

import io.jmix.ui.screen.*;
import com.company.innoagri.entity.CausaliMovimentazioni;

@UiController("CausaliMovimentazioni.browse")
@UiDescriptor("causali-movimentazioni-browse.xml")
@LookupComponent("causaliMovimentazionisTable")
public class CausaliMovimentazioniBrowse extends StandardLookup<CausaliMovimentazioni> {
}