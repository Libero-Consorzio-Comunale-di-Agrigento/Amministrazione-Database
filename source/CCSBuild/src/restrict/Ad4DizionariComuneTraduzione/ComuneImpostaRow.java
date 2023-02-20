//ComuneImpostaRow: import @5-72480396
package restrict.Ad4DizionariComuneTraduzione;

import java.util.*;
import com.codecharge.db.*;
//End ComuneImpostaRow: import

//ComuneImpostaRow: class head @5-5BC6A497
public class ComuneImpostaRow {
//End ComuneImpostaRow: class head

//ComuneImpostaRow: declare fiels @5-53099352
    private TextField COMUNE_LABEL = new TextField("COMUNE_LABEL", "COMUNE");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField PROVINCIA_LABEL = new TextField("PROVINCIA_LABEL", "PROVINCIA_STATO");
    private TextField DENOMINAZIONE_ALT = new TextField("DENOMINAZIONE_ALT", "DENOMINAZIONE_ALT");
//End ComuneImpostaRow: declare fiels

//ComuneImpostaRow: constructor @5-B4FE0BAB
    public ComuneImpostaRow() {
    }
//End ComuneImpostaRow: constructor

//ComuneImpostaRow: method(s) of COMUNE_LABEL @43-D981A933
    public TextField getCOMUNE_LABELField() {
        return COMUNE_LABEL;
    }

    public String getCOMUNE_LABEL() {
        return COMUNE_LABEL.getValue();
    }

    public void setCOMUNE_LABEL(String value) {
        this.COMUNE_LABEL.setValue(value);
    }
//End ComuneImpostaRow: method(s) of COMUNE_LABEL

//ComuneImpostaRow: method(s) of DENOMINAZIONE @44-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of DENOMINAZIONE

//ComuneImpostaRow: method(s) of PROVINCIA_LABEL @6-5A891313
    public TextField getPROVINCIA_LABELField() {
        return PROVINCIA_LABEL;
    }

    public String getPROVINCIA_LABEL() {
        return PROVINCIA_LABEL.getValue();
    }

    public void setPROVINCIA_LABEL(String value) {
        this.PROVINCIA_LABEL.setValue(value);
    }
//End ComuneImpostaRow: method(s) of PROVINCIA_LABEL

//ComuneImpostaRow: method(s) of DENOMINAZIONE_ALT @10-39DDA5A9
    public TextField getDENOMINAZIONE_ALTField() {
        return DENOMINAZIONE_ALT;
    }

    public String getDENOMINAZIONE_ALT() {
        return DENOMINAZIONE_ALT.getValue();
    }

    public void setDENOMINAZIONE_ALT(String value) {
        this.DENOMINAZIONE_ALT.setValue(value);
    }
//End ComuneImpostaRow: method(s) of DENOMINAZIONE_ALT

//ComuneImpostaRow: class tail @5-FCB6E20C
}
//End ComuneImpostaRow: class tail

