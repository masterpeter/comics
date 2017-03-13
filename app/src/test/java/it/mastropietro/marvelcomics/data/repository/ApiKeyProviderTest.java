package it.mastropietro.marvelcomics.data.repository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import it.mastropietro.marvelcomics.data.repository.clock.Clock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Angelo Mastropietro on 11/03/17.
 */
public class ApiKeyProviderTest {

    @Mock Clock clock;

    private static final String publicKey = "publicKey";
    private static final String privateKey = "privateKey";
    private ApiKeyProvider provider;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(clock.getCurrentTimeInMillis()).thenReturn(12345L);
        provider = new ApiKeyProvider(clock, publicKey, privateKey);
    }

    @Test
    public void whenBuildQueryMapIsCalled_returnExpectedHashMap() throws Exception {
        Map<String, String> expectedMap = buildExpectedMap();

        Map<String, String> queryMap = provider.getQueryMap();

        assertEquals(expectedMap, queryMap);
    }

    private Map<String, String> buildExpectedMap() {
        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("apikey", publicKey);
        expectedMap.put("hash", "8416b2a85f0fcfe551fd8ba4cc49d904");
        expectedMap.put("ts", "12345");
        return expectedMap;
    }
}