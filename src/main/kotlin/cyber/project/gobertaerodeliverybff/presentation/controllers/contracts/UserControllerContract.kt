package cyber.project.gobertaerodeliverybff.presentation.controllers.contracts

import cyber.project.gobertaerodeliverybff.presentation.controllers.annotations.ApiResponseError400
import cyber.project.gobertaerodeliverybff.presentation.controllers.annotations.ApiResponseError500
import cyber.project.gobertaerodeliverybff.presentation.controllers.annotations.ApiResponseError503
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import reactor.core.publisher.Mono

@Tag(name = "BFF-01 : User")
interface UserControllerContract {

    @Operation(summary = "get users", operationId = "BFF-01-01")
    @ApiResponse(
        responseCode = "202",
        description = "Accepted",
        content = []
    )
    @ApiResponseError400
    @ApiResponseError500
    @ApiResponseError503
    @PostMapping("/users")
    fun getUsers(
    ): Mono<List<Int>>
}