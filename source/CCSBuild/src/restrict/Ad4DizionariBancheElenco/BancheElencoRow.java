//BancheElencoRow: import @8-441E4CA3
package restrict.Ad4DizionariBancheElenco;

import java.util.*;
import com.codecharge.db.*;
//End BancheElencoRow: import

//BancheElencoRow: class head @8-1DC81BFA
public class BancheElencoRow {
//End BancheElencoRow: class head

//BancheElencoRow: declare fiels @8-1E10C394
    private TextField Aggiungi = new TextField("Aggiungi", "Aggiungi");
    private TextField ABI = new TextField("ABI", "ABI");
    private TextField LINK_SPORTELLI = new TextField("LINK_SPORTELLI", "LINK_SPORTELLI");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField CIN_ABI = new TextField("CIN_ABI", "CIN_ABI");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField BANCA = new TextField("BANCA", "ABI");
//End BancheElencoRow: declare fiels

//BancheElencoRow: constructor @8-CE16A78A
    public BancheElencoRow() {
    }
//End BancheElencoRow: constructor

//BancheElencoRow: method(s) of Aggiungi @9-30CF9B46
    public TextField getAggiungiField() {
        return Aggiungi;
    }

    public String getAggiungi() {
        return Aggiungi.getValue();
    }

    public void setAggiungi(String value) {
        this.Aggiungi.setValue(value);
    }
//End BancheElencoRow: method(s) of Aggiungi

//BancheElencoRow: method(s) of ABI @12-DB48A390
    public TextField getABIField() {
        return ABI;
    }

    public String getABI() {
        return ABI.getValue();
    }

    public void setABI(String value) {
        this.ABI.setValue(value);
    }
//End BancheElencoRow: method(s) of ABI

//BancheElencoRow: method(s) of LINK_SPORTELLI @23-9F3FC3A6
    public TextField getLINK_SPORTELLIField() {
        return LINK_SPORTELLI;
    }

    public String getLINK_SPORTELLI() {
        return LINK_SPORTELLI.getValue();
    }

    public void setLINK_SPORTELLI(String value) {
        this.LINK_SPORTELLI.setValue(value);
    }
//End BancheElencoRow: method(s) of LINK_SPORTELLI

//BancheElencoRow: method(s) of DENOMINAZIONE @13-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End BancheElencoRow: method(s) of DENOMINAZIONE

//BancheElencoRow: method(s) of CIN_ABI @16-4BF442FB
    public TextField getCIN_ABIField() {
        return CIN_ABI;
    }

    public String getCIN_ABI() {
        return CIN_ABI.getValue();
    }

    public void setCIN_ABI(String value) {
        this.CIN_ABI.setValue(value);
    }
//End BancheElencoRow: method(s) of CIN_ABI

//BancheElencoRow: method(s) of AFCNavigator @22-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End BancheElencoRow: method(s) of AFCNavigator

//BancheElencoRow: method(s) of BANCA @15-29F00028
    public TextField getBANCAField() {
        return BANCA;
    }

    public String getBANCA() {
        return BANCA.getValue();
    }

    public void setBANCA(String value) {
        this.BANCA.setValue(value);
    }
//End BancheElencoRow: method(s) of BANCA

//BancheElencoRow: class tail @8-FCB6E20C
}
//End BancheElencoRow: class tail

