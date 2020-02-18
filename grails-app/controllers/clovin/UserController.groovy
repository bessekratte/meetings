package clovin

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

class UserController {

    SpringSecurityService springSecurityService

    @Secured('ROLE_ADMIN')
    def lockedUsers() {
        [users: User.findAllByAccountLocked(true), user: springSecurityService.getCurrentUser()]
    }

    @Transactional
    @Secured('ROLE_ADMIN')
    def unlockUser(User user) {
        user.accountLocked = false
        user.save flush: true
        redirect(action: 'lockedUsers')
    }
}
