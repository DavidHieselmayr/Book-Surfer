/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  david_hauser
 * Created: 27.04.2020
 */

/*
Author Daten einfügen
*/
INSERT INTO autor (vorname, nachname, gebdatum, autorid, biographie) VALUES ('Amalie Wilhelmine', 'Sieveking', '25.07.1794', next value for seq_autor, 'Amalie Wilhelmine 
Sieveking wurde am 25. Juli 1794 in Hamburg geboren und starb am 1. April 1859 ebendort. Sie war eine Philanthropin und Mitbegründerin der organisierten Diakonie in 
Deutschland. Mit dem von ihr gegründeten Weiblichen Verein für Armen- und Krankenpflege, ihren Initiativen zur Arbeitsbeschaffung und Berufsausbildung für Arme und Aktionen
 für den Bau von Wohnungen und Krankenhäusern gilt sie als eine Vorreiterin der modernen Sozialarbeit in Deutschland. Außerdem verfaßte sie Schriften zur Sozialarbeit 
sowie theologische Abhandlungen.');

INSERT INTO autor (vorname, nachname, gebdatum, autorid, biographie) VALUES ('Arthur', 'Schnitzler', '15.05.1862', next value for seq_autor, 'Geboren am 15.5.1862
in Wien; gestorben am 21.10.1931 in Wien. Sein Vater Johann Schnitzler, aus einer einfachen jüdischen Familie stammend, kam über Budapest nach Wien, heiratete in eine
prominente Familie ein und wurde ein angesehener Arzt als Leiter der Allgemeinen Poliklinik. Sein Sohn Arthur besuchte von 1871 bis 1879 das Akademische Gymnasium und 
studierte ebenfalls Medizin (Promotion 1885). Er arbeitete an der Zeitschrift "Internationale Klinische Rundschau" mit und interessierte sich schon früh für Psychologie. 
Als Sekundararzt bei dem Psychiater Theodor Meynert setzte er Hypnose und Suggestion experimentell ein.');

INSERT INTO autor (vorname, nachname, gebdatum, autorid, biographie) VALUES ('Henrik', 'Ibsen', '15.05.1862', next value for seq_autor, 'Geboren am 20.3.1828 in Skien. Sohn
 eines reichen Kaufmanns, der bei seinem Bankrott aus der Gesellschaft ausgestoßen wurde; diese bittere Erfahrung des 8jährigen wurde später für ihn zum Hauptthema vieler 
Dramen. Er beschloß, zum »Broterwerb« Apotheker zu werden, und schrieb 1848 - dem Revolutionsjahr - neben revolutionären Gedichten das Schauspiel »Catilina«, eine Abrechnung
 mit der Gesellschaft Roms. 1850 Stud. med. in Oslo. Starkes politisches Engagement. Freundschaft mit Björnson. 1851 künstlerischer Leiter des neuen Theaters in Bergen, 
in dem seine Stücke aufgeführt wurden, wodurch er praktische Erfahrungen in der Dramaturgie sammelte. 1852 Studienreise nach Dresden. 1857-1862 Direktor des »Norske 
Teatret«, Oslo, dessen Zusammenbruch ihn auch wirtschaftlich schwer belastete. 1858 Heirat mit Susannah Thoresen. 1864 erhielt er durch die Vermittlung Björnsons ein 
Stipendium für eine Studienreise ins Ausland und blieb bis 1891 in Rom, Dresden und München. Mit seinem Thesen- und Diskussionstheater wurde er besonders mit den Stücken 
aus dieser Periode zum Begründer einer modernen Schauspielkunst. Bei seinem Tode am 23.5.1906 in Oslo hielt man ihn für den größten »modernen« Dichter.');

INSERT INTO autor (vorname, nachname, gebdatum, autorid, biographie) VALUES ('Hermann', 'Essig', '15.05.1862', next value for seq_autor, 'Geboren am 28.8.1878 in 
Truchtelfingen/Schwäbische Alb, gestorben am 21.6.1918 in Berlin. Der Sohn eines protestantischen Pfarrers besuchte die Lateinschule in Weinsberg und das Gymnasium 
in Heilbronn; anschließend studierte er an der Technischen Hochschule in Stuttgart. 1902 zog er sich eine langwierige und lebensgefährliche Lungenkrankheit zu und mußte 
das Ingenieurstudium aufgeben. Nach der Genesung wandte er sich 1904 nach Berlin, wo ihm ein Studienkolleg Arbeit angeboten hatte. 1905 heiratete er seine Zimmerwirtin, 
die junge Witwe des sozialdemokratischen Reichstagsabgeordneten und Dramatikers Emil Rosenow. Seine eigenen dramatischen Arbeiten erhielten literarische Anerkennung, an 
den Bühnen jedoch fand er mit seinen exzentrischen Volksstücken (hauptsächlich wegen der Zensur) keine Aufnahme. 1913 und 1914 erhielt er den Kleist-Preis.');

/*
Genre Daten einfügen
*/
INSERT INTO genre (name, beschreibung, genreid) VALUES ('Krimi', 'Das Wort Krimi ist umgangssprachlich und steht abkürzend für „Kriminalfilm, Kriminalroman“, also 
ein Genre, das vor allem in den populären Medien bzw. in der Unterhaltungsindustrie verbreitet ist und einen aufklärerischen Spannungsbogen hat. Krimis werden häufig 
als Serien dargeboten und können unter anderem in Detektiv-, Gangster-, Polizei- und Spionagegeschichten unterteilt werden.', next value for seq_genre);

INSERT INTO genre (name, beschreibung, genreid) VALUES ('Abenteuer', 'Ein Abenteuerroman ist eine umfangreiche Literaturform, in deren Mittelpunkt die Darstellung 
von gefährlichen Ereignissen und Erlebnissen steht, die von den Helden der Dichtung bestanden werden müssen. Als solche können eine Hauptfigur, aber auch eine 
Gruppe von Figuren auftreten.', next value for seq_genre);

INSERT INTO genre (name, beschreibung, genreid) VALUES ('Komödie', 'Eine Komödie ist ein Drama mit oft erheiterndem Handlungsablauf, das für den bzw. die Helden 
glücklich endet. Die unterhaltsame Grundstimmung entsteht durch eine übertriebene Darstellung menschlicher Schwächen, die neben der Belustigung des Publikums auch 
kritische Zwecke haben kann.', next value for seq_genre);

INSERT INTO genre (name, beschreibung, genreid) VALUES ('Liebesroman', 'Unter einem Liebesroman versteht man einen Roman, dessen zentrales Thema die Liebe ist. 
Obwohl viele Liebesromane der Trivialliteratur zuzurechnen sind (siehe weiter unten), schließt der Begriff grundsätzlich auch Werke der Hochliteratur ein. Zu den 
historischen Vorläufern des modernen Liebesromans – sowohl in seiner trivialliterarischen als auch in seiner hochliterarischen Form – zählen der barocke Schäferroman, 
der galante Roman und der englische Sittenroman des 18. und 19. Jahrhunderts.', next value for seq_genre);

INSERT INTO genre (name, beschreibung, genreid) VALUES ('Fiktion', 'Fiktion bezeichnet die Schaffung einer eigenen Welt durch Literatur, Film, Malerei oder andere Formen
 der Darstellung sowie den Umgang mit einer solchen Welt. Bei der Fiktion handelt es sich um eine bedeutende Kulturtechnik, die in weiten Teilen der Kunst zum Einsatz 
kommt.', next value for seq_genre);

INSERT INTO genre (name, beschreibung, genreid) VALUES ('Schauspiel', 'Der Begriff Schauspiel im Theater wird entweder für ein überwiegend gesprochenes Drama verwendet 
oder für eine Sparte der Bühnenkünste, die von Schauspielern ausgeübt wird. Im klassischen Mehrspartentheater bezeichnet er die mehrheitlich gesprochenen Theateraufführungen
im Unterschied zu Musiktheater und Tanztheater.', next value for seq_genre);

INSERT INTO genre (name, beschreibung, genreid) VALUES ('Drama', 'Drama ist ein Oberbegriff für Texte mit verteilten Rollen. Die Dramatik ist neben der Epik und der Lyrik 
eine der drei grundlegenden literarischen Gattungen.', next value for seq_genre);

/*
Buch Daten einfügen
*/
INSERT INTO buch (titel, buchid, releasedatum, kapitelanzahl, preis) VALUES ('Der Schweinepriester', next value for seq_buch, '01.01.1912',4, 10.00);

INSERT INTO buch (titel, buchid, releasedatum, kapitelanzahl, preis) VALUES ('Gespenster', next value for seq_buch, '20.05.1882',3, 13.95);

INSERT INTO buch (titel, buchid, releasedatum, kapitelanzahl, preis) VALUES ('Komoedie der Liebe', next value for seq_buch, '24.11.1873',3, 19.99);

INSERT INTO buch (titel, buchid, releasedatum, kapitelanzahl, preis) VALUES ('Nora oder Ein Puppenheim', next value for seq_buch, '21.12.1879',3, 14.75);

INSERT INTO buch (titel, buchid, releasedatum, kapitelanzahl, preis) VALUES ('Vermächtnis für meine jungen Freundinnen', next value for seq_buch, '01.01.1865',1, 16.90);

INSERT INTO buch (titel, buchid, releasedatum, kapitelanzahl, preis) VALUES ('Zwischenspiel', next value for seq_buch, '12.10.1905',3, 17.80);

/*
Kapitel Daten einfügen
*/
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Inhalt', 1, next value for seq_kapitel, 1, 'Der Schweinepriester/inhalt.rtfd/TXT.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Erster Aufzug', 2, next value for seq_kapitel, 1, 'Der Schweinepriester/Erster_Aufzug.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Zweiter Aufzug', 3, next value for seq_kapitel, 1, 'Der Schweinepriester/Zweiter_Aufzug.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Dritter Aufzug', 4, next value for seq_kapitel, 1, 'Der Schweinepriester/Dritter_Aufzug.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Vierter Aufzug', 5, next value for seq_kapitel, 1, 'Der Schweinepriester/Vierter_Aufzug.txt');

INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Inhalt', 1, next value for seq_kapitel, 2, 'Gespenster/Inhalt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Erster Akt', 2, next value for seq_kapitel, 2, 'Gespenster/Erster_Akt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Zweiter Akt', 3, next value for seq_kapitel, 2, 'Gespenster/Zweiter_Akt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Dritter Akt', 4, next value for seq_kapitel, 2, 'Gespenster/Dritter_Akt.txt');

INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Inhalt', 1, next value for seq_kapitel, 3, 'Komoedie_der_Liebe/Inhalt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Erster Akt', 2, next value for seq_kapitel, 3, 'Komoedie_der_Liebe/Erster_Akt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Zweiter Akt', 3, next value for seq_kapitel, 3, 'Komoedie_der_Liebe/Zweiter_Akt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Dritter Akt', 4, next value for seq_kapitel, 3, 'Komoedie_der_Liebe/Dritter_Akt.txt');

INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Inhalt', 1, next value for seq_kapitel, 4, 'Nora oder Ein Puppenheim/Inhalt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Erster Akt', 2, next value for seq_kapitel, 4, 'Nora oder Ein Puppenheim/Erster_Akt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Zweiter Akt', 3, next value for seq_kapitel, 4, 'Nora oder Ein Puppenheim/Zweiter_Akt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Dritter Akt', 4, next value for seq_kapitel, 4, 'Nora oder Ein Puppenheim/Dritter_Akt.txt');

INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Inhalt', 1, next value for seq_kapitel, 5, 'Vermächtnis_für_meine_jungen_Freundinnen/Inhalt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Kapitel', 2, next value for seq_kapitel, 5, 'Vermächtnis_für_meine_jungen_Freundinnen/Kapitel.txt');

INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Inhalt', 1, next value for seq_kapitel, 6, 'Zwischenspiel/Inhalt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Erster Akt', 2, next value for seq_kapitel, 6, 'Zwischenspiel/Erster_Akt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Zweiter Akt', 3, next value for seq_kapitel, 6, 'Zwischenspiel/Zweiter_Akt.txt');
INSERT INTO kapitel (ueberschrift, nummer, kapitelid, buch_buchid, textdateiurl) VALUES ('Dritter Akt', 4, next value for seq_kapitel, 6, 'Zwischenspiel/Dritter_Akt.txt');
