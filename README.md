# About nuxeo-labs-user-registration

This plugin provides a Web portal so that an anonymous user may request a user account, and an operation to leverage the Nuxeo Platform's `UserInvitationService` to complete the registration.

Note that the `UserInvitationService` performs the actual user registration. This service takes care of sending the user an invitation email for registration, which allows them to set their own password when creating the account. I.e. this plugin does *not* create users.

Note also that this plugin is an example of exposing resources for anonymous access, *without* enabling [full anonymous access](https://doc.nuxeo.com/n/4X8), in particular:

* The `user_registration` endpoint, which provides access to the web portal
* The `javascript.api_UserRegistration_requestAccount` Automation request, which does not require authentication to access

# Usage

The plug-in provides the following default flow:

* An anonymous user accesses the endpoint `<yourserver>/nuxeo/user_registration` where they will find the self-registration form (`my-app.html`)
* They fill in the form and submit the request
* The request info is sent to the Automation Script `javascript.api_UserRegistration_requestAccount`

It is up to you to decide how the request is handled. A typical flow involves creating a document to track the request, running a workflow on that document to track the approval, etc. In particular you will call the `Service.InviteUser` operation to complete the registration request, when ready. The "Nuxeo Self Registration" [Studio Template](https://doc.nuxeo.com/n/vOI/) is available to help scaffold this flow.

## Automation Script javascript.api_UserRegistration_requestAccount

The registration form calls this Automation Script with the registration info.

Note: you need to implement this script in your Studio project. The following information is sent in `params.request`:

property | type | description
--- | --- | ---
email | string | Will be the user name in Nuxeo
firstname | string |
lastname | string |
company | string |
department | string |
jobTitle | string |
reason | string |

## Operation Service.InviteUser

This operation uses the `UserInvitationService` to invite a user.

param | type | required | description
--- | --- | --- | ---
email | string | true | Will be the user name in Nuxeo
first_name | string | true
last_name | string | true
Output Variable | string | true | Name of the context variable; the UUID of the `UserInvitation` document is returned here
groups | string[] | false | List of groups for the new user; default is `{"members"}`
info | key/value map | false | Can be used to set values on the `UserInvitation` document, for example to change `registration:originatingUser`
validationMethod | string | false | Default "Email"
autoAccept | boolean | false | Default "true"
comment | string | false | Stored in the `regisration:comment` field of the `UserInvitation` document

Reminder: this operation does _not_ create users. Unless the user accepts the invitation (which is sent by the `UserInvitationService`), no user will be created in Nuxeo.

# Requirements

The self-service Web portal expects a logo at `/nuxeo/img/user-registration-logo.png`. See [my-app.html](nuxeo-labs-user-registration-web/src/my-app.html) for CSS details. Note that the Studio template contains a default logo that you can override.

# Build

<!-- Hide for now as this server is no longer publicly accessible.
[![Build Status](https://qa.nuxeo.org/jenkins/buildStatus/icon?job=Sandbox/sandbox_nuxeo-labs-user-registration-master)](https://qa.nuxeo.org/jenkins/job/Sandbox/job/sandbox_nuxeo-labs-user-registration-master/)
-->

Requirements

- git
- maven

```
git clone https://github.com/nuxeo-sandbox/nuxeo-labs-user-registration
cd nuxeo-labs-user-registration
mvn clean install
```

Notice that a Docker image is included for testing. It's possible to skip Docker build by setting default `skipDocker` property value to `true`:

```bash
# Skip Docker build
mvn -DskipDocker=true clean install
```

Notice that for unit tests, credentials are set differently (see below or in the source code). To compile without running the tests:

```bash
mvn clean install -DskipTests=true
# To compile the tests without running them:
mvn test-compile
```

# Support

**These features are not part of the Nuxeo Production platform.**

These solutions are provided for inspiration and we encourage customers to use them as code samples and learning resources.

This is a moving project (no API maintenance, no deprecation process, etc.) If any of these solutions are found to be useful for the Nuxeo Platform in general, they will be integrated directly into platform, not maintained here.

# License

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

# About Nuxeo

Nuxeo Platform is an open source Content Services platform, written in Java. Data can be stored in both SQL & NoSQL databases.

The development of the Nuxeo Platform is mostly done by Nuxeo employees with an open development model.

The source code, documentation, roadmap, issue tracker, testing, benchmarks are all public.

Typically, Nuxeo users build different types of information management solutions for [document management](https://www.nuxeo.com/solutions/document-management/), [case management](https://www.nuxeo.com/solutions/case-management/), and [digital asset management](https://www.nuxeo.com/solutions/dam-digital-asset-management/), use cases. It uses schema-flexible metadata & content models that allows content to be repurposed to fulfill future use cases.

More information is available at [www.nuxeo.com](https://www.nuxeo.com).
