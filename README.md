# About nuxeo-labs-user-registration

This plugin provides a Web portal so that an anonymous user may request a user account, and an operation to leverage the Nuxeo Platform's `UserInvitationService` to complete the registration.

Note that the `UserInvitationService` performs the actual user registration. This service takes care of sending the user an invitation email for registration, which allows them to set their own password when creating the account. I.e. this plugin does *not* create users.

Note also that this plugin is an example of exposing resources for anonymous access, *without* enabling [full anonymous access](https://doc.nuxeo.com/n/4X8), in particular:

* The `user_registration` endpoint, which provides access to the web portal
* The `javascript.api_new_account_request` Automation request, which does not require authentication to access

# Usage

The plug-in provides the following default flow:

* An anonymous user accesses the endpoint `<yourserver>/nuxeo/user_registration` where they will find the self-registration form (`my-app.html`)
* They fill in the form and submit the request
* The request info is sent to the Automation Script `javascript.api_new_account_request`

It is up to you to decide how the request is handled. A typical flow involves creating a document to track the request (see "UserRegistration Document Type" below), running a workflow on that document to track the approval, etc. In particular you will call the `Service.InviteUser` operation to complete the registration request, when ready. A Studio Template will be available to help scaffold this flow.

## UserRegistration Document Type

The plugin includes a document type definition called "UserRegistration" for storing the registration request data. Currently this document type is required by the `Service.InviteUser` operation so you need to use this type when validating the request.

## TODO

* Refactor `Service.InviteUser` to not require a document but instead use params
* Remove document contribs, they aren't needed

# Requirements

The self-service Web portal expects a logo at `/nuxeo/img/user-registration-logo.png`. See (my-app.html)[nuxeo-labs-user-registration-web/src/my-app.html] for CSS details.

# Build

[![Build Status](https://qa.nuxeo.org/jenkins/buildStatus/icon?job=Sandbox/sandbox_nuxeo-labs-user-registration-master)](https://qa.nuxeo.org/jenkins/job/Sandbox/job/sandbox_nuxeo-labs-user-registration-master/)

Requirements

- git
- maven

```
git clone https://github.com/nuxeo-sandbox/nuxeo-labs-user-registration
cd nuxeo-labs-user-registration
mvn clean install
```
Notice that for unit test, credentials are set differenty (see below or in the source code). To compile without running the tests:

```
mvn clean install -DskipTests=true
#to compile the test without running them:
mvn test-compile
```

# Support

**These features are not part of the Nuxeo Production platform.**

These solutions are provided for inspiration and we encourage customers to use them as code samples and learning resources.

This is a moving project (no API maintenance, no deprecation process, etc.) If any of these solutions are found to be useful for the Nuxeo Platform in general, they will be integrated directly into platform, not maintained here.


# Licensing

[Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)


# About Nuxeo

[Nuxeo](www.nuxeo.com), developer of the leading Content Services Platform, is reinventing enterprise content management (ECM) and digital asset management (DAM). Nuxeo is fundamentally changing how people work with data and content to realize new value from digital information. Its cloud-native platform has been deployed by large enterprises, mid-sized businesses and government agencies worldwide. Customers like Verizon, Electronic Arts, ABN Amro, and the Department of Defense have used Nuxeo's technology to transform the way they do business. Founded in 2008, the company is based in New York with offices across the United States, Europe, and Asia.

Learn more at www.nuxeo.com.

