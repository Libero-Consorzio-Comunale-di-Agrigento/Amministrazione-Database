CREATE OR REPLACE package body UTENTI_SALT_PKG is
/******************************************************************************
 NOME: UTENTI_SALT_PKG

 DESCRIZIONE: Funzione per la determinazione del url di accesso alla documentazione condivisa
 ANNOTAZIONI:
 REVISIONI:

 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 00   03/03/2020 SN     Creazione.
 01   22/09/2020 Neg    Introduzione get_algoritmo_usato

 *****************************************************************************/
--genera una stringa di 128bit(32 caratteri) utilizzata come SALT. In futuro si potrà aumentare la lunghezza del salt modificando l'algoritmo di HASH utilizzato
function generate_salt
return varchar2 is
BEGIN
    return DBMS_RANDOM.STRING('p',32);
END generate_salt;
-----------------------------------------------------------------------------------------------------------------------------
PROCEDURE SISTEMA_PASSWORD (p_utente varchar2, p_password IN OUT varchar2, p_algoritmo IN OUT varchar2)
IS
         -- determino il metodo x criptare
         d_salt  utenti_salt.salt%TYPE;
         begin
         p_algoritmo := global_utility.get_crypt_algorithm;
          if p_algoritmo <> 'STANDARD' then
         -- calcolo salt
            d_salt := UTENTI_SALT_PKG.generate_salt;
            --aggiungo alla password
            p_password := d_salt || p_password;
          end if;
            if utenti_salt_tpk.exists_id(p_utente) = 1 then
               utenti_salt_tpk.upd(
                      p_check_OLD  => 0
                    , p_NEW_utente  => p_utente
                    , p_NEW_salt => d_salt
                    , p_NEW_algoritmo => p_algoritmo);
            else
               utenti_salt_tpk.ins(
                      p_utente  => p_utente
                    , p_salt => d_salt
                    , p_algoritmo => p_algoritmo);
            end if;

end;

function get_algoritmo_usato
(
  p_utente  in UTENTI_SALT.utente%type
) return UTENTI_SALT.algoritmo%type is /* SLAVE_COPY */
/******************************************************************************
 NOME:        get_algoritmo
 DESCRIZIONE: Getter per attributo algoritmo di riga identificata dalla chiave.
 PARAMETRI:   Attributi chiave.
 RITORNA:     UTENTI_SALT.algoritmo%type.
 NOTE:        La riga identificata deve essere presente.
 REVISIONI:

 Rev. Data       Autore Descrizione
 ---- ---------- ------ ------------------------------------------------------
 01   22/09/2020 Neg    Introduzione get_algoritmo_usato
******************************************************************************/
   d_result UTENTI_SALT.algoritmo%type;
begin
   begin
   select algoritmo
   into   d_result
   from   UTENTI_SALT
   where
   utente = p_utente
   ;
   exception
   when no_data_found then
     d_result := 'STANDARD';
    end;

   return  d_result;
end get_algoritmo_usato;

end;
/

