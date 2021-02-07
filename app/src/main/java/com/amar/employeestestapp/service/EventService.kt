package com.amar.employeestestapp.service

class EventService {

    companion object {

        fun sendEvent(eventType: EventType, eventValue: String) {
            //send an event to backend
        }

    }

}

enum class EventType {
    EMPLOYEE_SHOWED_IN_LIST, PROFILE_OPENED
}