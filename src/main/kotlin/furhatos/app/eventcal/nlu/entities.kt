package furhatos.app.eventcal.nlu

import furhatos.nlu.EnumEntity
import furhatos.nlu.ListEntity
import furhatos.util.Language


class Event : EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf("breakfast", "brunch", "lunch", "dinner", "shopping", "movies", "theater", "drinks", "girls night out")
    }

}
class ListOfDates : ListEntity<Date>()

class Date : EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    }
}