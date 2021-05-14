package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.SetupDAO;
import br.com.fiap.model.Setup;
import br.com.fiap.model.User;

@Named  //CDI
@RequestScoped
public class SetupBean {
	
	private Setup setup = new Setup();
	FacesContext context = FacesContext.getCurrentInstance();

	
	public void save() {
		User user = (User) context.getExternalContext().getSessionMap().get("user");
		this.setup.setUser(user);
		new SetupDAO().save(this.setup);
		this.setup = new Setup();
		
		context.addMessage(null, new FacesMessage("Setup cadastrado com sucesso"));
		
	}
	
	public List<Setup> getSetups(){
		return new SetupDAO().getAll();
	}
	
	public List<Setup> getSetupsByUser(){
		return new SetupDAO().getSetupsByUser((User) context.getExternalContext().getSessionMap().get("user"));
	}
	
	public void executar() {
		System.out.println("Acionando o bean");
	}

	public Setup getSetup() {
		return setup;
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
	}
	
	
}
