package renotrack.server.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import renotrack.server.models.Event;

@Repository
public class EventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //Method to retrieve list of events by userId
    public List<Event> getAllEvents(String userId) {
        List<Event> events = new LinkedList<>();

        String sql = "SELECT * FROM events WHERE user_id = ?";        
        SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, userId);
        while(rs.next()){
            events.add(Event.createEventFromRowSet(rs));
        }
        return events;
    }
    
    //Method to insert events into database
    public int createEvent(String userId, Event event) {
        String sql = "INSERT INTO events (user_id, event_id, subject, start_time, end_time, description, location) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, userId, event.getEventId(), event.getSubject(), event.getStartTime(), event.getEndTime(), event.getDescription(), event.getLocation());
    }
    
    //Method to update event in database
    public int updateEvent(String userId, int eventId, Event event) {
        String sql = "UPDATE events SET subject = ?, start_time = ?, end_time = ?, description = ?, location = ? WHERE user_id = ? AND event_id = ?";
        return jdbcTemplate.update(sql, event.getSubject(), event.getStartTime(), event.getEndTime(), event.getDescription(), event.getLocation(), userId, eventId);
    }

    //Method to delete event from database
    public int deleteEvent(String userId, int eventId) {
        String sql = "DELETE FROM events WHERE user_id = ? AND event_id = ?";
        return jdbcTemplate.update(sql, userId, eventId);
    }   
    
}