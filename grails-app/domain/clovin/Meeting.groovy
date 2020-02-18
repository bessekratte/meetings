package clovin

class Meeting {

    String subject
    Date start
    Date end
    Date registerTime = new Date()
    static belongsTo = [owner: User] // ta jest git
    static hasMany = [participants: User]
//    static mappedBy = [participants: "meeting"]


    static constraints = {
        owner(nullable: true)
        subject(nullable: false)
        start(nullable: false, min: new Date())
        end(nullable: false, validator: { val, obj ->
            if (!val?.after(obj.start))
                return 'meeting.start.after.end'
        })
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "owner=" + owner +
                ", participants=" + participants +
                ", subject='" + subject + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}