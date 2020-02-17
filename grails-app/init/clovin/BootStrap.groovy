package clovin

import java.time.LocalDateTime
import java.time.ZoneId

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

        def testUser = new User(username: 'admin', password: 'admin').save()
        UserRole.create testUser, userRole

        UserRole.withTransaction { status ->
            UserRole.withSession {
                it.flush()
                it.clear()
            }
        }

        Meeting meeting1 = new Meeting(subject: "Animals",
                start: Date.from(LocalDateTime.of(2020, 3, 3, 14, 0).atZone(ZoneId.systemDefault()).toInstant()),
                end: Date.from(LocalDateTime.of(2020, 3, 3, 15, 0).atZone(ZoneId.systemDefault()).toInstant())).save()
        Meeting meeting2 = new Meeting(subject: "QWERTY",
                start: Date.from(LocalDateTime.of(2020, 3, 3, 16, 0).atZone(ZoneId.systemDefault()).toInstant()),
                end: Date.from(LocalDateTime.of(2020, 3, 3, 17, 0).atZone(ZoneId.systemDefault()).toInstant())).save()
        Meeting meeting3 = new Meeting(subject: "ASDFG",
                start: Date.from(LocalDateTime.of(2020, 3, 3, 18, 0).atZone(ZoneId.systemDefault()).toInstant()),
                end: Date.from(LocalDateTime.of(2020, 3, 3, 19, 0).atZone(ZoneId.systemDefault()).toInstant())).save()
        Meeting meeting4 = new Meeting(subject: "ZXCVBNM",
                start: Date.from(LocalDateTime.of(2020, 3, 3, 20, 0).atZone(ZoneId.systemDefault()).toInstant()),
                end: Date.from(LocalDateTime.of(2020, 3, 3, 21, 0).atZone(ZoneId.systemDefault()).toInstant())).save()
        Meeting meeting5 = new Meeting(subject: "123456",
                start: Date.from(LocalDateTime.of(2020, 3, 3, 22, 0).atZone(ZoneId.systemDefault()).toInstant()),
                end: Date.from(LocalDateTime.of(2020, 3, 3, 23, 0).atZone(ZoneId.systemDefault()).toInstant())).save()

    }
    def destroy = {
    }
}
