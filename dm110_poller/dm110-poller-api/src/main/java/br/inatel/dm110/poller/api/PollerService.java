package br.inatel.dm110.poller.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.inatel.dm110.poller.api.to.EquipamentTO;

@Path("/poller")
@Produces(MediaType.APPLICATION_JSON)
public interface PollerService {

	@GET
	@Path("/start/{ip}/{mask}")
	void startPoller(@PathParam("ip") String ip, @PathParam("mask") String mask);
	
	@GET
	@Path("/status/{ip}")
	EquipamentTO getEquipament(@PathParam("ip") String ip);

}
