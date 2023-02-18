package cyber.project.gobertaerodeliverybff.presentation.controllers

import cyber.project.gobertaerodeliverybff.presentation.controllers.contracts.UserControllerContract
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController() : UserControllerContract {

    override fun getUsers(): Mono<List<Int>> {
        return Mono.just(listOf(1, 2, 3))
    }
}