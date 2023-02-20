//AslImpostaHandler Head @5-9BC408A8
package restrict.Ad4DizionariAslImposta;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AslImpostaRecordHandler implements RecordListener {
//End AslImpostaHandler Head

//BeforeShow Head @5-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values @5-765489DA
        if (! e.getRecord().isEditMode()) {
            e.getRecord().getControl("ATTIVA").setDefaultValue("S");
        }
//End Set values

//BeforeShow Tail @5-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @5-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @5-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @5-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @5-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @5-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @5-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @5-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @5-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @5-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @5-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @5-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @5-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @5-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @5-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @5-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @5-FCB6E20C
    }
//End AfterDelete Tail

//AslImpostaHandler Tail @5-FCB6E20C
}
//End AslImpostaHandler Tail

