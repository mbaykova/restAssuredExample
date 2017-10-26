package util;


import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.junit.AllureRunListener;

/**
 * Created by 777 on 08.05.2017.
 */
public class AllureHelper {


    @Attachment(value = "{0}", type = "text/xml")
    public static String logResponse(String attachName, String message) {
        return message;
    }

    @Attachment(value = "{0}", type = "text/xml")
    public static String logRequest(String attachName, String message) {
        return message;
    }

}
