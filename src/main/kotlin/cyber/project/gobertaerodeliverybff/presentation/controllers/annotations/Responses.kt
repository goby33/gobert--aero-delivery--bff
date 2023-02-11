package cyber.project.gobertaerodeliverybff.presentation.controllers.annotations

import cyber.project.gobertaerodeliverybff.core.exceptions.ErrorBody
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.core.annotation.AliasFor

/*
Theses pre-made error annotations exist to avoid code duplication in sonar process, and to reduce the quantity of code
 in contracts.
 */

@ApiResponse(
        responseCode = "400",
        description = "Invalid request",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorBody::class))]
)
annotation class ApiResponseError400


@ApiResponse(
        responseCode = "404",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorBody::class))]
)
annotation class ApiResponseError404(
        @get:AliasFor(annotation = ApiResponse::class, value = "description")
        val description: String = "Element not found"
)

@ApiResponse(
        responseCode = "500",
        description = "Unexpected error",
        content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorBody::class))]
)
annotation class ApiResponseError500

@ApiResponse(
    responseCode = "503",
    description = "Service unavailable",
    content = [Content(mediaType = "application/json", schema = Schema(implementation = ErrorBody::class))]
)
annotation class ApiResponseError503