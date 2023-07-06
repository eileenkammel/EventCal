package furhatos.app.eventcal.flow

import furhatos.flow.kotlin.*

val Idle: State = state {
    onEntry {
        furhat.attendNobody()
    }

    onUserEnter {
        furhat.attend(it)
        goto(Greeting)
    }

}

val Interaction : State = state {

    var nomatches = 0
    var silences = 0

    onResponse {
        nomatches++
        when (nomatches)  {
            1 -> furhat.ask("I didn't get that, sorry. Try again!")
            2 -> furhat.ask("I still didn't get that, sorry. Could you rephrase?")
            else -> {
                furhat.say("Still couldn't get that. Let's start over.")
                reentry()
            }
        }
    }

    onNoResponse {
        silences++
        when (silences)  {
            1 -> furhat.ask("I didn't hear anything")
            2 -> furhat.ask("I still didn't hear you. Could you speak up please?")
            else -> {
                furhat.say("Still didn't hear anything.  Let's start over")
                reentry()
            }
        }
    }

    onUserLeave(instant=true) {
        if (users.count < 1) {
            goto(Idle)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }
}