//AslComuneImpostaRow: import @5-4CBDC4D5
package ad4web.Ad4DizionariAslComuneImposta;

import java.util.*;
import com.codecharge.db.*;
//End AslComuneImpostaRow: import

//AslComuneImpostaRow: class head @5-F758DEF9
public class AslComuneImpostaRow {
//End AslComuneImpostaRow: class head

//AslComuneImpostaRow: declare fiels @5-1B90CFD9
    private TextField ASL_DESC = new TextField("ASL_DESC", "ASL_DESC");
    private LongField REGIONE_ASL = new LongField("REGIONE_ASL", "REGIONE_ASL");
    private LongField CODICE_ASL = new LongField("CODICE_ASL", "CODICE_ASL");
    private TextField COMUNE_DESC = new TextField("COMUNE_DESC", "COMUNE_DESC");
    private TextField COMUNE_LOV = new TextField("COMUNE_LOV", "COMUNE_LOV");
    private LongField PROVINCIA = new LongField("PROVINCIA", "PROVINCIA");
    private LongField COMUNE = new LongField("COMUNE", "COMUNE");
    private TextField PROPOSTA = new TextField("PROPOSTA", "PROPOSTA");
    private TextField ATTIVA = new TextField("ATTIVA", "ATTIVA");
    private TextField LABEL_UPD = new TextField("LABEL_UPD", "LABEL_UPD");
//End AslComuneImpostaRow: declare fiels

//AslComuneImpostaRow: constructor @5-82997D4B
    public AslComuneImpostaRow() {
    }
//End AslComuneImpostaRow: constructor

//AslComuneImpostaRow: method(s) of ASL_DESC @6-82C4377B
    public TextField getASL_DESCField() {
        return ASL_DESC;
    }

    public String getASL_DESC() {
        return ASL_DESC.getValue();
    }

    public void setASL_DESC(String value) {
        this.ASL_DESC.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of ASL_DESC

//AslComuneImpostaRow: method(s) of REGIONE_ASL @52-0784D561
    public LongField getREGIONE_ASLField() {
        return REGIONE_ASL;
    }

    public Long getREGIONE_ASL() {
        return REGIONE_ASL.getValue();
    }

    public void setREGIONE_ASL(Long value) {
        this.REGIONE_ASL.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of REGIONE_ASL

//AslComuneImpostaRow: method(s) of CODICE_ASL @53-FF005A8C
    public LongField getCODICE_ASLField() {
        return CODICE_ASL;
    }

    public Long getCODICE_ASL() {
        return CODICE_ASL.getValue();
    }

    public void setCODICE_ASL(Long value) {
        this.CODICE_ASL.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of CODICE_ASL

//AslComuneImpostaRow: method(s) of COMUNE_DESC @10-E6E222F6
    public TextField getCOMUNE_DESCField() {
        return COMUNE_DESC;
    }

    public String getCOMUNE_DESC() {
        return COMUNE_DESC.getValue();
    }

    public void setCOMUNE_DESC(String value) {
        this.COMUNE_DESC.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of COMUNE_DESC

//AslComuneImpostaRow: method(s) of COMUNE_LOV @54-ECEAD2C3
    public TextField getCOMUNE_LOVField() {
        return COMUNE_LOV;
    }

    public String getCOMUNE_LOV() {
        return COMUNE_LOV.getValue();
    }

    public void setCOMUNE_LOV(String value) {
        this.COMUNE_LOV.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of COMUNE_LOV

//AslComuneImpostaRow: method(s) of PROVINCIA @49-A3592314
    public LongField getPROVINCIAField() {
        return PROVINCIA;
    }

    public Long getPROVINCIA() {
        return PROVINCIA.getValue();
    }

    public void setPROVINCIA(Long value) {
        this.PROVINCIA.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of PROVINCIA

//AslComuneImpostaRow: method(s) of COMUNE @50-532A21D6
    public LongField getCOMUNEField() {
        return COMUNE;
    }

    public Long getCOMUNE() {
        return COMUNE.getValue();
    }

    public void setCOMUNE(Long value) {
        this.COMUNE.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of COMUNE

//AslComuneImpostaRow: method(s) of PROPOSTA @51-51BDA2ED
    public TextField getPROPOSTAField() {
        return PROPOSTA;
    }

    public String getPROPOSTA() {
        return PROPOSTA.getValue();
    }

    public void setPROPOSTA(String value) {
        this.PROPOSTA.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of PROPOSTA

//AslComuneImpostaRow: method(s) of ATTIVA @41-907CE3CD
    public TextField getATTIVAField() {
        return ATTIVA;
    }

    public String getATTIVA() {
        return ATTIVA.getValue();
    }

    public void setATTIVA(String value) {
        this.ATTIVA.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of ATTIVA

//AslComuneImpostaRow: method(s) of LABEL_UPD @39-F6A687CF
    public TextField getLABEL_UPDField() {
        return LABEL_UPD;
    }

    public String getLABEL_UPD() {
        return LABEL_UPD.getValue();
    }

    public void setLABEL_UPD(String value) {
        this.LABEL_UPD.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of LABEL_UPD

//AslComuneImpostaRow: class tail @5-FCB6E20C
}
//End AslComuneImpostaRow: class tail

