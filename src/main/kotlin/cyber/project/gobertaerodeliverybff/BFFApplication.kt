package cyber.project.gobertaerodeliverybff

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class BFFApplication {
    @Bean
    fun corsConfigurer(@Value("\${cors.config.allowed-origins}") corsConfigAllowed: String?): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {

                registry
                    .addMapping("/**")
                    .allowedOrigins(corsConfigAllowed)
            }
        }
    }
}


fun main(args: Array<String>) {
    runApplication<BFFApplication>(*args)
}