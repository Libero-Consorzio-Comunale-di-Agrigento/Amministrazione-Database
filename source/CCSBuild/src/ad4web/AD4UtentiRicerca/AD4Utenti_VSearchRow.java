//AD4Utenti_VSearchRow: import @6-6EF27B21
package ad4web.AD4UtentiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4Utenti_VSearchRow: import

//AD4Utenti_VSearchRow: class head @6-46947FBC
public class AD4Utenti_VSearchRow {
//End AD4Utenti_VSearchRow: class head

//AD4Utenti_VSearchRow: declare fiels @6-2FD01CA0
    private TextField IMMAGINE_FILTRO = new TextField("IMMAGINE_FILTRO", "IMMAGINE_FILTRO");
    private TextField s_NOMINATIVO = new TextField("s_NOMINATIVO", "");
    private TextField DESCRIZIONE_FILTRO = new TextField("DESCRIZIONE_FILTRO", "DESCRIZIONE_FILTRO");
    private TextField s_NOME = new TextField("s_NOME", "");
    private TextField s_COD_FISCALE = new TextField("s_COD_FISCALE", "");
    private TextField s_STATO = new TextField("s_STATO", "");
    private TextField s_GRUPPO = new TextField("s_GRUPPO", "");
    private TextField s_ACCESSO = new TextField("s_ACCESSO", "");
    private DateField s_DATA_DA = new DateField("s_DATA_DA", "");
    private DateField s_DATA_A = new DateField("s_DATA_A", "");
    private TextField s_RICERCA = new TextField("s_RICERCA", "");
    private TextField s_GRUPPO_LAVORO = new TextField("s_GRUPPO_LAVORO", "");
    private TextField Nuovo = new TextField("Nuovo", "Nuovo");
//End AD4Utenti_VSearchRow: declare fiels

//AD4Utenti_VSearchRow: constructor @6-3F2F897E
    public AD4Utenti_VSearchRow() {
    }
//End AD4Utenti_VSearchRow: constructor

//AD4Utenti_VSearchRow: method(s) of IMMAGINE_FILTRO @363-76125EC6
    public TextField getIMMAGINE_FILTROField() {
        return IMMAGINE_FILTRO;
    }

    public String getIMMAGINE_FILTRO() {
        return IMMAGINE_FILTRO.getValue();
    }

    public void setIMMAGINE_FILTRO(String value) {
        this.IMMAGINE_FILTRO.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of IMMAGINE_FILTRO

//AD4Utenti_VSearchRow: method(s) of s_NOMINATIVO @331-F8BF6226
    public TextField getS_NOMINATIVOField() {
        return s_NOMINATIVO;
    }

    public String getS_NOMINATIVO() {
        return s_NOMINATIVO.getValue();
    }

    public void setS_NOMINATIVO(String value) {
        this.s_NOMINATIVO.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of s_NOMINATIVO

//AD4Utenti_VSearchRow: method(s) of DESCRIZIONE_FILTRO @362-B84799DF
    public TextField getDESCRIZIONE_FILTROField() {
        return DESCRIZIONE_FILTRO;
    }

    public String getDESCRIZIONE_FILTRO() {
        return DESCRIZIONE_FILTRO.getValue();
    }

    public void setDESCRIZIONE_FILTRO(String value) {
        this.DESCRIZIONE_FILTRO.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of DESCRIZIONE_FILTRO

//AD4Utenti_VSearchRow: method(s) of s_NOME @292-71FC669D
    public TextField getS_NOMEField() {
        return s_NOME;
    }

    public String getS_NOME() {
        return s_NOME.getValue();
    }

    public void setS_NOME(String value) {
        this.s_NOME.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of s_NOME

//AD4Utenti_VSearchRow: method(s) of s_COD_FISCALE @92-F46D1533
    public TextField getS_COD_FISCALEField() {
        return s_COD_FISCALE;
    }

    public String getS_COD_FISCALE() {
        return s_COD_FISCALE.getValue();
    }

    public void setS_COD_FISCALE(String value) {
        this.s_COD_FISCALE.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of s_COD_FISCALE

//AD4Utenti_VSearchRow: method(s) of s_STATO @322-C2E8354F
    public TextField getS_STATOField() {
        return s_STATO;
    }

    public String getS_STATO() {
        return s_STATO.getValue();
    }

    public void setS_STATO(String value) {
        this.s_STATO.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of s_STATO

//AD4Utenti_VSearchRow: method(s) of s_GRUPPO @324-CBB850D0
    public TextField getS_GRUPPOField() {
        return s_GRUPPO;
    }

    public String getS_GRUPPO() {
        return s_GRUPPO.getValue();
    }

    public void setS_GRUPPO(String value) {
        this.s_GRUPPO.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of s_GRUPPO

//AD4Utenti_VSearchRow: method(s) of s_ACCESSO @325-93E237C8
    public TextField getS_ACCESSOField() {
        return s_ACCESSO;
    }

    public String getS_ACCESSO() {
        return s_ACCESSO.getValue();
    }

    public void setS_ACCESSO(String value) {
        this.s_ACCESSO.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of s_ACCESSO

//AD4Utenti_VSearchRow: method(s) of s_DATA_DA @284-06B3D21C
    public DateField getS_DATA_DAField() {
        return s_DATA_DA;
    }

    public Date getS_DATA_DA() {
        return s_DATA_DA.getValue();
    }

    public void setS_DATA_DA(Date value) {
        this.s_DATA_DA.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of s_DATA_DA

//AD4Utenti_VSearchRow: method(s) of s_DATA_A @288-FE68531A
    public DateField getS_DATA_AField() {
        return s_DATA_A;
    }

    public Date getS_DATA_A() {
        return s_DATA_A.getValue();
    }

    public void setS_DATA_A(Date value) {
        this.s_DATA_A.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of s_DATA_A

//AD4Utenti_VSearchRow: method(s) of s_RICERCA @289-92E9199A
    public TextField getS_RICERCAField() {
        return s_RICERCA;
    }

    public String getS_RICERCA() {
        return s_RICERCA.getValue();
    }

    public void setS_RICERCA(String value) {
        this.s_RICERCA.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of s_RICERCA

//AD4Utenti_VSearchRow: method(s) of s_GRUPPO_LAVORO @372-78D27E9F
    public TextField getS_GRUPPO_LAVOROField() {
        return s_GRUPPO_LAVORO;
    }

    public String getS_GRUPPO_LAVORO() {
        return s_GRUPPO_LAVORO.getValue();
    }

    public void setS_GRUPPO_LAVORO(String value) {
        this.s_GRUPPO_LAVORO.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of s_GRUPPO_LAVORO

//AD4Utenti_VSearchRow: method(s) of Nuovo @351-42611BD0
    public TextField getNuovoField() {
        return Nuovo;
    }

    public String getNuovo() {
        return Nuovo.getValue();
    }

    public void setNuovo(String value) {
        this.Nuovo.setValue(value);
    }
//End AD4Utenti_VSearchRow: method(s) of Nuovo

//AD4Utenti_VSearchRow: class tail @6-FCB6E20C
}
//End AD4Utenti_VSearchRow: class tail

