package furhatos.app.eventcal.flow

import furhatos.app.eventcal.nlu.RequestHowToIntent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val Greeting: State = state(Parent) {
    onEntry {
        furhat.say("Hey there!")
        furhat.ask("Do you want to add an event to your calendar?")
    }

    onReentry {
        furhat.ask("Do you want to add an event to your calendar?")
    }

    onResponse<Yes> {
        furhat.say("Great!")
        goto(Start)

    }

    onResponse<No> {
        furhat.say("Ok.")
    }

    onResponse<RequestHowToIntent> {
        furhat.say("You can add an event to you calendar by telling me an event type, a date and a time for the event")
        reentry()
    }
}

