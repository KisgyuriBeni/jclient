import controllers.TodoController;

public class App {
    public static void main(String[] args) throws Exception {
        new TodoController().create();
        //new TodoController().index();
    }
}
