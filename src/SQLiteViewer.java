import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SQLiteViewer extends JFrame
{

    final private String PROGRAM_TITLE = "SQLite Viewer";
    final private String BTN_OPEN = "Open";
    private String BTN_EXECUTE = "Execute";
    private JTextField fileNameTextField = new JTextField();
    private JComboBox<String> tablesComboBox = new JComboBox<>();
    private JButton openFileButton = new JButton(BTN_OPEN);
    private JTextArea queryTextArea = new JTextArea();
    private JButton executeQueryButton = new JButton(BTN_EXECUTE);
    private DefaultTableModel model = new DefaultTableModel();

    public SQLiteViewer()
    {
        setTitle(PROGRAM_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        fileNameTextField.setName("FileNameTextField");
        fileNameTextField.setBounds(20, 20, getWidth() - 130, 30);
        add(fileNameTextField);

        openFileButton.setName("OpenFileButton");
        openFileButton.setBounds(getWidth() - 100, 20, 70, 30);
        add(openFileButton);

        tablesComboBox.setName("TablesComboBox");
        tablesComboBox.setBounds(20, 70, getWidth() - 50, 30);
        add(tablesComboBox);

        queryTextArea.setName("QueryTextArea");
        queryTextArea.setBounds(20, 120, getWidth() - 170, 60);
        add(queryTextArea);

        executeQueryButton.setName("ExecuteQueryButton");
        executeQueryButton.setBounds(getWidth() - 140, 120, 110, 30);
        add(executeQueryButton);

        JTable table = new JTable(model);
        table.setName("Table");
        JScrollPane contentTable = new JScrollPane(table);
        contentTable.setBounds(20, 200, getWidth() - 50, 300);
        add(contentTable);
    }

    public String getFileName()
    {
        return fileNameTextField.getText();
    }

    public String getQuery()
    {
        return queryTextArea.getText();
    }

    public String getComboBoxSelection()
    {
        return (String) tablesComboBox.getSelectedItem();
    }

    public void setTablesComboBox(ArrayList<String> tables)
    {
        tablesComboBox.removeAllItems();

        for (String table :
                tables)
        {
            tablesComboBox.addItem(table);
        }

        queryTextArea.removeAll();
    }

    public void clearTableModel()
    {
        model.setRowCount(0);
    }

    public void setTableColumns(Object[] columns)
    {
        model.setColumnIdentifiers(columns);
    }

    public void setTableRows(ArrayList<ArrayList<String>> data)
    {
        for (ArrayList<String> row :
                data)
        {
            model.addRow(row.toArray());
        }
    }


    public void setQueryTextArea(String response)
    {
        queryTextArea.removeAll();
        queryTextArea.setText(response);
    }

    public void addExecuteQueryButtonListener(ActionListener listenForExecuteQueryButton)
    {
        executeQueryButton.addActionListener(listenForExecuteQueryButton);
    }

    public void addOpenFileButtonListener(ActionListener listenForOpenFileButton)
    {
        openFileButton.addActionListener(listenForOpenFileButton);
    }

    public void addTablesComboBoxListener(ActionListener listenForTablesComboBox)
    {
        tablesComboBox.addActionListener(listenForTablesComboBox);
    }

    void displayErrorMessage(String errorMessage)
    {
        JOptionPane.showMessageDialog(new Frame(), errorMessage);
    }

}