/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package happytravell.database;
import java.sql.*;
/**
 *
 * @author User
 */
public interface DbConnection {
    Connection openConnection();
    void closeConnection(Connection conn);
    
}
