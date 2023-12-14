import org.springframework.security.core.AuthenticationException

class UserAlreadyExistAuthenticationException(msg: String = "User with that email already exists - debug only bad practice") : AuthenticationException(msg)