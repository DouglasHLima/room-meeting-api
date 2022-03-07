package com.doughlima.roommeetingapi.controllers;

import com.doughlima.roommeetingapi.exceptions.ResourceNotFoundException;
import com.doughlima.roommeetingapi.model.Room;
import com.doughlima.roommeetingapi.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RoomController {

    RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long roomId)
        throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not Found:: "+ roomId));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping("/rooms")
    public Room createRoom(@Valid @RequestBody Room room){
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value = "id") Long roomid,
                                           @Valid @RequestBody Room roomDetails)
        throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomid)
                .orElseThrow(() -> new ResourceNotFoundException("Room not Found for this id:: "+roomid));
        BeanUtils.copyProperties(room,roomDetails,"id");
        final Room updatedRoom = roomRepository.save(room);
        return  ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("rooms/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable(value = "id") Long roomId)
        throws ResourceNotFoundException{
        Room room = roomRepository.findById(roomId)
                .orElseThrow( () -> new ResourceNotFoundException("Room not Found for this id:: "+roomId));
        roomRepository.delete(room);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
