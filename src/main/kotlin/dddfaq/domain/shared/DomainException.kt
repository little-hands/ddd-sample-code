package dddfaq.domain.shared

class DomainException(
  val errorMsg: String,
) : RuntimeException(errorMsg)