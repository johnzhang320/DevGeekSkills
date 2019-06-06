package com.websystique.springmvc.viewresolver;

import java.awt.Color;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.websystique.springmvc.model.Pizza;

public class PdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Pizza pizza = (Pizza) model.get("pizza");

		PdfPTable table = new PdfPTable(3);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
		table.getDefaultCell().setBackgroundColor(Color.lightGray);

		table.addCell("Name");
		table.addCell("Flavor");
		table.addCell("Toppings");

		table.addCell(pizza.getName());
		table.addCell(pizza.getFlavor());

		StringBuffer toppings = new StringBuffer("");
		for (String topping : pizza.getToppings()) {
			toppings.append(topping);
			toppings.append(" ");
		}
		table.addCell(toppings.toString());
		document.add(table);

	}

}
