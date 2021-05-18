# jasminpuuntiheys : Ohjelmointi 2 -kurssin harjoitustyö

Ohjelmisto on katsottavissa http://softwareservice.fi:8080/jasminpuuntiheys/puun-tiheys.
Ohjelmiston verkkokäyttöliittymää ei ole mobiilioptimoitu.

Sivustoon liittyvän tietokannan polku on /home/jasmin/puuntiheys.sqlite, se täytyy vaihtaa jos haluaa että projektitiedosto toimii.
Polku löytyy src/database/JDBCPuuntiheysDao.java classista kolmesta eri kohdasta.

Ohjelmointi 2 -kurssin harjoitustyön aiheena oli luoda puuntiheyslaskuri käyttäen Javaa ja servletejä.
Ohjelmiston tuli myös sisältää autentikaatio-ominaisuus (kirjautumisikkuna) "edistyneelle puolelle" pääsyä varten.
"Edistyneellä puolella" puuntiheyslaskelmat päivittyvät tietokantaan, ja käyttäjä voi myös poistaa tietokannasta rivejä.
