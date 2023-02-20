//AD4Ruoli_VSearchRow: import @6-C604B991
package ad4web.AD4RuoliRicerca;

import java.util.*;
import com.codecharge.db.*;
//End AD4Ruoli_VSearchRow: import

//AD4Ruoli_VSearchRow: class head @6-67EE2AB8
public class AD4Ruoli_VSearchRow {
//End AD4Ruoli_VSearchRow: class head

//AD4Ruoli_VSearchRow: declare fiels @6-A6A8387B
    private TextField IMMAGINE_FILTRO = new TextField("IMMAGINE_FILTRO", "IMMAGINE_FILTRO");
    private TextField s_DESCRIZIONE = new TextField("s_DESCRIZIONE", "");
    private TextField AD4_RUOLI_Insert = new TextField("AD4_RUOLI_Insert", "");
//End AD4Ruoli_VSearchRow: declare fiels

//AD4Ruoli_VSearchRow: constructor @6-A113A82C
    public AD4Ruoli_VSearchRow() {
    }
//End AD4Ruoli_VSearchRow: constructor

//AD4Ruoli_VSearchRow: method(s) of IMMAGINE_FILTRO @388-76125EC6
    public TextField getIMMAGINE_FILTROField() {
        return IMMAGINE_FILTRO;
    }

    public String getIMMAGINE_FILTRO() {
        return IMMAGINE_FILTRO.getValue();
    }

    public void setIMMAGINE_FILTRO(String value) {
        this.IMMAGINE_FILTRO.setValue(value);
    }
//End AD4Ruoli_VSearchRow: method(s) of IMMAGINE_FILTRO

//AD4Ruoli_VSearchRow: method(s) of s_DESCRIZIONE @335-E2DEFF5F
    public TextField getS_DESCRIZIONEField() {
        return s_DESCRIZIONE;
    }

    public String getS_DESCRIZIONE() {
        return s_DESCRIZIONE.getValue();
    }

    public void setS_DESCRIZIONE(String value) {
        this.s_DESCRIZIONE.setValue(value);
    }
//End AD4Ruoli_VSearchRow: method(s) of s_DESCRIZIONE

//AD4Ruoli_VSearchRow: method(s) of AD4_RUOLI_Insert @384-BDD7A3C6
    public TextField getAD4_RUOLI_InsertField() {
        return AD4_RUOLI_Insert;
    }

    public String getAD4_RUOLI_Insert() {
        return AD4_RUOLI_Insert.getValue();
    }

    public void setAD4_RUOLI_Insert(String value) {
        this.AD4_RUOLI_Insert.setValue(value);
    }
//End AD4Ruoli_VSearchRow: method(s) of AD4_RUOLI_Insert

//AD4Ruoli_VSearchRow: class tail @6-FCB6E20C
}
//End AD4Ruoli_VSearchRow: class tail

