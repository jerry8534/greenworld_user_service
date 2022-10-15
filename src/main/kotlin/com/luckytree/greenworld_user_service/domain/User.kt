package com.luckytree.greenworld_user_service.domain

import javax.persistence.*

@Entity
class User(name: String, email: String, password: String) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column
    var name: String = name

    @Column
    var email: String = email

    @Column
    var password: String = password

}