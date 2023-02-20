//AD4Istanze_VSearchRow: import @175-E71366F5
package ad4web.AD4IstaElenco;

import java.util.*;
import com.codecharge.db.*;
//End AD4Istanze_VSearchRow: import

//AD4Istanze_VSearchRow: class head @175-05911145
public class AD4Istanze_VSearchRow {
//End AD4Istanze_VSearchRow: class head

//AD4Istanze_VSearchRow: declare fiels @175-73D10C1B
    private TextField IMMAGINE_FILTRO = new TextField("IMMAGINE_FILTRO", "IMMAGINE_FILTRO");
    private TextField s_DESCRIZIONE = new TextField("s_DESCRIZIONE", "");
//End AD4Istanze_VSearchRow: declare fiels

//AD4Istanze_VSearchRow: constructor @175-F7798513
    public AD4Istanze_VSearchRow() {
    }
//End AD4Istanze_VSearchRow: constructor

//AD4Istanze_VSearchRow: method(s) of IMMAGINE_FILTRO @176-76125EC6
    public TextField getIMMAGINE_FILTROField() {
        return IMMAGINE_FILTRO;
    }

    public String getIMMAGINE_FILTRO() {
        return IMMAGINE_FILTRO.getValue();
    }

    public void setIMMAGINE_FILTRO(String value) {
        this.IMMAGINE_FILTRO.setValue(value);
    }
//End AD4Istanze_VSearchRow: method(s) of IMMAGINE_FILTRO

//AD4Istanze_VSearchRow: method(s) of s_DESCRIZIONE @177-E2DEFF5F
    public TextField getS_DESCRIZIONEField() {
        return s_DESCRIZIONE;
    }

    public String getS_DESCRIZIONE() {
        return s_DESCRIZIONE.getValue();
    }

    public void setS_DESCRIZIONE(String value) {
        this.s_DESCRIZIONE.setValue(value);
    }
//End AD4Istanze_VSearchRow: method(s) of s_DESCRIZIONE

//AD4Istanze_VSearchRow: class tail @175-FCB6E20C
}
//End AD4Istanze_VSearchRow: class tail

