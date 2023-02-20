//ComuniElencoRow: import @8-DD846E31
package restrict.Ad4DizionariComuniElenco;

import java.util.*;
import com.codecharge.db.*;
//End ComuniElencoRow: import

//ComuniElencoRow: class head @8-D377F9E5
public class ComuniElencoRow {
//End ComuniElencoRow: class head

//ComuniElencoRow: declare fiels @8-861E8571
    private TextField Aggiungi = new TextField("Aggiungi", "Aggiungi");
    private TextField PROVINCIA_STATO = new TextField("PROVINCIA_STATO", "PROVINCIA_STATO");
    private TextField COMUNE = new TextField("COMUNE", "COMUNE");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField CAP = new TextField("CAP", "CAP");
    private TextField PROVINCIA_DESC = new TextField("PROVINCIA_DESC", "PROVINCIA_DESC");
    private TextField SOPPRESSIONED_DATA = new TextField("SOPPRESSIONED_DATA", "SOPPRESSIONED_DATA");
    private TextField FUSIONE_DESC = new TextField("FUSIONE_DESC", "FUSIONE_DESC");
    private TextField UTENTE_AGGIORNAMENTO = new TextField("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO");
    private TextField DATA_AGGIORNAMENTO = new TextField("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End ComuniElencoRow: declare fiels

//ComuniElencoRow: constructor @8-EEFA7C7A
    public ComuniElencoRow() {
    }
//End ComuniElencoRow: constructor

//ComuniElencoRow: method(s) of Aggiungi @9-30CF9B46
    public TextField getAggiungiField() {
        return Aggiungi;
    }

    public String getAggiungi() {
        return Aggiungi.getValue();
    }

    public void setAggiungi(String value) {
        this.Aggiungi.setValue(value);
    }
//End ComuniElencoRow: method(s) of Aggiungi

//ComuniElencoRow: method(s) of PROVINCIA_STATO @31-4F0EF6DC
    public TextField getPROVINCIA_STATOField() {
        return PROVINCIA_STATO;
    }

    public String getPROVINCIA_STATO() {
        return PROVINCIA_STATO.getValue();
    }

    public void setPROVINCIA_STATO(String value) {
        this.PROVINCIA_STATO.setValue(value);
    }
//End ComuniElencoRow: method(s) of PROVINCIA_STATO

//ComuniElencoRow: method(s) of COMUNE @32-B41017A6
    public TextField getCOMUNEField() {
        return COMUNE;
    }

    public String getCOMUNE() {
        return COMUNE.getValue();
    }

    public void setCOMUNE(String value) {
        this.COMUNE.setValue(value);
    }
//End ComuniElencoRow: method(s) of COMUNE

//ComuniElencoRow: method(s) of DENOMINAZIONE @13-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End ComuniElencoRow: method(s) of DENOMINAZIONE

//ComuniElencoRow: method(s) of CAP @16-CDFF7BC8
    public TextField getCAPField() {
        return CAP;
    }

    public String getCAP() {
        return CAP.getValue();
    }

    public void setCAP(String value) {
        this.CAP.setValue(value);
    }
//End ComuniElencoRow: method(s) of CAP

//ComuniElencoRow: method(s) of PROVINCIA_DESC @12-D627FA02
    public TextField getPROVINCIA_DESCField() {
        return PROVINCIA_DESC;
    }

    public String getPROVINCIA_DESC() {
        return PROVINCIA_DESC.getValue();
    }

    public void setPROVINCIA_DESC(String value) {
        this.PROVINCIA_DESC.setValue(value);
    }
//End ComuniElencoRow: method(s) of PROVINCIA_DESC

//ComuniElencoRow: method(s) of SOPPRESSIONED_DATA @33-D637A0DE
    public TextField getSOPPRESSIONED_DATAField() {
        return SOPPRESSIONED_DATA;
    }

    public String getSOPPRESSIONED_DATA() {
        return SOPPRESSIONED_DATA.getValue();
    }

    public void setSOPPRESSIONED_DATA(String value) {
        this.SOPPRESSIONED_DATA.setValue(value);
    }
//End ComuniElencoRow: method(s) of SOPPRESSIONED_DATA

//ComuniElencoRow: method(s) of FUSIONE_DESC @34-40A66BD6
    public TextField getFUSIONE_DESCField() {
        return FUSIONE_DESC;
    }

    public String getFUSIONE_DESC() {
        return FUSIONE_DESC.getValue();
    }

    public void setFUSIONE_DESC(String value) {
        this.FUSIONE_DESC.setValue(value);
    }
//End ComuniElencoRow: method(s) of FUSIONE_DESC

//ComuniElencoRow: method(s) of UTENTE_AGGIORNAMENTO @21-87CA4B9A
    public TextField getUTENTE_AGGIORNAMENTOField() {
        return UTENTE_AGGIORNAMENTO;
    }

    public String getUTENTE_AGGIORNAMENTO() {
        return UTENTE_AGGIORNAMENTO.getValue();
    }

    public void setUTENTE_AGGIORNAMENTO(String value) {
        this.UTENTE_AGGIORNAMENTO.setValue(value);
    }
//End ComuniElencoRow: method(s) of UTENTE_AGGIORNAMENTO

//ComuniElencoRow: method(s) of DATA_AGGIORNAMENTO @20-12C4CA82
    public TextField getDATA_AGGIORNAMENTOField() {
        return DATA_AGGIORNAMENTO;
    }

    public String getDATA_AGGIORNAMENTO() {
        return DATA_AGGIORNAMENTO.getValue();
    }

    public void setDATA_AGGIORNAMENTO(String value) {
        this.DATA_AGGIORNAMENTO.setValue(value);
    }
//End ComuniElencoRow: method(s) of DATA_AGGIORNAMENTO

//ComuniElencoRow: method(s) of AFCNavigator @28-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End ComuniElencoRow: method(s) of AFCNavigator

//ComuniElencoRow: class tail @8-FCB6E20C
}
//End ComuniElencoRow: class tail

