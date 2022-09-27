/*******************************************************************************
 * Copyright 2008-2022 Marco Lopes (marcolopespt@gmail.com)
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
package pt.gov.portaldasfinancas.servicos.documentos.types;

import pt.gov.portaldasfinancas.servicos.documentos.PaymentStatus;

public enum PaymentStatusType {

	/** Normal */ N ("Normal"),
	/** Anulado */ A ("Anulado");

	public final String descricao;

	private PaymentStatusType(String descricao) {
		this.descricao=descricao;
	}

	public static PaymentStatusType get(PaymentStatus status) {
		try{return valueOf(status.getPaymentStatus());
		}catch(Exception e){}
		return null;
	}

	public String value() {
		return name();
	}

	@Override
	public String toString() {
		return name() + "-" + descricao;
	}

}