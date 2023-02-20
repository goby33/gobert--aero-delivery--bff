package cyber.project.gobertaerodeliverybff.data.repositories.user

import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.Firestore
import cyber.project.gobertaerodeliverybff.data.converters.user.GetProfileConverter
import cyber.project.gobertaerodeliverybff.data.examples.user.aGetUserResponseBo
import cyber.project.gobertaerodeliverybff.data.examples.user.aUserResponseModel
import cyber.project.gobertaerodeliverybff.data.models.response.user.UserResponseModel
import cyber.project.gobertaerodeliverybff.data.repositories.UserRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import reactor.test.StepVerifier



@ExtendWith(MockKExtension::class)
class UserRepositoryImplTest {

    private lateinit var userRepositoryImpl: UserRepositoryImpl
    private val firestore: Firestore = mockk()
    private val collectionRef: CollectionReference = mockk()
    private val docRef: DocumentReference = mockk()
    private val docSnapshot: DocumentSnapshot = mockk()

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        userRepositoryImpl = UserRepositoryImpl(firestore, GetProfileConverter())
        every { firestore.collection(any()) } returns collectionRef
        every { collectionRef.document(any()) } returns docRef
        every { docRef.get().get() } returns docSnapshot
        every { docSnapshot.exists() } returns true // Ajout de la réponse pour la méthode exists()
        every { docSnapshot.toObject(UserResponseModel::class.java) } returns aUserResponseModel()
    }

    @Test
    fun getUser_returnsListOfGetUserResponseBo() {
        // GIVEN
        val userUid = "testUser"

        // WHEN
        val result = userRepositoryImpl.getUser(userUid)

        // THEN
        StepVerifier.create(result)
            .expectNext(listOf(aGetUserResponseBo()))
            .verifyComplete()
    }
}