package dddfaq.usecase.shared

class UseCaseException(
    val errorMsg: String,
) : RuntimeException(errorMsg)