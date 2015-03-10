package org.primefaces.material.component.input;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.primefaces.material.MaterialWidgetBuilder;
import org.primefaces.renderkit.CoreRenderer;
import org.primefaces.util.HTML;
import org.primefaces.util.WidgetBuilder;

public class InputRenderer extends CoreRenderer{
	
	public static final String RENDERER_TYPE = "org.primefaces.material.component.InputRenderer";
	
	@Override
	public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
		Input input = (Input) component;

		encodeMarkup(context, input);
		encodeScript(context, input);
	}

	private void encodeMarkup(FacesContext context, Input input) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		
		String inputId = input.getClientId() + "_input";
		
		String inputClass = "form-control";
		if(input.getPlaceholder() != null){
			inputClass += " floating-label";
		}
		
		writer.startElement("div", input);
			writer.writeAttribute("id", input.getClientId(), null);
				writer.startElement("input", null);
					writer.writeAttribute("id", inputId, null);
					writer.writeAttribute("class", inputClass, null);
					writer.writeAttribute("type", input.getType(), null);
					writer.writeAttribute("placeholder", input.getPlaceholder(), null);
					renderPassThruAttributes(context, input, HTML.INPUT_TEXT_ATTRS_WITHOUT_EVENTS);
					renderDomEvents(context, input, HTML.INPUT_TEXT_EVENTS);
				writer.endElement("input");
		writer.endElement("div");
	}
	
	private void encodeScript(FacesContext context, Input input) throws IOException {
		String clientId = input.getClientId();
		String widgetVar = input.resolveWidgetVar();
		 
		WidgetBuilder wb = MaterialWidgetBuilder.getInstance(context);
		 
		wb.initWithDomReady("Input", widgetVar, clientId);
		wb.attr("widgetName", widgetVar);
		encodeClientBehaviors(context, input);
		 
		wb.finish();
		 
	}


}
