package com.xtremelabs.robolectric.res;

import com.xtremelabs.robolectric.R;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class StringArrayResourceLoaderTest {
    private StringArrayResourceLoader stringArrayResourceLoader;

    @Before public void setUp() throws Exception {
        ResourceExtractor resourceExtractor = new ResourceExtractor();
        resourceExtractor.addRClass(R.class);
        StringResourceLoader stringResourceLoader = new StringResourceLoader(resourceExtractor);
        File resourceXmlDir = new File("test/res/values");
        new DocumentLoader(stringResourceLoader).loadResourceXmlDir(resourceXmlDir);
        stringArrayResourceLoader = new StringArrayResourceLoader(resourceExtractor, stringResourceLoader);
        new DocumentLoader(stringArrayResourceLoader).loadResourceXmlDir(resourceXmlDir);
    }

    @Test
    public void testStringsAreResolved() throws Exception {
        assertThat(Arrays.asList(stringArrayResourceLoader.getArrayValue(R.array.items)), contains("foo", "bar"));
    }

    @Test
    public void testStringsAreWithReferences() throws Exception {
        assertThat(Arrays.asList(stringArrayResourceLoader.getArrayValue(R.array.greetings)), contains("hola", "Hello"));
    }
}
