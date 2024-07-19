package com.service.microservice_room.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "room")
public class RoomEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private short capacity;
    @Column(unique = true, nullable = false)
    private int roomNumber;
    private int floor;
    @Column(nullable = false,length = 50)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_roomType")
    private TypeRoomEntity roomType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_state")
    private StateEntity state;


}
