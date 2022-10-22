package com.luckytree.greenworld_user_service.domain

import javax.persistence.*

@Entity
class Person(username: String, password: String, nickname: String?, isActivated: Boolean, authorities: Set<Authority>) : BaseTimeEntity() {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long? = null

    @Column(name = "username", length = 50, unique = true)
    var username: String? = username

    @Column(name = "password", length = 100)
    var password: String? = password

    @Column(name = "nickname", length = 50)
    var nickname: String? = nickname

    @Column(name = "activated")
    var isActivated: Boolean = isActivated

    @ManyToMany
    @JoinTable(
        name = "person_authority",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "authority_name", referencedColumnName = "authority_name")]
    )
    var authorities: Set<Authority>? = null
}