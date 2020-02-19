package clovin

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

class UserController {

    SpringSecurityService springSecurityService
    UserService userService

    @Secured('ROLE_ADMIN')
    def users() {
        [users: User.findAll(), user: springSecurityService.getCurrentUser()]
    }

    @Secured('ROLE_ADMIN')
    def unlockUser(User user) {
        user.accountLocked = false
        userService.save(user)
        redirect(action: 'users')
    }

    @Secured('ROLE_ADMIN')
    def lockUser(User user) {
        user.accountLocked = true
        userService.save(user)
        redirect(action: 'users')
    }
}
