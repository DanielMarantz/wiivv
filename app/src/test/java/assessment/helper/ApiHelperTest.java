package assessment.helper;

import com.wiivv.assessment.api.ApiClient;
import com.wiivv.assessment.helper.ApiHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

public class ApiHelperTest {

    private ApiHelper mApiHelper;
    private ApiClient mApiClient;

    @Before
    public void setUp() {
        mApiHelper = mock(ApiHelper.class);
        mApiClient = mock(ApiClient.class);
    }

    @After
    public void tearDown() {
        mApiHelper = null;
        mApiClient = null;
    }

    @Test
    public void apiHelperInstance() {
        assertNotNull(mApiHelper);
    }

    @Test
    public void apiClientInstance() {
        assertNotNull(mApiClient);
    }
}
