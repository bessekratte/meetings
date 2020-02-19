package clovin

import grails.gorm.transactions.Transactional

@Transactional
class MeetingService {

    Set<Meeting> getUserMeetings(User user) {
        return Meeting.where {
            end >= new Date();
        }.findAllByOwner(user)
    }

    boolean isDateAvailable(Meeting meeting) {
        return Meeting.where {
            (((start <= meeting.start) && (end >= meeting.start)) || ((start <= meeting.end) && (end >= meeting.end)) ||
                    ((start <= meeting.start) && (end >= meeting.end)) || ((start >= meeting.start) && (end <= meeting.end)))
        }.isEmpty()
//        .list().isEmpty()
    }

    def save(Meeting meeting) {
        meeting.save flush: true
    }

    def delete(Meeting meeting) {
        meeting.delete(flush: true)
    }

}
