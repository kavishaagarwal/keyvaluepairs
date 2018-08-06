package com.goibibo.resources;

import com.codahale.metrics.annotation.Timed;
import com.goibibo.constants.AuthConstants;
import com.goibibo.services.KeyValueService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/v1/key-value-pairs")
public class KeyValueResource {

    private KeyValueService keyValueService = new KeyValueService();

    @POST
    @Timed
    @Path("/upload-csv")
    public Response testResource(@HeaderParam("token") String token) {
        if (!validateToken(token)) {
            return Response.serverError().build();
        }

        boolean fileInserted = keyValueService.uploadData();
        String response = "";
        if (fileInserted) {
            response = "Succesfully uploaded the file";
        }
        else {
            response = "Could not upload the file";
        }

        return Response.ok().entity(response).build();
    }

    @GET
    @Timed
    @Path("/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKeyValuePair(@HeaderParam("token") String token,
                                    @PathParam("value") Integer value) {
        if (!validateToken(token)) {
            return Response.serverError().build();
        }

        JSONObject response = keyValueService.getKeyValuePair(value);

        return Response.ok().entity(response).build();
    }

    private boolean validateToken(String token) {
        return StringUtils.equals(token, AuthConstants.AUTH_TOKEN);
    }
}
