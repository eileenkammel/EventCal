package furhatos.app.eventcal.flow

import furhatos.app.eventcal.*
import furhatos.app.eventcal.nlu.*
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.flow.VirtualUsersManagement.Companion.addVirtualUser
import furhatos.nlu.common.No
import furhatos.nlu.common.Number
import furhatos.nlu.common.Time
import furhatos.nlu.common.Yes
import java.time.LocalTime


val Questions: State = state(Interaction) {
    onResponse<RequestEventTypeIntent> {
        furhat.say("You can choose from" + Event().optionsToText())
        reentry()
    }

    onResponse<RequestTimeIntent> {
        furhat.say("Just choose any time")
        reentry()
    }

    onResponse<RequestDateIntent> {
        furhat.say("You can choose from all seven week days")
        reentry()
    }
}

val Start : State = state(Questions) {
    onEntry {
        furhat.ask( "Please tell me which event to add to your calendar"
        )
    }
    onResponse<AddEventIntent>{
        users.current.event.adjoin(it.intent)
        goto(CheckEvent)
    }
}

val CheckEvent : State = state(Questions) {
    onEntry {
        val event = users.current.event
        when {
            event.eventDate == null -> goto(RequestDate)
            event.eventType == null -> goto(RequestType)
            event.eventTime == null -> goto(RequestTime)
            else -> {
                furhat.say("I added the following to your calendar: $event")
                goto(ConfirmEvent)
            }
        }
    }
}

val RequestDate : State = state(Questions) {
    onEntry {
        furhat.ask("On what day of the week the event will take place?")
    }
    onResponse<TellDateIntent> {
        furhat.say("Okay, ${it.intent.eventDate}")
        users.current.event.eventDate = it.intent.eventDate
        goto(CheckEvent)
    }
}


val RequestType : State = state(Questions) {
    onEntry {
        furhat.ask("What kind of event you want to add?")
    }
    onResponse<TellEventTypeIntent> {
        furhat.say("Okay, ${it.intent.eventType}")
        users.current.event.eventType = it.intent.eventType
        goto(CheckEvent)
    }
}

val RequestTime : State = state(Questions) {
    onEntry {
        furhat.ask("At what time will the event take place?")
    }
    onResponse<TellTimeIntent> {
        furhat.say("Okay, ${it.intent.time}")
        users.current.event.eventTime = it.intent.time
        goto(CheckEvent)
    }
}

val ConfirmEvent : State = state(Questions) {
    onEntry {
        furhat.ask("Does that sound good?")
    }
    onResponse<Yes> {
        furhat.say("I saved the event to your calendar")
        goto(AddReminder)
    }
    onResponse<No> {
        goto(MakeChanges)
    }
}

val MakeChanges : State = state(Questions) {
    onEntry {
        furhat.ask("What do you want to change?")
    }
    onResponse<ChangeDateIntent> {
        goto(RequestDate)
    }
    onResponse<ChangeTimeIntent> {
        goto(RequestTime)
    }
    onResponse<ChangeTypeIntent> {
        goto(RequestType)
    }
}

val AddReminder : State = state(Interaction) {
    onEntry {
        val answer = furhat.askYN("Do you want to add a reminder for that event?")
        users.current.event.eventReminder = answer
        if (answer) {goto(ChooseReminderTime)}
        goto(End)
    }
}

val ChooseReminderTime : State = state(Interaction) {
    onEntry {
        furhat.ask("How many hours before the event should I remind you?")
    }
    onResponse<TellReminderTimeIntent> {
        furhat.say("Okay, i will remind you ${it.intent.number} hours before. ")
        users.current.event.reminderTime = it.intent.number
        goto(End)
    }
}

val End = state {
    onEntry {
        furhat.ask("Do you want to add another event to your calendar?")
    }
    onResponse<Yes> {
        users.current.event.eventDate = null
        users.current.event.eventTime = null
        users.current.event.eventType = null
        users.current.event.eventReminder = null
        users.current.event.eventReminder = null
        goto(Start)
    }
    onResponse<No> {
        goto(Idle)
    }
}
