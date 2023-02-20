//AslComuneImpostaRow: import @5-762B7467
package restrict.Ad4DizionariAslComuneImposta;

import java.util.*;
import com.codecharge.db.*;
//End AslComuneImpostaRow: import

//AslComuneImpostaRow: class head @5-F758DEF9
public class AslComuneImpostaRow {
//End AslComuneImpostaRow: class head

//AslComuneImpostaRow: declare fiels @5-277CAF9A
    private TextField ASL_DESC = new TextField("ASL_DESC", "ASL_DESC");
    private LongField REGIONE_ASL = new LongField("REGIONE_ASL", "REGIONE_ASL");
    private LongField CODICE_ASL = new LongField("CODICE_ASL", "CODICE_ASL");
    private TextField Label1 = new TextField("Label1", "HIDE_BEGIN");
    private TextField COD_ASL = new TextField("COD_ASL", "COD_ASL");
    private TextField Label2 = new TextField("Label2", "HIDE_END");
    private TextField COMUNE_DESC = new TextField("COMUNE_DESC", "COMUNE_DESC");
    private LongField PROVINCIA = new LongField("PROVINCIA", "PROVINCIA");
    private LongField COMUNE = new LongField("COMUNE", "COMUNE");
    private TextField Label3 = new TextField("Label3", "HIDE_BEGIN2");
    private TextField COD_COMUNE_ASL = new TextField("COD_COMUNE_ASL", "COD_COMUNE_ASL");
    private TextField Label4 = new TextField("Label4", "HIDE_END2");
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

//AslComuneImpostaRow: method(s) of Label1 @54-6401A877
    public TextField getLabel1Field() {
        return Label1;
    }

    public String getLabel1() {
        return Label1.getValue();
    }

    public void setLabel1(String value) {
        this.Label1.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of Label1

//AslComuneImpostaRow: method(s) of COD_ASL @55-D7B78473
    public TextField getCOD_ASLField() {
        return COD_ASL;
    }

    public String getCOD_ASL() {
        return COD_ASL.getValue();
    }

    public void setCOD_ASL(String value) {
        this.COD_ASL.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of COD_ASL

//AslComuneImpostaRow: method(s) of Label2 @56-3C4C6B57
    public TextField getLabel2Field() {
        return Label2;
    }

    public String getLabel2() {
        return Label2.getValue();
    }

    public void setLabel2(String value) {
        this.Label2.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of Label2

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

//AslComuneImpostaRow: method(s) of Label3 @57-0B88D5B7
    public TextField getLabel3Field() {
        return Label3;
    }

    public String getLabel3() {
        return Label3.getValue();
    }

    public void setLabel3(String value) {
        this.Label3.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of Label3

//AslComuneImpostaRow: method(s) of COD_COMUNE_ASL @58-4C883277
    public TextField getCOD_COMUNE_ASLField() {
        return COD_COMUNE_ASL;
    }

    public String getCOD_COMUNE_ASL() {
        return COD_COMUNE_ASL.getValue();
    }

    public void setCOD_COMUNE_ASL(String value) {
        this.COD_COMUNE_ASL.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of COD_COMUNE_ASL

//AslComuneImpostaRow: method(s) of Label4 @59-8CD7ED17
    public TextField getLabel4Field() {
        return Label4;
    }

    public String getLabel4() {
        return Label4.getValue();
    }

    public void setLabel4(String value) {
        this.Label4.setValue(value);
    }
//End AslComuneImpostaRow: method(s) of Label4

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

