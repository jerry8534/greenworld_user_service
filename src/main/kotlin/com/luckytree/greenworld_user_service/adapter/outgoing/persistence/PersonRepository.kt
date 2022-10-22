package com.luckytree.greenworld_user_service.adapter.outgoing.persistence

import com.luckytree.greenworld_user_service.domain.Person
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PersonRepository : JpaRepository<Person, Long?> {
    @EntityGraph(attributePaths = ["authorities"])
    fun findOneWithAuthoritiesByUsername(username: String): Optional<Person>
}