package com.company.innoagri.app;

import com.company.innoagri.entity.CausaliMovimentazioni;
import com.company.innoagri.entity.Fitosanitario;
import com.company.innoagri.entity.MovimentoFitosanitario;
import io.jmix.core.DataManager;
import io.jmix.ui.component.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class CalcoloGiacenzaFitosanitarioBean {
    @Autowired
    private DataManager dataManager;
    public Double  giacenzaQta(Fitosanitario fs){
        List<MovimentoFitosanitario> mfs = dataManager.load(MovimentoFitosanitario.class)
                .query("select e from MovimentoFitosanitario e where e.fitosanitario = :fitosanitario")
                .parameter("fitosanitario", fs)
                .list();
        AtomicReference<Double> qta = new AtomicReference<>(0.0);
        mfs.forEach(movimentoFitosanitario -> {
            qta.set(qta.get() + movimentoFitosanitario.getQuantita() * movimentoFitosanitario.getCausale().getSegno());
        });
        return Math.floor(qta.get() * 100) / 100;
    }
    public Double  giacenzaQtaMin(Fitosanitario fs){
        CausaliMovimentazioni cm = dataManager.load(CausaliMovimentazioni.class)
                .query("select e from CausaliMovimentazioni e where e.causale='Acquisto'")
                .one();

        List<MovimentoFitosanitario> mfs = dataManager.load(MovimentoFitosanitario.class)
                .query("select e from MovimentoFitosanitario e where e.fitosanitario = :fitosanitario")
                .parameter("fitosanitario", fs)
                .list();

        AtomicReference<Double> qtaMin = new AtomicReference<>(0.0);
        mfs.forEach(movimentoFitosanitario -> {
            if(movimentoFitosanitario.getCausale().equals(cm)){
                qtaMin.set(qtaMin.get() + movimentoFitosanitario.getQuantita()*movimentoFitosanitario.getCausale().getSegno());
            }else{
                qtaMin.set(qtaMin.get() + movimentoFitosanitario.getQuantitaMin()*movimentoFitosanitario.getCausale().getSegno());
            }
        });
        return Math.floor(qtaMin.get() * 100) / 100;    }
}