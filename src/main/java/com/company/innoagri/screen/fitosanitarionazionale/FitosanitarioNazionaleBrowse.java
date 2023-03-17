package com.company.innoagri.screen.fitosanitarionazionale;

import io.jmix.core.FileRef;
import io.jmix.core.FileStorage;
import io.jmix.dataimport.InputDataFormat;
import io.jmix.dataimport.configuration.ImportTransactionStrategy;
import io.jmix.dataimport.extractor.data.ImportedData;
import io.jmix.dataimport.*;
import io.jmix.dataimport.result.ImportResult;
import io.jmix.dataimport.configuration.ImportConfiguration;
import io.jmix.ui.Dialogs;
import io.jmix.ui.app.inputdialog.DialogActions;
import io.jmix.ui.app.inputdialog.DialogOutcome;
import io.jmix.ui.app.inputdialog.InputParameter;
import io.jmix.ui.component.Button;
import io.jmix.ui.screen.*;
import com.company.innoagri.entity.FitosanitarioNazionale;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@UiController("FitosanitarioNazionale.browse")
@UiDescriptor("fitosanitario-nazionale-browse.xml")
@LookupComponent("fitosanitarioNazionalesTable")
public class FitosanitarioNazionaleBrowse extends StandardLookup<FitosanitarioNazionale> {
    @Autowired
    private Button importFito;
    @Autowired
    private Dialogs dialogs;
    @Autowired
    private FileStorage fileStorage;
    @Autowired
    private DataImporter dataImporter;
    @Subscribe("importFito")
    public void onImportFitoClick(Button.ClickEvent event) {



        dialogs.createInputDialog(this)
                .withCaption("Scegli File")
                .withParameters(
                        InputParameter.fileParameter("file").withCaption("XLSX DB Fitosanitari")

                )
                .withActions(DialogActions.OK_CANCEL)
                .withCloseListener(closeEvent -> {
                    if (closeEvent.closedWith(DialogOutcome.OK)) {
                        FileRef fileRef = closeEvent.getValue("file");
                        InputStream inputStream = fileStorage.openStream(fileRef);
                        try {
                            byte[] bytes =  IOUtils.toByteArray(inputStream);
                            ImportConfiguration importConfiguration = ImportConfiguration.builder(FitosanitarioNazionale.class, InputDataFormat.XLSX)
                                    .addSimplePropertyMapping("id", "NUMERO_REGISTRAZIONE")
                                    .addSimplePropertyMapping("prodotto", "PRODOTTO")
                                    .addSimplePropertyMapping("impresa", "IMPRESA")
                                    .addSimplePropertyMapping("dataRegistrazione", "DATA_REGISTRAZIONE")
                                    .addSimplePropertyMapping("scadenzaAutorizzazione", "SCADENZA_AUTORIZZAZIONE")
                                    .addSimplePropertyMapping("attivita", "ATTIVITA")
                                    .addSimplePropertyMapping("codiceFormulazione", "CODICE_FORMULAZIONE")
                                    .addSimplePropertyMapping("descrizioneFormulazione", "DESCRIZIONE_FORMULAZIONE")
                                    .addSimplePropertyMapping("sostanzeAttive", "SOSTANZE_ATTIVE")
                                    .addSimplePropertyMapping("contenutoPerCentoGrammiDiProdotto", "CONTENUTO_PER_100G_DI_PRODOTTO")
                                    .addSimplePropertyMapping("statoAmministrativo", "STATO_AMMINISTRATIVO")
                                    .addSimplePropertyMapping("motivoRevoca", "MOTIVO_DELLA_REVOCA")
                                    .addSimplePropertyMapping("dataDecorrenzaRevoca", "DATA_DECORRENZA_REVOCA")
                                    .withDateFormat("dd/MM/yy")
                                    .withTransactionStrategy(ImportTransactionStrategy.TRANSACTION_PER_ENTITY)
                                    .build();
                           ImportResult result = dataImporter.importData(importConfiguration, bytes);
                        } catch (IOException e) {
                            throw new RuntimeException("Error reading file storage content to byte array", e);
                        }

                    }
                })
                .show();





    }

}