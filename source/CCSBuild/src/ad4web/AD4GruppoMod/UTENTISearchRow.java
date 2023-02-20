//UTENTISearchRow: import @84-444D74E7
package ad4web.AD4GruppoMod;

import java.util.*;
import com.codecharge.db.*;
//End UTENTISearchRow: import

//UTENTISearchRow: class head @84-029AAFC4
public class UTENTISearchRow {
//End UTENTISearchRow: class head

//UTENTISearchRow: declare fiels @84-3F99D8A9
    private TextField IMMAGINE_FILTRO = new TextField("IMMAGINE_FILTRO", "IMMAGINE_FILTRO");
    private TextField s_UTENTE = new TextField("s_UTENTE", "");
    private TextField s_TIPO_UTENTE = new TextField("s_TIPO_UTENTE", "");
//End UTENTISearchRow: declare fiels

//UTENTISearchRow: constructor @84-E52F346B
    public UTENTISearchRow() {
    }
//End UTENTISearchRow: constructor

//UTENTISearchRow: method(s) of IMMAGINE_FILTRO @94-76125EC6
    public TextField getIMMAGINE_FILTROField() {
        return IMMAGINE_FILTRO;
    }

    public String getIMMAGINE_FILTRO() {
        return IMMAGINE_FILTRO.getValue();
    }

    public void setIMMAGINE_FILTRO(String value) {
        this.IMMAGINE_FILTRO.setValue(value);
    }
//End UTENTISearchRow: method(s) of IMMAGINE_FILTRO

//UTENTISearchRow: method(s) of s_UTENTE @95-A2DA8BBA
    public TextField getS_UTENTEField() {
        return s_UTENTE;
    }

    public String getS_UTENTE() {
        return s_UTENTE.getValue();
    }

    public void setS_UTENTE(String value) {
        this.s_UTENTE.setValue(value);
    }
//End UTENTISearchRow: method(s) of s_UTENTE

//UTENTISearchRow: method(s) of s_TIPO_UTENTE @96-7176F91A
    public TextField getS_TIPO_UTENTEField() {
        return s_TIPO_UTENTE;
    }

    public String getS_TIPO_UTENTE() {
        return s_TIPO_UTENTE.getValue();
    }

    public void setS_TIPO_UTENTE(String value) {
        this.s_TIPO_UTENTE.setValue(value);
    }
//End UTENTISearchRow: method(s) of s_TIPO_UTENTE

//UTENTISearchRow: class tail @84-FCB6E20C
}
//End UTENTISearchRow: class tail

