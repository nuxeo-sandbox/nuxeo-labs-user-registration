package org.nuxeo.user.registration.automation;

import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.common.utils.ArrayUtils;
import org.nuxeo.common.utils.FileUtils;
import org.nuxeo.ecm.automation.AutomationService;
import org.nuxeo.ecm.automation.OperationChain;
import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.IdRef;
import org.nuxeo.ecm.core.api.PathRef;
import org.nuxeo.ecm.core.api.impl.blob.FileBlob;
import org.nuxeo.ecm.core.test.CoreFeature;
import org.nuxeo.ecm.platform.mimetype.interfaces.MimetypeRegistry;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;

import java.io.File;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(FeaturesRunner.class)
@Features({CoreFeature.class, AutomationFeature.class})
@Deploy({"org.nuxeo.ecm.user.invite", "nuxeo-labs-user-registration-core"})
public class TestInviteUserOp {

    @Inject
    CoreSession session;

    @Inject
    AutomationService service;

    protected DocumentModel doc;

    @Before
    public void initRepo() throws Exception {

        // I don't know why this is needed because UserInvitationComponent creates the document if it doesn't exist, but
        // without this I get a DocumentNotFound exception.
        DocumentModel management = session.createDocumentModel("/", "management", "Folder");
        session.createDocument(management);

        // The operation uses the document to get the originating user
        doc = session.createDocumentModel("/", "TheDoc", "File");
        doc.setPropertyValue("dc:title", "TheDoc");
        doc = session.createDocument(doc);

        session.save();
    }

    @Test
    public void shouldCreateRegistrationRequest() throws Exception {

        assertNotNull(doc);

        String email = "null@void.com";
        String first_name = "first";
        String last_name = "last";
        String[] groups = {"members", "administrators"};
        String outputVariable = "foo";

        OperationContext ctx = new OperationContext(session);
        ctx.setInput(doc);
        OperationChain chain = new OperationChain("shouldCreateRegistrationRequest");
        chain.add(InviteUserOp.ID)
            .set("email", email)
            .set("first_name", first_name)
            .set("last_name", last_name)
            .set("groups", groups)
            .set("Output Variable", outputVariable);
        service.run(ctx, chain);

        String invitationUUID = (String) ctx.get(outputVariable);

        assertNotNull(invitationUUID);

        DocumentModel invitationDoc = session.getDocument(new IdRef(invitationUUID));

        assertNotNull(invitationDoc);

        assertEquals(invitationDoc.getType(), "UserInvitation");
        assertEquals(invitationDoc.getPropertyValue("userinfo:login"), email);
        assertEquals(invitationDoc.getPropertyValue("userinfo:email"), email);
        assertEquals(invitationDoc.getPropertyValue("userinfo:firstName"), first_name);
        assertEquals(invitationDoc.getPropertyValue("userinfo:lastName"), last_name);
        assertEquals(invitationDoc.getPropertyValue("registration:originatingUser"), "Administrator");

        // TODO: figure how to perform this comparison
        // assertEquals(invitationDoc.getPropertyValue("userinfo:groups").length,groups);
        // String[] invitationGroups = (String[])invitationDoc.getPropertyValue("userinfo:groups");
        // assertArrayEquals(invitationGroups,groups);
    }
}