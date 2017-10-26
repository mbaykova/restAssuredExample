import io.restassured.response.Response;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;
import util.RequestHelper;
import util.SoapMessageBuilder;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static util.RequestHelper.checkResponse;
import static util.RequestHelper.sendPOST;

/**
 * Created by Maria on 26.10.2017.
 */
public class ExampleRestTest {

		@Test
		@Title("Пример GET - запроса")
		public void testGETWithAllureReport(){
				Response response = RequestHelper.sendGet("https://config.tinkoff.ru/resources?name=products");
				checkResponse(response, "result.value.name","Оформить карту", "Открыть депозит", "Оформить кредит", "Оформить счет");
		}

		@Test
		@Title("Пример SOAP - запроса")
		public void testSOAPWithAllureReport(){
				String url = "http://86.57.245.235/TimeTable/Service.asmx";
				HashMap<String, String> formParams = new HashMap<String, String>();
				formParams.put("xpath:/GetTimeTable/Airport", "DME");
				formParams.put("xpath:/GetTimeTable/Type", "Arrival");
				formParams.put("xpath:/GetTimeTable/ViewDate", "2017-10-26");
				String soapMessage = new SoapMessageBuilder("GetTimeTable", url + "?WSDL").createSoapMessage(formParams);
			  System.out.println(soapMessage);
			  Response response = sendPOST(url, soapMessage, "text/xml");

		}


			@Test
			public void testGet(){
				Response response = given()
						.param("name", "products")
						.when().get("https://config.tinkoff.ru/resources");
				response.then()
						.statusCode(200).body("result.value.name", hasItems("Оформить карту", "Открыть депозит", "Оформить кредит", ""));
			}
}
