package com.Gongdae9.room.service;


import com.Gongdae9.room.domain.Room;
import com.Gongdae9.room.repository.RoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public boolean createRoom(Room room){
        return roomRepository.save(room);
    }

    public Room findById(Long id){ return roomRepository.findById(id);}

    public List<Room> findAll(){return roomRepository.findAll();}
}
