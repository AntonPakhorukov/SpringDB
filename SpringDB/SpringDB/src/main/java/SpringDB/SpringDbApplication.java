package SpringDB;
/**
 * Разработайте тесты для службы аутентификации `AuthService`.
 * Этот сервис имеет методы `login(String username, String password)`,
 * `register(User user)` и `logout(Long userId)`.
 * Служба использует `UserRepository` для управления данными пользователя и `SessionRepository`
 * для управления сессионными данными.
 *
 * 1. Создайте mock-объекты для `UserRepository` и `SessionRepository`.
 * 2. Напишите тест, который проверяет, что при успешной регистрации метод `save` репозитория пользователя вызывается.
 * 3. Напишите тест, который проверяет, что при входе в систему для существующего пользователя создается новая сессия.
 * 4. Напишите тест, проверяющий корректное завершение сессии при выходе из системы.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDbApplication.class, args);
	}

}
