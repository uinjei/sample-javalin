package com.winj.javalin;

import org.pac4j.core.context.HttpConstants;
import org.pac4j.javalin.DefaultHttpActionAdapter;
import org.pac4j.javalin.Pac4jContext;

import io.javalin.ForbiddenResponse;
import io.javalin.UnauthorizedResponse;


/**
 * @author Edwin Jay Javier
 *
 */
public class HttpActionAdapter extends DefaultHttpActionAdapter {

    @Override
    public Void adapt(int code, Pac4jContext context) {
        if (code == HttpConstants.UNAUTHORIZED) {
            throw new UnauthorizedResponse();
        } else if (code == HttpConstants.FORBIDDEN) {
            throw new ForbiddenResponse();
        } else {
            return super.adapt(code, context);
        }
    }
}
