//ldap_configRow: import @110-71960620
package ad4web.AD4Omonimie;

import java.util.*;
import com.codecharge.db.*;
//End ldap_configRow: import

//ldap_configRow: class head @110-E8056342
public class ldap_configRow {
//End ldap_configRow: class head

//ldap_configRow: declare fiels @110-12796693
    private TextField NOMINATIVO = new TextField("NOMINATIVO", "NOMINATIVO");
    private TextField SOSIA_NOMINATIVO = new TextField("SOSIA_NOMINATIVO", "SOSIA_NOMINATIVO");
    private TextField UNIFICA_PROFILO = new TextField("UNIFICA_PROFILO", "UNIFICA_PROFILO");
    private TextField COPIA_PROFILO = new TextField("COPIA_PROFILO", "COPIA_PROFILO");
    private TextField IGNORA = new TextField("IGNORA", "IGNORA");
    private TextField MODIFICA = new TextField("MODIFICA", "MODIFICA");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField STRINGA = new TextField("STRINGA", "STRINGA");
    private TextField UTENTE_UNIFICARE = new TextField("UTENTE_UNIFICARE", "SOSIA");
    private TextField SOSIA = new TextField("SOSIA", "SOSIA");
    private TextField UTENTE_ALIMENTARE_UNIFICARE = new TextField("UTENTE_ALIMENTARE_UNIFICARE", "UTENTE");
    private TextField UTENTE = new TextField("UTENTE", "UTENTE");
    private TextField UTENTE_ALIMENTARE = new TextField("UTENTE_ALIMENTARE", "UTENTE");
    private TextField UTENTE_ORIGINE = new TextField("UTENTE_ORIGINE", "SOSIA");
    private TextField S_SOSIA = new TextField("S_SOSIA", "SOSIA");
    private TextField S_UTENTE = new TextField("S_UTENTE", "UTENTE");
//End ldap_configRow: declare fiels

//ldap_configRow: constructor @110-0E133156
    public ldap_configRow() {
    }
//End ldap_configRow: constructor

//ldap_configRow: method(s) of NOMINATIVO @111-3BDE962A
    public TextField getNOMINATIVOField() {
        return NOMINATIVO;
    }

    public String getNOMINATIVO() {
        return NOMINATIVO.getValue();
    }

    public void setNOMINATIVO(String value) {
        this.NOMINATIVO.setValue(value);
    }
//End ldap_configRow: method(s) of NOMINATIVO

//ldap_configRow: method(s) of SOSIA_NOMINATIVO @112-0085A0B4
    public TextField getSOSIA_NOMINATIVOField() {
        return SOSIA_NOMINATIVO;
    }

    public String getSOSIA_NOMINATIVO() {
        return SOSIA_NOMINATIVO.getValue();
    }

    public void setSOSIA_NOMINATIVO(String value) {
        this.SOSIA_NOMINATIVO.setValue(value);
    }
//End ldap_configRow: method(s) of SOSIA_NOMINATIVO

//ldap_configRow: method(s) of UNIFICA_PROFILO @145-FD19EE43
    public TextField getUNIFICA_PROFILOField() {
        return UNIFICA_PROFILO;
    }

    public String getUNIFICA_PROFILO() {
        return UNIFICA_PROFILO.getValue();
    }

    public void setUNIFICA_PROFILO(String value) {
        this.UNIFICA_PROFILO.setValue(value);
    }
//End ldap_configRow: method(s) of UNIFICA_PROFILO

//ldap_configRow: method(s) of COPIA_PROFILO @146-C7937519
    public TextField getCOPIA_PROFILOField() {
        return COPIA_PROFILO;
    }

    public String getCOPIA_PROFILO() {
        return COPIA_PROFILO.getValue();
    }

    public void setCOPIA_PROFILO(String value) {
        this.COPIA_PROFILO.setValue(value);
    }
//End ldap_configRow: method(s) of COPIA_PROFILO

//ldap_configRow: method(s) of IGNORA @151-F561C664
    public TextField getIGNORAField() {
        return IGNORA;
    }

    public String getIGNORA() {
        return IGNORA.getValue();
    }

    public void setIGNORA(String value) {
        this.IGNORA.setValue(value);
    }
//End ldap_configRow: method(s) of IGNORA

//ldap_configRow: method(s) of MODIFICA @154-EF108664
    public TextField getMODIFICAField() {
        return MODIFICA;
    }

