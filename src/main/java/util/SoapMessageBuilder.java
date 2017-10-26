package util;

import com.predic8.wsdl.Definitions;
import com.predic8.wsdl.WSDLParser;
import com.predic8.wstool.creator.RequestCreator;
import com.predic8.wstool.creator.SOARequestCreator;
import groovy.xml.MarkupBuilder;

import java.io.StringWriter;
import java.util.HashMap;

/**
 * Created by Maria on 26.10.2017.
 */
public class SoapMessageBuilder {

			WSDLParser parser = new WSDLParser();
			StringWriter writer = new StringWriter();
			Definitions wsdl;
			String portType;
			String operationName;
			String bindingName;

			public SoapMessageBuilder(String operationName, String wsdlUrl){
				wsdl = parser.parse(wsdlUrl);
				this.portType = wsdl.getPortTypes().get(0).getName();
				this.bindingName = wsdl.getBindings().get(0).getName();
				this.operationName = operationName;
			}

			public String createSoapMessage(HashMap<String, String> formParams){
				SOARequestCreator creator = new SOARequestCreator(wsdl, new RequestCreator(), new MarkupBuilder(writer));
				creator.setFormParams(formParams);
				creator.createRequest(portType, operationName, bindingName);
				return writer.toString();
			}
}
