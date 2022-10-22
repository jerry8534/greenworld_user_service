package com.luckytree.greenworld_user_service.application.service

import com.luckytree.greenworld_user_service.adapter.outgoing.persistence.PersonRepository
import com.luckytree.greenworld_user_service.domain.Authority
import com.luckytree.greenworld_user_service.domain.Person
import com.luckytree.greenworld_user_service.domain.PersonDto
import com.luckytree.greenworld_user_service.domain.PersonDto.Companion.from
import com.luckytree.greenworld_user_service.exception.DuplicateMemberException
import com.luckytree.greenworld_user_service.exception.NotFoundMemberException
import com.luckytree.greenworld_user_service.util.SecurityUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun signup(personDto: PersonDto): PersonDto {
        if (personRepository.findOneWithAuthoritiesByUsername(personDto.username!!).orElse(null) != null) {
            throw DuplicateMemberException("already registered user")
        }

        val authority = Authority("ROLE_USER")

        val user = Person(
            username = personDto.username!!,
            password = passwordEncoder.encode(personDto.password),
            nickname = personDto.nickname,
            authorities = setOf(authority),
            isActivated = true
        )

        return from(personRepository.save(user))
    }

    @Transactional(readOnly = true)
    fun getUserWithAuthorities(username: String): PersonDto {
        return from(
            personRepository.findOneWithAuthoritiesByUsername(username)
                .orElse(null)
        )
    }

    @get:Transactional(readOnly = true)
    val myPersonWithAuthorities: PersonDto
    get() = from(
        SecurityUtil.currentUserName
            .flatMap {
                username: String -> personRepository.findOneWithAuthoritiesByUsername(username)
            }
            .orElseThrow {
                throw NotFoundMemberException("Member not found")
            }
    )
}