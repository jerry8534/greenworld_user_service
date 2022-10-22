package com.luckytree.greenworld_user_service.domain

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class PersonDto(
    @field:NotNull
    @field:Size(min = 3, max = 50)
    var username: String? = null,

    @field:NotNull
    @field:Size(min = 3, max = 100)
    var password: String? = null,

    @field:NotNull
    @field:Size(min = 3, max = 50)
    var nickname: String? = null,

    var authorityDtoSet: Set<AuthorityDto>? = null
) {
    companion object {
        fun from(person: Person): PersonDto {
            return person.run {
                PersonDto(
                    username = username,
                    nickname = nickname,
                    authorityDtoSet = person.authorities!!
                        .map { authority ->
                            AuthorityDto(authority.authorityName)
                        }
                        .toSet()
                )
            }
        }
    }
}
