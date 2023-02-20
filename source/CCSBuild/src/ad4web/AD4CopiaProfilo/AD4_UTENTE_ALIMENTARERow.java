//AD4_UTENTE_ALIMENTARERow: import @6-AAF92696
package ad4web.AD4CopiaProfilo;

import java.util.*;
import com.codecharge.db.*;
//End AD4_UTENTE_ALIMENTARERow: import

//AD4_UTENTE_ALIMENTARERow: class head @6-31B182BA
public class AD4_UTENTE_ALIMENTARERow {
//End AD4_UTENTE_ALIMENTARERow: class head

//AD4_UTENTE_ALIMENTARERow: declare fiels @6-E11BE7BE
    private TextField UTENTE_ALIMENTARE = new TextField("UTENTE_ALIMENTARE", "UTENTE_ALIMENTARE");
    private TextField UTENTE_ORIGINE = new TextField("UTENTE_ORIGINE", "UTENTE_ORIGINE");
    private TextField NOMINATIVO_ALIMENTARE = new TextField("NOMINATIVO_ALIMENTARE", "NOMINATIVO_ALIMENTARE");
    private TextField NOMINATIVO_ORIGINE = new TextField("NOMINATIVO_ORIGINE", "NOMINATIVO_ORIGINE");
    private TextField DATI_UTENTE_ALIMENTARE = new TextField("DATI_UTENTE_ALIMENTARE", "DATI_UTENTE_ALIMENTARE");
    private TextField DATI_UTENTE_ORIGINE = new TextField("DATI_UTENTE_ORIGINE", "DATI_UTENTE_ORIGINE");
    private TextField GRUPPI_DIAC_ALIMENTARE = new TextField("GRUPPI_DIAC_ALIMENTARE", "GRUPPI_DIAC_ALIMENTARE");
    private TextField COPIA_GRUPPI = new TextField("COPIA_GRUPPI", "GRP_SI_NO");
    private TextField GRUPPI_DIAC_ORIGINE = new TextField("GRUPPI_DIAC_ORIGINE", "GRUPPI_DIAC_ORIGINE");
    private TextField DIAC_ALIMENTARE = new TextField("DIAC_ALIMENTARE", "DIAC_ALIMENTARE");
    private TextField COPIA_DIAC = new TextField("COPIA_DIAC", "DIAC_SI_NO");
    private TextField DIAC_ORIGINE = new TextField("DIAC_ORIGINE", "DIAC_ORIGINE");
    private TextField CAAC_ALIMENTARE = new TextField("CAAC_ALIMENTARE", "CAAC_ALIMENTARE");
    private TextField COPIA_CAAC = new TextField("COPIA_CAAC", "CAAC_SI_NO");
    private TextField CAAC_ORIGINE = new TextField("CAAC_ORIGINE", "CAAC_ORIGINE");
//End AD4_UTENTE_ALIMENTARERow: declare fiels

//AD4_UTENTE_ALIMENTARERow: constructor @6-952504DC
    public AD4_UTENTE_ALIMENTARERow() {
    }
//End AD4_UTENTE_ALIMENTARERow: constructor

//AD4_UTENTE_ALIMENTARERow: method(s) of UTENTE_ALIMENTARE @87-02123E41
    public TextField getUTENTE_ALIMENTAREField() {
        return UTENTE_ALIMENTARE;
    }

    public String getUTENTE_ALIMENTARE() {
        return UTENTE_ALIMENTARE.getValue();
    }

