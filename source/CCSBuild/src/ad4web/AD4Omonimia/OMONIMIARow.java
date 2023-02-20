//OMONIMIARow: import @6-55C16E2D
package ad4web.AD4Omonimia;

import java.util.*;
import com.codecharge.db.*;
//End OMONIMIARow: import

//OMONIMIARow: class head @6-3EC0540C
public class OMONIMIARow {
//End OMONIMIARow: class head

//OMONIMIARow: declare fiels @6-9549626C
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private LongField ID_OMONIMIA = new LongField("ID_OMONIMIA", "ID_OMONIMIA");
    private LongField SCELTO_PRIMARIO = new LongField("SCELTO_PRIMARIO", "SCELTO_PRIMARIO");
    private TextField DATI_SOGGETTO = new TextField("DATI_SOGGETTO", "DATI_SOGGETTO");
    private LongField UNIFICATO = new LongField("UNIFICATO", "UNIFICATO");
    private LongField COPIATO = new LongField("COPIATO", "COPIATO");
    private TextField P_UTENTE = new TextField("P_UTENTE", "UTENTE");
    private TextField SOSIA = new TextField("SOSIA", "SOSIA");
    private TextField SOSIA_NOMINATIVO = new TextField("SOSIA_NOMINATIVO", "SOSIA_NOMINATIVO");
    private TextField DATI_SOGGETTO_SOSIA = new TextField("DATI_SOGGETTO_SOSIA", "DATI_SOGGETTO_SOSIA");
    private TextField P_SOSIA = new TextField("P_SOSIA", "SOSIA");
    private LongField s_primario = new LongField("s_primario", "s_primario");
    private LongField s_ignorare = new LongField("s_ignorare", "IGNORARE");
    private TextField NOTE = new TextField("NOTE", "NOTE");
//End OMONIMIARow: declare fiels

//OMONIMIARow: constructor @6-A7F61EFB
    public OMONIMIARow() {
    }
//End OMONIMIARow: constructor

//OMONIMIARow: method(s) of UTENTE @78-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End OMONIMIARow: method(s) of UTENTE

//OMONIMIARow: method(s) of NOMINATIVO @122-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End OMONIMIARow: method(s) of NOMINATIVO

//OMONIMIARow: method(s) of ID_OMONIMIA @127-BE483183
    public LongField getID_OMONIMIAField() {
        return ID_OMONIMIA;
    }

    public Long getID_OMONIMIA() {
        return ID_OMONIMIA.getValue();
    }

    public void setID_OMONIMIA(Long value) {
        this.ID_OMONIMIA.setValue(value);
    }
//End OMONIMIARow: method(s) of ID_OMONIMIA

//OMONIMIARow: method(s) of SCELTO_PRIMARIO @128-658CA218
    public LongField getSCELTO_PRIMARIOField() {
        return SCELTO_PRIMARIO;
    }

    public Long getSCELTO_PRIMARIO() {
        return SCELTO_PRIMARIO.getValue();
    }

    public void setSCELTO_PRIMARIO(Long value) {
        this.SCELTO_PRIMARIO.setValue(value);
    }
//End OMONIMIARow: method(s) of SCELTO_PRIMARIO

//OMONIMIARow: method(s) of DATI_SOGGETTO @125-869C3147
    public TextField getDATI_SOGGETTOField() {
        return DATI_SOGGETTO;
    }

    public String getDATI_SOGGETTO() {
        return DATI_SOGGETTO.getValue();
    }

    public void setDATI_SOGGETTO(String value) {
        this.DATI_SOGGETTO.setValue(value);
    }
//End OMONIMIARow: method(s) of DATI_SOGGETTO

//OMONIMIARow: method(s) of UNIFICATO @129-5BC5BCE1
    public LongField getUNIFICATOField() {
        return UNIFICATO;
    }

    public Long getUNIFICATO() {
        return UNIFICATO.getValue();
    }

