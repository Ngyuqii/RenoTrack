package renotrack.server.repositories;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmailVerificationRepository {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    //Save userEmail - verificationCode key value pair in Redis
    public int saveToRedis(String userEmail, String verificationCode) {

        //Save verification code in Redis
        //Data will only be saved for 3minutes
        redisTemplate.opsForValue().set(userEmail, verificationCode, 3, TimeUnit.MINUTES);

        //Check if data is saved
        String result = (String) redisTemplate.opsForValue().get(userEmail);
        if (result != null) {
            return 1;
        }
        else {
            return 0;
        }
    
    }

    //Retrieve the verificationCode of userEmail
    public String getFromRedis(String userEmail) {
        String verificationCode = (String) redisTemplate.opsForValue().get(userEmail);
        return verificationCode;
    }
    
}