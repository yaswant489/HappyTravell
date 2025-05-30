/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.dao;
import happytravell.database.MySqlConnection;
import happytravell.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class UserDao {
    MySqlConnection mySql;

    public UserDao() {
        this.mySql = new MySqlConnection();
    }
}
