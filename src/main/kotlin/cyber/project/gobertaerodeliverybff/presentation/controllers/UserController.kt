package cyber.project.gobertaerodeliverybff.presentation.controllers

import cyber.project.gobertaerodeliverybff.domain.bo.response.GetUserResponseBo
import cyber.project.gobertaerodeliverybff.domain.repositories.UserRepository
import cyber.project.gobertaerodeliverybff.presentation.controllers.contracts.UserControllerContract
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class UserController(
    val userRepository: UserRepository
) : UserControllerContract {

    override fun getUsers(
        userUid: String
    ): Mono<List<GetUserResponseBo>> {
        return userRepository.getUser(userUid)
    }
}