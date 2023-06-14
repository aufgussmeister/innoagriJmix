package com.company.innoagri.screen.fitosanitario;

import com.company.innoagri.app.CalcoloGiacenzaFitosanitarioBean;
import com.company.innoagri.entity.CausaliMovimentazioni;
import com.company.innoagri.entity.MovimentoFitosanitario;
import io.jmix.core.DataManager;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.Table;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Fitosanitario;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;



@UiController("Fitosanitario.browse")
@UiDescriptor("fitosanitario-browse.xml")
@LookupComponent("fitosanitariosTable")


public class FitosanitarioBrowse extends StandardLookup<Fitosanitario> {
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CalcoloGiacenzaFitosanitarioBean calcoloGiacenzaFit;
    @Install(to = "fitosanitariosTable.qta", subject = "columnGenerator")
    private Component tableGeneratedColumnFullNameColumnGenerator(Fitosanitario fitosanitario) {
        return new Table.PlainTextCell(calcoloGiacenzaFit.giacenzaQta(fitosanitario).toString());
    }

    @Install(to = "fitosanitariosTable.qtaMin", subject = "columnGenerator")
    private Component tableGeneratedColumnQtaMinColumnGenerator(Fitosanitario fitosanitario) {
        return new Table.PlainTextCell(calcoloGiacenzaFit.giacenzaQtaMin(fitosanitario).toString());
    }
}