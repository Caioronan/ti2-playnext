package service;

import com.fasterxml.jackson.databind.JsonNode;

import spark.Request;
import spark.Response;
import util.Seguranca;
import util.WebUtil;
import dao.UsuarioDAO;
import model.Usuario;

public class LoginService extends Service<UsuarioDAO> {

	public LoginService() {
		super(new UsuarioDAO(), "indexLogin.html");
	}

	public Object getListar(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/logins");
		return html;
	}

	public Object getLerLogin(Request request, Response response) {
		String html = construirPagina();
		html = html.replaceFirst("~FETCH~", "/login");
		html = html.replaceFirst("~FBODY~", "{\"idLogin\":\"" + request.params(":idLogin") + "\"}");
		return html;
	}

	public Object postListar(Request request, Response response) {
		response.type("application/json");
		return WebUtil.jsonLista(dao.listarUsuarios());
	}

	public Object postLerLogin(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idLogin = parent.path("idLogin").asLong();
			if (idLogin == 0L)
				return WebUtil.jsonLista(dao.listarUsuarios());
			return WebUtil.jsonPadrao(dao.lerUsuario(idLogin));
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postCriarLogin(Request request, Response response) {
		response.type("application/json");
		try {
			Usuario u = objectMapper.readValue(request.body(), Usuario.class);
			u.setSenha(Seguranca.hash(u.getSenha()));
			return WebUtil.jsonPadrao(dao.inserirUsuario(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postAtualizarLogin(Request request, Response response) {
		response.type("application/json");
		try {
			Usuario u = objectMapper.readValue(request.body(), Usuario.class);
			u.setSenha(Seguranca.hash(u.getSenha()));
			return WebUtil.jsonPadrao(dao.atualizarUsuario(u) ? "Sucesso" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}

	public Object postDeletarLogin(Request request, Response response) {
		response.type("application/json");
		try {
			JsonNode parent = objectMapper.readTree(request.body());
			long idLogin = parent.path("idLogin").asLong();
			return WebUtil.jsonPadrao(0, dao.excluirUsuario(idLogin) ? "Excluido" : "Erro interno");
		} catch (Exception e) {
			response.status(400);
			return WebUtil.jsonPadrao("\"BAD REQUEST\"");
		}
	}
}
