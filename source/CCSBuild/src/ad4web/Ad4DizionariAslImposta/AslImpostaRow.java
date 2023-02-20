//AslImpostaRow: import @5-2169358D
package ad4web.Ad4DizionariAslImposta;

import java.util.*;
import com.codecharge.db.*;
//End AslImpostaRow: import

//AslImpostaRow: class head @5-2D784EA0
public class AslImpostaRow {
//End AslImpostaRow: class head

//AslImpostaRow: declare fiels @5-83DC7A66
    private LongField REGIONE_ASL = new LongField("REGIONE_ASL", "REGIONE_ASL");
    private TextField CODICE_ASL_LABEL = new TextField("CODICE_ASL_LABEL", "CODICE_ASL");
    private TextField HIDE_BEGIN = new TextField("HIDE_BEGIN", "HIDE_BEGIN");
    private LongField CODICE_ASL = new LongField("CODICE_ASL", "CODICE_ASL");
    private TextField HIDE_END = new TextField("HIDE_END", "HIDE_END");
    private TextField DESCRIZIONE = new TextField("DESCRIZIONE", "DESCRIZIONE");
    private TextField ATTIVA = new TextField("ATTIVA", "ATTIVA");
    private TextField LABEL_UPD = new TextField("LABEL_UPD", "LABEL_UPD");
//End AslImpostaRow: declare fiels

//AslImpostaRow: constructor @5-67D33B3F
    public AslImpostaRow() {
    }
//End AslImpostaRow: constructor

//AslImpostaRow: method(s) of REGIONE_ASL @40-0784D561
    public LongField getREGIONE_ASLField() {
        return REGIONE_ASL;
    }

    public Long getREGIONE_ASL() {
        return REGIONE_ASL.getValue();
    }

    public void setREGIONE_ASL(Long value) {
        this.REGIONE_ASL.setValue(value);
    }
//End AslImpostaRow: method(s) of REGIONE_ASL

//AslImpostaRow: method(s) of CODICE_ASL_LABEL @6-9F08A184
    public TextField getCODICE_ASL_LABELField() {
        return CODICE_ASL_LABEL;
    }

    public String getCODICE_ASL_LABEL() {
        return CODICE_ASL_LABEL.getValue();
    }

    public void setCODICE_ASL_LABEL(String value) {
        this.CODICE_ASL_LABEL.setValue(value);
    }
//End AslImpostaRow: method(s) of CODICE_ASL_LABEL

//AslImpostaRow: method(s) of HIDE_BEGIN @7-C8D37CAD
    public TextField getHIDE_BEGINField() {
        return HIDE_BEGIN;
    }

    public String getHIDE_BEGIN() {
        return HIDE_BEGIN.getValue();
    }

    public void setHIDE_BEGIN(String value) {
        this.HIDE_BEGIN.setValue(value);
    }
//End AslImpostaRow: method(s) of HIDE_BEGIN

//AslImpostaRow: method(s) of CODICE_ASL @8-FF005A8C
    public LongField getCODICE_ASLField() {
        return CODICE_ASL;
    }

    public Long getCODICE_ASL() {
        return CODICE_ASL.getValue();
    }

    public void setCODICE_ASL(Long value) {
        this.CODICE_ASL.setValue(value);
    }
//End AslImpostaRow: method(s) of CODICE_ASL

//AslImpostaRow: method(s) of HIDE_END @9-A2CCED87
    public TextField getHIDE_ENDField() {
        return HIDE_END;
    }

    public String getHIDE_END() {
        return HIDE_END.getValue();
    }

    public void setHIDE_END(String value) {
        this.HIDE_END.setValue(value);
    }
//End AslImpostaRow: method(s) of HIDE_END

//AslImpostaRow: method(s) of DESCRIZIONE @10-07D33E44
    public TextField getDESCRIZIONEField() {
        return DESCRIZIONE;
    }

    public String getDESCRIZIONE() {
        return DESCRIZIONE.getValue();
    }

    public void setDESCRIZIONE(String value) {
        this.DESCRIZIONE.setValue(value);
    }
//End AslImpostaRow: method(s) of DESCRIZIONE

//AslImpostaRow: method(s) of ATTIVA @41-907CE3CD
    public TextField getATTIVAField() {
        return ATTIVA;
    }

    public String getATTIVA() {
        return ATTIVA.getValue();
    }

    public void setATTIVA(String value) {
        this.ATTIVA.setValue(value);
    }
//End AslImpostaRow: method(s) of ATTIVA

//AslImpostaRow: method(s) of LABEL_UPD @39-F6A687CF
    public TextField getLABEL_UPDField() {
        return LABEL_UPD;
    }

    public String getLABEL_UPD() {
        return LABEL_UPD.getValue();
    }

    public void setLABEL_UPD(String value) {
        this.LABEL_UPD.setValue(value);
    }
//End AslImpostaRow: method(s) of LABEL_UPD

//AslImpostaRow: class tail @5-FCB6E20C
}
//End AslImpostaRow: class tail

