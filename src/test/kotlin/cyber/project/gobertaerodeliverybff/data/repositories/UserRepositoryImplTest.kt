package cyber.project.gobertaerodeliverybff.data.repositories

import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.Firestore
import cyber.project.gobertaerodeliverybff.data.converters.user.GetProfileConverter
import cyber.project.gobertaerodeliverybff.data.examples.user.aGetUserResponseBo
import cyber.project.gobertaerodeliverybff.data.examples.user.aUserResponseModel
import cyber.project.gobertaerodeliverybff.data.models.response.user.UserResponseModel
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import reactor.core.publisher.Mono
import reactor.test.StepVerifier


class UserRepositoryImplTest {

    private val firestore: Firestore = mockk()
    private val collectionRef: CollectionReference = mockk()
    private val docRef: DocumentReference = mockk()
    private val docSnapshot: DocumentSnapshot = mockk()

    private val userRepositoryImpl = UserRepositoryImpl(firestore, GetProfileConverter())


    @Test
    fun getUser_returnsListOfGetUserResponseBo() {
        // GIVEN
        every { firestore.collection(any()) } returns collectionRef
        every { collectionRef.document(any()) } returns docRef
        every { docRef.get().get() } returns docSnapshot
        every { docSnapshot.exists() } returns true // Ajout de la réponse pour la méthode exists()
        every { docSnapshot.toObject(UserResponseModel::class.java) } returns aUserResponseModel()
        val userUid = "testUser"

        // WHEN
        val result = userRepositoryImpl.getUser(userUid)

        // THEN
        StepVerifier.create(result)
            .expectNext(listOf(aGetUserResponseBo()))
            .verifyComplete()
    }

    @Test
    fun getUser_returnsEmptyList() {
        // GIVEN
        every { firestore.collection(any()) } returns collectionRef
        every { collectionRef.document(any()) } returns docRef
        every { docRef.get().get() } returns docSnapshot
        every { docSnapshot.exists() } returns false // Ajout de la réponse pour la méthode exists()
        val userUid = "testUser"

        // WHEN
        val result = userRepositoryImpl.getUser(userUid)

        // THEN
        StepVerifier.create(result)
            .expectNext(emptyList())
            .verifyComplete()
    }

    @Test
    fun getUser_returnsError() {
        // GIVEN
        every { firestore.collection(any()) } returns collectionRef
        every { collectionRef.document(any()) } returns docRef
        every { docRef.get().get() } returns docSnapshot
        every { docSnapshot.exists() } returns true // Ajout de la réponse pour la méthode exists()
        every { docSnapshot.toObject(UserResponseModel::class.java) } returns null
        val userUid = "testUser"

        // WHEN
        val result = userRepositoryImpl.getUser(userUid)

        // THEN
        StepVerifier.create(result)
            .expectError()
            .verify()
    }

}