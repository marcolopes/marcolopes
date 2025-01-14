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
package pt.gov.portaldasfinancas.servicos.series.types;

import pt.gov.portaldasfinancas.servicos.series.SeriesInfo;

public enum EstadoSerieType {

	/** Ativa */ A ("Ativa"),
	/** Anulada */ N ("Anulada"),
	/** Finalizada */ F ("Finalizada");

	public String value() {return name();}

	public final String descricao;

	private EstadoSerieType(String descricao) {
		this.descricao=descricao;
	}

	public static EstadoSerieType get(String name) {
		try{return valueOf(name);
		}catch(Exception e){}
		return null;
	}

	public static EstadoSerieType get(SeriesInfo info) {
		return get(info.getEstado());
	}

	@Override
	public String toString() {
		return name() + "-" + descricao;
	}

}
