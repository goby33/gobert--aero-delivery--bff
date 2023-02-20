package cyber.project.gobertaerodeliverybff.data.repositories

import com.google.cloud.firestore.Firestore
import cyber.project.gobertaerodeliverybff.data.converters.user.GetProfileConverter
import cyber.project.gobertaerodeliverybff.data.models.response.user.UserResponseModel
import cyber.project.gobertaerodeliverybff.domain.bo.response.GetUserResponseBo
import cyber.project.gobertaerodeliverybff.domain.repositories.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import reactor.kotlin.core.publisher.switchIfEmpty
import java.util.concurrent.CompletableFuture


@Service
class UserRepositoryImpl(
    private val objectFirestore: Firestore,
    private val getProfileConverter: GetProfileConverter
) : UserRepository {
    override fun getUser(userUid: String): Mono<List<GetUserResponseBo>> {
        val docRef = objectFirestore.collection("test").document(userUid)
        return Mono.fromFuture(CompletableFuture.supplyAsync {
            docRef.get().get()
        })
            .map { document ->
                if (document.exists()) {
                    val user = document.toObject(UserResponseModel::class.java)
                    listOf(getProfileConverter.fromUserResponseModeltoGetUserResponseBo(user!!))
                } else {
                    listOf()
                }
            }
            .switchIfEmpty {
                Mono.empty()
            }
            .subscribeOn(Schedulers.boundedElastic())
    }
}
