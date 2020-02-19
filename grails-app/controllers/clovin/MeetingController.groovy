package clovin

import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured

class MeetingController {

    final static int PAGE_SIZE = 3
    SpringSecurityService springSecurityService
    MeetingService meetingService

    @Secured("permitAll")
    def upcoming(int offset) {
        def query = Meeting.where {
            end >= new Date();
        }
        def resultPage = query.list(sort: "start", order: "asc", offset: offset, max: PAGE_SIZE)
        def totalMatches = query.list().size()
        def actualPageNumber = offset / PAGE_SIZE + 1
        def allAvailablePages = totalMatches % PAGE_SIZE != 0 ? 1..(totalMatches / PAGE_SIZE + 1) : 1..(totalMatches / PAGE_SIZE)
        render(view: "upcoming", model: [meetings: resultPage, actualPage: actualPageNumber, pages: allAvailablePages, pageSize: PAGE_SIZE, user: springSecurityService.getCurrentUser()])
    }

    @Secured('ROLE_USER')
    def addUserToMeeting() {
        Meeting meeting = Meeting.get(params.id)
        User user = springSecurityService.getCurrentUser()
        meeting.participants.add(user)
        meetingService.save(meeting)
        redirect(action: "upcoming", model: [user: springSecurityService.getCurrentUser()])
    }

    @Secured('ROLE_USER')
    def create() {
        [user: springSecurityService.getCurrentUser()]
    }

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
        if (!meetingService.isDateAvailable(meeting)) {
            render(view: 'create', model: [alreadyMeetings: true, user: user])
            return
        }
        meeting.owner = user
        meetingService.save(meeting)
        redirect(action: "upcoming", model: [user: user])
    }

    @Secured('ROLE_USER')
    def userMeetings() {
        User user = springSecurityService.getCurrentUser()
        def meetings = meetingService.getUserMeetings(user)
        render(view: 'userMeetings', model: [meetings: meetings, user: user])
    }

    @Secured('ROLE_USER')
    def edit(Meeting meeting) {
        render(view: 'edit', model: [meeting: meeting, user: springSecurityService.getCurrentUser()])
    }

    @Secured('ROLE_USER')
    def update(Meeting meeting) {
        User user = springSecurityService.getCurrentUser()
        if (meeting == null) {
            render status: HttpStatus.NOT_FOUND, model: [user: user]
            return
        }
        if (meeting.hasErrors()) {
            render(view: 'edit', model: [meeting: meeting.errors, user: user])
            return
        }
        if (!meetingService.isDateAvailable(meeting)) {
            render(view: 'create', model: [alreadyMeetings: true, user: user])
            return
        }
        meetingService.save(meeting)
        redirect(action: "upcoming", model: [user: user])
    }

    @Secured('ROLE_USER')
    def delete(Meeting meeting) {
        meetingService.delete(meeting)
        redirect(action: 'userMeetings')
    }
}