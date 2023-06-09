package com.company.innoagri.screen.fitosanitario;

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
    @Install(to = "fitosanitariosTable.qta", subject = "columnGenerator")
    private Component tableGeneratedColumnFullNameColumnGenerator(Fitosanitario fitosanitario) {
        List<MovimentoFitosanitario> mfs = dataManager.load(MovimentoFitosanitario.class)
                .query("select e from MovimentoFitosanitario e where e.fitosanitario = :fitosanitario")
                .parameter("fitosanitario", fitosanitario)
                .list();

        AtomicReference<Double> qta = new AtomicReference<>(0.0);
        mfs.forEach(movimentoFitosanitario -> {
            qta.set(qta.get() + movimentoFitosanitario.getQuantita() * movimentoFitosanitario.getCausale().getSegno());
        });
        return new Table.PlainTextCell(qta.get().toString());
    }

    @Install(to = "fitosanitariosTable.qtaMin", subject = "columnGenerator")
    private Component tableGeneratedColumnQtaMinColumnGenerator(Fitosanitario fitosanitario) {
        CausaliMovimentazioni cm = dataManager.load(CausaliMovimentazioni.class)
                .query("select e from CausaliMovimentazioni e where e.causale='Acquisto'")
                .one();

        List<MovimentoFitosanitario> mfs = dataManager.load(MovimentoFitosanitario.class)
                .query("select e from MovimentoFitosanitario e where e.fitosanitario = :fitosanitario")
                .parameter("fitosanitario", fitosanitario)
                .list();

        AtomicReference<Double> qtaMin = new AtomicReference<>(0.0);
        mfs.forEach(movimentoFitosanitario -> {
            if(movimentoFitosanitario.getCausale().equals(cm)){
                qtaMin.set(qtaMin.get() + movimentoFitosanitario.getQuantita()*movimentoFitosanitario.getCausale().getSegno());
            }else{
                qtaMin.set(qtaMin.get() + movimentoFitosanitario.getQuantitaMin()*movimentoFitosanitario.getCausale().getSegno());
            }
        });
        return new Table.PlainTextCell(qtaMin.get().toString());
    }
}