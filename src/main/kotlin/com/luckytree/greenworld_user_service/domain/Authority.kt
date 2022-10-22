package com.luckytree.greenworld_user_service.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Authority(authorityName: String) {

    @Id
    @Column(name = "Authority_name", length = 50)
    var authorityName: String? = null
}