//StatiElencoRow: import @8-26A92836
package restrict.Ad4DizionariStatiTerritoriElenco;

import java.util.*;
import com.codecharge.db.*;
//End StatiElencoRow: import

//StatiElencoRow: class head @8-51E1F286
public class StatiElencoRow {
//End StatiElencoRow: class head

//StatiElencoRow: declare fiels @8-A9EA5973
    private TextField Aggiungi = new TextField("Aggiungi", "Aggiungi");
    private TextField STATO_TERRITORIO = new TextField("STATO_TERRITORIO", "STATO_TERRITORIO");
    private TextField LINK_COMUNI = new TextField("LINK_COMUNI", "LINK_COMUNI");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField SIGLA = new TextField("SIGLA", "SIGLA");
    private TextField DESC_CITTADINANZA = new TextField("DESC_CITTADINANZA", "DESC_CITTADINANZA");
    private TextField UTENTE_AGGIORNAMENTO = new TextField("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO");
    private TextField DATA_AGGIORNAMENTO = new TextField("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End StatiElencoRow: declare fiels

//StatiElencoRow: constructor @8-57889249
    public StatiElencoRow() {
    }
//End StatiElencoRow: constructor

//StatiElencoRow: method(s) of Aggiungi @9-30CF9B46
    public TextField getAggiungiField() {
        return Aggiungi;
    }

    public String getAggiungi() {
        return Aggiungi.getValue();
    }

    public void setAggiungi(String value) {
        this.Aggiungi.setValue(value);
    }
//End StatiElencoRow: method(s) of Aggiungi

//StatiElencoRow: method(s) of STATO_TERRITORIO @12-D4320782
    public TextField getSTATO_TERRITORIOField() {
        return STATO_TERRITORIO;
    }

    public String getSTATO_TERRITORIO() {
        return STATO_TERRITORIO.getValue();
    }

    public void setSTATO_TERRITORIO(String value) {
        this.STATO_TERRITORIO.setValue(value);
    }
//End StatiElencoRow: method(s) of STATO_TERRITORIO

//StatiElencoRow: method(s) of LINK_COMUNI @24-CB592EF2
    public TextField getLINK_COMUNIField() {
        return LINK_COMUNI;
    }

    public String getLINK_COMUNI() {
        return LINK_COMUNI.getValue();
    }

    public void setLINK_COMUNI(String value) {
        this.LINK_COMUNI.setValue(value);
    }
//End StatiElencoRow: method(s) of LINK_COMUNI

//StatiElencoRow: method(s) of DENOMINAZIONE @13-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End StatiElencoRow: method(s) of DENOMINAZIONE

//StatiElencoRow: method(s) of SIGLA @16-3A41CFD1
    public TextField getSIGLAField() {
        return SIGLA;
    }

    public String getSIGLA() {
        return SIGLA.getValue();
    }

    public void setSIGLA(String value) {
        this.SIGLA.setValue(value);
    }
//End StatiElencoRow: method(s) of SIGLA

//StatiElencoRow: method(s) of DESC_CITTADINANZA @23-6F5A500C
    public TextField getDESC_CITTADINANZAField() {
        return DESC_CITTADINANZA;
    }

    public String getDESC_CITTADINANZA() {
        return DESC_CITTADINANZA.getValue();
    }

    public void setDESC_CITTADINANZA(String value) {
        this.DESC_CITTADINANZA.setValue(value);
    }
//End StatiElencoRow: method(s) of DESC_CITTADINANZA

//StatiElencoRow: method(s) of UTENTE_AGGIORNAMENTO @21-87CA4B9A
    public TextField getUTENTE_AGGIORNAMENTOField() {
        return UTENTE_AGGIORNAMENTO;
    }

    public String getUTENTE_AGGIORNAMENTO() {
        return UTENTE_AGGIORNAMENTO.getValue();
    }

    public void setUTENTE_AGGIORNAMENTO(String value) {
        this.UTENTE_AGGIORNAMENTO.setValue(value);
    }
//End StatiElencoRow: method(s) of UTENTE_AGGIORNAMENTO

//StatiElencoRow: method(s) of DATA_AGGIORNAMENTO @20-12C4CA82
    public TextField getDATA_AGGIORNAMENTOField() {
        return DATA_AGGIORNAMENTO;
    }

    public String getDATA_AGGIORNAMENTO() {
        return DATA_AGGIORNAMENTO.getValue();
    }

    public void setDATA_AGGIORNAMENTO(String value) {
        this.DATA_AGGIORNAMENTO.setValue(value);
    }
//End StatiElencoRow: method(s) of DATA_AGGIORNAMENTO

//StatiElencoRow: method(s) of AFCNavigator @22-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End StatiElencoRow: method(s) of AFCNavigator

//StatiElencoRow: class tail @8-FCB6E20C
}
//End StatiElencoRow: class tail

