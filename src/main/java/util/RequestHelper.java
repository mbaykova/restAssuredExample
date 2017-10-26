package util;

import io.restassured.response.Response;
import ru.yandex.qatools.allure.annotations.Step;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static util.AllureHelper.*;


/**
 * Created by Maria on 26.10.2017.
 */
public class RequestHelper {

			@Step("отправлен Get запрос {0}")
			public static Response sendGet(String url){
					logRequest("request:", url);
					Response response = get(url);
					logResponse("response:", response.getBody().prettyPrint());
					return response;
			}


			@Step("отправлен POST запрос {0}")
			public static Response sendPOST(String url, String body, String contentType){
				logRequest("request body:", body);
				Response response = given().contentType(contentType).body(body).post(url);
				logResponse("response:", response.getBody().prettyPrint());
				return response;
			}

			@Step("параметр {1} в ответе равен значению {2}")
			public static void checkResponse(Response response, String param, String... value){
				response.then()
						.body(param, hasItems(value));
			}
}
