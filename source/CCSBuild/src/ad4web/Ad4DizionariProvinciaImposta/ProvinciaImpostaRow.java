//ProvinciaImpostaRow: import @5-02BD1203
package ad4web.Ad4DizionariProvinciaImposta;

import java.util.*;
import com.codecharge.db.*;
//End ProvinciaImpostaRow: import

//ProvinciaImpostaRow: class head @5-20281DCB
public class ProvinciaImpostaRow {
//End ProvinciaImpostaRow: class head

//ProvinciaImpostaRow: declare fiels @5-BB37FD92
    private TextField PROVINCIA_LABEL = new TextField("PROVINCIA_LABEL", "PROVINCIA");
    private TextField HIDE_BEGIN = new TextField("HIDE_BEGIN", "HIDE_BEGIN");
    private LongField PROVINCIA = new LongField("PROVINCIA", "PROVINCIA");
    private TextField HIDE_END = new TextField("HIDE_END", "HIDE_END");
    private TextField DENOMINAZIONE = new TextField("DENOMINAZIONE", "DENOMINAZIONE");
    private TextField SIGLA = new TextField("SIGLA", "SIGLA");
    private LongField REGIONE = new LongField("REGIONE", "REGIONE");
    private TextField LABEL_UPD = new TextField("LABEL_UPD", "LABEL_UPD");
//End ProvinciaImpostaRow: declare fiels

//ProvinciaImpostaRow: constructor @5-15A4B9D3
    public ProvinciaImpostaRow() {
    }
//End ProvinciaImpostaRow: constructor

//ProvinciaImpostaRow: method(s) of PROVINCIA_LABEL @6-5A891313
    public TextField getPROVINCIA_LABELField() {
        return PROVINCIA_LABEL;
    }

    public String getPROVINCIA_LABEL() {
        return PROVINCIA_LABEL.getValue();
    }

    public void setPROVINCIA_LABEL(String value) {
        this.PROVINCIA_LABEL.setValue(value);
    }
//End ProvinciaImpostaRow: method(s) of PROVINCIA_LABEL

//ProvinciaImpostaRow: method(s) of HIDE_BEGIN @7-C8D37CAD
    public TextField getHIDE_BEGINField() {
        return HIDE_BEGIN;
    }

    public String getHIDE_BEGIN() {
        return HIDE_BEGIN.getValue();
    }

    public void setHIDE_BEGIN(String value) {
        this.HIDE_BEGIN.setValue(value);
    }
//End ProvinciaImpostaRow: method(s) of HIDE_BEGIN

//ProvinciaImpostaRow: method(s) of PROVINCIA @8-A3592314
    public LongField getPROVINCIAField() {
        return PROVINCIA;
    }

    public Long getPROVINCIA() {
        return PROVINCIA.getValue();
    }

    public void setPROVINCIA(Long value) {
        this.PROVINCIA.setValue(value);
    }
//End ProvinciaImpostaRow: method(s) of PROVINCIA

//ProvinciaImpostaRow: method(s) of HIDE_END @9-A2CCED87
    public TextField getHIDE_ENDField() {
        return HIDE_END;
    }

    public String getHIDE_END() {
        return HIDE_END.getValue();
    }

    public void setHIDE_END(String value) {
        this.HIDE_END.setValue(value);
    }
//End ProvinciaImpostaRow: method(s) of HIDE_END

//ProvinciaImpostaRow: method(s) of DENOMINAZIONE @10-7DCDE77E
    public TextField getDENOMINAZIONEField() {
        return DENOMINAZIONE;
    }

    public String getDENOMINAZIONE() {
        return DENOMINAZIONE.getValue();
    }

    public void setDENOMINAZIONE(String value) {
        this.DENOMINAZIONE.setValue(value);
    }
//End ProvinciaImpostaRow: method(s) of DENOMINAZIONE

//ProvinciaImpostaRow: method(s) of SIGLA @41-3A41CFD1
    public TextField getSIGLAField() {
        return SIGLA;
    }

    public String getSIGLA() {
        return SIGLA.getValue();
    }

    public void setSIGLA(String value) {
        this.SIGLA.setValue(value);
    }
//End ProvinciaImpostaRow: method(s) of SIGLA

//ProvinciaImpostaRow: method(s) of REGIONE @40-893791B0
    public LongField getREGIONEField() {
        return REGIONE;
    }

    public Long getREGIONE() {
        return REGIONE.getValue();
    }

    public void setREGIONE(Long value) {
        this.REGIONE.setValue(value);
    }
//End ProvinciaImpostaRow: method(s) of REGIONE

//ProvinciaImpostaRow: method(s) of LABEL_UPD @39-F6A687CF
    public TextField getLABEL_UPDField() {
        return LABEL_UPD;
    }

    public String getLABEL_UPD() {
        return LABEL_UPD.getValue();
    }

    public void setLABEL_UPD(String value) {
        this.LABEL_UPD.setValue(value);
    }
//End ProvinciaImpostaRow: method(s) of LABEL_UPD

//ProvinciaImpostaRow: class tail @5-FCB6E20C
}
//End ProvinciaImpostaRow: class tail

