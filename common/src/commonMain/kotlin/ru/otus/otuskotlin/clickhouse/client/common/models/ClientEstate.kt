package ru.otus.otuskotlin.clickhouse.client.common.models

data class ClientEstate(
    var id: ClientEstateId = ClientEstateId.NONE,
    var price: Int = 0,
    var date: String = "",
    var postcode1: String = "",
    var postcode2: String = "",
    var type: ClientType = ClientType.NONE,
    var isnew: ClientIsNew = ClientIsNew.NONE,
    var duration: ClientDuration = ClientDuration.UNKNOWN,
    var addr1: String = "",
    var addr2: String = "",
    var street: String = "",
    var locality: String = "",
    var town: String = "",
    var district: String = "",
    var county: String = "",
    var category: ClientCategory = ClientCategory.A,
    var ownerId: ClientUserId = ClientUserId.NONE,
    val permissionsClient: MutableSet<ClientEstatePermissionClient> = mutableSetOf(),
    var offset: Int = 0,
    var pagesize: Int = 1,
    ) {
    fun isEmpty() = this == NONE

    companion object {
        val NONE = ClientEstate()
    }

}