    public void setUTENTE_ALIMENTARE(String value) {
        this.UTENTE_ALIMENTARE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of UTENTE_ALIMENTARE

//AD4_UTENTE_ALIMENTARERow: method(s) of UTENTE_ORIGINE @88-C035050B
    public TextField getUTENTE_ORIGINEField() {
        return UTENTE_ORIGINE;
    }

    public String getUTENTE_ORIGINE() {
        return UTENTE_ORIGINE.getValue();
    }

    public void setUTENTE_ORIGINE(String value) {
        this.UTENTE_ORIGINE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of UTENTE_ORIGINE

//AD4_UTENTE_ALIMENTARERow: method(s) of NOMINATIVO_ALIMENTARE @94-CCAC62C0
    public TextField getNOMINATIVO_ALIMENTAREField() {
        return NOMINATIVO_ALIMENTARE;
    }

    public String getNOMINATIVO_ALIMENTARE() {
        return NOMINATIVO_ALIMENTARE.getValue();
    }

    public void setNOMINATIVO_ALIMENTARE(String value) {
        this.NOMINATIVO_ALIMENTARE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of NOMINATIVO_ALIMENTARE

//AD4_UTENTE_ALIMENTARERow: method(s) of NOMINATIVO_ORIGINE @96-B0AD34EC
    public TextField getNOMINATIVO_ORIGINEField() {
        return NOMINATIVO_ORIGINE;
    }

    public String getNOMINATIVO_ORIGINE() {
        return NOMINATIVO_ORIGINE.getValue();
    }

    public void setNOMINATIVO_ORIGINE(String value) {
        this.NOMINATIVO_ORIGINE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of NOMINATIVO_ORIGINE

//AD4_UTENTE_ALIMENTARERow: method(s) of DATI_UTENTE_ALIMENTARE @95-0D65D2CA
    public TextField getDATI_UTENTE_ALIMENTAREField() {
        return DATI_UTENTE_ALIMENTARE;
    }

    public String getDATI_UTENTE_ALIMENTARE() {
        return DATI_UTENTE_ALIMENTARE.getValue();
    }

    public void setDATI_UTENTE_ALIMENTARE(String value) {
        this.DATI_UTENTE_ALIMENTARE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of DATI_UTENTE_ALIMENTARE

//AD4_UTENTE_ALIMENTARERow: method(s) of DATI_UTENTE_ORIGINE @97-D5E36C07
    public TextField getDATI_UTENTE_ORIGINEField() {
        return DATI_UTENTE_ORIGINE;
    }

    public String getDATI_UTENTE_ORIGINE() {
        return DATI_UTENTE_ORIGINE.getValue();
    }

    public void setDATI_UTENTE_ORIGINE(String value) {
        this.DATI_UTENTE_ORIGINE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of DATI_UTENTE_ORIGINE

//AD4_UTENTE_ALIMENTARERow: method(s) of GRUPPI_DIAC_ALIMENTARE @103-6385AB7C
    public TextField getGRUPPI_DIAC_ALIMENTAREField() {
        return GRUPPI_DIAC_ALIMENTARE;
    }

    public String getGRUPPI_DIAC_ALIMENTARE() {
        return GRUPPI_DIAC_ALIMENTARE.getValue();
    }

    public void setGRUPPI_DIAC_ALIMENTARE(String value) {
        this.GRUPPI_DIAC_ALIMENTARE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of GRUPPI_DIAC_ALIMENTARE

//AD4_UTENTE_ALIMENTARERow: method(s) of COPIA_GRUPPI @84-E19F6784
    public TextField getCOPIA_GRUPPIField() {
        return COPIA_GRUPPI;
    }

    public String getCOPIA_GRUPPI() {
        return COPIA_GRUPPI.getValue();
    }

    public void setCOPIA_GRUPPI(String value) {
        this.COPIA_GRUPPI.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of COPIA_GRUPPI

//AD4_UTENTE_ALIMENTARERow: method(s) of GRUPPI_DIAC_ORIGINE @106-BABA15AD
    public TextField getGRUPPI_DIAC_ORIGINEField() {
        return GRUPPI_DIAC_ORIGINE;
    }

    public String getGRUPPI_DIAC_ORIGINE() {
        return GRUPPI_DIAC_ORIGINE.getValue();
    }

    public void setGRUPPI_DIAC_ORIGINE(String value) {
        this.GRUPPI_DIAC_ORIGINE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of GRUPPI_DIAC_ORIGINE

//AD4_UTENTE_ALIMENTARERow: method(s) of DIAC_ALIMENTARE @105-EF622724
    public TextField getDIAC_ALIMENTAREField() {
        return DIAC_ALIMENTARE;
    }

    public String getDIAC_ALIMENTARE() {
        return DIAC_ALIMENTARE.getValue();
    }

    public void setDIAC_ALIMENTARE(String value) {
        this.DIAC_ALIMENTARE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of DIAC_ALIMENTARE

//AD4_UTENTE_ALIMENTARERow: method(s) of COPIA_DIAC @85-09F51B01
    public TextField getCOPIA_DIACField() {
        return COPIA_DIAC;
    }

    public String getCOPIA_DIAC() {
        return COPIA_DIAC.getValue();
    }

    public void setCOPIA_DIAC(String value) {
        this.COPIA_DIAC.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of COPIA_DIAC

//AD4_UTENTE_ALIMENTARERow: method(s) of DIAC_ORIGINE @107-0D984A0F
    public TextField getDIAC_ORIGINEField() {
        return DIAC_ORIGINE;
    }

    public String getDIAC_ORIGINE() {
        return DIAC_ORIGINE.getValue();
    }

    public void setDIAC_ORIGINE(String value) {
        this.DIAC_ORIGINE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of DIAC_ORIGINE

//AD4_UTENTE_ALIMENTARERow: method(s) of CAAC_ALIMENTARE @108-EEDC58F6
    public TextField getCAAC_ALIMENTAREField() {
        return CAAC_ALIMENTARE;
    }

    public String getCAAC_ALIMENTARE() {
        return CAAC_ALIMENTARE.getValue();
    }

    public void setCAAC_ALIMENTARE(String value) {
        this.CAAC_ALIMENTARE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of CAAC_ALIMENTARE

//AD4_UTENTE_ALIMENTARERow: method(s) of COPIA_CAAC @86-F3DA2096
    public TextField getCOPIA_CAACField() {
        return COPIA_CAAC;
    }

    public String getCOPIA_CAAC() {
        return COPIA_CAAC.getValue();
    }

    public void setCOPIA_CAAC(String value) {
        this.COPIA_CAAC.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of COPIA_CAAC

//AD4_UTENTE_ALIMENTARERow: method(s) of CAAC_ORIGINE @109-736023D4
    public TextField getCAAC_ORIGINEField() {
        return CAAC_ORIGINE;
    }

    public String getCAAC_ORIGINE() {
        return CAAC_ORIGINE.getValue();
    }

    public void setCAAC_ORIGINE(String value) {
        this.CAAC_ORIGINE.setValue(value);
    }
//End AD4_UTENTE_ALIMENTARERow: method(s) of CAAC_ORIGINE

//AD4_UTENTE_ALIMENTARERow: class tail @6-FCB6E20C
}
//End AD4_UTENTE_ALIMENTARERow: class tail

