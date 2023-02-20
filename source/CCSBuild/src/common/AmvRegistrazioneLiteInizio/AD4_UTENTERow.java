//AD4_UTENTERow: import @6-C5AF12B8
package common.AmvRegistrazioneLiteInizio;

import java.util.*;
import com.codecharge.db.*;
//End AD4_UTENTERow: import

//AD4_UTENTERow: class head @6-1D90A030
public class AD4_UTENTERow {
//End AD4_UTENTERow: class head

//AD4_UTENTERow: declare fiels @6-9E0A49EB
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "");
    private TextField COGNOME = new TextField("COGNOME", "");
    private TextField NOME = new TextField("NOME", "");
    private TextField POSTA_OBBL1 = new TextField("POSTA_OBBL1", "POSTA_OBBL");
    private TextField VIA = new TextField("VIA", "");
    private TextField INDIRIZZO = new TextField("INDIRIZZO", "");
    private TextField NUM = new TextField("NUM", "");
    private TextField POSTA_OBBL2 = new TextField("POSTA_OBBL2", "POSTA_OBBL");
    private LongField PROVINCIA = new LongField("PROVINCIA", "");
    private TextField POSTA_OBBL3 = new TextField("POSTA_OBBL3", "POSTA_OBBL");
    private LongField COMUNE = new LongField("COMUNE", "");
    private TextField POSTA_OBBL4 = new TextField("POSTA_OBBL4", "POSTA_OBBL");
    private TextField CAP = new TextField("CAP", "");
    private TextField MAIL_OBBL1 = new TextField("MAIL_OBBL1", "MAIL_OBBL");
    private TextField INDIRIZZO_WEB = new TextField("INDIRIZZO_WEB", "");
    private TextField SMS_OBBL1 = new TextField("SMS_OBBL1", "SMS_OBBL");
    private TextField TELEFONO = new TextField("TELEFONO", "");
    private TextField FAX_OBBL1 = new TextField("FAX_OBBL1", "FAX_OBBL");
    private TextField FAX = new TextField("FAX", "");
    private TextField RR = new TextField("RR", "");
    private TextField REDIRECTION = new TextField("REDIRECTION", "REDIRECTION");
//End AD4_UTENTERow: declare fiels

//AD4_UTENTERow: constructor @6-F4EC37D7
    public AD4_UTENTERow() {
    }
//End AD4_UTENTERow: constructor

//AD4_UTENTERow: method(s) of NOMINATIVO @18-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End AD4_UTENTERow: method(s) of NOMINATIVO

//AD4_UTENTERow: method(s) of COGNOME @19-393F9325
    public TextField getCOGNOMEField() {
        return COGNOME;
    }

    public String getCOGNOME() {
        return COGNOME.getValue();
    }

    public void setCOGNOME(String value) {
        this.COGNOME.setValue(value);
    }
//End AD4_UTENTERow: method(s) of COGNOME

//AD4_UTENTERow: method(s) of NOME @20-DBA8086C
    public TextField getNOMEField() {
        return NOME;
    }

    public String getNOME() {
        return NOME.getValue();
    }

    public void setNOME(String value) {
        this.NOME.setValue(value);
    }
//End AD4_UTENTERow: method(s) of NOME

//AD4_UTENTERow: method(s) of POSTA_OBBL1 @47-15274B4D
    public TextField getPOSTA_OBBL1Field() {
        return POSTA_OBBL1;
    }

    public String getPOSTA_OBBL1() {
        return POSTA_OBBL1.getValue();
    }

    public void setPOSTA_OBBL1(String value) {
        this.POSTA_OBBL1.setValue(value);
    }
//End AD4_UTENTERow: method(s) of POSTA_OBBL1

//AD4_UTENTERow: method(s) of VIA @35-09F721F0
    public TextField getVIAField() {
        return VIA;
    }

    public String getVIA() {
        return VIA.getValue();
    }

    public void setVIA(String value) {
        this.VIA.setValue(value);
    }
//End AD4_UTENTERow: method(s) of VIA

//AD4_UTENTERow: method(s) of INDIRIZZO @36-79B8981C
    public TextField getINDIRIZZOField() {
        return INDIRIZZO;
    }

    public String getINDIRIZZO() {
        return INDIRIZZO.getValue();
    }

    public void setINDIRIZZO(String value) {
        this.INDIRIZZO.setValue(value);
    }
//End AD4_UTENTERow: method(s) of INDIRIZZO

//AD4_UTENTERow: method(s) of NUM @37-54D4C21D
    public TextField getNUMField() {
        return NUM;
    }

    public String getNUM() {
        return NUM.getValue();
    }

    public void setNUM(String value) {
        this.NUM.setValue(value);
    }
//End AD4_UTENTERow: method(s) of NUM

//AD4_UTENTERow: method(s) of POSTA_OBBL2 @48-958E267D
    public TextField getPOSTA_OBBL2Field() {
        return POSTA_OBBL2;
    }

    public String getPOSTA_OBBL2() {
        return POSTA_OBBL2.getValue();
    }

    public void setPOSTA_OBBL2(String value) {
        this.POSTA_OBBL2.setValue(value);
    }
//End AD4_UTENTERow: method(s) of POSTA_OBBL2

