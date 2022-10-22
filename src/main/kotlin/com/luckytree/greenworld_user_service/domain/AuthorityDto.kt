package com.luckytree.greenworld_user_service.domain

data class AuthorityDto(
    var authorityName: String? = null
) {
    companion object {
        fun from(authority: Authority): AuthorityDto {
            return authority.run {
                AuthorityDto(
                    authorityName = authorityName
                )
            }
        }
    }
}
