package models

@JvmInline
value class ClientRequestId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ClientRequestId("")
    }
}
