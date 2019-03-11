package service;

/**
 * Сервис приложения для отправки сообщений по JMS
 */
public interface AdminApiService {

    /**
     * Конвертация входной строки в сообщение и отправка
     *
     * @param text входная строка
     * @return результат работы метода
     */
    String serve(String text);
}
