package aseca.roobinhood.api.loaders

import aseca.roobinhood.api.domain.User
import aseca.roobinhood.api.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.core.Ordered
import org.springframework.stereotype.Component

@Profile("!mem")
@Component
class UsersLoader @Autowired constructor(private val userRepository: UserRepository) : CommandLineRunner, Ordered {
    override fun run(vararg args: String?) {
        val user = User("a", "a", "a", "a", "a", "a", 0.0, linkedSetOf())
        userRepository.save(user)
    }

    override fun getOrder(): Int {
        return 0
    }


}
