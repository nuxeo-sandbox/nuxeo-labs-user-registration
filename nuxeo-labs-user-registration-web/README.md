# About

This is a simple HTML application for submitting a user creation request to our endpoint. Note especially that authentication is not required. See [the contribs](nuxeo/OSGI-INF/) for details on exposing a Nuxeo endpoint that does not require authentication.

The application is composed of pure HTML and JS (no custom framework) with [W3.CSS](https://www.w3schools.com/w3css/default.asp) for the look and feel. This is a complete replacement of the previous application, which was based on the [Polymer Starter Kit](https://github.com/Polymer/polymer-starter-kit). That project is no longer maintained and contains several critical security vulnerabilities. Thus the application was re-written in part to avoid the maintenance headaches associated with custom frameworks. Given the simplistic requirements of this application, coupled with advancements in OOTB browser support, any full front-end stack is overkill and more difficult to maintain.

# Requirements

Npm (5.2.0+) is required for development. There are no requirements for build/runtime.

# Install

There are no dependencies for the application, so nothing to install. In other words, `npm install` is not required.

# Start the development server

[http-server](https://www.npmjs.com/package/http-server) is used for development via `npx` so that nothing is permanently installed. You can start the development server like so:

```shell
npm start
```

You can configure the dev server by editing the [package.json](package.json) file.

**TODO**

By default nuxeo requests are proxied to `http://localhost:8080/nuxeo`.

# Usage

All form fields are required. The application calls the `javascript.api_UserRegistration_requestAccount` Automation Script to make the request (this script is available in the ["Nuxeo Self Registration" Studio Template](https://doc.nuxeo.com/studio/default-configuration-templates/#nuxeo-self-registration))

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
