import com.Classifier.Installer;
import com.Classifier.InstallerSerializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by von on 12/16/14.
 */
public class SerializerTest {



    Installer installer;
    List<Installer> installers;

    @Before
    public void initialize() {
        installers = new ArrayList<>();
        installer = new Installer("test.exe",false);
        installers.add(new Installer("somefile.exe",true));
        installers.add(new Installer("somefile1.exe",false));

    }

    @Test
    public void testSerializer() {
        InstallerSerializer serializer = new InstallerSerializer();
        String expectedVal2 = "[{\"fileName\":\"somefile.exe\"," +
                "\"installer\":true},{\"fileName\":\"somefile1.exe\",\"installer\":false}]";
        String expectedVal = "{\"fileName\":\"test.exe\",\"installer\":false}";

        Assert.assertEquals(expectedVal,serializer.serialize(installer));
        Assert.assertEquals(expectedVal2,serializer.serialize(installers));

        String expected_filename = "test.exe";
        boolean expected_classify = false;
        Installer deserialized = serializer.deserialize(expectedVal);
        Assert.assertEquals(expected_filename,deserialized.getFileName());
        Assert.assertEquals(expected_classify,deserialized.isInstaller());

        List deserializedList = serializer.deserializeList(expectedVal2);

        String expectedList = "[{fileName=somefile.exe, installer=true}, " +
                "{fileName=somefile1.exe, installer=false}]";
        Assert.assertEquals(expectedList,deserializedList.toString());

    }
}
