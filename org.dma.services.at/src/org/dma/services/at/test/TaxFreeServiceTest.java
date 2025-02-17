/*******************************************************************************
 * Copyright 2008-2025 Marco Lopes (marcolopespt@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors
 * Marco Lopes (marcolopespt@gmail.com)
 *******************************************************************************/
package org.dma.services.at.test;

import java.math.BigDecimal;

import org.dma.java.util.RandomValue;
import org.dma.java.util.TimeDateUtils;
import org.dma.services.at.ServiceCertificates;
import org.dma.services.at.proxy.TaxFreeServiceHandler;

import pt.gov.portaldasfinancas.servicos.taxfree.BuyerType;
import pt.gov.portaldasfinancas.servicos.taxfree.CalculatedTaxType;
import pt.gov.portaldasfinancas.servicos.taxfree.CalculatedTaxesType;
import pt.gov.portaldasfinancas.servicos.taxfree.CompanyType;
import pt.gov.portaldasfinancas.servicos.taxfree.IdentityDocType;
import pt.gov.portaldasfinancas.servicos.taxfree.InvoiceIdentifiersType;
import pt.gov.portaldasfinancas.servicos.taxfree.InvoiceLineType;
import pt.gov.portaldasfinancas.servicos.taxfree.InvoiceLinesListType;
import pt.gov.portaldasfinancas.servicos.taxfree.InvoiceType;
import pt.gov.portaldasfinancas.servicos.taxfree.InvoicesListType;
import pt.gov.portaldasfinancas.servicos.taxfree.InvoicesType;
import pt.gov.portaldasfinancas.servicos.taxfree.RefundType;
import pt.gov.portaldasfinancas.servicos.taxfree.RequestHeaderType;
import pt.gov.portaldasfinancas.servicos.taxfree.TaxFreeCommType;
import pt.gov.portaldasfinancas.servicos.taxfree.TaxFreeSubmissionRequestType;
import pt.gov.portaldasfinancas.servicos.taxfree.TaxFreeSubmissionResponseType;

/**
 * Teste de eTaxFree
 */
public class TaxFreeServiceTest extends TaxFreeServiceHandler {

	public static final Integer RequesterTaxID = 599999993;

	public static final String InvoiceDate = TimeDateUtils.getDateFormatted("yyyy-MM-dd");

	public TaxFreeServiceTest() {
		this(RequesterTaxID+"/0037", "testes1234");
	}

	public TaxFreeServiceTest(String username, String password) {
		super(ENDPOINTS.TEST, username, password, ServiceCertificates.getInstance());
	}

	public static TaxFreeSubmissionRequestType build() throws Exception {

		//--- HEADER ---
		RequestHeaderType header = new RequestHeaderType();
		header.setRequesterTaxID(RequesterTaxID);

		//--- COMPANY ---
		CompanyType company = new CompanyType();
		company.setTaxRegistrationNumber(RequesterTaxID);
		company.setBusinessName("Empresa");
		company.setEmail("tax@empresa.com");

		//--- BUYER ---
		BuyerType buyer = new BuyerType();
		buyer.setName("Turista nao europeu");
		buyer.setResidenceCountryCode("BR");
		buyer.setTaxRegistrationNumber(123456789);
		buyer.setBirthDate(TimeDateUtils.getXMLGregorianCalendar("2001-09-11"));

		IdentityDocType identity = new IdentityDocType();
		identity.setType("PASSAPORTE");
		identity.setNumber(new RandomValue().integer(9));
		identity.setCountryCode("BR");
		buyer.setIdentityDoc(identity);

		//--- REFUND ---
		RefundType refund = new RefundType();
		refund.setGuaranteeTotal(new BigDecimal("0.00"));
		refund.setCalculatedTaxTotal(new BigDecimal("46.00"));

		//--- INVOICES ---
		InvoicesType invoices = new InvoicesType();
		invoices.setNumberOfEntries(1);
		//invoices.setInvoicesGrossTotal(new BigDecimal("200.00"));
		//invoices.setCalculatedTaxes(taxes);

		InvoiceIdentifiersType invoiceIdentifiers = new InvoiceIdentifiersType();
		invoiceIdentifiers.setInvoiceNo("CFA 2018/"+new RandomValue().integer(6));
		invoiceIdentifiers.setHashTermination(new RandomValue().letters(4));

		/*
		SimpleBuyerType simpleBuyer = new SimpleBuyerType();
		simpleBuyer.setCompanyName("Turista nao europeu");
		VatNumberInfoType vatNumberInfo = new VatNumberInfoType();
		vatNumberInfo.setCustomerTaxID("123456789");
		vatNumberInfo.setCountryCode("BR");
		simpleBuyer.setVatNumberInfo(vatNumberInfo);
		*/

		CalculatedTaxesType taxes = new CalculatedTaxesType();
		CalculatedTaxType tax = new CalculatedTaxType();
		tax.setTaxPercentage(new BigDecimal("23.00"));
		tax.setTaxAmount(new BigDecimal("46.00"));
		taxes.getCalculatedTax().add(tax);

		InvoiceType invoice = new InvoiceType();
		//invoice.setATCUD(StringUtils.numbers(100));
		invoice.setInvoiceIdentifiers(invoiceIdentifiers);
		invoice.setInvoiceType("FT");
		invoice.setInvoiceDate(TimeDateUtils.getXMLGregorianCalendar(InvoiceDate));
		//invoice.setBuyer(simpleBuyer);
		invoice.setGrossTotal(new BigDecimal("246.00"));
		invoice.setRefundableAmount(new BigDecimal("46.00"));
		invoice.setCalculatedTaxes(taxes);

		//--- INVOICE LINES ---
		InvoiceLineType invoiceLine = new InvoiceLineType();
		invoiceLine.setProductClass("ALI");
		invoiceLine.setProductDescription("Vinho do Porto");
		invoiceLine.setQuantity(new BigDecimal("1.00"));
		invoiceLine.setTaxBaseTotal(new BigDecimal("200.00"));

		InvoiceLinesListType invoiceLinesList = new InvoiceLinesListType();
		invoiceLinesList.getInvoiceLine().add(invoiceLine);
		invoice.setInvoiceLinesList(invoiceLinesList);

		//--- INVOICES LIST ---
		InvoicesListType invoicesList = new InvoicesListType();
		invoicesList.getInvoice().add(invoice);
		invoices.setInvoicesList(invoicesList);

		//--- TAX FREE ---
		TaxFreeCommType taxFree = new TaxFreeCommType();
		taxFree.setIdentifier(new RandomValue().integer(9));
		taxFree.setSeller(company);
		taxFree.setBuyer(buyer);
		taxFree.setRefund(refund);
		taxFree.setInvoices(invoices);

		//--- REQUEST ---
		TaxFreeSubmissionRequestType request = new TaxFreeSubmissionRequestType();

		request.setRequestHeader(header);
		request.setTaxFreeComm(taxFree);

		return request;

	}


	public static void main(String[] args) {

		try{
			TaxFreeServiceTest service=new TaxFreeServiceTest();

			TaxFreeSubmissionResponseType response=service.register(build());

			System.out.println(response.getReturnInfo().getReturnCode());
			System.out.println(response.getReturnInfo().getReturnMessage());

			if (response.getTaxFreeCommRegistration()!=null){
				System.out.println(response.getTaxFreeCommRegistration().getTaxFreeCommCode());
				System.out.println(response.getTaxFreeCommRegistration().getQRCodeContent());
				System.out.println(response.getTaxFreeCommRegistration().getRegistrationDateTime());
			}

		}catch(Exception e){
			e.printStackTrace();
		}

	}


}
