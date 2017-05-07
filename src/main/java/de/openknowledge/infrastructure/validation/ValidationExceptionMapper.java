package de.openknowledge.infrastructure.validation;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author Sven Koelpin
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        JsonArrayBuilder errors = Json.createArrayBuilder();

        for (ConstraintViolation violation : exception.getConstraintViolations()) {
            JsonObjectBuilder errMsg = Json.createObjectBuilder();
            errMsg.add(extractInvalidPropName(violation), violation.getMessage());
            errors.add(errMsg);
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(errors.build().toString()).build();
    }

    private String extractInvalidPropName(ConstraintViolation violation) {
        //name is enough for this demo
        String[] pathParts = violation.getPropertyPath().toString().split("\\.");
        return pathParts[pathParts.length - 1];
    }
}
