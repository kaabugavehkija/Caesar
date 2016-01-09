# Caesar
Aine i200 java projekt M&M's šifreerimine.
Autorid Mikk Erlenheim ja Mario Mustasaar.
Projekti ideeks oli luua lahendus, mis šifreeriks ja dešifreeriks etteantud teksti Caesari šifri abil.

Lähemalt tehtud lahendusest:
Sifreerimisel on võimalik valida drop-down listist nelja tähestiku vahel (eesti, inglise, araabia tähestiku ning kirillitsa vahel). Eraldi on võimalik ka käsitsi sisestada omale meelepärane tähestik, mida soovitakse šifreerimisel või dešifreerimisel kasutada. Lisaks tähestikule on võimalik drop-down listist valida meelepärane nihe (ühest kolmeteistkümneni), mida soovitakse kasutada.

"Plain text" lahtrisse tuleb sisestada tekst, mida soovitakse šifreerida või dešifreerida. Toimingu tegemiseks tuleb vajutada "Ciphered/deciphered text" lahtri all olevatele nuppudele "Cipher" või "Decipher". Nupp "Clear result" tühjendab lahtri "Ciphered/deciphered text" sisu ning nupp "Clear plain text" tühjendab omakorda lahtri "Plain text" (sisestatud teksti) sisu.

Šifreeritud teksti on võimalik salvestada formaadis .txt nupuga "Save to file". Salvestatud teksti on võimalik hiljem uuesti nupuga "Choose file" sisse lugeda. Teades šifreerimisel kasutatud tähestikku ning nihet on võimalik teksti hiljem uuesti dešifreerida. See võimaldab kasutajatel jagada omavahel salajast informatsiooni (šifreeritud kujul teksti).

Šifri akna sulgemisel (x-i vajutamisel) küsitakse kasutajalt üle, kas soovitakse šifrit sulgeda.

Võimalikud edasiarendused:
*Kasutajakonto loomine (koostada kasutajate kohta andmebaas) ning lisada võimalus kasutajatel salvestada šifreeritud või dešifreeritud teksti andmebaasi ning hiljem uuesti kuvada. Andmebaasi lisamisel tuleks tagada ka kaitse algaja häkkeri vastu (kasutajanimi ja parool peavad olema piiratud pikkusega + sql injectioni vastu peab olema andmebaas kaitstud).
