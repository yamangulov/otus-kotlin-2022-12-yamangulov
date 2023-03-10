package models

@JvmInline
value class ClientUserId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ClientUserId("")
    }
}
