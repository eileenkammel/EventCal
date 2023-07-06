package furhatos.app.eventcal

import furhatos.app.eventcal.nlu.AddEventIntent
import furhatos.flow.kotlin.NullSafeUserDataDelegate
import furhatos.records.User

// Associate an order to a user
val User.event by NullSafeUserDataDelegate {
    AddEventIntent()
}