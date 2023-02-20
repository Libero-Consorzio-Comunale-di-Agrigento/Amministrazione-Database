//AD4Soggetti_VSearchRow: import @6-90910653
package ad4web.AD4SoggRicercaInclusa;

import java.util.*;
import com.codecharge.db.*;
//End AD4Soggetti_VSearchRow: import

//AD4Soggetti_VSearchRow: class head @6-0553F05D
public class AD4Soggetti_VSearchRow {
//End AD4Soggetti_VSearchRow: class head

//AD4Soggetti_VSearchRow: declare fiels @6-170CB5A5
    private TextField IMMAGINE_FILTRO = new TextField("IMMAGINE_FILTRO", "IMMAGINE_FILTRO");
    private TextField s_NOME = new TextField("s_NOME", "");
    private TextField s_RICERCA = new TextField("s_RICERCA", "");
    private TextField s_UTENTE = new TextField("s_UTENTE", "");
    private TextField s_ENTE = new TextField("s_ENTE", "");
    private TextField Nuovo = new TextField("Nuovo", "PAGINA");
//End AD4Soggetti_VSearchRow: declare fiels

//AD4Soggetti_VSearchRow: constructor @6-CF0D066B
    public AD4Soggetti_VSearchRow() {
    }
//End AD4Soggetti_VSearchRow: constructor

//AD4Soggetti_VSearchRow: method(s) of IMMAGINE_FILTRO @389-76125EC6
    public TextField getIMMAGINE_FILTROField() {
        return IMMAGINE_FILTRO;
    }

    public String getIMMAGINE_FILTRO() {
        return IMMAGINE_FILTRO.getValue();
    }

    public void setIMMAGINE_FILTRO(String value) {
        this.IMMAGINE_FILTRO.setValue(value);
    }
//End AD4Soggetti_VSearchRow: method(s) of IMMAGINE_FILTRO

//AD4Soggetti_VSearchRow: method(s) of s_NOME @337-71FC669D
    public TextField getS_NOMEField() {
        return s_NOME;
    }

    public String getS_NOME() {
        return s_NOME.getValue();
    }

    public void setS_NOME(String value) {
        this.s_NOME.setValue(value);
    }
//End AD4Soggetti_VSearchRow: method(s) of s_NOME

//AD4Soggetti_VSearchRow: method(s) of s_RICERCA @347-92E9199A
    public TextField getS_RICERCAField() {
        return s_RICERCA;
    }

    public String getS_RICERCA() {
        return s_RICERCA.getValue();
    }

    public void setS_RICERCA(String value) {
        this.s_RICERCA.setValue(value);
    }
//End AD4Soggetti_VSearchRow: method(s) of s_RICERCA

//AD4Soggetti_VSearchRow: method(s) of s_UTENTE @363-A2DA8BBA
    public TextField getS_UTENTEField() {
        return s_UTENTE;
    }

    public String getS_UTENTE() {
        return s_UTENTE.getValue();
    }

    public void setS_UTENTE(String value) {
        this.s_UTENTE.setValue(value);
    }
//End AD4Soggetti_VSearchRow: method(s) of s_UTENTE

//AD4Soggetti_VSearchRow: method(s) of s_ENTE @378-61AA297A
    public TextField getS_ENTEField() {
        return s_ENTE;
    }

    public String getS_ENTE() {
        return s_ENTE.getValue();
    }

    public void setS_ENTE(String value) {
        this.s_ENTE.setValue(value);
    }
//End AD4Soggetti_VSearchRow: method(s) of s_ENTE

//AD4Soggetti_VSearchRow: method(s) of Nuovo @390-42611BD0
    public TextField getNuovoField() {
        return Nuovo;
    }

    public String getNuovo() {
        return Nuovo.getValue();
    }

    public void setNuovo(String value) {
        this.Nuovo.setValue(value);
    }
//End AD4Soggetti_VSearchRow: method(s) of Nuovo

//AD4Soggetti_VSearchRow: class tail @6-FCB6E20C
}
//End AD4Soggetti_VSearchRow: class tail

