package clovin

import grails.gorm.transactions.Transactional

@Transactional
class MeetingService {

    Set<Meeting> getUserMeetings(User user) {
        return Meeting.findAllByOwner(user)
    }
}
