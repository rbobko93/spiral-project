package com.rbobko.spiralproject.mapper;

import com.rbobko.spiralproject.model.DailyQuoteCard;
import com.rbobko.spiralproject.model.Quote;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuoteMapper {

    DailyQuoteCard toCard(Quote quote);

}
