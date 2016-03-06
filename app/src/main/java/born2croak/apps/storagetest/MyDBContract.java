package born2croak.apps.storagetest;


import android.provider.BaseColumns;

/**
 * Created by Tim on 3/1/2016.
 */
public final class MyDBContract implements BaseColumns {

    public MyDBContract() {};

    public static final String TABLE_NAME = "counting";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_COUNT = "count";

}
