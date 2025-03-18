package dine.swipe.Dine.Service;

import dine.swipe.Dine.Models.GroupPreference;
import dine.swipe.Dine.Models.Rooms;
import dine.swipe.Dine.Repository.GroupPreferenceRepository;
import dine.swipe.Dine.Repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class RoomService {

    @Autowired
    private RoomsRepository roomsRepository;

    @Autowired
    private RedisTemplate<String,Object>redisTemplate;
    @Autowired
    private GroupPreferenceRepository groupPreferenceRepository;

    @Cacheable(value = "rooms" , key = "'room_' + #id")
    public Optional<Rooms> getRoomById(UUID id){
        return roomsRepository.findById(id);
    }

    public List<Rooms> getAllRooms(){
        return roomsRepository.findAll();
    }

    public Optional<Rooms> getAllRoomsCreatedByUserID(String userID){
        return roomsRepository.findByCreatedBy(userID);
    }

    public Optional<String> getGroupPreferences(UUID groupId){
        return groupPreferenceRepository.findByGroupId(groupId)
                .map(GroupPreference::getGroupPreference);
    }

    @CachePut(value="rooms" , key = "'room_' +#result.roomId ")
    public Rooms saveRoom(Rooms room){
        UUID roomId = UUID.randomUUID();
        room.setRoomId(roomId);
        room.setJoiningLink("http://localhost:8083/rooms/join/"+roomId);
        return roomsRepository.save(room);
    }

    public GroupPreference saveGroupPreference(GroupPreference groupPreference){
        return groupPreferenceRepository.save(groupPreference);
    }

    public Object joinRoom(UUID id){
        Set<String> object= redisTemplate.keys("rooms::");
        return object;
    }
}
