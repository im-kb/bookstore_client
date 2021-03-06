package com.example.ksiegarnia_klient.api_data_structures

import java.io.Serializable

class MyBooks : Serializable {
    var idKsiazki: String? = null
    var tytul: String? = null
    var autor: String? = null
    var imieAutora: String? = null
    var nazwiskoAutora: String? = null
    var wydawnictwo: String? = null
    var temat: String? = null
    var jezykKsiazki: String? = null
    var rokWydania: String? = null
    var dostepnosc: String? = null
    var opis: String? = null


    constructor(
        tytul: String?,
        temat: String?,
        imieAutora: String?,
        nazwiskoAutora: String?,
        wydawnictwo: String?,
        jezykKsiazki: String?,
        rokWydania: String?,
        dostepnosc: String?,
        opis: String?
    ) {
        this.tytul = tytul
        this.temat = temat
        this.imieAutora=imieAutora
        this.nazwiskoAutora=nazwiskoAutora
        this.wydawnictwo = wydawnictwo
        this.jezykKsiazki = jezykKsiazki
        this.rokWydania = rokWydania
        this.dostepnosc = dostepnosc
        this.opis = opis
    }
}