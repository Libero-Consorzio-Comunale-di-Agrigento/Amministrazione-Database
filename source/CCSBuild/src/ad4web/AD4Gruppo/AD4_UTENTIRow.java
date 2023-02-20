//AD4_UTENTIRow: import @23-273C5FE5
package ad4web.AD4Gruppo;

import java.util.*;
import com.codecharge.db.*;
//End AD4_UTENTIRow: import

//AD4_UTENTIRow: class head @23-D8604D31
public class AD4_UTENTIRow {
//End AD4_UTENTIRow: class head

//AD4_UTENTIRow: declare fiels @23-C97A8FF9
    private TextField NUOVO = new TextField("NUOVO", "");
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
    private TextField UTENTE_ORIG = new TextField("UTENTE_ORIG", "UTENTE_ORIG");
    private TextField SE_NUOVO = new TextField("SE_NUOVO", "SE_NUOVO");
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private TextField TIPO_UTENTE = new TextField("TIPO_UTENTE", "TIPO_UTENTE");
    private TextField NOTE = new TextField("NOTE", "NOTE");
    private TextField GRUPPO_LAVORO = new TextField("GRUPPO_LAVORO", "GRUPPO_LAVORO");
//End AD4_UTENTIRow: declare fiels

//AD4_UTENTIRow: constructor @23-A3432298
    public AD4_UTENTIRow() {
    }
//End AD4_UTENTIRow: constructor

//AD4_UTENTIRow: method(s) of NUOVO @82-54617F82
    public TextField getNUOVOField() {
        return NUOVO;
    }

    public String getNUOVO() {
        return NUOVO.getValue();
    }

    public void setNUOVO(String value) {
        this.NUOVO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of NUOVO

//AD4_UTENTIRow: method(s) of UTENTE @30-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of UTENTE

//AD4_UTENTIRow: method(s) of UTENTE_ORIG @41-AB006FEE
    public TextField getUTENTE_ORIGField() {
        return UTENTE_ORIG;
    }

    public String getUTENTE_ORIG() {
        return UTENTE_ORIG.getValue();
    }

    public void setUTENTE_ORIG(String value) {
        this.UTENTE_ORIG.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of UTENTE_ORIG

//AD4_UTENTIRow: method(s) of SE_NUOVO @103-DFB5DA7B
    public TextField getSE_NUOVOField() {
        return SE_NUOVO;
    }

    public String getSE_NUOVO() {
        return SE_NUOVO.getValue();
    }

    public void setSE_NUOVO(String value) {
        this.SE_NUOVO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of SE_NUOVO

//AD4_UTENTIRow: method(s) of NOMINATIVO @31-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of NOMINATIVO

//AD4_UTENTIRow: method(s) of TIPO_UTENTE @109-3F9E6945
    public TextField getTIPO_UTENTEField() {
        return TIPO_UTENTE;
    }

    public String getTIPO_UTENTE() {
        return TIPO_UTENTE.getValue();
    }

    public void setTIPO_UTENTE(String value) {
        this.TIPO_UTENTE.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of TIPO_UTENTE

//AD4_UTENTIRow: method(s) of NOTE @33-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of NOTE

//AD4_UTENTIRow: method(s) of GRUPPO_LAVORO @114-D83D33C7
    public TextField getGRUPPO_LAVOROField() {
        return GRUPPO_LAVORO;
    }

    public String getGRUPPO_LAVORO() {
        return GRUPPO_LAVORO.getValue();
    }

    public void setGRUPPO_LAVORO(String value) {
        this.GRUPPO_LAVORO.setValue(value);
    }
//End AD4_UTENTIRow: method(s) of GRUPPO_LAVORO

//AD4_UTENTIRow: class tail @23-FCB6E20C
}
//End AD4_UTENTIRow: class tail

