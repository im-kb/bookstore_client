package com.example.ksiegarnia_klient.api_data_structures

import java.io.Serializable

class ClientData(
    var nazwisko: String?,
    var imie: String?,
    var kodPocztowy: String?,
    var miejscowosc: String?,
    var ulica: String?,
    var nrDomu: String?,
    var telefon: String?,
    var login: String?,
    var haslo: String?
) : Serializable {
    var idKlienta: String? = null
}