//AD4Registro_VSearchRow: import @10-4B286E4A
package ad4web.AD4RegistroTree;

import java.util.*;
import com.codecharge.db.*;
//End AD4Registro_VSearchRow: import

//AD4Registro_VSearchRow: class head @10-B194EBE8
public class AD4Registro_VSearchRow {
//End AD4Registro_VSearchRow: class head

//AD4Registro_VSearchRow: declare fiels @10-3B59ACEE
    private TextField DESCRIZIONE_FILTRO = new TextField("DESCRIZIONE_FILTRO", "DESCRIZIONE_FILTRO");
    private TextField s_DESCRIZIONE = new TextField("s_DESCRIZIONE", "");
    private TextField USRORCL = new TextField("USRORCL", "USRORCL");
//End AD4Registro_VSearchRow: declare fiels

//AD4Registro_VSearchRow: constructor @10-A0AEA6AD
    public AD4Registro_VSearchRow() {
    }
//End AD4Registro_VSearchRow: constructor

//AD4Registro_VSearchRow: method(s) of DESCRIZIONE_FILTRO @11-B84799DF
    public TextField getDESCRIZIONE_FILTROField() {
        return DESCRIZIONE_FILTRO;
    }

    public String getDESCRIZIONE_FILTRO() {
        return DESCRIZIONE_FILTRO.getValue();
    }

    public void setDESCRIZIONE_FILTRO(String value) {
        this.DESCRIZIONE_FILTRO.setValue(value);
    }
//End AD4Registro_VSearchRow: method(s) of DESCRIZIONE_FILTRO

//AD4Registro_VSearchRow: method(s) of s_DESCRIZIONE @13-E2DEFF5F
    public TextField getS_DESCRIZIONEField() {
        return s_DESCRIZIONE;
    }

    public String getS_DESCRIZIONE() {
        return s_DESCRIZIONE.getValue();
    }

    public void setS_DESCRIZIONE(String value) {
        this.s_DESCRIZIONE.setValue(value);
    }
//End AD4Registro_VSearchRow: method(s) of s_DESCRIZIONE

//AD4Registro_VSearchRow: method(s) of USRORCL @32-66EC325D
    public TextField getUSRORCLField() {
        return USRORCL;
    }

    public String getUSRORCL() {
        return USRORCL.getValue();
    }

    public void setUSRORCL(String value) {
        this.USRORCL.setValue(value);
    }
//End AD4Registro_VSearchRow: method(s) of USRORCL

//AD4Registro_VSearchRow: class tail @10-FCB6E20C
}
//End AD4Registro_VSearchRow: class tail

