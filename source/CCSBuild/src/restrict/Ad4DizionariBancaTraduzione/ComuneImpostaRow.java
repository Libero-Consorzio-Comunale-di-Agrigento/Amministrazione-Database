//ComuneImpostaRow: import @5-D88BC7C9
package restrict.Ad4DizionariBancaTraduzione;

import java.util.*;
import com.codecharge.db.*;
//End ComuneImpostaRow: import

//ComuneImpostaRow: class head @5-5BC6A497
public class ComuneImpostaRow {
//End ComuneImpostaRow: class head

//ComuneImpostaRow: declare fiels @5-BF0F2291
    private TextField ABI_LABEL = new TextField("ABI_LABEL", "ABI");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField DENOMINAZIONE_ALT = new TextField("DENOMINAZIONE_ALT", "DENOMINAZIONE_ALT");
//End ComuneImpostaRow: declare fiels

//ComuneImpostaRow: constructor @5-B4FE0BAB
    public ComuneImpostaRow() {
    }
//End ComuneImpostaRow: constructor

//ComuneImpostaRow: method(s) of ABI_LABEL @43-19BA6DAE
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

