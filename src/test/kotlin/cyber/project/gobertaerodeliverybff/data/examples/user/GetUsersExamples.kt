package cyber.project.gobertaerodeliverybff.data.examples.user

import com.google.api.core.ApiFuture
import com.google.cloud.firestore.DocumentSnapshot
import cyber.project.gobertaerodeliverybff.data.models.response.user.UserResponseModel

fun aUserResponseModel() = UserResponseModel (
    id = "testUser",
)
