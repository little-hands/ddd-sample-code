package com.example.demo.domain.shared

import org.springframework.stereotype.Component

/**
 * エンティティのメソッド内でドメインイベントを生成する場合、
 * イベントを生成することを呼び元に明示するために引数に渡すオブジェクト。
 * 後続処理では使用しないが、型の制約を効かせるためだけに使用する。
 *
 * これがないと呼び元で挙動が想像できない副作用となるため、
 * イベントが生成されることだけは呼び元で把握できるようにすることを目指す。
 * エンティティ外での生成を強制して、エンティティのメソッドに必ず渡させるようにするために、
 * DomainEventSeedImplをprivateにしてDomainEventSeedFactoryをDIしないと生成できないようにする
 */
interface DomainEventSeed

private class DomainEventSeedImpl : DomainEventSeed

@Component
class DomainEventSeedFactory {
    fun createSeed(): DomainEventSeed = DomainEventSeedImpl()
}
