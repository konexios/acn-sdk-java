package com.arrow.acn.client.api;

import com.arrow.acn.client.model.CreateSocialEventRegistrationModel;
import com.arrow.acn.client.model.RegisterSocialEventRegistrationModel;
import com.arrow.acn.client.model.SocialEventRegistrationModel;
import com.arrow.acn.client.model.SocialEventRegistrationStatuses;
import com.arrow.acn.client.search.SocialEventRegistrationRegisterCriteria;
import com.arrow.acn.client.search.SocialEventRegistrationSearchCriteria;
import com.arrow.acn.client.search.SocialEventRegistrationVerifyCriteria;
import com.arrow.acn.client.websocket.MessageListener;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.PagingResultModel;
import com.arrow.acs.client.model.StatusModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class SocialEventApiTest {

    private AcnClient acnClient;

    private MessageListener messageListener = new MessageListener() {
        @Override
        public void onMessage(String message) {
            System.out.println(message);
        }
    };

    @Before
    public void init() {
        ApiConfig apiConfig = new ApiConfig()
                .withBaseUrl("http://localhost:12001")
                .withApiKey("5a3264fb990a18e0e180efbbfadd5d95db3baed93defcb1d5a35966c6b98fef5")
                .withSecretkey("secretKey");

        acnClient = new AcnClient(apiConfig);
    }

    @Test
    public void create() throws Exception {
    	CreateSocialEventRegistrationModel model = new CreateSocialEventRegistrationModel();
        model.setOrigEmail("test@mail.ru");
        model.setOrigPassword("origPassword");
        model.setSocialEventHid("socialEventHid");
		HidModel result = acnClient.getSocialEventRegistrationApi().create(model);
		assertNotNull(result.getHid());
    }
    
    @Test
    public void register() throws Exception {
    	SocialEventRegistrationRegisterCriteria criteria = new SocialEventRegistrationRegisterCriteria();
    	criteria.withSocialEventHid("socialEventHid");
    	criteria.withEventCode("code");
    	
    	RegisterSocialEventRegistrationModel model = new RegisterSocialEventRegistrationModel();
        model.setEmail("test@mail.ru");
        model.setName("test");
        model.setPassword("123");
        HidModel result = acnClient.getSocialEventRegistrationApi().register(criteria, model);
        assertNotNull(result.getHid());
    }
    
    @Test
    public void verify() throws Exception {
    	
    	SocialEventRegistrationVerifyCriteria criteria = new SocialEventRegistrationVerifyCriteria();
    	criteria.withVerificationCode("0000");
    	HidModel result = acnClient.getSocialEventRegistrationApi().verify("hid", criteria);
        assertNotNull(result.getHid());
    }
    
    @Test
    public void update() throws Exception {
    	
    	RegisterSocialEventRegistrationModel model = new RegisterSocialEventRegistrationModel();
    	model.setEmail("test1@mail.ru");
    	model.setName("test1");
    	model.setPassword("456");
    	HidModel result = acnClient.getSocialEventRegistrationApi().update("hid", model);
        assertNotNull(result.getHid());
    }
    
    @Test
    public void delete() throws Exception {
    	StatusModel result = acnClient.getSocialEventRegistrationApi().delete("hid");
        assertEquals(result.getStatus(), "OK");
    }
    
    @Test
    public void findByHid() throws Exception {
    	SocialEventRegistrationModel result = acnClient.getSocialEventRegistrationApi().findByHid("hid");
        assertNotNull(result);
    }
    
    @Test
    public void findAll() throws Exception {
    	SocialEventRegistrationSearchCriteria criteria = new SocialEventRegistrationSearchCriteria();
    	criteria.withSocialEventHids("socialEventHid");
    	criteria.withPage(0);
    	criteria.withSize(2);
    	criteria.withStatuses(SocialEventRegistrationStatuses.PENDING, SocialEventRegistrationStatuses.REGISTERED);
    	PagingResultModel<SocialEventRegistrationModel> result = acnClient.getSocialEventRegistrationApi().findAllBy(criteria);
        assertNotNull(result);
    }
}