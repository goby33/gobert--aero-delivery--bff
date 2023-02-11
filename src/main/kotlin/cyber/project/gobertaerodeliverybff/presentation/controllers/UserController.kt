package cyber.project.gobertaerodeliverybff.presentation.controllers

import cyber.project.gobertaerodeliverybff.presentation.controllers.contracts.UserControllerContract
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController() : UserControllerContract {

    override fun printSscc(
        sscc: String
    ): Mono<Int> {
        return Mono.just(10)
    }
}