package ananas.lib.impl.axk.security;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ananas.lib.axk.security.AXKSecurityListener;

public class DefaultAXKSecurityListener implements AXKSecurityListener {

	final static Logger logger = LogManager.getLogger(new Object() {
	});

	@Override
	public void onX509CertificateException(CertificateException e,
			X509Certificate[] certificates, String authType)
			throws CertificateException {

		logger.error(e);
		logger.error("authType=" + authType);
		int cnt = 0;
		for (X509Certificate cert : certificates) {
			logger.error("certificate[" + (cnt++) + "]=" + cert);
		}

		throw e;

	}

}
