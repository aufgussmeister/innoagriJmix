package com.company.innoagri.screen.documentofitosanitario;

import com.company.innoagri.entity.Cliente;
import com.company.innoagri.entity.TipoDocumentoFitosanitario;
import com.company.innoagri.screen.attivita.AttivitaEdit;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.core.security.SystemAuthenticator;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.DocumentoFitosanitario;
import org.apache.poi.ss.formula.functions.Today;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Objects;

@UiController("DocumentoFitosanitario.edit")
@UiDescriptor("documento-fitosanitario-edit.xml")
@EditedEntityContainer("documentoFitosanitarioDc")

public class DocumentoFitosanitarioEdit extends StandardEditor<DocumentoFitosanitario> {

    @Autowired
    private SystemAuthenticator systemAuthenticator;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    private static final Logger log = LoggerFactory.getLogger(AttivitaEdit.class);

    @Autowired
    private TextField<String> tenantField;
    String tipoMovimento = "Edit";
    @Autowired
    private ComboBox<TipoDocumentoFitosanitario> tipoDocumentoField;
    @Autowired
    private DateField<LocalDate> dataField;
    @Autowired
    private EntityPicker<Cliente> clienteField;
    @Autowired
    private Label titoloPaginaLbl;

    public void setTipoMovimento(String value){
        if(Objects.nonNull(value))
            tipoMovimento = value;

    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        systemAuthenticator.withUser("admin", () -> {
            UserDetails user = currentAuthentication.getUser();
            tenantField.setVisible(true);
            return true;
        });

        if(tipoMovimento.equals("CaricoDDT")){
            tipoDocumentoField.setValue(TipoDocumentoFitosanitario.DDT_FORNITORE);
            tipoDocumentoField.setEditable(false);
            clienteField.setVisible(false);
            //dataField.setValue(LocalDate.now());
            getEditedEntity().setData(LocalDate.now());
            titoloPaginaLbl.setValue("CARICAMENTO DDT FORNITORE");
        }
        if(Objects.nonNull(getEditedEntity().getTipoDocumento()) && getEditedEntity().getTipoDocumento().equals(TipoDocumentoFitosanitario.DDT_FORNITORE)){
            clienteField.setVisible(false);
            tipoDocumentoField.setEditable(false);
        }
    }



}