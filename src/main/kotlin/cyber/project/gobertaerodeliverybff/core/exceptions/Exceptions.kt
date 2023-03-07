package cyber.project.gobertaerodeliverybff.core.exceptions

data class ConflictException(val reason: String) : Exception(reason)

data class ApiException(val reason: String) : Exception(reason)



data class BadGatewayException(val reason: String) : Exception(reason)

data class ResourceNotFoundException(val reason: String) : Exception(reason)

data class ServiceUnavailableException(val reason: String) : Exception(reason)
