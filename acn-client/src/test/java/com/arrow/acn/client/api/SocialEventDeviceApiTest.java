package com.arrow.acn.client.api;

import com.arrow.acn.client.model.CreateSocialEventDeviceModel;
import com.arrow.acn.client.model.SocialEventDeviceModel;
import com.arrow.acn.client.search.RTUAvailableSearchCriteria;
import com.arrow.acn.client.search.RTURequestSearchCriteria;
import com.arrow.acn.client.search.SocialEventDeviceSearchCriteria;
import com.arrow.acn.client.websocket.MessageListener;
import com.arrow.acs.client.api.ApiConfig;
import com.arrow.acs.client.model.HidModel;
import com.arrow.acs.client.model.PagingResultModel;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class SocialEventDeviceApiTest {

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
                .withApiKey("apiKey")
                .withSecretkey("secretKey");

        acnClient = new AcnClient(apiConfig);
    }

    @Test
    public void create() throws Exception {
    	CreateSocialEventDeviceModel model = new CreateSocialEventDeviceModel();
    	model.setMacAddress("123");
    	model.setPinCode("1M9D");
    	model.setDeviceTypeHid("deviceTypeHid");
        HidModel result = acnClient.getSocialEventDeviceApi().create(model);
        assertNotNull(result.getHid());
    }
    
    @Test
    public void findByHid() throws Exception {
    	 SocialEventDeviceModel result = acnClient.getSocialEventDeviceApi().findByHid("hid");
    	 assertNotNull(result);
    }
    
    @Test
    public void findAll() throws Exception {
    	SocialEventDeviceSearchCriteria criteria = new SocialEventDeviceSearchCriteria();
    	criteria.withSize(2);
    	criteria.withPage(0);
    	criteria.withMacAddresses("1234");
		PagingResultModel<SocialEventDeviceModel> result = acnClient.getSocialEventDeviceApi().findAllBy(criteria);
		assertNotNull(result);
    }
    
    @Test
    public void update() throws Exception {
    	CreateSocialEventDeviceModel model = new CreateSocialEventDeviceModel();
    	model.setMacAddress("1234");
    	model.setPinCode("1M98");
    	model.setDeviceTypeHid("deviceTypeHid");
        HidModel result = acnClient.getSocialEventDeviceApi().update("hid", model);
        assertNotNull(result.getHid());
    }
}