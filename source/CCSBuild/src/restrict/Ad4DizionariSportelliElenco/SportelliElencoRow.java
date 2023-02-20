//SportelliElencoRow: import @8-128ECA27
package restrict.Ad4DizionariSportelliElenco;

import java.util.*;
import com.codecharge.db.*;
//End SportelliElencoRow: import

//SportelliElencoRow: class head @8-0608AD8B
public class SportelliElencoRow {
//End SportelliElencoRow: class head

//SportelliElencoRow: declare fiels @8-8093B27D
    private TextField Aggiungi = new TextField("Aggiungi", "Aggiungi");
    private TextField CAB = new TextField("CAB", "CAB");
    private TextField ABI = new TextField("ABI", "ABI");
    private TextField CIN_CAB = new TextField("CIN_CAB", "CIN_CAB");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField INDIRIZZO = new TextField("INDIRIZZO", "INDIRIZZO");
    private TextField LOCALITA = new TextField("LOCALITA", "LOCALITA");
    private TextField COMUNE = new TextField("COMUNE", "COMUNE");
    private TextField PROVINCIA = new TextField("PROVINCIA", "PROVINCIA");
    private TextField CAP = new TextField("CAP", "CAP");
    private TextField DIPENDENZA = new TextField("DIPENDENZA", "DIPENDENZA");
    private TextField BIC = new TextField("BIC", "BIC");
    private TextField BANCA = new TextField("BANCA", "BANCA");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField SPORTELLO = new TextField("SPORTELLO", "CAB");
//End SportelliElencoRow: declare fiels

//SportelliElencoRow: constructor @8-9D4C0A47
    public SportelliElencoRow() {
    }
//End SportelliElencoRow: constructor

//SportelliElencoRow: method(s) of Aggiungi @9-30CF9B46
    public TextField getAggiungiField() {
        return Aggiungi;
    }

    public String getAggiungi() {
        return Aggiungi.getValue();
    }

    public void setAggiungi(String value) {
        this.Aggiungi.setValue(value);
    }
//End SportelliElencoRow: method(s) of Aggiungi

//SportelliElencoRow: method(s) of CAB @23-7E8C0C24
    public TextField getCABField() {
        return CAB;
    }

    public String getCAB() {
        return CAB.getValue();
    }

    public void setCAB(String value) {
        this.CAB.setValue(value);
    }
//End SportelliElencoRow: method(s) of CAB

//SportelliElencoRow: method(s) of ABI @39-DB48A390
    public TextField getABIField() {
        return ABI;
    }

    public String getABI() {
        return ABI.getValue();
    }

    public void setABI(String value) {
        this.ABI.setValue(value);
    }
//End SportelliElencoRow: method(s) of ABI

//SportelliElencoRow: method(s) of CIN_CAB @33-7C0A1945
    public TextField getCIN_CABField() {
        return CIN_CAB;
    }

    public String getCIN_CAB() {
        return CIN_CAB.getValue();
    }

    public void setCIN_CAB(String value) {
        this.CIN_CAB.setValue(value);
    }
//End SportelliElencoRow: method(s) of CIN_CAB

//SportelliElencoRow: method(s) of DESCRIZIONE @13-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End SportelliElencoRow: method(s) of DESCRIZIONE

//SportelliElencoRow: method(s) of INDIRIZZO @16-79B8981C
    public TextField getINDIRIZZOField() {
        return INDIRIZZO;
    }

    public String getINDIRIZZO() {
        return INDIRIZZO.getValue();
    }

    public void setINDIRIZZO(String value) {
        this.INDIRIZZO.setValue(value);
    }
//End SportelliElencoRow: method(s) of INDIRIZZO

//SportelliElencoRow: method(s) of LOCALITA @12-8441C816
    public TextField getLOCALITAField() {
        return LOCALITA;
    }

    public String getLOCALITA() {
        return LOCALITA.getValue();
    }

    public void setLOCALITA(String value) {
        this.LOCALITA.setValue(value);
    }
//End SportelliElencoRow: method(s) of LOCALITA

//SportelliElencoRow: method(s) of COMUNE @29-B41017A6
    public TextField getCOMUNEField() {
        return COMUNE;
    }

    public String getCOMUNE() {
        return COMUNE.getValue();
    }

    public void setCOMUNE(String value) {
        this.COMUNE.setValue(value);
    }
//End SportelliElencoRow: method(s) of COMUNE

//SportelliElencoRow: method(s) of PROVINCIA @30-0A4A498E
    public TextField getPROVINCIAField() {
        return PROVINCIA;
    }

    public String getPROVINCIA() {
        return PROVINCIA.getValue();
    }

    public void setPROVINCIA(String value) {
        this.PROVINCIA.setValue(value);
    }
//End SportelliElencoRow: method(s) of PROVINCIA

//SportelliElencoRow: method(s) of CAP @21-CDFF7BC8
    public TextField getCAPField() {
        return CAP;
    }

    public String getCAP() {
        return CAP.getValue();
    }

    public void setCAP(String value) {
        this.CAP.setValue(value);
    }
//End SportelliElencoRow: method(s) of CAP

//SportelliElencoRow: method(s) of DIPENDENZA @31-D3A0FEBE
    public TextField getDIPENDENZAField() {
        return DIPENDENZA;
    }

    public String getDIPENDENZA() {
        return DIPENDENZA.getValue();
    }

    public void setDIPENDENZA(String value) {
        this.DIPENDENZA.setValue(value);
    }
//End SportelliElencoRow: method(s) of DIPENDENZA

//SportelliElencoRow: method(s) of BIC @32-14D3AFCD
    public TextField getBICField() {
        return BIC;
    }

    public String getBIC() {
        return BIC.getValue();
    }

    public void setBIC(String value) {
        this.BIC.setValue(value);
    }
//End SportelliElencoRow: method(s) of BIC

//SportelliElencoRow: method(s) of BANCA @20-29F00028
    public TextField getBANCAField() {
        return BANCA;
    }

    public String getBANCA() {
        return BANCA.getValue();
    }

    public void setBANCA(String value) {
        this.BANCA.setValue(value);
    }
//End SportelliElencoRow: method(s) of BANCA

//SportelliElencoRow: method(s) of AFCNavigator @28-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End SportelliElencoRow: method(s) of AFCNavigator

//SportelliElencoRow: method(s) of SPORTELLO @37-420C4BF3
    public TextField getSPORTELLOField() {
        return SPORTELLO;
    }

    public String getSPORTELLO() {
        return SPORTELLO.getValue();
    }

    public void setSPORTELLO(String value) {
        this.SPORTELLO.setValue(value);
    }
//End SportelliElencoRow: method(s) of SPORTELLO

//SportelliElencoRow: class tail @8-FCB6E20C
}
//End SportelliElencoRow: class tail

