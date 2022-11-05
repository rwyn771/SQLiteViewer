public class ApplicationRunner
{
    public static void main(String[] args)
    {
        SQLiteViewer viewer = new SQLiteViewer();

        DataBase db = new DataBase();

        SQLiteController controller = new SQLiteController(viewer, db);

        viewer.setVisible(true);
    }
}
