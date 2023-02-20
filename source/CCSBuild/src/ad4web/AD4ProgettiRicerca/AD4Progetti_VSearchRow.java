//AD4Progetti_VSearchRow: import @6-472E1C9D
package ad4web.AD4ProgettiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4Progetti_VSearchRow: import

//AD4Progetti_VSearchRow: class head @6-99C2B8AE
public class AD4Progetti_VSearchRow {
//End AD4Progetti_VSearchRow: class head

//AD4Progetti_VSearchRow: declare fiels @6-571483A1
    private TextField IMMAGINE_FILTRO = new TextField("IMMAGINE_FILTRO", "IMMAGINE_FILTRO");
    private TextField s_DESCRIZIONE = new TextField("s_DESCRIZIONE", "");
    private TextField Nuovo = new TextField("Nuovo", "Nuovo");
//End AD4Progetti_VSearchRow: declare fiels

//AD4Progetti_VSearchRow: constructor @6-C727ADA1
    public AD4Progetti_VSearchRow() {
    }
//End AD4Progetti_VSearchRow: constructor

//AD4Progetti_VSearchRow: method(s) of IMMAGINE_FILTRO @340-76125EC6
    public TextField getIMMAGINE_FILTROField() {
        return IMMAGINE_FILTRO;
    }

    public String getIMMAGINE_FILTRO() {
        return IMMAGINE_FILTRO.getValue();
    }

    public void setIMMAGINE_FILTRO(String value) {
        this.IMMAGINE_FILTRO.setValue(value);
    }
//End AD4Progetti_VSearchRow: method(s) of IMMAGINE_FILTRO

//AD4Progetti_VSearchRow: method(s) of s_DESCRIZIONE @331-E2DEFF5F
    public TextField getS_DESCRIZIONEField() {
        return s_DESCRIZIONE;
    }

    public String getS_DESCRIZIONE() {
        return s_DESCRIZIONE.getValue();
    }

    public void setS_DESCRIZIONE(String value) {
        this.s_DESCRIZIONE.setValue(value);
    }
//End AD4Progetti_VSearchRow: method(s) of s_DESCRIZIONE

//AD4Progetti_VSearchRow: method(s) of Nuovo @328-42611BD0
    public TextField getNuovoField() {
        return Nuovo;
    }

    public String getNuovo() {
        return Nuovo.getValue();
    }

    public void setNuovo(String value) {
        this.Nuovo.setValue(value);
    }
//End AD4Progetti_VSearchRow: method(s) of Nuovo

//AD4Progetti_VSearchRow: class tail @6-FCB6E20C
}
//End AD4Progetti_VSearchRow: class tail

