package com.luckytree.greenworld_user_service.adapter.incoming

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/page")
class WebController {

    @GetMapping("/home")
    fun goHome(): String {
        return "index"
    }

    @GetMapping("/login")
    fun goLogin(): String {
        return "login"
    }
}