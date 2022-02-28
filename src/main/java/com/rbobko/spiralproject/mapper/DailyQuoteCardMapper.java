package com.rbobko.spiralproject.mapper;

import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.model.dto.DailyQuoteCardUpdateDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DailyQuoteCardMapper {

    DailyQuoteCard toEntity(DailyQuoteCardUpdateDTO dto);

}
