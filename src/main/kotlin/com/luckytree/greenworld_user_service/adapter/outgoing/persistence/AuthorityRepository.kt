package com.luckytree.greenworld_user_service.adapter.outgoing.persistence

import com.luckytree.greenworld_user_service.domain.Authority
import org.springframework.data.jpa.repository.JpaRepository

interface AuthorityRepository: JpaRepository<Authority?, String?> {
}