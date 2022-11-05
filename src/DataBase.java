import java.sql.*;
import java.util.ArrayList;

public class DataBase
{
    private final String url;
    private ArrayList<String> columnNames;
    private ArrayList<ArrayList<String>> contentTable;

    public DataBase()
    {
        url = "";
        columnNames = null;
        contentTable = null;
    }

    public DataBase(String fileName)
    {
        this.url = "jdbc:sqlite:" + fileName;
    }

    private Connection connect()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection(url);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return connection;
    }


    public ArrayList<String> getTableNames()
    {
        String sql = "SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';";
        ArrayList<String> result = new ArrayList<>();

        try
        {
            Connection connection = this.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next())
            {
                result.add(resultSet.getString("name"));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public void getResponse(String request)
    {
        columnNames = new ArrayList<>();
        int columnNums;
        contentTable = new ArrayList<>();

        try
        {
            Connection connection = this.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);

            ResultSetMetaData columns = resultSet.getMetaData();
            columnNums = columns.getColumnCount();

            for (int i = 1; i <= columnNums; i++)
            {
                columnNames.add(columns.getColumnName(i));
            }

            while (resultSet.next())
            {
                ArrayList<String> contentLine = new ArrayList<>();

                for (String columnName:
                        columnNames)
                {
                    String content = resultSet.getString(columnName);
                    contentLine.add(content);
                }
                contentTable.add(contentLine);
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> getColumnNames()
    {
        return columnNames;
    }

    public ArrayList<ArrayList<String>> getContentTable()
    {
        return contentTable;
    }
}