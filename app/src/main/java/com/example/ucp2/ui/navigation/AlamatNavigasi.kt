package com.example.ucp2.ui.navigation

import com.example.ucp2.ui.navigation.DestinasiDetail.KODE

interface AlamatNavigasi {
    val route: String
}
object DestinasiHome : AlamatNavigasi {
    override val route = "home"
}
object DestinasiDetail : AlamatNavigasi {
    override val route = "detail"
    const val KODE = "kode"
    val routeWithArgs = "$route/{$KODE}"
}
object DestinasiUpdate : AlamatNavigasi {
    override val route = "update"
    const val KODE = "kode"
    val routeWithArg = "$route/{$KODE}"
}