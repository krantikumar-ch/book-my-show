package com.examples.lld.bms.services;

import com.examples.lld.bms.exceptions.SeatsUnavailableException;
import com.examples.lld.bms.exceptions.UserNotFoundException;
import com.examples.lld.bms.models.*;
import com.examples.lld.bms.repositories.BookingRepository;
import com.examples.lld.bms.repositories.ShowRepository;
import com.examples.lld.bms.repositories.ShowSeatRepository;
import com.examples.lld.bms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.examples.lld.bms.exceptions.ShowNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.examples.lld.bms.models.showSeatStatus.AVAILABLE;
import static com.examples.lld.bms.models.showSeatStatus.BLOCKED;


@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private ShowSeatRepository showSeatRepository;
    private ShowRepository showRepository;
    private PriceCalculator priceCalcualtor;

    private static final int BLOCKED_TIME = 15;

    @Autowired
    public BookingService(BookingRepository bookingRepository, UserRepository userRepository,
                          ShowSeatRepository showSeatRepository, ShowRepository showRepository,
                          PriceCalculator priceCalcualtor){
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.showSeatRepository = showSeatRepository;
        this.showRepository = showRepository;
        this.priceCalcualtor = priceCalcualtor;
    }

    /**
     * 1.get user by userId
     * 2. show by showId
     *  ---------------- TAKE LOCK ---------------------
     * 3. get show seats with seatId
     * 4. check  if all show seats are aviilable
     * 5. if no throw error
     * 6. if yes mark the status is locked
     * 7. save updated show seat to DB
     *  ----------------END LOCK -------------------------
     * 8 return

     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, List<Long> seatIds, Long showId){

        Optional<User> userOptional =  userRepository.findById(userId);
        if(userOptional.isEmpty()){
          throw new UserNotFoundException();
        }
        User bookedBy = userOptional.get();

        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new ShowNotFoundException();
        }

        Show bookedShow = showOptional.get();

        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);


        for(ShowSeat showSeat: showSeats){
            if(showSeat.getShowSeatStatus().equals(AVAILABLE)
                || (showSeat.getShowSeatStatus().equals(BLOCKED) && isDateGreaterThanBlockedTime(showSeat.getBlockedAt()))){
                showSeat.setShowSeatStatus(BLOCKED);
            }
            else{
                throw new SeatsUnavailableException();
            }
        }

       List<ShowSeat> savedSeats = showSeatRepository.saveAll(showSeats);

        Booking booking = new Booking();
        booking.setBookingstatus(BookingStatus.PENDING);
        booking.setShowSeats(savedSeats);
        booking.setUser(bookedBy);
        booking.setBookedAt(new Date());
        booking.setShow(bookedShow);

        Booking savedBooking = bookingRepository.save(booking);
        return savedBooking;
    }

    private boolean isDateGreaterThanBlockedTime(Date date){
        return Duration.between(date.toInstant(), new Date().toInstant()).toMinutes() > 15;
    }
}
