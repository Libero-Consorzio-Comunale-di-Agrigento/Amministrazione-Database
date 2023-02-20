//RegioniElencoRow: import @8-441E4CA3
package restrict.Ad4DizionariBancheElenco;

import java.util.*;
import com.codecharge.db.*;
//End RegioniElencoRow: import

//RegioniElencoRow: class head @8-1E06F14B
public class RegioniElencoRow {
//End RegioniElencoRow: class head

//RegioniElencoRow: declare fiels @8-52844985
    private TextField Aggiungi = new TextField("Aggiungi", "Aggiungi");
    private TextField REGIONE = new TextField("REGIONE", "REGIONE");
    private TextField LINK_PROVINCE = new TextField("LINK_PROVINCE", "LINK_PROVINCE");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField ID_REGIONE = new TextField("ID_REGIONE", "ID_REGIONE");
    private TextField UTENTE_AGGIORNAMENTO = new TextField("UTENTE_AGGIORNAMENTO", "UTENTE_AGGIORNAMENTO");
    private TextField DATA_AGGIORNAMENTO = new TextField("DATA_AGGIORNAMENTO", "DATA_AGGIORNAMENTO");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
//End RegioniElencoRow: declare fiels

//RegioniElencoRow: constructor @8-6CC5AC1B
    public RegioniElencoRow() {
    }
//End RegioniElencoRow: constructor

//RegioniElencoRow: method(s) of Aggiungi @9-30CF9B46
    public TextField getAggiungiField() {
        return Aggiungi;
    }

    public String getAggiungi() {
        return Aggiungi.getValue();
    }

    public void setAggiungi(String value) {
        this.Aggiungi.setValue(value);
    }
//End RegioniElencoRow: method(s) of Aggiungi

//RegioniElencoRow: method(s) of REGIONE @12-3A5F1C79
    public TextField getREGIONEField() {
        return REGIONE;
    }

    public String getREGIONE() {
        return REGIONE.getValue();
    }

    public void setREGIONE(String value) {
        this.REGIONE.setValue(value);
    }
//End RegioniElencoRow: method(s) of REGIONE

//RegioniElencoRow: method(s) of LINK_PROVINCE @23-FF6B018B
    public TextField getLINK_PROVINCEField() {
        return LINK_PROVINCE;
    }

    public String getLINK_PROVINCE() {
        return LINK_PROVINCE.getValue();
    }

    public void setLINK_PROVINCE(String value) {
        this.LINK_PROVINCE.setValue(value);
    }
//End RegioniElencoRow: method(s) of LINK_PROVINCE

//RegioniElencoRow: method(s) of DENOMINAZIONE @13-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End RegioniElencoRow: method(s) of DENOMINAZIONE

//RegioniElencoRow: method(s) of ID_REGIONE @16-5AAC51C8
    public TextField getID_REGIONEField() {
        return ID_REGIONE;
    }

    public String getID_REGIONE() {
        return ID_REGIONE.getValue();
    }

    public void setID_REGIONE(String value) {
        this.ID_REGIONE.setValue(value);
    }
//End RegioniElencoRow: method(s) of ID_REGIONE

//RegioniElencoRow: method(s) of UTENTE_AGGIORNAMENTO @21-87CA4B9A
    public TextField getUTENTE_AGGIORNAMENTOField() {
        return UTENTE_AGGIORNAMENTO;
    }

    public String getUTENTE_AGGIORNAMENTO() {
        return UTENTE_AGGIORNAMENTO.getValue();
    }

    public void setUTENTE_AGGIORNAMENTO(String value) {
        this.UTENTE_AGGIORNAMENTO.setValue(value);
    }
//End RegioniElencoRow: method(s) of UTENTE_AGGIORNAMENTO

//RegioniElencoRow: method(s) of DATA_AGGIORNAMENTO @20-12C4CA82
    public TextField getDATA_AGGIORNAMENTOField() {
        return DATA_AGGIORNAMENTO;
    }

    public String getDATA_AGGIORNAMENTO() {
        return DATA_AGGIORNAMENTO.getValue();
    }

    public void setDATA_AGGIORNAMENTO(String value) {
        this.DATA_AGGIORNAMENTO.setValue(value);
    }
//End RegioniElencoRow: method(s) of DATA_AGGIORNAMENTO

//RegioniElencoRow: method(s) of AFCNavigator @22-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End RegioniElencoRow: method(s) of AFCNavigator

//RegioniElencoRow: class tail @8-FCB6E20C
}
//End RegioniElencoRow: class tail

