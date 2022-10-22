package com.luckytree.greenworld_user_service.application.port.incoming

interface UserUseCase {
    fun signUp()
    fun getUserWithAuthorities()
    fun myUserWithAuthorities()
}