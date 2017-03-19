package biz.bagira.auction.configuration;

import org.dbunit.dataset.datatype.DataType;
import org.dbunit.dataset.datatype.DataTypeException;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import java.sql.Types;

/**
 * Created by Dmitriy on 19.03.2017.
 */

/**
 * HSQL has added a new data type, BOOLEAN, which replaces BIT.
 * But DBUnit is not updated to support this, and an error is throw
 * when you attempt to insert some BOOLEAN data using DBUnit.
 *
 */

public class HsqlDataTypeFactory extends DefaultDataTypeFactory {

    public DataType createDataType(int sqlType, String sqlTypeName)
            throws DataTypeException {
        if (sqlType == Types.BOOLEAN) {
            return DataType.BOOLEAN;
        }

        return super.createDataType(sqlType, sqlTypeName);
    }


}
