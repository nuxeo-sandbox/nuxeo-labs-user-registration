package org.nuxeo.user.registration;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nuxeo.ecm.automation.test.AutomationFeature;
import org.nuxeo.ecm.core.test.annotations.Granularity;
import org.nuxeo.ecm.core.test.annotations.RepositoryConfig;
import org.nuxeo.ecm.restapi.test.RestServerFeature;
import org.nuxeo.runtime.test.runner.Deploy;
import org.nuxeo.runtime.test.runner.Features;
import org.nuxeo.runtime.test.runner.FeaturesRunner;
import org.nuxeo.runtime.test.runner.ServletContainerFeature;
import org.nuxeo.user.registration.endpoint.UserRegistrationObject;

import jakarta.inject.Inject;
import java.io.IOException;

@RunWith(FeaturesRunner.class)
@Features({AutomationFeature.class, RestServerFeature.class})
@RepositoryConfig(cleanup = Granularity.METHOD)
@Deploy({
        "nuxeo-labs-user-registration-core",
        "nuxeo-labs-user-registration-core:OSGI-INF/default-chain-contrib.xml"
})
public class TestRegistrationEndpoint {

    public static final String BASE_URL = "http://localhost";

    @Inject
    protected ServletContainerFeature servletContainerFeature;

    @Test
    public void testReceiveRegistrationCall() throws IOException {
        int port = servletContainerFeature.getPort();
        String url = BASE_URL + ":"+port+"/"+ UserRegistrationObject.PATH;
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        HttpResponse response = client.execute(post);
        Assert.assertEquals(200,response.getStatusLine().getStatusCode());
    }

}
