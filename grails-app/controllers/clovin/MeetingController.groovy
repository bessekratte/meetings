package clovin

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

class MeetingController {

    final static int PAGE_SIZE = 3
    SpringSecurityService springSecurityService

    @Secured("permitAll")
    def upcoming(int offset) {
        def query = Meeting.where {
            end >= new Date();
        }
        def resultPage = query.list(sort: "start", order: "asc", offset: offset, max: PAGE_SIZE)
        def totalMatches = query.list().size()
        def actualPageNumber = offset / PAGE_SIZE + 1
        def allAvailablePages = totalMatches % PAGE_SIZE != 0 ? 1..(totalMatches / PAGE_SIZE + 1) : 1..(totalMatches / PAGE_SIZE)
        render(view: "upcoming", model: [meetings: resultPage, offset: offset ?: 0, actualPage: actualPageNumber, pages: allAvailablePages, querySize: totalMatches, pageSize: PAGE_SIZE, user: springSecurityService.getCurrentUser()])
    }

    @Secured('ROLE_USER')
    @Transactional
    def addUserToMeeting() {
        Meeting meeting = Meeting.get(params.id)
        User user = springSecurityService.getCurrentUser()
        meeting.participants.add(user)
        meeting.save flush: true
        render(view: "upcoming", model: [user: springSecurityService.getCurrentUser()])
        return
    }

    @Secured('ROLE_USER')
    def create() {
        [user: springSecurityService.getCurrentUser()]
    }

    @Transactional
    @Secured('ROLE_USER')
    def save(Meeting meeting) {
        User user = springSecurityService.getCurrentUser()
        if (meeting == null) {
            render status: HttpStatus.NOT_FOUND, model: [user: user]
            return
        }
        if (meeting.hasErrors()) {
            render(view: 'create', model: [meeting: meeting.errors, user: user])
            return
        }
        def query = Meeting.where {
            (((start <= meeting.start) && (end >= meeting.start)) || ((start <= meeting.end) && (end >= meeting.end)) ||
                    ((start <= meeting.start) && (end >= meeting.end)) || ((start >= meeting.start) && (end <= meeting.end)))
        }.list()
        if (!query.isEmpty()) {
            render(view: 'create', model: [alreadyMeetings: true, user: user])
            return
        }
//        meeting.owner = user
        meeting.save flush: true
        render(view: "upcoming", model: [user: user])
    }

/*    @Secured('ROLE_USER')
    def userMeetings() {
        User user = springSecurityService.getCurrentUser()
        def userMeetings = Meeting.findAllByOwner(user)
        render(view: 'userMeetings', model: [meetings: userMeetings])
    }

    @Secured('ROLE_USER')
    @Transactional
    def editMeeting(int meetingId) {
        def meeting = Meeting.get(meetingId)

    }*/
}
