import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SQLiteController
{
    private SQLiteViewer viewer;
    private DataBase db;

    SQLiteController(SQLiteViewer viewer, DataBase db)
    {
        this.viewer = viewer;
        this.db = db;

        this.viewer.addOpenFileButtonListener(new OpenFileButtonListener());
        this.viewer.addExecuteQueryButtonListener(new ExecuteQueryButtonListener());
        this.viewer.addTablesComboBoxListener(new TablesComboBoxListener());
    }

    class OpenFileButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String fileName = viewer.getFileName();

                if(!fileName.equals(""))
                {
                    System.out.println(fileName);
                    db = new DataBase(fileName);
                    ArrayList<String> tables = db.getTableNames();
                    viewer.setTablesComboBox(tables);
                }
            }
            catch (Exception SQLException)
            {
                viewer.displayErrorMessage("File not found");
            }
        }
    }
    class TablesComboBoxListener implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            String item = viewer.getComboBoxSelection();
            String response = "Select * FROM " + item + ";";
            viewer.setQueryTextArea(response);
        }
    }

    class ExecuteQueryButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            viewer.clearTableModel();
            String request = viewer.getQuery();
            db.getResponse(request);

            Object[] columns = db.getColumnNames().toArray();
            viewer.setTableColumns(columns);
            ArrayList<ArrayList<String>> data = db.getContentTable();
            viewer.setTableRows(data);
        }
    }
}