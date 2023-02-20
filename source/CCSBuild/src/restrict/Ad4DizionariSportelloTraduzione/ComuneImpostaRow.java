//ComuneImpostaRow: import @5-FFFD70D4
package restrict.Ad4DizionariSportelloTraduzione;

import java.util.*;
import com.codecharge.db.*;
//End ComuneImpostaRow: import

//ComuneImpostaRow: class head @5-5BC6A497
public class ComuneImpostaRow {
//End ComuneImpostaRow: class head

//ComuneImpostaRow: declare fiels @5-82E02FC2
    private TextField CAB_LABEL = new TextField("CAB_LABEL", "CAB");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField ABI_LABEL = new TextField("ABI_LABEL", "ABI");
    private TextField DENOMINAZIONE_ALT = new TextField("DENOMINAZIONE_ALT", "DENOMINAZIONE_ALT");
//End ComuneImpostaRow: declare fiels

//ComuneImpostaRow: constructor @5-B4FE0BAB
    public ComuneImpostaRow() {
    }
//End ComuneImpostaRow: constructor

//ComuneImpostaRow: method(s) of CAB_LABEL @43-C7DFE93A
    public TextField getCAB_LABELField() {
        return CAB_LABEL;
    }

    public String getCAB_LABEL() {
        return CAB_LABEL.getValue();
    }

    public void setCAB_LABEL(String value) {
        this.CAB_LABEL.setValue(value);
    }
//End ComuneImpostaRow: method(s) of CAB_LABEL

//ComuneImpostaRow: method(s) of DESCRIZIONE @44-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End ComuneImpostaRow: method(s) of DESCRIZIONE

//ComuneImpostaRow: method(s) of ABI_LABEL @6-19BA6DAE
    public TextField getABI_LABELField() {
        return ABI_LABEL;
    }

    public String getABI_LABEL() {
        return ABI_LABEL.getValue();
    }

    public void setABI_LABEL(String value) {
        this.ABI_LABEL.setValue(value);
    }
//End ComuneImpostaRow: method(s) of ABI_LABEL

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

