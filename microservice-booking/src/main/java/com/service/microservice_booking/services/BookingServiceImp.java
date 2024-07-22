package com.service.microservice_booking.services;

import com.service.microservice_booking.DTOs.BookingDTO;
import com.service.microservice_booking.DTOs.RoomDTO;
import com.service.microservice_booking.DTOs.UserDTO;
import com.service.microservice_booking.clients.RoomClient;
import com.service.microservice_booking.clients.UserClient;
import com.service.microservice_booking.entities.BookingEntity;
import com.service.microservice_booking.repositories.BookingRepository;
import com.service.microservice_booking.requests.CreateBookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService{

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserClient userClient;

    @Autowired
    RoomClient roomClient;

    /**
     * @return List : BookingDTO
     */
    @Override
    public List<BookingDTO> getAll() {
        if(bookingRepository.findAll().isEmpty()) {
            return new ArrayList<BookingDTO>();
        }
        return buildListBookingDTO(bookingRepository.findAll());
    }

    private BookingDTO buildBookingDTO(BookingEntity bookingEntity) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(bookingEntity.getId());
        bookingDTO.setBookingDate(bookingEntity.getBookingDate());
        bookingDTO.setArrivalDate(bookingEntity.getArrivalDate());
        bookingDTO.setDepartureDate(bookingEntity.getDepartureDate());
        bookingDTO.setNights(bookingEntity.getNights());
        bookingDTO.setRoom(roomClient.getRoomById(bookingEntity.getIdRoom()).orElse(null));
        bookingDTO.setUser(userClient.getUser(bookingEntity.getUserNumDoc()).orElse(null));
        return bookingDTO;
    }

    private List<BookingDTO> buildListBookingDTO(List<BookingEntity> bookingEntities) {
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        for (BookingEntity bookingEntity : bookingEntities) {
            bookingDTOs.add(buildBookingDTO(bookingEntity));
        }
        return bookingDTOs;
    }

    /**
     * @param id long
     * @return BookingDTO
     * @throws ClassNotFoundException if booking not found
     */
    @Override
    public BookingDTO getBookingById(long id) throws ClassNotFoundException {
        if(bookingRepository.findById(id).isEmpty()){
            throw new ClassNotFoundException("Booking not found");
        }
        return buildBookingDTO(bookingRepository.findById(id).get());
    }

    /**
     * @param roomNumber int
     * @return List : BookingDTO
     * @throws NullPointerException if roomNumber <= 0
     * @throws ClassNotFoundException if room not found
     */
    @Override
    public List<BookingDTO> getBookingsByRoom(int roomNumber) throws ClassNotFoundException, NullPointerException {
        if (roomNumber <= 0){
            throw new NullPointerException("room number must be greater than 0");
        }
        if(bookingRepository.findAll().isEmpty()) {
            return new ArrayList<>();
        }
        Optional<RoomDTO> roomFind = roomClient.getRoomByRoomNumber(roomNumber);
        if(roomFind.isEmpty()){
            throw new ClassNotFoundException("room not found");
        }
        List<BookingEntity> roomEntities = bookingRepository.findByIdRoom(roomFind.get().getId());
        if(roomEntities.isEmpty()){
            return new ArrayList<>();
        }
        return buildListBookingDTO(roomEntities);
    }

    /**
     * @param date LocalDate
     * @return List : BookingDTO}
     * @throws NullPointerException if date is null
     */
    @Override
    public List<BookingDTO> getBookingsByDate(LocalDate date) throws NullPointerException {
        if (date == null){
            throw new NullPointerException("date cannot be null");
        }
        List<BookingEntity> bookingEntities = bookingRepository.findByBookingDate(date);
        if(bookingEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return buildListBookingDTO(bookingEntities);
    }

    /**
     * @param userNumDoc String
     * @return List : BookingDTO
     * @throws NullPointerException if user document number is null
     * @throws ClassNotFoundException if user not found
     */
    @Override
    public List<BookingDTO> getBookingsByUser(String userNumDoc) throws ClassNotFoundException, NullPointerException {
        if(userNumDoc == null){
            throw new NullPointerException("user doc number must not be null");
        }
        Optional<UserDTO> userFind = userClient.getUser(userNumDoc);
        if(userFind.isEmpty()){
            throw new ClassNotFoundException("user not found");
        }
        List<BookingEntity> bookingEntities = bookingRepository.findByUserNumDoc(userNumDoc);
        if(bookingEntities.isEmpty()){
            return new ArrayList<>();
        }
        return buildListBookingDTO(bookingEntities);
    }

    /**
     * @param request CreateBookingRequest
     * @return int
     * @throws NullPointerException if information is lost
     */
    @Override
    public int addBooking(CreateBookingRequest request) {
        if(request.getArrivalDate() == null || request.getBookingDate() == null || request.getDepartureDate() == null || request.getNights() <= 0 || request.getRoomNumber() <= 0 || request.getUserEmail() == null){
            throw new NullPointerException("all fields must be filled");
        }
        if(request.getUserEmail().isEmpty() || request.getUserEmail().isBlank()){
            throw new NullPointerException("all fields must be filled");
        }
        return 0;
    }

    /**
     * @return
     */
    @Override
    public int updateBooking() {
        return 0;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public int deleteBooking(long id) {
        return 0;
    }
}
