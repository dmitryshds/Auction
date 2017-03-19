package biz.bagira.auction.util;

import org.junit.Test;
import org.testng.Assert;

import java.sql.Timestamp;

/**
 * Created by Dmitriy on 19.03.2017.
 */
public class DateUtilTest {
    @Test
    public void convert() throws Exception {
        String data = "03/05/2017";
        Timestamp convert = DateUtil.convert(data);
        Assert.assertNotNull(convert);
    }

}