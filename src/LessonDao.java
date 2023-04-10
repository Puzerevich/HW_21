import ConnectionDB.DataBaseConnection;
import Tables.Homework;
import Tables.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDao {

    private List<Lesson> lessList = new ArrayList<>();
    private Connection connection;

    public LessonDao(Connection connection) {
        this.connection = connection;
    }

    public static void addLesson(Lesson lesson) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DataBaseConnection.getConnection();
            String sql = "INSERT INTO src.tables.Lesson (id, name, updatedAt, homework_id) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, lesson.getId());
            ps.setString(2, lesson.getName());
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            ps.setInt(4, lesson.getHomework().getId());
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            DataBaseConnection.close(conn);
        }
    }

    public void deleteLesson(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DataBaseConnection.getConnection();
            String sql = "DELETE FROM src.tables.Lesson WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
            DataBaseConnection.close(conn);
        }
    }

    // Get Lesson list
    public List<Lesson> getAllLessons() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Lesson> lessons = new ArrayList<>();
        try {
            conn = DataBaseConnection.getConnection();
            String sql = "SELECT * FROM src.tables.Lesson JOIN src.tables.Homework ON src.tables.Lesson.homework_id = src.tables.Homework.id";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int lessonId = rs.getInt(1);
                String lessonName = rs.getString(2);
                Timestamp updatedAt = rs.getTimestamp(3);
                int homeworkId = rs.getInt(4);
                String homeworkName = rs.getString(5);
                String homeworkDescription = rs.getString(6);
                Homework homework = new Homework(homeworkId, homeworkName, homeworkDescription);
                Lesson lesson = new Lesson(lessonId, lessonName, homework);
                lessons.add(lesson);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            DataBaseConnection.close(conn);
        }
        lessList = lessons;

        for (Lesson les : lessList) {
            System.out.println(les.toString());
        }
        return lessList;
    }

// Get Lesson by ID
    public Lesson getLessonById(int id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DataBaseConnection.getConnection();

            String sql = "SELECT * FROM src.tables.Lesson JOIN src.tables.Homework ON src.tables.Lesson.homework_id = src.tables.Homework.id" +
                         "WHERE src.tables.Lesson.id= ?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int lessonId = rs.getInt(1);
                String lessonName = rs.getString(2);
                Timestamp updatedAt = rs.getTimestamp(3);
                int homeworkId = rs.getInt(4);
                String homeworkName = rs.getString(5);
                String homeworkDescription = rs.getString(6);
                Homework homework = new Homework(homeworkId, homeworkName, homeworkDescription);
                return new Lesson(lessonId, lessonName, homework);
            } else {
                return null;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            DataBaseConnection.close(conn);
        }
    }
}
