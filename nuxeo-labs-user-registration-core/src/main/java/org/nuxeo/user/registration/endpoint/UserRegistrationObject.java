package org.nuxeo.user.registration.endpoint;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationChain;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.OperationException;
import org.nuxeo.ecm.core.api.CloseableCoreSession;
import org.nuxeo.ecm.core.api.CoreInstance;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.ecm.webengine.model.WebObject;
import org.nuxeo.ecm.webengine.model.impl.ModuleRoot;
import org.nuxeo.runtime.api.Framework;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * WebEngine module to handle the self-service registration
 */
@Path("/selfregistration")
@WebObject(type = "selfregistration")
public class UserRegistrationObject extends ModuleRoot {

    protected static final Log log = LogFactory.getLog(UserRegistrationObject.class);

    public static final String PATH = "selfregistration";

    @Path("/")
    @POST
    public Object doPost(@Context HttpServletRequest request) throws IOException {
        InputStream in = request.getInputStream();
        String json = IOUtils.toString(in, Charset.defaultCharset());
        AutomationService as = Framework.getService(AutomationService.class);
        CoreSession session = CoreInstance.openCoreSessionSystem(null);
        try {
            OperationContext ctx = new OperationContext();
            ctx.setCoreSession(session);
            OperationChain chain = new OperationChain("processUserRegistrationRequest");
            chain.add("javascript.api_UserRegistration_requestAccount").
                    set("request",json);
            as.run(ctx, chain);
            return Response.status(Response.Status.OK).build();
        } catch (OperationException e) {
            throw new NuxeoException(e);
        } finally {
            if (session != null) {
                ((CloseableCoreSession) session).close();
                session = null;
            }
        }
    }

}
