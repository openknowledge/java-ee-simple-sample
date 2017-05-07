package de.openknowledge.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * @author Sven Koelpin
 */

@ApplicationPath(HttpApi.PATH)
public class HttpApi extends Application {
    static final String PATH = "api";
}
