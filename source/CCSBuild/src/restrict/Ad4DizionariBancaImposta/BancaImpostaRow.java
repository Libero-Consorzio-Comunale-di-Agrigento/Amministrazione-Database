//BancaImpostaRow: import @5-4CAF89CA
package restrict.Ad4DizionariBancaImposta;

import java.util.*;
import com.codecharge.db.*;
//End BancaImpostaRow: import

//BancaImpostaRow: class head @5-2CB3A3BE
public class BancaImpostaRow {
//End BancaImpostaRow: class head

//BancaImpostaRow: declare fiels @5-6E932D55
    private TextField BANCA_LABEL = new TextField("BANCA_LABEL", "BANCA");
    private TextField HIDE_BEGIN = new TextField("HIDE_BEGIN", "HIDE_BEGIN");
    private TextField BANCA = new TextField("BANCA", "BANCA");
    private TextField HIDE_END = new TextField("HIDE_END", "HIDE_END");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField TRADUZIONE = new TextField("TRADUZIONE", "OPEN_TRADUZIONE");
    private TextField CIN_ABI = new TextField("CIN_ABI", "CIN_ABI");
    private TextField LABEL_UPD = new TextField("LABEL_UPD", "LABEL_UPD");
//End BancaImpostaRow: declare fiels

//BancaImpostaRow: constructor @5-524E0EA3
    public BancaImpostaRow() {
    }
//End BancaImpostaRow: constructor

//BancaImpostaRow: method(s) of BANCA_LABEL @6-4C791328
    public TextField getBANCA_LABELField() {
        return BANCA_LABEL;
    }

    public String getBANCA_LABEL() {
        return BANCA_LABEL.getValue();
    }

    public void setBANCA_LABEL(String value) {
        this.BANCA_LABEL.setValue(value);
    }
//End BancaImpostaRow: method(s) of BANCA_LABEL

//BancaImpostaRow: method(s) of HIDE_BEGIN @7-C8D37CAD
    public TextField getHIDE_BEGINField() {
        return HIDE_BEGIN;
    }

    public String getHIDE_BEGIN() {
        return HIDE_BEGIN.getValue();
    }

    public void setHIDE_BEGIN(String value) {
        this.HIDE_BEGIN.setValue(value);
    }
//End BancaImpostaRow: method(s) of HIDE_BEGIN

//BancaImpostaRow: method(s) of BANCA @8-29F00028
    public TextField getBANCAField() {
        return BANCA;
    }

    public String getBANCA() {
        return BANCA.getValue();
    }

    public void setBANCA(String value) {
        this.BANCA.setValue(value);
    }
//End BancaImpostaRow: method(s) of BANCA

//BancaImpostaRow: method(s) of HIDE_END @9-A2CCED87
    public TextField getHIDE_ENDField() {
        return HIDE_END;
    }

    public String getHIDE_END() {
        return HIDE_END.getValue();
    }

    public void setHIDE_END(String value) {
        this.HIDE_END.setValue(value);
    }
//End BancaImpostaRow: method(s) of HIDE_END

//BancaImpostaRow: method(s) of DENOMINAZIONE @10-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End BancaImpostaRow: method(s) of DENOMINAZIONE

//BancaImpostaRow: method(s) of TRADUZIONE @40-283362F9
    public TextField getTRADUZIONEField() {
        return TRADUZIONE;
    }

    public String getTRADUZIONE() {
        return TRADUZIONE.getValue();
    }

    public void setTRADUZIONE(String value) {
        this.TRADUZIONE.setValue(value);
    }
//End BancaImpostaRow: method(s) of TRADUZIONE

//BancaImpostaRow: method(s) of CIN_ABI @38-4BF442FB
    public TextField getCIN_ABIField() {
        return CIN_ABI;
    }

    public String getCIN_ABI() {
        return CIN_ABI.getValue();
    }

    public void setCIN_ABI(String value) {
        this.CIN_ABI.setValue(value);
    }
//End BancaImpostaRow: method(s) of CIN_ABI

//BancaImpostaRow: method(s) of LABEL_UPD @39-F6A687CF
    public TextField getLABEL_UPDField() {
        return LABEL_UPD;
    }

    public String getLABEL_UPD() {
        return LABEL_UPD.getValue();
    }

    public void setLABEL_UPD(String value) {
        this.LABEL_UPD.setValue(value);
    }
//End BancaImpostaRow: method(s) of LABEL_UPD

//BancaImpostaRow: class tail @5-FCB6E20C
}
//End BancaImpostaRow: class tail

