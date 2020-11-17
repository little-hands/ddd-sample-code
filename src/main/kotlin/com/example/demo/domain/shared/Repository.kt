package com.example.demo.domain.shared

import org.springframework.context.ApplicationEventPublisher

abstract class Repository<T : AggregateRoot>(private val applicationEventPublisher: ApplicationEventPublisher) {
  fun insert(entity: T) {
    insertImpl(entity)
    publishDomainEvent(entity)
  }

  protected fun publishDomainEvent(entity: T) {
    entity.getDomainEvents()
        .forEach { applicationEventPublisher.publishEvent(it) }
    entity.clearDomainEvents()
  }

  protected abstract fun insertImpl(entity: T)
}

abstract class UpdatableRepository<T : AggregateRoot>(applicationEventPublisher: ApplicationEventPublisher)
  : Repository<T>(applicationEventPublisher) {
  fun update(entity: T) {
    updateImpl(entity)
    publishDomainEvent(entity)
  }

  protected abstract fun updateImpl(entity: T)
}