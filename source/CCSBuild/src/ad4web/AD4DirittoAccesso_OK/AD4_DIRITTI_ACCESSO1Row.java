//AD4_DIRITTI_ACCESSO1Row: import @38-1B834105
package ad4web.AD4DirittoAccesso_OK;

import java.util.*;
import com.codecharge.db.*;
//End AD4_DIRITTI_ACCESSO1Row: import

//AD4_DIRITTI_ACCESSO1Row: class head @38-7D217F52
public class AD4_DIRITTI_ACCESSO1Row {
//End AD4_DIRITTI_ACCESSO1Row: class head

//AD4_DIRITTI_ACCESSO1Row: declare fiels @38-DD2A840A
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private TextField CaratteristicheAccesso = new TextField("CaratteristicheAccesso", "");
    private LongField SEQUENZA = new LongField("SEQUENZA", "SEQUENZA");
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
    private TextField ISLISTBOX = new TextField("ISLISTBOX", "");
    private TextField MODULO = new TextField("MODULO", "MODULO");
    private TextField MODULO_ORIG = new TextField("MODULO_ORIG", "MODULO");
    private TextField ISTANZA = new TextField("ISTANZA", "ISTANZA");
    private TextField ISTANZA_ORIG = new TextField("ISTANZA_ORIG", "ISTANZA");
    private TextField RUOLO = new TextField("RUOLO", "RUOLO");
    private TextField NOTE = new TextField("NOTE", "NOTE");
    private DateField ULTIMO_ACCESSO = new DateField("ULTIMO_ACCESSO", "ULTIMO_ACCESSO");
    private LongField NUMERO_ACCESSI = new LongField("NUMERO_ACCESSI", "NUMERO_ACCESSI");
    private TextField GRUPPO = new TextField("GRUPPO", "GRUPPO");
    private TextField TIPO_ACCESSO = new TextField("TIPO_ACCESSO", "TIPO_ACCESSO");
    private TextField PROGETTO = new TextField("PROGETTO", "PROGETTO");
//End AD4_DIRITTI_ACCESSO1Row: declare fiels

//AD4_DIRITTI_ACCESSO1Row: constructor @38-5CE6281C
    public AD4_DIRITTI_ACCESSO1Row() {
    }
//End AD4_DIRITTI_ACCESSO1Row: constructor

//AD4_DIRITTI_ACCESSO1Row: method(s) of NOMINATIVO @138-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of NOMINATIVO

//AD4_DIRITTI_ACCESSO1Row: method(s) of CaratteristicheAccesso @139-472E96DE
    public TextField getCaratteristicheAccessoField() {
        return CaratteristicheAccesso;
    }

    public String getCaratteristicheAccesso() {
        return CaratteristicheAccesso.getValue();
    }

