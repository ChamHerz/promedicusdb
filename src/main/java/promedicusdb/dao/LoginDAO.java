package promedicusdb.dao;

import promedicusdb.consumes.LoginConsume;
import promedicusdb.model.Login;
import promedicusdb.model.Usuario;
import promedicusdb.util.LoginResult;

public class LoginDAO {
	
	public LoginResult getLogin(LoginConsume loginConsume) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		//Usuario usuario = usuarioDAO.getUsuarioByPassword(loginConsume.getUsername(),loginConsume.getPassword());
		LoginResult loginResult = usuarioDAO.getUsuarioByPassword(loginConsume.getUsername(),loginConsume.getPassword());
		
		return loginResult;
	}
	
}