    public void setUNIFICATO(Long value) {
        this.UNIFICATO.setValue(value);
    }
//End OMONIMIARow: method(s) of UNIFICATO

//OMONIMIARow: method(s) of COPIATO @130-7B41C69F
    public LongField getCOPIATOField() {
        return COPIATO;
    }

    public Long getCOPIATO() {
        return COPIATO.getValue();
    }

    public void setCOPIATO(Long value) {
        this.COPIATO.setValue(value);
    }
//End OMONIMIARow: method(s) of COPIATO

//OMONIMIARow: method(s) of P_UTENTE @131-20BF9ECD
    public TextField getP_UTENTEField() {
        return P_UTENTE;
    }

    public String getP_UTENTE() {
        return P_UTENTE.getValue();
    }

    public void setP_UTENTE(String value) {
        this.P_UTENTE.setValue(value);
    }
//End OMONIMIARow: method(s) of P_UTENTE

//OMONIMIARow: method(s) of SOSIA @119-2B1323C5
    public TextField getSOSIAField() {
        return SOSIA;
    }

    public String getSOSIA() {
        return SOSIA.getValue();
    }

    public void setSOSIA(String value) {
        this.SOSIA.setValue(value);
    }
//End OMONIMIARow: method(s) of SOSIA

//OMONIMIARow: method(s) of SOSIA_NOMINATIVO @123-0085A0B4
    public TextField getSOSIA_NOMINATIVOField() {
        return SOSIA_NOMINATIVO;
    }

    public String getSOSIA_NOMINATIVO() {
        return SOSIA_NOMINATIVO.getValue();
    }

    public void setSOSIA_NOMINATIVO(String value) {
        this.SOSIA_NOMINATIVO.setValue(value);
    }
//End OMONIMIARow: method(s) of SOSIA_NOMINATIVO

//OMONIMIARow: method(s) of DATI_SOGGETTO_SOSIA @126-9A9E99DA
    public TextField getDATI_SOGGETTO_SOSIAField() {
        return DATI_SOGGETTO_SOSIA;
    }

    public String getDATI_SOGGETTO_SOSIA() {
        return DATI_SOGGETTO_SOSIA.getValue();
    }

    public void setDATI_SOGGETTO_SOSIA(String value) {
        this.DATI_SOGGETTO_SOSIA.setValue(value);
    }
//End OMONIMIARow: method(s) of DATI_SOGGETTO_SOSIA

//OMONIMIARow: method(s) of P_SOSIA @132-E0E70A5D
    public TextField getP_SOSIAField() {
        return P_SOSIA;
    }

    public String getP_SOSIA() {
        return P_SOSIA.getValue();
    }

    public void setP_SOSIA(String value) {
        this.P_SOSIA.setValue(value);
    }
//End OMONIMIARow: method(s) of P_SOSIA

//OMONIMIARow: method(s) of s_primario @133-3F6D57BD
    public LongField getS_primarioField() {
        return s_primario;
    }

    public Long getS_primario() {
        return s_primario.getValue();
    }

    public void setS_primario(Long value) {
        this.s_primario.setValue(value);
    }
//End OMONIMIARow: method(s) of s_primario

//OMONIMIARow: method(s) of s_ignorare @124-2F858721
    public LongField getS_ignorareField() {
        return s_ignorare;
    }

    public Long getS_ignorare() {
        return s_ignorare.getValue();
    }

    public void setS_ignorare(Long value) {
        this.s_ignorare.setValue(value);
    }
//End OMONIMIARow: method(s) of s_ignorare

//OMONIMIARow: method(s) of NOTE @22-3CDD33C5
    public TextField getNOTEField() {
        return NOTE;
    }

    public String getNOTE() {
        return NOTE.getValue();
    }

    public void setNOTE(String value) {
        this.NOTE.setValue(value);
    }
//End OMONIMIARow: method(s) of NOTE

//OMONIMIARow: class tail @6-FCB6E20C
}
//End OMONIMIARow: class tail

