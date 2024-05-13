package dddfaq.domain.shared

class DomainException(
    private val errorMsg: String,
) : RuntimeException(errorMsg)

class EntityNotFoundException(
    private val errorMsg: String,
) : RuntimeException(errorMsg)

