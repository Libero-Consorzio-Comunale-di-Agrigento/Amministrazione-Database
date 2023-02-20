//AD4Gruppi_VSearchRow: import @10-4B4528B8
package ad4web.AD4GruppiTreeInclusa;

import java.util.*;
import com.codecharge.db.*;
//End AD4Gruppi_VSearchRow: import

//AD4Gruppi_VSearchRow: class head @10-9C4DC990
public class AD4Gruppi_VSearchRow {
//End AD4Gruppi_VSearchRow: class head

//AD4Gruppi_VSearchRow: declare fiels @10-101CC6A7
    private TextField DESCRIZIONE_FILTRO = new TextField("DESCRIZIONE_FILTRO", "DESCRIZIONE_FILTRO");
    private TextField s_DESCRIZIONE = new TextField("s_DESCRIZIONE", "");
//End AD4Gruppi_VSearchRow: declare fiels

//AD4Gruppi_VSearchRow: constructor @10-43E8556E
    public AD4Gruppi_VSearchRow() {
    }
//End AD4Gruppi_VSearchRow: constructor

//AD4Gruppi_VSearchRow: method(s) of DESCRIZIONE_FILTRO @11-B84799DF
    public TextField getDESCRIZIONE_FILTROField() {
        return DESCRIZIONE_FILTRO;
    }

    public String getDESCRIZIONE_FILTRO() {
        return DESCRIZIONE_FILTRO.getValue();
    }

    public void setDESCRIZIONE_FILTRO(String value) {
        this.DESCRIZIONE_FILTRO.setValue(value);
    }
//End AD4Gruppi_VSearchRow: method(s) of DESCRIZIONE_FILTRO

//AD4Gruppi_VSearchRow: method(s) of s_DESCRIZIONE @13-E2DEFF5F
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

//AD4Gruppi_VSearchRow: class tail @10-FCB6E20C
}
//End AD4Gruppi_VSearchRow: class tail

