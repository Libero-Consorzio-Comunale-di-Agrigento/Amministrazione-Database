﻿Insert into AMV_DIRITTI
   (ID_DIRITTO, ID_AREA, GRUPPO, ACCESSO, ID_TIPOLOGIA)
 Values
   (1, 1, NULL, 'C', NULL);
Insert into AMV_DIRITTI
   (ID_DIRITTO, ID_AREA, GRUPPO, ACCESSO, ID_TIPOLOGIA)
 Values
   (2, 2, 'GUESTS', 'R', NULL);
Insert into AMV_DIRITTI
   (ID_DIRITTO, ID_AREA, GRUPPO, ACCESSO, ID_TIPOLOGIA)
 Values
   (3, 1, 'ADMINS', 'U', NULL);
Insert into AMV_DIRITTI
   (ID_DIRITTO, ID_AREA, GRUPPO, ACCESSO, ID_TIPOLOGIA)
 Values
   (4, 2, 'ADMINS', 'U', NULL);
COMMIT;