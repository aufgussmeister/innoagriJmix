package com.company.innoagri.screen.cliente;

import com.company.innoagri.entity.CampagnaTrattamenti;
import com.company.innoagri.entity.DocumentoFitosanitario;
import com.company.innoagri.entity.MovimentoFitosanitario;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.EntityComboBox;
import io.jmix.ui.component.GroupTable;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.Label;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@UiController("Cliente.edit")
@UiDescriptor("cliente-edit.xml")
@EditedEntityContainer("clienteDc")
public class ClienteEdit extends StandardEditor<Cliente> {


    @Autowired
    private Label clienteLbl;
    @Autowired
    private Notifications notifications;
    @Autowired
    private CollectionContainer<DocumentoFitosanitario> documentoFitosanitariosDc;
    @Autowired
    private DataManager dataManager;
    @Autowired
    private CollectionContainer<MovimentoFitosanitario> movimentoFitosanitariosDc;
    @Autowired
    private EntityComboBox<CampagnaTrattamenti> campagnaTrattamentiBox;
    @Autowired
    private GroupTable<DocumentoFitosanitario> documentoFitosanitariosTable;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if(!getEditedEntity().getCliente().contains("null null"))
            clienteLbl.setValue(getEditedEntity().getCliente());
        Collection<DocumentoFitosanitario> df = dataManager.load(DocumentoFitosanitario.class)
                .query("select e from DocumentoFitosanitario e where e.cliente = :cliente")
                .parameter("cliente", getEditedEntity())
                .list();
        documentoFitosanitariosDc.setItems(df);
        Collection<MovimentoFitosanitario> mf = dataManager.load(MovimentoFitosanitario.class)
                .query("select e from MovimentoFitosanitario e where e.appezzamento.cliente = :cliente")
                .parameter("cliente", getEditedEntity())
                .list();
        movimentoFitosanitariosDc.setItems(mf);

        LocalDate td = LocalDate.now();
        Optional<CampagnaTrattamenti> ct = dataManager.load(CampagnaTrattamenti.class)
                .query("select e from CampagnaTrattamenti e where e.anno = :anno")
                .parameter("anno", td.getYear())
                .optional();
        ct.ifPresent(campagnaTrattamenti -> campagnaTrattamentiBox.setValue(campagnaTrattamenti));
    }

    @Subscribe("listiniTable.importaLavorazioni")
    public void onImportaLavorazioni(Action.ActionPerformedEvent event) {
        notifications.create()
                .withCaption("Funzione non ancora implementata")
                .withType(Notifications.NotificationType.HUMANIZED)
                .show();
    }

    public void updateTrattamentiCampagna(){
        CampagnaTrattamenti ct = campagnaTrattamentiBox.getValue();
        Collection<DocumentoFitosanitario> df = dataManager.load(DocumentoFitosanitario.class)
                .query("select e from DocumentoFitosanitario e where e.cliente = :cliente and extract(year from e.data)  = :anno")
                .parameter("cliente", getEditedEntity())
                .parameter("anno", ct.getAnno())
                .list();
        documentoFitosanitariosDc.setItems(df);
        Collection<MovimentoFitosanitario> mf = dataManager.load(MovimentoFitosanitario.class)
                .query("select e from MovimentoFitosanitario e where e.appezzamento.cliente = :cliente and extract(year from e.data) = :anno")
                .parameter("cliente", getEditedEntity())
                .parameter("anno", ct.getAnno())
                .list();
        movimentoFitosanitariosDc.setItems(mf);
    }

    @Subscribe("campagnaTrattamentiBox")
    public void onCampagnaTrattamentiBoxValueChange(HasValue.ValueChangeEvent<CampagnaTrattamenti> event) {
        updateTrattamentiCampagna();
    }

}