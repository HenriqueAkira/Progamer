package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.UserDAO;
import br.com.fiap.model.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserEndPoint {

	UserDAO userDao = new UserDAO();
	
	@GET
	public Response index() {
		try {
			List<User> lista = userDao.getAll();
			return Response.status(Response.Status.OK).entity(lista).build();
		}catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(User user) {
		if(user == null) return Response.status(Response.Status.BAD_REQUEST).build();
		try {
			System.out.println(user.getBirthDate());
			userDao.save(user);
			return Response.status(Response.Status.CREATED).entity(user).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("{id}")
	public Response show(@PathParam("id") Integer id) {
		User user = userDao.findById(id);
		if(user == null) return Response.status(Response.Status.NOT_FOUND).build();
		
		return Response.status(Response.Status.OK).entity(user).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Integer id, User user) {
		 if(id == null) return Response.status(Response.Status.BAD_REQUEST).build();
		 if(user == null) return Response.status(Response.Status.BAD_REQUEST).build();
		 User userUpdate = userDao.findById(id);
		 if(userUpdate == null) return Response.status(Response.Status.NOT_FOUND).build();
		 
		 try {
			 user.setId(id);
			 userDao.update(user);
			 return Response.status(Response.Status.OK).entity(user).build();
		 }catch (Exception e) {
			 e.printStackTrace();
			 return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		 
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Integer id) {
		if(id == null) return Response.status(Response.Status.BAD_REQUEST).build();
		if(userDao.findById(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
		
		try {
			System.out.println(id);
			userDao.delete(id);
			return Response.status(Response.Status.OK).build();
		}catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	
	
	
	
}

