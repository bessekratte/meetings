package clovin

class Meeting {


    String subject
    Date start
    Date end
    Date registerTime = new Date()

//    static hasOne = [owner: User]
    static hasMany = [participants: User]

    static constraints = {
        subject(nullable: false)
        start(nullable: false, min: new Date())
        end(nullable: false, validator: { val, obj ->
            if (!val?.after(obj.start))
                return 'meeting.start.after.end'
        })
    }
}