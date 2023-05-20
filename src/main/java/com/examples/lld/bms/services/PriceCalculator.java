package com.examples.lld.bms.services;

import com.examples.lld.bms.models.Show;
import com.examples.lld.bms.models.ShowSeat;
import com.examples.lld.bms.models.ShowSeatType;
import com.examples.lld.bms.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PriceCalculator {

    @Autowired
    private ShowSeatTypeRepository showSeatTypeRepository;

    /**
     * get show seat type for that show
     * get seat type for all seats
     * add amount of all
     * @param showSeats
     * @param show
     * @return
     */
    public int calculatePrice(List<ShowSeat> showSeats, Show show ){
      int price = 0;
      List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
      for(ShowSeat showSeat: showSeats){
        price += showSeatTypes.stream().filter(showSeatType -> showSeatType.getSeatType().equals(showSeat.getSeat().getSeatType()))
                .mapToInt(ShowSeatType::getPrice).sum();
      }

      return price;

    }

}
