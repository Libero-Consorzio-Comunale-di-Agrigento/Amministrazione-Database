//AD4Eventi_VSearchRow: import @358-28F1335F
package ad4web.AD4EventiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4Eventi_VSearchRow: import

//AD4Eventi_VSearchRow: class head @358-BC0BB363
public class AD4Eventi_VSearchRow {
//End AD4Eventi_VSearchRow: class head

//AD4Eventi_VSearchRow: declare fiels @358-B149ED0A
    private TextField s_TIPO = new TextField("s_TIPO", "");
    private TextField DESCRIZIONE_FILTRO = new TextField("DESCRIZIONE_FILTRO", "DESCRIZIONE_FILTRO");
    private TextField IMMAGINE_FILTRO = new TextField("IMMAGINE_FILTRO", "IMMAGINE_FILTRO");
    private DateField s_DATA_DA = new DateField("s_DATA_DA", "");
    private DateField s_DATA_A = new DateField("s_DATA_A", "");
    private TextField s_TESTO = new TextField("s_TESTO", "");
    private TextField s_UTENTE = new TextField("s_UTENTE", "");
    private TextField s_MODULO = new TextField("s_MODULO", "");
    private TextField s_ISTANZA = new TextField("s_ISTANZA", "");
    private TextField s_DB_USER = new TextField("s_DB_USER", "");
    private TextField s_GRAVITA = new TextField("s_GRAVITA", "");
    private TextField s_STATO = new TextField("s_STATO", "");
    private TextField s_FILE = new TextField("s_FILE", "");
    private TextField s_NOTE = new TextField("s_NOTE", "");
//End AD4Eventi_VSearchRow: declare fiels

//AD4Eventi_VSearchRow: constructor @358-BE05ED1E
    public AD4Eventi_VSearchRow() {
    }
//End AD4Eventi_VSearchRow: constructor

//AD4Eventi_VSearchRow: method(s) of s_TIPO @383-6E4BA706
    public TextField getS_TIPOField() {
        return s_TIPO;
    }

    public String getS_TIPO() {
        return s_TIPO.getValue();
    }

    public void setS_TIPO(String value) {
        this.s_TIPO.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_TIPO

//AD4Eventi_VSearchRow: method(s) of DESCRIZIONE_FILTRO @410-B84799DF
    public TextField getDESCRIZIONE_FILTROField() {
        return DESCRIZIONE_FILTRO;
    }

    public String getDESCRIZIONE_FILTRO() {
        return DESCRIZIONE_FILTRO.getValue();
    }

    public void setDESCRIZIONE_FILTRO(String value) {
        this.DESCRIZIONE_FILTRO.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of DESCRIZIONE_FILTRO

//AD4Eventi_VSearchRow: method(s) of IMMAGINE_FILTRO @411-76125EC6
    public TextField getIMMAGINE_FILTROField() {
        return IMMAGINE_FILTRO;
    }

    public String getIMMAGINE_FILTRO() {
        return IMMAGINE_FILTRO.getValue();
    }

    public void setIMMAGINE_FILTRO(String value) {
        this.IMMAGINE_FILTRO.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of IMMAGINE_FILTRO

//AD4Eventi_VSearchRow: method(s) of s_DATA_DA @367-06B3D21C
    public DateField getS_DATA_DAField() {
        return s_DATA_DA;
    }

    public Date getS_DATA_DA() {
        return s_DATA_DA.getValue();
    }

    public void setS_DATA_DA(Date value) {
        this.s_DATA_DA.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_DATA_DA

//AD4Eventi_VSearchRow: method(s) of s_DATA_A @368-FE68531A
    public DateField getS_DATA_AField() {
        return s_DATA_A;
    }

    public Date getS_DATA_A() {
        return s_DATA_A.getValue();
    }

    public void setS_DATA_A(Date value) {
        this.s_DATA_A.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_DATA_A

//AD4Eventi_VSearchRow: method(s) of s_TESTO @362-8A65CBD6
    public TextField getS_TESTOField() {
        return s_TESTO;
    }

    public String getS_TESTO() {
        return s_TESTO.getValue();
    }

    public void setS_TESTO(String value) {
        this.s_TESTO.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_TESTO

//AD4Eventi_VSearchRow: method(s) of s_UTENTE @395-A2DA8BBA
    public TextField getS_UTENTEField() {
        return s_UTENTE;
    }

    public String getS_UTENTE() {
        return s_UTENTE.getValue();
    }

    public void setS_UTENTE(String value) {
        this.s_UTENTE.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_UTENTE

//AD4Eventi_VSearchRow: method(s) of s_MODULO @396-1EDA3FC7
    public TextField getS_MODULOField() {
        return s_MODULO;
    }

    public String getS_MODULO() {
        return s_MODULO.getValue();
    }

    public void setS_MODULO(String value) {
        this.s_MODULO.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_MODULO

//AD4Eventi_VSearchRow: method(s) of s_ISTANZA @391-846A8DEB
    public TextField getS_ISTANZAField() {
        return s_ISTANZA;
    }

    public String getS_ISTANZA() {
        return s_ISTANZA.getValue();
    }

    public void setS_ISTANZA(String value) {
        this.s_ISTANZA.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_ISTANZA

//AD4Eventi_VSearchRow: method(s) of s_DB_USER @397-09B8DBDA
    public TextField getS_DB_USERField() {
        return s_DB_USER;
    }

    public String getS_DB_USER() {
        return s_DB_USER.getValue();
    }

    public void setS_DB_USER(String value) {
        this.s_DB_USER.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_DB_USER

//AD4Eventi_VSearchRow: method(s) of s_GRAVITA @363-84489B45
    public TextField getS_GRAVITAField() {
        return s_GRAVITA;
    }

    public String getS_GRAVITA() {
        return s_GRAVITA.getValue();
    }

    public void setS_GRAVITA(String value) {
        this.s_GRAVITA.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_GRAVITA

//AD4Eventi_VSearchRow: method(s) of s_STATO @364-C2E8354F
    public TextField getS_STATOField() {
        return s_STATO;
    }

    public String getS_STATO() {
        return s_STATO.getValue();
    }

    public void setS_STATO(String value) {
        this.s_STATO.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_STATO

//AD4Eventi_VSearchRow: method(s) of s_FILE @394-359A3B53
    public TextField getS_FILEField() {
        return s_FILE;
    }

    public String getS_FILE() {
        return s_FILE.getValue();
    }

    public void setS_FILE(String value) {
        this.s_FILE.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_FILE

//AD4Eventi_VSearchRow: method(s) of s_NOTE @388-D3032E42
    public TextField getS_NOTEField() {
        return s_NOTE;
    }

    public String getS_NOTE() {
        return s_NOTE.getValue();
    }

    public void setS_NOTE(String value) {
        this.s_NOTE.setValue(value);
    }
//End AD4Eventi_VSearchRow: method(s) of s_NOTE

//AD4Eventi_VSearchRow: class tail @358-FCB6E20C
}
//End AD4Eventi_VSearchRow: class tail

