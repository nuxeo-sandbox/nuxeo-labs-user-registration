# About

This is a simple HTML application for submitting a user creation request to our endpoint. Note especially that authentication is not required. See [the contribs](nuxeo/OSGI-INF/) for details on exposing an endpoint this way.

# Requirements

* npm

# Install

There are no dependencies for the application itself, so nothing to install in that regard. For development you need to install the web server:

```shell
npm install
```

# Start the development server

[Web Dev Server](https://modern-web.dev/docs/dev-server/overview/) is used for development.

```shell
npm run
```

You can [configure it](https://modern-web.dev/docs/dev-server/cli-and-configuration/#configuration-file) using the [web-dev-server.config.mjs](web-dev-server.config.mjs) file.

**TODO**

By default nuxeo requests are proxied to `http://localhost:8080/nuxeo`. You can modify the [proxy settings](https://modern-web.dev/guides/dev-server/proxy-to-other-servers/) in [web-dev-server.config.mjs](web-dev-server.config.mjs).

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
