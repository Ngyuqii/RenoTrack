package renotrack.server.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import renotrack.server.models.Inspiration;

@Repository
public class InspirationRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Method to retrieve list of inspirations by userId
    public List<Inspiration> findAllInspo(String userId) {
        List<Inspiration> inspo = new LinkedList<>();
        
        String sql = "SELECT * FROM inspirations WHERE user_id = ?";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);
        while (rs.next()) {
            inspo.add(Inspiration.createInspirationFromRowSet(rs));
        }
        return inspo;
    }

    //Method to insert inspiration into database
    public int insertInspo(String userId, String imageUrl) {
        String sql = "INSERT INTO inspirations (user_id, imageUrl) VALUES (?, ?)";
        return jdbcTemplate.update(sql, userId, imageUrl);
    }

    //Method to delete inspiration from database
    public int deleteInspo(int inspoId) {
        String sql = "DELETE FROM inspirations WHERE inspo_id = ?";
        return jdbcTemplate.update(sql, inspoId);
    }
    
}
