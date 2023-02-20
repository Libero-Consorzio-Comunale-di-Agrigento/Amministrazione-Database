//RegioneImpostaRow: import @5-B81A2240
package ad4web.Ad4DizionariRegioneImposta;

import java.util.*;
import com.codecharge.db.*;
//End RegioneImpostaRow: import

//RegioneImpostaRow: class head @5-6A655B70
public class RegioneImpostaRow {
//End RegioneImpostaRow: class head

//RegioneImpostaRow: declare fiels @5-E50BB547
    private TextField REGIONE_LABEL = new TextField("REGIONE_LABEL", "REGIONE");
    private TextField HIDE_BEGIN = new TextField("HIDE_BEGIN", "HIDE_BEGIN");
    private LongField REGIONE = new LongField("REGIONE", "REGIONE");
    private TextField HIDE_END = new TextField("HIDE_END", "HIDE_END");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private LongField ID_REGIONE = new LongField("ID_REGIONE", "ID_REGIONE");
    private TextField LABEL_UPD = new TextField("LABEL_UPD", "LABEL_UPD");
//End RegioneImpostaRow: declare fiels

//RegioneImpostaRow: constructor @5-8EC965B5
    public RegioneImpostaRow() {
    }
//End RegioneImpostaRow: constructor

//RegioneImpostaRow: method(s) of REGIONE_LABEL @6-79C85A07
    public TextField getREGIONE_LABELField() {
        return REGIONE_LABEL;
    }

    public String getREGIONE_LABEL() {
        return REGIONE_LABEL.getValue();
    }

    public void setREGIONE_LABEL(String value) {
        this.REGIONE_LABEL.setValue(value);
    }
//End RegioneImpostaRow: method(s) of REGIONE_LABEL

//RegioneImpostaRow: method(s) of HIDE_BEGIN @7-C8D37CAD
    public TextField getHIDE_BEGINField() {
        return HIDE_BEGIN;
    }

    public String getHIDE_BEGIN() {
        return HIDE_BEGIN.getValue();
    }

    public void setHIDE_BEGIN(String value) {
        this.HIDE_BEGIN.setValue(value);
    }
//End RegioneImpostaRow: method(s) of HIDE_BEGIN

//RegioneImpostaRow: method(s) of REGIONE @8-893791B0
    public LongField getREGIONEField() {
        return REGIONE;
    }

    public Long getREGIONE() {
        return REGIONE.getValue();
    }

    public void setREGIONE(Long value) {
        this.REGIONE.setValue(value);
    }
//End RegioneImpostaRow: method(s) of REGIONE

//RegioneImpostaRow: method(s) of HIDE_END @9-A2CCED87
    public TextField getHIDE_ENDField() {
        return HIDE_END;
    }

    public String getHIDE_END() {
        return HIDE_END.getValue();
    }

    public void setHIDE_END(String value) {
        this.HIDE_END.setValue(value);
    }
//End RegioneImpostaRow: method(s) of HIDE_END

//RegioneImpostaRow: method(s) of DENOMINAZIONE @10-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End RegioneImpostaRow: method(s) of DENOMINAZIONE

//RegioneImpostaRow: method(s) of ID_REGIONE @38-FBB61745
    public LongField getID_REGIONEField() {
        return ID_REGIONE;
    }

    public Long getID_REGIONE() {
        return ID_REGIONE.getValue();
    }

    public void setID_REGIONE(Long value) {
        this.ID_REGIONE.setValue(value);
    }
//End RegioneImpostaRow: method(s) of ID_REGIONE

//RegioneImpostaRow: method(s) of LABEL_UPD @39-F6A687CF
    public TextField getLABEL_UPDField() {
        return LABEL_UPD;
    }

    public String getLABEL_UPD() {
        return LABEL_UPD.getValue();
    }

    public void setLABEL_UPD(String value) {
        this.LABEL_UPD.setValue(value);
    }
//End RegioneImpostaRow: method(s) of LABEL_UPD

//RegioneImpostaRow: class tail @5-FCB6E20C
}
//End RegioneImpostaRow: class tail

