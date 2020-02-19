package clovin

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def save(User user) {
        user.save(flush: true)
    }
}