    public String getMODIFICA() {
        return MODIFICA.getValue();
    }

    public void setMODIFICA(String value) {
        this.MODIFICA.setValue(value);
    }
//End ldap_configRow: method(s) of MODIFICA

//ldap_configRow: method(s) of AFCNavigator @131-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End ldap_configRow: method(s) of AFCNavigator

//ldap_configRow: method(s) of STRINGA @127-A3BF594E
    public TextField getSTRINGAField() {
        return STRINGA;
    }

    public String getSTRINGA() {
        return STRINGA.getValue();
    }

    public void setSTRINGA(String value) {
        this.STRINGA.setValue(value);
    }
//End ldap_configRow: method(s) of STRINGA

//ldap_configRow: method(s) of UTENTE_UNIFICARE @147-DD77E696
    public TextField getUTENTE_UNIFICAREField() {
        return UTENTE_UNIFICARE;
    }

    public String getUTENTE_UNIFICARE() {
        return UTENTE_UNIFICARE.getValue();
    }

    public void setUTENTE_UNIFICARE(String value) {
        this.UTENTE_UNIFICARE.setValue(value);
    }
//End ldap_configRow: method(s) of UTENTE_UNIFICARE

//ldap_configRow: method(s) of SOSIA @147-2B1323C5
    public TextField getSOSIAField() {
        return SOSIA;
    }

    public String getSOSIA() {
        return SOSIA.getValue();
    }

    public void setSOSIA(String value) {
        this.SOSIA.setValue(value);
    }
//End ldap_configRow: method(s) of SOSIA

//ldap_configRow: method(s) of UTENTE_ALIMENTARE_UNIFICARE @148-A1E9A354
    public TextField getUTENTE_ALIMENTARE_UNIFICAREField() {
        return UTENTE_ALIMENTARE_UNIFICARE;
    }

    public String getUTENTE_ALIMENTARE_UNIFICARE() {
        return UTENTE_ALIMENTARE_UNIFICARE.getValue();
    }

    public void setUTENTE_ALIMENTARE_UNIFICARE(String value) {
        this.UTENTE_ALIMENTARE_UNIFICARE.setValue(value);
    }
//End ldap_configRow: method(s) of UTENTE_ALIMENTARE_UNIFICARE

//ldap_configRow: method(s) of UTENTE @148-95517C36
    public TextField getUTENTEField() {
        return UTENTE;
    }

    public String getUTENTE() {
        return UTENTE.getValue();
    }

    public void setUTENTE(String value) {
        this.UTENTE.setValue(value);
    }
//End ldap_configRow: method(s) of UTENTE

//ldap_configRow: method(s) of UTENTE_ALIMENTARE @149-02123E41
    public TextField getUTENTE_ALIMENTAREField() {
        return UTENTE_ALIMENTARE;
    }

    public String getUTENTE_ALIMENTARE() {
        return UTENTE_ALIMENTARE.getValue();
    }

    public void setUTENTE_ALIMENTARE(String value) {
        this.UTENTE_ALIMENTARE.setValue(value);
    }
//End ldap_configRow: method(s) of UTENTE_ALIMENTARE

//ldap_configRow: method(s) of UTENTE_ORIGINE @150-C035050B
    public TextField getUTENTE_ORIGINEField() {
        return UTENTE_ORIGINE;
    }

    public String getUTENTE_ORIGINE() {
        return UTENTE_ORIGINE.getValue();
    }

    public void setUTENTE_ORIGINE(String value) {
        this.UTENTE_ORIGINE.setValue(value);
    }
//End ldap_configRow: method(s) of UTENTE_ORIGINE

//ldap_configRow: method(s) of S_SOSIA @155-54440694
    public TextField getS_SOSIAField() {
        return S_SOSIA;
    }

    public String getS_SOSIA() {
        return S_SOSIA.getValue();
    }

    public void setS_SOSIA(String value) {
        this.S_SOSIA.setValue(value);
    }
//End ldap_configRow: method(s) of S_SOSIA

//ldap_configRow: method(s) of S_UTENTE @156-03486328
    public TextField getS_UTENTEField() {
        return S_UTENTE;
    }

    public String getS_UTENTE() {
        return S_UTENTE.getValue();
    }

    public void setS_UTENTE(String value) {
        this.S_UTENTE.setValue(value);
    }
//End ldap_configRow: method(s) of S_UTENTE

//ldap_configRow: class tail @110-FCB6E20C
}
//End ldap_configRow: class tail

