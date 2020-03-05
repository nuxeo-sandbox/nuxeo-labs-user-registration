package org.nuxeo.user.registration.automation;

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
    description = "Send invitation for user to create account.")
public class InviteUserOp {

    public static final String ID = "Service.InviteUser";

    @Context
    protected OperationContext ctx;
    @Context
    protected UserInvitationService invitationService;

    @Param(name = "email", required = true)
    protected String email;

    @Param(name = "first_name", required = true)
    protected String first_name;

    @Param(name = "last_name", required = true)
    protected String last_name;

    @Param(name = "validationMethod", required = false)
    protected ValidationMethod validationMethod = ValidationMethod.EMAIL;

    @Param(name = "autoAccept", required = false)
    protected boolean autoAccept = true;

    @Param(name = "info", required = false)
    protected Map<String, Serializable> info = new HashMap<>();

    @Param(name = "comment", required = false)
    protected String comment;

    @Param(name = "groups", required = false)
    protected String[] groups = {"members"};

    @Param(name = "Output Variable", required = true)
    protected String outputVariable;

    @OperationMethod
    public void run() {
        DocumentModel invitation = invitationService.getUserRegistrationModel(null);
        UserRegistrationConfiguration config = invitationService.getConfiguration();
        invitation.setPropertyValue(config.getUserInfoUsernameField(), email);
        invitation.setPropertyValue(config.getUserInfoFirstnameField(), first_name);
        invitation.setPropertyValue(config.getUserInfoLastnameField(), last_name);
        invitation.setPropertyValue(config.getUserInfoEmailField(), email);
        invitation.setPropertyValue(config.getUserInfoGroupsField(), groups);
        //invitation.setPropertyValue(config.getUserInfoTenantIdField(), user.getTenantId());
        invitation.setPropertyValue(config.getUserInfoCompanyField(), email);
        invitation.setPropertyValue("registration:comment", comment);

        if (info.get("registration:originatingUser") == null) {
            String originatingUser = ctx.getCoreSession().getPrincipal().getName();
            info.put("registration:originatingUser", originatingUser);
        }

        String inviteId = invitationService.submitRegistrationRequest(invitation, info, validationMethod, autoAccept);
        ctx.put(outputVariable, inviteId);
    }
}

