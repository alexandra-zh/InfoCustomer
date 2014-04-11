package core.data;

import core.helpers.Actions;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;

public class CommonDataProvider {

    private static final String DEFAULT_DEVIDER = ",";

    private static final String LOGIN_FILE_LOCATION = "/loginCreds";
    private static final String PERFORMANCE_GROUPS_FILE_LOCATION = "/performanceGroups";

    @DataProvider()
    public static Object [][] loginData() throws IOException {
        return getDataFromTextFile(LOGIN_FILE_LOCATION);
    }

    @DataProvider()
    public static Object [][] performanceGroupsData() throws IOException {
        return getDataFromTextFile(PERFORMANCE_GROUPS_FILE_LOCATION);
    }

    private static Object [][] getDataFromTextFile(String fileLocation, String devider){
        List<String> text  = Actions.getStringsFromFile(fileLocation);
        int rowsCount = text.size();
        int columnsCount = text.get(0).split(devider).length;
        Object [][] objects = new Object[rowsCount][columnsCount];
        for(int i = 0; i < text.size(); i++){
            objects[i] = text.get(i).split(devider);
        }
        return objects;
    }

    private static Object [][] getDataFromTextFile(String fileLocation){
        return getDataFromTextFile(fileLocation, DEFAULT_DEVIDER);
    }
}
