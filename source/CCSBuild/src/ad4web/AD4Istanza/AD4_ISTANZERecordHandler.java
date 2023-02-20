//AD4_ISTANZEHandler Head @56-41E3CF44
package ad4web.AD4Istanza;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AD4_ISTANZERecordHandler implements RecordListener {
//End AD4_ISTANZEHandler Head

//BeforeShow Head @56-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values @56-1B4AFE84
        e.getRecord().getLink("CARATTERISTICHE").getParameter("TIPO_ACCESSO").setValue(( "I" ));
        e.getRecord().getLink("REGISTRO").getParameter("MENU").setValue(( "N" ));
        if (! e.getRecord().isEditMode()) {
            e.getRecord().getControl("PWD_MODIFIED").setDefaultValue("N");
        }
//End Set values

//BeforeShow Tail @56-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @56-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @56-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @56-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @56-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @56-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @56-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @56-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @56-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @56-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @56-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @56-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @56-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @56-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @56-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @56-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @56-FCB6E20C
    }
//End AfterDelete Tail

//AD4_ISTANZEHandler Tail @56-FCB6E20C
}
//End AD4_ISTANZEHandler Tail

