package renotrack.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import renotrack.server.models.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Method to retrieve User Object by userEmail
    public User findUserByEmail(String userEmail) {
        String sql = "SELECT * FROM users WHERE user_email = ?";        
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userEmail);
        if (rs.next()) {
			return User.createUserFromRowSet(rs);
        }
        else {
            return null;
        }
    }

    //Method to retrieve encripted userPassword by userEmail
    public String findPassword(String userEmail) {
        String sql = "SELECT user_password FROM users WHERE user_email = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userEmail);
        if (rs.next()) {
            return rs.getString("user_password");
        } else {
            return null;
        }
    }

    //Method to insert user details into users table for account registration
    //Encript userPassword with BCryptPasswordEncoder
    public int registerUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getUserPassword());

        String sql = "INSERT INTO users (user_id, user_name, user_email, user_password) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, user.getUserId(), user.getUserName(), user.getUserEmail(), encryptedPassword);
    }
    
}