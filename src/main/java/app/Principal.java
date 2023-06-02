package app;

import static spark.Spark.*;

import service.InteresseService;
import service.JogoService;
import service.RecomendacaoService;
import service.SteamSyncService;
import service.TagLinkService;
import service.TagService;
import service.PerfilService;
import service.AventuraService;
import service.UsuarioService;
import service.HomeService;
import service.EditarService;
import service.LoginService;

public class Principal {

	public static void main(String[] args) {
		JogoService jogoService = new JogoService();
		UsuarioService usuarioService = new UsuarioService();
		TagService tagService = new TagService();
		TagLinkService tagLinkService = new TagLinkService();
		InteresseService interesseService = new InteresseService();
		RecomendacaoService recomendacaoService = new RecomendacaoService();
		PerfilService perfilService = new PerfilService();
		AventuraService aventuraService = new AventuraService();
		SteamSyncService steamSyncService = new SteamSyncService();
		HomeService homeService = new HomeService();
		EditarService editarService = new EditarService();
		LoginService loginService = new LoginService();

		port(4500);

		staticFiles.location("/public");

		get("/", (request, response) -> {
			response.redirect("/home");
			return "";
		});

		// Jogos
		post("/jogos", jogoService::postListar);
		get("/jogos", jogoService::getListar);

		post("/jogo", jogoService::postLerJogo);
		get("/jogo/:idJogo", jogoService::getLerJogo);

		post("/jogo/create", jogoService::postCriarJogo);
		post("/jogo/update", jogoService::postAtualizarJogo);
		post("/jogo/delete", jogoService::postDeletarJogo);

		// Home
		post("/home", homeService::postListar);
		get("/home", homeService::getListar);

		post("/home", homeService::postLerHome);
		get("/home/:idhome", homeService::getLerHome);

		post("/home/create", homeService::postCriarHome);
		post("/home/update", homeService::postAtualizarHome);
		post("/home/delete", homeService::postDeletarHome);

		// Usuarios
		post("/usuarios", usuarioService::postListar);
		get("/usuarios", usuarioService::getListar);

		post("/usuario", usuarioService::postLerUsuario);
		get("/usuario/:idUsuario", usuarioService::getLerUsuario);

		post("/usuario/create", usuarioService::postCriarUsuario);
		post("/usuario/update", usuarioService::postAtualizarUsuario);
		post("/usuario/delete", usuarioService::postDeletarUsuario);

		// Login
		post("/login", loginService::postListar);
		get("/login", loginService::getListar);

		post("/login", loginService::postLerLogin);
		get("/login/:idlogin", loginService::getLerLogin);

		post("/login/create", loginService::postCriarLogin);
		post("/login/update", loginService::postAtualizarLogin);
		post("/login/delete", loginService::postDeletarLogin);

		// Perfil
		post("/perfil", perfilService::postListar);
		get("/perfil", perfilService::getListar);

		post("/perfil", perfilService::postLerPerfil);
		get("/perfil/:idUsuario", perfilService::getLerPerfil);

		post("/perfil/create", perfilService::postCriarPerfil);
		post("/perfil/update", perfilService::postAtualizarPerfil);
		post("/perfil/delete", perfilService::postDeletarPerfil);

		// Editar
		get("/editar", editarService::getListar);

		// Tags
		post("/tags", tagService::postListar);
		get("/tags", tagService::getListar);

		post("/tags/jogo", tagService::listarTagsPorJogo);

		post("/tag", tagService::postLerTag);
		get("/tag/:idTag", tagService::getLerTag);

		post("/tag/create", tagService::postCriarTag);
		post("/tag/update", tagService::postAtualizarTag);
		post("/tag/delete", tagService::postDeletarTag);

		// TagLinks
		post("/taglinks", tagLinkService::postListar);
		get("/taglinks", tagLinkService::getListar);

		post("/taglink", tagLinkService::postLerTagLink);
		get("/taglink/:idJogo/:idTag/", tagLinkService::getLerTagLink);

		post("/taglink/create", tagLinkService::postCriarTagLink);
		post("/taglink/update", tagLinkService::postAtualizarTagLink);
		post("/taglink/delete", tagLinkService::postDeletarTagLink);

		// Interesses
		post("/interesses", interesseService::postListar);
		get("/interesses", interesseService::getListar);

		post("/interesses/usuario", interesseService::postListarUsuario);

		post("/interesse", interesseService::postLerInteresse);
		get("/interesse/:idInteresse", interesseService::getLerInteresse);

		post("/interesse/create", interesseService::postCriarInteresse);
		post("/interesse/update", interesseService::postAtualizarInteresse);
		post("/interesse/delete", interesseService::postDeletarInteresse);

		// Recomendacao
		post("/recomendacoes", recomendacaoService::postListar);
		get("/recomendacoes", recomendacaoService::getListar);

		post("/recomendacoes/usuario", recomendacaoService::postListarUsuario);

		post("/recomendacao", recomendacaoService::postLerRecomendacao);
		get("/recomendacao/:idRecomendacao", recomendacaoService::getLerRecomendacao);

		post("/recomendacao/create", recomendacaoService::postCriarRecomendacao);
		post("/recomendacao/update", recomendacaoService::postAtualizarRecomendacao);
		post("/recomendacao/delete", recomendacaoService::postDeletarRecomendacao);

		post("/sincronizar/steam", steamSyncService::postSincronizar);

		// Aventura
		post("/aventuras", aventuraService::postListar);
		get("/aventuras", aventuraService::getListar);

		post("/aventuras/usuario", aventuraService::postListarUsuario);

		post("/aventuras", aventuraService::postLerAventura);
		get("/aventuras/:idAventura", aventuraService::getLerAventura);

		post("/aventuras/create", aventuraService::postCriarAventura);
		post("/aventuras/update", aventuraService::postAtualizarAventura);
		post("/aventuras/delete", aventuraService::postDeletarAventura);

		get("/sincronizar/steam", steamSyncService::postSincronizar);
	}
}