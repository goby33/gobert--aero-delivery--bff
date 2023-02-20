package cyber.project.gobertaerodeliverybff.presentation.controllers.contracts

import cyber.project.gobertaerodeliverybff.domain.bo.response.GetUserResponseBo
import cyber.project.gobertaerodeliverybff.presentation.controllers.annotations.ApiResponseError400
import cyber.project.gobertaerodeliverybff.presentation.controllers.annotations.ApiResponseError500
import cyber.project.gobertaerodeliverybff.presentation.controllers.annotations.ApiResponseError503
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import reactor.core.publisher.Mono

@Tag(name = "BFF-01 : User")
interface UserControllerContract {

    @Operation(summary = "get users", operationId = "BFF-01-01")
    @ApiResponse(
        responseCode = "202",
        description = "Accepted",
        content = [
            Content(
                mediaType = "application/json",
                array = ArraySchema(
                    schema = Schema(implementation = GetUserResponseBo::class)
                )
            )
        ]
    )
    @ApiResponseError400
    @ApiResponseError500
    @ApiResponseError503
    @PostMapping("/users")
    fun getUsers(
        @RequestHeader("USER-UID") userUid: String,
    ): Mono<List<GetUserResponseBo>>
}