//AD4_UTENTERow: method(s) of PROVINCIA @38-A3592314
    public LongField getPROVINCIAField() {
        return PROVINCIA;
    }

    public Long getPROVINCIA() {
        return PROVINCIA.getValue();
    }

    public void setPROVINCIA(Long value) {
        this.PROVINCIA.setValue(value);
    }
//End AD4_UTENTERow: method(s) of PROVINCIA

//AD4_UTENTERow: method(s) of POSTA_OBBL3 @49-EA16FD6D
    public TextField getPOSTA_OBBL3Field() {
        return POSTA_OBBL3;
    }

    public String getPOSTA_OBBL3() {
        return POSTA_OBBL3.getValue();
    }

    public void setPOSTA_OBBL3(String value) {
        this.POSTA_OBBL3.setValue(value);
    }
//End AD4_UTENTERow: method(s) of POSTA_OBBL3

//AD4_UTENTERow: method(s) of COMUNE @40-532A21D6
    public LongField getCOMUNEField() {
        return COMUNE;
    }

    public Long getCOMUNE() {
        return COMUNE.getValue();
    }

    public void setCOMUNE(Long value) {
        this.COMUNE.setValue(value);
    }
//End AD4_UTENTERow: method(s) of COMUNE

//AD4_UTENTERow: method(s) of POSTA_OBBL4 @50-4FADFA5C
    public TextField getPOSTA_OBBL4Field() {
        return POSTA_OBBL4;
    }

    public String getPOSTA_OBBL4() {
        return POSTA_OBBL4.getValue();
    }

    public void setPOSTA_OBBL4(String value) {
        this.POSTA_OBBL4.setValue(value);
    }
//End AD4_UTENTERow: method(s) of POSTA_OBBL4

//AD4_UTENTERow: method(s) of CAP @43-CDFF7BC8
    public TextField getCAPField() {
        return CAP;
    }

    public String getCAP() {
        return CAP.getValue();
    }

    public void setCAP(String value) {
        this.CAP.setValue(value);
    }
//End AD4_UTENTERow: method(s) of CAP

//AD4_UTENTERow: method(s) of MAIL_OBBL1 @51-D09CEF78
    public TextField getMAIL_OBBL1Field() {
        return MAIL_OBBL1;
    }

    public String getMAIL_OBBL1() {
        return MAIL_OBBL1.getValue();
    }

    public void setMAIL_OBBL1(String value) {
        this.MAIL_OBBL1.setValue(value);
    }
//End AD4_UTENTERow: method(s) of MAIL_OBBL1

//AD4_UTENTERow: method(s) of INDIRIZZO_WEB @44-F718DE73
    public TextField getINDIRIZZO_WEBField() {
        return INDIRIZZO_WEB;
    }

    public String getINDIRIZZO_WEB() {
        return INDIRIZZO_WEB.getValue();
    }

    public void setINDIRIZZO_WEB(String value) {
        this.INDIRIZZO_WEB.setValue(value);
    }
//End AD4_UTENTERow: method(s) of INDIRIZZO_WEB

//AD4_UTENTERow: method(s) of SMS_OBBL1 @52-795880C0
    public TextField getSMS_OBBL1Field() {
        return SMS_OBBL1;
    }

    public String getSMS_OBBL1() {
        return SMS_OBBL1.getValue();
    }

    public void setSMS_OBBL1(String value) {
        this.SMS_OBBL1.setValue(value);
    }
//End AD4_UTENTERow: method(s) of SMS_OBBL1

//AD4_UTENTERow: method(s) of TELEFONO @45-E71ADE24
    public TextField getTELEFONOField() {
        return TELEFONO;
    }

    public String getTELEFONO() {
        return TELEFONO.getValue();
    }

    public void setTELEFONO(String value) {
        this.TELEFONO.setValue(value);
    }
//End AD4_UTENTERow: method(s) of TELEFONO

//AD4_UTENTERow: method(s) of FAX_OBBL1 @53-3ED0D20B
    public TextField getFAX_OBBL1Field() {
        return FAX_OBBL1;
    }

    public String getFAX_OBBL1() {
        return FAX_OBBL1.getValue();
    }

    public void setFAX_OBBL1(String value) {
        this.FAX_OBBL1.setValue(value);
    }
//End AD4_UTENTERow: method(s) of FAX_OBBL1

//AD4_UTENTERow: method(s) of FAX @46-AF87E71F
    public TextField getFAXField() {
        return FAX;
    }

    public String getFAX() {
        return FAX.getValue();
    }

    public void setFAX(String value) {
        this.FAX.setValue(value);
    }
//End AD4_UTENTERow: method(s) of FAX

//AD4_UTENTERow: method(s) of RR @60-9D393128
    public TextField getRRField() {
        return RR;
    }

    public String getRR() {
        return RR.getValue();
    }

    public void setRR(String value) {
        this.RR.setValue(value);
    }
//End AD4_UTENTERow: method(s) of RR

//AD4_UTENTERow: method(s) of REDIRECTION @65-E5AD4252
    public TextField getREDIRECTIONField() {
        return REDIRECTION;
    }

    public String getREDIRECTION() {
        return REDIRECTION.getValue();
    }

    public void setREDIRECTION(String value) {
        this.REDIRECTION.setValue(value);
    }
//End AD4_UTENTERow: method(s) of REDIRECTION

//AD4_UTENTERow: class tail @6-FCB6E20C
}
//End AD4_UTENTERow: class tail

