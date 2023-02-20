package cyber.project.gobertaerodeliverybff.data.converters.user

import cyber.project.gobertaerodeliverybff.data.models.response.user.UserResponseModel
import cyber.project.gobertaerodeliverybff.domain.bo.response.GetUserResponseBo
import org.springframework.stereotype.Service

@Service
class GetProfileConverter {

    fun fromUserResponseModeltoGetUserResponseBo(
        body: UserResponseModel
    ): GetUserResponseBo {
        return GetUserResponseBo(
            id = body.id!!,
        )
    }
}