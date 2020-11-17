package com.example.demo.domain.shared

/**
 * 集約ルートの基底クラス
 */
abstract class AggregateRoot {
    private val domainEvents: MutableList<DomainEvent> = mutableListOf()

    protected fun addDomainEvent(domainEvent: DomainEvent) {
        this.domainEvents.add(domainEvent)
    }

    fun getDomainEvents(): List<DomainEvent> {
        return this.domainEvents.toList()
    }

    fun clearDomainEvents() {
        this.domainEvents.clear()
    }
}
