--liquibase formatted sql

--changeset mturra:201901301231_205 runOnChange:true stripComments:false endDelimiter:/


create or replace package AFC_BFILE is
/******************************************************************************
 Funzioni per la gestione dei Binary File del DataBase.
 REVISIONI.
 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 00   05/12/2005 MF     Prima emissione.
 01   04/07/2007 MM     Inserimento funzioni drop_directory, create_directory, next_directory e ClobtoBfile.
 02   14/07/2011 FT     Allineati i commenti col nuovo standard di plsqldoc.
******************************************************************************/
   -- Package revision value
   s_revisione constant VARCHAR2(30) := 'V1.02';
   /******************************************************************************
    Restituisce versione e revisione di distribuzione del package.
    %return varchar2: contiene versione e revisione.
    %note <UL>
          <LI> Primo numero: versione compatibilita del Package.</LI>
          <LI> Secondo numero: revisione del Package specification.</LI>
          <LI> Terzo numero: revisione del Package body.</LI>
          </UL>
   ******************************************************************************/
   function versione return VARCHAR2;
   /******************************************************************************
    Ottiene il nome del Path di File System sulla base del DirName indicato.
    %param p_dirName Nome logico attribuito alla Directory
   ******************************************************************************/
   function get_dirPath
   ( p_dirName IN varchar2
   ) RETURN varchar2;
   /******************************************************************************
    Ottiene il nome logico della directory sulla base del Path di File System.
    %param p_dirPath Nome fisico della Directory
   ******************************************************************************/
   function get_dirName
   ( p_dirPath IN varchar2
   , p_prefix  IN varchar2 default '') RETURN varchar2;
   /******************************************************************************
    Elimina l'alias di directory passato.
    %param p_alias_dir varchar2 alias della directory.
    %note utilizza transaction autonoma.
   ******************************************************************************/
   PROCEDURE drop_directory ( p_alias_dir varchar2);
   /******************************************************************************
    Crea un alias di directory per il percorso passato.
    %param p_alias_dir varchar2 alias della directory.
    %param p_path_dir varchar2 percorso corrispondente sul server.
    %param p_replace number se = 1 viene aggiunta l'opzione 'or replace' in creazione dell'alias, altrimenti viene fatta una create.
    %param p_test number se = 1 prova a creare un file xml di test nella directory, altrimenti non fa nulla.
    %return number: <UL>
                    <LI> 0 = OK
                    <LI> -1 = errore in creazione dell'alias
                    <LI> -2 = errore in creazione del file di test nella directory
                    <LI> -3 = errore in eliminazione del file di test dalla directory
                    </UL>
    %note Utilizza transaction autonoma.
   ******************************************************************************/
   FUNCTION create_directory ( p_alias_dir varchar2
                             , p_path_dir varchar2
                             , p_replace number default 1
                             , p_test number default 1) return number;
   /******************************************************************************
    Crea un alias di directory per il percorso passato.
    %param p_prefix varchar2 prefisso da anteporre al primo numero libero in creazione dell'alias della directory.
    %param p_path varchar2 percorso corrispondente sul server.
    %return varchar2: nome alias creato
   ******************************************************************************/
   FUNCTION next_directory ( p_prefix  varchar2
                           , p_path    varchar2) return varchar2;
   /******************************************************************************
    Scrive in un file sul file system del db il contenuto di un clob.
    %param p_clob clob contenuto del file
    %param p_alias_directory varchar2 alias della directory in cui salvare il file.
    %param p_filename varchar2 nome da dare al file
    %return bfile: puntatore al file creato.
   ******************************************************************************/
   FUNCTION ClobtoBfile ( p_clob clob
                        , p_alias_directory varchar2
                        , p_filename  varchar2) RETURN BFILE;
end AFC_BFILE;
/

