import com.fasterxml.jackson.databind.ObjectMapper

val estateV1Mapper = ObjectMapper().apply {
//    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
//    setSerializationInclusion(JsonInclude.Include.NON_NULL)
}