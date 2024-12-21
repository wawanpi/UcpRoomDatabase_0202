package com.example.ucp2.ui.navigation

import com.example.ucp2.ui.navigation.DestinasiDetail.KODE


interface AlamatNavigasi {
    val route: String
}

object HomeRoute : AlamatNavigasi {
    override val route = "home"
}

object HomeDsnRoute : AlamatNavigasi {
    override val route = "home_dsn"
}

object HomeMkRoute : AlamatNavigasi {
    override val route = "home_mk"
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
