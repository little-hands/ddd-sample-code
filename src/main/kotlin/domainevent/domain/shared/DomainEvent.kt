package domainevent.domain.shared

import org.springframework.context.ApplicationEvent

/**
 * エンティティの処理から、イベントを経由して後続の別の処理を実行するために作成するイベントです。
 *
 * ドメインイベントを使用する利点は
 * ・このイベントを経由することにより、イベント作成する側のエンティティからは後続の処理を知らなくて済む(依存しなくなる)
 * ・ユースケースの実装がシンプルになる
 * ・別の集約の作成などを確実に実行させることができるので、
 *   ユースケース層の実装漏れで集約間の整合性が壊れることを防げる
 */
abstract class DomainEvent(@Suppress("UNUSED_PARAMETER") seed: DomainEventSeed) : ApplicationEvent(DomainEventSource)

/**
 * ApplicationEventのSourceとして指定するためのオブジェクト。
 *
 * Springのアプリケーションイベントの使用上、イベント生成元をSourceとして渡す必要があるが、
 * 特にアプリケーション的に使用するわけではなく、
 * エンティティをセットすると実装が煩雑になるため、
 * 固定のオブジェクトを渡すことで代用している。
 */
private object DomainEventSource



