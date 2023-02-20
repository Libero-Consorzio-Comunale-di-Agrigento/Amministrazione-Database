//AD4SoggettiVRow: import @142-90910653
package ad4web.AD4SoggRicercaInclusa;

import java.util.*;
import com.codecharge.db.*;
//End AD4SoggettiVRow: import

//AD4SoggettiVRow: class head @142-C8EDED68
public class AD4SoggettiVRow {
//End AD4SoggettiVRow: class head

//AD4SoggettiVRow: declare fiels @142-4935BF74
    private TextField SOGGETTO = new TextField("SOGGETTO", "SOGGETTO");
    private TextField SOGGETTO_VIS = new TextField("SOGGETTO_VIS", "PAGINA");
    private TextField DATI = new TextField("DATI", "DATI");
    private TextField RIPORTA_UTENTE = new TextField("RIPORTA_UTENTE", "RIPORTA_UTENTE");
    private TextField RIPORTA_ENTE = new TextField("RIPORTA_ENTE", "RIPORTA_ENTE");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField PAGINA = new TextField("PAGINA", "PAGINA");
//End AD4SoggettiVRow: declare fiels

//AD4SoggettiVRow: constructor @142-396359BD
    public AD4SoggettiVRow() {
    }
//End AD4SoggettiVRow: constructor

//AD4SoggettiVRow: method(s) of SOGGETTO @387-585B3330
    public TextField getSOGGETTOField() {
        return SOGGETTO;
    }

    public String getSOGGETTO() {
        return SOGGETTO.getValue();
    }

    public void setSOGGETTO(String value) {
        this.SOGGETTO.setValue(value);
    }
//End AD4SoggettiVRow: method(s) of SOGGETTO

//AD4SoggettiVRow: method(s) of SOGGETTO_VIS @351-DCF098C1
    public TextField getSOGGETTO_VISField() {
        return SOGGETTO_VIS;
    }

    public String getSOGGETTO_VIS() {
        return SOGGETTO_VIS.getValue();
    }

    public void setSOGGETTO_VIS(String value) {
        this.SOGGETTO_VIS.setValue(value);
    }
//End AD4SoggettiVRow: method(s) of SOGGETTO_VIS

//AD4SoggettiVRow: method(s) of DATI @263-214A91E2
    public TextField getDATIField() {
        return DATI;
    }

    public String getDATI() {
        return DATI.getValue();
    }

    public void setDATI(String value) {
        this.DATI.setValue(value);
    }
//End AD4SoggettiVRow: method(s) of DATI

//AD4SoggettiVRow: method(s) of RIPORTA_UTENTE @359-CA07EC68
    public TextField getRIPORTA_UTENTEField() {
        return RIPORTA_UTENTE;
    }

    public String getRIPORTA_UTENTE() {
        return RIPORTA_UTENTE.getValue();
    }

    public void setRIPORTA_UTENTE(String value) {
        this.RIPORTA_UTENTE.setValue(value);
    }
//End AD4SoggettiVRow: method(s) of RIPORTA_UTENTE

//AD4SoggettiVRow: method(s) of RIPORTA_ENTE @372-8245E138
    public TextField getRIPORTA_ENTEField() {
        return RIPORTA_ENTE;
    }

    public String getRIPORTA_ENTE() {
        return RIPORTA_ENTE.getValue();
    }

    public void setRIPORTA_ENTE(String value) {
        this.RIPORTA_ENTE.setValue(value);
    }
//End AD4SoggettiVRow: method(s) of RIPORTA_ENTE

//AD4SoggettiVRow: method(s) of AFCNavigator @379-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End AD4SoggettiVRow: method(s) of AFCNavigator

//AD4SoggettiVRow: method(s) of PAGINA @PAGINA-3D0882F5
    public TextField getPAGINAField() {
        return PAGINA;
    }

    public String getPAGINA() {
        return PAGINA.getValue();
    }

    public void setPAGINA(String value) {
        this.PAGINA.setValue(value);
    }
//End AD4SoggettiVRow: method(s) of PAGINA

//AD4SoggettiVRow: class tail @142-FCB6E20C
}
//End AD4SoggettiVRow: class tail

