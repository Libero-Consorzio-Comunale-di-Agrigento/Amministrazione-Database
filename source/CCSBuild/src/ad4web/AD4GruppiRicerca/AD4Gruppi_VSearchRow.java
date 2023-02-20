//AD4Gruppi_VSearchRow: import @6-91AFF762
package ad4web.AD4GruppiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4Gruppi_VSearchRow: import

//AD4Gruppi_VSearchRow: class head @6-9C4DC990
public class AD4Gruppi_VSearchRow {
//End AD4Gruppi_VSearchRow: class head

//AD4Gruppi_VSearchRow: declare fiels @6-3E3CA20E
    private TextField IMMAGINE_FILTRO = new TextField("IMMAGINE_FILTRO", "FILTRO");
    private TextField s_DESCRIZIONE = new TextField("s_DESCRIZIONE", "");
    private TextField s_UTENTE = new TextField("s_UTENTE", "");
    private TextField Nuovo = new TextField("Nuovo", "Nuovo");
//End AD4Gruppi_VSearchRow: declare fiels

//AD4Gruppi_VSearchRow: constructor @6-43E8556E
    public AD4Gruppi_VSearchRow() {
    }
//End AD4Gruppi_VSearchRow: constructor

//AD4Gruppi_VSearchRow: method(s) of IMMAGINE_FILTRO @346-76125EC6
    public TextField getIMMAGINE_FILTROField() {
        return IMMAGINE_FILTRO;
    }

    public String getIMMAGINE_FILTRO() {
        return IMMAGINE_FILTRO.getValue();
    }

    public void setIMMAGINE_FILTRO(String value) {
        this.IMMAGINE_FILTRO.setValue(value);
    }
//End AD4Gruppi_VSearchRow: method(s) of IMMAGINE_FILTRO

//AD4Gruppi_VSearchRow: method(s) of s_DESCRIZIONE @331-E2DEFF5F
    public TextField getS_DESCRIZIONEField() {
        return s_DESCRIZIONE;
    }

    public String getS_DESCRIZIONE() {
        return s_DESCRIZIONE.getValue();
    }

    public void setS_DESCRIZIONE(String value) {
        this.s_DESCRIZIONE.setValue(value);
    }
//End AD4Gruppi_VSearchRow: method(s) of s_DESCRIZIONE

//AD4Gruppi_VSearchRow: method(s) of s_UTENTE @338-A2DA8BBA
    public TextField getS_UTENTEField() {
        return s_UTENTE;
    }

    public String getS_UTENTE() {
        return s_UTENTE.getValue();
    }

    public void setS_UTENTE(String value) {
        this.s_UTENTE.setValue(value);
    }
//End AD4Gruppi_VSearchRow: method(s) of s_UTENTE

//AD4Gruppi_VSearchRow: method(s) of Nuovo @328-42611BD0
    public TextField getNuovoField() {
        return Nuovo;
    }

    public String getNuovo() {
        return Nuovo.getValue();
    }

    public void setNuovo(String value) {
        this.Nuovo.setValue(value);
    }
//End AD4Gruppi_VSearchRow: method(s) of Nuovo

//AD4Gruppi_VSearchRow: class tail @6-FCB6E20C
}
//End AD4Gruppi_VSearchRow: class tail