    public void setCaratteristicheAccesso(String value) {
        this.CaratteristicheAccesso.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of CaratteristicheAccesso

//AD4_DIRITTI_ACCESSO1Row: method(s) of SEQUENZA @49-E6112114
    public LongField getSEQUENZAField() {
        return SEQUENZA;
    }

    public Long getSEQUENZA() {
        return SEQUENZA.getValue();
    }

    public void setSEQUENZA(Long value) {
        this.SEQUENZA.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of SEQUENZA

//AD4_DIRITTI_ACCESSO1Row: method(s) of UTENTE @149-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of UTENTE

//AD4_DIRITTI_ACCESSO1Row: method(s) of ISLISTBOX @153-8158BD72
    public TextField getISLISTBOXField() {
        return ISLISTBOX;
    }

    public String getISLISTBOX() {
        return ISLISTBOX.getValue();
    }

    public void setISLISTBOX(String value) {
        this.ISLISTBOX.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of ISLISTBOX

//AD4_DIRITTI_ACCESSO1Row: method(s) of MODULO @46-D7A676D3
    public TextField getMODULOField() {
        return MODULO;
    }

    public String getMODULO() {
        return MODULO.getValue();
    }

    public void setMODULO(String value) {
        this.MODULO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of MODULO

//AD4_DIRITTI_ACCESSO1Row: method(s) of MODULO_ORIG @83-8590EB2D
    public TextField getMODULO_ORIGField() {
        return MODULO_ORIG;
    }

    public String getMODULO_ORIG() {
        return MODULO_ORIG.getValue();
    }

    public void setMODULO_ORIG(String value) {
        this.MODULO_ORIG.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of MODULO_ORIG

//AD4_DIRITTI_ACCESSO1Row: method(s) of ISTANZA @47-CC23EEBF
    public TextField getISTANZAField() {
        return ISTANZA;
    }

    public String getISTANZA() {
        return ISTANZA.getValue();
    }

    public void setISTANZA(String value) {
        this.ISTANZA.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of ISTANZA

//AD4_DIRITTI_ACCESSO1Row: method(s) of ISTANZA_ORIG @84-8837BB9C
    public TextField getISTANZA_ORIGField() {
        return ISTANZA_ORIG;
    }

    public String getISTANZA_ORIG() {
        return ISTANZA_ORIG.getValue();
    }

    public void setISTANZA_ORIG(String value) {
        this.ISTANZA_ORIG.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of ISTANZA_ORIG

//AD4_DIRITTI_ACCESSO1Row: method(s) of RUOLO @48-E9232889
    public TextField getRUOLOField() {
        return RUOLO;
    }

    public String getRUOLO() {
        return RUOLO.getValue();
    }

    public void setRUOLO(String value) {
        this.RUOLO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of RUOLO

//AD4_DIRITTI_ACCESSO1Row: method(s) of NOTE @53-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of NOTE

//AD4_DIRITTI_ACCESSO1Row: method(s) of ULTIMO_ACCESSO @50-6E82FE67
    public DateField getULTIMO_ACCESSOField() {
        return ULTIMO_ACCESSO;
    }

    public Date getULTIMO_ACCESSO() {
        return ULTIMO_ACCESSO.getValue();
    }

    public void setULTIMO_ACCESSO(Date value) {
        this.ULTIMO_ACCESSO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of ULTIMO_ACCESSO

//AD4_DIRITTI_ACCESSO1Row: method(s) of NUMERO_ACCESSI @51-217686D1
    public LongField getNUMERO_ACCESSIField() {
        return NUMERO_ACCESSI;
    }

    public Long getNUMERO_ACCESSI() {
        return NUMERO_ACCESSI.getValue();
    }

    public void setNUMERO_ACCESSI(Long value) {
        this.NUMERO_ACCESSI.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of NUMERO_ACCESSI

//AD4_DIRITTI_ACCESSO1Row: method(s) of GRUPPO @52-C886BC8A
    public TextField getGRUPPOField() {
        return GRUPPO;
    }

    public String getGRUPPO() {
        return GRUPPO.getValue();
    }

    public void setGRUPPO(String value) {
        this.GRUPPO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of GRUPPO

//AD4_DIRITTI_ACCESSO1Row: method(s) of TIPO_ACCESSO @140-D9722F75
    public TextField getTIPO_ACCESSOField() {
        return TIPO_ACCESSO;
    }

    public String getTIPO_ACCESSO() {
        return TIPO_ACCESSO.getValue();
    }

    public void setTIPO_ACCESSO(String value) {
        this.TIPO_ACCESSO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of TIPO_ACCESSO

//AD4_DIRITTI_ACCESSO1Row: method(s) of PROGETTO @141-7637616D
    public TextField getPROGETTOField() {
        return PROGETTO;
    }

    public String getPROGETTO() {
        return PROGETTO.getValue();
    }

    public void setPROGETTO(String value) {
        this.PROGETTO.setValue(value);
    }
//End AD4_DIRITTI_ACCESSO1Row: method(s) of PROGETTO

//AD4_DIRITTI_ACCESSO1Row: class tail @38-FCB6E20C
}
//End AD4_DIRITTI_ACCESSO1Row: class tail

