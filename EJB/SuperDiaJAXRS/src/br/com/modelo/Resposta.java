package br.com.modelo;

import javax.ws.rs.core.Response;

public final class Resposta {
	public static Response status(Integer status, Object entiadade) {
		return Response
				.status(status)
				.header("Acces-Control-Allow-Origin", "*")
				.entity(entiadade.toString())
				.build();
	}

}
