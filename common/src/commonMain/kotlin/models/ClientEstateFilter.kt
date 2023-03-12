package models

data class ClientEstateFilter(
    var searchString: String = "",
    var ownerId: ClientUserId = ClientUserId.NONE,
)
