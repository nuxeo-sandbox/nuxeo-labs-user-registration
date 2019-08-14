# About nuxeo-labs-user-registration

This plugin provides a portal to request a user account and a service for users to be invited via submission of a registration request

# Usage

The InviteUserOp operation provides a method to invite a user to the platform by using the InvitationService and submitting a request. Though, of course, the implementation of this package can surely vary, the use case for this was specifically:

- User navigates to nuxeo site
- User hits my-app.html allowing them to either login or remain as Guest
- As Guest, user submits form for a registration request which triggers automation scripts for administrators to review and subsequently approve/decline the request
- If administrator approves the request, InviteUserOp should be triggered which will register the request with the InvitationService and automatically accept
- UserRegistrationRoot document type and UserRegistration document type are generally used for this setup

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

