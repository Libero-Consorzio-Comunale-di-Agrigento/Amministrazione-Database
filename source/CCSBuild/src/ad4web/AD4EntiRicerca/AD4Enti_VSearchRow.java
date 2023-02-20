//AD4Enti_VSearchRow: import @6-EA0254E8
package ad4web.AD4EntiRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4Enti_VSearchRow: import

//AD4Enti_VSearchRow: class head @6-E7124281
public class AD4Enti_VSearchRow {
//End AD4Enti_VSearchRow: class head

//AD4Enti_VSearchRow: declare fiels @6-571483A1
    private TextField IMMAGINE_FILTRO = new TextField("IMMAGINE_FILTRO", "IMMAGINE_FILTRO");
    private TextField s_DESCRIZIONE = new TextField("s_DESCRIZIONE", "");
    private TextField Nuovo = new TextField("Nuovo", "Nuovo");
//End AD4Enti_VSearchRow: declare fiels

//AD4Enti_VSearchRow: constructor @6-B45F9929
    public AD4Enti_VSearchRow() {
    }
//End AD4Enti_VSearchRow: constructor

//AD4Enti_VSearchRow: method(s) of IMMAGINE_FILTRO @343-76125EC6
    public TextField getIMMAGINE_FILTROField() {
        return IMMAGINE_FILTRO;
    }

    public String getIMMAGINE_FILTRO() {
        return IMMAGINE_FILTRO.getValue();
    }

    public void setIMMAGINE_FILTRO(String value) {
        this.IMMAGINE_FILTRO.setValue(value);
    }
//End AD4Enti_VSearchRow: method(s) of IMMAGINE_FILTRO

//AD4Enti_VSearchRow: method(s) of s_DESCRIZIONE @344-E2DEFF5F
    public TextField getS_DESCRIZIONEField() {
        return s_DESCRIZIONE;
    }

    public String getS_DESCRIZIONE() {
        return s_DESCRIZIONE.getValue();
    }

    public void setS_DESCRIZIONE(String value) {
        this.s_DESCRIZIONE.setValue(value);
    }
//End AD4Enti_VSearchRow: method(s) of s_DESCRIZIONE

//AD4Enti_VSearchRow: method(s) of Nuovo @354-42611BD0
    public TextField getNuovoField() {
        return Nuovo;
    }

    public String getNuovo() {
        return Nuovo.getValue();
    }

    public void setNuovo(String value) {
        this.Nuovo.setValue(value);
    }
//End AD4Enti_VSearchRow: method(s) of Nuovo

//AD4Enti_VSearchRow: class tail @6-FCB6E20C
}
//End AD4Enti_VSearchRow: class tail

