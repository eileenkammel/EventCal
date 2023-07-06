package furhatos.app.eventcal.nlu

import furhatos.nlu.TextGenerator
import furhatos.util.Language
import furhatos.nlu.*
import furhatos.nlu.common.*
import furhatos.nlu.common.Number

open class AddEventIntent : Intent(), TextGenerator {
    var eventType: Event? = null
    var eventDate: Date? = null
    var eventTime: Time? = null
    var eventReminder: Boolean? = null
    var reminderTime: Number? = null

    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "Add @eventType on @eventDate at @eventTime to my calendar.",
            "@eventType on @eventDate at @time",
            "@eventType on @eventDate",
            "@eventType", "@eventDate", "@time"
        )
    }

    override fun toText(lang: Language): String {
        return generate(lang, "[$eventType] [on $eventDate] [$eventTime]")
    }

    override fun toString(): String {
        return toText()
    }
}


class TellDateIntent : Intent() {
    var eventDate: Date? = null

    override fun getExamples(lang: Language): List<String> {
        return listOf("@eventDate", "on @eventDate")
    }
}

class TellTimeIntent(var time: Time? = null) : Intent() {

    override fun getExamples(lang: Language): List<String> {
        return listOf("@time", "at @time")
    }
}

class TellEventTypeIntent : Intent() {
    var eventType: Event? = null

    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "@eventType"
        )
    }
}

class TellReminderTimeIntent (var number: Number? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "@number",
            "@number",
            "@number hours"
        )
    }
}

class ChangeTypeIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("type",
            "the type",
            "the type of the event",
            "the event type",
            "event type"
        )
    }
}

class ChangeDateIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("date",
            "the date",
            "the date of the event",
            "the event date",
            "event date"
        )
    }
}

class ChangeTimeIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("time",
            "the time",
            "the time of the event",
            "the event time",
            "event time"
        )
    }
}

class RequestEventTypeIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "what options are there",
            "what are the options",
            "what can I choose from",
            "what do you have",
            "what events are there",
            "what are the available events",
            "what event types are there"
        )
    }
}

class RequestDateIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "what kind of dates are there"
        )
    }
}

class RequestTimeIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "what times can you handle",
            "what times can i give you"
        )
    }
}

class RequestHowToIntent : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
            "what instructions can i give you",
            "how does it work",
            "what can i say to you",
            "how do i do that"
        )
    }
}
