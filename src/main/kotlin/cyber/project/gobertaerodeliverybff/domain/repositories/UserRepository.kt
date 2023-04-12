package cyber.project.gobertaerodeliverybff.domain.repositories

import cyber.project.gobertaerodeliverybff.domain.bo.response.GetUserResponseBo
import reactor.core.publisher.Mono

interface UserRepository {
    fun getUser(userUid: String): Mono<List<GetUserResponseBo>>
}