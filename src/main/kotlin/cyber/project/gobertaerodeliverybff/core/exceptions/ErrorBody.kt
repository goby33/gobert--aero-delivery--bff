package cyber.project.gobertaerodeliverybff.core.exceptions

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorBody(
    @JsonProperty("statusCode") val code: Int,
    @JsonProperty("error") val error: String?,
    @JsonProperty("message") val message: String?
)