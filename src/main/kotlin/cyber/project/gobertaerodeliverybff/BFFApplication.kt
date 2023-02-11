package cyber.project.gobertaerodeliverybff

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.DocumentReference
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.io.FileInputStream


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

    @Bean
    fun objectFirestore(): Firestore {
        val serviceAccount = FileInputStream("src/main/resources/aero-delivery-firebase-adminsdk-wjbaz-3e45620c92.json")

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount)) // The database URL depends on the location of the database
            .setDatabaseUrl("https://aero-delivery.firebaseio.com")
            .build()

        FirebaseApp.initializeApp(options)
        return FirestoreClient.getFirestore()
    }
}


fun main(args: Array<String>) {
    runApplication<BFFApplication>(*args)
}