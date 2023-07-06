package furhatos.app.eventcal

import furhatos.app.eventcal.flow.Init
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class EventcalSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
