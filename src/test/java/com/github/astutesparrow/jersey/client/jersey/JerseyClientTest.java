package com.github.astutesparrow.jersey.client.jersey;

import com.github.astutesparrow.jersey.domain.Address;
import com.github.astutesparrow.jersey.domain.Contact;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import java.util.Arrays;

/**
 * @author: 智深
 * @since: 2013-05-11
 */
public class JerseyClientTest {
    WebResource r = null;

    @BeforeTest()
    public void initWebContactsResource() {
        Client c = Client.create();
        r = c.resource("http://localhost:8080/learn_jersey/rest/contacts");
    }

    @Test(enabled = false)
    public void getContacts() {
        ClientResponse response = r.get(ClientResponse.class);
        System.out.println( response.getStatus() );
        System.out.println( response.getHeaders().get("Content-Type") );
        String entity = response.getEntity(String.class);
        System.out.println(entity);
    }

    @Test(enabled = false)
    public void createContact() {
        Address[] addrs = {
                new Address("Shanghai", "Ke Yuan Street")
        };
        Contact c = new Contact("foo", "Foo Bar", Arrays.asList(addrs));

        ClientResponse response = r
                .path(c.getId())
                .accept(MediaType.APPLICATION_XML)
                .put(ClientResponse.class, c);
        System.out.println(response.getStatus());
    }

    @Test
    public void deleteContact() {
        GenericType<JAXBElement<Contact>> generic = new GenericType<JAXBElement<Contact>>() {};
        JAXBElement<Contact> jaxbContact = r
                .path("foo")
                .type(MediaType.APPLICATION_XML)
                .get(generic);
        Contact contact = jaxbContact.getValue();
        System.out.println(contact.getId() + ": " + contact.getName());

        ClientResponse response = r.path("foo").delete(ClientResponse.class);
        System.out.println(response.getStatus());
    }

}
