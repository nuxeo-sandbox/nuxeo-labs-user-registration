package org.nuxeo.labs.user.registration.automation;

import org.nuxeo.ecm.automation.OperationContext;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.user.invite.UserInvitationService;
import org.nuxeo.ecm.user.invite.UserRegistrationConfiguration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static org.nuxeo.ecm.user.invite.UserInvitationService.ValidationMethod;

@Operation(id = InviteUserOp.ID, category = Constants.CAT_SERVICES, label = "Invite a user",
        description = "Stores a registration request and returns its ID.")
public class InviteUserOp {

    public static final String ID = "Service.InviteUser";

    @Context
    protected OperationContext ctx;
    @Context
    protected UserInvitationService invitationService;

    @Param(name = "validationMethod", required = false)
    protected ValidationMethod validationMethod = ValidationMethod.EMAIL;

    @Param(name = "autoAccept", required = false)
    protected boolean autoAccept = true;

    @Param(name = "info", required = false)
    protected Map<String, Serializable> info = new HashMap<>();

    @Param(name = "comment", required = false)
    protected String comment;

    @Param(name = "Output Variable", required = true)
    protected String outputVariable;

    @OperationMethod
    public DocumentModel run(DocumentModel doc) {
        DocumentModel invitation = invitationService.getUserRegistrationModel(null);
        UserRegistrationConfiguration config = invitationService.getConfiguration();
        invitation.setPropertyValue(config.getUserInfoUsernameField(), doc.getPropertyValue("user_registration:email"));
        invitation.setPropertyValue(config.getUserInfoFirstnameField(), doc.getPropertyValue("user_registration:first_name"));
        invitation.setPropertyValue(config.getUserInfoLastnameField(), doc.getPropertyValue("user_registration:last_name"));
        invitation.setPropertyValue(config.getUserInfoEmailField(),  doc.getPropertyValue("user_registration:email"));
        invitation.setPropertyValue(config.getUserInfoGroupsField(), new String[]{"members","consumers"});
        //invitation.setPropertyValue(config.getUserInfoTenantIdField(), user.getTenantId());
        invitation.setPropertyValue(config.getUserInfoCompanyField(), doc.getPropertyValue("user_registration:email"));
        invitation.setPropertyValue("registration:comment", comment);
        String inviteId =  invitationService.submitRegistrationRequest(invitation, info, validationMethod, autoAccept);
        ctx.put(outputVariable, inviteId);
        return doc;
    }
}

