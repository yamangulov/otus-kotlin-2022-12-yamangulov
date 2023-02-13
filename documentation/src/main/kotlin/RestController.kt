package ru.otus.otuskotlin.chapi.documentation

class RestController {
    fun getRowResponse(sqlQuery: String): String {
        TODO("Not yet implemented")
    }

    public fun getRowResponse(tableName: String, filter: Filter, offset: Int): String {
        val sqlQuery = createSqlQuery(tableName, filter, offset)
        return getRowResponse(sqlQuery)
    }

    private fun createSqlQuery(tableName: String, filter: Filter, offset: Int): String {
        TODO("Not yet implemented")
    }

}

class Filter {
    // Not yet implemented
}
