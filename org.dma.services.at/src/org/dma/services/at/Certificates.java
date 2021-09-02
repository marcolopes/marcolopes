/*******************************************************************************
 * 2008-2021 Public Domain
 * Contributors
 * Marco Lopes (marcolopespt@gmail.com)
 *******************************************************************************/
package org.dma.services.at;

import org.dma.java.security.JKSCertificate;
import org.dma.java.security.JKSCertificate.CERTIFICATE_TYPE;

public class Certificates {

	/** Scheme Administrator Certificate */
	public static final JKSCertificate ChavePublicaAT = new JKSCertificate(CERTIFICATE_TYPE.JKS, "certificates/output/ChavePublicaAT.jks", "123456", "sapubkey");

	/** Software Developer Certificate */
	public static final JKSCertificate TesteWebservices = new JKSCertificate(CERTIFICATE_TYPE.PKCS12, "certificates/output/TesteWebservices.pfx", "TESTEwebservice");

	@Deprecated
	/** Trusted Store Certificate */
	public static final JKSCertificate TrustStoreAT = new JKSCertificate(CERTIFICATE_TYPE.JKS, "certificates/output/TrustStoreAT.jks", "123456", "portaldasfinancas");


}