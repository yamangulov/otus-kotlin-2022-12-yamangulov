package ru.otus.otuskotlin.clickhouse.client.repo.inmemory.model

import ru.otus.otuskotlin.clickhouse.client.common.models.*

data class ClientEstateEntity(
    val id: String? = null,
    val type: String? = null,
    val isnew: String? = null,
    val category: String? = null,
    val ownerId: String? = null,
) {
    constructor(model: ClientEstate) : this(
        id = model.id.asString().takeIf { it.isNotBlank() },
//        type = model.type.takeIf { it != ClientType.NONE }?.name,
        type = model.type.name,
//        isnew = model.isnew.takeIf { it != ClientIsNew.NONE }?.name,
        isnew = model.isnew.name,
//        category = model.category.takeIf { it != ClientCategory.A }?.name,
        category = model.category.name,
        ownerId = model.ownerId.asString().takeIf { it.isNotBlank() },
    )

    fun toInternal() = ClientEstate(
        id = id?.let { ClientEstateId(it) } ?: ClientEstateId.NONE,
        type = type?.let { ClientType.valueOf(it) } ?: ClientType.NONE,
        isnew = isnew?.let { ClientIsNew.valueOf(it) } ?: ClientIsNew.NONE,
        category = category?.let { ClientCategory.valueOf(it) } ?: ClientCategory.A,
        ownerId = ownerId?.let { ClientUserId(it) } ?: ClientUserId.NONE,
    )
}
