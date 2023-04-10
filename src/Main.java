import ConnectionDB.DataBaseConnection;
import Tables.Homework;
import Tables.Lesson;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;

        //Create connection to db
        try {
            conn = DataBaseConnection.getConnection();


            LessonDao lessonDao = new LessonDao(conn);
            // створити обєкти для роботи з базою
            Homework hw0 = new Homework(1, "homework 1", "homework description 1" );
            Homework hw1 = new Homework(2, "homework 2", "homework description 2" );
            Homework hw2 = new Homework(3, "homework 3", "homework description 3" );
            Homework hw3 = new Homework(4, "homework 4", "homework description 4" );
            Homework hw4 = new Homework(5, "homework 5", "homework description 5" );
            Homework hw5 = new Homework(5, "homework 6", "homework description 6" );
            Homework hw6 = new Homework(5, "homework 6", "homework description 7" );

            Lesson les1 = new Lesson(1,"English", hw0);
            Lesson les2 = new Lesson(2,"Philosofie", hw1);
            Lesson les3 = new Lesson(3,"Math", hw2);
            Lesson les4 = new Lesson(4,"Physics", hw3);
            Lesson les5 = new Lesson(5,"Architecture", hw4);
            Lesson les6 = new Lesson(6,"Engineering", hw5);
            Lesson les7 = new Lesson(7,"Biology", hw6);

            // lesson list before delete
            lessonDao.getAllLessons();

            lessonDao.addLesson (les1);
            lessonDao.addLesson (les2);
            lessonDao.addLesson (les3);
            lessonDao.addLesson (les4);
            lessonDao.addLesson (les5);
            lessonDao.addLesson (les6);
            lessonDao.addLesson (les7);

            lessonDao.deleteLesson (2);
            lessonDao.deleteLesson (4);
            lessonDao.deleteLesson (5);

            // lesson list after delete
            lessonDao.getAllLessons();

            System.out.println(lessonDao.getLessonById(1).toString());
            System.out.println(lessonDao.getLessonById(6).toString());
            System.out.println(lessonDao.getLessonById(7).toString());

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
