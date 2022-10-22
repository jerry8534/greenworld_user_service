package com.luckytree.greenworld_user_service.adapter.incoming

import com.luckytree.greenworld_user_service.application.service.PersonService
import com.luckytree.greenworld_user_service.domain.PersonDto
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PersonController(
    private val userService: PersonService
) {
    @GetMapping("/hello")
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("hello")
    }

    @PostMapping("/test-redirect")
    @Throws(IOException::class)
    fun testRedirect(response: HttpServletResponse) {
        response.sendRedirect("/api/user")
    }

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid personDto: PersonDto): ResponseEntity<PersonDto> {
        return ResponseEntity.ok(userService.signup(personDto))
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    fun getMyUserInfo(request: HttpServletRequest): ResponseEntity<PersonDto> {
        return ResponseEntity.ok(userService.myPersonWithAuthorities)
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    fun getUserInfo(@PathVariable username: String): ResponseEntity<PersonDto> {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username))
    }
}